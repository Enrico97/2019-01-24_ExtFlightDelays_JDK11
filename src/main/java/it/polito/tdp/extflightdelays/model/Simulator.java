package it.polito.tdp.extflightdelays.model;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

import org.jgrapht.Graphs;

public class Simulator {
	
	Model model;
	private Map <String, Integer> turisti;
	private PriorityQueue<Event> queue = new PriorityQueue<>();
	int giorni;
	
	public Simulator (Model model) {
		this.model = model;
	}
	
	public void simula(int giorni, int persone, String partenza) {
		
		turisti = new HashMap<>();
		queue.clear();
		int i = 0;
		this.giorni = giorni;
		boolean partito = false;
		
		for(String s : model.creaGrafo().vertexSet())
			turisti.put(s, 0);
		turisti.put(partenza, persone);
		
		double tot = 0;
		for(String s : Graphs.successorListOf(model.creaGrafo(), partenza))
			tot += model.creaGrafo().getEdgeWeight(model.creaGrafo().getEdge(partenza, s));
		
		while (i < persone) {
			double y = Math.random();
			for(String s : Graphs.successorListOf(model.creaGrafo(), partenza)) {
				if(model.creaGrafo().getEdgeWeight(model.creaGrafo().getEdge(partenza, s))/tot > y) {
					Event e = new Event(s, 1, 1);
					queue.add(e); 
					i++;
					turisti.put(s, turisti.get(s)+1);
					turisti.put(partenza, turisti.get(partenza)-1);
					System.out.println(e);
					partito = true;
					break;
				}	
			}if (partito == false) {
					Event e = new Event (partenza, 1, 1);
					queue.add(e); 
					System.out.println(e);
				}
		}
		while(!this.queue.isEmpty()) {
			Event ee = this.queue.poll();
			processEvent(ee);
	}
	}

	private void processEvent(Event e) {
		if(e.getTempo()<giorni) {
			double y = Math.random();
			double tot = 0;
			boolean partito = false;
			for(String s : Graphs.successorListOf(model.creaGrafo(), e.getStato()))
				tot += model.creaGrafo().getEdgeWeight(model.creaGrafo().getEdge(e.getStato(), s));
			
			for(String s : Graphs.successorListOf(model.creaGrafo(), e.getStato())) {
				if(model.creaGrafo().getEdgeWeight(model.creaGrafo().getEdge(e.getStato(), s))/tot > y) {
					Event ev = new Event(s, 1, e.getTempo()+1);
					queue.add(ev);
					turisti.put(s, turisti.get(s)+1);
					turisti.put(e.getStato(), turisti.get(e.getStato())-1);
					System.out.println(ev);
					partito = true;
					break;
				}	
			}if (partito == false){
					Event ev = new Event (e.getStato(), 1, e.getTempo()+1); 
					queue.add(ev);
					System.out.println(ev);
				}
	}
	}

	public Map<String, Integer> getTuristi() {
		return turisti;
	}
	
}
