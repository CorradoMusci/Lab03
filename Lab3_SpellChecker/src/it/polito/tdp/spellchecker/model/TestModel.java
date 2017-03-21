package it.polito.tdp.spellchecker.model;

import java.util.ArrayList;

public class TestModel {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Dictionary d = new Dictionary();
		
		d.loadDictionary("inglese");
		d.loadDictionary("italiano");
		
		ArrayList<String> prova = new ArrayList<String>();
		
		prova.add("ciao");
		prova.add("bello");
		
		ArrayList<RichWord> output = new ArrayList<RichWord>(d.spellCheckText(prova));
		
		for(RichWord r : output){
			System.out.println("La parola è "+ r.toString());
		}

	}

}
