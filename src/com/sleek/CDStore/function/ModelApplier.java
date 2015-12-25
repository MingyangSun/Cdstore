package com.sleek.CDStore.function;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;

import com.sleek.CDStore.model.*;

import net.sf.json.JSONObject;
import net.sf.json.JSONArray;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

import javax.xml.namespace.QName;

import com.sleek.CDStore.config.URLMapper;
import com.sleek.CDStore.database.DatabaseAgent;

public class ModelApplier {
	
	private static List<CategoryBean> cateList = new ArrayList<CategoryBean>();
	private static List<CDBean> CDList =new ArrayList<CDBean>();
	private static CategoryBean cate = new CategoryBean();
	private static List<AddressBean> addressList = new ArrayList<AddressBean>();
	private static CDBean CD = new CDBean();
	private static AccountBean account = new AccountBean();
	private static List<TrackBean> TrackList =new ArrayList<TrackBean>();
	private static DatabaseAgent db =new DatabaseAgent();
	/**
	 * restore the list of CategoryBean from the JSon string stream
	 * @return List of Category
	 */

	public static List<CategoryBean> getAllCategory(){
		cateList = new ArrayList<CategoryBean>();
		try{
			Service service = new Service();
			Call call = (Call) service.createCall();
			call.setTargetEndpointAddress( new java.net.URL( URLMapper.CATEGORY_SERIVCE_URL ) );
			call.setOperationName( new QName(URLMapper.CATEGORY_SERIVCE_Qname, "getCategoryList") );
			String ret = (String) call.invoke( new Object[] {} );
			JSONArray cateArray = JSONArray.fromObject(ret);
			for(int i=0;i<cateArray.size();i++){
				CategoryBean cate= (CategoryBean) JSONObject.toBean( cateArray.getJSONObject(i),CategoryBean.class);
				cateList.add(cate);
			}
			
		}catch(Exception e){
		     System.err.println(e.toString());
		 }
		return cateList;
	}
	/**
	 * restore the list of CDBean of all CDs from the string stream of JSon
	 * @return List of CD
	 */
	public static List<CDBean> getAllProduct(){
		return getProductByCateId(0);
	}
	/**
	 * restore the list of CDBean of CDs in a certain category from the JSon String stream
	 * @param id
	 * @return List of CD
	 */
	public static List<CDBean> getProductByCateId(int id){
		CDList =new ArrayList<CDBean>();
		try{
			Service service = new Service();
			Call call = (Call) service.createCall();
			call.setTargetEndpointAddress( new java.net.URL( URLMapper.CATEGORY_SERIVCE_URL ) );
			call.setOperationName( new QName(URLMapper.CATEGORY_SERIVCE_Qname, "getProductList") );
			String ret = (String) call.invoke( new Object[] {id} );
			JSONArray cateArray = JSONArray.fromObject(ret);
			for(int i=0;i<cateArray.size();i++){
				CDBean cd= (CDBean) JSONObject.toBean( cateArray.getJSONObject(i),CDBean.class);
				CDList.add(cd);
			}
			
		}catch(Exception e){
		     System.err.println(e.toString());
		}
		return CDList;
	}
	/**
	 * restore the CategoryBean from the JSon String stream
	 * @param id
	 * @return Category
	 */
	public static CategoryBean getCategoryById(int id){
		cate = new CategoryBean();
		try{
			Service service = new Service();
			Call call = (Call) service.createCall();
			call.setTargetEndpointAddress( new java.net.URL( URLMapper.CATEGORY_SERIVCE_URL ) );
			call.setOperationName( new QName(URLMapper.CATEGORY_SERIVCE_Qname, "getCategory") );
			String ret = (String) call.invoke( new Object[] {id} );
			System.out.println("sssssssssss" + ret + "sssssssssssssssssssssssssss");
			cate = (CategoryBean) JSONObject.toBean(JSONObject.fromObject(ret),CategoryBean.class);
		}catch(Exception e){
		     System.err.println(e.toString());
		}
		return cate;
	}
	/**
	 * restore the CDBean from the JSon String stream
	 * @param id
	 * @return CD
	 */
	public static CDBean getProductById(int id){
		CDBean CD = new CDBean();
		try{
			Service service = new Service();
			Call call = (Call) service.createCall();
			call.setTargetEndpointAddress( new java.net.URL( URLMapper.CATEGORY_SERIVCE_URL ) );
			call.setOperationName( new QName(URLMapper.CATEGORY_SERIVCE_Qname, "getProductInfo") );
			String ret = (String) call.invoke( new Object[] {id} );
			CD = (CDBean) JSONObject.toBean(JSONObject.fromObject(ret),CDBean.class);
		}catch(Exception e){
		     System.err.println(e.toString());
		}
		return CD;
	}
	/**
	 * restore the list of TrackBean from the JSon String stream
	 * @param id
	 * @return list of track
	 */
	public static List<TrackBean> getTrackById(int id) {
		TrackList = new ArrayList<TrackBean>();
		try{
			Service service = new Service();
			Call call = (Call) service.createCall();
			call.setTargetEndpointAddress(new java.net.URL( URLMapper.CATEGORY_SERIVCE_URL ));
			call.setOperationName( new QName(URLMapper.CATEGORY_SERIVCE_Qname, "getTrackList") );
			String ret = (String) call.invoke( new Object[] {id} );
			
			System.out.println(ret);
			
			JSONArray cdArray = JSONArray.fromObject(ret);
			for(int i=0 ; i<cdArray.size() ; i++ ){
				TrackBean track = (TrackBean) JSONObject.toBean( cdArray.getJSONObject(i),TrackBean.class);
				TrackList.add(track);
			}
		}catch(Exception e){
			System.err.println(e.toString());
		}
		return TrackList;
	}
	/**
	 * restore the AddressBean from the JSon String stream
	 * @param user id and address type
	 * @return Address
	 */
	public static AddressBean getAddress(int id, String type) {
		AddressBean address =new AddressBean();
		try{
			Service service = new Service();
			Call call = (Call) service.createCall();
			call.setTargetEndpointAddress( new java.net.URL( URLMapper.PRODUCTORDER_SERIVCE_URL ) );
			call.setOperationName( new QName(URLMapper.PRODUCTORDER_SERIVCE_Qname, "getAddress") );
			String ret = (String) call.invoke( new Object[] {id,type} );
			address= (AddressBean) JSONObject.toBean( JSONObject.fromObject(ret),AddressBean.class);	
		}catch(Exception e){
		     System.err.println(e.toString());
		}
		return address;
	}
	/**
	 * restore the AddressBean from the JSon String stream
	 * @param user name
	 * @return Address
	 */
	public static AccountBean getAccountByName(String username){
		
		account = new AccountBean();
		try{
			Service service = new Service();
			Call call = (Call) service.createCall();
			call.setTargetEndpointAddress( new java.net.URL( URLMapper.PRODUCTORDER_SERIVCE_URL ) );
			call.setOperationName( new QName(URLMapper.PRODUCTORDER_SERIVCE_Qname, "getAccount") );
			String ret = (String) call.invoke( new Object[] {username} );
			if(ret==null)
			{
				System.out.print("getAccount ByName model applier is null");
				return null;
			}
				//System.out.println("ModelApplier: "+account);//0000000000000000000000000000
			//System.out.println("ModelApplier: "+account.getUsername());//000000000000000000000000000000000
			account = (AccountBean) JSONObject.toBean(JSONObject.fromObject(ret),AccountBean.class);
			//System.out.println("ModelApplier: "+account);//000000000000000000000000000
			//System.out.println("ModelApplier: "+account.getUsername());//000000000000000000000000000000000
		}catch(Exception e){
		     System.err.println(e.toString());
		}
		return account;
	}
	/**
	 * store the account to the JSon String stream
	 * @param user accountBean
	 * @return whether succeed
	 */
	public static String createAccount(AccountBean account){
		String jsonStr=JSONObject.fromObject(account).toString();
		System.out.print(jsonStr);
		try{
			Service service = new Service();
			Call call = (Call) service.createCall();
			call.setTargetEndpointAddress( new java.net.URL( URLMapper.PRODUCTORDER_SERIVCE_URL ) );
			call.setOperationName( new QName(URLMapper.PRODUCTORDER_SERIVCE_Qname, "createAccount") );
			String ret = (String) call.invoke( new Object[] {jsonStr} );
			return ret;
		}catch(Exception e){
		     System.err.println(e.toString());
		}
		return "-1";
	}
	/**
	 * store the purchaseOrderBean and accountBean to the JSon String stream
	 * @param purchaseOrderBean and accountBean
	 */
	public static void createOrder(PurchaseOrderBean purchaseOrderBean, AccountBean accountBean){
		String jsonStrAcc=JSONObject.fromObject(account).toString();
		System.out.print(jsonStrAcc);
		String jsonStrOr=JSONObject.fromObject(purchaseOrderBean).toString();
		System.out.print(jsonStrOr);
		try{
			Service service = new Service();
			Call call = (Call) service.createCall();
			call.setTargetEndpointAddress( new java.net.URL( URLMapper.PRODUCTORDER_SERIVCE_URL ) );
			call.setOperationName( new QName(URLMapper.PRODUCTORDER_SERIVCE_Qname, "createOrder") );
			String ret = (String) call.invoke( new Object[] {jsonStrOr, jsonStrAcc} );
		}catch(Exception e){
		     System.err.println(e.toString());
		}
	}
	/**
	 * store the orderedItemBean to the JSon String stream
	 * @param orderedItemBean
	 */
	public static void createOrderedItem(OrderedItemBean orderedItemBean){
		String jsonStrOI=JSONObject.fromObject(orderedItemBean).toString();
		
		System.out.println("jsonStrOI"+jsonStrOI);
		try{
			Service service = new Service();
			Call call = (Call) service.createCall();
			call.setTargetEndpointAddress( new java.net.URL( URLMapper.PRODUCTORDER_SERIVCE_URL ) );
			call.setOperationName( new QName(URLMapper.PRODUCTORDER_SERIVCE_Qname, "createOrderedItem") );
			String ret = (String) call.invoke( new Object[] {jsonStrOI} );
		}catch(Exception e){
		     System.err.println(e.toString());
		}
	}
	
