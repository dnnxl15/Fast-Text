package FastText;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;


import fast.text.library.*;

public class ThreadWord extends Thread implements IObservable 
{	  	  
	private long duration;
	private ArrayList<IObserver> observers;
	private Word word;
	private HashTableWord tableWord;
	private ArrayList<String> words; // ArrayList of words 
	
	public ThreadWord()
	{
		this.observers = new ArrayList<IObserver>();
 		this.tableWord = HashTableWord.getInstance(); // Instance of HashTable
		UserInterface userGui = UserInterface.getInstance(); // Instance of UI
	}
	
	public void setWords(ArrayList<String> pWords)
	{
		this.words = pWords;
	}
	
	public long getDuration()
	{
		return this.duration;
	}
	
	// Calculate the time 
	
	private long calculateTime(long pBefore, long pNow)
	{
		long total = pNow - pBefore;
		this.duration = total;
		return total;
	}
	
	@Override
	public void run()
	{
		int cont = 0; 
		while(this.words.size()>cont)
		{
			if(this.tableWord.searchWord(this.words.get(cont)) == false) // Ask if the hashtable conteins the word.
			{
				synchronized(tableWord)
				{
					this.word = new Word();
					long before = System.currentTimeMillis(); // The time in miliseconds
					WebAnswer request = new WebAnswer(); // Create an object WebAnswer to get the request.
					String direction = Constant.DIRECTION + this.words.get(cont).toString(); // The direction + the word.
					this.word.setVocabulary(this.words.get(cont)); // Set the string word to the object word.
					try 
					{
						request.sendGet(direction); // Send request.
						String answer = request.getRequest(); // Get the request.
						this.word.setUrlAnswer(answer); // Set the requet in the object Word.
						long now = System.currentTimeMillis(); // Calculate the time.
						this.word.setTime(calculateTime(before,now)); // Set the time.
						notifyObserver(); // Notify the observers.
					} 
					catch (IOException io) 
					{
						io.printStackTrace();
					} 
					catch (Exception e) 
					{
						e.printStackTrace();
					}
					try 
					{
						sleep(Constant.TIME_TO_SLEEP); // Sleep the thread.
					} 
					catch (InterruptedException e) 
					{
						e.printStackTrace();
					}
					cont++; // Increment the cont.
				}
			}
			else
			{
				this.tableWord.getWord(this.words.get(cont)); // Get the word and notify the observers.
				notifyObserver();
				cont++;
			}
		}
	}

	@Override
	public void addObserver(IObserver observer) 
	{
		this.observers.add(observer);
	}

	@Override
	public void removeObserver(IObserver observer) 
	{
		this.observers.remove(observer);
	}
	
	//@Override
	public Word getWord()
	{
		return this.word;
	}
	
	public ArrayList<IObserver> getObservers() 
	{
		return observers;
	}
	
	// This method notify all the observers
	
	private void notifyObserver()
	{
        for (Iterator<IObserver> it = observers.iterator(); it.hasNext();) 
        {
            IObserver iObserver = it.next();
            iObserver.update();
        }
    }
}
