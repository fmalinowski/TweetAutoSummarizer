package Tweet_Cleaner;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.List;

public class Cleaner {

	public static List<String> getCleanedTweets(String p) throws IOException{
		String output = p.replaceAll(".txt$", "_cleaned.txt");
		UnASCIICleaner.cleanNotASCII(new File(p), new File(p + ".tmp.1"));
		LinkCleaner.cleanLink(new File(p + ".tmp.1"), new File(p + ".tmp.2"));
		RetweetCleaner.cleanRTmark(new File(p + ".tmp.2"), new File(p + ".tmp.3"));
		ExtractCleaner.ExtractClean(new File(p + ".tmp.3"), new File(output));
		Files.delete(new File(p + ".tmp.1").toPath());
		Files.delete(new File(p + ".tmp.2").toPath());
		Files.delete(new File(p + ".tmp.3").toPath());
		return Files.readAllLines(new File(output).toPath(), Charset.defaultCharset());

	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//File inputFile = new File(args[0]);
		//File outputFile = new File(args[1]);
		
		
		UnASCIICleaner.cleanNotASCII(new File("C:\\backfile\\cs290n\\CS290NTweet\\apps\\Twitter-aggregator\\tweet-samples\\60 minutes.txt"), new File("C:\\backfile\\cs290n\\tweets\\#KidLegislation-1.txt"));
		LinkCleaner.cleanLink(new File("C:\\backfile\\cs290n\\tweets\\#KidLegislation-1.txt"), new File("C:\\backfile\\cs290n\\tweets\\#KidLegislation-2.txt"));
		RetweetCleaner.cleanRTmark(new File("C:\\backfile\\cs290n\\tweets\\#KidLegislation-2.txt"), new File("C:\\backfile\\cs290n\\tweets\\#KidLegislation-3.txt"));
		ExtractCleaner.ExtractClean(new File("C:\\backfile\\cs290n\\tweets\\#KidLegislation-3.txt"), new File("C:\\backfile\\cs290n\\tweets\\#KidLegislation-4.txt"));
	}

}
