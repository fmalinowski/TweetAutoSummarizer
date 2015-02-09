import java.util.HashMap;
import java.util.List;


public class SummaryBuilder {
	
	private String filename;
	
	public SummaryBuilder(String filename) {
		this.filename = filename;
	}
	
	public String buildSummary(String filename) {
		SentencesToWordsDivider sentencesToWordsDivider;
		HashMap<String, Word> wordsHashTable;
		int numberOfWordsConsidered;
		int minimumThreshold = 11;
		String summarySentence;
		
		List<String> listOfPosts = new TweetPostsRetriever(filename).getAllPosts();
		
		List<Sentence> listOfSentences = new PostsToSentencesDivider(listOfPosts).dividePostsIntoSentences();
		
		sentencesToWordsDivider = new SentencesToWordsDivider(listOfSentences);
		wordsHashTable = sentencesToWordsDivider.divideSentencesIntoUniqueWords();
		numberOfWordsConsidered = sentencesToWordsDivider.getTotalNumberOfWords();
		
		computeTermsWeight(wordsHashTable, listOfSentences, numberOfWordsConsidered);
		summarySentence = computeSentencesWeight(listOfSentences, wordsHashTable, minimumThreshold);
		
		return summarySentence;
	}
	
	public void computeTermsWeight(HashMap<String, Word> wordsHashTable, List<Sentence> listOfSentences, 
			int numberOfWordsConsidered) {
		//TODO
	}

	public String computeSentencesWeight(List<Sentence> listOfSentences, HashMap<String, Word> wordsHashTable, 
			int minimumThreshold) {
		
		//TODO
		return null;
	}

}
