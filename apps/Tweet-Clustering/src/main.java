
public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Clustering c;
		(c = new Clustering("C:\\backfile\\cs290n\\tweets\\example.txt",false)).doClustering();
		c.printClusters();
	}

}
