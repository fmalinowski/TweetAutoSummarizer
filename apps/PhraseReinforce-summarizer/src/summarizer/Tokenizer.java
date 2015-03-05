package summarizer;

import java.util.LinkedList;
import java.util.List;

import org.tartarus.snowball.ext.EnglishStemmer;

import cmu.arktweetnlp.Twokenize;

public class Tokenizer {
	public List<String> tokenize(String sentence){
		return Twokenize.tokenizeRawTweetText(sentence);
	}
	
	public List<String> tokenizeStem(String sentence){
		List<String> l = tokenize(sentence);
		for(int i = 0;i < l.size();i++){
			EnglishStemmer es = new EnglishStemmer();
			es.setCurrent(l.get(i));
			es.stem();
			l.set(i, es.getCurrent());
		}
		return l;
	}
}
