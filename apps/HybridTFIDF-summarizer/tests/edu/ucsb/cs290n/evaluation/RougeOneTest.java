package edu.ucsb.cs290n.evaluation;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class RougeOneTest {
	
	private ArrayList<String> manualSummaries1;
	private ArrayList<String> automatedSummaries1;
	
	private ArrayList<String> manualSummaries2;
	private ArrayList<String> automatedSummaries2;
	
	private ArrayList<String> manualSummaries3;
	private ArrayList<String> automatedSummaries3;
	
	RougeOne rougeOne1;
	RougeOne rougeOne2;
	RougeOne rougeOne3;
	
	@Before
	public void setUp() {
		this.manualSummaries1 = new ArrayList<String>();
		this.automatedSummaries1 = new ArrayList<String>();
		
		this.manualSummaries1.add("The summary is the best one.");
		this.automatedSummaries1.add("summary the greatest.");
		
		this.manualSummaries1.add("I want to sleep a lot today huh");
		this.automatedSummaries1.add("sleep he wants to do today as much as possible like i said");
		
		this.rougeOne1 = new RougeOne(this.manualSummaries1, this.automatedSummaries1);
		
		this.manualSummaries2 = new ArrayList<String>();
		this.automatedSummaries2 = new ArrayList<String>();
		
		this.manualSummaries2.add("The best summary");
		this.automatedSummaries2.add("The best summary");
		
		this.manualSummaries2.add("No fault here");
		this.automatedSummaries2.add("No fault here");
		
		this.rougeOne2 = new RougeOne(this.manualSummaries2, this.automatedSummaries2);
		
		this.manualSummaries3 = new ArrayList<String>();
		this.automatedSummaries3 = new ArrayList<String>();
		
		this.manualSummaries3.add("The best summary");
		this.automatedSummaries3.add("The best summary");
		
		this.manualSummaries3.add("Half wrong");
		this.automatedSummaries3.add("Completely false huh");
		
		this.rougeOne3 = new RougeOne(this.manualSummaries3, this.automatedSummaries3);
	}

	@Test
	public void testGetRecall() {
		assertEquals(0.4286, rougeOne1.getRecall(), 0.0001);
		assertEquals(1.0, rougeOne2.getRecall(), 0.0001);
		assertEquals(0.6, rougeOne3.getRecall(), 0.0001);
	}

	@Test
	public void testGetPrecision() {
		assertEquals(0.375, rougeOne1.getPrecision(), 0.0001);
		assertEquals(1.0, rougeOne2.getPrecision(), 0.0001);
		assertEquals(0.5, rougeOne3.getPrecision(), 0.0001);
	}

	@Test
	public void testGetFmeasure() {
		assertEquals(0.4000, rougeOne1.getFmeasure(), 0.0001);
		assertEquals(1.0, rougeOne2.getFmeasure(), 0.0001);
		assertEquals(0.5455, rougeOne3.getFmeasure(), 0.0001);
	}
	
	@Test
	public void testGetOneGrams() {
		String sentence1 = "I'm somewhere else, but you're not!";
		String sentence2 = "Imagine a sentence with 2,, that's so weird huh?";
		
		String[] expected1 = {"I'm", "somewhere", "else", "but", "you're", "not"};
		String[] expected2 = {"Imagine", "a", "sentence", "with", "2", ",,", "that's", "so", "weird", "huh"};
		
		assertEquals(expected1, RougeOne.getOneGrams(sentence1).toArray());
		assertEquals(expected2, RougeOne.getOneGrams(sentence2).toArray());
	}

}
