import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class AffinityPropagation {
	private double[][] A;
	private double[][] R;
	private double[][] S;
	List<List<Integer>> clusters;
	List<Integer> examplars;
	
	AffinityPropagation(double[][] s){
		S = s;
		int n = s.length;
		int m = s[0].length;
		A = new double[n][m];
		R = new double[n][m];
		for(int i = 0;i < n;i++){
			for(int j = 0;j < m;j++){
				A[i][j] = 0;
				R[i][j] = 0;
			}
		}
	}
	public void Calculate(int maxItr){
		Calculate(maxItr,0);
	}
	public void Calculate(int maxItr,double lambda){
		int n = S.length;
		int m = S[0].length;
		double delta = Double.MAX_VALUE;
		double threshold = 1e-3;
		int itr = 0;
		while(itr < maxItr && delta >= threshold){
			delta = 0;
			
			double[][] A_new = new double[n][m];
			double[][] R_new = new double[n][m];
			
			double[][] aps = new double[n][m];
			for(int i = 0;i < n;i++){
				for(int j = 0;j < m;j++){
					aps[i][j] = A[i][j] + S[i][j];
				}
			}
			for(int i = 0;i < n;i++){
				double[] maxFromLeftAS = new double[m];
				double[] maxFromRightAS= new double[m];
				maxFromLeftAS[0] = -Double.MAX_VALUE;
				maxFromRightAS[0]= -Double.MAX_VALUE;
				for(int j = 1;j < m;j++){
					maxFromLeftAS[j] = Math.max(maxFromLeftAS[j - 1],aps[i][j - 1]);
					maxFromRightAS[m - j - 1] = Math.max(maxFromRightAS[m - j], aps[i][m - j]);
				}
				
				double rs = 0;
				for(double d : R[i]){
					rs += Math.max(0, d);
				}
				for(int j = 0;j < m;j++){
					R_new[i][j] = S[i][j] - Math.max(maxFromLeftAS[j], maxFromRightAS[j]);
					R_new[i][j] = lambda*R[i][j] + (1 - lambda)*R_new[i][j];
					
					double sum = rs - Math.max(0, R[i][j]) - Math.max(0, R[j][j]);
					if(i == j){
						A_new[i][j] = sum;
					} else {
						A_new[i][j] = Math.min(R[j][j] + sum, 0);
					}
					
					A_new[i][j] = lambda*A[i][j] + (1 - lambda)*A_new[i][j];
					delta += Math.abs(A_new[i][j] - A[i][j]) + Math.abs(R_new[i][j] - R[i][j]);
				}
				
			}
			
			itr++;
			System.out.println("iterate:" + itr + ", delta" + delta + ";");
			A = A_new;
			R = R_new;
		}
		
		clusters = new ArrayList<List<Integer>>();
		examplars = new ArrayList<Integer>();
		
		double[][] sumMat = new double[n][m];
		for(int i = 0;i < n;i++){
			for(int j = 0;j < m;j++){
				sumMat[i][j] = A[i][j] + R[i][j];
			}
		}
		Map<Integer, List<Integer>> c = new HashMap<Integer, List<Integer>>();
		for(int i = 0;i < n;i++){
			double maxScore = -Double.MAX_VALUE;
			int maxIdx = -1;
			for(int j = 0;j < m;j++){
				if(maxScore < sumMat[i][j]){
					maxScore = sumMat[i][j];
					maxIdx = j;
				}
			}
			if(!c.containsKey(maxIdx)){
				c.put(maxIdx, new LinkedList<Integer>());
			}
			c.get(maxIdx).add(i);
		}
		for(int i : c.keySet()){
			clusters.add(c.get(i));
			examplars.add(i);
		}
		
	}
	
}
