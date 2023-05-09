package it.polito.tdp.artsmia.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graphs;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.artsmia.db.ArtsmiaDAO;

public class Model {
	
	public ArtsmiaDAO dao;
	
	public Graph<ArtObject, DefaultWeightedEdge> creaGrafo() {
		
		dao = new ArtsmiaDAO();
		
        Graph<ArtObject, DefaultWeightedEdge> grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		List<ArtObject> listaOggetti = new ArrayList<ArtObject>();
		listaOggetti = dao.listObjects();
		 
		//aggiungiamo i vertici
		 
		Graphs.addAllVertices(grafo, listaOggetti);
		 
		//System.out.println(grafo);
		
		List<Collegamento> listaArchi = dao.trovaCollegamenti();
		
		for(Collegamento c: listaArchi) {
			Graphs.addEdge(grafo, c.getO1(), c.getO2(), c.getCount());
		}
		 
		System.out.println(grafo.vertexSet().size());
		System.out.println(grafo.edgeSet().size());
		
		return grafo;
	}
	
	public List<ArtObject> listObjects(){
		dao = new ArtsmiaDAO();
		return this.dao.listObjects();
	}
	public Map<Integer,ArtObject> listObjects2(){
		dao = new ArtsmiaDAO();
		return this.dao.listObjects2();
	}
}
