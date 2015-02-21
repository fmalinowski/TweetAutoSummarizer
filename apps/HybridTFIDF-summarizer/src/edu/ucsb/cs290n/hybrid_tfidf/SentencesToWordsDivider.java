package edu.ucsb.cs290n.hybrid_tfidf;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;


public class SentencesToWordsDivider {
	
	private List<Sentence> listOfSentences;
	private int totalNumberOfWords;
	private HashMap<String, Word> wordsHashTable;

	public SentencesToWordsDivider(List<Sentence> listOfSentences) {
		this.listOfSentences = listOfSentences;
		this.totalNumberOfWords = 0;
	}
	
	public HashMap<String, Word> divideSentencesIntoUniqueWords() {
		this.wordsHashTable = new HashMap<String, Word>();
		this.totalNumberOfWords = 0;

		for (Sentence sentence : this.listOfSentences) {
			String[] words = sentence.getWords();
				
			this.addSentenceWordsIntoWordHash(words);			
		}
			
		return wordsHashTable;
	}
	
	public HashMap<String, Word> getWordsHashTable() {
		return this.wordsHashTable;
	}
	
	public int getTotalNumberOfWords() {
		return this.totalNumberOfWords;
	}
	
	private void addSentenceWordsIntoWordHash(String sentenceWords[]) {
		HashMap<String, Boolean> wordsInSentenceHashTable = new HashMap<String, Boolean>();
		
		for (String wordString : sentenceWords) {
			Word word;
			if (this.wordsHashTable.containsKey(wordString)) {
				word = this.wordsHashTable.get(wordString);
			}
			else {
				word = new Word(wordString);
				this.wordsHashTable.put(wordString, word);
			}
			
			word.setNumberOfOccurences(word.getNumberOfOccurences()+1);
			if (!wordsInSentenceHashTable.containsKey(wordString)) {
				wordsInSentenceHashTable.put(wordString, true);
				word.setNumberOfSentencesContainingWord(word.getNumberOfSentencesContainingWord()+1);
			}
			
			this.totalNumberOfWords++;
		}
	}
	
}
