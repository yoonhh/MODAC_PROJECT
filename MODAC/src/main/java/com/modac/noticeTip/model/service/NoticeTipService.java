package com.modac.noticeTip.model.service;

import static com.modac.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.ArrayList;

import com.modac.QA.model.dao.QaDao;
import com.modac.QA.model.vo.Qa;
import com.modac.common.model.vo.Attachment;
import com.modac.common.model.vo.PageInfo;
import com.modac.notice.model.dao.NoticeDao;
import com.modac.notice.model.vo.Notice;
import com.modac.noticeTip.model.dao.NoticeTipDao;
import com.modac.noticeTip.model.vo.NoticeTip;

public class NoticeTipService {

	public ArrayList<NoticeTip> selectNoticeTipList(PageInfo pi, String field, String query) {

		Connection conn = getConnection();

		ArrayList<NoticeTip> qList = new NoticeTipDao().selectNoticeTipList(conn, pi, field, query);
		
		new NoticeTipDao().selectAttachment(conn, qList);
		
		close();

		return qList;
	}

	public int selectListCount() {

		Connection conn = getConnection();
		int listCount = new NoticeTipDao().selectListCount(conn);
		close();
		return listCount;
	}
	
	
	public NoticeTip selectNoticeTip(String postNo) {
		Connection conn = getConnection();
		
		NoticeTip n = new NoticeTipDao().selectNoticeTip(postNo, conn);
		
		close();
		
		return n;
	}
	
	public Attachment selectAttachment(String postNo) {
		Connection conn = getConnection();
		
		Attachment list = new NoticeTipDao().selectAttachment(conn, postNo);
		
		close();
		
		return list;
	}
	
	
	public int insertNoticeTip(NoticeTip nt, Attachment at) {
		
		Connection conn = getConnection();
		
		int result1 = new NoticeTipDao().insertNoticeTip(nt, conn);
		
		int result2 = 1;
		
		if(at != null) {
			result2 = new NoticeTipDao().insertAttachment(at, conn);
		}
		
		// 트랜잭션 처리
		if(result1 > 0 && result2 > 0) {
			// 첨부파일이 없는 경우, insert가 성공했을 때도 result2는 여전히 0이라 rollback처리가 될 수 있으므로
			// result2 = 1로 초기화 시켜둠
			commit(conn);
		}else {
			rollback(conn);
		}
		
		close();
		
		return result1 * result2;
		// 혹시라도 하나라도 실패하여 0이될 경우 아예 실패값을 반환받기 위해 곱셈결과를 리턴
	}
	
	
	public int updateNoticeTip(NoticeTip nt, Attachment at) {
		Connection conn = getConnection();
		
		int result1 = new NoticeTipDao().updateNoticeTip(nt, conn);
		int result2 = 1;
		
		// 새롭게 첨부된 파일이 있는 경우
		if(at != null) {
			
			// 기존에 첨부파일이 있었을 경우 => update문 실행
			if(at.getPhotoNo() != null) {
				result2 = new NoticeTipDao().updateAttachment(at, conn);
			}
			
		}
		
		if(result1 > 0 && result2 > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close();
		
		System.out.println("resutl2 : " + result2);
		return result1 * result2;
		
	}
	
	
	public int deleteNoticeTip(String postNo) {
		Connection conn = getConnection();
		
		int result = new NoticeTipDao().deleteNoticeTip(postNo, conn);
		
		new NoticeTipDao().deleteNoticeTip(postNo, conn);
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close();
		
		return result;
	}
	
}
