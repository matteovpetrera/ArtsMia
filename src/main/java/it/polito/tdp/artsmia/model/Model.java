package it.polito.tdp.artsmia.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;
import org.jgrapht.traverse.DepthFirstIterator;

import it.polito.tdp.artsmia.db.ArtsmiaDAO;


public class Model {
	
	private Graph<ArtObject, DefaultWeightedEdge> graph;
	private List<ArtObject> allNodes; 
	private ArtsmiaDAO dao;
	private Map<Integer, ArtObject> idMap;
	
	public Model() {
		this.graph = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		this.allNodes = new ArrayList<>();
		this.dao = new ArtsmiaDAO();
		this.idMap = new HashMap<>();
	}
	
	private void loadNodes() {
		if (this.allNodes.isEmpty())
			this.allNodes = this.dao.listObjects();
		
		if (this.idMap.isEmpty()) {
			for (ArtObject a : this.allNodes)
				this.idMap.put(a.getId(), a);
		}
			
	}
	
	public void buildGraph() {
		loadNodes();
		
    	if (this.graph.vertexSet().size()==0) {

    		Graphs.addAllVertices(this.graph, allNodes);
		
// 		Modo 1: doppio ciclo sui vertici, facile ma molto costoso, sconsigliato per grandi numeri
//		for (ArtObject a1 : this.allNodes) {
//			for (ArtObject a2 : this.allNodes) {
//				
//				int peso = this.dao.getWeight(a1.getId(), a1.getId());
//				Graphs.addEdgeWithVertices(this.graph, a1, a2, peso);
//			}
//		}
		
    		List<edgeModel> allEdges = this.dao.getAllWeights(idMap);
		
			for (edgeModel edgeI : allEdges)
				Graphs.addEdgeWithVertices(this.graph,
						edgeI.getSource(), edgeI.getTarget(),
						edgeI.getPeso());
    	}
    	
		System.out.println("This graphs contains "+ this.graph.vertexSet().size() + " nodes.");
		System.out.println("This graphs contains "+ this.graph.edgeSet().size() + " edges.");
		
	}
	
	public Boolean isGraphCreated() {
		if (this.graph.vertexSet().size()>0 )
			return true;
		else 
			return false;
	}
	
	public Boolean isIDinGraph (Integer objID) {
		if (this.idMap.get(objID) != null)
			return true;
		else 
			return false;
	}
	
	public Integer calcolaConnessa(Integer objID) {
		
		// Modo 1: esploro il grafo, e calcolo la size del set 
		//dei vertici esplorati dall'iteratore (garanzia di aver esplorato tutti i nodi connessi)
		DepthFirstIterator<ArtObject, DefaultWeightedEdge> iterator = 
				new DepthFirstIterator<>(this.graph, this.idMap.get(objID));
		
		List<ArtObject> compConnessa = new ArrayList<>();
		
		while(iterator.hasNext())
			compConnessa.add(iterator.next());
		
		
		// Modo 2: uso la classe ConnectivityInspector che già implementa un metodo per 
		// recuperare tutti i nodi connesso ad un nodo radice
		ConnectivityInspector<ArtObject, DefaultWeightedEdge> inspector = new ConnectivityInspector<>(this.graph);
		Set<ArtObject> setConnesso = inspector.connectedSetOf(this.idMap.get(objID));
		
		return compConnessa.size();
		//return setConnesso.size();
		
	}

}
