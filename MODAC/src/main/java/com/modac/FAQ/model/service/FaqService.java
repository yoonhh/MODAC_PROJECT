package com.modac.FAQ.model.service;

import static com.modac.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.ArrayList;

import com.modac.FAQ.model.dao.FaqDao;
import com.modac.FAQ.model.vo.Faq;
import com.modac.notice.model.dao.NoticeDao;


public class FaqService {

	public ArrayList<Faq> selectFaqList(){
		
		Connection conn = getConnection();
		
		ArrayList<Faq> list = new FaqDao().selectFaqList(conn);
		
		close();
		
		return list;
	}
	
	public Faq selectFaq(String faqNo) {
		Connection conn = getConnection();
		
		Faq f = new FaqDao().selectFaq(faqNo, conn);
		
		close();
		
		return f;
	}
	
	
	public int insertFaq(Faq f) {
		
		Connection conn = getConnection();

		int result = new FaqDao().insertFaq(f, conn);

		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}

		close();

		return result;
		
	}
	
	
	public int updateFaq(Faq f) {
		
		Connection conn = getConnection();
		
		int result = new FaqDao().updateFaq(f, conn);
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		close();
		return result;
	}
	
	
	
	public int deleteFaq(String faqNo) {
		Connection conn = getConnection();
		
		int result = new FaqDao().deleteFaq(faqNo, conn);
		
		commitOrRollback(result, conn);
		
		return result;
	}
	
	
	public void commitOrRollback(int result, Connection conn) {
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close();
	}
}
