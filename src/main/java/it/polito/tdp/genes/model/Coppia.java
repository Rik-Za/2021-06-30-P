package it.polito.tdp.genes.model;

public class Coppia {
	private String locazione1;
	private String locazione2;
	private double peso;
	public Coppia(String locazione1, String locazione2, double peso) {
		super();
		this.locazione1 = locazione1;
		this.locazione2 = locazione2;
		this.peso = peso;
	}
	public String getLocazione1() {
		return locazione1;
	}
	public void setLocazione1(String locazione1) {
		this.locazione1 = locazione1;
	}
	public String getLocazione2() {
		return locazione2;
	}
	public void setLocazione2(String locazione2) {
		this.locazione2 = locazione2;
	}
	public double getPeso() {
		return peso;
	}
	public void setPeso(double peso) {
		this.peso = peso;
	}
	
	

}
