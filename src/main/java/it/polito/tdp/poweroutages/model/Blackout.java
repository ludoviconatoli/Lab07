package it.polito.tdp.poweroutages.model;
import java.time.*;
import java.util.Date;

public class Blackout {

	int id;
	LocalDateTime inizio;
	LocalDateTime fine;
	int clientiCoinvolti;
	
	public Blackout(int id, LocalDateTime date, LocalDateTime date2, int clientiCoinvolti) {
		
		this.id = id;
		this.inizio = date;
		this.fine = date2;
		this.clientiCoinvolti = clientiCoinvolti;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDateTime getInizio() {
		return inizio;
	}

	public void setInizio(LocalDateTime inizio) {
		this.inizio = inizio;
	}

	public LocalDateTime getFine() {
		return fine;
	}

	public void setFine(LocalDateTime fine) {
		this.fine = fine;
	}

	public int getClientiCoinvolti() {
		return clientiCoinvolti;
	}

	public void setClientiCoinvolti(int clientiCoinvolti) {
		this.clientiCoinvolti = clientiCoinvolti;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Blackout other = (Blackout) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	public String toString() {
		return this.id+" "+this.getClientiCoinvolti()+" "+this.inizio +" "+fine;
	}
}