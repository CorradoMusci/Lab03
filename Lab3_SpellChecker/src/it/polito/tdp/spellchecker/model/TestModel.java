package it.polito.tdp.spellchecker.model;

import java.util.ArrayList;
import java.util.HashSet;



public class TestModel {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		Dictionary d = new Dictionary();

		d.loadDictionary("inglese");
		d.loadDictionary("italiano");

		ArrayList<String> prova = new ArrayList<String>();

		prova.add("ciao");
		prova.add("bello");
		prova.add("ggsdfs");

		for (int i = 0; i < 10000000; i++) {
			prova.add("ciao");
		}
		long t0 = System.nanoTime();
		
		ArrayList<RichWord> output =  d.spellCheckText(prova);
        
		long t1 = System.nanoTime();
		
                                     
	/*	for (RichWord r : output) {
			System.out.println("La parola è " + r.toString());
		}*/
	
		// STO PROVANDO IL NUOVO ALGORITMO
	/*	for (String s : prova) {
			int m = d.spellCheckText(s);
			/*
			  if (m == -1) { 
			  	System.out.println("La parola " + s +  " non è stata trovata"); 
			  } else
			  		System.out.println(d.getDizionario().get(m));
			 

		}*/
		
		long t2 = System.nanoTime();

		System.out.println("il primo metodo ha tempo : " + ((t1 - t0) / 1e9) + "\nil secondo metodo ha tempo : "
				+ ((t2 - t1) / 1e9));

	}

}
