package com.modac.best.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.modac.camStagram.model.dao.CamStagramDao;
import com.modac.camStagram.model.vo.CamStagram;
import com.modac.common.JDBCTemplate;
import com.modac.common.model.vo.PageInfo;

public class bestDao {
	private Properties prop = new Properties();
	
	public bestDao() {
		String fileName = CamStagramDao.class.getResource("/sql/camStagram/camStagram-mapper.xml").getPath();
		
		try {
			prop.loadFromXML(new FileInputStream(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	 public ArrayList<CamStagram> selectCamStagramList(Connection conn){
		 
		 ArrayList<CamStagram> list = new ArrayList<>();
		 
		 PreparedStatement psmt = null;
		 
		 ResultSet rset = null;
		 
		 String sql = prop.getProperty("selectBest");
		 
		 try {
			psmt = conn.prepareStatement(sql);
			
			rset = psmt.executeQuery();
			
			while(rset.next()) {
				list.add(new CamStagram(
						rset.getString("TITLE_IMG"),
        				rset.getString("POST_NO"),
		                rset.getString("POST_CONTENT"),
						rset.getString("MEMBER_NIC"),
						rset.getDate("CREATE_DATE"),
						rset.getString("LIKE_COUNT"),
						rset.getString("REPLY_COUNT")
						));
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(psmt);
		}
		 return list;
	 };
	
	
	
	
	
	
	
	
	
	
	
	
	

}
