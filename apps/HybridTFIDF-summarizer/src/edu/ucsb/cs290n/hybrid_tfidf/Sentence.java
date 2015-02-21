package edu.ucsb.cs290n.hybrid_tfidf;
import java.util.HashMap;


public class Sentence extends Object {
	
	private String sentence;
	private double weight = 0;
	private int numberOfWords = 0;
	private int normalizationFactor = 0;
	
	private String[] words;
	
	public Sentence(String sentence) {
		this.sentence = sentence;
	}
	
	public String getSentence() {
		return sentence;
	}
	
	public void setSentence(String sentence) {
		this.sentence = sentence;
	}
	
	public double getWeight() {
		return weight;
	}

	public int getNumberOfWords() {
		return numberOfWords;
	}

	public void setNumberOfWords(int numberOfWords) {
		this.numberOfWords = numberOfWords;
	}

	public int getNormalizationFactor() {
		return normalizationFactor;
	}

	public void setNormalizationFactor(int normalizationFactor) {
		this.normalizationFactor = normalizationFactor;
	}
	
	public double computeAndSetWeight(HashMap<String,Word> wordsHashTable, 
			int minimumThreshold) {
		
		String[] wordsInSentence = this.getWords();
		this.weight = 0;
		
		for (String wordString : wordsInSentence) {
			this.weight += wordsHashTable.get(wordString).getWeight();
		}
		
		this.weight /= Math.max(this.getNumberOfWords(), minimumThreshold);
		
		return this.weight;
	}
	
	public String[] breakIntoWords() {
		// Here we should be able to get the total number of words (even with stop words)
		// but we should remove the stop words and punctuation from the results. 
		// Also all the words should be lower case and stemmed if an option is provided.
		this.words = new SentenceToWordsBreaker(this).breakIntoWords(); // We should lower case
		this.setNumberOfWords(words.length);
		return this.words;
	}
	
	public String[] getWords() {
		if (this.words == null) {
			return this.breakIntoWords();
		}
		return this.words;
	}
}
