package br.com.autopass.clearing.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.autopass.clearing.dao.StageDAO;
import br.com.autopass.clearing.vo.StageVO;

@ViewScoped
@ManagedBean(name = "StageBean")
public class StageBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public List<StageVO> getLastStage() {
		List<StageVO> stageVO = new ArrayList<StageVO>();
		StageDAO stageDAO = new StageDAO();
		stageVO = stageDAO.getLastStage();
		return stageVO;
	}
	
	public String getStage() {
		String mensagem;
		StageDAO stageDAO = new StageDAO();
		if (stageDAO.isFinishStage()) {
			mensagem = "Finished.";
		} else {
			mensagem = "Running.";
		}
		return mensagem;
	}
}
