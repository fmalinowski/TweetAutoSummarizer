package edu.ucsb.cs290n.hybrid_tfidf;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;


public class PostsToSentencesDividerTest {

	@Test
	public void testDividePostsIntoSentencesAndClean() {
		
		List<String> listOfPosts = new ArrayList<String>();
		listOfPosts.add("My great sentence 1. Then, it's followed by the sentence2...");
		listOfPosts.add("I could talk about sth else?! Yes, why not :) but let's see what happens!!");
		listOfPosts.add("At last this is my final sentence!");
		
		PostsToSentencesDivider sentenceDivider = new PostsToSentencesDivider(listOfPosts);
		
		List<Sentence> resultSentencesList = sentenceDivider.dividePostsIntoSentencesAndClean();
		
		assertEquals(4, resultSentencesList.size());
		assertEquals("My great sentence 1.", resultSentencesList.get(0).getSentence());
		assertEquals("Then, it's followed by the sentence2...", resultSentencesList.get(1).getSentence());
		assertEquals("I could talk about sth else?! Yes, why not :) but let's see what happens!!", 
				resultSentencesList.get(2).getSentence());
		assertEquals("At last this is my final sentence!", resultSentencesList.get(3).getSentence());
	}
}
