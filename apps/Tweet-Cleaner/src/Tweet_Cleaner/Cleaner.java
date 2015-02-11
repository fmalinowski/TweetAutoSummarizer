package Tweet_Cleaner;

import java.io.File;

public class Cleaner {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File inputFile = new File(args[0]);
		File outputFile = new File(args[1]);
		UnASCIICleaner.cleanNotASCII(inputFile, outputFile);
	}

}
