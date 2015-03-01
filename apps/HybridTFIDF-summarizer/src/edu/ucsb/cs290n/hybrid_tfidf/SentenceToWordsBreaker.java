package edu.ucsb.cs290n.hybrid_tfidf;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import cmu.arktweetnlp.Twokenize;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;


public class SentenceToWordsBreaker {
	
	private Sentence sentence;
	
	public SentenceToWordsBreaker(Sentence sentence) {
		this.sentence = sentence;
	}
	
//	This methods really needs to be improved by using some stemmers like Porter stemmer and also
//	remove stop words like the, a but also the punctuation
//	it should also consider (or remove completely) the whole URL addresses.
//	Look at the test to see the problems
	public String[] breakIntoWords() {
		String words[] = null;
			
		List<String> wordsList = Twokenize.tokenizeRawTweetText(this.sentence.getSentence());
		return wordsList.toArray(new String[wordsList.size()]);
	}

}
