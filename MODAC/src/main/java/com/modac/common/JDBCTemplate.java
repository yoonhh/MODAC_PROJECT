package com.modac.common;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCTemplate {

	private JDBCTemplate() {
		
	}
	
	private static Connection conn = null;
	private static Properties prop = new Properties();
	
	public static Connection getConnection() {
		try {
			String fileName = JDBCTemplate.class.getResource("/sql/driver/driver.properties").getPath();
			prop.load(new FileInputStream(fileName));
			
			Class.forName(prop.getProperty("driver"));
			if(conn == null) {
				conn = DriverManager.getConnection(prop.getProperty("url"), 
						prop.getProperty("username"),
						prop.getProperty("password"));
			}
			
		
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	
	public static void commit(Connection conn) {
		try {
			if(conn != null && !conn.isClosed()) {
				conn.commit();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	// rollback 메소드
	public static void rollback(Connection conn) {
		try {
			if(conn != null && !conn.isClosed()) {
				conn.rollback();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	// Connection 반납 메소드
	public static void close() {
		try {
			if(conn != null && !conn.isClosed()) {
				conn.close();
				conn = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
  	
	
	// Statement 반납 메소드
	public static void close(Statement stmt) {
		try {
			if(stmt != null && !stmt.isClosed()) {
				stmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	// ResultSet 반납 메소드
	public static void close(ResultSet rset) {
		try {
			if(rset != null && !rset.isClosed()) {
				rset.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
}
