package net.sleek.CDStore.AjaxServer;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sleek.CDStore.model.*;
import com.sleek.CDStore.function.*;
import com.sleek.CDStore.function.shoppingCart.ShoppingCart;
/**
 * Servlet implementation class for Servlet: AddToCart
 */
 public class AddToCart extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public AddToCart() {
		super();
	}   	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ServletOutputStream out = response.getOutputStream();  
		if(request.getParameter("cdId")!=null){
			int cateId=Integer.parseInt(request.getParameter("cdId"));
			CDBean cd= ModelApplier.getProductById(cateId);
			HttpSession session = request.getSession(false);
			ShoppingCart sc=new ShoppingCart();
			if(session.getAttribute("shoppingCart")!=null)
			{
				sc=(ShoppingCart)session.getAttribute("shoppingCart");
				sc.addToCart(cd);
				// initialize the wrong count
				session.setAttribute("wrongCount",""+0);
				//1 Success return for Ajax function
				out.print('1');
			}
			else{
				//0 no session 
				out.print('0');
			}
		}
		else 
		{
			//2 error for the cdId;
			out.print('2');
		}
	}  	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}   	  	    
}