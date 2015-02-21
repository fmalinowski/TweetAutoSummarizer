** Twitter Summarizer **


*Run the summarizer*

In order to run the summarizer, you need to have run previously the twitter aggregator.
This have generated 2 files: [topicname].txt and [topicname]_text.txt
The second file _text.txt contains only the text of the tweets. All the metadata have been deleted. You will need to provide this file to the summarizer.

To launch the summarizer, you need to go into the bin folder.
Then you can run with the following command:
java -cp "../lib/*:." edu/ucsb/cs290n/hybrid_tfidf/Main [Path to tweets file]

For instance:
java -cp "../lib/*:." edu/ucsb/cs290n/hybrid_tfidf/Main ../../Twitter-aggregator/tweet-samples/Kings_text.txt

The summary will be displayed on your terminal.

Warning: it can take 10 minutes to generate a summary of 1500 tweets.
No optimizations have been done yet...