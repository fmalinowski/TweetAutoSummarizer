import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import twitter4j.Trend;
import twitter4j.Trends;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import twitter4j.conf.ConfigurationBuilder;

public class Main {

	private final static String CONSUMER_KEY = "PLACE HERE YOUR KEY";
	private final static String CONSUMER_KEY_SECRET = "PLACE HERE YOUR SECRET KEY";
	
	private final static String ACCESS_TOKEN = "PLACE HERE YOUR TOKEN";
	private final static String ACCESS_TOKEN_SECRET = "PLACE HERE YOUR TOKEN SECRET"; 
	
	private final static int NEW_YORK_WOEID = 2459115;
	private final static int LOS_ANGELES_WOEID = 2442047;
	
	public static void main(String[] args) throws TwitterException {
		start();
	}
	
	public static void start() throws TwitterException {
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setOAuthConsumerKey(CONSUMER_KEY)
		  .setOAuthConsumerSecret(CONSUMER_KEY_SECRET)
		  .setOAuthAccessToken(ACCESS_TOKEN)
		  .setOAuthAccessTokenSecret(ACCESS_TOKEN_SECRET);
		
		Twitter twitter = new TwitterFactory(cb.build()).getInstance();
		
		Trend[] trends = twitter.getPlaceTrends(LOS_ANGELES_WOEID).getTrends();
		
		System.out.println("Here are the trending topics for Los Angeles:");
		for(int i = 0; i < trends.length; i++) {
			System.out.println("(" + i + ") - " + trends[i].getName());
		}
		System.out.println("Please choose one trending topic among the ones above: ");
		Scanner consoleInput = new Scanner(System.in);
		int desiredTopic = consoleInput.nextInt();
		consoleInput.close();
		
		new TweetTopicAggregator(twitter, trends[desiredTopic].getName(), trends[desiredTopic].getQuery()).getTweetsAndStore();
	}
}
