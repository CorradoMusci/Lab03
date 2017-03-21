package it.polito.tdp.spellchecker.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Dictionary {

	private ArrayList<String> dizionarioInglese = new ArrayList<String>();
	private ArrayList<String> dizionariaoItaliano = new ArrayList<String>();
	//private String language;

	/**
	 * Aggiunge un dizionario di una nuova lingua
	 * 
	 * @param language
	 */

	public void loadDictionary(String language) {
		try {
			FileReader fr;
			//this.language = language;

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

		ArrayList<RichWord> output = new ArrayList<RichWord>();

		for (String s : inputTextList) {
			if (dizionarioInglese.contains(s) || dizionariaoItaliano.contains(s)) {
				output.add(new RichWord(s, true));
			} else {
				output.add(new RichWord(s, false));

			}

		}
		return output;

	}
	
	public int spellCheckText(ArrayList<String> inputTextList,String daCercare){
		int p, u , m;
		
		p = 0;
		
		u = inputTextList.size() -1 ;
		
		while( p <= u){
			m = (p+u)/2;
			if(inputTextList.get(m).compareTo(daCercare) == 0 ){
				return m;
			}else if(inputTextList.get(m).compareTo(daCercare) < 0){
				p = m+1;
			}
			else
				u = m-1;	
		}
		
		return -1;
		
	}

	public ArrayList<String> getDizionarioInglese() {
		return dizionarioInglese;
	}

	public ArrayList<String> getDizionariaoItaliano() {
		return dizionariaoItaliano;
	}
	
	

}
