package it.polito.tdp.poweroutages.model;

import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.poweroutages.DAO.PowerOutageDAO;

public class Model {
	
	PowerOutageDAO podao;
	
	public Model() {
		podao = new PowerOutageDAO();
	}
	
	public List<Nerc> getNercList() {
		return podao.getNercList();
	}
	
	public List<Blackout> calcolaSottoinsieme(int nerc_id, int x, int y){
		List<Blackout> eventi = this.podao.getBlackouts(nerc_id);
		List<Blackout> sottoinsieme = new ArrayList<>();
		
		cercaSoluzione(sottoinsieme, x, y, 0);
		return sottoinsieme;
	}
	
	//DA QUI
	public void cercaSoluzione(List<Blackout> parziale, int x, int y, int livello) {
		if(!sommaOre(parziale, y))
			return;
		
		
	}
	
	public boolean sommaOre(List<Blackout> parziale, int y) {
		int somma = 0;
		for(Blackout b: parziale)
		{
			int differenza = b.getFine().getHour()-b.getInizio().getHour();
			somma = somma + differenza;
		}
		
		if(somma <= y)
			return true;
		
		return false;
	}

}
