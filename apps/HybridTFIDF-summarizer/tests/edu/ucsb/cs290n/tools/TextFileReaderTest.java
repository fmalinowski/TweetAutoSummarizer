package edu.ucsb.cs290n.tools;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class TextFileReaderTest {

	@Test
	public void testGetLines() {
		TextFileReader fileLinesRetriever = new TextFileReader("tests/resources/test_resource.txt");
		
		List<String> expectedLines = new ArrayList<String>();
		expectedLines.add("line1");
		expectedLines.add("line2");
		expectedLines.add("line3");
		assertEquals(expectedLines, fileLinesRetriever.getLines());
	}

}
