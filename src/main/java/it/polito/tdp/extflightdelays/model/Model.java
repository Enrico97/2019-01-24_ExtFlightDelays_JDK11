package it.polito.tdp.extflightdelays.model;

import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultDirectedWeightedGraph;
import org.jgrapht.graph.DefaultWeightedEdge;

import it.polito.tdp.extflightdelays.db.ExtFlightDelaysDAO;

public class Model {
	
	ExtFlightDelaysDAO dao = new ExtFlightDelaysDAO();
	Graph<String, DefaultWeightedEdge> grafo;
	
	public List<String> loadAllStates(){
		return dao.loadAllStates();
	}
	
	public Graph<String, DefaultWeightedEdge> creaGrafo() {
		grafo = new DefaultDirectedWeightedGraph<>(DefaultWeightedEdge.class);
		Graphs.addAllVertices(grafo, dao.loadAllStates());
		for (Adiacenza a : dao.adiacenze())
			Graphs.addEdge(grafo, a.getS1(), a.getS2(), a.getPeso());
		System.out.println(grafo);
		return grafo;
	}

}
