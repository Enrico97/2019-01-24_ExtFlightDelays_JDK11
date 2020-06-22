package it.polito.tdp.extflightdelays.model;

public class Event implements Comparable<Event>{
	
	private String stato;
	private int persone;
	private int tempo;
	
	@Override
	public int compareTo(Event o) {
		// TODO Auto-generated method stub
		return this.tempo-o.tempo;
	}

	public Event(String stato, int persone, int tempo) {
		super();
		this.stato = stato;
		this.persone = persone;
		this.tempo = tempo;
	}

	public String getStato() {
		return stato;
	}

	public int getPersone() {
		return persone;
	}

	public int getTempo() {
		return tempo;
	}

	@Override
	public String toString() {
		return stato + " - " + persone + " - " + tempo;
	}
	
	

}
