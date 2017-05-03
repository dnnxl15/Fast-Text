package FastText;

import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JEditorPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class UserInterface implements IObserver{

	private JFrame frmFastSearch;
	private JTextField textField;
	private Control manipulate = new Control(); // Create a Control object to manipulate the interface.
	private JPanel mainPanel = new JPanel();
	private IObservable observable; // The observable is going to be the ThreadWord.
	private static UserInterface Instance; // Create an one instance, because the ThreadWord need to access this.

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserInterface window = UserInterface.getInstance();
					window.frmFastSearch.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	private UserInterface() 
	{
		initialize();
	}
	
	// Singleton Pattern, return one instance.
	
	public synchronized static UserInterface getInstance()
	{
		if (Instance==null)
		{
			Instance = new UserInterface();
		}
		
		return Instance;
	}
	
	// Description: This method set observable and add the observer.
	
	public void setObservable(IObservable observable)
	{
		this.observable = observable;
		this.observable.addObserver(this);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmFastSearch = new JFrame();
		frmFastSearch.getContentPane().setBackground(new Color(204, 0, 0));
		frmFastSearch.getContentPane().setForeground(new Color(0, 51, 51));
		frmFastSearch.setTitle("Fast Search ");
		frmFastSearch.setBounds(100, 100, 601, 522);
		frmFastSearch.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmFastSearch.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(26, 36, 406, 22);
		frmFastSearch.getContentPane().add(textField);
		textField.setColumns(10);
		
		
		mainPanel.setBackground(new Color(0, 153, 204));
		mainPanel.setBounds(0, 0, 600, 800);
		mainPanel.setLayout(null);
		mainPanel.setVisible(true);
		int mainPanelAxiY = manipulate.getAxisYPanel() + 1200;
		mainPanel.setPreferredSize(new Dimension(500, mainPanelAxiY));// Modificar el 1200 para aumentar el scrollarea !!!!!!!!!!!!!!11111

		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				try {
					execute();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}  // Button search, execute the funtion execute.
			}
		});
		
		JScrollPane mainScroll = new JScrollPane(mainPanel);
		mainScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		mainScroll.setVisible(true);
		mainScroll.setBounds(15,80,556,350);
		
		btnSearch.setBounds(461, 35, 97, 25);
		frmFastSearch.getContentPane().add(btnSearch);
        frmFastSearch.getContentPane().add(mainScroll);
		
	}

	// Method to execute the control method, execute threads.
	
	public void execute() throws Exception
	{
		String phrase = this.textField.getText().toString();
		manipulate.searchWords(phrase);
	}

	// Update the observers
	
	@Override
	public void update() 
	{
		Word word = this.observable.getWord(); // Get the word 	from the observable.
		JEditorPane paneEditor = new JEditorPane(); // Panel Editor where i put the html code.
        paneEditor.setContentType("text/html"); // Type of text.
        paneEditor.setText(word.getUrlAnswer()); // Set the code html
    
        JScrollPane paneScroll = new JScrollPane(paneEditor); // Create a new scrollPane.
        paneScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); // Set the scroll
        paneScroll.setVisible(true); // Set visible.
        int axiYPanel = manipulate.getAxisYPanel(); // The axis y in the panel.
        paneScroll.setBounds(20,axiYPanel,500,400);
        
        JLabel labelName = new JLabel(); // Label where contains the word.
        labelName.setText(word.getVocabulary()); // Set the text.
        int axiYLabel = manipulate.getAxisYLabel(); // The axi y in the label.
        labelName.setBounds(10,axiYLabel,50,50);
        
        JLabel labelTime = new JLabel(); // Label where contains the time.
        String textLabel = String.valueOf(word.getTime()); // Convert the time to the string.
        labelTime.setText(textLabel); // Set the text.
        int axiYLabel2 = manipulate.getAxisYLabel(); // Get the y axis.
        labelTime.setBounds(50, axiYLabel2, 50, 50);
        
        mainPanel.add(paneScroll); // Add the ScrollPanel to the main panel.
        mainPanel.add(labelName); // Add the label name.
        mainPanel.add(labelTime); // Add the label time.
        manipulate.updateAxis(); // Update the axis.
     }
}
