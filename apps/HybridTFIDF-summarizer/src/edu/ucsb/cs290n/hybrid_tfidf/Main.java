package edu.ucsb.cs290n.hybrid_tfidf;
import java.io.File;
import java.util.HashMap;
import java.util.List;




public class Main {

	// Only the tweet texts should be present in the file (no metadata) and one tweet text
	// per line in the text file.
	public static void main(String[] args) {
		String fileloc = args[0];
		String summarySentence;

		File file = new File(fileloc);
		if (file.exists()) {
			SummaryBuilder summaryBuilder = new SummaryBuilder(fileloc);
			System.out.println("Generating the summary");
			summarySentence = summaryBuilder.buildSummary();
			System.out.println("");
			System.out.println("Here is the generated summary:");
			System.out.println(summarySentence);
			System.out.println("");
		}
		else {
			System.out.println("The file doesn't exist!");
		}
	}
}
