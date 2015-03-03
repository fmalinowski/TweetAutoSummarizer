package summarizer;

import java.util.LinkedList;
import java.util.List;

import cmu.arktweetnlp.Twokenize;

public class Tokenizer {
	public List<String> tokenize(String sentence){
		return Twokenize.tokenizeRawTweetText(sentence);
	}
}
