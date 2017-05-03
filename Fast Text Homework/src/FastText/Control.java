package FastText;

import java.util.ArrayList;
import java.util.Arrays;

import fast.text.library.Constant;

public class Control 
{
	private int axisYPanel;
	private int axisYLabel;
	private Logic manipulate;
	public ArrayList<String> vocabulary;
	
	public Control()
	{
		this.manipulate = new Logic(); // New Logic object.
		this.axisYPanel = 50;
		this.axisYLabel = 0;
	}
	
	// Execute the thread and divide the phrase into arraylist
	
	public void searchWords(String pPhrase)
	{
		divide(pPhrase);
		manipulate.executeThreads(this.vocabulary);
	}
	
	// Divide the phrase to the arraylist.
	
	private ArrayList<String> divide(String pPhrase)
	{
		ArrayList<String> list = new ArrayList<String>(Arrays.asList(pPhrase.split(" ")));
		this.vocabulary = list;
		return list;
	}
	
	// Update the axis y 
	
	public void updateAxis()
	{
		this.axisYLabel = this.axisYLabel + Constant.AXIS_Y_LABEL;
		this.axisYPanel = this.axisYPanel + Constant.AXIS_Y_PANEL;
	}
	
	public int getAxisYLabel()
	{
		return this.axisYLabel;
	}
	
	public int getAxisYPanel()
	{
		return this.axisYPanel;
	}
}