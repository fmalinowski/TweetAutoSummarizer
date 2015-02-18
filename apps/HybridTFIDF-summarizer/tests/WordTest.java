import static org.junit.Assert.*;

import org.junit.Test;


public class WordTest {

	@Test(expected=IllegalArgumentException.class)
	public void testComputeAndSetWeightExceptionWhenNumberOfWordsIs0() {
		Word word = new Word("random");
		word.computeAndSetWeight(1, 0);		
	}

	@Test(expected=IllegalArgumentException.class)
	public void testComputeAndSetWeightExceptionWhenNumberOfSentencesContainingWordIs0() {
		Word word = new Word("random");
		word.computeAndSetWeight(1, 1);		
	}
	
	@Test
	public void testComputeAndSetWeight() {
		Word word = new Word("random");
		double expectedWeight;
		
		word.setNumberOfOccurences(5);
		word.setNumberOfSentencesContainingWord(3);
		expectedWeight = 0.512109;
		assertEquals(expectedWeight, word.computeAndSetWeight(19, 26), 0.00001);
		
		word.setNumberOfOccurences(56);
		word.setNumberOfSentencesContainingWord(29);
		expectedWeight = 0.03367;
		assertEquals(expectedWeight, word.computeAndSetWeight(7401, 13298), 0.00001);
	}
}
