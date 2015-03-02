package Tweet_Cleaner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;


public class LinkCleaner {
	private static final String LINK_REGEX = "(\\()*http(s)*://t\\.co/\\w*(\\))*(( )*бн)*|http://(t\\.)*\\w*( )*бн|http(:)*( )*бн|http://( )*бн";
	private static final String LINK_REGEX2 = "http://(t)*";
	private static final String LINK_REGEX3 = "http(:)*|//";
	public static void cleanLink(File in,File out){
		try {
			BufferedReader bReader = new BufferedReader(new FileReader(in));
			PrintWriter pWriter = new PrintWriter(out);
			String line = bReader.readLine();
			while(line != null){
				line = line.replaceAll(LINK_REGEX, "").replaceAll(LINK_REGEX2, "").replaceAll(LINK_REGEX3, "");
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
