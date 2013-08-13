package br.com.autopass.clearing.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.autopass.clearing.vo.StatusVO;

public class StatusDAO {
	
	private DataSourceAutopass connection;

	public List<StatusVO> getStatus() {
		List<StatusVO> retorno = new ArrayList<StatusVO>();
		connection = new DataSourceAutopass();
		PreparedStatement pstmt;
		String sql = " select case when b.terminou = 0 then round(((sysdate - a.inicio) * 24), 2) "+
					 "             else round(((a.ultimo - a.inicio) * 24), 2) "+
					 "        end executando, "+
					 "        to_char(a.ultimo, 'dd/mm/yyyy hh24:mi:ss') ultimo, "+
					 "        to_char(a.inicio, 'dd/mm/yyyy hh24:mi:ss') inicio "+
					 "  from (select max(stagestart) ultimo, "+
					 "               min(stagestart) inicio "+
					 "          from cmt.clearingstage@dominica "+
					 "         where clr_id = (select max(clr_id) from cmt.clearingstage@dominica)) a, "+
					 "       (select nvl(max(clr_id),0) terminou "+
					 "          from cmt.clearingstage@dominica "+
					 "         where clr_id = (select max(clr_id) from cmt.clearingstage@dominica) "+
					 "           and upper(stage) like '%ALL_DONE%') b";
		pstmt = connection.getPreparedStatement(sql);

		ResultSet rs = connection.executaQuery(pstmt);
		StatusVO temp;
		try {
			while (rs.next()) {
				temp = new StatusVO();
				temp.setLast(rs.getString("ultimo"));
				temp.setStart(rs.getString("inicio"));
				temp.setTimeRunning(rs.getString("executando"));
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
