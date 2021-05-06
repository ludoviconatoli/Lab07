package it.polito.tdp.poweroutages.model;

import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.poweroutages.DAO.PowerOutageDAO;

public class Model {
	
	PowerOutageDAO podao;
	List<Blackout> eventi;
	List<Blackout> soluzioneMigliore;
	List<Nerc> nercList;
	int maxNumClienti;
	
	public Model() {
		podao = new PowerOutageDAO();
		nercList = podao.getNercList();
	}
	
	public List<Nerc> getNercList() {
		return podao.getNercList();
	}
	
	public List<Blackout> calcolaSottoinsieme(int nerc_id, int x, int y){
		eventi = this.podao.getBlackouts(nerc_id);
		soluzioneMigliore = new ArrayList<Blackout>();
		
		this.maxNumClienti = 0;
		
		cercaSoluzione(new ArrayList<Blackout>(), x, y);
		return soluzioneMigliore;
	}
	
	
	public void cercaSoluzione(List<Blackout> parziale, int x, int y) {
		
		if(maxNumClienti < clientiTotali(parziale)) {
			maxNumClienti = clientiTotali(parziale);
			soluzioneMigliore = new ArrayList<Blackout>(parziale);
		}
		
		for(Blackout b: eventi) {
			if(!parziale.contains(b)) {
				parziale.add(b);
				
				if(differenzaOre(parziale, y) && differenzaAnni(parziale, x)) {
					cercaSoluzione(parziale, x, y);
				}
				
				parziale.remove(b);
			}
		}	
	}
	
	public boolean differenzaOre(List<Blackout> parziale, int y) {
		int sum = sommaOre(parziale);
		
		if(sum > y) {
			return false;
		}
		
		return true;
	}
	
	public boolean differenzaAnni(List<Blackout> parziale, int x) {
		if(parziale.size() >= 2) {
			int differenza = parziale.get(parziale.size()-1).getFine().getYear() - parziale.get(0).getInizio().getYear();
			if(differenza > x)
				return false;
			else
				return true;
		}
		return true;
	}

	public boolean numeroClientiMaggiore(List<Blackout> lista1, List<Blackout> lista2) {
		
		int l1 = 0;
		int l2 = 0;
		for(Blackout b: lista1)
			l1 = l1 + b.getClientiCoinvolti();
		
		for(Blackout b: lista2)
			l2 = l2 + b.getClientiCoinvolti();
		
		if(l1<l2)
			return false;
		else
			return true;
	}
	
	public int sommaOre(List<Blackout> ps) {
		int somma = 0;
		for(Blackout b: ps) {
			somma += Math.abs(b.getFine().getHour() - b.getInizio().getHour());
		}
		
		return somma;
	}
	
	public int clientiTotali(List<Blackout> ps) {
		int somma = 0;
		for(Blackout b: ps) {
			somma += b.getClientiCoinvolti();
		}
		
		return somma;
	}
}
