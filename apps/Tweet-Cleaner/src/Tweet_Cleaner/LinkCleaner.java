package Tweet_Cleaner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;


public class LinkCleaner {
	private static final String LINK_REGEX = "http://t.co/\\w*";
	public static void cleanLink(File in,File out){
		try {
			BufferedReader bReader = new BufferedReader(new FileReader(in));
			PrintWriter pWriter = new PrintWriter(out);
			String line = bReader.readLine();
			while(line != null){
				line.replaceAll(LINK_REGEX, "");
				pWriter.write(line);
				pWriter.write("\n");
				line = bReader.readLine();
			}
			pWriter.close();
			bReader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
