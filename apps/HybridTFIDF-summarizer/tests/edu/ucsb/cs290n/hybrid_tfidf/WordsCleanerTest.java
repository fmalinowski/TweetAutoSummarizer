package edu.ucsb.cs290n.hybrid_tfidf;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class WordsCleanerTest {
	
	private String[] wordsInput = {
			"I", "want", "to", "eat", ",", "but", "no", "!", "!"
	};
	
	private WordsCleaner wordsCleaner;
	
	@Before
	public void setUp() {
		wordsCleaner = new WordsCleaner(wordsInput);
	}

	@Test
	public void testRemovePunctuation() {
		String[] expectedResult = {"i", "want", "to", "eat", "but", "no"};
		String[] result = wordsCleaner.removePunctuation();
		assertEquals(expectedResult, result);
	}

	@Test
	public void testGetWordNumberWithoutPunctuation() {
		assertEquals(0, wordsCleaner.getWordNumberWithoutPunctuation());
		wordsCleaner.removePunctuation();
		assertEquals(6, wordsCleaner.getWordNumberWithoutPunctuation());
	}

}
