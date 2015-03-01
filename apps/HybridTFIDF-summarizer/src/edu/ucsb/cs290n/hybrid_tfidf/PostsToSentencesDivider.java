package edu.ucsb.cs290n.hybrid_tfidf;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.ucsb.cs290n.preprocessing.Cleaner;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;


public class PostsToSentencesDivider {
	
	private List<String> listOfTweetPosts;
	
	public PostsToSentencesDivider(List<String> listOfTweetPosts) {
		this.listOfTweetPosts = listOfTweetPosts;
	}
	
	public List<Sentence> dividePostsIntoSentencesAndClean() {
		List<Sentence> listOfSentences = new ArrayList<Sentence>();
		
		InputStream enSentenceInputStream = null;
		String cleanedSentence;
		
		try {
			enSentenceInputStream = PostsToSentencesDivider.class.getClassLoader().getResourceAsStream("resources/en-sent.bin");
			SentenceModel model = new SentenceModel(enSentenceInputStream);
			
			SentenceDetectorME sentenceDetector = new SentenceDetectorME(model);
			
			for (String post: listOfTweetPosts) {
				
				for (String sentenceString : sentenceDetector.sentDetect(post)) {
					cleanedSentence = Cleaner.cleanMentions(sentenceString);
					cleanedSentence = Cleaner.cleanHashtags(cleanedSentence);
					listOfSentences.add(new Sentence(cleanedSentence, sentenceString));					
				}
			}
			
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			if (enSentenceInputStream != null) {
				try {
					enSentenceInputStream.close();
			    }
			    catch (IOException e) {
			    }
			}
		}
		
		return listOfSentences;
	}
	

}
