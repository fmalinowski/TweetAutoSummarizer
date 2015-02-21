package edu.ucsb.cs290n.evaluation;

import java.io.File;
import java.util.ArrayList;

import edu.ucsb.cs290n.tools.TextFileReader;

public class Main {

	public static void main(String[] args) {
		if(args.length < 2) {
			System.err.println("The path to the referenced summaries and the automated summaries are needed");
			System.exit(1);
		}
		
		String manualSummariesFileLoc = args[0];
		String automatedSummariesFileLoc = args[1];
		
		File manualSummariesFile = new File(manualSummariesFileLoc);
		File automatedSummariesFile = new File(automatedSummariesFileLoc);
		
		if (manualSummariesFile.exists() && automatedSummariesFile.exists()) {
			double recall, precision, fMeasure;
			RougeOne rougeOne;
			
			System.out.println("Retrieving the referenced summaries...");
			ArrayList<String> manualSummaries = (ArrayList<String>) new TextFileReader(manualSummariesFileLoc).getLines();
			System.out.println("DONE!");
			
			System.out.println("Retrieving the automated summaries...");
			ArrayList<String> automatedSummaries = (ArrayList<String>) new TextFileReader(automatedSummariesFileLoc).getLines();
			System.out.println("DONE!");
			
			System.out.println("Computing the ROUGE-1 measures...");
			rougeOne = new RougeOne(manualSummaries, automatedSummaries);
			recall = rougeOne.getRecall();
			precision = rougeOne.getPrecision();
			fMeasure = rougeOne.getFmeasure();
			
			System.out.println("");
			System.out.println("Referenced Summaries: " + manualSummariesFileLoc);
			System.out.println("Automated Summaries: " + automatedSummariesFileLoc);
			System.out.println("");
			System.out.println("ROUGE-1 recall: " + recall);
			System.out.println("ROUGE-1 precision: " + precision);
			System.out.println("ROUGE-1 F-Measure: " + fMeasure);
		}
		else {
			System.err.println("One of the files containing the summaries doesn't exist!");
			System.exit(1);
		}
	}

}
