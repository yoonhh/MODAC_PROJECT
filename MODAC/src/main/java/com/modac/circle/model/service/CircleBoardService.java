package com.modac.circle.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.modac.circle.model.dao.CircleBoardDao;
import com.modac.circle.model.vo.Circle;

import com.modac.common.JDBCTemplate;
import com.modac.common.model.vo.Attachment;
import com.modac.common.model.vo.PageInfo;
import com.modac.common.model.vo.Reply;

public class CircleBoardService {

	public int insertBoard(Circle c, Attachment at) {

		Connection conn = JDBCTemplate.getConnection();
		int result1 = new CircleBoardDao().insertBoard(c, conn);

		int result2 = 1;

		if (at != null) {
			result2 = new CircleBoardDao().insertAttachment(at, conn);
		}

		// 트랜잭션 처리
		if (result1 > 0 && result2 > 0) {
			// 첨부파일이 없는경우, insert가 성공했을때도 result2는 여전히 0이라 rollback 처리가 될 수 있으믈
			// result2 = 1ㄹ 초기화 시켜둠
			JDBCTemplate.commit(conn);

		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close();

		// 혹시라도 하나라도 실패하여 0이될 경우 아예 실패값을 반환받기위해 곱세결과를 리턴.
		return result1 * result2;
	}

	public int increaseCount(int postNo) {
		Connection conn = JDBCTemplate.getConnection();

		int result = new CircleBoardDao().increaseCount(conn, postNo);

		if (result > 0) {
			// 첨부파일이 없는경우, insert가 성공했을때도 result2는 여전히 0이라 rollback 처리가 될 수 있으믈
			// result2 = 1ㄹ 초기화 시켜둠
			JDBCTemplate.commit(conn);

		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close();

		return result;

	}

	public Circle selectBoard(int postNo) {
		Connection conn = JDBCTemplate.getConnection();

		Circle c = new CircleBoardDao().selectBoard(conn, postNo);

		JDBCTemplate.close();

		return c;
	}

	public ArrayList<Circle> selectList(PageInfo pi, String field, String query) {
		Connection conn = JDBCTemplate.getConnection();

		ArrayList<Circle> list = new CircleBoardDao().selectList(conn, pi, field, query);

		JDBCTemplate.close();

		return list;
	}

	public int updateBoard(Circle c, Attachment at) {
		Connection conn = JDBCTemplate.getConnection();

		int result1 = new CircleBoardDao().updateBoard(conn, c);

		int result2 = 1;

		if (at != null) { // 기존에 첨부파일이 있었을경우 => update문 실행
			if (at.getPhotoNo() != null) {
				result2 = new CircleBoardDao().updateAttachment(at, conn);//

			} else {
				result2 = new CircleBoardDao().insertNewAttachment(at, conn);
			}
		}

		if (result1 > 0 && result2 > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close();

		return result1 * result2;
	}

	public int deleteBoard(int postNo) {

		Connection conn = JDBCTemplate.getConnection();

		int result = new CircleBoardDao().deleteBoard(postNo, conn);

		// new CircleBoardDao().deleteAttachment(postNo, conn);

		if (result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close();

		return result;

	}

	public int selectListCount(String field, String query) {
		Connection conn = JDBCTemplate.getConnection();
		int listCount = new CircleBoardDao().selectListCount(conn, field, query);

		JDBCTemplate.close();

		return listCount;
	}

	public Attachment selectAttachment(int postNo) {
		Connection conn = JDBCTemplate.getConnection();
		Attachment at = new CircleBoardDao().selectAttachment(conn, postNo);

		JDBCTemplate.close();

		return at;
	}
	public ArrayList<Reply> selectReplyList(int postNo){
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Reply> list = new CircleBoardDao().selectReplyList(conn, postNo);
		
		JDBCTemplate.close();
		
		return list;
		
	}
public int insertReply(Reply r) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new CircleBoardDao().insertReply(conn, r);
		if(result>0) {
			JDBCTemplate.commit(conn);
			
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		return result;
	}

public int replyDel(int replyNo) {
	Connection conn = JDBCTemplate.getConnection();
	int result = new CircleBoardDao().replyDel(conn, replyNo);
	if(result>0) {
		JDBCTemplate.commit(conn);
		
	}else {
		JDBCTemplate.rollback(conn);
	}
	
	return result;
	
}

}
