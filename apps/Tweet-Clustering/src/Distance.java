import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Distance {
	
	public static double getDistJac(List<String> t1, List<String> t2){
		Set<String> t1kens = new HashSet<String>(t1);
		Set<String> t2kens = new HashSet<String>(t2);
		
		Set<String> joint = new HashSet<String>(t1kens);
		joint.addAll(t2kens);
		
		int j = joint.size();
		int i = 0;
		for(String s : t1kens){
			if(t2kens.contains(s)){
				i++;
			}
		}
		return 1 - ((double)i)/j;
	}
}
