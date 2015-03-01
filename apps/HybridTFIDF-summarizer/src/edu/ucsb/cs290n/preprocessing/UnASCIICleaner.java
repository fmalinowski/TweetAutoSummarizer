package edu.ucsb.cs290n.preprocessing;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class UnASCIICleaner {
	public static void cleanNotASCII(File input, File output) {
		PrintWriter pWriter = null;
		BufferedReader bReader = null;
		String line;
		
		try {
			bReader = new BufferedReader(new FileReader(input));
			pWriter = new PrintWriter(output);
			
			while ((line = bReader.readLine()) != null) {
				StringBuilder sBuilder = new StringBuilder();
				
				for(char c : line.toCharArray()){
					if(c >= 32 && c <= 127){
						sBuilder.append(c);
					}
				}
				pWriter.append(sBuilder.toString() + "\n");
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
}