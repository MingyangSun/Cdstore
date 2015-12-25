package com.sleek.CDStore.function.shoppingCart;


import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.sleek.CDStore.model.*;

/**
 * This class process shopping cart operations
 *
 */
public class ShoppingCart
{	
	private List<ShoppingCartItemBean> cartList= new ArrayList<ShoppingCartItemBean>();
	private java.text.DecimalFormat   df   =new   java.text.DecimalFormat("#.00");
	
	public List<ShoppingCartItemBean> getCartList(){
		return cartList;
	}
	public ShoppingCart() {
		
	}
	public String getTotal(){
		double sum=0;
		for(int i=0;i<cartList.size();i++){
			sum+=cartList.get(i).getPrice();
		}
		return df.format(sum);
	}
	//get total price in shopping cart
	public double getTotalDouble(){
		double sum=0;
		for(int i=0;i<cartList.size();i++){
			sum+=cartList.get(i).getPrice();
		}
		return sum;
		
	}
	//get tax in shopping cart
	public String getTax(){
		
		return df.format(0.13*getTotalDouble());
	}
	//get total tax in shopping cart
	public String getTotalTax(){
		return df.format(1.13*getTotalDouble());
	}
	//add items into shopping cart
	public int addToCart(CDBean cd){
		//return value 0 update 
		//1 add new
		for(int i=0;i<cartList.size();i++){
			
			System.out.println("i:"+i+",cdid="+cartList.get(i).getItem().getId());
			if(cartList.get(i).getCD().getId()==cd.getId()){
				cartList.get(i).addOne();
				return 0;
			}
		}
		
		OrderedItemBean _item=new OrderedItemBean();
		_item.setCdId(cd.getId());
		_item.setId(cartList.size());
		_item.setQuantity(1);
		_item.setSaleFactor(1);
		
		ShoppingCartItemBean scItem= new ShoppingCartItemBean();
		scItem.setCD(cd);
		scItem.setItem(_item);
		cartList.add(scItem);
		return 1;
	}
	//update items in shopping cart
	public void updateCart(CDBean cd,int q){
		if(q>0){
			for(int i=0;i<cartList.size();i++){
				if(cartList.get(i).getCD().getId()==cd.getId()){
					cartList.get(i).setQuantity(q);
				}
			}
		}
	}
	//remove items from shopping cart
	public void removeToCart(CDBean cd){
		for(int i=0;i<cartList.size();i++){
			if(cartList.get(i).getCD().getId()==cd.getId()){
				cartList.remove(i);
			}
		}
	}
}
