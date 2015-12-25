package com.sleek.CDStore.database;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import com.sleek.CDStore.model.*;

/**
 * This class handles the database query transactions
 * Uses:  QueryStore class, ConnectPool, and other java helper classes.
 * NOTE: StartTransaction and EndTransaction are implemented by Connection class method setAutoCommit(Boolean).
 */
public class DatabaseAgent {

	protected ConnectPool connectionPool;
	protected Connection connection;
	protected QueryStore queryStore;


	public DatabaseAgent()
	{
		queryStore = new QueryStore(); 

	}

	public ResultSet getQueryResult(String queryId, List<?> parameterList)

	{
		ResultSet queryResultSet = null;

		connectionPool = ConnectPool.getInstance();
		String queryStatement = queryStore.getQuery(queryId);
		System.out.println(queryStatement);
		PreparedStatement prepareStatement = null;

		try {
			connection = connectionPool.connect();
			connection.setAutoCommit(false);
			prepareStatement = connection.prepareStatement(queryStatement);
			int parameterNumber = 0;
			if (parameterList != null) {
				for (Object object : parameterList) {
					parameterNumber = parameterList.indexOf(object) + 1; 
					// parameterNumber =the right order of the current parameter
					// (return 1 for the first parameter)
					if (object instanceof String) {
						prepareStatement.setString(parameterNumber,
								(String) object);
					} else {
						prepareStatement.setObject(parameterNumber, object);
					}

				}
			}
			queryResultSet = prepareStatement.executeQuery();
			connection.commit();
			connection.setAutoCommit(true);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			closeConnectionPool();
		}

		return queryResultSet;
	}

