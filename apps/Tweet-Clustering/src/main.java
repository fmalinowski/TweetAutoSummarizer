import java.util.List;


public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Clustering c;
		final TFIDF t = new TFIDF("C:\\backfile\\cs290n\\tweets\\example.txt");
		c = new Clustering("C:\\backfile\\cs290n\\tweets\\example.txt",false,new DistanceIdx() {
			
			@Override
			public double getDistance(int i, int j) {
				// TODO Auto-generated method stub
				return t.TFIDFDistance(i, j);
			}
		});
		for(int i = 0;i < c.getTweetTokens().size();i++){
			List<Clustering.DistanceId> l = c.getCloserThan(0.5, i, false);
			System.out.println("[" + c.getTweetRawText().get(i));
			for(int j = 0;j < l.size();j++){
				System.out.print("\t");
				System.out.println(l.get(j));
			}
		}
		
	}

}
