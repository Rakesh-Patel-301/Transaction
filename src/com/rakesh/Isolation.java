package com.rakesh;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Isolation {

	public static void main(String[] args) throws SQLException {
		String dburl ="jdbc:mysql://localhost:3306/testdb";
		String dbuser ="root";
		String dbpassword ="rakesh301";
		
		Connection conn = DriverManager.getConnection(dburl, dbuser, dbpassword);
		System.out.println(conn.getTransactionIsolation());
		
		DatabaseMetaData metaData = conn.getMetaData();
		System.out.println(metaData.supportsTransactionIsolationLevel(Connection.TRANSACTION_SERIALIZABLE));
		conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
		System.out.println(conn.getTransactionIsolation());
		System.out.println(conn.getAutoCommit());
		
		//turning off the autocommit mode
		
		conn.setAutoCommit(false);
		String sql1 ="insert into student values(1,'rakesh','rakesh@gmail.com')";
		
		//String sql2 ="insert into student values(8,'kumar','kumar@gmail.com')";
		
		Statement st = conn.createStatement();
		try {
		st.executeUpdate(sql1);
		//st.executeUpdate(sql2);
		conn.commit();
		}catch(Exception e) {
			conn.rollback();
		}
		
		System.out.println("Done....");
		
		
		
		

	}

}
