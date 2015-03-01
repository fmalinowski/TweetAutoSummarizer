package edu.ucsb.cs290n.preprocessing;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

public class RetweetCleaner {
	
	public static void cleanRTmark(File input,File output){
		PrintWriter pWriter = null;
		BufferedReader bReader = null;
		String line;
		
		try {
			bReader = new BufferedReader(new FileReader(input));
			pWriter = new PrintWriter(output);
			
			while ((line = bReader.readLine()) != null) {
				line = line.replaceAll("^(:){0,1}RT @\\w+:( ){0,1}", "");
				pWriter.append(line);
				pWriter.append('\n');
			}
			bReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(pWriter != null){
				pWriter.close();
			}
		
		}
	}
}