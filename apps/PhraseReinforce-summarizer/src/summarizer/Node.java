package summarizer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Node {
	String word;
	int count;
	double score;
	List<Node> left = new LinkedList<Node>();
	List<Node> right = new LinkedList<Node>();
	Map<String,Integer> forms = new HashMap<String,Integer>();
	
	Node(String word){
		this.word = word;
		forms.put(word, 1);
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
			if(node.forms.containsKey(w)){
				node.forms.put(w, node.forms.get(w) + 1);
			} else {
				node.forms.put(w, 1);
			}
			node.count++;
		} else {
			node = new Node(w);
			left.add(node);
		}
		return node;
	}
	
	public Node updateRightNode(String w){
		String l = w.toLowerCase();
		Node node = getRightNodeByWord(l);
		if(node != null){
			if(node.forms.containsKey(w)){
				node.forms.put(w, node.forms.get(w) + 1);
			} else {
				node.forms.put(w, 1);
			}
			node.count++;
		} else {
			node = new Node(l);
			right.add(node);
		}
		return node;
	}
	private Node getNodeByWord(List<Node> l,String s){
		int i = l.size() - 1;
		s = s.toLowerCase();
		while(i >= 0 && !l.get(i).word.equals(s)) i--;
		return i == -1 ? null : l.get(i);
		
	}
	@Override
	public String toString(){
		return word + "(" + count + "(" + forms + ")" + ", " + score + ")";
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
	private static final double b = 100;
	private static final String[] STOP_WORDS = new String[]{
		"a","an","and","are","as","at","be","by","for","from","has","he","in","is","its","of","on","that","the","to","was","were","will","with","rt"
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
	public List<Node> findTheLargestPath(){
		List<Node> l = new ArrayList<Node>();
		List<Node> r = new ArrayList<Node>();
		largestPath(true, l);
		largestPath(false, r);
		l.remove(0);
		Collections.reverse(l);
		l.addAll(r);
		return l;
	}
	public List<Node> findTheLargestPathWithBeginPrun(int threshold){
		List<Node> l = new ArrayList<Node>();
		List<Node> r = new ArrayList<Node>();
		largestPathWithBeginingPruning(true, l, threshold);
		largestPathWithBeginingPruning(false, r, threshold);
		l.remove(0);
		Collections.reverse(l);
		l.addAll(r);
		return l;
	}
	public double largestPath(boolean isLeft,List<Node> path){
		List<Node> l = isLeft ? left : right;
		path.add(this);
		if(l.size() == 0){
			return score;
		}
		List<Node> maxPath = null;
		double maxScore = 0;
		for(Node n : l){
			List<Node> l2 = new ArrayList<Node>();
			double p = n.largestPath(isLeft, l2);
			if(maxPath == null || maxScore < p){
				maxPath = l2;
				maxScore = p;
			}
		}
		path.addAll(maxPath);
		return maxScore + score;
		
	}
	
	public double largestPathWithBeginingPruning(boolean isLeft,List<Node> path,int threshold){
		List<Node> l = isLeft ? left : right;
		path.add(this);
		if(l.size() == 0){
			return score;
		}
		List<Node> maxPath = null;
		double maxScore = 0;
		for(Node n : l){
			if(isLeft && n.left.size() < threshold){
				continue;
			} else if(!isLeft && n.right.size() < threshold){
				continue;
			}
			List<Node> l2 = new ArrayList<Node>();
			double p = n.largestPath(isLeft, l2);
			if(maxPath == null || maxScore < p){
				maxPath = l2;
				maxScore = p;
			}
		}
		path.addAll(maxPath);
		return maxScore + score;
		
	}
}