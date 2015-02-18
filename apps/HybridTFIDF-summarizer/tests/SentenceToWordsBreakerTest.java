import static org.junit.Assert.*;

import org.junit.Test;


public class SentenceToWordsBreakerTest {

	@SuppressWarnings("deprecation")
	@Test
	public void testBreakIntoWords() {		
		Sentence sentence1 = new Sentence("I'm writing this word :) .");
		Sentence sentence2 = new Sentence("Let's check this out: http://www.google.com/~a");
		Sentence sentence3 = new Sentence("What the heck   is that?!!");
		Sentence sentence4 = new Sentence("One word and several words...");
		
		String[] expected1 = {"I", "'m", "writing", "this", "word", ":", ")", "."};
		String[] expected2 = {"Let", "'s", "check", "this", "out", ":", "http", "://www.google", ".com/~a"};
		String[] expected3 = {"What", "the", "heck", "is", "that", "?", "!", "!"};
		String[] expected4 = {"One", "word", "and", "several", "words", "..."};
		
		String[] words1 = new SentenceToWordsBreaker(sentence1).breakIntoWords();
		String[] words2 = new SentenceToWordsBreaker(sentence2).breakIntoWords();
		String[] words3 = new SentenceToWordsBreaker(sentence3).breakIntoWords();
		String[] words4 = new SentenceToWordsBreaker(sentence4).breakIntoWords();
		
		assertEquals(expected1, words1);
		assertEquals(expected2, words2);
		assertEquals(expected3, words3);
		assertEquals(expected4, words4);
	}

}
