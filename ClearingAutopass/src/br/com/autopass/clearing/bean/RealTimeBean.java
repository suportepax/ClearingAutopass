package br.com.autopass.clearing.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.autopass.clearing.dao.RealTimeDAO;
import br.com.autopass.clearing.vo.RealTimeVO;

@ViewScoped
@ManagedBean(name = "RealTimeBean")
public class RealTimeBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public List<RealTimeVO> getRealTime() {
		List<RealTimeVO> realTimeVO = new ArrayList<RealTimeVO>();
		RealTimeDAO realTimeDAO = new RealTimeDAO();
		realTimeVO = realTimeDAO.getRealTime();
		return realTimeVO;
	}
}
