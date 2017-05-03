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
import java.awt.SystemColor;

public class UserInterfaceProof implements IObserver{

	private JFrame frmFastSearch;
	private JTextField textField;
	private Control manipulate = new Control(); // Create a Control object to manipulate the interface.
	private JPanel mainPanel = new JPanel();
	private IObservable observable; // The observable is going to be the ThreadWord.
	private static UserInterfaceProof Instance; // Create an one instance, because the ThreadWord need to access this.

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserInterfaceProof window = UserInterfaceProof.getInstance();
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
	private UserInterfaceProof() 
	{
		initialize();
	}
	
	// Singleton Pattern, return one instance.
	
	public synchronized static UserInterfaceProof getInstance()
	{
		if (Instance==null)
		{
			Instance = new UserInterfaceProof();
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
					execute(); // Tocar 
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

	public void execute() throws Exception
	{
		// Prueba 
		// Panel 1 de https://www.facebook.com
		
		JEditorPane paneEditor = new JEditorPane();
        paneEditor.setContentType("text/html");
        WebAnswer p = new WebAnswer();
        p.sendGet("https://www.facebook.com/");
        paneEditor.setText(p.getRequest());
    
        JScrollPane paneScroll = new JScrollPane(paneEditor);
        paneScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        paneScroll.setVisible(true);
        int axiYPanel = manipulate.getAxisYPanel();
        paneScroll.setBounds(20,axiYPanel,500,400);
        manipulate.updateAxis();
        
        // Panel 2 de https://www.google.com/search?q=Pedro
        // Palabra a buscar Pedro
        
        JEditorPane paneEditor2 = new JEditorPane();
        paneEditor2.setContentType("text/html");
        WebAnswer p2 = new WebAnswer();
        p2.sendGet("https://www.google.com/search?q=Pedro");
        paneEditor2.setText(p2.getRequest());
    
        JScrollPane paneScroll2 = new JScrollPane(paneEditor2);
        paneScroll2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        paneScroll2.setVisible(true);
        int axiYPanel2 = manipulate.getAxisYPanel();
        paneScroll2.setBounds(20,axiYPanel2,500,400);
        
        mainPanel.add(paneScroll);
        mainPanel.add(paneScroll2);
	}
}
