package com.modac.camp.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.modac.camp.model.vo.Camp;
import com.modac.common.model.vo.PageInfo;

import static com.modac.common.JDBCTemplate.*;

public class CampDao {
	
	private Properties prop = new Properties();
	
	public CampDao() {
		String fileName = CampDao.class.getResource("/sql/camp/camp-mapper.xml").getPath();
		
		try {
			prop.loadFromXML(new FileInputStream(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	// 검색 x 페이징 처리
	public int selectListCount(Connection conn) {
		
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
		} finally {
			close(rset);
			close(psmt);
		}
		
		return listCount;
	}
	
	
	
	// 지역 검색 페이징 처리
	public int locationListCount(String loc1, String loc2, Connection conn) {
		int listCount = 0;
		PreparedStatement psmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("locationListCount");
		
		try {
			psmt = conn.prepareStatement(sql);
			
			psmt.setString(1, loc1);
			psmt.setString(2, loc2);
			
			rset = psmt.executeQuery();
			
			if(rset.next()) {
				listCount = rset.getInt("COUNT");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(psmt);
		}
		return listCount;
	}
	
	
	// 체크박스 페이징 처리
	public int checkBoxListCount(String [] item1, String pet, Connection conn) {
		
		if(item1==null) { // 테마 체크박스를 사용 x 경우
			
			int listCount = 0;
			PreparedStatement psmt = null;
			ResultSet rset = null;
			String sql = prop.getProperty("noCheckBoxCount");
			
			try {
				psmt = conn.prepareStatement(sql);

				psmt.setString(1, pet);
				
				rset = psmt.executeQuery();
				
				if(rset.next()) {
					listCount = rset.getInt("COUNT");
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(rset);
				close(psmt);
			}
			return listCount;
		} else { 
			int c = item1.length;
			int [] listCount = new int [item1.length];
			int sum = 0;
			
			// 반려동물 선택안했을 경우
			if(pet == null) {
				
				for(int i=0; i<c; i++) {
					PreparedStatement psmt = null;
					ResultSet rset = null;
					String sql = prop.getProperty("noPetBoxCount");
					
					try {
						psmt = conn.prepareStatement(sql);
						
						psmt.setString(1, item1[i]);
						
						rset = psmt.executeQuery();
						
						if(rset.next()) {						
							listCount[i] = rset.getInt("COUNT");
						}
						
						sum += listCount[i];
						
					} catch (SQLException e) {
						e.printStackTrace();
					} finally {
						close(rset);
						close(psmt);
					}
				}
				return sum;
				
			} else { // 둘다 선택했을 경우
				for(int i=0; i<c; i++) {
					
					PreparedStatement psmt = null;
					ResultSet rset = null;
					String sql = prop.getProperty("boxCount");
					
					try {
						psmt = conn.prepareStatement(sql);
									
						psmt.setString(1, item1[i]);
						psmt.setString(2, pet);
						
						System.out.println(item1[i]+", "+pet);
						
						rset = psmt.executeQuery();
						
						if(rset.next()) {
							listCount[i] = rset.getInt("COUNT");
						}
						
						sum += listCount[i];
						
						
					} catch (SQLException e) {
						e.printStackTrace();
					} finally {
						close(rset);
						close(psmt);
					}
				}
				return sum;
			}
			
		}
	
	}
	
	
	
	// 모든 검색 이용시 페이징 처리
	public int allCount(String loc1, String loc2, String [] item1, String pet, Connection conn) {		
		
		if(item1==null) { // 지역 + 펫 검색
			
			int listCount = 0;
			PreparedStatement psmt = null;
			ResultSet rset = null;
			String sql = prop.getProperty("locationPetCount");
			
			try {
				
				psmt = conn.prepareStatement(sql);
				
			
				psmt.setString(1, loc1);
				psmt.setString(2, loc2);
				psmt.setString(3, pet);
				
				
				rset = psmt.executeQuery();
				
				if(rset.next()) {
					listCount = rset.getInt("COUNT");
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(rset);
				close(psmt);
			}
			return listCount;
			
		} else { // 모든 검색 기능 사용
			
			int c = item1.length;
			int [] listCount = new int [c];
			int sum = 0;
			
			for(int i=0; i<c; i++) {
						
				PreparedStatement psmt = null;
				ResultSet rset = null;
				String sql = prop.getProperty("cCount");
				
				try {
					psmt = conn.prepareStatement(sql);
					
					
					psmt.setString(1, loc1);
					psmt.setString(2, loc2);				
					psmt.setString(3, item1[i]);
					psmt.setString(4, pet);
					
					System.out.println(loc1+", "+loc2+", "+item1[i]+", "+pet);
					
					rset = psmt.executeQuery();
					
					if(rset.next()) {
						listCount[i] = rset.getInt("COUNT");
					}
					
					sum += listCount[i];
					
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					close(rset);
					close(psmt);
				}
			}
			return sum;
			
		}
		
		
	}
	

	
	
	
	// 전체 검색
	public ArrayList<Camp> selectCampList(PageInfo pi, Connection conn) {
		
		// select 여러 행 조회
		ArrayList<Camp> list = new ArrayList<>();
		PreparedStatement psmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectCampList");
		
		try {
			
			psmt = conn.prepareStatement(sql);
			
			int startRow = (pi.getCurrentPage()-1)*pi.getBoardLimit()+1;
			int endRow = startRow + pi.getBoardLimit()-1;
			
			psmt.setInt(1, startRow);
			psmt.setInt(2, endRow);
			
			rset = psmt.executeQuery();
			
			while(rset.next()) {
				list.add(new Camp(rset.getString("CAMP_NAME"),
									rset.getString("LOCATION_1"),
									rset.getString("ADDRESS"),
									rset.getString("NATURAL_ATTRI"),
									rset.getString("CAMP_IMG")
						));
			       
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(psmt);
		}
		
		return list;
	}
	
	
	
	
	public ArrayList<Camp> selectCampList(Connection conn) {
		
		// select 여러 행 조회
		ArrayList<Camp> list = new ArrayList<>();
		PreparedStatement psmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectCampList");
		
		try {
			
			psmt = conn.prepareStatement(sql);
			
			rset = psmt.executeQuery();
			
			while(rset.next()) {
				list.add(new Camp(rset.getString("CAMP_NAME"),
									rset.getString("LOCATION_1"),
									rset.getString("ADDRESS"),
									rset.getString("NATURAL_ATTRI"),
									rset.getString("CAMP_IMG")
						));
			       
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(psmt);
		}
		
		return list;
	}
	
	
	
	// 검색 박스 모두 사용
	public ArrayList<Camp> cSelect(String loc1, String loc2, String [] item1, String pet, PageInfo pi, Connection conn) {
		
		ArrayList<Camp> clist = new ArrayList<>();
		
		if(item1==null) { // 지역 + 펫 검색
			
			PreparedStatement psmt = null;
			ResultSet rset = null;
			String sql = prop.getProperty("locationPetSelect");
			
			try {
				
				psmt = conn.prepareStatement(sql);
				
				int startRow = (pi.getCurrentPage()-1)*pi.getBoardLimit()+1;
				int endRow = startRow + pi.getBoardLimit()-1;
			
				psmt.setString(1, loc1);
				psmt.setString(2, loc2);
				psmt.setString(3, pet);
				psmt.setInt(4, startRow);
				psmt.setInt(5, endRow);
				
				rset = psmt.executeQuery();
				
				while(rset.next()) {
					clist.add(new Camp(rset.getString("CAMP_NAME"),
							rset.getString("LOCATION_1"),
							rset.getString("ADDRESS"),
							rset.getString("NATURAL_ATTRI"),
							rset.getString("CAMP_IMG")
							));
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(rset);
				close(psmt);
			}
			return clist;
			
		} else { // 모든 검색 기능 사용
			
			int c = item1.length;
			
			for(int i=0; i<c; i++) {
						
				PreparedStatement psmt = null;
				ResultSet rset = null;
				String sql = prop.getProperty("cSelect");
				
				try {
					psmt = conn.prepareStatement(sql);
					
					int startRow = (pi.getCurrentPage()-1)*pi.getBoardLimit()+1;
					int endRow = startRow + pi.getBoardLimit()-1;
					
					psmt.setString(1, loc1);
					psmt.setString(2, loc2);				
					psmt.setString(3, item1[i]);
					psmt.setString(4, pet);
					psmt.setInt(5, startRow);
					psmt.setInt(6, endRow);
					
					
					System.out.println(loc1+", "+loc2+", "+item1[i]+", "+pet);
					
					rset = psmt.executeQuery();
					
					while(rset.next()) {
						clist.add(new Camp(rset.getString("CAMP_NAME"),
								rset.getString("LOCATION_1"),
								rset.getString("ADDRESS"),
								rset.getString("NATURAL_ATTRI"),
								rset.getString("CAMP_IMG")
								));
					}
					
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					close(rset);
					close(psmt);
				}
			}
			return clist;
			
		}
	}
	
	
	
	// 지역 검색
	public ArrayList<Camp> cSelect(String loc1, String loc2, PageInfo pi, Connection conn) {
		
			ArrayList<Camp> clist = new ArrayList<>();
			
			PreparedStatement psmt = null;
			ResultSet rset = null;
			String sql = prop.getProperty("locationSelect");
			
			try {
				
				psmt = conn.prepareStatement(sql);
				
				int startRow = (pi.getCurrentPage()-1)*pi.getBoardLimit()+1;
				int endRow = startRow + pi.getBoardLimit()-1;
				
				psmt.setString(1, loc1);
				psmt.setString(2, loc2);
				psmt.setInt(3, startRow);
				psmt.setInt(4, endRow);
				
				rset = psmt.executeQuery();
				
				while(rset.next()) {
					clist.add(new Camp(rset.getString("CAMP_NAME"),
							rset.getString("LOCATION_1"),
							rset.getString("ADDRESS"),
							rset.getString("NATURAL_ATTRI"),
							rset.getString("CAMP_IMG")
							));
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(rset);
				close(psmt);
			}
		return clist;
		
	}
	
	
	
	// 체크박스 검색
	public ArrayList<Camp> cSelect(String [] item1, String pet, PageInfo pi, Connection conn) {
		
		ArrayList<Camp> clist = new ArrayList<>();
		
		
		if(item1==null) { // 테마 체크박스를 사용 x 경우
			
			PreparedStatement psmt = null;
			ResultSet rset = null;
			String sql = prop.getProperty("noCheckBoxSelect");
			
			try {
				psmt = conn.prepareStatement(sql);
				
				int startRow = (pi.getCurrentPage()-1)*pi.getBoardLimit()+1;
				int endRow = startRow + pi.getBoardLimit()-1;
				
				
				psmt.setString(1, pet);
				psmt.setInt(2, startRow);
				psmt.setInt(3, endRow);
				
				System.out.println(pet);
				
				rset = psmt.executeQuery();
				
				while(rset.next()) {
					clist.add(new Camp(rset.getString("CAMP_NAME"),
							rset.getString("LOCATION_1"),
							rset.getString("ADDRESS"),
							rset.getString("NATURAL_ATTRI"),
							rset.getString("CAMP_IMG")
							));
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(rset);
				close(psmt);
			}
			return clist;
		} else { 
			int c = item1.length;
			
			// 반려동물 선택안했을 경우
			if(pet == null) {
				
				for(int i=0; i<c; i++) {
					PreparedStatement psmt = null;
					ResultSet rset = null;
					String sql = prop.getProperty("noPetBoxSelect");
					
					try {
						psmt = conn.prepareStatement(sql);
						
						int startRow = (pi.getCurrentPage()-1)*pi.getBoardLimit()+1;
						int endRow = startRow + pi.getBoardLimit()-1;
						
						psmt.setString(1, item1[i]);
						psmt.setInt(2, startRow);
						psmt.setInt(3, endRow);
						
						System.out.println(item1[i]+", "+pet);
						
						rset = psmt.executeQuery();
						
						while(rset.next()) {
							clist.add(new Camp(rset.getString("CAMP_NAME"),
									rset.getString("LOCATION_1"),
									rset.getString("ADDRESS"),
									rset.getString("NATURAL_ATTRI"),
									rset.getString("CAMP_IMG")
									));
						}
						
					} catch (SQLException e) {
						e.printStackTrace();
					} finally {
						close(rset);
						close(psmt);
					}
				}
				return clist;
				
			} else { // 둘다 선택했을 경우
				for(int i=0; i<c; i++) {
					
					PreparedStatement psmt = null;
					ResultSet rset = null;
					String sql = prop.getProperty("boxSelect");
					
					try {
						psmt = conn.prepareStatement(sql);
						
						int startRow = (pi.getCurrentPage()-1)*pi.getBoardLimit()+1;
						int endRow = startRow + pi.getBoardLimit()-1;
									
						psmt.setString(1, item1[i]);
						psmt.setString(2, pet);
						psmt.setInt(3, startRow);
						psmt.setInt(4, endRow);
						
						System.out.println(item1[i]+", "+pet);
						
						rset = psmt.executeQuery();
						
						while(rset.next()) {
							clist.add(new Camp(rset.getString("CAMP_NAME"),
									rset.getString("LOCATION_1"),
									rset.getString("ADDRESS"),
									rset.getString("NATURAL_ATTRI"),
									rset.getString("CAMP_IMG")
									));
						}
						
					} catch (SQLException e) {
						e.printStackTrace();
					} finally {
						close(rset);
						close(psmt);
					}
				}
				return clist;
			}
			
		}
		
	}
	
	
	//----------------------------------------------------------------------------------
	
	
	// 조회수
	public int increaseCount(int cNo, Connection conn) {
		
		int result = 0;
		PreparedStatement psmt = null;
		String sql = prop.getProperty("increaseCount");
		
		try {
			psmt = conn.prepareStatement(sql);
			
			psmt.setInt(1, cNo);
			
			result = psmt.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(psmt);
		}
		
		return result;
	}
	
	
	
	// 상세보기 화면(캠핑장 기본 정보)
	public Camp selectCampDetail(int cNo, Connection conn) {
		
		Camp c = null;
		PreparedStatement psmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectCampDetail");
		
		try {
			psmt = conn.prepareStatement(sql);
			
			psmt.setInt(1, cNo);
			
			rset = psmt.executeQuery();
			
			if(rset.next()) {
				c = new Camp(rset.getString("CAMP_NO"),
							rset.getString("CAMP_NAME"),
							rset.getString("ADDRESS"),
							rset.getString("CAMP_CALL"),
							rset.getString("CAMP_WEB"),
							rset.getString("CAMP_CONTENT"),
							rset.getString("CAMP_IMG")
						);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(psmt);
		}
		
		return c;
		
	}
	
	
	
	// 자연경관
	public ArrayList<Camp> n1Detail(int cNo, Connection conn) {
		
		ArrayList<Camp> n1 = new ArrayList<>();
		PreparedStatement psmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("n1Detail");
		
		try {
			psmt = conn.prepareStatement(sql);
			
			psmt.setInt(1, cNo);
			
			rset = psmt.executeQuery();
			
			while(rset.next()) {
				n1.add(new Camp(rset.getString("NATURAL_ATTRI"),
								rset.getString("NATURAL_PIC")
						));
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(psmt);
		}
		
		return n1;
	}
	
	
	
	// 지형
	public ArrayList<Camp> n2Detail(int cNo, Connection conn) {
		
		ArrayList<Camp> n2 = new ArrayList<>();
		PreparedStatement psmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("n2Detail");
		
		try {
			psmt = conn.prepareStatement(sql);
			
			psmt.setInt(1, cNo);
			
			rset = psmt.executeQuery();
			
			while(rset.next()) {
				n2.add(new Camp(rset.getString("NATURAL_ATTRI"),
								rset.getString("NATURAL_PIC")
						));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(psmt);
		}
		
		return n2;	
	}
	
	
	
	// 편의시설
	public ArrayList<Camp> n3Detail(int cNo, Connection conn) {
		
		ArrayList<Camp> n3 = new ArrayList<>();
		PreparedStatement psmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("n3Detail");
		
		try {
			psmt = conn.prepareStatement(sql);
			
			psmt.setInt(1, cNo);
			
			rset = psmt.executeQuery();
			
			while(rset.next()) {
				n3.add(new Camp(rset.getString("NATURAL_ATTRI"),
								rset.getString("NATURAL_PIC")
						));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(psmt);
		}
		
		return n3;
		
	}
	
	
	

}
