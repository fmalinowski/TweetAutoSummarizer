import java.awt.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.RateLimitStatus;
import twitter4j.RateLimitStatusEvent;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.User;


public class TweetTopicAggregator {
	
	private final static int TOTAL_TWEETS_PER_TOPIC = 1500;
	private final static int MAX_REQUESTS_PER_15MIN = 180;
	
	private Twitter twitter;
	private String topicName;
	private String topicQuery;
	
	public TweetTopicAggregator(Twitter twitter, String topicName, String topicQuery) {
		this.twitter = twitter;
		this.topicName = topicName;
		this.topicQuery = topicQuery;
	}
	
	@SuppressWarnings("deprecation")
	public void getTweetsAndStore() {
		TweetFilePrinter tweetFilePrinter = new TweetFilePrinter(this.topicName);
		
		int totalCount = 0;
		int remainingRequestsBeforeRateLimit = MAX_REQUESTS_PER_15MIN;
		int secondsUntilLimitRateReset = 0;
		
		RateLimitStatus rateLimitStatus;
		long tweetId;
		String tweetText;
		User user;
		long userId;
		String userName;
		Date tweetCreatedAt;
		String tweetCreatedAtStr;
		
		Boolean hasStillResults = true;
		
		System.out.println("Start retrieving the tweets");
		
		Query query = new Query(this.topicQuery);
		query.count(100);
		query.setLang("en");
		
		tweetFilePrinter.prepare();
		
		while ((totalCount < TOTAL_TWEETS_PER_TOPIC) && hasStillResults) {
			try {				
				QueryResult result = twitter.search(query);
				totalCount += result.getCount();
				
				rateLimitStatus = result.getRateLimitStatus();
				remainingRequestsBeforeRateLimit = rateLimitStatus.getRemaining();
				secondsUntilLimitRateReset = rateLimitStatus.getSecondsUntilReset();
				
				java.util.List<Status> tweets = result.getTweets();
				
				for (Status tweet : tweets) {
					tweetId = tweet.getId();
					tweetText = tweet.getText();
					tweetCreatedAt = tweet.getCreatedAt();
					tweetCreatedAtStr = tweetCreatedAt.toString();
					
					user = tweet.getUser();
					userId = user.getId();
					userName = user.getName();
					
					tweetFilePrinter.printTweet(tweetId, tweetCreatedAtStr, userId, userName, tweetText);
				}
				
				System.out.println("Got " + totalCount + " tweets");
				
				hasStillResults = result.hasNext();
				query = result.nextQuery();
				
				if (remainingRequestsBeforeRateLimit <= 0) {
					tweetFilePrinter.close();
					System.out.println("Need to sleep for " + secondsUntilLimitRateReset + "s");
					Thread.sleep(secondsUntilLimitRateReset * 1000);
					tweetFilePrinter.prepare();
				}
			} catch (TwitterException | InterruptedException e) {
				e.printStackTrace();
			}
		}
		tweetFilePrinter.close();
		
		System.out.println("Remaining requests before waiting 15mn: " + remainingRequestsBeforeRateLimit);
	}

}
