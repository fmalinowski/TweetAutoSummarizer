import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Distance {
	
	public static double getDistJac(List<String> t1, List<String> t2){
		Set<String> t1kens = new HashSet<String>();
		Set<String> t2kens = new HashSet<String>();
		for(String t : t1){
			if(t.matches("\\w+")){
				t1kens.add(t);
			}
		}
		for(String t : t2){
			if(t.matches("\\w+")){
				t2kens.add(t);
			}
		}
		Set<String> joint = new HashSet<String>(t1kens);
		for(String t : t2){
			if(t.matches("\\w+")){
				joint.add(t);
			}
		}
		
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
