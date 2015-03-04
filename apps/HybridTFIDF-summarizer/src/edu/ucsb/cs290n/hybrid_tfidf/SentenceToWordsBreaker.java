package edu.ucsb.cs290n.hybrid_tfidf;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.tartarus.snowball.ext.EnglishStemmer;

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
		String word;
		String words[] = null;
		EnglishStemmer stemmer;
			
		List<String> wordsList = Twokenize.tokenizeRawTweetText(this.sentence.getSentence());
		
		// Stem the words
		for (int i = 0; i < wordsList.size(); i++) {
			word = wordsList.get(i);
			stemmer = new EnglishStemmer();
			stemmer.setCurrent(word);
			stemmer.stem();
			wordsList.set(i, stemmer.getCurrent());
		}
		
		return wordsList.toArray(new String[wordsList.size()]);
	}

}
