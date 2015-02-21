package edu.ucsb.cs290n.hybrid_tfidf;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class TweetPostsRetriever {
	
	private String filename; 

	public TweetPostsRetriever(String filename) {
		this.filename = filename;
	}
	
	public List<String> getAllPosts() {
		List<String> postsArray = new ArrayList<String>();
		String line;
		
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(this.filename));
			
			while ((line = bufferedReader.readLine()) != null) {
				postsArray.add(line);
			}
			
			bufferedReader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("Error while reading line from the Tweet posts file");
			e.printStackTrace();
		}
		
		return postsArray;
	}
	
}
