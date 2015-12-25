package com.sleek.CDStore.model;

public class OrderedItemBean {
	
	protected int id;
	protected int cdId;
	protected int purchaseOrderId;
	protected int quantity;
	protected double saleFactor;
	/**get Id
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/** get Cd Id
	 * @return the cdId
	 */
	public int getCdId() {
		return cdId;
	}
	/** get Purchase Order Id
	 * @return the purchaseOrderId
	 */
	public int getPurchaseOrderId() {
		return purchaseOrderId;
	}
	/**get Quantity
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}
	/** get SaleFactor
	 * @return the saleFactor
	 */
	public double getSaleFactor() {
		return saleFactor;
	}
	/** set id
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}
	/** set cd id
	 * @param the cd Id to set
	 */
	public void setCdId(int cdId) {
		this.cdId = cdId;
	}
	/** set Purchase Order Id
	 * @param purchaseOrderId
	 */
	public void setPurchaseOrderId(int purchaseOrderId) {
		this.purchaseOrderId = purchaseOrderId;
	}
	/**set Quantity
	 * @param quantity
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	/**set saleFactor
	 * @param saleFactor
	 */
	public void setSaleFactor(double saleFactor) {
		this.saleFactor = saleFactor;
	}

}
