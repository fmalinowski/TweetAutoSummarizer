**Twitter aggregator**

**Configure your app in the source code*

This app aggregates all the tweets from a trending topic in the Los Angeles area.
In order to run the app, you need to specify the CONSUMER_KEY, CONSUMER_KEY_SECRET, ACCESS_TOKEN and ACCESS_TOKEN_SECRET in the Main class. Those keys and secret keys are given by twitter.
You need to have created a new app on the twitter app websites - https://apps.twitter.com/ - in order to get those credentials.


** Run the program*

In order to run the program, you need to include all the jar files in the lib directory into your JAVA classpath. Then you specify the Main class to the java command.
For instance from the bin folder, you can execute the following command: 
java -cp "../lib/*:." Main

Then in order to retrieve the 1500 tweets for one trending topic, you will select one of the 10 topics displayed in the command line.
Once this step is done, the program will retrieve 1500 tweets from the selected trending topic and will generate 2 files: trendingtopic.txt and trendingtopic_text.txt (trendingtopic is the trending topic name).
trendingtopic.txt will contain all the tweets with other information such as the user and the tweetID etc.
It follows the following format:
TweetID::DateTweetWasCreated::UserID::UserName::TweetText

The other file contains only the text of the tweets for data mining.