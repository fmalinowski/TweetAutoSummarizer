package edu.ucsb.cs290n.evaluation;

import java.util.ArrayList;
import java.util.Arrays;

import edu.ucsb.cs290n.hybrid_tfidf.Sentence;
import edu.ucsb.cs290n.hybrid_tfidf.SentenceToWordsBreaker;
import edu.ucsb.cs290n.hybrid_tfidf.StopWordChecker;

public class RougeOne {

	ArrayList<String> manualSummaries;
	ArrayList<String> automatedSummaries;
	
	public RougeOne(ArrayList<String> manualSummaries, ArrayList<String> automatedSummaries) {
		this.manualSummaries = manualSummaries;
		this.automatedSummaries = automatedSummaries;
	}
	
	public double getRecall() {
		ArrayList<String> manualOneGrams;
		ArrayList<String> automatedOneGrams;
		String manualSummary, automatedSummary;
		int onegramMatches = 0;
		int manualOneGramCount = 0;
		
		for (int i = 0; i < this.manualSummaries.size(); i++) {
			manualSummary = this.manualSummaries.get(i);
			automatedSummary = this.automatedSummaries.get(i);
			manualOneGrams = getOneGrams(manualSummary);
			automatedOneGrams = getOneGrams(automatedSummary);
			
			manualOneGramCount += manualOneGrams.size();
			
			for (String manualOneGram : manualOneGrams) {
				for (String automatedOneGram : automatedOneGrams) {
					if (manualOneGram.equalsIgnoreCase(automatedOneGram)) {
						onegramMatches++;
						// If the manual Summary has twice the same word but the automated summary
						// has just one occurrence of the word, we don't want to have two matches,
						// we want just one match! That's why we remove the automatedOneGram
						automatedOneGrams.remove(automatedOneGram);
						break;
					}
				}
			}
		}
		
		return ((double)onegramMatches)/manualOneGramCount;
	}
	
	public double getPrecision() {
		ArrayList<String> manualOneGrams;
		ArrayList<String> automatedOneGrams;
		String manualSummary, automatedSummary;
		int onegramMatches = 0;
		int automatedOneGramCount = 0;
		
		for (int i = 0; i < this.manualSummaries.size(); i++) {
			manualSummary = this.manualSummaries.get(i);
			automatedSummary = this.automatedSummaries.get(i);
			manualOneGrams = getOneGrams(manualSummary);
			automatedOneGrams = getOneGrams(automatedSummary);
			
			automatedOneGramCount += automatedOneGrams.size();
			
			for (String automatedOneGram : automatedOneGrams) {
				for (String manualOneGram : manualOneGrams) {
					if (automatedOneGram.equalsIgnoreCase(manualOneGram)) {
						onegramMatches++;
						// If the automated Summary has twice the same word but the manual summary
						// has just one occurrence of the word, we don't want to have two matches,
						// we want just one match! That's why we remove the manualOneGram
						manualOneGrams.remove(manualOneGram);
						break;
					}
				}
			}
		}
		
		return ((double)onegramMatches)/automatedOneGramCount;
	}
	
	public double getFmeasure() {
		double precision = getPrecision();
		double recall = getRecall();
		return (2 * (precision * recall) / (precision + recall));
	}
	
	public static ArrayList<String> getOneGrams(String sentence) {
		String oneGram;
		String[] oneGrams = new SentenceToWordsBreaker(new Sentence(sentence)).breakIntoWords();
		ArrayList<String> oneGramsList = new ArrayList<String>(Arrays.asList(oneGrams));
		
		for (int i = 0; i < oneGramsList.size();) {
			oneGram = oneGramsList.get(i);
			if (StopWordChecker.isPunctuation(oneGram)) {
				oneGramsList.remove(i);
			}
			else {
				i++;
			}
		}
		return oneGramsList;
	}
	
}