	/**
	 * store the addressBean to the JSon String stream
	 * @param address
	 */
	public static void updateAddress(AddressBean address){
		String jsonStrAdd=JSONObject.fromObject(address).toString();
		
		try{
			Service service = new Service();
			Call call = (Call) service.createCall();
			call.setTargetEndpointAddress( new java.net.URL( URLMapper.PRODUCTORDER_SERIVCE_URL ) );
			call.setOperationName( new QName(URLMapper.PRODUCTORDER_SERIVCE_Qname, "updateAddress") );
			String ret = (String) call.invoke( new Object[] {jsonStrAdd} );
		}catch(Exception e){
		     System.err.println(e.toString());
		}
	}
	/**
	 * store the accountBean to the JSon String stream
	 * @param account
	 */
	public static void updateAccount(AccountBean account){
		String jsonStrAcc=JSONObject.fromObject(account).toString();
		
		try{
			Service service = new Service();
			Call call = (Call) service.createCall();
			call.setTargetEndpointAddress( new java.net.URL( URLMapper.PRODUCTORDER_SERIVCE_URL ) );
			call.setOperationName( new QName(URLMapper.PRODUCTORDER_SERIVCE_Qname, "updateAccount") );
			String ret = (String) call.invoke( new Object[] {jsonStrAcc} );
		}catch(Exception e){
		     System.err.println(e.toString());
		}
	}
	/**
	 * store the addresBean to the JSon String stream
	 * @param address
	 */
	public static void createAddress(AddressBean addresBean){
		//db.createAddress(addresBean);
		String jsonStrAdd=JSONObject.fromObject(addresBean).toString();
		
		try{
			Service service = new Service();
			Call call = (Call) service.createCall();
			call.setTargetEndpointAddress( new java.net.URL( URLMapper.PRODUCTORDER_SERIVCE_URL ) );
			call.setOperationName( new QName(URLMapper.PRODUCTORDER_SERIVCE_Qname, "createAddress") );
			String ret = (String) call.invoke( new Object[] {jsonStrAdd} );
		}catch(Exception e){
		     System.err.println(e.toString());
		}
	}
	/**
	 * call the confirm order function in database agent
	 * @param order id
	 */
	public static void confirmOrder(int id){
		db.confirmOrder(id);
	}
	/**
	 * call the getNewAcconutId in database agent
	 * @return id for new account
	 */
	public static int getNewAcconutId(){
		return db.getNewAccountId();
	}
	/**
	 * call the getNewOrderId in database agent
	 * @return id for new order
	 */
	public static int getNewOrderId() {
		return db.getNewOrderId();
	}
	/**
	 * call the getNewOrderItemId in database agent
	 * @return id for new ordered Item
	 */
	public static int getNewOrderedItemId() {
		return db.getNewOrderedItemId();
	}
	/**
	 * call the getNewAddressId in database agent
	 * @return new address id
	 */
	public static int getNewAddressId(){
		return db.getNewAddressId();
	}
	/**
	 * call the getAddressId in database agent
	 * @param user id and address type
	 * @return address id
	 */
	public static int getAddressId(int id, String type){
		return db.getAddressId(id, type);
	}
}
