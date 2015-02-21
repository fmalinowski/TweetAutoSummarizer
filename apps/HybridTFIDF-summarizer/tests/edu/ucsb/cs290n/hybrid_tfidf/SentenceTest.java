package edu.ucsb.cs290n.hybrid_tfidf;
import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.BeforeClass;
import org.junit.Test;


public class SentenceTest {

	@Test
	public void testComputeAndSetWeightWithStopWords() {
		StopWordChecker.getStopWords();
		
		Sentence sentence = new Sentence("Hello I am french");
		Word word1 = new Word("hello");
		Word word2 = new Word("i");
		Word word3 = new Word("am");
		Word word4 = new Word("french");
		
		word1.setWeight(3);
		word2.setWeight(5);
		word3.setWeight(7);
		word4.setWeight(11);
		
		HashMap<String,Word> wordsHashTable = new HashMap<String, Word>();
		wordsHashTable.put("hello", word1);
		wordsHashTable.put("i", word2);
		wordsHashTable.put("am", word3);
		wordsHashTable.put("french", word4);
		
		assertEquals(1.272727, sentence.computeAndSetWeight(wordsHashTable, 11), 0.00001);
		assertEquals(3.5, sentence.computeAndSetWeight(wordsHashTable, 2), 0.00001);
	}
	
	@Test
	public void testComputeAndSetWeightWithoutStopWords() {
		StopWordChecker.getStopWords();
		
		Sentence sentence = new Sentence("hello hasd asd french");
		Word word1 = new Word("hello");
		Word word2 = new Word("hasd");
		Word word3 = new Word("asd");
		Word word4 = new Word("french");
		
		word1.setWeight(3);
		word2.setWeight(5);
		word3.setWeight(7);
		word4.setWeight(11);
		
		HashMap<String,Word> wordsHashTable = new HashMap<String, Word>();
		wordsHashTable.put("hello", word1);
		wordsHashTable.put("hasd", word2);
		wordsHashTable.put("asd", word3);
		wordsHashTable.put("french", word4);
		
		assertEquals(2.363636, sentence.computeAndSetWeight(wordsHashTable, 11), 0.00001);
		assertEquals(6.5, sentence.computeAndSetWeight(wordsHashTable, 2), 0.00001);
	}
	
	@Test
	public void testBreakIntoWords() {
		Sentence sentence = new Sentence("hello, I am frEnCh.");
		
		assertEquals(0, sentence.getNumberOfWords());
		String[] expectedWords = {"hello", "i", "am", "french"};
		assertEquals(expectedWords, sentence.breakIntoWords());
		assertEquals(4, sentence.getNumberOfWords());
	}
	
	@Test
	public void testGetWords() {
		Sentence sentence = new Sentence("hello, I am frEnCh.");
		
		assertEquals(0, sentence.getNumberOfWords());
		String[] expectedWords = {"hello", "i", "am", "french"};
		assertEquals(expectedWords, sentence.breakIntoWords());
		assertEquals(4, sentence.getNumberOfWords());
	}

}
