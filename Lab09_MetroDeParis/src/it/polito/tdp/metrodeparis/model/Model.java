package it.polito.tdp.metrodeparis.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.jgrapht.Graphs;
import org.jgrapht.WeightedGraph;
import org.jgrapht.alg.DijkstraShortestPath;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.GraphPathImpl;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.graph.WeightedMultigraph;

import com.javadocmd.simplelatlng.LatLng;
import com.javadocmd.simplelatlng.LatLngTool;
import com.javadocmd.simplelatlng.util.LengthUnit;

import it.polito.tdp.metrodeparis.dao.MetroDAO;

public class Model {

	private WeightedGraph<Fermata, DefaultWeightedEdge> grafo;
	private List<Fermata>listaFermate;
	private List<Linea>listaLinee;
	private List<Connessione>listaConnessioni;
	private DijkstraShortestPath<Fermata, DefaultWeightedEdge>cmin;
	

	public Model()
	{
		
	}
	
	
	public List<Fermata> getFermate() {
		if(this.listaFermate==null) {
			MetroDAO dao = new MetroDAO() ;
			this.listaFermate = dao.getAllFermate();
		}
		return this.listaFermate ;
	}
	
	public List<Linea> getLinee() {
		if(this.listaLinee==null) {
			MetroDAO dao = new MetroDAO() ;
			this.listaLinee = dao.getAllLinee() ;
		}
		return this.listaLinee ;
	}
	
	public List<Connessione> getConnessioni() {
		if(this.listaConnessioni==null) {
			MetroDAO dao = new MetroDAO() ;
			this.listaConnessioni = dao.getAllConnessione() ;
		}
		return this.listaConnessioni ;
	}
	
	
	
	
	public void creaGrafo() {
		
		this.grafo = new WeightedMultigraph <Fermata,DefaultWeightedEdge>(DefaultWeightedEdge.class);
		
		Connessione c1=null;
		Connessione c2=null;
		
		MetroDAO dao = new MetroDAO() ;
		DefaultWeightedEdge e;
		
		listaConnessioni=dao.getAllConnessione();
		// crea i vertici del grafo
		Graphs.addAllVertices(grafo, this.getFermate()) ;
	
		// crea gli archi del grafo -- versione 2
		
		for(Fermata f: grafo.vertexSet()) {
			List<Fermata> vicini = dao.getVicini(f);
			for(Fermata f2: vicini)
			{
				//trovo la connessione
				for(Connessione c: listaConnessioni)
				{
					if(c.getStazP()==f.getIdFermata())
					{
						 c1=c;
					}
					
					if(c.getStazA()==f2.getIdFermata())
					{
						 c2=c;
					}
				}
				
				//calcolo la distanza
				double distanza= Math.abs(LatLngTool.distance(f.getCoords(), f2.getCoords(), LengthUnit.KILOMETER));
			
				
				//calcolo la velocita
				double velocita= dao.getVelocita(c1, c2);
				
				
				//costruisco l'arco
				
				if(!f.equals(f2))
				{
					 e= grafo.addEdge(f, f2) ;
					 
					//peso il grafo
					 grafo.setEdgeWeight(e, (distanza/velocita));
				}
			
				
			}
			
		}
		
		System.out.println(grafo.vertexSet());
		
		for(DefaultWeightedEdge arco: grafo.edgeSet())
		{
			System.out.println(arco);
			System.out.println(grafo.getEdgeWeight(arco));
		}
		
	}

	
	public void printStats() {
		System.out.format("Grafo: Vertici %d, Archi %d\n", grafo.vertexSet().size(), grafo.edgeSet().size());
	}

	
	//definizione cammino
	
	public List<Fermata> creaCammino(Fermata partenza, Fermata arrivo)
	{
		List<Fermata> ftemp= new LinkedList<>();
		cmin=new DijkstraShortestPath<Fermata, DefaultWeightedEdge>(grafo, partenza, arrivo);
		cmin.getPath();
		
		for(DefaultWeightedEdge e : cmin.getPathEdgeList())
		{
			
		}
		
		return null;
		
	}
	
	
}
