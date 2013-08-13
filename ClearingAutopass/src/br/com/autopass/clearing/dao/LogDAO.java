package br.com.autopass.clearing.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.autopass.clearing.vo.LogVO;

public class LogDAO {

	private DataSourceAutopass connection;

	public List<LogVO> getLogById(Integer id) {
		List<LogVO> retorno = new ArrayList<LogVO>();
		connection = new DataSourceAutopass();
		PreparedStatement pstmt;
		String sql = "select clr_id, "+
					 "       clg_messsage, "+
					 "		 to_char(clg_date, 'dd/mm/yyyy hh24:mi:ss') clg_date "+
					 "  from cmt.clearinglog@dominica "+
					 " where clr_id = ? "+
					 " order by clg_date desc";
		pstmt = connection.getPreparedStatement(sql);
		try {
			pstmt.setInt(1, id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ResultSet rs = connection.executaQuery(pstmt);
		LogVO temp;
		try {
			while (rs.next()) {
				temp = new LogVO();
				temp.setId(rs.getInt("clr_id"));
				temp.setMessage(rs.getString("clg_messsage"));
				temp.setDate(rs.getString("clg_date"));
				retorno.add(temp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		connection.closeConnection(pstmt);
		return retorno;
	}

	public List<LogVO> getLogByDate(Date date) {
		List<LogVO> retorno = new ArrayList<LogVO>();
		connection = new DataSourceAutopass();
		PreparedStatement pstmt;
		String sql = "select clr_id, "+
					 "       clg_messsage, "+
					 "		 to_char(clg_date, 'dd/mm/yyyy hh24:mi:ss') clg_date "+
					 "  from cmt.clearinglog@dominica "+
					 " where clg_date = ? "+
					 " order by clg_date desc";
		pstmt = connection.getPreparedStatement(sql);
		try {
			pstmt.setDate(1, (java.sql.Date) date);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ResultSet rs = connection.executaQuery(pstmt);
		LogVO temp;
		try {
			while (rs.next()) {
				temp = new LogVO();
				temp.setId(rs.getInt("clr_id"));
				temp.setMessage(rs.getString("clg_messsage"));
				temp.setDate(rs.getString("clg_date"));
				retorno.add(temp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		connection.closeConnection(pstmt);
		return retorno;
	}
	
	public List<LogVO> getLastLog() {
		List<LogVO> retorno = new ArrayList<LogVO>();
		connection = new DataSourceAutopass();
		PreparedStatement pstmt;
		String sql = "select a.clr_id, "+
					 "       a.clg_messsage, "+
					 "		 to_char(a.clg_date, 'dd/mm/yyyy hh24:mi:ss') clg_date "+
					 "  from cmt.clearinglog@dominica a "+
					 " where a.clr_id = (select max(clr_id) from cmt.clearinglog@dominica) "+
					 " order by a.clg_date desc";
		pstmt = connection.getPreparedStatement(sql);

		ResultSet rs = connection.executaQuery(pstmt);
		LogVO temp;
		try {
			while (rs.next()) {
				temp = new LogVO();
				temp.setId(rs.getInt("clr_id"));
				temp.setMessage(rs.getString("clg_messsage"));
				temp.setDate(rs.getString("clg_date"));
				retorno.add(temp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		connection.closeConnection(pstmt);
		return retorno;
	}	
	
	public List<LogVO> getErrorLog() {
		List<LogVO> retorno = new ArrayList<LogVO>();
		connection = new DataSourceAutopass();
		PreparedStatement pstmt;
		String sql = "select a.clr_id, "+
					 "       a.clg_messsage, "+
					 "		 to_char(a.clg_date, 'dd/mm/yyyy hh24:mi:ss') clg_date "+
					 "  from cmt.clearinglog@dominica a "+
					 " where (a.clg_type in (2,3) "+
					 "    or upper(a.clg_messsage) like '%ERRO%')" +
					 "   and a.clr_id = (select max(clr_id) from cmt.clearinglog@dominica) ";
		pstmt = connection.getPreparedStatement(sql);

		ResultSet rs = connection.executaQuery(pstmt);
		LogVO temp;
		try {
			while (rs.next()) {
				temp = new LogVO();
				temp.setId(rs.getInt("clr_id"));
				temp.setMessage(rs.getString("clg_messsage"));
				temp.setDate(rs.getString("clg_date"));
				retorno.add(temp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		connection.closeConnection(pstmt);
		return retorno;
	}		
}
