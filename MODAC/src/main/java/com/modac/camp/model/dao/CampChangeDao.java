package com.modac.camp.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import static com.modac.common.JDBCTemplate.*;

public class CampChangeDao {

	private Properties prop = new Properties();
	
	
	public CampChangeDao() {
		
		String fileName = CampChangeDao.class.getResource("/sql/camp/campChange-mapper.xml").getPath();
		
		try {
			prop.loadFromXML(new FileInputStream(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	// 캠핑장 번호 찾기
	public int selectCNo(String cName, Connection conn) {
		
		int cNo = 0;
		PreparedStatement psmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectCNo");
		
		try {
			psmt = conn.prepareStatement(sql);
			
			psmt.setString(1, cName);
			
			rset = psmt.executeQuery();
			
			if(rset.next()) {
				cNo = Integer.parseInt(rset.getString("CAMP_NO"));
			}
			System.out.println("cno" + cNo);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(psmt);
		}
		
		return cNo;
		
	}
	
	
	
	
}
