package net.sleek.CDStore.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
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
 * Servlet implementation class for Servlet :checkout
 */
public class Checkout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Checkout() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ShoppingCart sc=new ShoppingCart();
		PurchaseOrderBean purchaseOrderBean = new PurchaseOrderBean();
		AccountBean account = new AccountBean();
		AddressBean billingAddress = new AddressBean();
		AddressBean shippingAddress = new AddressBean();
		HttpSession session = request.getSession(false);
		//get the information of user account and its shopping cart
		account=(AccountBean)session.getAttribute("account");
		sc=(ShoppingCart)session.getAttribute("shoppingCart");
		String errorMsg = null;
		
		Date curDate = new Date();
	 	
	 	ArrayList<OrderedItemBean> orderedItemsBeanList = new ArrayList<OrderedItemBean>();
	 	
	 	for (int i = 0; i<sc.getCartList().size(); i++)
	 	{
	 		orderedItemsBeanList.add(sc.getCartList().get(i).getItem());
	 	}
	 	if(account != null)
		{
			if(sc!=null)
			{
				//get the order information through Id 
				//(includes user information and order details in the shopping cart)
				if(sc.getCartList().size() > 0)
				{
					int id=ModelApplier.getNewOrderId();
					purchaseOrderBean.setId(id);
					purchaseOrderBean.setAccount_id(account.getId());
					purchaseOrderBean.setCreated(curDate);
					purchaseOrderBean.setOrderedItemsBeanList(orderedItemsBeanList);
					purchaseOrderBean.setShippingAmount(10.00);
					purchaseOrderBean.setStatus("cart");
					purchaseOrderBean.setTaxFactor(0.13);
					purchaseOrderBean.setTotalAmount(sc.getTotalDouble());
					
					//set purchaseOrder to session control
					session.setAttribute("purchaseOrder", purchaseOrderBean);
					//create order
					ModelApplier.createOrder(purchaseOrderBean, account);
					//create ordered items
					for(int i = 0; i < purchaseOrderBean.getOrderedItemsBeanList().size(); i++)
					{
						int idItem = ModelApplier.getNewOrderedItemId();
						OrderedItemBean newOI=new OrderedItemBean();
						newOI.setId(idItem);
						newOI.setPurchaseOrderId(purchaseOrderBean.getId());	
						newOI.setCdId(orderedItemsBeanList.get(i).getCdId());
						newOI.setQuantity(orderedItemsBeanList.get(i).getQuantity());
						newOI.setSaleFactor(1.00);
						ModelApplier.createOrderedItem(newOI);
					}
					// return address and others
					billingAddress = ModelApplier.getAddress(account.getId(),"billing");
					shippingAddress = ModelApplier.getAddress(account.getId(),"shipping");
					request.setAttribute("shippingAdress",shippingAddress);
					request.setAttribute("billingAddress",billingAddress);
					request.setAttribute("order",purchaseOrderBean);
					request.setAttribute("account",account);
					request.setAttribute("shoppingCart",sc);
					getServletConfig().getServletContext().getRequestDispatcher(URLMapper.VIEW_CHECKOUT).forward(request, response);
				}
				// if shopping cart is empty, return to home page
				else
				{
					errorMsg+="Wrong Shopping Cart";
					response.sendRedirect(URLMapper.HOME_URL);
				}
			}
			else
			{
				errorMsg+="Wrong Shopping Cart";
				response.sendRedirect(URLMapper.HOME_URL);
			}
		}
		else
		{
			errorMsg+="Wrong Account Cart";
			response.sendRedirect(URLMapper.LOGIN_URL);
		}	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
