package it.polito.tdp.extflightdelays.model;

public class Stampa implements Comparable<Stampa> {
	
	private String s;
	private double i;
	
	public Stampa(String s, double i) {
		super();
		this.s = s;
		this.i = i;
	}

	public String getS() {
		return s;
	}

	public double getI() {
		return i;
	}

	@Override
	public String toString() {
		return s + " " + i;
	}

	@Override
	public int compareTo(Stampa o) {
		int x = 0;
		if(this.i<o.i)
			x=1;
		if(this.i>o.i)
			x=-1;
		return x;
	}
	
	

}
