package com.modac.usedProduct.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import static com.modac.common.JDBCTemplate.*;

import com.modac.common.JDBCTemplate;
import com.modac.common.model.vo.PageInfo;
import com.modac.common.model.vo.Reply;
import com.modac.usedProduct.model.vo.Attachment;
import com.modac.usedProduct.model.vo.Market;

public class MarketDao {
	
	//prop안에 mapper내용이 저장
	private Properties prop = new Properties();
	
	public MarketDao() {
		String fileName = MarketDao.class.getResource("/sql/usedProduct/market-mapper.xml").getPath();
		
		try {
			prop.loadFromXML(new FileInputStream(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
		//=>dao를 호출하면 mapper내용이 반환된다.
	}
	
	
	//총 게시글 갯수
	public int selectListCount(Connection conn) {
		
		//select => ResultSet
		int listCount = 0;
		
		PreparedStatement psmt = null;
		
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectListCount");
		
		try {
			psmt = conn.prepareStatement(sql);
			
			rset = psmt.executeQuery();
			
			if(rset.next()) {
				listCount = rset.getInt("COUNT");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(psmt);
		}
		return listCount;
	}
	
	
	//조회수 증가
	public int increaseCount(String postNo, Connection conn) {
		//update문 -> 처리된 행의 갯수를 반환
		int result = 0;
		
		PreparedStatement psmt = null;
		
		String sql = prop.getProperty("increaseCount");
		
		try {
			psmt = conn.prepareStatement(sql);
			//물음표 값
			psmt.setString(1, postNo);
			
			result = psmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(psmt);
		}
		return result;
	}
	
	//게시글 목록페이지
	public ArrayList<Market> marketList(Connection conn, PageInfo pi, String field, String query){
		
		//select문 => ResultSet
		ArrayList<Market> list = new ArrayList<>();
		
		PreparedStatement psmt = null;
		
		ResultSet rset = null;
		
		String sql = prop.getProperty("marketList");
		sql = sql.replace("$", field);
		
		try {
			psmt = conn.prepareStatement(sql);
			
			/*
			 * boardLimit가 10이라고 가정
			 * 
			 * currentPage = 1 => 시작값 1, 끝값 10
			 * currentPage = 2 => 시작값 11, 끝값 20
			 * currentPage = 3 => 시작값 21, 끝값 30
			 * 
			 * 시작값 = (currentPage -1) * boardLimit +1;
			 * 끝값  = 시작값 + boardLimit -1;
			 */
			int startRow = (pi.getCurrentPage() -1) * pi.getBoardLimit() +1;
			int endRow = startRow + pi.getBoardLimit() -1;
			
			psmt.setString(1, "%"+query+"%");
	        psmt.setInt(2, startRow);
	        psmt.setInt(3, endRow);
			
			rset = psmt.executeQuery();
			
			while(rset.next()) {
				list.add(new Market(rset.getString("POST_TITLE"),
									rset.getDate("CREATE_DATE"),
									rset.getInt("READ_COUNT"),
									rset.getString("SALE"),
									rset.getString("MEMBER_NIC"),
									rset.getString("POST_NO"),
									rset.getString("TITLEIMG")
						));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(psmt);
		}
		return list;
	}
	
	//게시글 목록 썸네일
//	public int marketListAtt(Connection conn,String postNo, Attachment at) {
//		int result = 0;
//		
//		PreparedStatement psmt = null;
//		ResultSet rset = null;
//		
//		String sql = prop.getProperty("marketListAtt");
//		
//		try {
//			psmt = conn.prepareStatement(sql);
//			
//			psmt.setString(1, postNo);
//			
//			rset = psmt.executeQuery();
//			
//			at = new Attachment(rset.getString("NEW_NAME"));
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}finally {
//			close(psmt);
//		}
//		return result;
//	}
	
	
//	//마켓 게시글조회 목록버전
//	public ArrayList<Market> marketListTwo(Connection conn){
//		ArrayList<Market> list = new ArrayList<>();
//		
//		PreparedStatement psmt = null;		
//		ResultSet rset = null;
//		String sql = prop.getProperty("marketListTwo");
//		
//		try {
//			psmt = conn.prepareStatement(sql);
//			
//			rset = psmt.executeQuery();
//			
//			while(rset.next()) {
//				list.add(new Market(rset.getString("POST_NO"),
//									rset.getString("POST_TITLE"),
//									rset.getDate("CREATE_DATE"),
//									rset.getInt("READ_COUNT"),
//									rset.getString("MEMBER_NIC")
//						));
//			}
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}finally {
//			close(rset);
//			close(psmt);
//		}
//		return list;
//	}


	//게시글 상세페이지
	public Market selectMarket(String postNo, Connection conn) {
		//selecr문은 반환형이 ResultSet
		
		Market m = null;
		PreparedStatement psmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectMarket");
		
		try {
			psmt = conn.prepareStatement(sql);
			
			//sql문의 '?' 값
			psmt.setString(1, postNo);
			
			rset = psmt.executeQuery();
			
			if(rset.next()) {
				m = new Market (
						rset.getString("POST_TITLE"), 
						rset.getString("POST_CONTENT"),
						rset.getDate("CREATE_DATE"),
						rset.getString("SALE"),
						rset.getString("MEMBER_NO"),
						rset.getString("POST_NO"),
						rset.getString("MEMBER_NIC")
						);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(psmt);
		}
		return m;
	}
	
	//상세페이지 첨부파일 조회
	public ArrayList<Attachment> selectAttachment(Connection conn , String postNo) {
		
		ArrayList<Attachment> list = new ArrayList<>();
		PreparedStatement psmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectAttachment");
		
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, postNo);
			
			rset = psmt.executeQuery();
			
			while(rset.next()) {
				Attachment at = new Attachment();
				
				at.setPhotoNo(rset.getString("PHOTO_NO"));
				at.setOriginName(rset.getString("ORIGIN_NAME"));
				at.setNewName(rset.getString("NEW_NAME"));
				at.setPath(rset.getString("PATH"));
				at.setBoardNo(rset.getString("BOARD_NO"));
				at.setFileLevel(rset.getInt("FILE_LEVEL"));
				list.add(at);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(psmt);
		}
		return list;
		
	}
	
	//마켓 게시글 등록
	public int insertMarketPost(Market m , Connection conn) {
		
		int result = 0;
		
		PreparedStatement psmt = null;
		
		String sql = prop.getProperty("insertMarketPost");
		
		try {
			psmt = conn.prepareStatement(sql);
			
			psmt.setString(1, m.getPostTitle());
			psmt.setString(2, m.getPostContent());
			psmt.setString(3, m.getMemberNo());
			
			result = psmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(psmt);
		}
		return result;
	}
	
	//마켓 첨부파일 등록
	public int insertAttachment(ArrayList<Attachment> list, Connection conn) {
		
		int result = 1;
		int result2 = 1;
		
		PreparedStatement psmt = null;
		
		String sql = prop.getProperty("insertAttachment");
		
		try {
			psmt = conn.prepareStatement(sql);
			
			for(Attachment at : list) {
				
				psmt.setString(1, at.getOriginName());
				psmt.setString(2, at.getNewName());
				psmt.setString(3, at.getPath());
				psmt.setInt(4, at.getFileLevel());
				
				//실행결과
				result2 = psmt.executeUpdate();
				
				if(result2 == 0) {
					result = 0;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(psmt);
		}
		return result;
	}
	
	//마켓 첨부파일 새로 추가
	public int insertNewAttachment(Attachment at , Connection conn) {
		int result = 0;
		
		PreparedStatement psmt = null;
		
		String sql = prop.getProperty("insertNewAttachment");
		
		try {
			
			psmt = conn.prepareStatement(sql);
			
			psmt.setString(1, at.getPostNo());
			psmt.setString(2, at.getOriginName());
			psmt.setString(3, at.getNewName());
			psmt.setString(4, at.getPath());
			psmt.setInt(5, at.getFileLevel());
			
			result = psmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(psmt);
		}
		return result; 
	}
	
	//마켓 게시글 수정
	public int updateMarket(Connection conn, Market m) {
		
		int result = 0;
		
		PreparedStatement psmt = null;
		
		String sql = prop.getProperty("updateMarket");
		
		try {
			psmt = conn.prepareStatement(sql);
			
			psmt.setString(1, m.getPostTitle());
			psmt.setString(2, m.getPostContent());
			psmt.setString(3, m.getPostNo());
			
			result = psmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(psmt);
		}
		return result;
	}
	
	//게시글 첨부파일 수정
	public int updateAttachment(ArrayList<Attachment> list , Connection conn) {
		int result = 0;
		PreparedStatement psmt = null;
		String sql = prop.getProperty("updateAttachment");
	
		try {
			for(Attachment at : list) {
				psmt = conn.prepareStatement(sql);
				
				psmt.setString(1, at.getOriginName());
				psmt.setString(2, at.getNewName());
				psmt.setString(3, at.getPath());
				psmt.setString(4, at.getPhotoNo());
				
				result = psmt.executeUpdate();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(psmt);
		}
		return result;
	}
	
	public int updateAttachment(Attachment at , Connection conn) {
		int result = 0;
		PreparedStatement psmt = null;
		String sql = prop.getProperty("updateAttachment");
	
		try {
				psmt = conn.prepareStatement(sql);
				
				psmt.setString(1, at.getOriginName());
				psmt.setString(2, at.getNewName());
				psmt.setString(3, at.getPath());
				psmt.setString(4, at.getPhotoNo());
				
				result = psmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(psmt);
		}
		return result;
	}
	
	
	//게시글 삭제
	public int deleteMarket(String postNo, Connection conn) {
		int result = 0;
		
		PreparedStatement psmt = null;
		
		String sql = prop.getProperty("deleteMarket");
		
		try {
			psmt = conn.prepareStatement(sql);
			
			psmt.setString(1, postNo);
			
			result = psmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(psmt);
		}
		return result;		
	}

	//게시글 첨부파일 삭제
	public void deleteAttachment(String postNo, Connection conn) {
		
		PreparedStatement psmt = null;
		
		String sql = prop.getProperty("deleteAttachment");
		
		try {
			psmt = conn.prepareStatement(sql);
			
			psmt.setString(1, postNo);
			
			psmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(psmt);
		}
	}
	
	//게시글 첨부파일 수정 삭제
	public int deleteAtt(String photoNo, Connection conn) {
		int result = 0;
		
		PreparedStatement psmt = null;
		
		String sql = prop.getProperty("deleteAtt");
		
		try {
			psmt = conn.prepareStatement(sql);
			
			psmt.setString(1, photoNo);
			
			psmt.executeUpdate();
			
			result = psmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(psmt);
		}
		return result;
	}
	
	//판매완료
	public int changeSale(String postNo, Connection conn) {
		int result = 0;
		PreparedStatement psmt = null;
		String sql = prop.getProperty("changeSale");

		try {
			psmt = conn.prepareStatement(sql);
			
			psmt.setString(1, postNo);
			
			result =  psmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(psmt);
		}
		return result;
	}
	
	//끌어올리기
	public int updateDate(String postNo, Connection conn) {
		int result = 0;
		PreparedStatement psmt = null;
		String sql = prop.getProperty("updateDate");
		
		try {
			psmt = conn.prepareStatement(sql);
			
			psmt.setString(1, postNo);
			
			result = psmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(psmt);
		}
		return result;
	}
	
	//판매중만 보기
	public ArrayList<Market> onlySaleView(Connection conn, PageInfo pi, String field, String query){

		ArrayList<Market> list = new ArrayList<>();
		PreparedStatement psmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("onlySaleView");
		sql = sql.replace("$", field);
		
		try {
			psmt = conn.prepareStatement(sql);

			int startRow = (pi.getCurrentPage() -1) * pi.getBoardLimit() +1;
			int endRow = startRow + pi.getBoardLimit() -1;
			
			psmt.setString(1, "%"+query+"%");
	        psmt.setInt(2, startRow);
	        psmt.setInt(3, endRow);
			
			rset = psmt.executeQuery();
			
			while(rset.next()) {
				list.add(new Market(rset.getString("POST_TITLE"),
									rset.getDate("CREATE_DATE"),
									rset.getInt("READ_COUNT"),
									rset.getString("SALE"),
									rset.getString("MEMBER_NIC"),
									rset.getString("POST_NO"),
									rset.getString("TITLEIMG")
						));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(psmt);
		}
		return list;
	}
	
	
	//날짜순 정렬
	public ArrayList<Market> sortOfDate(Connection conn, PageInfo pi, String field, String query){

		ArrayList<Market> list = new ArrayList<>();
		PreparedStatement psmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("sortOfDate");
		sql = sql.replace("$", field);
		
		try {
			psmt = conn.prepareStatement(sql);

			int startRow = (pi.getCurrentPage() -1) * pi.getBoardLimit() +1;
			int endRow = startRow + pi.getBoardLimit() -1;
			
			psmt.setString(1, "%"+query+"%");
	        psmt.setInt(2, startRow);
	        psmt.setInt(3, endRow);
			
			rset = psmt.executeQuery();
			
			while(rset.next()) {
				list.add(new Market(rset.getString("POST_TITLE"),
									rset.getDate("CREATE_DATE"),
									rset.getInt("READ_COUNT"),
									rset.getString("SALE"),
									rset.getString("MEMBER_NIC"),
									rset.getString("POST_NO"),
									rset.getString("TITLEIMG")
						));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(psmt);
		}
		return list;
	}
	
	
	//조회순 정렬
	public ArrayList<Market> sortOfCount(Connection conn, PageInfo pi, String field, String query){

		ArrayList<Market> list = new ArrayList<>();
		PreparedStatement psmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("sortOfCount");
		sql = sql.replace("$", field);
		
		try {
			psmt = conn.prepareStatement(sql);

			int startRow = (pi.getCurrentPage() -1) * pi.getBoardLimit() +1;
			int endRow = startRow + pi.getBoardLimit() -1;
			
			psmt.setString(1, "%"+query+"%");
	        psmt.setInt(2, startRow);
	        psmt.setInt(3, endRow);
			
			rset = psmt.executeQuery();
			
			while(rset.next()) {
				list.add(new Market(rset.getString("POST_TITLE"),
									rset.getDate("CREATE_DATE"),
									rset.getInt("READ_COUNT"),
									rset.getString("SALE"),
									rset.getString("MEMBER_NIC"),
									rset.getString("POST_NO"),
									rset.getString("TITLEIMG")
						));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(psmt);
		}
		return list;
	}
	
	
	//날짜순 정렬-판매중만 보기
	public ArrayList<Market> sortOfDateOnlySale(Connection conn, PageInfo pi, String field, String query){

		ArrayList<Market> list = new ArrayList<>();
		PreparedStatement psmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("sortOfDateOnlySale");
		sql = sql.replace("$", field);
		
		try {
			psmt = conn.prepareStatement(sql);

			int startRow = (pi.getCurrentPage() -1) * pi.getBoardLimit() +1;
			int endRow = startRow + pi.getBoardLimit() -1;
			
			psmt.setString(1, "%"+query+"%");
	        psmt.setInt(2, startRow);
	        psmt.setInt(3, endRow);
			
			rset = psmt.executeQuery();
			
			while(rset.next()) {
				list.add(new Market(rset.getString("POST_TITLE"),
									rset.getDate("CREATE_DATE"),
									rset.getInt("READ_COUNT"),
									rset.getString("SALE"),
									rset.getString("MEMBER_NIC"),
									rset.getString("POST_NO"),
									rset.getString("TITLEIMG")
						));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(psmt);
		}
		return list;
	}
	
	
	//조회순 정렬-판매중만 보기
	public ArrayList<Market> sortOfCountOnlySale(Connection conn, PageInfo pi, String field, String query){

		ArrayList<Market> list = new ArrayList<>();
		PreparedStatement psmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("sortOfCountOnlySale");
		sql = sql.replace("$", field);
		
		try {
			psmt = conn.prepareStatement(sql);

			int startRow = (pi.getCurrentPage() -1) * pi.getBoardLimit() +1;
			int endRow = startRow + pi.getBoardLimit() -1;
			
			psmt.setString(1, "%"+query+"%");
	        psmt.setInt(2, startRow);
	        psmt.setInt(3, endRow);
			
			rset = psmt.executeQuery();
			
			while(rset.next()) {
				list.add(new Market(rset.getString("POST_TITLE"),
									rset.getDate("CREATE_DATE"),
									rset.getInt("READ_COUNT"),
									rset.getString("SALE"),
									rset.getString("MEMBER_NIC"),
									rset.getString("POST_NO"),
									rset.getString("TITLEIMG")
						));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(psmt);
		}
		return list;
	}
	
	//댓글 조회
	public ArrayList<Reply> selectReplyList(Connection conn, int postNo ){
		ArrayList<Reply> list =  new ArrayList<>();
		
		PreparedStatement psmt = null;
		
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectReplyList");
		
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1,  postNo);
			rset = psmt.executeQuery();
			
			while(rset.next()) {
				list.add(new Reply(
						
						rset.getString(1),
						rset.getString(2),
						rset.getString(3),
						rset.getString(4)
						));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(psmt);
		}
		return list;
	}
	
	//댓글 작성
	public int insertReply(Connection conn, Reply r) {
		
		int result = 0;
		PreparedStatement psmt = null;
		
		String sql = prop.getProperty("insertReply");
		
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, r.getReplyContent());
			psmt.setString(2, r.getPostNo());
			psmt.setString(3, r.getWriter());
			result = psmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(psmt);
		}
		System.out.println("result : "+result);
		return result;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
	
	
	
	
	
	
	
}
