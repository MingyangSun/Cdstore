package net.sleek.CDStore.Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sleek.CDStore.config.URLMapper;
import com.sleek.CDStore.function.shoppingCart.ShoppingCart;

/**
 * Servlet implementation class for Servlet: MyCart
 *
 */
 public class MyCart extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public MyCart() {
		super();
	}   	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(false);
		ShoppingCart sc=new ShoppingCart();
		if(session.getAttribute("shoppingCart")!=null)
		{
			//if shopping cart is not empty, go to cart.jsp to check the items in shopping cart
			//get shopping cart
			sc=(ShoppingCart)session.getAttribute("shoppingCart");
			request.setAttribute("cart",sc);
			getServletConfig().getServletContext().getRequestDispatcher(URLMapper.VIEW_CART).forward(request, response);
		}
		else{
			//otherwise, user should login to see their shopping cart
			response.sendRedirect(URLMapper.LOGIN_URL);
		}
		
	}  	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}   	  	    
}