package Tweet_Cleaner;

public class Helper {
	public static String getTweetText(String line){
		int i = 1;
		int count = 0;
		for(;i < line.length();i++){
			if(line.charAt(i) == ':' && line.charAt(i - 1) == ':'){
				count++;
			}
			if(count == 4){
				break;
			}
		}
		
		if(count < 4){
			return null;
		}
		
		return line.substring(i + 1);
	}
}
