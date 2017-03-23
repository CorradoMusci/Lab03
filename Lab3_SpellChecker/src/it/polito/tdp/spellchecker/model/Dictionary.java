package it.polito.tdp.spellchecker.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Dictionary {

	HashMap<String, ArrayList<String>> dizionari = new HashMap<>();
	String linguaCorrente;

	/**
	 * Aggiunge un dizionario di una nuova lingua
	 * 
	 * @param language
	 */

	public void loadDictionary(String language) {
		ArrayList<String> dizionario = new ArrayList<String>();
		if (!dizionari.containsKey(language)) {
			try {

				FileReader fr;

				if (language.compareTo("inglese") == 0) {

					fr = new FileReader("rsc/English.txt");

				} else {
					fr = new FileReader("rsc/Italian.txt");
				}

				BufferedReader br = new BufferedReader(fr);

				String word;

				while ((word = br.readLine()) != null) {
					// Aggiungere parola alla struttura dati

					dizionario.add(word);
				}
				br.close();
			} catch (IOException e) {
				System.out.println("Errore nella lettura del file");
			}
			Collections.sort(dizionario);
			dizionari.put(language, dizionario);

		}
		linguaCorrente = language;
	}

	public List<RichWord> spellCheckText(List<String> inputTextList) throws Exception {

		ArrayList<RichWord> output = new ArrayList<RichWord>();

		for (String s : inputTextList) {

			if (dizionari.get(linguaCorrente).contains(s)) {
				output.add(new RichWord(s, true));
			} else {
				output.add(new RichWord(s, false));

			}
		}

		return output;

	}

	public int spellCheckText(String daCercare) {
		int p, u, m;

		p = 0;

		u = dizionari.get(linguaCorrente).size() - 1;

		while (p <= u) {
			m = (p + u) / 2;
			if (dizionari.get(linguaCorrente).get(m).compareTo(daCercare) == 0) {
				return m;
			} else if (dizionari.get(linguaCorrente).get(m).compareTo(daCercare) < 0) {
				p = m + 1;
			} else
				u = m - 1;
		}

		return -1;

	}

	public ArrayList<String> getDizionario() {
		return dizionari.get(linguaCorrente);
	}

}
