package Tweet_Cleaner;

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
		try {
			bReader = new BufferedReader(new FileReader(input));
			pWriter = new PrintWriter(output);
			String line = bReader.readLine();
			while (line != null) {
				String t = Helper.getTweetText(line);
				StringBuilder sBuilder = new StringBuilder();
				String tt = Helper.getTweetText(line);
				if(tt == null) {
					tt = line;
				}
				for(char c : tt.toCharArray()){
					if(c >= 32 && c <= 127){
						sBuilder.append(c);
					}
				}
				pWriter.append(Helper.getTweenInfo(line) + sBuilder.toString() + "\n");
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
			if (pWriter != null) {
				pWriter.close();
			}

		}

	}
}
