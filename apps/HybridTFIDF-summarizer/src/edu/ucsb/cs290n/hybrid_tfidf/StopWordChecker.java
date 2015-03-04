package edu.ucsb.cs290n.hybrid_tfidf;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class StopWordChecker {
	
	private static HashSet<String> stopWords;
	
	public static void getStopWords() {
		stopWords = new HashSet<String>();
		String line;
		
		try {
			
			InputStream stopWordsInputStream = PostsToSentencesDivider.class.getClassLoader().getResourceAsStream("resources/stopwords.txt");
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stopWordsInputStream, "UTF-8"));
			
			while ((line = bufferedReader.readLine()) != null) {
				stopWords.add(line);
			}
			
			bufferedReader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("Error while reading line from the Tweet posts file");
			e.printStackTrace();
		}
	}
	
	public static Boolean isStopWord(String word) {
		return stopWords.contains(word);
	}
	
	public static Boolean isPunctuation(String word) {
		String[] punctuation = {
				",", ".", "?", "!", ":", ";", "\"", "/", "\\", "|", "'", "(", ")", "{", "}", 
				"[", "]", "`"
		};
		
		return Arrays.asList(punctuation).contains(word);
	}

}
