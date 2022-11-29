package com.modac.report.model.service;

import java.sql.Connection;

import com.modac.common.JDBCTemplate;
import com.modac.report.model.dao.reportDao;

public class reportService {
	
	public int insertReport(String senderNo,String  postNo,String  reportTitle,String reportContent) {
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new reportDao().insertReport(senderNo, postNo, reportTitle,reportContent,conn );
		
		if(result > 0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close();
		
		return result;
	}
	
	public int countReport(String postNo) {
		Connection conn = JDBCTemplate.getConnection();
		
		int count = new reportDao().countReport(postNo, conn);
		
		JDBCTemplate.close();
		
		return count;
		
	}
}
