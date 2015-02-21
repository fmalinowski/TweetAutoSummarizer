package summarizer;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Node {
	String word;
	int count;
	double score;
	private List<Node> left = new LinkedList<Node>();
	private List<Node> right = new LinkedList<Node>();
	
	Node(String word){
		this.word = word;
		count = 1;
		score = 0;
	}
	
	public Node getLeftNodeByWord(String s){
		return getNodeByWord(left,s);
	}
	
	public Node getRightNodeByWord(String s){
		return getNodeByWord(right,s);
	}
	
	public Node updateLeftNode(String w){
		Node node = getLeftNodeByWord(w);
		if(node != null){
			node.count++;
		} else {
			node = new Node(w);
			left.add(node);
		}
		return node;
	}
	
	public Node updateRightNode(String w){
		Node node = getRightNodeByWord(w);
		if(node != null){
			node.count++;
		} else {
			node = new Node(w);
			right.add(node);
		}
		return node;
	}
	private Node getNodeByWord(List<Node> l,String s){
		int i = l.size() - 1;
		while(i >= 0 && !l.get(i).word.equals(s)) i--;
		return i == -1 ? null : l.get(i);
		
	}
	@Override
	public String toString(){
		return word + "(" + count + ", " + score + ")";
	}
	public void print(){
		print(true,0);
		print(false,0);
	}
	
	public void print(Boolean isLeft,int t){
		if(t != 0){
			System.out.print("\n");
		}
		for(int i = 0;i < t;i++){
			System.out.print("\t");
		}
		System.out.print(toString());
		List<Node> l = isLeft ? left : right;
		boolean nl = true;
		for(Node n : l){
			nl = false;
			n.print(isLeft,t + 1);
		}
		if(nl){
			System.out.print("\n");
		}
	}
	
	public void assignScore(){
		assignScore(true,true,0);
		assignScore(true,false,0);
	}
	private static final double b = 10;
	private static final String[] STOP_WORDS = new String[]{
		"a","an","and","are","as","at","be","by","for","from","has","he","in","is","its","of","on","that","the","to","was","were","will","with"
	};
	private static final Set<String> STOP_WORDS_SET = new HashSet<String>(Arrays.asList(STOP_WORDS));

	public void assignScore(Boolean isRoot,Boolean isLeft,int depth){
		if(isRoot || STOP_WORDS_SET.contains(word)){
			score = 0;
		} else {
			score = count - depth*Math.log(count)/Math.log(b);
		}
		List<Node> l = isLeft ? left : right;
		for(Node n : l){
			n.assignScore(false, isLeft, depth + 1);
		}
	}
}
