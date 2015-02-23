package summarizer;

import java.util.List;

public class Summarizer {
	static final String[] exampleSentence = new String[]{
		"Aw, Comedian Soupy Sales died.",
		"RIP Comedian Soupy Sales dies at age 83.",
		"My favorite comedian Soupy Sales died.",
		"RT @NY: RIP Comedian Soupy Sales dies at age 83.",
		"RIP: Soupy Sales Died Today.",
		"Soupy Sales meant silliness and laughs."
	};
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Summarizer().summarize(exampleSentence,"Soupy Sales");
	}
	
	public String summarize(String[] sentences,String topic){
		Node rootNode = new Node(topic);
		Tokenizer t = new Tokenizer();
		
		for(String s : sentences){
			String[] parts = s.split(topic);
			if(parts.length != 2){
				continue;
			}
			
			// left part
			String l = parts[0];
			List<String> lword = t.tokenize(l);
			Node n = rootNode;
			for(int i = lword.size() - 1;i >= 0;i--){
				n = n.updateLeftNode(lword.get(i).toLowerCase());
			} 
			
			// right part
			String r = parts[1];
			List<String> rword = t.tokenize(r);
			n = rootNode;
			for(int i = 0;i < rword.size();i++){
				n = n.updateRightNode(rword.get(i).toLowerCase());
			}
		}
		rootNode.assignScore();
		rootNode.print();
		System.out.println(rootNode.findTheLargestPath());
		return null;
	}
	
	
}
