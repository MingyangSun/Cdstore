package net.sleek.CDStore.webService.test;

import com.sleek.CDStore.config.*;
import com.sleek.CDStore.model.AccountBean;

import static org.junit.Assert.*;
import net.sf.json.JSONObject;

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
public class TestgetAccount {
	private String username;//parameter of getAcccount()
	private String result;//expected result
	Service service;
	Call call;
	
	/**
	 * construction function for initialization
	 */
	
	public TestgetAccount(String username, String result){
		//parameter for testing
		this.username = username;	
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
			call = (Call) service.createCall();
			//set the target url of SOAP webservice
			call.setTargetEndpointAddress( new java.net.URL(URLMapper.PRODUCTORDER_SERIVCE_URL) );
			//set the operation needed to be called
			call.setOperationName( new QName(URLMapper.PRODUCTORDER_SERIVCE_Qname, "getAccount") );
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
			{"ali","{\"email\":\"asa@foo.com\",\"firstname\":\"Ali\",\"id\":1,\"lastname\":\"Gap\",\"password\":\"0000\",\"phone\":\"\",\"username\":\"ali\"}"},
			{"Saad","{\"email\":\"121A@sd.co\",\"firstname\":\"ma\",\"id\":2,\"lastname\":\"sada\",\"password\":\"23232\",\"phone\":\"\",\"username\":\"Saad\"}"},
		});
	}

	/**
	 * Test method for {@link net.sleek.CDStore.webService.productOrder.ProductOrderService#getAccount(java.lang.String)}.
	 */
	@Test
	public void testGetAccount() {
		//variable for actual result
	    String ret = null;
	    
		try {
			//invoke the operation with current parameter
			ret = (String) call.invoke( new Object[] {username} );
			System.out.println(username);
			System.out.println(ret);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
		//judge whether the actual result and expected result are matched
        assertEquals(result,ret);
	}

}