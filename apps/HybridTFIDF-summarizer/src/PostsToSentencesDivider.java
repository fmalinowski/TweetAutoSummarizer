import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;


public class PostsToSentencesDivider {
	
	private List<String> listOfTweetPosts;
	
	public PostsToSentencesDivider(List<String> listOfTweetPosts) {
		this.listOfTweetPosts = listOfTweetPosts;
	}
	
	public List<Sentence> dividePostsIntoSentences() {
		List<Sentence> listOfSentences = new ArrayList<Sentence>();
		
		InputStream enSentenceInputStream = null;
		
		try {
			enSentenceInputStream = PostsToSentencesDivider.class.getClassLoader().getResourceAsStream("resources/en-sent.bin");
			SentenceModel model = new SentenceModel(enSentenceInputStream);
			
			SentenceDetectorME sentenceDetector = new SentenceDetectorME(model);
			
			for (String post: listOfTweetPosts) {
				
				for (String sentenceString : sentenceDetector.sentDetect(post)) {
					listOfSentences.add(new Sentence(sentenceString));					
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
