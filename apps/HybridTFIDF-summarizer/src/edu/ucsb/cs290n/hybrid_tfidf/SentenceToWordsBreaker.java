package edu.ucsb.cs290n.hybrid_tfidf;
import java.io.IOException;
import java.io.InputStream;

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
		InputStream enTokenInputStream = PostsToSentencesDivider.class.getClassLoader().getResourceAsStream("resources/en-token.bin");
		
		try {
			TokenizerModel tokenizerModel = new TokenizerModel(enTokenInputStream);
			Tokenizer tokenizer = new TokenizerME(tokenizerModel);
			
			words = tokenizer.tokenize(this.sentence.getSentence());
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			if (enTokenInputStream != null) {
				try {
					enTokenInputStream.close();
				}
				catch (IOException e) {
				}
			}
		}
		
		return words;
	}

}
