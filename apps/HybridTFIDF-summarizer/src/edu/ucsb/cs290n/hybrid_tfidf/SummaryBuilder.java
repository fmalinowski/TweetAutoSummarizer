package edu.ucsb.cs290n.hybrid_tfidf;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import edu.ucsb.cs290n.preprocessing.Cleaner;
import edu.ucsb.cs290n.tools.TextFileReader;

public class SummaryBuilder {
	
	public static final int MINIMUM_THRESHOLD = 10;
	private String filename;
	private String preprocessedFilename;
	
	public SummaryBuilder(String filename) {
		this.filename = filename;
		this.preprocessedFilename = new File(this.filename).getName() + ".preprocessed";
	}
	
	public String buildSummary() {
		SentencesToWordsDivider sentencesToWordsDivider;
		HashMap<String, Word> wordsHashTable;
		int numberOfWordsConsidered;
		Sentence summarySentence;
		
		System.out.println("Step 1/7 - Preprocess the tweets...");
		Cleaner.run(this.filename, this.preprocessedFilename);
		
		
		System.out.println("Step 2/7 - Retrieving the stop words...");
		StopWordChecker.getStopWords();
		
		System.out.println("Step 3/7 - Getting all the tweets...");
		List<String> listOfPosts = new TextFileReader(this.preprocessedFilename).getLines();
		
		System.out.println("Step 4/7 - Dividing tweets into sentences...");
		List<Sentence> listOfSentences = new PostsToSentencesDivider(listOfPosts).dividePostsIntoSentencesAndClean();
		
		System.out.println("Step 5/7 - Dividing sentences into words...");
		sentencesToWordsDivider = new SentencesToWordsDivider(listOfSentences);
		wordsHashTable = sentencesToWordsDivider.divideSentencesIntoUniqueWords();
		numberOfWordsConsidered = sentencesToWordsDivider.getTotalNumberOfWords();
		
		System.out.println("Step 6/7 - Computing term weights...");
		computeTermsWeight(wordsHashTable, listOfSentences.size(), numberOfWordsConsidered);
		System.out.println("Step 7/7 - Computing sentence weights...");
		summarySentence = computeSentencesWeight(listOfSentences, wordsHashTable);
		
		return summarySentence.getOriginalSentence();
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

	public Sentence computeSentencesWeight(List<Sentence> listOfSentences, HashMap<String, Word> wordsHashTable) {
		double maxSentenceWeight = 0;
		Sentence summarySentence = null;
		
		for (int i = 0; i < listOfSentences.size(); i++) {
			Sentence sentence = listOfSentences.get(i);
			sentence.computeAndSetWeight(wordsHashTable, MINIMUM_THRESHOLD);
			
			if (sentence.getWeight() > maxSentenceWeight) {
				maxSentenceWeight = sentence.getWeight();
				summarySentence = sentence;				
			}
		}
		
		return summarySentence;
	}
}
