package FastText;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class PanelObject implements IObserver
{
	private IObservable observable;
	private JPanel space;
	private static PanelObject Instance;
	
	private PanelObject()
	{
		space = new JPanel();
		space.setBackground(new Color(0, 153, 204));
		space.setBounds(0, 0, 600, 800);
		space.setLayout(null);
		space.setVisible(true);
		space.setPreferredSize(new Dimension(500, 1200));// Modificar el 1200 para aumentar el scrollarea !!!!!!!!!!!!!!11111
		
		JScrollPane nuevo22 = new JScrollPane(space);
	    nuevo22.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	    nuevo22.setVisible(true);
	    nuevo22.setBounds(15,80,556,350);
	}
	
	public synchronized static PanelObject getInstance()
	{
		if (Instance==null)
		{
			Instance = new PanelObject();
		}
		
		return Instance;
	}
	
	public IObservable getObservable()
	{
		return this.observable;
	}
	
	

	public JPanel getSpace()
	{
		return this.space;
	}
	
	@Override
	public void update() 
	{
				Word vocabulary = this.observable.getWord();
				JEditorPane jep = new JEditorPane();
			    jep.setContentType("text/html");
			    jep.setText(vocabulary.getUrlAnswer());  //n.getRequest());
			    
			    JScrollPane nuevo = new JScrollPane(jep);
			    nuevo.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			    nuevo.setVisible(true);
			    nuevo.setBounds(20,50,500,400);
			    space.add(nuevo);
	}
}
