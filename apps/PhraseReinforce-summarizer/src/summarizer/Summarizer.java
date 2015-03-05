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

import Tweet_Cleaner.Cleaner;

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
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		System.out.println(new Summarizer().summarize(
				Cleaner.getCleanedTweets("C:\\backfile\\cs290n\\CS290NTweet\\apps\\Twitter-aggregator\\tweet-samples\\Huntington Beach.txt").toArray(new String[1500]),"Huntington Beach"));
		System.out.println();
		System.out.println(new Summarizer().summarize(
				Cleaner.getCleanedTweets("C:\\backfile\\cs290n\\CS290NTweet\\apps\\Twitter-aggregator\\tweet-samples\\Kings.txt").toArray(new String[1500]),"Kings"));
		System.out.println();
		System.out.println(new Summarizer().summarize(
				Cleaner.getCleanedTweets("C:\\backfile\\cs290n\\CS290NTweet\\apps\\Twitter-aggregator\\tweet-samples\\60 Minutes.txt").toArray(new String[1500]),"60 Minutes"));
		System.out.println();
		System.out.println(new Summarizer().summarize(
				Cleaner.getCleanedTweets("C:\\backfile\\cs290n\\CS290NTweet\\apps\\Twitter-aggregator\\tweet-samples\\#20DaysOfDallas.txt").toArray(new String[1500]),"@camerondallas"));
		System.out.println();
		System.out.println(new Summarizer().summarize(
				Cleaner.getCleanedTweets("C:\\backfile\\cs290n\\CS290NTweet\\apps\\Twitter-aggregator\\tweet-samples\\LAPD.txt").toArray(new String[1500]),"police"));
		System.out.println();
		System.out.println(new Summarizer().summarize(
				Cleaner.getCleanedTweets("C:\\backfile\\cs290n\\CS290NTweet\\apps\\Twitter-aggregator\\tweet-samples\\Brian Williams.txt").toArray(new String[1500]),"Brian Williams"));
		System.out.println();
		System.out.println(new Summarizer().summarize(
				Cleaner.getCleanedTweets("C:\\backfile\\cs290n\\CS290NTweet\\apps\\Twitter-aggregator\\tweet-samples\\#DylansNewVideo.txt").toArray(new String[1500]),"#DylansNewVideo"));
		System.out.println();
		System.out.println(new Summarizer().summarize(
				Cleaner.getCleanedTweets("C:\\backfile\\cs290n\\CS290NTweet\\apps\\Twitter-aggregator\\tweet-samples\\Disneyland.txt").toArray(new String[1500]),"Disneyland"));
	}
	
	public String summarize(String[] sentences,String topic){
		Node rootNode = new Node(topic);
		Tokenizer t = new Tokenizer();
		
		for(String s : sentences){
			if(s == null || s.length() == 0){
				continue;
			}
			String[] parts = s.split("(?i)" + topic);
			if(parts.length != 2){
				continue;
			}
			
			// left part
			String l = parts[0].replaceAll("(@|#)\\w*", "");
			List<String> lword = t.tokenizeStem(l);
			Node n = rootNode;
			for(int i = lword.size() - 1;i >= 0;i--){
				n = n.updateLeftNode(lword.get(i));
			} 
			
			// right part
			String r = parts[1].replaceAll("(@|#)\\w*", "");
			List<String> rword = t.tokenizeStem(r);
			n = rootNode;
			for(int i = 0;i < rword.size();i++){
				n = n.updateRightNode(rword.get(i));
			}
		}
		rootNode.assignScore();
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
