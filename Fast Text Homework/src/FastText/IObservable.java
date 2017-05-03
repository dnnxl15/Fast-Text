package FastText;

public interface IObservable 
{
	void addObserver(IObserver observer);
	void removeObserver(IObserver observer);
	Word getWord();
}
