package edu.ucsb.cs290n.hybrid_tfidf;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


@RunWith(Suite.class)
@SuiteClasses({ PostsToSentencesDividerTest.class, SentencesToWordsDividerTest.class, SentenceTest.class, SentenceToWordsBreakerTest.class, SummaryBuilderTest.class, WordTest.class, StopWordCheckerTest.class, WordsCleanerTest.class })
public class AllTests {

}
