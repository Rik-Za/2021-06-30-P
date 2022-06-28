package it.polito.tdp.genes.model;

public class Adiacenza {
	private String locazione;
	private double peso;
	public Adiacenza(String locazione, double peso) {
		super();
		this.locazione = locazione;
		this.peso = peso;
	}
	public String getLocazione() {
		return locazione;
	}
	public void setLocazione(String locazione) {
		this.locazione = locazione;
	}
	public double getPeso() {
		return peso;
	}
	public void setPeso(double peso) {
		this.peso = peso;
	}
	@Override
	public String toString() {
		return locazione +" "+peso;
	}
	
	

}
