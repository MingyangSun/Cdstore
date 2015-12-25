package net.sleek.CDStore.Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sleek.CDStore.config.URLMapper;
import com.sleek.CDStore.function.ModelApplier;
import com.sleek.CDStore.model.AccountBean;
import com.sleek.CDStore.model.AddressBean;

/**
 * Servlet implementation class for Servlet: Account
 *
 */
 public class Account extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public Account() {
		super();
	}   	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//check if user is login
		AccountBean account = new AccountBean();
		AddressBean billingAddress = new AddressBean();
		AddressBean shippingAddress = new AddressBean();
		HttpSession session = request.getSession(false);
		if(session!= null)
		{
			if(session.getAttribute("account") != null){
				//get user's account info
				account=(AccountBean)session.getAttribute("account");
				request.setAttribute("account", account);
				//get user's two address
				billingAddress = ModelApplier.getAddress(account.getId(),"billing");
				shippingAddress = ModelApplier.getAddress(account.getId(),"shipping");
				// return the values
				request.setAttribute("shippingAdress",shippingAddress);
				request.setAttribute("billingAddress",billingAddress);
				getServletConfig().getServletContext().getRequestDispatcher(URLMapper.VIEW_ACCOUNT).forward(request, response);
			}
			else{
				response.sendRedirect(URLMapper.LOGIN_URL);
			}
		}
		else{
			response.sendRedirect(URLMapper.LOGIN_URL);
		}
		
	}  	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String errorMsg="";
		//generate account info base on user's input
		// check input
		String firstname=null;
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
			errorMsg+="Please input a vaild street !<br>";
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
		
		if(errorMsg=="")
		{
			AccountBean account = new AccountBean();
			HttpSession session = request.getSession(false);
			if(session==null) response.sendRedirect(URLMapper.LOGIN_URL);
			account=(AccountBean)session.getAttribute("account");
			if(account==null) response.sendRedirect(URLMapper.LOGIN_URL);
			//update user's account
			account.setFirstname(firstname);
			account.setLastname(lastname);
			account.setPhone(phone);
			ModelApplier.updateAccount(account);
			//update/create user's shipping/billing address
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
			response.sendRedirect(URLMapper.ACCOUNT_URL+"?Sucess");
		}
		else{
			System.out.print(errorMsg);
			response.sendRedirect(URLMapper.ACCOUNT_URL+"?Error");
		}
	}   	  	    
}