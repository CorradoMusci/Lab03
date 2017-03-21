package it.polito.tdp.spellchecker.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Dictionary {

	private ArrayList<String> dizionarioInglese = new ArrayList<String>();
	private ArrayList<String> dizionariaoItaliano = new ArrayList<String>();
	private String language;

	/**
	 * Aggiunge un dizionario di una nuova lingua
	 * 
	 * @param language
	 */

	public void loadDictionary(String language) {
		try {
			FileReader fr;
			this.language = language;

			if (language.compareTo("inglese") == 0) {

				fr = new FileReader("rsc/English.txt");

			} else {
				fr = new FileReader("rsc/Italian.txt");
			}

			BufferedReader br = new BufferedReader(fr);

			String word;

			while ((word = br.readLine()) != null) {
				// Aggiungere parola alla struttura dati

				if (language.compareTo("inglese") == 0) {

					dizionarioInglese.add(word);

				} else {
					dizionariaoItaliano.add(word);

				}
			}
			br.close();
		} catch (IOException e) {
			System.out.println("Errore nella lettura del file");
		}

	}

	public List<RichWord> spellCheckText(List<String> inputTextList) {

		ArrayList<String> input = new ArrayList<String>(inputTextList);

		ArrayList<RichWord> output = new ArrayList<RichWord>();

		for (String s : input) {
			if (dizionarioInglese.contains(s) || dizionariaoItaliano.contains(s)) {
				output.add(new RichWord(s, true));
			} else {
				output.add(new RichWord(s, false));

			}

		}
		
		
	/*	int confronto;
		for (String s : input) {
			if (language.compareTo("inglese") == 0) {
				confronto = s.compareTo(dizionarioInglese.get(dizionarioInglese.size() / 2));
				if(confronto == 0){
					
				}
				
				if (confronto > 0) {
                   for(int y = (dizionarioInglese.size()/2); y < dizionarioInglese.size(); y++){
                	   confronto = s.compareTo(dizionarioInglese.get(dizionarioInglese.size() / 4));
                	     if(confronto > 0){
                	    	 for(int z = (dizionarioInglese.size()/4); z < dizionarioInglese.size(); z++))
                	     }
                   }
				}

			} else if (language.compareTo("italiano") == 0) {

			}

		}*/

		return output;

	}

}
