package it.polito.tdp.metrodeparis.model;

import org.jgrapht.WeightedGraph;
import org.jgrapht.graph.DefaultWeightedEdge;

public class TestModel {

	public static void main(String[] args) {
		
		Model model = new Model();
		long start, end ;
		
		start=System.nanoTime() ;
		model.creaGrafo();
		end=System.nanoTime() ;
		System.out.format("Metodo 1: %d ms\n", (end-start)/1000000) ;
		model.printStats();
		
		
	}

	
	
	
}
