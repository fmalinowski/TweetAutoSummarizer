import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import edu.ucsb.cs290n.hybrid_tfidf.PostsToSentencesDivider;
import edu.ucsb.cs290n.hybrid_tfidf.Sentence;
import edu.ucsb.cs290n.hybrid_tfidf.SentencesToWordsDivider;
import edu.ucsb.cs290n.hybrid_tfidf.StopWordChecker;
import edu.ucsb.cs290n.hybrid_tfidf.SummaryBuilder;
import edu.ucsb.cs290n.hybrid_tfidf.Word;
import edu.ucsb.cs290n.tools.TextFileReader;


public class TFIDF {
	private HashMap<String,Word> wordsHashTable;
	private Map<Integer, Map<String, Integer>> wordsSentences = new HashMap<Integer, Map<String, Integer>>();
	private double[][] distance;
	TFIDF(String pathname){
		SummaryBuilder sb = new SummaryBuilder(pathname);
		
		System.out.println("Step 2/7 - Retrieving the stop words...");
		StopWordChecker.getStopWords();
		
		System.out.println("Step 3/7 - Getting all the tweets...");
		List<String> listOfPosts = new TextFileReader(pathname).getLines();
		
		System.out.println("Step 4/7 - Dividing tweets into sentences...");
		List<Sentence> listOfSentences = new PostsToSentencesDivider(listOfPosts).dividePostsIntoSentencesAndClean();
		for(int si = 0;si < listOfSentences.size();si++){
			String[] s = listOfSentences.get(si).getWords();
			Map<String, Integer> m1 = new HashMap<String,Integer>();
			for(String w : s){
				int c = 0;
				if(m1.containsKey(w)){
					c = m1.get(w);
				}
				m1.put(w, c + 1);
			}
			wordsSentences.put(si, m1);
		}
		
		
		
		System.out.println("Step 5/7 - Dividing sentences into words...");
		SentencesToWordsDivider sentencesToWordsDivider = new SentencesToWordsDivider(listOfSentences);
		wordsHashTable = sentencesToWordsDivider.divideSentencesIntoUniqueWords();
		int numberOfWordsConsidered = sentencesToWordsDivider.getTotalNumberOfWords();
		
		System.out.println("Step 6/7 - Computing term weights...");
		sb.computeTermsWeight(wordsHashTable, listOfSentences.size(), numberOfWordsConsidered);
		
		int b = listOfPosts.size();
		distance = new double[b][b];
		for(int i = 0;i < b;i++){
			for(int j = i + 1;j < b;j++){
				if(i == j){
					distance[i][j] = 0;
				}
				Map<String, Integer> m1 = wordsSentences.get(i);
				Map<String, Integer> m2 = wordsSentences.get(j);
				
				int maxC1 = 0;
				int maxC2 = 0;
				for(int c : m1.values()){
					maxC1 = Math.max(maxC1, c);
				}
				for(int c : m2.values()){
					maxC2 = Math.max(maxC2, c);
				}
				
				Set<String> common = new HashSet<String>();
				for(String s : m1.keySet()){
					if(m2.containsKey(s)){
						common.add(s);
					}
				}
				double[] tfidf1 = new double[common.size()];Arrays.fill(tfidf1,0);
				double[] tfidf2 = new double[common.size()];Arrays.fill(tfidf2,0);
				int wi = 0;
				double tf = 0,idf = 0;
				for(String w : common){
					tf = 0.5 + 0.5*m1.get(w) / maxC1;
					idf= Math.log(((double)b)/(1.0 + wordsHashTable.get(w).getNumberOfSentencesContainingWord()));
					tfidf1[wi]  = tf*idf;
					tf = 0.5 + 0.5*m2.get(w) / maxC2;
					tfidf2[wi]  = tf*idf;
					wi++;
				}
				double l21 = 0, l22 = 0;
				for(String w : m1.keySet()){
					tf = 0.5 + 0.5*m1.get(w) / maxC1;
					idf= Math.log(((double)b)/(1.0 + wordsHashTable.get(w).getNumberOfSentencesContainingWord()));
					l21 += tf*idf*tf*idf;
				}
				for(String w : m2.keySet()){
					tf = 0.5 + 0.5*m2.get(w) / maxC2;
					idf= Math.log(((double)b)/(1.0 + wordsHashTable.get(w).getNumberOfSentencesContainingWord()));
					l22 += tf*idf*tf*idf;
				}
				distance[i][j] = distance[j][i] = 1 - ((wi == 0) ? -1 : dotproduct(tfidf1, tfidf2)/(Math.sqrt(l21)*Math.sqrt(l22)));
			}
		}
	}
	private double dotproduct(double[] b1,double[] b2){
		double d = 0;
		for(int i = 0;i < b1.length;i++){
			d += b1[i]*b2[i];
		}
		return d;
	}
	private double normCal(double[] b){
		double d = dotproduct(b, b);
		return Math.sqrt(d);		
	}
	public double TFIDFDistance(int i,int j){
		
		return distance[i][j];
	}
}
