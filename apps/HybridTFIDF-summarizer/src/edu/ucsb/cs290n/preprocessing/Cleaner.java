package edu.ucsb.cs290n.preprocessing;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

public class Cleaner {
	
	public static void run(String input, String output) {
		PrintWriter pWriter = null;
		BufferedReader bReader = null;
		String line;
		
		try {
			bReader = new BufferedReader(new FileReader(input));
			pWriter = new PrintWriter(output);
			
			while ((line = bReader.readLine()) != null) {
				
				line = cleanNotASCII(line);
				line = cleanLink(line);
				line = cleanRTmark(line);
				
				pWriter.append(line + "\n");
			}
			bReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (pWriter != null) {
				pWriter.close();
			}

		}
	}
	
	public static String cleanNotASCII(String line) {
		StringBuilder sBuilder = new StringBuilder();
		
		for(char c : line.toCharArray()){
			if(c >= 32 && c <= 127){
				sBuilder.append(c);
			}
		}
		
		return sBuilder.toString();
	}
	
	public static String cleanLink(String line) {
		String LINK_REGEX = "http://t\\.co/\\w*";
		
		return line.replaceAll(LINK_REGEX, "");
	}
	
	public static String cleanRTmark(String line) {
		return line.replaceAll("^(:){0,1}RT @\\w+:( ){0,1}", "");
	}
	
	public static String cleanMentions(String line) {
		return line.replaceAll("@\\w+", "");
	}
	
	public static String cleanHashtags(String line) {
		return line.replaceAll("#\\w+", "");
	}
	
	public static String removeHashtagOrMentionDuplicates(String line) {
		HashMap<String,Boolean> hashtableWords = new HashMap<String,Boolean>();
		String word;
		String[] words = line.split(" ");
		
		line = "";
		for (int i = 0; i < words.length; i++) {
			word = words[i];
			if (word.startsWith("@") || word.startsWith("#")) {
				if (!hashtableWords.containsKey(word)){
					hashtableWords.put(word, true);
					line += word + " ";
				}
			}
			else {
				line += word + " ";
			}
		}
		
		return line;
	}
}