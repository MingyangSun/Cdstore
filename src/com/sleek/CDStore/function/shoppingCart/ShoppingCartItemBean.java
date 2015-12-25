package com.sleek.CDStore.function.shoppingCart;

import com.sleek.CDStore.model.CDBean;
import com.sleek.CDStore.model.OrderedItemBean;

/**
 *
 */
public class ShoppingCartItemBean
{
	private CDBean cd;
	private OrderedItemBean item; 
	/**
	 * @return the item
	 */
	public OrderedItemBean getItem()
	{
		return item;
	}
	/**
	 * set item 
	 */
	public void setItem(OrderedItemBean item)
	{
		this.item = item;
	}
	/**
	 *  set cd 
	 */
	public void setCD(CDBean item)
	{
		this.cd = item;
	}
	/**
	 * @return cd
	 */
	public CDBean getCD(){
		return this.cd;
	}
	/**
	 *  get quantity
	 */
	public int getQuantity()
	{
		return item.getQuantity();
	}
	/**
	 *  set quantity 
	 */
	public void setQuantity(int quantity)
	{
		item.setQuantity(quantity);
	}
	/**
	 * update the quantity
	 */
	public void addOne(){
		item.setQuantity(item.getQuantity()+1);
	}
	public void minusOne(){
		item.setQuantity(item.getQuantity()-1);
	}
	/**
	 * @return the totalPrice
	 */
	public double getPrice()
	{
		return (item.getQuantity() * cd.getPrice()) ;
	}
}
