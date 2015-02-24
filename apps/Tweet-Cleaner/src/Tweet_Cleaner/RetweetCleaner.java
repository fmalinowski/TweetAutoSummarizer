package Tweet_Cleaner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

public class RetweetCleaner {
	public static void cleanRetweet(File input,File output){
		Set<String> allText = new HashSet<String>();
		PrintWriter pWriter = null;
		BufferedReader bReader = null;
		try {
			bReader = new BufferedReader(new FileReader(input));
			pWriter = new PrintWriter(output);
			String line = bReader.readLine();
			while(line != null){
				String t = Helper.getTweetText(line);
				if(t == null || !allText.contains(t)){
					pWriter.append(line);
					pWriter.append('\n');
					if(t != null){
						allText.add(t);
					}
				}
				line = bReader.readLine();
			}
			bReader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(pWriter != null){
				pWriter.close();
			}
		
		}
		
	}
	
	public static void cleanRTmark(File input,File output){
		PrintWriter pWriter = null;
		BufferedReader bReader = null;
		try {
			bReader = new BufferedReader(new FileReader(input));
			pWriter = new PrintWriter(output);
			String line = bReader.readLine();
			while(line != null){
				String t = Helper.getTweetText(line);
				if(t != null){
					t = t.replaceAll("^(:){0,1}RT @\\w+:( ){0,1}", "");
					pWriter.append(Helper.getTweenInfo(line));
					pWriter.append(t);
					pWriter.append('\n');
				}
				line = bReader.readLine();
			}
			bReader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(pWriter != null){
				pWriter.close();
			}
		
		}
		
	}
}
