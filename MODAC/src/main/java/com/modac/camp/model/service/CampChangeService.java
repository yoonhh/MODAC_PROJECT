package com.modac.camp.model.service;

import java.sql.Connection;

import com.modac.camp.model.dao.CampChangeDao;

import static com.modac.common.JDBCTemplate.*; 

public class CampChangeService {
	
	
	public int selectCNo(String cName) {
		
		Connection conn = getConnection();
		
		int cNo = new CampChangeDao().selectCNo(cName, conn);
		
		close();
		
		return cNo;
		
	}
	
	
	

}
