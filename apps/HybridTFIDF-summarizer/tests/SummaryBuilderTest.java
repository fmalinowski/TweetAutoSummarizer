import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.easymock.PowerMock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;


@RunWith(PowerMockRunner.class)
@PrepareForTest({TweetPostsRetriever.class, PostsToSentencesDivider.class, SentencesToWordsDivider.class, SummaryBuilder.class})
public class SummaryBuilderTest {

	@Test
	public void testComputeTermsWeight() {
		Word word1 = EasyMock.createMockBuilder(Word.class).addMockedMethod("computeAndSetWeight").withConstructor(String.class).withArgs("hello").createMock();
		Word word2 = EasyMock.createMockBuilder(Word.class).addMockedMethod("computeAndSetWeight").withConstructor(String.class).withArgs("I").createMock();
		Word word3 = EasyMock.createMockBuilder(Word.class).addMockedMethod("computeAndSetWeight").withConstructor(String.class).withArgs("am").createMock();
		Word word4 = EasyMock.createMockBuilder(Word.class).addMockedMethod("computeAndSetWeight").withConstructor(String.class).withArgs("french").createMock();
		
		EasyMock.expect(word1.computeAndSetWeight(32, 345)).andReturn(1.0).once();
		EasyMock.expect(word2.computeAndSetWeight(32, 345)).andReturn(1.0).once();
		EasyMock.expect(word3.computeAndSetWeight(32, 345)).andReturn(1.0).once();
		EasyMock.expect(word4.computeAndSetWeight(32, 345)).andReturn(1.0).once();
		
		EasyMock.replay(word1);
		EasyMock.replay(word2);
		EasyMock.replay(word3);
		EasyMock.replay(word4);
		
		HashMap<String,Word> wordsHashTable = new HashMap<String,Word>();
		wordsHashTable.put("hello", word1);
		wordsHashTable.put("I", word2);
		wordsHashTable.put("am", word3);
		wordsHashTable.put("french", word4);
		
		SummaryBuilder summaryBuilder = new SummaryBuilder("whatever");
		summaryBuilder.computeTermsWeight(wordsHashTable, 32, 345);
	}

	@Test
	public void testComputeSentencesWeight() {
		Sentence sentence1 = EasyMock.createMockBuilder(Sentence.class)
				.addMockedMethods("computeAndSetWeight", "getWeight")
				.withConstructor(String.class).withArgs("First Sentence").createMock();
		Sentence sentence2 = EasyMock.createMockBuilder(Sentence.class)
				.addMockedMethods("computeAndSetWeight", "getWeight")
				.withConstructor(String.class).withArgs("Second Sentence").createMock();
		Sentence sentence3 = EasyMock.createMockBuilder(Sentence.class)
				.addMockedMethods("computeAndSetWeight", "getWeight")
				.withConstructor(String.class).withArgs("Third Sentence").createMock();
		Sentence sentence4 = EasyMock.createMockBuilder(Sentence.class)
				.addMockedMethods("computeAndSetWeight", "getWeight")
				.withConstructor(String.class).withArgs("Fourth Sentence").createMock();
		
		HashMap<String, Word> wordsHashTable = new HashMap<String, Word>();
				
		EasyMock.expect(sentence1.computeAndSetWeight(wordsHashTable, SummaryBuilder.MINIMUM_THRESHOLD)).andReturn(1.0).once();
		EasyMock.expect(sentence2.computeAndSetWeight(wordsHashTable, SummaryBuilder.MINIMUM_THRESHOLD)).andReturn(1.0).once();
		EasyMock.expect(sentence3.computeAndSetWeight(wordsHashTable, SummaryBuilder.MINIMUM_THRESHOLD)).andReturn(1.0).once();
		EasyMock.expect(sentence4.computeAndSetWeight(wordsHashTable, SummaryBuilder.MINIMUM_THRESHOLD)).andReturn(1.0).once();
		
		EasyMock.expect(sentence1.getWeight()).andReturn(4.0).atLeastOnce();
		EasyMock.expect(sentence2.getWeight()).andReturn(3.0).atLeastOnce();
		EasyMock.expect(sentence3.getWeight()).andReturn(5.0).atLeastOnce();
		EasyMock.expect(sentence4.getWeight()).andReturn(1.0).atLeastOnce();
		
		EasyMock.replay(sentence1);
		EasyMock.replay(sentence2);
		EasyMock.replay(sentence3);
		EasyMock.replay(sentence4);
		
		List<Sentence> listOfSentences = new ArrayList<Sentence>();
		listOfSentences.add(sentence1);
		listOfSentences.add(sentence2);
		listOfSentences.add(sentence3);
		listOfSentences.add(sentence4);
		
		SummaryBuilder summaryBuilder = new SummaryBuilder("whatever");
		assertEquals("Third Sentence", summaryBuilder.computeSentencesWeight(listOfSentences, wordsHashTable));
	}
	
