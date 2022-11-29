package com.modac.FAQ.model.dao;

import static com.modac.common.JDBCTemplate.close;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

import com.modac.FAQ.model.vo.Faq;

public class FaqDao {

	private Properties prop = new Properties();
	
	public FaqDao() {
		String fileName = FaqDao.class.getResource("/sql/FAQ/faq-mapper.xml").getPath(); 
		
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
	
	
	public ArrayList<Faq> selectFaqList(Connection conn){
		
		ArrayList<Faq> list = new ArrayList<>();
		
		PreparedStatement psmt = null;
		
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectFaqList");
		
		try {
			psmt = conn.prepareStatement(sql);
			
			psmt.executeQuery();
			
			rset = psmt.executeQuery();
			
			while(rset.next()) {
				list.add(new Faq(
							rset.getString("POST_NO"),
							rset.getString("POST_TITLE"),
							rset.getString("POST_CONTENT")
						));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(psmt);
		}
		return list;
	}
	
	
	public Faq selectFaq(String faqNo, Connection conn) {
		
		ResultSet rset = null;
		
		Faq f = null;
		
		PreparedStatement psmt = null;
		
		String sql = prop.getProperty("selectFaq");
		
		try {
			psmt = conn.prepareStatement(sql);
			
			psmt.setString(1, faqNo);
			
			rset = psmt.executeQuery();
			
			if(rset.next()) {
				f = new Faq(rset.getString("POST_NO"),
							rset.getString("POST_TITLE"),
							rset.getString("POST_CONTENT"));
				}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(psmt);
		}
		
		return f;
		
	}
	
	
	public int insertFaq(Faq f, Connection conn) {
		
		int result = 0;

		PreparedStatement psmt = null;

		String sql = prop.getProperty("insertFaq");

		try {
			psmt = conn.prepareStatement(sql);

			psmt.setString(1, f.getFaqTitle());
			psmt.setString(2, f.getFaqContent());

			result = psmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(psmt);
		}

		return result;
	}
	
	
	public int updateFaq(Faq f, Connection conn) {

		int result = 0;

		PreparedStatement psmt = null;

		String sql = prop.getProperty("updateFaq");

		try {
			psmt = conn.prepareStatement(sql);

			psmt.setString(1, f.getFaqTitle());
			psmt.setString(2, f.getFaqContent());
			psmt.setString(3, f.getFaqNo());

			result = psmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(psmt);
		}

		return result;

	}
	
	
	
	public int deleteFaq(String faqNo, Connection conn) {

		int result = 0;

		PreparedStatement psmt = null;

		String sql = prop.getProperty("deleteFaq");

		try {
			psmt = conn.prepareStatement(sql);

			psmt.setString(1, faqNo);

			result = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(psmt);
		}

		return result;
	}
}
