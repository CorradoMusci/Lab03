/**
 * Sample Skeleton for 'SpellChecker.fxml' Controller Class
 */

package it.polito.tdp.spellchecker.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import it.polito.tdp.spellchecker.model.Dictionary;
import it.polito.tdp.spellchecker.model.RichWord;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class SpellCheckerController {

	Dictionary model;

	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location;

	@FXML // fx:id="boxLingua"
	private ComboBox<String> boxLingua; // Value injected by FXMLLoader

	@FXML // fx:id="txtInput"
	private TextArea txtInput; // Value injected by FXMLLoader

	@FXML // fx:id="txtResult"
	private TextArea txtResult; // Value injected by FXMLLoader

	@FXML // fx:id="txtError"
	private TextField txtError; // Value injected by FXMLLoader

	@FXML // fx:id="txtTempEsecuzione"
	private TextField txtTempEsecuzione; // Value injected by FXMLLoader

	@FXML
	private Label txtTempEsecuzione2;

	@FXML
	void doClearText(ActionEvent event) {

		txtInput.clear();
		txtResult.clear();
		txtError.clear();
		txtTempEsecuzione.clear();
		txtTempEsecuzione2.setText("");

	}

	@FXML
	void doSpellCheck(ActionEvent event) throws Exception {

		// Scelgo la lingua
		String lingua = boxLingua.getValue();
		model.loadDictionary(lingua);

		String input[] = txtInput.getText().split(" ");

		// Creo la lista di input per il metodo omonimo
		ArrayList<String> inputList = new ArrayList<String>();

		for (int i = 0; i < input.length; i++) {
			inputList.add(input[i].toLowerCase().replaceAll("[ \\p{Punct}]", ""));
		}

		// Misuro il tempo di esecuzione del metodo
		long t1 = System.nanoTime();

		int errori = 0;
		for (RichWord r : model.spellCheckText(inputList))
			if (r.isCorretta() == false) {
				txtResult.appendText(r.toString() + "\n");
				errori++;
			}

		// Misuro il tempo di esecuzione del metodo {Conclusione del primo ed
		// inizio del secondo)
		long t2 = System.nanoTime();

		for (String s : inputList) {
			int m = model.spellCheckText(s);

			if (m == -1) {
				// System.out.println("La parola " + s + " non è stata
				// trovata");
				txtResult.appendText(s.toString() + "\n");
			} // else
				// System.out.println(model.getDizionario().get(m));
				// txtResult.appendText(s.toString() + "\n");

		}

		long t3 = System.nanoTime();

		txtError.setText("The text contains " + errori + " errors");

		txtTempEsecuzione.setText("SpellCheck completed in " + ((t2 - t1) / 1e9) + " seconds");

		txtTempEsecuzione2.setText("SpellCheck completed in " + ((t3 - t2) / 1e9) + " seconds");

	}

	@FXML // This method is called by the FXMLLoader when initialization is
			// complete
	void initialize() {

		boxLingua.getItems().addAll("inglese", "italiano");

	}

	public void set(Dictionary model) {
		// TODO Auto-generated method stub
		this.model = model;

	}
}