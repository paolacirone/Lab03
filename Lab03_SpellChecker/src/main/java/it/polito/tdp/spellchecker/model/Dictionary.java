package it.polito.tdp.spellchecker.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Dictionary {

	private List<String> dizionarioItaliano;
	private List<String> dizionarioInglese;
	private String lingua;

	public Dictionary() {
		dizionarioItaliano = new ArrayList<String>();
		dizionarioInglese = new ArrayList<String>();
	}

	public void loadDictionary(String language) {

		if (language.compareTo("Italian") == 0) {

			try {
				FileReader fr = new FileReader(
						"C:\\Users\\Lenovo Personal Pc\\git\\Lab03\\Lab03_SpellChecker\\target\\classes\\Italian.txt");
				BufferedReader br = new BufferedReader(fr);
				String word;
				while (((word = br.readLine()) != null)) {
					dizionarioItaliano.add(word.toLowerCase());

				}
				br.close();
			} catch (IOException e) {
				System.out.println("Errore nella lettura del file");

			}
		} else if (language.compareTo("English") == 0) {
			try {
				FileReader fr = new FileReader(
						"C:\\Users\\Lenovo Personal Pc\\git\\Lab03\\Lab03_SpellChecker\\target\\classes\\English.txt");
				BufferedReader br = new BufferedReader(fr);
				String word;
				while (((word = br.readLine()) != null)) {
					dizionarioInglese.add(word.toLowerCase());
				}
				br.close();
			} catch (IOException e) {
				System.out.println("Errore nella lettura del file");

			}
		}
	}

	public String lingua(String language) {
		return lingua = language;
	}

	public List<RichWord> spellCheckList(List<String> inputTextList) {

		List<RichWord> h = new ArrayList<RichWord>();

		if (lingua.compareTo("Italian") == 0) {
			for (String s : inputTextList) {
				RichWord w = new RichWord();
				if (dizionarioItaliano.contains(s)) {
					w.setParola(s);
					w.setCorretta(true);
				} else {
					w.setParola(s);
					w.setCorretta(false);

				}
				h.add(w);
			}

		} else {
			for (String s : inputTextList) {
				RichWord w = new RichWord();
				if (dizionarioInglese.contains(s)) {
					w.setParola(s);
					w.setCorretta(true);
				} else {
					w.setParola(s);
					w.setCorretta(false);
				}

				h.add(w);
			}
		}
		return h;

	}

	public List<String> getDizionarioItaliano() {
		return dizionarioItaliano;
	}

	public void setDizionarioItaliano(List<String> dizionarioItaliano) {
		this.dizionarioItaliano = dizionarioItaliano;
	}

	public List<String> getDizionarioInglese() {
		return dizionarioInglese;
	}

	public void setDizionarioInglese(List<String> dizionarioInglese) {
		this.dizionarioInglese = dizionarioInglese;
	}

}
