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
public class TestcreateAccount {
	private String accountInfo;//parameters of createAccount
	private String result;//expected result
	Service service;
	Call call;
	
	/**
	 * construction function for initialization
	 */
	
	public TestcreateAccount(String accountInfo, String result){
		//parameter for testing
		this.accountInfo = accountInfo;	
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
				{"{\"email\":\"hchen069@uottawa.ca\",\"firstname\":\"Huang\",\"id\":67,\"lastname\":\"Cheng\",\"password\":\"1000\",\"phone\":\"1234567\",\"username\":\"henry\"}","{\"email\":\"hchen069@uottawa.ca\",\"firstname\":\"Huang\",\"id\":67,\"lastname\":\"Cheng\",\"password\":\"1000\",\"phone\":\"1234567\",\"username\":\"henry\"}"},
				{"{\"email\":\"\",\"firstname\":\"\",\"id\":,\"lastname\":\"\",\"password\":\"\",\"phone\":\"\",\"username\":\"\"}","{\"email\":\"\",\"firstname\":\"\",\"id\":,\"lastname\":\"\",\"password\":\"\",\"phone\":\"\",\"username\":\"\"}"},
				{"{\"email\":\"hchen069@uottawa.ca\",\"firstname\":\"Huang\",\"id\":4,\"lastname\":\"Cheng\",\"password\":\"1000\",\"phone\":\"1234567\",\"username\":\"henry\"}","{\"email\":\"hchen069@uottawa.ca\",\"firstname\":\"Huang\",\"id\":4,\"lastname\":\"Cheng\",\"password\":\"1000\",\"phone\":\"1234567\",\"username\":\"henry\"}"},
			});
		}

	/**
	 * Test method for {@link net.sleek.CDStore.webService.productOrder.ProductOrderService#createAccount(java.lang.String)}.
	 */
	@Test
	public void testCreateAccount() {
		//variable for actual result
	    String ret = null;
	    
		try {
			
			//set the operation needed to be called
			call.setOperationName( new QName(URLMapper.PRODUCTORDER_SERIVCE_Qname, "createAccount") );
			//invoke the operation with current parameter
			call.invoke( new Object[] {accountInfo} );
					
			//set the operation needed to be called
			call.setOperationName( new QName(URLMapper.PRODUCTORDER_SERIVCE_Qname, "getAccount") );
			//transfer the String to Bean
			AccountBean accountBean =(AccountBean)JSONObject.toBean( JSONObject.fromObject(accountInfo),AccountBean.class);
			//invoke the operation with current parameter
			ret = (String) call.invoke( new Object[] {accountBean.getUsername()} );
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
		//judge whether the actual result and expected result are matched
        assertEquals(result,ret);
	}

}
