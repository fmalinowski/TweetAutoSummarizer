import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;


import java.util.Set;

import cmu.arktweetnlp.Twokenize;


public class Clustering {
	private List<String> tweetRawText = new ArrayList<String>();
	private List<List<String>> tweetTokens = new ArrayList<List<String>>();
	private double[][] distance;
	private boolean allowRe;
	Clustering(String path,boolean allowRe){
		try {
			this.allowRe = allowRe;
			readTweets(path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void readTweets(String path) throws IOException{
		Set<String> ts = new HashSet<String>();
		BufferedReader br = new BufferedReader(new FileReader(new File(path)));
		String line = br.readLine();
		while(line != null){
			line = line.trim();
			if(line.length() == 0 || (!allowRe && ts.contains(line))){
				line = br.readLine();
				continue;
			}
			
			tweetRawText.add(line);
			ts.add(line);
			tweetTokens.add(Twokenize.tokenizeRawTweetText(line));
			line = br.readLine();
		}
	}
	 
	private void generatedistance(){
		distance = new double[tweetRawText.size()][tweetRawText.size()];
		for(int i = 0;i < tweetRawText.size();i++){
			for(int j = 0;j < tweetRawText.size();j++){
				distance[i][j] = -Distance.getDistJac(tweetTokens.get(i), tweetTokens.get(j));
			}
		}
	}
	List<List<Integer>> c;
	public void doClustering(){
		generatedistance();
		AffinityPropagation af = new AffinityPropagation(distance);
		af.Calculate(10000);
		c = af.clusters;
	}
	public List<List<String>> getClusters(){
		List<List<String>> l = new LinkedList<List<String>>();
		for(List<Integer> il : c){
			List<String> s = new LinkedList<String>();
			for(int i : il){
				s.add(tweetRawText.get(i));
			}
			l.add(s);
		}
		return l;
	}
	public void printClusters(){
		List<List<String>> c = getClusters();
		for(List<String> l : c){
			for(String s : l){
				System.out.println(s);
			}
			System.out.println();
		}
	}
}
