package com.rakesh;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Resultset_Holdability {

	public static void main(String[] args) throws SQLException {
		String dburl ="jdbc:mysql://localhost:3306/testdb";
		String dbuser="root";
		String dbpassword ="rakesh301";
		
		Connection conn = DriverManager.getConnection(dburl, dbuser, dbpassword);
		
		DatabaseMetaData metaData = conn.getMetaData();
		System.out.println(metaData.getResultSetHoldability());
		System.out.println(metaData.supportsResultSetHoldability(ResultSet.CLOSE_CURSORS_AT_COMMIT));
		
		// turning of the auto commit mode
		conn.setAutoCommit(false);
		
		String sql ="select*from student";
		
	//	Statement st = conn.createStatement();
		
	//	Statement st =conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
		
		Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY,ResultSet.CLOSE_CURSORS_AT_COMMIT);
		
		ResultSet rs =null;
		
		try {
		rs=st.executeQuery(sql);
		while(rs.next()) {
			System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3));
		}
		conn.commit();
		}catch(Exception e) {
			conn.rollback();
		}
        System.out.println("=======");
        
        rs.beforeFirst();
        while(rs.next()) {
			System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3));
		}
        System.out.println("Done.....");
	}

}

