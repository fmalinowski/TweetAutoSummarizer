package summarizer;

import java.util.List;

import org.tartarus.snowball.ext.EnglishStemmer;

import cmu.arktweetnlp.Twokenize;

public class Tokenizer {
	public static boolean cleanSym = false;
	public List<String> tokenize(String sentence){
		return cleanSymbols(Twokenize.tokenizeRawTweetText(sentence));
	}
	
	public List<String> tokenizeStem(String sentence){
		List<String> l = tokenize(sentence);
		for(int i = 0;i < l.size();i++){
			EnglishStemmer es = new EnglishStemmer();
			es.setCurrent(l.get(i));
			es.stem();
			l.set(i, es.getCurrent());
		}
		return cleanSymbols(l);
	}
	private List<String> cleanSymbols(List<String> words){
		if(cleanSym == false){
			return words;
		}
		for(int i = 0;i < words.size();i++){
			if(words.get(i).matches("\\W+")){
				words.remove(i);
				i--;
			}
				
		}
		return words;
	}
}
