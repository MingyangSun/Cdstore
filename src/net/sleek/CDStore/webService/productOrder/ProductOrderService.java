package net.sleek.CDStore.webService.productOrder;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.sleek.CDStore.database.DatabaseAgent;
import com.sleek.CDStore.model.AccountBean;
import com.sleek.CDStore.model.AddressBean;
import com.sleek.CDStore.model.CDBean;
import com.sleek.CDStore.model.PurchaseOrderBean;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.sleek.CDStore.database.DatabaseAgent;
import com.sleek.CDStore.model.AccountBean;
import com.sleek.CDStore.model.CDBean;
import com.sleek.CDStore.model.PurchaseOrderBean;
import com.sleek.CDStore.model.OrderedItemBean;;
public class ProductOrderService {
	private DatabaseAgent db =new DatabaseAgent();
	/**
	 * Create Purchase Order
	 * @param Purchase Order and account info
	 */
	public void createOrder(String purchaseOrderJSON, String accountJSON){
		PurchaseOrderBean purchaseOrderBean=(PurchaseOrderBean)JSONObject.toBean( JSONObject.fromObject(purchaseOrderJSON),PurchaseOrderBean.class);
		AccountBean accountBean=(AccountBean)JSONObject.toBean( JSONObject.fromObject(accountJSON),AccountBean.class);
		db.createOrder( purchaseOrderBean, accountBean);
	}
	/**
	 * Create Ordered Item 
	 * @param Ordered Item's info
	 */
	public void createOrderedItem(String OrderedItemJSON){
		OrderedItemBean orderedItem=(OrderedItemBean)JSONObject.toBean( JSONObject.fromObject(OrderedItemJSON),OrderedItemBean.class);
		db.createOrderedItem( orderedItem);
	}
	/**
	 * Create　Account
	 * @param Account info
	 */
	public String  createAccount(String acconutJson){
		AccountBean accountBean =(AccountBean)JSONObject.toBean( JSONObject.fromObject(acconutJson),AccountBean.class);
		AccountBean ifExist = db.getAccount(accountBean.getUsername());
		if(ifExist!=null)
			return "-1";
		db.createAccount(accountBean); 
		return ""+1;
	}
	/**
	 *  Create Address for user 
	 *  @param address info
	 */
	public void  createAddress(String addressJson){
		AddressBean addressBean =(AddressBean)JSONObject.toBean( JSONObject.fromObject(addressJson),AddressBean.class);
		db.createAddress(addressBean);
	}
	/**
	 * Update Existing Address
	 * @param address info
	 */
	public void  updateAddress(String addressJson){
		AddressBean addressBean =(AddressBean)JSONObject.toBean( JSONObject.fromObject(addressJson),AddressBean.class);
		db.updateAddress(addressBean);
	}
	/**
	 * Update Existing Account
	 * @param account info 
	 */
	public void  updateAccount(String accountJson){
		AccountBean accountBean =(AccountBean)JSONObject.toBean( JSONObject.fromObject(accountJson),AccountBean.class);
		db.updateAccount(accountBean);
	}
	/**
	 * Get Existing Address 
	 * @param user id and address type
	 */
	public String  getAddress(int id, String type){
		AddressBean address= db.getAddress(id, type);
		if(address == null)
			return null;
		JSONObject returnJson = JSONObject.fromObject( address ); 
		return returnJson.toString();
	}
	/**
	 * Get Existing Account 
	 * @param user name
	 */
	public String getAccount(String name){
		System.out.println("get account");
		AccountBean accountBean=db.getAccount(name);
		if(accountBean == null)
		{
			System.out.println("No such account");
			return null;
		}
		
		System.out.println(accountBean.getFirstname() + accountBean.getLastname()  + accountBean.getPassword());
		JSONObject returnJson = JSONObject.fromObject(accountBean); 
		return returnJson.toString();
	}
	/**
	 * The following functions are implemented for testing only
	 */
	//get the latest order information to test createOrder
	public String getOrder(int id){
		PurchaseOrderBean purchaseOrderBean = db.getOrder(id);
		System.out.println(purchaseOrderBean.getCreated().toString());
		JSONObject returnJson = JSONObject.fromObject(purchaseOrderBean); 
		return returnJson.toString();
	}
	
	//get the latest orderId as the parameter for confirmOrder()
	public int getNewOrderId(){	
		return db.getNewOrderId()-1;
	}

}
