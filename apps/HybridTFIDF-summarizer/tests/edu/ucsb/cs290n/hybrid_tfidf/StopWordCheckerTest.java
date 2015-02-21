package edu.ucsb.cs290n.hybrid_tfidf;

import static org.junit.Assert.*;

import org.junit.Test;

public class StopWordCheckerTest {

	@Test
	public void testGetStopWordsAndIsStopWord() {
		StopWordChecker.getStopWords();
		assertTrue(StopWordChecker.isStopWord("i"));
		assertTrue(StopWordChecker.isStopWord("you"));
		assertTrue(StopWordChecker.isStopWord("was"));
		assertFalse(StopWordChecker.isStopWord("frank"));
		assertFalse(StopWordChecker.isStopWord("leaf"));
	}
	
	@Test
	public void testIsPunctuation() {
		assertTrue(StopWordChecker.isPunctuation(","));
		assertTrue(StopWordChecker.isPunctuation("."));
		assertTrue(StopWordChecker.isPunctuation("?"));
		assertTrue(StopWordChecker.isPunctuation("!"));
		assertTrue(StopWordChecker.isPunctuation(":"));
		assertTrue(StopWordChecker.isPunctuation(";"));
		assertTrue(StopWordChecker.isPunctuation("\""));
		assertTrue(StopWordChecker.isPunctuation("'"));
		assertTrue(StopWordChecker.isPunctuation("("));
		assertTrue(StopWordChecker.isPunctuation(")"));
		assertTrue(StopWordChecker.isPunctuation("{"));
		assertTrue(StopWordChecker.isPunctuation("}"));
		assertTrue(StopWordChecker.isPunctuation("["));
		assertTrue(StopWordChecker.isPunctuation("]"));
		assertTrue(StopWordChecker.isPunctuation("`"));
		assertFalse(StopWordChecker.isPunctuation("&"));
		assertFalse(StopWordChecker.isPunctuation("hello"));
	}

}
