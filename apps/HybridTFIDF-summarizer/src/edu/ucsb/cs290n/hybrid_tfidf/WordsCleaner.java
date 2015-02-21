package edu.ucsb.cs290n.hybrid_tfidf;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class WordsCleaner {
	
	private ArrayList<String> words;
	private ArrayList<String> processedWords;
	private int totalNumberOfWordsWithoutPunctuationAndStemming = 0;
	
	public WordsCleaner(String[] words) {
		this.words = new ArrayList<String>(Arrays.asList(words));
		this.processedWords = new ArrayList<String>();
	}
	
	public String[] removePunctuation() {
		// We should save the number of words without punctuation
		// While we do this we remove the punctuation, we remove the stop words 
		// and stem the words if needed
		
		this.processedWords.clear();
		totalNumberOfWordsWithoutPunctuationAndStemming = 0;
		
		for (String entity : this.words) {
			if (!StopWordChecker.isPunctuation(entity)) {
				totalNumberOfWordsWithoutPunctuationAndStemming++;
				
				processedWords.add(entity.toLowerCase());				
			}
		}
		
		return (String[]) this.processedWords.toArray(new String[processedWords.size()]);
	}

	// Without punctuation and without removing stop words and without stemming the words
	public int getWordNumberWithoutPunctuation() {
		return totalNumberOfWordsWithoutPunctuationAndStemming;
	}
}
