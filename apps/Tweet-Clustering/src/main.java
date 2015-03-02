import java.util.List;


public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Clustering c;
		c = new Clustering("C:\\backfile\\cs290n\\tweets\\example.txt",false,new DistanceInterface() {
			
			@Override
			public double getDistance(List<String> t1, List<String> t2) {
				// TODO Auto-generated method stub
				return Distance.getDistJac(t1, t2);
			}
		});
		for(int i = 0;i < c.getTweetTokens().size();i++){
			List<Clustering.DistanceId> l = c.getClosestK(30, i, false);
			if(Math.abs(l.get(l.size() - 1).distance) < 0.8){
				System.out.println("[" + c.getTweetRawText().get(i));
				for(int j = 0;j < 10;j++){
					System.out.print("\t");
					System.out.println(l.get(j));
				}
				System.out.println("]");
			}
		}
	}

}