	// TEST TO BE FIXED
	
//	@Test
//	public void testBuildSummary() throws Exception {
//		String filename = "whatever";
//		
//		TweetPostsRetriever tweetPostsRetriever = EasyMock.createMockBuilder(TweetPostsRetriever.class)
//				.addMockedMethod("getAllPosts")
//				.withConstructor(String.class)
//				.withArgs(filename)
//				.createMock();
//		
//		tweetPostsRetriever = PowerMock.createMock(TweetPostsRetriever.class);
//		PowerMock.expectNew(TweetPostsRetriever.class, filename).andReturn(tweetPostsRetriever);
//		
//		List<String> listOfPosts = new ArrayList<String>();
//		EasyMock.expect(tweetPostsRetriever.getAllPosts()).andReturn(listOfPosts).once();
//		
//		
//		PostsToSentencesDivider postsToSentencesDivider = EasyMock.createMockBuilder(PostsToSentencesDivider.class)
//				.addMockedMethod("dividePostsIntoSentences")
//				.withConstructor(List.class)
//				.withArgs(listOfPosts)
//				.createMock();
//		
//		postsToSentencesDivider = PowerMock.createMock(PostsToSentencesDivider.class);
//		PowerMock.expectNew(PostsToSentencesDivider.class, listOfPosts).andReturn(postsToSentencesDivider);
//		
//		List<Sentence> listOfSentences = new ArrayList<Sentence>();
//		listOfSentences.add(new Sentence("hohoho"));
//		EasyMock.expect(postsToSentencesDivider.dividePostsIntoSentences()).andReturn(listOfSentences).once();
//		
//		
//		SentencesToWordsDivider sentencesToWordsDivider = EasyMock.createMockBuilder(SentencesToWordsDivider.class)
//				.addMockedMethods("divideSentencesIntoUniqueWords", "getTotalNumberOfWords")
//				.withConstructor(List.class)
//				.withArgs(listOfPosts)
//				.createMock();
//		
//		sentencesToWordsDivider = PowerMock.createMock(SentencesToWordsDivider.class);
//		PowerMock.expectNew(SentencesToWordsDivider.class, listOfSentences).andReturn(sentencesToWordsDivider);
//		
//		HashMap<String, Word> wordsHashTable = new HashMap<String, Word>();
//		EasyMock.expect(sentencesToWordsDivider.divideSentencesIntoUniqueWords()).andReturn(wordsHashTable).once();
//		
//		int numberOfWordsConsidered = 456;
//		EasyMock.expect(sentencesToWordsDivider.getTotalNumberOfWords()).andReturn(numberOfWordsConsidered).once();
//		
//		SummaryBuilder summaryBuilder = EasyMock.createMockBuilder(SummaryBuilder.class)
//				.addMockedMethods("computeTermsWeight", "computeSentencesWeight")
//				.withConstructor(String.class)
//				.withArgs(filename)
//				.createMock();
//		
//		summaryBuilder.computeTermsWeight(wordsHashTable, listOfSentences.size(), numberOfWordsConsidered);
//		EasyMock.expectLastCall().once();
//		
//		EasyMock.expect(summaryBuilder.computeSentencesWeight(listOfSentences, wordsHashTable)).andReturn("My flipping summary!!!").once();
//		
////		PowerMock.replay(TweetPostsRetriever.class, PostsToSentencesDivider.class, SentencesToWordsDivider.class);
////		PowerMock.replayAll();
////		EasyMock.replay(tweetPostsRetriever);
////		EasyMock.replay(postsToSentencesDivider);
////		EasyMock.replay(sentencesToWordsDivider);
////		EasyMock.replay(summaryBuilder);
//		
//		assertEquals("My flipping summary!!!", summaryBuilder.buildSummary(filename));
//	}
}
