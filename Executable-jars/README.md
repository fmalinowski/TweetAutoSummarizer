**Summarize tweets executables**

*Instructions to retrieve tweets from Twitter*
You can retrieve 1500 tweets for a trending topic in the Los Angeles area by typing this command:
java -jar summarizer-tweets-aggregator.jar

This will generate two files:  [topic-name].txt and [topic-name]_text.txt
You will use the latter file with the summarizers.


*Instructions to generate automated summaries with Hybrid TF-IDF*
java -jar hybrid-tfidf-summarizer.jar [tweet_text_file]


*Evaluations with the ROUGE-1 metric*
java -jar summarizer-rouge-evaluation.jar [referenced_file_containing_one_summary_per_line] [automated_summaries_file_containing_one_summary_per_line]
