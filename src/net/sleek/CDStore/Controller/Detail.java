package net.sleek.CDStore.Controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sleek.CDStore.model.*;
import com.sleek.CDStore.function.ModelApplier;
import com.sleek.CDStore.config.URLMapper;

/**
 * Servlet implementation class for Servlet: Detail
 *
 */
 public class Detail extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public Detail() {
		super();
	}   	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/*		// TODO Auto-generated method stub
		//get Track by CD Id and display them to View part	
*/
	    response.setContentType("text/html;charset=UTF-8");  
        PrintWriter out = response.getWriter();  
        try {  
            response.setContentType("text/html");  
            response.setHeader("Cache-Control", "no-store");  
            response.setHeader("Pragma", "no-cache");  
            response.setDateHeader("Expires", 0);  
            int name = Integer.parseInt(request.getParameter("cdId"));  
            System.out.println(name);
            CDBean c = new CDBean();
    		c= ModelApplier.getProductById(name);
            
            System.out.println(name);
           
                out.write("<h2>CD Information</h2><table><tbody><tr><td><dl><dt>Title: "+c.getTitle()+"</dt><dt>Category: "+c.getCategory() +"</dt><dt>Price: "+c.getPrice() +" $</dt><dt>Description: "+c.getDescription() +"</dt></td></tr></tbody></table>");  
        } finally {   
            out.close();  
        }  
	}  	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}   	  	    
}