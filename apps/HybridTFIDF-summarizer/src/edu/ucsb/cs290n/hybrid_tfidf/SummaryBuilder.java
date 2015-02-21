package edu.ucsb.cs290n.hybrid_tfidf;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import edu.ucsb.cs290n.tools.TextFileReader;


public class SummaryBuilder {
	
	public static final int MINIMUM_THRESHOLD = 11;
	private String filename;
	
	public SummaryBuilder(String filename) {
		this.filename = filename;
	}
	
	public String buildSummary(String filename) {
		SentencesToWordsDivider sentencesToWordsDivider;
		HashMap<String, Word> wordsHashTable;
		int numberOfWordsConsidered;
		String summarySentence;
		
		List<String> listOfPosts = new TextFileReader(filename).getLines();
		
		List<Sentence> listOfSentences = new PostsToSentencesDivider(listOfPosts).dividePostsIntoSentences();
		
		sentencesToWordsDivider = new SentencesToWordsDivider(listOfSentences);
		wordsHashTable = sentencesToWordsDivider.divideSentencesIntoUniqueWords();
		numberOfWordsConsidered = sentencesToWordsDivider.getTotalNumberOfWords();
		
		computeTermsWeight(wordsHashTable, listOfSentences.size(), numberOfWordsConsidered);
		summarySentence = computeSentencesWeight(listOfSentences, wordsHashTable);
		
		return summarySentence;
	}
	
	public void computeTermsWeight(HashMap<String, Word> wordsHashTable, int numberOfSentences, 
			int numberOfWordsConsidered) {
		
		Iterator<Entry<String, Word>> iterator = wordsHashTable.entrySet().iterator();
		
		while (iterator.hasNext()) {
			Entry<String, Word> pairs = (Map.Entry<String,Word>) iterator.next();
			Word word = pairs.getValue();			
			word.computeAndSetWeight(numberOfSentences, numberOfWordsConsidered);			
		}
	}

	public String computeSentencesWeight(List<Sentence> listOfSentences, HashMap<String, Word> wordsHashTable) {
		
		String summarySentence;
		String[] wordsInSentence;
		double maxSentenceWeight = 0;
		summarySentence = null;
		
		for (Sentence sentence : listOfSentences) {
			sentence.computeAndSetWeight(wordsHashTable, MINIMUM_THRESHOLD);
			
			if (sentence.getWeight() > maxSentenceWeight) {
				maxSentenceWeight = sentence.getWeight();
				summarySentence = sentence.getSentence();
			}
		}
		
		return summarySentence;
	}

}
