package net.sleek.CDStore.Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sleek.CDStore.function.ModelApplier;
import com.sleek.CDStore.config.URLMapper;
import com.sleek.CDStore.model.*;
/**
 * Servlet implementation class for Servlet: Homepage
 *
 */
 public class Homepage extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public Homepage() {
		super();
	}   	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String isLogin = "no";
		AccountBean account=new AccountBean();
		//check login status
		HttpSession session = request.getSession(false);
		if(session!=null){
			///if there is a session, that means user is already login
			if(session.getAttribute("account")!=null)
			{	
				account=(AccountBean)session.getAttribute("account");
				isLogin="yes";
			}
		}
		//get category ID and display all of them
		int cate;
		if(request.getParameter("cateId")!=null){
			cate=Integer.parseInt(request.getParameter("cateId"));
		}
		else cate=0;
		
		//use ModelApplier get data from database and then send these information to View part
		request.setAttribute("isLogin",isLogin);
		request.setAttribute("account",account);
		request.setAttribute("productList",ModelApplier.getProductByCateId(cate));
		request.setAttribute("cateList",ModelApplier.getAllCategory());
		getServletConfig().getServletContext().getRequestDispatcher(URLMapper.VIEW_HOME).forward(request, response);
	}  	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}   	  	    
}