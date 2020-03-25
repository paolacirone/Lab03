package it.polito.tdp.spellchecker;

import java.lang.reflect.Array;
import java.net.URL;
import java.util.Arrays;
import java.util.*;
import java.util.ResourceBundle;

import it.polito.tdp.spellchecker.model.Dictionary;
import it.polito.tdp.spellchecker.model.RichWord;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class FXMLController {

	ObservableList<String> choiceBoxList = FXCollections.observableArrayList("Italian", "English");

	private Dictionary dizionario;
	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private ChoiceBox<String> choiceBox;

	@FXML
	private TextArea txtArea;

	@FXML
	private Button bttCheck;

	@FXML
	private TextArea txtWrongWords;

	@FXML
	private Button bttClear;

	@FXML
	private Label lblErrors;

	@FXML
	private Label lblTime;

	@FXML
	void doClearText(ActionEvent event) {

		txtArea.clear();
		this.txtWrongWords.clear();
		
	}

	@FXML
	void doSpellCheck(ActionEvent event) {
		
		long time1=System.nanoTime();

		String text = txtArea.getText().replaceAll("[.,\\/#!$%\\^&\\*;:{}=\\-_`~()\\[\\]\"]", "").toLowerCase();

		String[] testo = text.split(" ");
		int errate=0;

		List<String> x = Arrays.asList(testo);
		System.out.println(x.size());

		// controllo che nel choiceBox sia stata scelta una lingua
		if (choiceBox.getValue() == null) {
			txtArea.setText("Selezionare una lingua.");
			return;
		} 
		
		dizionario.lingua(choiceBox.getValue());
	    dizionario.loadDictionary(choiceBox.getValue());
		

		List<RichWord> parole = new ArrayList<RichWord>();

		//parole = dizionario.spellCheckList(x);
		parole=dizionario.spellCheckListLinear(x);
		for (RichWord r : parole) {
			if(r.isCorretta()==false) {
			txtWrongWords.appendText(r.getParola()+"\n");
			errate++;
			}
			
		}
		long time2=System.nanoTime();
		
		this.lblErrors.setText("The text contains "+errate+" errors");
		this.lblTime.setText("Spell check completed in "+(time2-time1));
	

	}

	@FXML
	void initialize() {
		assert choiceBox != null : "fx:id=\"choiceBox\" was not injected: check your FXML file 'Scene.fxml'.";
		assert txtArea != null : "fx:id=\"txtArea\" was not injected: check your FXML file 'Scene.fxml'.";
		assert bttCheck != null : "fx:id=\"bttCheck\" was not injected: check your FXML file 'Scene.fxml'.";
		assert txtWrongWords != null : "fx:id=\"txtWrongWords\" was not injected: check your FXML file 'Scene.fxml'.";
		assert bttClear != null : "fx:id=\"bttClear\" was not injected: check your FXML file 'Scene.fxml'.";
		assert lblErrors != null : "fx:id=\"lblErrors\" was not injected: check your FXML file 'Scene.fxml'.";
		assert lblTime != null : "fx:id=\"lblTime\" was not injected: check your FXML file 'Scene.fxml'.";
		choiceBox.setItems(choiceBoxList);
	}

	public void setModel(Dictionary model) {
		this.dizionario = model;

	}

}
