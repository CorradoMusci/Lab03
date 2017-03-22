package it.polito.tdp.spellchecker.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
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

	public List<RichWord> spellCheckText(List<String> inputTextList) throws Exception {

		ArrayList<RichWord> output = new ArrayList<RichWord>();

		for (String s : inputTextList) {
			switch (language) {
			case "italiano":
				if (dizionariaoItaliano.contains(s)) {
					output.add(new RichWord(s, true));
				} else {
					output.add(new RichWord(s, false));

				}
				break;

			case "inglese":
				if (dizionarioInglese.contains(s)) {
					output.add(new RichWord(s, true));
				} else {
					output.add(new RichWord(s, false));

				}
				break;

			default:
				throw new Exception("parola non trovata");
			}
		}

		return output;

	}

	public int spellCheckText(String daCercare) {
		int p, u = 0, m;

		p = 0;
		ArrayList<String> dictionary = null;
		
		dictionary = this.getDizionario();

		while (p <= u) {
			m = (p + u) / 2;
			if (dictionary.get(m).compareTo(daCercare) == 0) {
				return m;
			} else if (dictionary.get(m).compareTo(daCercare) < 0) {
				p = m + 1;
			} else
				u = m - 1;
		}

		return -1;

	}

	public ArrayList<String> getDizionario() {
		if (language.equals("inglese")) {
			Collections.sort(dizionarioInglese);
			return dizionarioInglese;
		} else if (language.equals("italiano")) {
			Collections.sort(dizionariaoItaliano);
			return dizionariaoItaliano;
		}
		return null;
	}

}
