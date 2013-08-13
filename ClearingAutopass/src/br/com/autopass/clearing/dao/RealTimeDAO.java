package br.com.autopass.clearing.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.autopass.clearing.vo.RealTimeVO;

public class RealTimeDAO {

	private DataSourceAutopass connection;

	public List<RealTimeVO> getRealTime() {
		List<RealTimeVO> retorno = new ArrayList<RealTimeVO>();
		connection = new DataSourceAutopass();
		PreparedStatement pstmt;
		String sql = "select clrt_level1_message, "+
					 "	     to_char(clrt_lastupdate, 'dd/mm/yyyy hh24:mi:ss') clrt_lastupdate "+
					 " from cmt.clearingrealtime@dominica";
		pstmt = connection.getPreparedStatement(sql);

		ResultSet rs = connection.executaQuery(pstmt);
		RealTimeVO temp;
		try {
			while (rs.next()) {
				temp = new RealTimeVO();
				temp.setMessage(rs.getString("clrt_level1_message"));
				temp.setLastUpdate(rs.getString("clrt_lastupdate"));
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
