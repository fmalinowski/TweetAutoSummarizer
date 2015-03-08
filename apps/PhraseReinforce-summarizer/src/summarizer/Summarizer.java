package summarizer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
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
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
	static String[] ps = new String[]{
			"C:\\backfile\\cs290n\\CS290NTweet\\apps\\Twitter-aggregator\\tweet-samples\\Huntington Beach.txt",
			"C:\\backfile\\cs290n\\CS290NTweet\\apps\\Twitter-aggregator\\tweet-samples\\Kings.txt",
			"C:\\backfile\\cs290n\\CS290NTweet\\apps\\Twitter-aggregator\\tweet-samples\\60 Minutes.txt",
			"C:\\backfile\\cs290n\\CS290NTweet\\apps\\Twitter-aggregator\\tweet-samples\\#20DaysOfDallas.txt",
			"C:\\backfile\\cs290n\\CS290NTweet\\apps\\Twitter-aggregator\\tweet-samples\\LAPD.txt",
			"C:\\backfile\\cs290n\\CS290NTweet\\apps\\Twitter-aggregator\\tweet-samples\\Brian Williams.txt",
			"C:\\backfile\\cs290n\\CS290NTweet\\apps\\Twitter-aggregator\\tweet-samples\\#DylansNewVideo.txt",
			"C:\\backfile\\cs290n\\CS290NTweet\\apps\\Twitter-aggregator\\tweet-samples\\Disneyland.txt"
	};
	static String[] ts = new String[]{
			"Huntington Beach",
			"Kings",
			"60 Minutes",
			"camerondallas",
			"LAPD",
			"Brian Williams",
			"DylansNewVideo",
			"Disneyland"
	};
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		if(args.length > 1){
			if(args[0].equals("-help")){
				System.out.println("input topic size [--stem] [--mention-tag] [--cleanSym] [--no-clean] [base]");
				return;
			}
			Summarizer e = new Summarizer();
			String p = args[0];
			String t = args[1];
			int s = Integer.parseInt(args[2]);
			boolean clean = true;
			for(int i = 3;i < args.length;i++){
				String a = args[i];
				if(a.equals("--stem")){
					e.stem = true;
					System.out.println("do steming");
				} else if(a.equals("--mention-tag")){
					e.mention_tag = false;
					System.out.println("clean mentions, tags");
				} else if(a.equals("--cleanSym")){
					Tokenizer.cleanSym = true;
					System.out.println("clean symbols");
				} else if(a.equals("--no-clean")){
					clean = false;
					System.out.println("no clean");
				} else {
					Node.b = Double.parseDouble(args[i]);
				}
			}
			if(clean){
				System.out.println(e.summarize(Cleaner.getCleanedTweets(p).toArray(new String[s]), t));
			} else {
				System.out.println(e.summarize(Cleaner.getUncleanedTweets(p).toArray(new String[s]), t));
			}
			return;
		}
		Tokenizer.cleanSym = false;
		Summarizer e = new Summarizer();
		e.mention_tag = true;
		e.stem = false;
		e.prune = false;
		System.out.println(e.summarize(exampleSentence, "Soupy Sales"));
		e.printTree();
		
		PrintWriter pw;
		Summarizer s = new Summarizer();
		String root = "C:\\backfile\\cs290n\\CS290NTweet\\Rouge-results\\phrase-reinforcement\\";
		s.prune = false;
		s.mention_tag = true;
		s.stem = false;
		pw = new PrintWriter(new File(root + "pr(ascii,link,rtmark).txt"));
		for(int i = 0;i < ts.length;i++){
			pw.append(s.summarize(
					Cleaner.getCleanedTweets(ps[i]).toArray(new String[1500]),ts[i]).trim());
			pw.append('\n');
		}
		pw.close();
		
		s.mention_tag = false;
		s.stem = false;
		pw = new PrintWriter(new File(root + "pr(ascii,link,rtmark-then-mentions,hashtags).txt"));
		for(int i = 0;i < ts.length;i++){
			pw.append(s.summarize(
					Cleaner.getCleanedTweets(ps[i]).toArray(new String[1500]),ts[i]).trim());
			pw.append('\n');
		}
		pw.close();
		
		s.mention_tag = true;
		s.stem = false;
		pw = new PrintWriter(new File(root + "pr(no-cleaning).txt"));
		for(int i = 0;i < ts.length;i++){
			pw.append(s.summarize(
					Cleaner.getUncleanedTweets(ps[i]).toArray(new String[1500]),ts[i]).trim());
			pw.append('\n');
		}
		pw.close();
		
		s.mention_tag = true;
		s.stem = true;
		pw = new PrintWriter(new File(root + "pr(no-cleaning-then-stemming).txt"));
		for(int i = 0;i < ts.length;i++){
			pw.append(s.summarize(
					Cleaner.getUncleanedTweets(ps[i]).toArray(new String[1500]),ts[i]).trim());
			pw.append('\n');
		}
		pw.close();
		
		s.mention_tag = true;
		s.stem = true;
		pw = new PrintWriter(new File(root + "pr(ascii,link,rtmark-then-stemming).txt"));
		for(int i = 0;i < ts.length;i++){
			pw.append(s.summarize(
					Cleaner.getCleanedTweets(ps[i]).toArray(new String[1500]),ts[i]).trim());
			pw.append('\n');
		}
		pw.close();
		
		Node.b = 100;
		s.mention_tag = true;
		s.stem = false;
		pw = new PrintWriter(new File(root + "pr(ascii,link,rtmark-base100).txt"));
		for(int i = 0;i < ts.length;i++){
			pw.append(s.summarize(
					Cleaner.getCleanedTweets(ps[i]).toArray(new String[1500]),ts[i]).trim());
			pw.append('\n');
		}
		pw.close();
		
		Node.b = Math.sqrt(10);
		s.mention_tag = true;
		s.stem = false;
		pw = new PrintWriter(new File(root + "pr(ascii,link,rtmark-base3.71).txt"));
		for(int i = 0;i < ts.length;i++){
			pw.append(s.summarize(
					Cleaner.getCleanedTweets(ps[i]).toArray(new String[1500]),ts[i]).trim());
			pw.append('\n');
		}
		pw.close();
		
		Node.b = 100;
		s.mention_tag = true;
		s.stem = false;
		pw = new PrintWriter(new File(root + "pr(ascii,link,rtmark-then-stemming-base100).txt"));
		for(int i = 0;i < ts.length;i++){
			pw.append(s.summarize(
					Cleaner.getCleanedTweets(ps[i]).toArray(new String[1500]),ts[i]).trim());
			pw.append('\n');
		}
		pw.close();
		
		Node.b = Math.sqrt(10);
		s.mention_tag = true;
		s.stem = false;
		pw = new PrintWriter(new File(root + "pr(ascii,link,rtmark-then-stemming-base3.71).txt"));
		for(int i = 0;i < ts.length;i++){
			pw.append(s.summarize(
					Cleaner.getCleanedTweets(ps[i]).toArray(new String[1500]),ts[i]).trim());
			pw.append('\n');
		}
		pw.close();
		
		Node.b = 100;
		s.mention_tag = false;
		s.stem = false;
		pw = new PrintWriter(new File(root + "pr(ascii,link,rtmark-base100-then-mentions,hashtags).txt"));
		for(int i = 0;i < ts.length;i++){
			pw.append(s.summarize(
					Cleaner.getCleanedTweets(ps[i]).toArray(new String[1500]),ts[i]).trim());
			pw.append('\n');
		}
		pw.close();
		
		Node.b = Math.sqrt(10);
		s.mention_tag = false;
		s.stem = false;
		pw = new PrintWriter(new File(root + "pr(ascii,link,rtmark-base3.71-then-mentions,hashtags).txt"));
		for(int i = 0;i < ts.length;i++){
			pw.append(s.summarize(
					Cleaner.getCleanedTweets(ps[i]).toArray(new String[1500]),ts[i]).trim());
			pw.append('\n');
		}
		pw.close();
		
	}
	public boolean stem = false;
	public boolean mention_tag = true;
	public boolean prune = false;
	private Node root;
	public String summarize(String[] sentences,String topic){
		Node rootNode = new Node(topic);
		root = rootNode;
		Tokenizer t = new Tokenizer();
		
		int two = 0;
		for(String s : sentences){
			if(s == null || s.length() == 0){
				two++;
				continue;
			}
			String[] parts = s.split("(?i)" + topic);
			if(parts.length != 2){
				two++;
				continue;
			}
			
			// left part
			String l;
			if(mention_tag){
				l = parts[0];
			} else {
				l = parts[0].replaceAll("(@|#)\\w*", "");
			}
			List<String> lword;
			
			if(stem){
				lword= t.tokenizeStem(l);
			} else {
				lword= t.tokenize(l);
			}
			Node n = rootNode;
			for(int i = lword.size() - 1;i >= 0;i--){
				n = n.updateLeftNode(lword.get(i));
			} 
			
			// right part
			String r;
			if(mention_tag){
				r = parts[1];
			} else {
				r = parts[1].replaceAll("(@|#)\\w*", "");
			}
			List<String> rword;
			
			if(stem){
				rword= t.tokenizeStem(r);
			} else {
				rword= t.tokenize(r);
			}
			n = rootNode;
			for(int i = 0;i < rword.size();i++){
				n = n.updateRightNode(rword.get(i));
			}
			
		}
		if(prune){
			rootNode.pruneLeft();
			rootNode.pruneRight();
		}
		rootNode.assignScore();
		List<Node> summList = rootNode.findTheLargestPath();
		StringBuilder sb = new StringBuilder();
		boolean hasQuote1 = false;
		boolean hasQuote2 = false;
		String last = null;
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
			if(mostCommon.equals(".") || mostCommon.equals(",") || mostCommon.equals(":") || mostCommon.equals(")") || mostCommon.equals(";")){
				sb.append(mostCommon);
			} else if(last != null && (last.equals("'") && hasQuote1 || last.equals("\"") && hasQuote2 || last.equals("@") || last.equals("#"))){
				sb.append(mostCommon);
			} else if(mostCommon.equals("'")){
				if(hasQuote1){
					hasQuote1 = false;
				} else {
					sb.append(' ');
					hasQuote1 = true;
				}
				sb.append(mostCommon);
			} else if(mostCommon.equals("\"")){
				if(hasQuote2){
					hasQuote2 = false;
				} else {
					sb.append(' ');
					hasQuote2 = true;
				}
				sb.append(mostCommon);
			} else {
				sb.append(" ");
				sb.append(mostCommon);
			}
			last = mostCommon;
		}
		return sb.toString();
	}
	
	public void printTree(){
		root.print();
	}
}
