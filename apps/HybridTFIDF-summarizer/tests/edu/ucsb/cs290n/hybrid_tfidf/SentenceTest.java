package edu.ucsb.cs290n.hybrid_tfidf;
import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Test;


public class SentenceTest {

	@Test
	public void testComputeAndSetWeight() {
		Sentence sentence = new Sentence("hello I am french");
		Word word1 = new Word("hello");
		Word word2 = new Word("I");
		Word word3 = new Word("am");
		Word word4 = new Word("french");
		
		word1.setWeight(3);
		word2.setWeight(5);
		word3.setWeight(7);
		word4.setWeight(11);
		
		HashMap<String,Word> wordsHashTable = new HashMap<String, Word>();
		wordsHashTable.put("hello", word1);
		wordsHashTable.put("I", word2);
		wordsHashTable.put("am", word3);
		wordsHashTable.put("french", word4);
		
		assertEquals(2.363636, sentence.computeAndSetWeight(wordsHashTable, 11), 0.00001);
		assertEquals(6.5, sentence.computeAndSetWeight(wordsHashTable, 2), 0.00001);
	}
	
	@Test
	public void testBreakIntoWords() {
		Sentence sentence = new Sentence("hello, I am french.");
		
		assertEquals(0, sentence.getNumberOfWords());
		String[] expectedWords = {"hello", ",", "I", "am", "french", "."};
		assertEquals(expectedWords, sentence.breakIntoWords());
		assertEquals(6, sentence.getNumberOfWords());
	}
	
	@Test
	public void testGetWords() {
		Sentence sentence = new Sentence("hello, I am french.");
		
		assertEquals(0, sentence.getNumberOfWords());
		String[] expectedWords = {"hello", ",", "I", "am", "french", "."};
		assertEquals(expectedWords, sentence.breakIntoWords());
		assertEquals(6, sentence.getNumberOfWords());
	}

}
