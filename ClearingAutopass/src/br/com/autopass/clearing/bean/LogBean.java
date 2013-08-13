package br.com.autopass.clearing.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.autopass.clearing.dao.LogDAO;
import br.com.autopass.clearing.vo.LogVO;

@ViewScoped
@ManagedBean(name = "LogBean")
public class LogBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public List<LogVO> getLastLog() {
		List<LogVO> logVO = new ArrayList<LogVO>();
		LogDAO logDAO = new LogDAO();
		logVO = logDAO.getLastLog();
		return logVO;
	}
	
	public int getErrors() {
		List<LogVO> logVO = new ArrayList<LogVO>();
		LogDAO logDAO = new LogDAO();
		logVO = logDAO.getErrorLog();
		int quantity = 0;
		quantity = logVO.size();
		return quantity;
	}
}	
