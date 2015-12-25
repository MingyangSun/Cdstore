package net.sleek.CDStore.webService.test;

import com.sleek.CDStore.config.*;

import static org.junit.Assert.*;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.*;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.Collection;

import javax.xml.namespace.QName;
import javax.xml.rpc.ServiceException;


//use special runner for testing with parameters
@RunWith(Parameterized.class)
public class TestgetProductList {
	
	private int productId;//parameters for getProductList()
	private String result;//expected result
	Service service;
	Call call;	
    
	/**
	 * construction function for initialization
	 */
    
    public TestgetProductList(int productId, String result){
		//parameter for testing
		this.productId = productId;
		//expected result for this parameter
		this.result = result;
		//variable for the called service
		service = new Service();
    }
    

	/**
	 * Invoke the SOAP webservice and corresponding function
	 * @throws java.lang.Exception
	 */
	
	@Before
	public void setUp() throws Exception {    
		try {
			//bond call and service
			this.call = (Call) service.createCall();
			//set the target url of SOAP webservice
		    call.setTargetEndpointAddress( new java.net.URL(URLMapper.CATEGORY_SERIVCE_URL) );
		    //set the operation needed to be called
		    call.setOperationName( new QName(URLMapper.CATEGORY_SERIVCE_Qname, "getProductList") );
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Parameters for testing boundary situation
	 */
	
	@Parameters
	public static Collection data(){
		return Arrays.asList(new Object[][]{
				{1,"[{\"artist\":\"aaa\",\"category\":\"ROOK\",\"description\":\"aaa\",\"id\":1,\"image\":\"aaa\",\"price\":12.2,\"title\":\"aaa\"}]"},
				{2,"[{\"artist\":\"nnbb\",\"category\":\"POP\",\"description\":\"bb\",\"id\":2,\"image\":\"bb\",\"price\":13,\"title\":\"bbb\"}]"},
				{4,""}
		});
	}

	/**
	 * Test method for {@link net.sleek.CDStore.webService.category.CategoryService#getProductList()}.
	 */
	@Test
	public void testGetProductList() {
		//variable for actual result
	    String ret = null;
	    
		try {
			//invoke the operation with current parameter
			ret = (String) call.invoke( new Object[] {productId} );
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//judge whether the actual result and expected result are matched
        assertEquals(result,ret);
	}

}