package it.polito.tdp.spellchecker.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Dictionary {

	List<String> dizionarioItaliano = new ArrayList<String>();
	List<String> dizionarioInglese = new ArrayList<String>();

	
	
	
	public Dictionary() {
		
	}

	public void loadDictionary(String language) {

		if (language.compareTo("Italian") == 0) {
			try {
				FileReader fr = new FileReader("Italian.txt");
				BufferedReader br = new BufferedReader(fr);
				String word;
				while (((word = br.readLine()) != null)) {
					dizionarioItaliano.add(word);

				}
				br.close();
			} catch (IOException e) {
				System.out.println("Errore nella lettura del file");

			}
		} else if (language.compareTo("English") == 0) {
			try {
				FileReader fr = new FileReader("English.txt");
				BufferedReader br = new BufferedReader(fr);
				String word;
				while (((word = br.readLine()) != null)) {
					dizionarioInglese.add(word);
				}
				br.close();
			} catch (IOException e) {
				System.out.println("Errore nella lettura del file");

			}
		}
	}

	public List<RichWord> spellCheckList(List<String> inputTextList) {

		Map<String, RichWord> h = new TreeMap<String, RichWord>();

		for (String s : inputTextList) {
			for (String x : dizionarioItaliano) {
				for (String y : dizionarioInglese) {
					if (s.compareTo(y) == 0 || s.compareTo(x) == 0) {
						RichWord w = new RichWord(s, true);
						h.put(s, w);
					} else {
						RichWord w = new RichWord(s, false);
						h.put(s, w);
					}

				}
			}
		}

		return (List<RichWord>) h.values();
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
