package com.sleek.CDStore.database;
/**
 * This class handles the database query transactions
 * Uses:  QueryStore class, ConnectPool, and other java helper classes.
 * NOTE: StartTransaction and EndTransaction are implemented by Connection class method setAutoCommit(Boolean).
 */
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class QueryStore {
	protected Properties properties;
	
	public QueryStore()
	{
		properties= new Properties();
		
		InputStream queryFile = this.getClass().getResourceAsStream("queryStore.properties");
		try {
			properties.load(queryFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * @return the properties
	 */
	protected Properties getProperties() {
		return properties;
	}

	/**
	 * @return the property by its id
	 */
	protected String getQuery(String queryId)
	{
		
		
		return  properties.getProperty(queryId);
		
		
	}

}
