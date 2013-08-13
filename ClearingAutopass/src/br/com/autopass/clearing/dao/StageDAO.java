package br.com.autopass.clearing.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.autopass.clearing.vo.StageVO;

public class StageDAO {
	private DataSourceAutopass connection;

	public List<StageVO> getStageById(Integer id) {
		List<StageVO> retorno = new ArrayList<StageVO>();
		connection = new DataSourceAutopass();
		PreparedStatement pstmt;
		String sql = "select clr_id, "+
					 "       stage, "+
					 "		 to_char(stagestart, 'dd/mm/yyyy hh24:mi:ss') stagestart "+
					 "  from cmt.clearingstage@dominica "+
					 " where clr_id = ? "+
					 " order by stagestart desc";
		pstmt = connection.getPreparedStatement(sql);
		try {
			pstmt.setInt(1, id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ResultSet rs = connection.executaQuery(pstmt);
		StageVO temp;
		try {
			while (rs.next()) {
				temp = new StageVO();
				temp.setId(rs.getInt("clr_id"));
				temp.setMessage(rs.getString("stage"));
				temp.setDate(new SimpleDateFormat("yyyy-MM-dd")
				.format(rs.getDate("stagestart")));
				retorno.add(temp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		connection.closeConnection(pstmt);
		return retorno;
	}

	public List<StageVO> getStageByDate(Date date) {
		List<StageVO> retorno = new ArrayList<StageVO>();
		connection = new DataSourceAutopass();
		PreparedStatement pstmt;
		String sql = "select clr_id, "+
					 "       stage, "+
					 "		 to_char(stagestart, 'dd/mm/yyyy hh24:mi:ss') stagestart "+
					 "  from cmt.clearingstage@dominica "+
					 " where stagestart = ? "+
					 " order by stagestart desc";
		pstmt = connection.getPreparedStatement(sql);
		try {
			pstmt.setDate(1, (java.sql.Date) date);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ResultSet rs = connection.executaQuery(pstmt);
		StageVO temp;
		try {
			while (rs.next()) {
				temp = new StageVO();
				temp.setId(rs.getInt("clr_id"));
				temp.setMessage(rs.getString("stage"));
				temp.setDate(new SimpleDateFormat("yyyy-MM-dd")
				.format(rs.getDate("stagestart")));
				retorno.add(temp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		connection.closeConnection(pstmt);
		return retorno;
	}
	
	public List<StageVO> getLastStage() {
		List<StageVO> retorno = new ArrayList<StageVO>();
		connection = new DataSourceAutopass();
		PreparedStatement pstmt;
		String sql = "select a.clr_id, "+
					 "       a.stage, "+
					 "		 to_char(a.stagestart, 'dd/mm/yyyy hh24:mi:ss') stagestart "+
					 "  from cmt.clearingstage@dominica a "+
					 " where a.clr_id = (select max(clr_id) from cmt.clearingstage@dominica) "+
					 " order by a.stagestart desc";
		pstmt = connection.getPreparedStatement(sql);

		ResultSet rs = connection.executaQuery(pstmt);
		StageVO temp;
		try {
			while (rs.next()) {
				temp = new StageVO();
				temp.setId(rs.getInt("clr_id"));
				temp.setMessage(rs.getString("stage"));
				temp.setDate(rs.getString("stagestart"));
				retorno.add(temp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		connection.closeConnection(pstmt);
		return retorno;
	}	
	
	public boolean isFinishStage() {
		connection = new DataSourceAutopass();
		PreparedStatement pstmt;
		String sql = "select 1 resultado"+
					 "  from cmt.clearingstage@dominica "+
					 " where upper(stage) like '%ALL_DONE%' " +
					 "   and clr_id = (select max(clr_id) from cmt.clearingstage@dominica)";
		pstmt = connection.getPreparedStatement(sql);

		ResultSet rs = connection.executaQuery(pstmt);
		boolean retorno = false;
		int resultado = 0;
		try {
			rs.next();
			resultado = rs.getInt("resultado");
			if (resultado == 1) {
				retorno = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		connection.closeConnection(pstmt);
		return retorno;
	}	
}
