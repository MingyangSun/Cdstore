package com.sleek.CDStore.model;

import java.util.ArrayList;
import java.util.Date;

public class PurchaseOrderBean {
	protected int id;
	protected Date created; // the date of adding a item to the cart 
	protected double shippingAmount; // the cost of shipping 
	protected double taxFactor;	// tax_ factor
	protected double totalAmount; // price for this quantity.
	protected String status; // is it on_cart / buy /money_received /on_delivery / delivered 
	protected int account_id; // user who added the item to his cart 
	protected ArrayList <OrderedItemBean> orderedItemsBeanList;
	
	public PurchaseOrderBean(){
		orderedItemsBeanList = new ArrayList<OrderedItemBean>();
	}

	/** get Id
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/** get Created date
	 * @return the created date
	 */
	public Date getCreated() {
		return created;
	}

	/** get shipping Amount
	 * @return the shippingAmount
	 */
	public double getShippingAmount() {
		return shippingAmount;
	}

	/** get tax Factor
	 * @return the taxFactor
	 */
	public double getTaxFactor() {
		return taxFactor;
	}

	/** get total Amount
	 * @return the totalAmount
	 */
	public double getTotalAmount() {
		return totalAmount;
	}

	/** get status
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/** get account id
	 * @return the account_id
	 */
	public int getAccount_id() {
		return account_id;
	}

	/** get ordered Items List
	 * @return the orderedItemsBeanList
	 */
	public ArrayList<OrderedItemBean> getOrderedItemsBeanList() {
		return orderedItemsBeanList;
	}

	/** set id
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/** set created date
	 * @param created date
	 */
	public void setCreated(Date created) {
		this.created = created;
	}

	/** set Shipping Amount
	 * @param shippingAmount
	 */
	public void setShippingAmount(double shippingAmount) {
		this.shippingAmount = shippingAmount;
	}

	/** set Tax Factor
	 * @param taxFactor
	 */
	public void setTaxFactor(double taxFactor) {
		this.taxFactor = taxFactor;
	}

	/** set Total Amount
	 * @param totalAmount
	 */
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	/** set Status
	 * @param status
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/** set Account id
	 * @param account id
	 */
	public void setAccount_id(int account_id) {
		this.account_id = account_id;
	}

	/** set Ordered Items List
	 * @param ordered ItemsBean List
	 */
	public void setOrderedItemsBeanList(
			ArrayList<OrderedItemBean> orderedItemsBeanList) {
		this.orderedItemsBeanList = orderedItemsBeanList;
	}	

}
