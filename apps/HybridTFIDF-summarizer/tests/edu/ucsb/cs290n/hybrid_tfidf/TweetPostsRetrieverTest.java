package edu.ucsb.cs290n.hybrid_tfidf;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;


public class TweetPostsRetrieverTest {

	@Test
	public void testGetAllPosts() {
		TweetPostsRetriever tweetPostsRetriever = new TweetPostsRetriever("tests/resources/test_resource.txt");
		
		List<String> expectedTweetPosts = new ArrayList<String>();
		expectedTweetPosts.add("line1");
		expectedTweetPosts.add("line2");
		expectedTweetPosts.add("line3");
		assertEquals(expectedTweetPosts, tweetPostsRetriever.getAllPosts());
	}

}
