
public class Word {

	private String word;
	private double weight = 0;
	private int numberOfSentencesContainingWord = 0;
	private int numberOfOccurences = 0;
	private double termFrequency = 0;
	private double inverseDocumentFrequency = 0;
	
	public Word(String word) {
		this.word = word;
	}
	
	public String getWord() {
		return word;
	}
	
	public void setWord(String word) {
		this.word = word;
	}
	
	public double getWeight() {
		return weight;
	}
	
	public void setWeight(double weight) {
		this.weight = weight;
	}

	public int getNumberOfSentencesContainingWord() {
		return numberOfSentencesContainingWord;
	}

	public void setNumberOfSentencesContainingWord(
			int numberOfSentencesContainingWord) {
		this.numberOfSentencesContainingWord = numberOfSentencesContainingWord;
	}

	public int getNumberOfOccurences() {
		return numberOfOccurences;
	}

	public void setNumberOfOccurences(int numberOfOccurences) {
		this.numberOfOccurences = numberOfOccurences;
	}

	public double getTermFrequency() {
		return termFrequency;
	}

	public double getInverseDocumentFrequency() {
		return inverseDocumentFrequency;
	}
	
	public double computeAndSetWeight(int numberOfSentences, int numberOfWords) {
		if (numberOfWords == 0 || this.numberOfSentencesContainingWord == 0) {
			throw new IllegalArgumentException("The number of words or the number of sentences containing the word is invalid");
		}
		this.termFrequency = (double)this.numberOfOccurences / (double)numberOfWords;
		this.inverseDocumentFrequency = (double)numberOfSentences / (double)this.numberOfSentencesContainingWord;
		this.weight = this.termFrequency * Math.log(this.inverseDocumentFrequency) / Math.log(2);
		
		return this.weight;
	}
}
