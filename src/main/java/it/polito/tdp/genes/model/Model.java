package it.polito.tdp.genes.model;

import java.util.ArrayList;
import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.genes.db.GenesDao;

public class Model {
	private Graph<String, DefaultWeightedEdge> grafo;
	private GenesDao dao;
	private List<String> vertici;
	private List<Coppia> archi;
	
	private List<String> migliore;
	private double pesoOttimo;
	
	public Model() {
		this.dao=new GenesDao();
		this.vertici=new ArrayList<>();
		this.archi= new ArrayList<>();
	}
	
	public String creaGrafo() {
		this.grafo= new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		//aggiungo vertici
		vertici= this.dao.getVertici();
		Graphs.addAllVertices(this.grafo, vertici);
		//aggiungo archi
		archi=this.dao.getArchi();
		for(Coppia c: archi)
			Graphs.addEdge(this.grafo, c.getLocazione1(), c.getLocazione2(), c.getPeso());
		String s="GRAFO CREATO!\n";
		s+="#VERTICI: "+this.grafo.vertexSet().size()+"\n";
		s+="#ARCHI: "+this.grafo.edgeSet().size()+"\n";
		return s;
		
	}
	
	public List<Adiacenza> getStatistiche(String locazione){
		List<Adiacenza> vicini = new ArrayList<>();
		for(String s: this.grafo.vertexSet())
			if(s.compareTo(locazione)==0) {
				for(String vicino: Graphs.neighborListOf(this.grafo, s)) {
					DefaultWeightedEdge e = this.grafo.getEdge(s, vicino);
					Adiacenza a = new Adiacenza(vicino, this.grafo.getEdgeWeight(e));
					vicini.add(a);
				}
			}
		return vicini;
	}
	
	public List<String> calcolaPercorso(String locazione){
		this.migliore= new ArrayList<>();
		this.pesoOttimo=0;
		List<String> parziale = new ArrayList<>();
		parziale.add(locazione);
		cerca(parziale);
		return migliore;
	}
	
	private void cerca(List<String> parziale) {
		//condizione di terminazione: genero solo soluzioni valide da verificare
		double peso= calcolaPeso(parziale);
		if(peso>pesoOttimo) {
			migliore = new ArrayList<String>(parziale);
			pesoOttimo=peso;
		}
		//costruzione ricorsione
		String ultimo = parziale.get(parziale.size()-1);
		for(String s: Graphs.neighborListOf(this.grafo, ultimo)) {
			if(!parziale.contains(s)) {
				parziale.add(s);
				cerca(parziale);
				parziale.remove(parziale.size()-1);
			}
		}
	}
	

	private double calcolaPeso(List<String> parziale) {
		double peso=0;
		if(parziale.size()>1) {
		for(int i=0; i<parziale.size()-1;i++) {
			String da = parziale.get(i);
			String a = parziale.get(i+1);
			DefaultWeightedEdge e = this.grafo.getEdge(da, a);
			peso+=this.grafo.getEdgeWeight(e);
		}
		return peso;
		}
		return 0;
	}

	public List<String> getVertici(){
		return this.vertici;
	}

	public double getPesoOttimo() {
		return this.pesoOttimo;
	}

}