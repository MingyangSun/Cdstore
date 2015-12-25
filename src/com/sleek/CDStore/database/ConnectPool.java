package com.sleek.CDStore.database;

	import java.sql.*;
	import javax.naming.Context;
	import javax.naming.InitialContext;
	import javax.naming.NamingException;
	import javax.sql.DataSource;


	/**
	 * A connection pool designed to handle connections to data source 
	 *  
	 * NOTE: the configuration of the Data Source Should be in META-INF\context.xml
	 *
	 **/

	public class ConnectPool
	{

		
		private static ConnectPool connPool = null; // Singleton pattern 
		private static Connection conn = null;
		private Context initContext = null;
		private DataSource dataSource;	
		private String dbSource = "java:comp/env/jdbc/cdstore";		
		
		/**
		 * constructor
		 */
		private ConnectPool() { 
					
			try {
				initContext = new InitialContext();
				dataSource = (DataSource) initContext.lookup(dbSource);
			} catch (NamingException e) {
				System.err.println("initial context error!\n" + e);
			}
		}
			
		/**
		 * Get the instance of Connection Pool
		 * @return an instance of connPool
		 */
		public static ConnectPool getInstance() 
		{
			if ( connPool == null)
			{
				connPool = new ConnectPool();
			}
			return connPool;
		}
		
		/**
		 * Retrieve a DB connection and return it to the calling application
		 * 
		 * @return DB connection
		 * @throws ClassNotFoundException 
		 * @throws SQLException 
		 */
		
		public Connection connect() throws SQLException, ClassNotFoundException 
		{
			if ( conn == null || conn.isClosed() )
				getConnection();

			return conn;	
		}
		
		/**
		 * Retrieve a DB connection from the connection pool 
		 *
		 */
		private void getConnection()  {
			
			try
			{
				conn = dataSource.getConnection();
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		
		/**
		 * Close a DB connection
		 * @throws NamingException 
		 */
		public void close() throws  NamingException 
		{
			if ( initContext != null )
				initContext.close();
		}
		
	}
