package edu.ucsb.cs290n.hybrid_tfidf;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;


public class SentencesToWordsDividerTest {

	@Test
	public void testDivideSentencesIntoUniqueWords() {
		List<Sentence> listOfSentences = new ArrayList<Sentence>();
		
		Sentence sentence1 = new Sentence("a word and the word");
		Sentence sentence2 = new Sentence("the word present too");
		Sentence sentence3 = new Sentence("too many the word too");
		;
		listOfSentences.add(sentence1);
		listOfSentences.add(sentence2);
		listOfSentences.add(sentence3);
		
		SentencesToWordsDivider sentencesToWordsDivider = new SentencesToWordsDivider(listOfSentences);
		HashMap<String, Word> wordsHashTable = sentencesToWordsDivider.divideSentencesIntoUniqueWords();
		
		assertEquals(7, wordsHashTable.size());
		
		assertEquals("a", wordsHashTable.get("a").getWord());
		assertEquals(1, wordsHashTable.get("a").getNumberOfOccurences());
		assertEquals(1, wordsHashTable.get("a").getNumberOfSentencesContainingWord());
		
		assertEquals("word", wordsHashTable.get("word").getWord());
		assertEquals(4, wordsHashTable.get("word").getNumberOfOccurences());
		assertEquals(3, wordsHashTable.get("word").getNumberOfSentencesContainingWord());
		
		assertEquals("and", wordsHashTable.get("and").getWord());
		assertEquals(1, wordsHashTable.get("and").getNumberOfOccurences());
		assertEquals(1, wordsHashTable.get("and").getNumberOfSentencesContainingWord());
		
		assertEquals("the", wordsHashTable.get("the").getWord());
		assertEquals(3, wordsHashTable.get("the").getNumberOfOccurences());
		assertEquals(3, wordsHashTable.get("the").getNumberOfSentencesContainingWord());
		
		assertEquals("present", wordsHashTable.get("present").getWord());
		assertEquals(1, wordsHashTable.get("present").getNumberOfOccurences());
		assertEquals(1, wordsHashTable.get("present").getNumberOfSentencesContainingWord());
		
		assertEquals("too", wordsHashTable.get("too").getWord());
		assertEquals(3, wordsHashTable.get("too").getNumberOfOccurences());
		assertEquals(2, wordsHashTable.get("too").getNumberOfSentencesContainingWord());
		
		assertEquals("many", wordsHashTable.get("many").getWord());
		assertEquals(1, wordsHashTable.get("many").getNumberOfOccurences());
		assertEquals(1, wordsHashTable.get("many").getNumberOfSentencesContainingWord());
		
		assertEquals(5, sentence1.getNumberOfWords());
		assertEquals(4, sentence2.getNumberOfWords());
		assertEquals(5, sentence3.getNumberOfWords());
	}
	
	@Test
	public void testGetTotalNumberOfWords() {
		List<Sentence> listOfSentences = new ArrayList<Sentence>();
		
		listOfSentences.add(new Sentence("a word and the word"));
		listOfSentences.add(new Sentence("the word present too"));
		listOfSentences.add(new Sentence("too many the word too"));
		
		SentencesToWordsDivider sentencesToWordsDivider = new SentencesToWordsDivider(listOfSentences);
		assertEquals(0, sentencesToWordsDivider.getTotalNumberOfWords());
		
		HashMap<String, Word> wordsHashTable = sentencesToWordsDivider.divideSentencesIntoUniqueWords();
		assertEquals(14, sentencesToWordsDivider.getTotalNumberOfWords());
	}
	
	@Test
	public void testGetWordsHashTable() {
		List<Sentence> listOfSentences = new ArrayList<Sentence>();
		
		listOfSentences.add(new Sentence("a word and the word"));
		listOfSentences.add(new Sentence("the word present too"));
		listOfSentences.add(new Sentence("too many the word too"));
		
		SentencesToWordsDivider sentencesToWordsDivider = new SentencesToWordsDivider(listOfSentences);
		assertEquals(null, sentencesToWordsDivider.getWordsHashTable());
		
		HashMap<String, Word> wordsHashTable = sentencesToWordsDivider.divideSentencesIntoUniqueWords();
		assertEquals(wordsHashTable, sentencesToWordsDivider.getWordsHashTable());
	}

}
