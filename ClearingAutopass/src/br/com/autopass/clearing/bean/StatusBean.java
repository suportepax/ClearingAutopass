package br.com.autopass.clearing.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.autopass.clearing.dao.StatusDAO;
import br.com.autopass.clearing.vo.StatusVO;

@ViewScoped
@ManagedBean(name = "StatusBean")
public class StatusBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public List<StatusVO> getStatus() {
		List<StatusVO> statusVO = new ArrayList<StatusVO>();
		StatusDAO statusDAO = new StatusDAO();
		statusVO = statusDAO.getStatus();
		return statusVO;
	}
}
