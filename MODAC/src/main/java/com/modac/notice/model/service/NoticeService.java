package com.modac.notice.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.modac.common.model.vo.Attachment;
import com.modac.common.model.vo.PageInfo;
import com.modac.notice.model.dao.NoticeDao;
import com.modac.notice.model.vo.Notice;
import static com.modac.common.JDBCTemplate.*;

public class NoticeService {

	public int selectListCount() {

		Connection conn = getConnection();
		int listCount = new NoticeDao().selectListCount(conn);
		close();
		return listCount;
	}

	
	public ArrayList<Notice> selectNoticeList(PageInfo pi, String field, String query){
		
		Connection conn = getConnection();
		
		ArrayList<Notice> list = new NoticeDao().selectNoticeList(conn, pi, field, query);
		
		close();
		
		return list;
	}
	
	
	public int increaseCount(String noticeNo) {
		Connection conn = getConnection();
		
		int result = new NoticeDao().increaseCount(noticeNo, conn);
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		
		return result;
	}
	
	
	public ArrayList<Attachment> selectAttachment(String noticeNo) {
		Connection conn = getConnection();
		
		ArrayList<Attachment> list = new NoticeDao().selectAttachmentList(conn, noticeNo);
		
		close();
		
		return list;
	}
	
	
	public Notice selectNotice(String noticeNo) {
		Connection conn = getConnection();
		
		Notice n = new NoticeDao().selectNotice(noticeNo, conn);
		
		close();
		
		return n;
	}
	
	
	public int insertNotice(Notice n, ArrayList<Attachment> list) {
		Connection conn = getConnection();
		
		int result1 = new NoticeDao().insertNotice(n, conn);
		
		int result2 = new NoticeDao().insertAttachmentList(list, conn);
		
		// 트랜잭션 처리
		if(result1 > 0 && result2 > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		close();
		
		return result1 * result2;
	}
	
	
	public int updateNotice(Notice n, ArrayList<Attachment> list) {
		Connection conn = getConnection();
		
		int result1 = new NoticeDao().updateNotice(n, conn);
		int result2 = 1;
		//int result2 = new NoticeDao().updateAttachment(list, conn);
		
		// 새롭게 첨부된 파일이 있는 경우
		if(!list.isEmpty()) {
			
			for(Attachment at : list) {
				// 기존에 첨부파일이 없었을 경우 => insert문 실행
				if(at.getPhotoNo() == null) {
					result2 = new NoticeDao().insertNewAttachment(at, conn);
				}else { // 기존에 첨부파일이 있었을 경우 => update문 실행
					result2 = new NoticeDao().updateAttachment(at, conn);
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
	
	
	public int deleteNotice(String noticeNo) {
		Connection conn = getConnection();
		
		int result = new NoticeDao().deleteNotice(noticeNo, conn);
		
		new NoticeDao().deleteAttachment(noticeNo, conn);
		
		commitOrRollback(result, conn);
		
		return result;
	}
	
	public int deleteUpfile(String photoNo) {
		Connection conn = getConnection();
		
		
		int result = new NoticeDao().deleteUpfile(photoNo, conn);
		
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






















