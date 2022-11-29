package com.modac.report.model.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

import com.modac.common.JDBCTemplate;

import com.modac.report.model.vo.report;

public class reportDao {
	private Properties prop = new Properties();
	public reportDao() {
		String fileName = reportDao.class.getResource("/sql/report/report-mapper.xml").getPath();
		
		try {
			prop.loadFromXML(new FileInputStream(fileName));
		} catch (InvalidPropertiesFormatException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public int insertReport(String senderNo, String postNo, String reportTitle, String reportContent, Connection conn) {
		int result = 0;
		PreparedStatement psmt = null;
		
		String sql = prop.getProperty("insertReport");
		
		try {
			psmt = conn.prepareStatement(sql);
			
			psmt.setString(1, postNo);
			psmt.setString(2, reportTitle);
			psmt.setString(3, reportContent);
			psmt.setString(4, senderNo);
			
			result = psmt.executeUpdate();
			
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			JDBCTemplate.close(psmt);
		}
		return result;
	}
	public int countReport(String postNo,Connection conn) {
		int count = 0;
		
		PreparedStatement psmt = null;
		
		ResultSet rset = null;
		
		String sql = prop.getProperty("countReport");
		
		try {
			psmt = conn.prepareStatement(sql);
			
			psmt.setString(1, postNo);
			
			rset = psmt.executeQuery();
			
			if(rset.next()) {
				count = rset.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(psmt);
		} return count;
	}
	
	
	
	
	
	
}
