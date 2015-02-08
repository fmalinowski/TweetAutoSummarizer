import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


public class TweetFilePrinter {	
	private final static String FIELD_SEPARATOR = "::";
	
	private String filenameRawTweets;
	private String filenameTextTweets;
	private PrintWriter pWriterRawTweets;
	private PrintWriter pWriterTextTweets;
	
	public TweetFilePrinter(String filename) {
		this.filenameRawTweets = filename + ".txt";
		this.filenameTextTweets = filename + "_text.txt";
	}
	
	public void prepare() {
		try {
			pWriterRawTweets = new PrintWriter(new FileWriter(this.filenameRawTweets, true));
			pWriterTextTweets = new PrintWriter(new FileWriter(this.filenameTextTweets, true));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void printTweet(long id, String tweetTime, long userId, String userName, String tweet) {
		tweet = tweet.replaceAll("\n", " ");
		pWriterRawTweets.println(id + FIELD_SEPARATOR + tweetTime + FIELD_SEPARATOR + userId + 
				FIELD_SEPARATOR + userName + FIELD_SEPARATOR + tweet);
		pWriterTextTweets.println(tweet);
	}
	
	public void close() {
		pWriterRawTweets.close();
		pWriterTextTweets.close();
	}
}
