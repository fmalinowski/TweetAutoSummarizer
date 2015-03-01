package edu.ucsb.cs290n.tools;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TextFileReader {
	private String filename; 

	public TextFileReader(String filename) {
		this.filename = filename;
	}
	
	public String getLineByIndex(int requestedIndex) {
		String line, requestedLine;
		int currentIndex = 0;
		
		requestedLine = "";
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(this.filename));
			
			while ((line = bufferedReader.readLine()) != null) {
				if (currentIndex == requestedIndex) {
					requestedLine = line;
					break;
				}
				
				currentIndex++;
			}
			
			bufferedReader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("Error while reading line from the Tweet posts file");
			e.printStackTrace();
		}
		
		return requestedLine;
	}
	
	public List<String> getLines() {
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
