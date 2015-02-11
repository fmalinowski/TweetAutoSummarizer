import java.util.HashMap;

import com.sun.xml.internal.bind.v2.runtime.RuntimeUtil.ToStringAdapter;


public class Sentence extends Object {
	
	private String sentence;
	private double weight = 0;
	private int numberOfWords = 0;
	private int normalizationFactor = 0;
	
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
		
		String[] wordsInSentence = new SentenceToWordsBreaker(this).breakIntoWords();
		this.weight = 0;
		
		for (String wordString : wordsInSentence) {
			this.weight += wordsHashTable.get(wordString).getWeight();
		}
		
		this.weight /= Math.max(wordsInSentence.length, minimumThreshold);
		
		return this.weight;
	}
}
