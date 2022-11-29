package com.modac.QA.model.service;

import static com.modac.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.ArrayList;

import com.modac.QA.model.dao.QaDao;
import com.modac.QA.model.vo.Qa;
import com.modac.common.model.vo.Attachment;
import com.modac.common.model.vo.PageInfo;
import com.modac.common.model.vo.Reply;
import com.modac.notice.model.dao.NoticeDao;
import com.modac.notice.model.vo.Notice;

public class QaService {

	public ArrayList<Qa> selectQaList(PageInfo pi, String field, String query) {
		
		Connection conn = getConnection();
		
		ArrayList<Qa> qList = new QaDao().selectQaList(conn, pi, field, query);
		
		close();
		
		return qList;
	}
	
	public int selectListCount() {

		Connection conn = getConnection();
		int listCount = new QaDao().selectListCount(conn);
		close();
		return listCount;
	}

	public int increaseCount(String qaNo) {
		Connection conn = getConnection();
		
		int result = new QaDao().increaseCount(qaNo, conn);
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		
		return result;
	}
	
	
	public int insertQa(Qa q, ArrayList<Attachment> list) {
		Connection conn = getConnection();
		
		int result1 = new QaDao().insertQa(q, conn);
		
		int result2 = new QaDao().insertAttachmentList(list, conn);
		
		// 트랜잭션 처리
		if(result1 > 0 && result2 > 0) { // 첨부파일이 없는 경우 insert가 성공했을 때도 result2는 여전히 0이라 rollback처리가 될 수 있으므로 result2 = 1로 초기화
			commit(conn);
		}else {
			rollback(conn);
		}
		
		close();
		
		return result1 * result2;
	}
	
	public Qa selectQa(String qaNo) {
		Connection conn = getConnection();
		
		Qa q = new QaDao().selectQa(qaNo, conn);
		
		close();
		
		return q;
	}
	
	
	public ArrayList<Attachment> selectAttachment(String qno) {
		Connection conn = getConnection();
		
		ArrayList<Attachment> list = new QaDao().selectAttachmentList(conn, qno);
		
		
		
		return list;
	}
	
	public int checkPwd(Qa q) {
		// 다시 수정
		Connection conn = getConnection();
		
		int result = new QaDao().checkPwd(q, conn);
		
		close();
		
		return result;
		
	}
	
	
	
	public int updateQa(Qa q, ArrayList<Attachment> list) {
		Connection conn = getConnection();
		
		int result1 = new QaDao().updateQa(q, conn);
		int result2 = 1;
		//int result2 = new NoticeDao().updateAttachment(list, conn);
		
		// 새롭게 첨부된 파일이 있는 경우
		if(!list.isEmpty()) {
			
			for(Attachment at : list) {
				// 기존에 첨부파일이 없었을 경우 => insert문 실행
				if(at.getPhotoNo() == null) {
					result2 = new QaDao().insertNewAttachment(at, conn);
				}else { // 기존에 첨3부파일이 있었을 경우 => update문 실행
					result2 = new QaDao().updateAttachment(at, conn);
				}
			}
		}else {
			result2 = 1;
		}
		
		if(result1 > 0 && result2 > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close();
		
		return result1 * result2;
	}
	
	
	public int deleteQa(String qaNo) {
		Connection conn = getConnection();
		
		int result = new QaDao().deleteQa(qaNo, conn);
		
		new QaDao().deleteQa(qaNo, conn);
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		return result;
	}
	
	
	public ArrayList<Reply> selectReplyList(int postNo){
		Connection conn = getConnection();
		ArrayList<Reply> list = new QaDao().selectReplyList(conn, postNo);
		
		close();
		
		return list;
		
	}
	
	
	public int insertReply(Reply r) {
		
		Connection conn = getConnection();
		
		int result = new QaDao().insertReply(conn, r);
		if(result>0) {
			commit(conn);
			
		}else {
			rollback(conn);
		}
		
		return result;
	}
	
}
