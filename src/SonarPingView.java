//import java.awt.BorderLayout;
import java.awt.Color;
//import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
//import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class SonarPingView extends JFrame {
	private JPanel armPanel;
	private JPanel disarmPanel;
	private JPanel main;
	
	
	JPanel colorPanel;
	JButton ArmJButton;
	JButton DisarmJButton;
	
	JMenuBar menuBar;
	JMenu menuDecleration;
	
	JMenuItem configureItem;
	JMenuItem closeItem;

	public SonarPingView(){

		//setLayout( new BorderLayout());
		armPanel = new JPanel(new GridLayout(1,1));
		disarmPanel = new JPanel(new GridLayout(1,1));
		
		colorPanel = new JPanel();
		menuBar = new JMenuBar();
		
		main = new JPanel();
		main.setLayout(new GridLayout(2,2));
		
		configureItem = new JMenuItem("Configure");
		configureItem.setToolTipText("Configure Data");
		configureItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				MongoMVC mongo = new MongoMVC();
				mongo.main(null);
			}
		});
		
		menuDecleration = new JMenu("File");		
		menuDecleration.add(configureItem);
		menuBar.add(menuDecleration);

		ArmJButton = new JButton("Arm"); 
		armPanel.add(ArmJButton);

		DisarmJButton = new JButton("Disarm"); 
		disarmPanel.add(DisarmJButton);

		
		setJMenuBar(menuBar);
		//main.add(bar);
		
		main.add(armPanel);
		main.add(disarmPanel);
		main.add(colorPanel);
		

		//ArmJButton.setPreferredSize(new Dimension(200, 200));
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(500, 200);
		this.setResizable(false);
		this.add(main);
		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		this.setVisible( true );
	}

	
	void armListener(ActionListener armListener){
		ArmJButton.addActionListener(armListener);
	}
	
	void disarmListener(ActionListener disarmListener){
		DisarmJButton.addActionListener(disarmListener);
	}
}
