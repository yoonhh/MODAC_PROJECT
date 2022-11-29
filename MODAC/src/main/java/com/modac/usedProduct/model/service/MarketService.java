package com.modac.usedProduct.model.service;

import static com.modac.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.ArrayList;


import com.modac.common.JDBCTemplate;
import com.modac.common.model.vo.PageInfo;
import com.modac.common.model.vo.Reply;
import com.modac.usedProduct.model.dao.MarketDao;
import com.modac.usedProduct.model.vo.Attachment;
import com.modac.usedProduct.model.vo.Market;


public class MarketService {
	
	//총 개시글 갯수
	public int selectListCount() {
		Connection conn = getConnection();
		
		int listCount = new MarketDao().selectListCount(conn);
		
		close();
		
		return listCount;
	}
	
	
	//마켓 게시글 조회수 증가
	public int increaseCount(String postNo) {
		Connection conn = getConnection();
		
		int result = new MarketDao().increaseCount(postNo, conn);
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close();
		return result;
	}
	
	//게시글 목록페이지
	public ArrayList<Market> marketList(PageInfo pi, String field, String query){
		Connection conn = getConnection();
		
		ArrayList<Market> list = new MarketDao().marketList(conn, pi, field, query);
		
		ArrayList<Market> list2 = new MarketDao().sortOfDate(conn, pi, field, query);
		
		ArrayList<Market> list3 = new MarketDao().sortOfCount(conn, pi, field, query);
		
		close();
		
		return list;
	}
	
	//게시글 목록 썸네일
//	public int marketListAtt(String postNo, Attachment at) {
//		Connection conn = getConnection();
//		
//		int result = new MarketDao().marketListAtt(conn, postNo, at);
//		
//		close();
//		
//		return result;
//		
//	}
	
	
//	//마켓 게시글조회 목록버전
//	public ArrayList<Market> selectMarketListTwo(){
//		//JDBCTemplate 커넥션 객체 호출, 커넥션 객체 초기화
//		Connection conn = getConnection();
//		
//		//dao에서 db에 접근해 가져온 값을 service에 반환
//		ArrayList<Market> list = new MarketDao().marketListTwo(conn);
//		
//		//커넥션 close
//		close();
//		
//		return list;
//	}
	
	
	//게시글 상세페이지
	public Market selectMarket(String postNo) {
		Connection conn = getConnection();
		
		Market m = new MarketDao().selectMarket(postNo, conn);
		
		close();
		
		return m;
	}
	
	//상세페이지 첨부파일 조회
	public ArrayList<Attachment> selectAttachment(String postNo) {
		Connection conn = getConnection();
		
		ArrayList<Attachment> list = new MarketDao().selectAttachment(conn, postNo);
		
		close();
		
		return list;
	}
	
