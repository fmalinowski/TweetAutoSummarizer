
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
	
	public void setWeight(int weight) {
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

	public void setTermFrequency(double termFrequency) {
		this.termFrequency = termFrequency;
	}

	public double getInverseDocumentFrequency() {
		return inverseDocumentFrequency;
	}

	public void setInverseDocumentFrequency(double inverseDocumentFrequency) {
		this.inverseDocumentFrequency = inverseDocumentFrequency;
	}
}
