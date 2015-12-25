package net.sleek.CDStore.Controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sleek.CDStore.config.URLMapper;
import com.sleek.CDStore.function.ModelApplier;
import com.sleek.CDStore.function.shoppingCart.ShoppingCart;
import com.sleek.CDStore.model.*;

import java.security.*;   
import java.security.spec.*;   
   
/**
 * Servlet implementation class for Servlet: Login
 *
 */
 public class LoginPage extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public LoginPage() {
		super();
	}   	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//GET request
		HttpSession session = request.getSession(false);
		if(session!=null){
			
			//if there is a session, that means user is already login
			if(session.getAttribute("account")!=null)
			{	
				response.sendRedirect(URLMapper.HOME_URL);
			}
			else{
				getServletConfig().getServletContext().getRequestDispatcher(URLMapper.VIEW_LOGIN).forward(request, response);
			}
		}else{
			getServletConfig().getServletContext().getRequestDispatcher(URLMapper.VIEW_LOGIN).forward(request, response);
		}
		
	
	}  	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//submit user's input
		String errorMsg="";
		String username=null;
		//check input
		if(request.getParameter("username")!=null&&request.getParameter("username")!=""){
			username=request.getParameter("username").toString();
		}
		else{
			errorMsg+="Please input a vaild username !\n";
		}
		String password=null;
		if(request.getParameter("password")!=null&&request.getParameter("password")!=""){
			password=request.getParameter("password").toString();
	
		}
		else{
			errorMsg+="Please input a vaild password !\n";
		}
		if(errorMsg=="")
		{
			AccountBean account =ModelApplier.getAccountByName(username);
			if(account!=null)
				{System.out.println("Account is not Null, so account is produced~");}
			
			if(account!=null){
				String password111=MD5_test.MD5(password);
				if(account.getPassword().equals(password111)){
					//success to login, set up user's home page and shopping cart
					ShoppingCart sc=new ShoppingCart();
					HttpSession session = request.getSession();
					session.setAttribute("account",account);
					session.setAttribute("shoppingCart",sc);
//					response.sendRedirect(URLMapper.HOME_URL);

				}
				else{
					//failed to login
					errorMsg+="Wrong Password!\n";
				}
			}
			else{
				//failed to login
				errorMsg+="The user do not exist !\n";
			}
		}
		
		if(errorMsg!=""){
		    response.setContentType("text/html;charset=UTF-8");  
	        PrintWriter out = response.getWriter();
	        try{
            response.setContentType("text/html");  
            response.setHeader("Cache-Control", "no-store");  
            response.setHeader("Pragma", "no-cache");  
            response.setDateHeader("Expires", 0);  
            out.write(errorMsg);
	        } finally {
	        	out.close();
	        }
		}
		/*
		if(errorMsg!=""){
			request.setAttribute("error",errorMsg);
			getServletConfig().getServletContext().getRequestDispatcher(URLMapper.VIEW_LOGIN).forward(request, response);
		}
		*/
		
	}   	  	    
}