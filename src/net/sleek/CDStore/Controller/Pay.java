package net.sleek.CDStore.Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sleek.CDStore.config.URLMapper;
import com.sleek.CDStore.function.ModelApplier;
import com.sleek.CDStore.function.shoppingCart.ShoppingCart;
import com.sleek.CDStore.model.AccountBean;
import com.sleek.CDStore.model.AddressBean;
import com.sleek.CDStore.model.OrderedItemBean;
import com.sleek.CDStore.model.PurchaseOrderBean;
/**
 * Servlet implementation class for Servlet: Pay
 *
 */
 public class Pay extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public Pay() {
		super();
	}   	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}  	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String errorMsg="";
		
		String firstname=null;
		AccountBean account = new AccountBean();
		HttpSession session = request.getSession(false);
		account=(AccountBean)session.getAttribute("account");
		//get user's input
		if(request.getParameter("firstname")!=null&&request.getParameter("firstnamee")!=""){
			firstname=request.getParameter("firstname").toString();
		}
		else{
			errorMsg+="Please input a vaild fistname !<br>";
		}
		String lastname=null;
		if(request.getParameter("lastname")!=null&&request.getParameter("lastname")!=""){
			lastname=request.getParameter("lastname").toString();
		}
		else{
			errorMsg+="Please input a vaild lastname !<br>";
		}
		String phone=null;
		if(request.getParameter("phone")!=null&&request.getParameter("phone")!=""){
			phone=request.getParameter("phone").toString();
		}
		else{
			errorMsg+="Please input a vaild phone !<br>";
		}
		String shippingstreet=null;
		if(request.getParameter("shippingstreet")!=null&&request.getParameter("shippingstreet")!=""){
			shippingstreet=request.getParameter("shippingstreet").toString();
		}
		else{
			errorMsg+="Please input a vaild street !<br>";
		}
		String shippingzip=null;
		if(request.getParameter("shippingzip")!=null&&request.getParameter("shippingzip")!=""){
			shippingzip=request.getParameter("shippingzip").toString();
		}
		else{
			errorMsg+="Please input a vaild zip !<br>";
		}
		String shippingcity=null;
		if(request.getParameter("shippingcity")!=null&&request.getParameter("shippingcity")!=""){
			shippingcity=request.getParameter("shippingcity").toString();
		}
		else{
			errorMsg+="Please input a vaild city !<br>";
		}
		String shippingprovince=null;
		if(request.getParameter("shippingprovince")!=null&&request.getParameter("shippingprovince")!=""){
			shippingprovince=request.getParameter("shippingprovince").toString();
		}
		else{
			errorMsg+="Please input a vaild province !<br>";
		}
		String shippingcountry=null;
		if(request.getParameter("shippingcountry")!=null&&request.getParameter("shippingcountry")!=""){
			shippingcountry=request.getParameter("shippingcountry").toString();
		}
		else{
			errorMsg+="Please input a vaild country !<br>";
		}
		String billingstreet=null;
		if(request.getParameter("billingstreet")!=null&&request.getParameter("billingstreet")!=""){
			billingstreet=request.getParameter("billingstreet").toString();
		}
		else{
			errorMsg+="Please input a vaild billing street !<br>";
		}
		String billingzip=null;
		if(request.getParameter("billingzip")!=null&&request.getParameter("billingzip")!=""){
			billingzip=request.getParameter("billingzip").toString();
		}
		else{
			errorMsg+="Please input a vaild zip !<br>";
		}
		String billingcity=null;
		if(request.getParameter("billingcity")!=null&&request.getParameter("billingcity")!=""){
			billingcity=request.getParameter("billingcity").toString();
		}
		else{
			errorMsg+="Please input a vaild city !<br>";
		}
		String billingprovince=null;
		if(request.getParameter("billingprovince")!=null&&request.getParameter("billingprovince")!=""){
			billingprovince=request.getParameter("billingprovince").toString();
		}
		else{
			errorMsg+="Please input a vaild province !<br>";
		}
		String billingcountry=null;
		if(request.getParameter("billingcountry")!=null&&request.getParameter("billingcountry")!=""){
			billingcountry=request.getParameter("billingcountry").toString();
		}
		else{
			errorMsg+="Please input a vaild country !<br>";
		}
		
		String cardnumber=null;
		if(request.getParameter("creditcardnumber")!=null&&request.getParameter("creditcardnumber")!=""){
			cardnumber=request.getParameter("creditcardnumber").toString();
		}
		else{
			errorMsg+="Please input a vaild card number !<br>";
		}
		String nameoncard=null;
		if(request.getParameter("nameoncard")!=null&&request.getParameter("nameoncard")!=""){
			nameoncard=request.getParameter("nameoncard").toString();
		}
		else{
			errorMsg+="Please input a vaild name !<br>";
		}
		String month=null;
		if(request.getParameter("month")!=null&&request.getParameter("month")!=""){
			month=request.getParameter("month").toString();
		}
		else{
			errorMsg+="Please input a vaild month !<br>";
		}
		String year=null;
		if(request.getParameter("year")!=null&&request.getParameter("year")!=""){
			year=request.getParameter("year").toString();
		}
		else{
			errorMsg+="Please input a vaild year !<br>";
		}
		String CID=null;
		if(request.getParameter("CID")!=null&&request.getParameter("CID")!=""){
			CID=request.getParameter("CID").toString();
		}
		else{
			errorMsg+="Please input a vaild CID !<br>";
		}
		
		String type=null;
		if(request.getParameter("type")!=null&&request.getParameter("type")!=""){
			type=request.getParameter("type").toString();
		}
		else{
			errorMsg+="Please input a vaild card type !<br>";
		}
		int count =0;
		//check credit card info
		if(errorMsg == "")
		{
			System.out.println(type+cardnumber+CID);
			if(type.equals("AMEX"))
			{
				if(cardnumber.length()!=15)
					errorMsg = "Wrong card number! ";
				if(CID.length()!=4)
					errorMsg = "Wrong security code! ";
			}
			else if(type.equals("VM"))
			{
				if(cardnumber.length()!=16)
					errorMsg = "Wrong card number! ";
				if(CID.length()!=3)
					errorMsg = "Wrong security code! ";
			}
		}
		if(errorMsg != "")
		{
			//get payment failure time, a payment can not be wrong for 5 times
			count =Integer.parseInt((String)session.getAttribute("wrongCount"));
			count++;
			session.setAttribute("wrongCount",""+count);
			if(count < 5)
				errorMsg += ""+count +" times. If faild 5 times, shopping cart will be cleaned";
			else 
			{
				//fails for 5th time, clear shopping cart.
				errorMsg +="5 times. We are sorry. Your shopping cart is cleaned";
				ShoppingCart sc=new ShoppingCart();
				session.setAttribute("shoppingCart",sc);
			}
		}
		
		if(errorMsg=="")
		{
			//update account info
			session = request.getSession(false);
			if(session==null) response.sendRedirect(URLMapper.LOGIN_URL);
			//account=(AccountBean)session.getAttribute("account");
			if(account==null) response.sendRedirect(URLMapper.LOGIN_URL);
			account.setFirstname(firstname);
			account.setLastname(lastname);
			account.setPhone(phone);
			ModelApplier.updateAccount(account);
			//update user's  address
			int shippingId= ModelApplier.getAddressId(account.getId() ,"shipping");
			
			int if_new=-1;
			int billingId;
			if_new=ModelApplier.getAddressId(account.getId(),"billing");
			if(if_new == -1)
			{
				billingId=ModelApplier.getNewAddressId();
			}
			else
				billingId = if_new;
			
			AddressBean shippingaddress=new AddressBean();
			shippingaddress.setAccountId(account.getId());
			shippingaddress.setCity(shippingcity);
			shippingaddress.setProvince(shippingprovince);
			shippingaddress.setStreet(shippingstreet);
			shippingaddress.setZip(shippingzip);
			shippingaddress.setType("shipping");
			shippingaddress.setId(shippingId);
			shippingaddress.setCountry(shippingcountry);
			ModelApplier.updateAddress(shippingaddress);
			
			AddressBean billingaddress=new AddressBean();
			billingaddress.setAccountId(account.getId());
			billingaddress.setCity(billingcity);
			billingaddress.setProvince(billingprovince);
			billingaddress.setStreet(billingstreet);
			billingaddress.setZip(billingzip);
			billingaddress.setType("billing");
			billingaddress.setId(billingId);
			billingaddress.setCountry(billingcountry);
			
			if(if_new!=-1){
				ModelApplier.updateAddress(billingaddress);
			}
			else{
				ModelApplier.createAddress(billingaddress);
			}
			
		}
		
		if(errorMsg == ""){
			PurchaseOrderBean purchaseOrderBean = new PurchaseOrderBean();
			purchaseOrderBean = (PurchaseOrderBean)session.getAttribute("purchaseOrder");
			ModelApplier.confirmOrder(purchaseOrderBean.getId());
			//pay success, clear shopping cart
			ShoppingCart sc=new ShoppingCart();
			session.setAttribute("shoppingCart",sc);
			getServletConfig().getServletContext().getRequestDispatcher(URLMapper.VIEW_PAYMENTSUCCESS).forward(request, response);
			
		}	
		else{
			request.setAttribute("msg",errorMsg);
			getServletConfig().getServletContext().getRequestDispatcher(URLMapper.VIEW_PAYMENTFAIL).forward(request, response);
		}
	}   	  	    
}