package summarizer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Summarizer {
	static String[] exampleSentence = new String[]{
		"Aw, Comedian Soupy Sales Died.",
		"RIP Comedian Soupy Sales dies at age 83.",
		"My favorite comedian Soupy Sales died.",
		"RT @NY: RIP Comedian Soupy Sales dies at age 83.",
		"RIP: Soupy Sales Died Today.",
		"Soupy Sales meant silliness and laughs."
	};
	
	static{
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File("C:\\backfile\\cs290n\\tweets\\#KidLegislation-4.txt")));
			List<String> e = new LinkedList<String>();
			String line = br.readLine();
			while(line != null){
				e.add(line);
				line = br.readLine();
			}
			exampleSentence = e.toArray(new String[e.size()]);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(new Summarizer().summarize(exampleSentence,"#KidLegislation"));
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
				n = n.updateLeftNode(lword.get(i));
			} 
			
			// right part
			String r = parts[1];
			List<String> rword = t.tokenize(r);
			n = rootNode;
			for(int i = 0;i < rword.size();i++){
				n = n.updateRightNode(rword.get(i));
			}
		}
		rootNode.assignScore();
		System.out.println(rootNode.left);
		System.out.println(rootNode.right);
		List<Node> summList = rootNode.findTheLargestPathWithBeginPrun(2);
		StringBuilder sb = new StringBuilder();
		for(int i = 0;i < summList.size();i++){
			Map<String,Integer> m = summList.get(i).forms;
			Iterator<Map.Entry<String, Integer>> mi = m.entrySet().iterator();
			String mostCommon = null;
			int count = 0;
			while(mi.hasNext()){
				Map.Entry<String, Integer> me = mi.next();
				if(me.getValue() > count){
					count = me.getValue();
					mostCommon = me.getKey();
				}
			}
			
			sb.append(" ");
			sb.append(mostCommon);
		}
		
		return sb.toString();
	}
	
	
}
