package FastText;

public class Word 
{
	// Attributes 
	
	private String vocabulary;
	private String urlAnswer;
	private long time;
	
	// Constructor
	// Constructor with parameters
	
	public Word(String pVocabulary, String pUrlAnswer, int pTime)
	{
		this.vocabulary = pVocabulary;
		this.urlAnswer = pUrlAnswer;
		this.setTime(pTime);
	}

	// Constructor without parameters
	
	public Word()
	{
		this.setVocabulary("");
		this.setUrlAnswer("");
		this.setTime(0);
	}
	
	// Methods
	// Description: The next methods return the atributes definied  upstairs and set values.
	
	public String getVocabulary()
	{
		return this.vocabulary;
	}
	
	public void setVocabulary(String pVocabulary)
	{
		this.vocabulary = pVocabulary;
	}
	
	public String getUrlAnswer()
	{
		return this.urlAnswer;
	}
	
	public void setUrlAnswer(String pUrlAnswer)
	{
		this.urlAnswer = pUrlAnswer;
	}

	public long getTime() 
	{
		return time;
	}

	public void setTime(long pTime) 
	{
		this.time = pTime;
	}
}
