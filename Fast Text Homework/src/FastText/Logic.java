package FastText;

import java.util.ArrayList;

public class Logic 
{	
	public Logic()
	{
	}
	
	public void executeThreads(ArrayList<String> pPhrase)
	{
		ThreadWord wire = new ThreadWord(); // Create a new Thread.
		HashTableWord.getInstance().setObservable(wire); // Set the observable thread to the HashTableWord.
		UserInterface.getInstance().setObservable(wire); // Set the observable thread to the UI interface.
		wire.setWords(pPhrase); // Set the words in the arraylist.
		wire.start(); // Start the thread.
	}
}