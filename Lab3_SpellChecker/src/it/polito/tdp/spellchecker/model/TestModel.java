package it.polito.tdp.spellchecker.model;

import java.util.ArrayList;

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

		ArrayList<RichWord> output = new ArrayList<RichWord>(d.spellCheckText(prova));

		for (RichWord r : output) {
			System.out.println("La parola è " + r.toString());
		}

		// STO PROVANDO IL NUOVO ALGORITMO
		for (String s : prova) {
			int m = d.spellCheckText(s);

			if (m == -1) {
				System.out.println("La parola " + s + " non è stata trovata");
			} else
				System.out.println(d.getDizionario().get(m));

		}

	}

}
