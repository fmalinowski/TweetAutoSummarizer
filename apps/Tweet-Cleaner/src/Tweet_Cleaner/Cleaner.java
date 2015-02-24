package Tweet_Cleaner;

import java.io.File;

public class Cleaner {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//File inputFile = new File(args[0]);
		//File outputFile = new File(args[1]);
		
		
		UnASCIICleaner.cleanNotASCII(new File("C:\\backfile\\cs290n\\tweets\\#KidLegislation.txt"), new File("C:\\backfile\\cs290n\\tweets\\#KidLegislation-1.txt"));
		LinkCleaner.cleanLink(new File("C:\\backfile\\cs290n\\tweets\\#KidLegislation-1.txt"), new File("C:\\backfile\\cs290n\\tweets\\#KidLegislation-2.txt"));
		RetweetCleaner.cleanRTmark(new File("C:\\backfile\\cs290n\\tweets\\#KidLegislation-2.txt"), new File("C:\\backfile\\cs290n\\tweets\\#KidLegislation-3.txt"));
		ExtractCleaner.ExtractClean(new File("C:\\backfile\\cs290n\\tweets\\#KidLegislation-3.txt"), new File("C:\\backfile\\cs290n\\tweets\\#KidLegislation-4.txt"));
	}

}
