package net.sleek.CDStore.Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sleek.CDStore.model.AccountBean;
import com.sleek.CDStore.model.AddressBean;
import com.sleek.CDStore.config.URLMapper;
import com.sleek.CDStore.function.ModelApplier;

/**
 * Servlet implementation class for Servlet: SignUp
 *
 */
 public class SignUp extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public SignUp() {
		super();
	}   	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		getServletConfig().getServletContext().getRequestDispatcher(URLMapper.VIEW_SIGNUP).forward(request, response);
	}  	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String errorMsg="";
		//get user's input information
		String username=null;
		if(request.getParameter("username")!=null&&request.getParameter("username")!=""){
			username=request.getParameter("username").toString();
		}
		else{
			errorMsg+="Please input a vaild username !<br>";
		}
		String password=null;
		if(request.getParameter("password")!=null&&request.getParameter("password")!=""){
			password=request.getParameter("password").toString();
		}
		else{
			errorMsg+="Please input a vaild password !<br>";
		}
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
		String street=null;
		if(request.getParameter("street")!=null&&request.getParameter("street")!=""){
			street=request.getParameter("street").toString();
		}
		else{
			errorMsg+="Please input a vaild street !<br>";
		}
		String zip=null;
		if(request.getParameter("zip")!=null&&request.getParameter("zip")!=""){
			zip=request.getParameter("zip").toString();
		}
		else{
			errorMsg+="Please input a vaild zip !<br>";
		}
		String city=null;
		if(request.getParameter("city")!=null&&request.getParameter("city")!=""){
			city=request.getParameter("city").toString();
		}
		else{
			errorMsg+="Please input a vaild city !<br>";
		}
		String province=null;
		if(request.getParameter("province")!=null&&request.getParameter("province")!=""){
			province=request.getParameter("province").toString();
		}
		else{
			errorMsg+="Please input a vaild province !<br>";
		}
		String country=null;
		if(request.getParameter("country")!=null&&request.getParameter("country")!=""){
			country=request.getParameter("country").toString();
		}
		else{
			errorMsg+="Please input a vaild country !<br>";
		}
		int id =0;
		if(errorMsg=="")
		{
			id=ModelApplier.getNewAcconutId();
			System.out.print(id);
			AccountBean account=new AccountBean();
			account.setId(id);
			account.setUsername(username);
			account.setPassword(password);
			account.setEmail(username);
			account.setFirstname(firstname);
			account.setLastname(lastname);
			account.setPhone(phone);
			// check if there exist such a user name
			int result = Integer.parseInt(ModelApplier.createAccount(account));
			System.out.print(result);
			if(result < 0)
			{
				errorMsg = "<br><br><br>Error"+"Exist this user name, please change a new one";
			}
		}
		if(errorMsg=="")
		{
			// create user's address
			AddressBean address=new AddressBean();
			address.setAccountId(id);
			address.setStreet(street);
			address.setCountry(country);
			address.setProvince(province);
			address.setCity(city);
			address.setZip(zip);
			ModelApplier.createAddress(address);
			request.setAttribute("sucess","Your account created sucessfully,login NOW!");
			getServletConfig().getServletContext().getRequestDispatcher(URLMapper.VIEW_LOGIN).forward(request, response);
		}
		else{
			request.setAttribute("error",errorMsg);
			getServletConfig().getServletContext().getRequestDispatcher(URLMapper.VIEW_SIGNUP).forward(request, response);
		}
	}   	  	    
}