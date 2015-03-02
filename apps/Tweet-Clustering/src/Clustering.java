import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;


import java.util.PriorityQueue;
import java.util.Set;

import cmu.arktweetnlp.Twokenize;


public class Clustering {
	public List<String> getTweetRawText() {
		return tweetRawText;
	}
	public List<List<String>> getTweetTokens() {
		return tweetTokens;
	}

	private List<String> tweetRawText = new ArrayList<String>();
	private List<List<String>> tweetTokens = new ArrayList<List<String>>();
	private double[][] distance;
	private boolean allowRe;
	DistanceInterface method;
	Clustering(String path,boolean allowRe,DistanceInterface method){
		try {
			this.allowRe = allowRe;
			this.method = method;
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
			line = line.replace((char)160,' ').trim().replaceAll("#\\w+", "").replaceAll("@\\w+","").replaceAll("^ *\\d+ - ", "");
			tweetRawText.add(line);
			ts.add(line);
			tweetTokens.add(Twokenize.tokenizeRawTweetText(line));
			line = br.readLine();
		}
		generatedistance();
	}
	 
	private void generatedistance(){
		distance = new double[tweetRawText.size()][tweetRawText.size()];
		for(int i = 0;i < tweetRawText.size();i++){
			for(int j = 0;j < tweetRawText.size();j++){
				distance[i][j] = -method.getDistance(tweetTokens.get(i), tweetTokens.get(j));
			}
		}
	}
	
	List<List<Integer>> c;
	public void doClustering(){
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
	
	class DistanceId{
		double distance;
		int id;
		String tt;
		public String toString(){
			return distance + "\t:" + tt;
		}
		DistanceId(double distance,int id){
			this.distance = distance;
			this.id = id;
			this.tt = tweetRawText.get(id);
		}
	}
	
	public List<DistanceId> getClosestK(int k,int which,boolean dup){
		List<DistanceId> ld = new ArrayList<DistanceId>();
		String t = tweetRawText.get(which);
		for(int i = 0;i < tweetRawText.size();i++){
			if(i == which){
				continue;
			}
			if(!dup && tweetRawText.get(i).equals(t)){
				continue;
			}
			ld.add(new DistanceId(distance[which][i], i));
		}
		Collections.sort(ld,new Comparator<DistanceId>() {

			@Override
			public int compare(DistanceId o1, DistanceId o2) {
				if(o1.distance == o2.distance){
					return 0;
				}
				return o1.distance < o2.distance ? 1 : -1;
			}
		});
		for(int i = 1;i < ld.size();i++){
			if(ld.get(i).tt.equals(ld.get(i - 1).tt)){
				ld.remove(i);
				i--;
			}
		}
		return ld.subList(0, k);
	}
	
}
