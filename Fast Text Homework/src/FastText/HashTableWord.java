package FastText;

import java.util.Enumeration;
import java.util.Hashtable;

public class HashTableWord implements IObserver 
{
	private Hashtable<Word,String> Hashlw;
	private static HashTableWord Instance;
	private IObservable observable;
	
	// Constructor private to use Singleton
	
	private HashTableWord()
	{
		this.Hashlw = new Hashtable<Word,String>();
	}
	
	// This funtion setObservable and add observe.
	
	public void setObservable(IObservable observable)
	{
		this.observable = observable;
		this.observable.addObserver(this);
	}
	
	// Singleton Instance.
	
	public synchronized static HashTableWord getInstance()
	{
		if (Instance==null)
		{
			Instance = new HashTableWord();
		}
		
		return Instance;
	}
	
	// The next method add a new word in the hashtable
	
	public void addNewWord(Word pVocabulary, String pAnswer)
	{
		if (!Hashlw.containsKey(pVocabulary))
		{
			Hashlw.put(pVocabulary, pAnswer);
		}
	}
	
	public Word getWord(String pKey)
	{
		Enumeration<Word> e = this.Hashlw.keys();
		if(searchWord(pKey) == true)
		{
			while( e.hasMoreElements() )
			{
				Word clave = e.nextElement();
				if(clave.getVocabulary() == pKey)
				{
					return clave;
				}
			}
		}
		else
		{
			return null;
		}
		return null;
	}
	
	// The next method search Word in the hashtable.
	
	public boolean searchWord(String pVocabulary)
	{
		Enumeration<Word> e = this.Hashlw.keys();
		Word clave;
		while( e.hasMoreElements())
		{
			clave = e.nextElement();
			if(clave.getVocabulary() == pVocabulary)
			{
				return true;
			}
		}
		return false;
	}

	// This method update this observer. 
	
	@Override
	public void update() 
	{
		Word word = this.observable.getWord();
		if(HashTableWord.getInstance().searchWord(word.getVocabulary()) == false)
		{
			HashTableWord.getInstance().addNewWord(word,word.getUrlAnswer());
		}
	}
}
