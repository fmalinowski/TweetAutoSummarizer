package summarizer;

import java.util.LinkedList;
import java.util.List;

public class Tokenizer {
	public List<String> tokenize(String sentence){
		String[] ws = sentence.split("\\W+");
		List<String> r = new LinkedList<String>();
		for(int i = 0;i < ws.length;i++){
			if(ws[i].length() > 0){
				r.add(ws[i]);
			}
		}
		return r;
	}
}