	//게시글 등록(게시글/첨부파일)
	public int insertMarketPost(Market m, ArrayList<Attachment> list) {
		Connection conn = getConnection();
		//게시글
		int post = new MarketDao().insertMarketPost(m, conn);
		
		//이미지
		int img = 1;
		
		if(list != null) {
			img = new MarketDao().insertAttachment(list, conn);
		}
		
		if(post > 0 && img > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close();
		
		return post * img;
	}
	
	//게시글 수정(게시글/첨부파일)
	public int updateMarket(Market m, ArrayList<Attachment> list) {
		Connection conn = getConnection();
		//게시글
		int result1 = new MarketDao().updateMarket(conn, m);
		
		int result2 = 1;
		
		//새롭게 첨부된 파일이 있는 경우
		if(list != null) {
			for(Attachment at : list) {
				if(at.getPhotoNo() != null) {//기존에 첨부파일이 있었을 경우 => update문 실행
					result2 = new MarketDao().updateAttachment(list, conn);
				}else { //기존에 첨부파일이 없었을 경우 => insert문 실행
					result2 = new MarketDao().insertNewAttachment(at, conn);
				}
			}
		}
		if(result1 > 0 && result2 > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close();
		return result1 * result2;
	}
	
	//게시글 삭제
	public int deleteMarket(String postNo) {
		Connection conn = getConnection();
		
		int result = new MarketDao().deleteMarket(postNo, conn);
		
		new MarketDao().deleteAttachment(postNo, conn);
		
		if(result >0) {
			commit(conn);
		}else{
			rollback(conn);
		}
		close();
		return result;
	}
	
	//첨부파일 삭제
//	public int deleteAttachment(String postNo) {
//		Connection conn = getConnection();
//		
//		int result = new MarketDao().deleteAttachment(postNo, conn);
//		
//		new MarketDao().deleteAttachment(postNo, conn);
//		
//		if(result >0) {
//			commit(conn);
//		}else{
//			rollback(conn);
//		}
//		close();
//		return result;
//	}
	
	//첨부파일 수정 삭제
	public int deleteAtt(String photoNo) {
		Connection conn = getConnection();
		
		int result = new MarketDao().deleteAtt(photoNo, conn);
		
		if(result > 0) {
			commit(conn);
		}else{
			rollback(conn);
		}
		close();
		return result;
	}
	
	//판매완료
	public int changeSale(String postNo) {
		Connection conn = getConnection();
		
		int result = new MarketDao().changeSale(postNo, conn);
		
		if(result >0) {
			commit(conn);
		}else{
			rollback(conn);
		}
		close();
		return result;
	}
	
	//끌어올리기
	public int updateDate(String postNo) {
		Connection conn = getConnection();
		
		int result = new MarketDao().updateDate(postNo, conn);
		
		if(result >0) {
			commit(conn);
		}else{
			rollback(conn);
		}
		close();
		return result;
	}
	
	//판매중만 보기
	public ArrayList<Market> onlySaleView(PageInfo pi, String field, String query){
		//JDBCTemplate 커넥션 객체 호출, 커넥션 객체 초기화
		Connection conn = getConnection();
		
		//dao에서 db에 접근해 가져온 값을 service에 반환
		ArrayList<Market> list = new MarketDao().onlySaleView(conn, pi, field, query);
		
		//커넥션 close
		close();
		
		return list;
	}
	

	//날짜순 정렬
	public ArrayList<Market> sortOfDate(PageInfo pi, String field, String query){
		//JDBCTemplate 커넥션 객체 호출, 커넥션 객체 초기화
		Connection conn = getConnection();
		
		//dao에서 db에 접근해 가져온 값을 service에 반환
		ArrayList<Market> list = new MarketDao().sortOfDate(conn, pi, field, query);
		
		//커넥션 close
		close();
		
		return list;
	}
	
	
	//조회순 정렬
	public ArrayList<Market> sortOfCount(PageInfo pi, String field, String query){
		//JDBCTemplate 커넥션 객체 호출, 커넥션 객체 초기화
		Connection conn = getConnection();
		
		//dao에서 db에 접근해 가져온 값을 service에 반환
		ArrayList<Market> list = new MarketDao().sortOfCount(conn, pi, field, query);
		
		//커넥션 close
		close();
		
		return list;
	}
	
	
	//날짜순 정렬-판매중만 보기
	public ArrayList<Market> sortOfDateOnlySale(PageInfo pi, String field, String query){
		//JDBCTemplate 커넥션 객체 호출, 커넥션 객체 초기화
		Connection conn = getConnection();
		
		//dao에서 db에 접근해 가져온 값을 service에 반환
		ArrayList<Market> list = new MarketDao().sortOfDateOnlySale(conn, pi, field, query);
		
		//커넥션 close
		close();
		
		return list;
	}
	
	
	//조회순 정렬-판매중만 보기
	public ArrayList<Market> sortOfCountOnlySale(PageInfo pi, String field, String query){
		//JDBCTemplate 커넥션 객체 호출, 커넥션 객체 초기화
		Connection conn = getConnection();
		
		//dao에서 db에 접근해 가져온 값을 service에 반환
		ArrayList<Market> list = new MarketDao().sortOfCountOnlySale(conn, pi, field, query);
		
		//커넥션 close
		close();
		
		return list;
	}
	
	//댓글 조회
	public ArrayList<Reply> selectReplyList(int postNo){
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Reply> list = new MarketDao().selectReplyList(conn, postNo);
		
		JDBCTemplate.close();
		
		return list;
	}
	
	//댓글 작성
	public int insertReply(Reply r) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new MarketDao().insertReply(conn, r);
		if(result>0) {
			JDBCTemplate.commit(conn);
			
		}else {
			JDBCTemplate.rollback(conn);
		}
		return result;
	}
	
	
	
	
	
	
	
	
	

}
