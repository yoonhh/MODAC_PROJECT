package com.modac.best.model.service;

import static com.modac.common.JDBCTemplate.close;
import static com.modac.common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import com.modac.best.model.dao.bestDao;
import com.modac.camStagram.model.vo.CamStagram;
import com.modac.common.JDBCTemplate;

public class bestService {
	
public ArrayList<CamStagram> selectBest(){
		
		Connection conn = getConnection();
		
		ArrayList<CamStagram> list = new bestDao().selectCamStagramList(conn);
		
		 JDBCTemplate.close();
		
		return list;
		
	}
}