	public void closeConnectionPool() {

		if (connectionPool != null) {
			try {
				connectionPool.close();
			} catch (NamingException e) {
				e.printStackTrace();
			}
		}
	}
	public ResultSet getQueryResult(String queryId, int parameter)
	{
		ArrayList<Integer> tempIntegerArray= new ArrayList<Integer>();
		tempIntegerArray.add(parameter);
		return this.getQueryResult(queryId,tempIntegerArray);
	}
	public ResultSet getQueryResult(String queryId, String parameter)
	{
		ArrayList<String> tempStringArray= new ArrayList<String>();
		tempStringArray.add(parameter);
		return this.getQueryResult(queryId,tempStringArray);
	}
	public ResultSet getQueryResult(String queryId)
	{
		List<?> nullList =null;
		return this.getQueryResult(queryId, nullList);
	}
	/*
	 * execute more than one query transaction (either all committed to the database or all rolled back)
	 * returns the number of affected rows
	 */
	public int executeSQL(Map <String, ArrayList<String>> queries)
	{
		int updatedRows = 0;
		try {
			connectionPool = ConnectPool.getInstance();
			connection = connectionPool.connect();
			connection.setAutoCommit(false);
			PreparedStatement prepareStatement = null;
			Iterator<Entry<String, ArrayList<String>>> iter = queries
					.entrySet().iterator();
			String queryId;
			String queryStatement;
			ArrayList<String> parameterList = null;
			int parameterNumber = 0;
			Entry<String, ArrayList<String>> entry =null;
			while (iter.hasNext()) {

				entry = iter.next();
				queryId = entry.getKey();
				queryStatement = queryStore.getQuery(queryId);
				prepareStatement = connection.prepareStatement(queryStatement);
				parameterList = entry.getValue();
				for (Object object : parameterList) {
					parameterNumber = parameterList.indexOf(object) + 1;
					prepareStatement
							.setString(parameterNumber, (String) object);
				}
				prepareStatement.execute();
				updatedRows += prepareStatement.getUpdateCount();
			}
			connection.commit();
			connection.setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}finally
		{
			closeConnectionPool();
		}

		return updatedRows;
	}
	/*
	 * returns the number of affected rows
	 */
	public int executeSQL(String queryId, List<?> parameterList)
	{
		int updatedRows = 0;
		connectionPool = ConnectPool.getInstance();
		String queryStatement = queryStore.getQuery(queryId);
		PreparedStatement prepareStatement =null;
		try {
			connection = connectionPool.connect();
			connection.setAutoCommit(false);
			prepareStatement = connection.prepareStatement(queryStatement); 
			int parameterNumber = 0;
			if(parameterList != null)
			{
				for (Object object : parameterList) {
					parameterNumber = parameterNumber +1; // parameterNumber = the right order of the current parameter ( return 1 for the first parameter)
					System.out.println(parameterNumber+":"+(String)object);
					prepareStatement.setString(parameterNumber, (String)object);
					}				
			}
			prepareStatement.execute();
			connection.commit();
			updatedRows = prepareStatement.getUpdateCount();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch blockget
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return updatedRows;

	}
	public int executeSQL(String queryID)
	{
		return this.executeSQL(queryID,null);
	}

	/**
	 * get a category according to its ID
	 * @param the ID of the category
	 * @return CategoryBean of the category
	 */
	public CategoryBean getCategory(int CategoryId)
	{
		String queryId ="GET_CATEGORY_BY_ID";
		CategoryBean categoryBean =null;
		ResultSet categoryResultSet = this.getQueryResult(queryId, CategoryId);
		if (categoryResultSet !=null) {
			try {
				if (categoryResultSet.next() == true) {
					categoryBean = new CategoryBean();
					categoryBean.setId(categoryResultSet.getInt("id"));
					categoryBean.setCategoryName(categoryResultSet
							.getString("category"));

				}
			
				//Close ResultSet
				categoryResultSet.close();
				
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return categoryBean;

	}
	/*
	 *  get a list of all categories
	 *  @return a list of categoryBean of all categories
	 * */
	public List<CategoryBean> getCategoryList()
	{
		String queryId = "GET_ALL_CATEGORY";
		System.out.println ("GET_ALL_CATEGORY");
		ResultSet categoryResultSet ;
		ArrayList <CategoryBean> categoryList = null; 
		CategoryBean categoryBean;
		categoryResultSet = this.getQueryResult(queryId);
		if(categoryResultSet!=null)
		{
			categoryList = new ArrayList<CategoryBean>();

		}
		try {
			while(categoryResultSet.next())
			{
				categoryBean = new CategoryBean();
				categoryBean.setId(categoryResultSet.getInt("id"));
				categoryBean.setCategoryName(categoryResultSet.getString("category"));
				categoryList.add(categoryBean);

			}
			//Close ResultSet
			categoryResultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return categoryList;
	}
	/**
	 * get a list of all CDs
	 * @return a list of CDBean of all CDs
	 */
	public List<CDBean> getCDList(){
		return this.getCDList(0); // if no parameter, return all CDs
	}
	/**
	 * get a list of CDs belong to a certain category
	 * @param the ID of the category. If ID is 0, all CDs will be returned
	 * @return a list of CDBean of the CDs in a category
	 */
	public List<CDBean> getCDList(int categoryId) // categoryId
	{
		String queryId;
		ResultSet CDresultSet;
		if (categoryId == 0)
		{
			queryId ="GET_ALL_CD";
			CDresultSet = this.getQueryResult(queryId); 

		}
		else
		{
			queryId ="GET_CD_BY_CATEGORY";
			ArrayList<Integer> array = null;
			array = new ArrayList<Integer>();
			array.add(new Integer(categoryId));
			CDresultSet = this.getQueryResult(queryId, array); 
		}

		ArrayList<CDBean> CDBeanList = null;

		if(CDresultSet!=null)
		{
			CDBeanList = new ArrayList<CDBean>();

			ArrayList<CategoryBean> categoryList= (ArrayList<CategoryBean>) this.getCategoryList(); // All categories
			//this hash table to reduce the database calls
			Hashtable<Integer, String> tempCategoryHashtable = new Hashtable<Integer,String>();
			// if there is cdID, just get its category from the method getCategory(categoryID)
			if(categoryId == 0)
			{			
				for (CategoryBean categoryBean : categoryList) {
					tempCategoryHashtable.put(new Integer (categoryBean.getId()), categoryBean.getCategoryName());
				}

			} else
			{
				// there is one category
				tempCategoryHashtable.put(categoryId,this.getCategory(categoryId).getCategoryName() );
			}

			try {
				while(CDresultSet.next())
				{
					CDBean cdBean = new CDBean();
					cdBean.setId(CDresultSet.getInt("id"));
					cdBean.setTitle(CDresultSet.getString("title"));
					cdBean.setArtist(CDresultSet.getString("artist"));
					cdBean.setDescription(CDresultSet.getString("description"));
					cdBean.setPrice(CDresultSet.getDouble("price"));		
					cdBean.setImage(CDresultSet.getString("Image"));
					cdBean.setCategory((String) tempCategoryHashtable.get(CDresultSet.getInt("category_id")) );	
					CDBeanList.add(cdBean);

				}
				//Close ResultSet
				CDresultSet.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return CDBeanList;

	}
	/**
	 * get a list of all tracks
	 * @return a list of TrackBean of all tracks
	 */
	public List<TrackBean> getTrackList(){
		return this.getTrackList(0); // if no parameter, return all Tracks
	}
	/**
	 * get a list of the tracks in a CD
	 * @param the ID of a CD. If the ID is 0, all tracks will be returned
	 * @return a list of TrackBean of the tracks in a CD
	 */

	public List<TrackBean> getTrackList(int CDId) // CDId
	{
		String queryId;
		ResultSet TrackresultSet;
		if (CDId == 0)
		{
			queryId ="GET_ALL_Track";
			TrackresultSet = this.getQueryResult(queryId); 

		}
		else
		{
			queryId ="GET_Track_BY_CD";
			ArrayList<Integer> array = null;
			array = new ArrayList<Integer>();
			array.add(new Integer(CDId));
			TrackresultSet = this.getQueryResult(queryId, array); 
		}

		ArrayList<TrackBean> TrackBeanList = null;

		if(TrackresultSet!=null)
		{
			TrackBeanList = new ArrayList<TrackBean>();

			ArrayList<CDBean> CDList= (ArrayList<CDBean>) this.getCDList(); // All categories
			//this hash table to reduce the database calls
			Hashtable<Integer, String> tempCDHashtable = new Hashtable<Integer,String>();
			// if there is TrackId, just get its CD title from the method getTitle(CDId)
			if(CDId == 0)
			{			
				for (CDBean CDBean : CDList) {
					tempCDHashtable.put(new Integer (CDBean.getId()), CDBean.getTitle());
				}

			} else
			{
				// there is one CD
				tempCDHashtable.put(CDId,this.getCDinfo(CDId).getTitle());
			}
			try {
				while(TrackresultSet.next())
				{
					TrackBean trackBean = new TrackBean();
					trackBean.setId(TrackresultSet.getInt("id"));
					trackBean.setTrackName(TrackresultSet.getString("name"));
					trackBean.setCD((String) tempCDHashtable.get(TrackresultSet.getInt("CD_id")) );	
					TrackBeanList.add(trackBean);
				}
				//Close ResultSet
				TrackresultSet.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return TrackBeanList;

	}
	/**
	 * get the information of a CD
	 * @param the ID of the CD
	 * @return a CDBean of a CD
	 */
	public CDBean getCDinfo(int cdId)
	{
		CDBean cdBean = null;
		String queryId ="GET_CD_BY_ID";
		ResultSet CDresultSet = this.getQueryResult(queryId, cdId);
		try {
			if (CDresultSet.next())
			{
				cdBean = new CDBean();
				cdBean.setTitle(CDresultSet.getString("title"));
				cdBean.setId(CDresultSet.getInt("id"));			
				cdBean.setArtist(CDresultSet.getString("artist"));
				cdBean.setDescription(CDresultSet.getString("description"));
				cdBean.setPrice(CDresultSet.getDouble("price"));		
				cdBean.setImage(CDresultSet.getString("Image"));
				cdBean.setCategory(this.getCategory(CDresultSet.getInt("category_id")).getCategoryName());

			}
			//Close ResultSet
			CDresultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return cdBean;

	}
	/**
	 * get the information of an Account
	 * @param the ID of the Account
	 * @return a AccountBean of a CD
	 */
	public AccountBean getAccount(int accountId)
	{
		return this.getAccount(accountId, "");

	}
	/**
	 * get the information of an Account
	 * @param the user name of the Account
	 * @return a AccountBean of a CD
	 */
	public AccountBean getAccount(String username)
	{

		return this.getAccount(0, username);

	}
	/**
	 * get the information of an Account
	 * @param the ID and user name of the Account
	 * @return a AccountBean of a CD
	 */
	public AccountBean getAccount(int accountId, String username)
	{
		AccountBean accountBean = null;
		String queryId;
		ResultSet rs;
		if (accountId > 0 &&  ( username !=null && username.equals("")))
		{
			queryId = "GET_ACCOUNT_BY_USERNAME_AND_PASSWORD";
			ArrayList<Object> parameterList = new ArrayList<Object>();
			parameterList.add(accountId);
			parameterList.add(username);
			rs = this.getQueryResult(queryId, parameterList);
		}
		else if (accountId > 0 )
		{
			queryId = "GET_ACCOUNT_BY_ID";
			rs = this.getQueryResult(queryId,accountId);

		}
	/*	//////////////////This is test!!!!!!!!!!!!!!!/////////////////////////
		else if(accountId == 0)
		{
			queryId="GET_ACCOUNT_BY_USERNAME";
			rs=this.getQueryResult(queryId, username);
		}
		/////////////////////////////////////////////////////////////////////*/
		else
		{
			queryId = "GET_ACCOUNT_BY_EMAIL";
			rs = this.getQueryResult(queryId, username);

		}

		try {
			if(rs.next())
			{
				accountBean = new AccountBean();
				accountBean.setId(rs.getInt("id"));
				accountBean.setUsername(rs.getString("username"));
				accountBean.setPassword(rs.getString("password"));
				accountBean.setFirstname(rs.getString("firstname"));
				accountBean.setLastname(rs.getString("lastname"));
				accountBean.setEmail(rs.getString("email"));
				accountBean.setPhone(rs.getString("phone"));

			}
			// Close ResultSet
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return  accountBean ;

	}
	/**
	 * get the information of an Account
	 * @param the user name and password of the Account
	 * @return a AccountBean of a CD
	 */
	public AccountBean getAccount(String username, String password)
	{
		AccountBean accountBean = null;
		String queryId;
		ResultSet rs;
		if (! username.equals("") && ! password.equals(""))
		{
			queryId = "GET_ACCOUNT_BY_USERNAME_AND_PASSWORD";
			ArrayList<String> parameterList = new ArrayList<String>();
			parameterList.add(username);
			parameterList.add(password);
			rs = this.getQueryResult(queryId, parameterList);
			try {
				if(rs.next())
				{
					
					accountBean = new AccountBean();
					accountBean.setId(rs.getInt("id"));
					accountBean.setUsername(rs.getString("username"));
					accountBean.setPassword(rs.getString("password"));
					accountBean.setFirstname(rs.getString("firstname"));
					accountBean.setLastname(rs.getString("lastname"));
					accountBean.setEmail(rs.getString("email"));
				}
				// Close ResultSet
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return  accountBean ;
	}
	/**
	 * get the id for a new ordered_items
	 * @return the new id, current max id +1
	 */
	public int getNewOrderedItemId(){
		String queryId ="GET_PURCHASE_ORDER_ITEMS_MAX_ID";
		ResultSet newOrderResultSet = this.getQueryResult(queryId);
		int id=-1;
		try{
			if(newOrderResultSet.next()){
				id=newOrderResultSet.getInt(1)+1;
			}
			newOrderResultSet.close();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id;
	}
	/**
	 * get the id for a new purchase order
	 * @return the new id, current max id +1
	 */
	public int getNewOrderId(){
		String queryId ="GET_PURCHASE_ORDER_MAX_ID";
		ResultSet newOrderResultSet = this.getQueryResult(queryId);
		int id=-1;
		try{
			if(newOrderResultSet.next()){
				id=newOrderResultSet.getInt(1)+1;
			}
			newOrderResultSet.close();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id;
	}
	/**
	 * get the id for a new account
	 * @return the new id, current max id +1
	 */
	public int getNewAccountId(){
		String queryId ="GET_NEW_ACCOUNT";
		ResultSet newAccountResultSet = this.getQueryResult(queryId);
		int id=-1;
		try{
			if(newAccountResultSet.next()){
				id=newAccountResultSet.getInt(1)+1;
			}
			newAccountResultSet.close();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return id;
	}
	/**
	 * get the id for a new address
	 * @return the new id, current max id +1
	 */
	public int getNewAddressId()
	{
		String queryId ="GET_NEW_ADDRESS";
		ResultSet newAccountResultSet = this.getQueryResult(queryId);
		int id=-1;
		try{
			if(newAccountResultSet.next()){
				id=newAccountResultSet.getInt(1)+1;
			}
			newAccountResultSet.close();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return id;
	}
	/**
	 * get the id of a user's specified address
	 * @param the user id and address type
	 * @return the address id
	 */
	public int getAddressId(int id, String type)
	{
		String queryId ="GET_ADDRESS_ID";
		int returnId = -1;
		ArrayList<String> parameterList = new ArrayList<String>();
		parameterList.add(""+id);
		parameterList.add(type);
		ResultSet newAccountResultSet = this.getQueryResult(queryId,parameterList);
		
		try{
			if(newAccountResultSet.next()){
				returnId=newAccountResultSet.getInt(1);
			}
			newAccountResultSet.close();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return returnId;
	}
	/**
	 * get the id of a user
	 * @param the user name
	 * @return the account id
	 */
	public int getAccountId(String username){
		String queryId ="GET_ACCOUNT_BY_USER";
		ResultSet newAccountResultSet = this.getQueryResult(queryId, username);
		int id=-1;
		try{
			if(newAccountResultSet.next()){
				id=newAccountResultSet.getInt(1);
			}
			newAccountResultSet.close();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return id;
	}
	/**
	 * create a new account
	 * @param the user account bean
	 * @return whether succeed
	 */
	public int createAccount(AccountBean accountBean)
	{ 
		int resultProcess= 0; 
		AccountBean tempAccountBean = this.getAccount(accountBean.getUsername());
		if( tempAccountBean != null)
		{
			resultProcess = 2; // there is a user has the name
		}
		else
		{
			ArrayList<String> parameterList = new ArrayList<String>();
			System.out.println(accountBean.getId()+"");
			System.out.println(accountBean.getUsername());
			System.out.println(accountBean.getPassword());
			System.out.println(accountBean.getFirstname());
			System.out.println(accountBean.getLastname());
			System.out.println(accountBean.getEmail());
			System.out.println(accountBean.getPhone());
			
			parameterList.add((accountBean.getId()+""));
			parameterList.add(accountBean.getUsername());
			parameterList.add(accountBean.getPassword());
			parameterList.add(accountBean.getFirstname());
			parameterList.add(accountBean.getLastname());
			parameterList.add(accountBean.getEmail());
			parameterList.add(accountBean.getPhone());

			resultProcess = this.executeSQL("ADD_ACCOUNT", parameterList); // Process done successfully
		}
		return resultProcess;
	}
	/**
	 * change date to String for database 
	 * @param the date
	 * @return date in String
	 */
	public String getStringDate(java.util.Date date)
	{
		java.text.SimpleDateFormat simpleDateFormatObj = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return simpleDateFormatObj.format(date);
	}
	/**
	 * change String to date 
	 * @param date in String
	 * @return date
	 */
	public java.util.Date getDateFromStringDate(String dateString)
	{
		java.util.Date date = null;
		java.text.SimpleDateFormat simpleDateFormatObj = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			date = simpleDateFormatObj.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
		 
	}
	/**
	 * create a new order
	 * @param the order info and account info
	 * @return whether succeed
	 */
	public int createOrder(PurchaseOrderBean purchaseOrderBean, AccountBean accountBean)
	{
		int resultProcess= 0; 
		ArrayList<String> parameterList = new ArrayList<String>();
		parameterList.add(this.getStringDate(purchaseOrderBean.getCreated())); 
		parameterList.add(""+purchaseOrderBean.getShippingAmount());  
		parameterList.add(""+purchaseOrderBean.getTaxFactor());
		parameterList.add(purchaseOrderBean.getStatus());
		parameterList.add(""+purchaseOrderBean.getTotalAmount());
		parameterList.add(""+accountBean.getId());
		
		resultProcess = this.executeSQL("ADD_PURCHASE_ORDER", parameterList); 

		return resultProcess;

	}
	/**
	 * create a new ordered item
	 * @param the ordered item bean
	 * @return whether succeed
	 */
	public int createOrderedItem(OrderedItemBean orderedItem){
		int resultProcess= 0; 

		System.out.println("here1 "+orderedItem.getPurchaseOrderId()+" "+orderedItem.getCdId()+" "+orderedItem.getQuantity()+" "+orderedItem.getSaleFactor());
		ArrayList<String> parameterList = new ArrayList<String>();	
		parameterList.add(""+orderedItem.getId());
		parameterList.add(""+orderedItem.getPurchaseOrderId());
		parameterList.add(""+orderedItem.getCdId());
		parameterList.add(""+orderedItem.getQuantity());
		parameterList.add(""+orderedItem.getSaleFactor());
			 			 
		this.executeSQL("ADD_ORDERED_ITEMS", parameterList);
		return resultProcess;
	}
	/**
	 * confirm the payment of an order
	 * @param the order id
	 * @return whether succeed
	 */
	public int confirmOrder (int id)
	{
		
		int resultProcess= 0; 
		ArrayList<String> parameterList1 = new ArrayList<String>();
		 parameterList1.add("paid");
		 parameterList1.add(""+id);
		
		 resultProcess = this.executeSQL("UPDATE_PURCHASE_ORDER_STATUS_BY_ID",parameterList1);
		 return resultProcess;
	}
	/**
	 * create a new address
	 * @param the address bean
	 * @return whether succeed
	 */
	public int createAddress(AddressBean addressBean)
	{
		
		int resultProcess= 0; 

		ArrayList<String> parameterList = new ArrayList<String>();
		parameterList.add(addressBean.getType());
		parameterList.add(addressBean.getStreet());
		parameterList.add(addressBean.getProvince());
		parameterList.add(addressBean.getZip());
		parameterList.add(addressBean.getCity());
		parameterList.add(addressBean.getCountry());
		parameterList.add(addressBean.getAccountId()+"");

		resultProcess = this.executeSQL("ADD_ADDRESS", parameterList); // Process done successfully

		return resultProcess;
	}
	
	/**
	 * update user's address
	 * @param the user address bean
	 * @return whether succeed
	 */
	public int updateAddress (AddressBean addressBean)
	{
		
		int resultProcess= 0; 
		ArrayList<String> parameterList = new ArrayList<String>();
		parameterList.add(addressBean.getStreet());
		parameterList.add(addressBean.getProvince());
		parameterList.add(addressBean.getZip());
		parameterList.add(addressBean.getCity());
		parameterList.add(addressBean.getCountry());
		parameterList.add(addressBean.getAccountId()+"");
		parameterList.add(addressBean.getType());

		resultProcess = this.executeSQL("UPDATE_ADDRESS", parameterList); // Process done successfully

		return resultProcess;
	}
	/**
	 * update a account (name and email only)
	 * @param the user account bean
	 * @return whether succeed
	 */
	public int updateAccount (AccountBean account)
	{
		
		int resultProcess= 0; 
		ArrayList<String> parameterList = new ArrayList<String>();
		parameterList.add(account.getFirstname());
		parameterList.add(account.getLastname());
		parameterList.add(account.getPhone());
		parameterList.add(""+account.getId());

		resultProcess = this.executeSQL("UPDATE_ACCOUNT", parameterList); // Process done successfully

		return resultProcess;
	}
	/**
	 * get the user's spicified type address
	 * @param the user id and  address type
	 * @return the address
	 */
	 public AddressBean getAddress (int id, String type)
	 {
		 AddressBean addressBean = new AddressBean();
		 if(type.equals("shipping"))
		 {
			 addressBean = this.getShippingAdress(id);
		 }
		 else if(type.equals("billing"))
		 {
			 addressBean = this.getBillingAddress(id);
		 }
			 
		return addressBean;
	}
	 /**
		 * get the user's addresses
		 * @param the user id 
		 * @return the shipping and billing address
		 */
	 public List<AddressBean> getAddress (int id)
	 {
		 ArrayList<AddressBean> addressBeanList = new ArrayList<AddressBean>();
		 AddressBean addressBean = this.getShippingAdress(id);
		 addressBeanList.add(addressBean);
		 addressBean = this.getBillingAddress(id);
		 addressBeanList.add(addressBean);
		return addressBeanList;
	}
	 /**
		 * get the user's shipping addresses
		 * @param the user id 
		 * @return the shipping address
		 */
	 public AddressBean getShippingAdress (int id)
	 {
		 AddressBean addressBean = null;
			String queryId;
			ResultSet rs;
		
			queryId = "GET_SHIPPING_ADDRESS_BY_ID";
			ArrayList<String> parameterList = new ArrayList<String>();
			parameterList.add(""+id);
			rs = this.getQueryResult(queryId, parameterList);
			try {
				if(rs.next())
				{
						
					addressBean = new AddressBean();
					addressBean.setType(rs.getString("type"));
					addressBean.setStreet(rs.getString("street"));;
					addressBean.setCity(rs.getString("city"));
					addressBean.setProvince(rs.getString("province"));
					addressBean.setZip(rs.getString("zip"));
					addressBean.setCountry(rs.getString("country"));
					addressBean.setId(Integer.parseInt(rs.getString("account_id")));
				}
					// Close ResultSet
				rs.close();
			} catch (SQLException e) {
					// TODO Auto-generated catch block
				e.printStackTrace();
		}
		return addressBean;
	}

	 /**
		 * get the user's billing addresses
		 * @param the user id 
		 * @return the billing address
		 */
	
	 public AddressBean getBillingAddress (int id)
	 {
		 AddressBean addressBean = null;
			String queryId;
			ResultSet rs;
		
			queryId = "GET_BILLING_ADDRESS_BY_ID";
			ArrayList<String> parameterList = new ArrayList<String>();
			parameterList.add(""+id);
			rs = this.getQueryResult(queryId, parameterList);
			try {
				if(rs.next())
				{
						
					addressBean = new AddressBean();
					addressBean.setType(rs.getString("type"));
					addressBean.setStreet(rs.getString("street"));;
					addressBean.setCity(rs.getString("city"));
					addressBean.setProvince(rs.getString("province"));
					addressBean.setZip(rs.getString("zip"));
					addressBean.setCountry(rs.getString("country"));
					addressBean.setId(Integer.parseInt(rs.getString("account_id")));
				}
					// Close ResultSet
				rs.close();
			} catch (SQLException e) {
					// TODO Auto-generated catch block
				e.printStackTrace();
		}
		return addressBean;
	 }
	 /**
		 * getNewOrder() is written for testing the result of creatOrder()
		 * this function returns the latest purchase_order inserted into DB
		 */
		
		public PurchaseOrderBean getOrder(int id)
		{
			String queryId = "GET_PURCHASE_ORDER_BY_ID";
			
			if(id == 0){//if id==0, query the latest order
				id = this.getNewOrderId()-1;
			}
			
			ResultSet rs = this.getQueryResult(queryId,id);
			PurchaseOrderBean purchaseOrderBean = null;
			
			try {
				if(rs.next())
				{
					purchaseOrderBean = new PurchaseOrderBean();
					purchaseOrderBean.setId(rs.getInt("id"));
					//System.out.println("hello***************************************1");
					//purchaseOrderBean.setCreated(getDateFromStringDate(rs.getString("created")));
					purchaseOrderBean.setCreated(this.getDateFromStringDate(rs.getString("created")));
					//System.out.println("data is " + rs.getString("created"));
					purchaseOrderBean.setShippingAmount(rs.getDouble("shipping_amount"));
					//System.out.println("hello***************************************3");
					purchaseOrderBean.setTaxFactor(rs.getDouble("tax_factor"));
					purchaseOrderBean.setStatus(rs.getString("status"));
					purchaseOrderBean.setTotalAmount(rs.getDouble("total_amount"));
					purchaseOrderBean.setAccount_id(rs.getInt("account_id"));
				}
				// Close ResultSet
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//System.out.println(purchaseOrderBean.getCreated().toString());
			return purchaseOrderBean;
		}
}