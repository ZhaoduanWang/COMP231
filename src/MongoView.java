import java.awt.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.event.ListSelectionEvent;  
import javax.swing.event.ListSelectionListener;

public class MongoView extends JFrame{
	
	private JLabel     firstNameLabel = new JLabel("Firstname: ");
	JTextField firstName = new JTextField(10);
	private JLabel     lastNameLabel = new JLabel("Lastname: ");
	JTextField lastName = new JTextField(10);
	private JLabel     EmailLabel = new JLabel("Email: ");
	JTextField email = new JTextField(20);
	private JLabel     PasswordLabel = new JLabel("Password: ");
	JTextField password = new JTextField(10);
	private JLabel browseUserInfo = new JLabel("Browse User Info :");
	JTextField type = new JTextField(10);
	private JLabel userType = new JLabel("Type :");
	String[] petStrings = { "Homeowner", "Dependant", "Time-Sensitive" };

	
	private JButton 	createButton = new JButton("Create");
	private JButton 	deleteButton = new JButton("Delete");
	private JButton 	updateButton = new JButton("Update");
	private JButton     browseButton = new JButton("Browse");
	//private JButton     removeallButton = new JButton("Remove All");
	private JButton 	btnView1;
 
	public JPanel panelMain;
	public JPanel panelCenter;
	//public JPanel calcPanel;
	
	public JPanel panelView;
	public JScrollPane srlpaneView;
	public JTable dbTableView;
	public DefaultTableModel tblmodelView;	
	
	
	public void buildViewPanel()
	{
		panelView = new JPanel();
		
		panelView.setLayout(new BorderLayout());
		btnView1 = new JButton("Reflesh");
		panelView.add("South",btnView1);
		
		Object[] columns = {"FirstName", "LastName", "Email", "Password", "Type"};
		tblmodelView = new DefaultTableModel();
		tblmodelView.setColumnIdentifiers(columns);
		dbTableView = new JTable(tblmodelView);
		dbTableView.setBackground(Color.lightGray);
		dbTableView.setForeground(Color.black);
		//Font font = new Font("",1,14);
		//dbTableView.setFont(font);	
		srlpaneView = new JScrollPane(dbTableView);
				
		panelView.add("North",srlpaneView);
		
		
		dbTableView.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
		    @Override
		    public void valueChanged(ListSelectionEvent event) 
		    {
		    	
		        if (dbTableView.getSelectedRow() > -1) 
		        {
		            // print first column value from selected row
		            //JOptionPane.showMessageDialog(null,dbTableView.getValueAt(dbTableView.getSelectedRow(), 0).toString());
		            firstName.setText(dbTableView.getValueAt(dbTableView.getSelectedRow(), 0).toString());
		            lastName.setText(dbTableView.getValueAt(dbTableView.getSelectedRow(), 1).toString());
		            email.setText(dbTableView.getValueAt(dbTableView.getSelectedRow(), 2).toString());
		            password.setText(dbTableView.getValueAt(dbTableView.getSelectedRow(), 3).toString());
		            type.setText(dbTableView.getValueAt(dbTableView.getSelectedRow(), 4).toString());
		        }
		    }
		});		
		
		
	}	
	
	MongoView()
	{
		
		panelMain = new JPanel();
		
		this.setSize(600,680);
		this.setResizable(false);
		this.setTitle("Users Information Management");
		panelMain.setLayout(new BorderLayout());
		this.add(panelMain);
		
		buildViewPanel();
		panelMain.add("North",panelView);
		
		panelCenter = new JPanel();
		panelCenter.setLayout(new GridBagLayout());		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		panelCenter.add(firstNameLabel,gbc);
		gbc.gridx = 1;
		gbc.gridy = 0;
		panelCenter.add(firstName,gbc);
		gbc.gridx = 0;
		gbc.gridy = 1;
		panelCenter.add(lastNameLabel,gbc);
		gbc.gridx = 1;
		gbc.gridy = 1;
		panelCenter.add(lastName,gbc);
		gbc.gridx = 0;
		gbc.gridy = 2;
		panelCenter.add(EmailLabel,gbc);
		gbc.gridx = 1;
		gbc.gridy = 2;
		panelCenter.add(email,gbc);
		gbc.gridx = 0;
		gbc.gridy = 3;		
		panelCenter.add(PasswordLabel,gbc);
		gbc.gridx = 1;
		gbc.gridy = 3;	
		panelCenter.add(password,gbc);
		gbc.gridx = 0;
		gbc.gridy = 4;
		panelCenter.add(userType,gbc);
		gbc.gridx = 1;
		gbc.gridy = 4;
		panelCenter.add(type,gbc);
		gbc.gridx = 0;
		gbc.gridy = 5;
		panelCenter.add(createButton,gbc);
		gbc.gridx = 1;
		gbc.gridy = 5;		
		panelCenter.add(deleteButton,gbc);
		gbc.gridx = 2;
		gbc.gridy = 5;		
		panelCenter.add(updateButton,gbc);
		gbc.gridx = 3;
		gbc.gridy = 5;		
		panelCenter.add(browseButton,gbc);	
		gbc.gridx = 5;
		gbc.gridy = 5;		
		//panelCenter.add(removeallButton,gbc);		
		setVisible(true);
		panelMain.add("Center",panelCenter);
		
		
	}
	
	public String getFirstName()
	{
		return firstName.getText();
	}
	
	public String getLastName()
	{
		return lastName.getText();
	}
	
	public String getEmail()
	{
		return email.getText();
	}
	
	public String getPassword()
	{
		return password.getText();
	}
	
	public String getUserType()
	{
		return type.getText();
	}

	
	void addMongoButtonListener(ActionListener listenForButton)
	{
		createButton.setActionCommand("CreateButton");
		createButton.addActionListener(listenForButton);
		deleteButton.setActionCommand("deleteButton");
		deleteButton.addActionListener(listenForButton);
		updateButton.setActionCommand("updateButton");
		updateButton.addActionListener(listenForButton);	
		browseButton.setActionCommand("browseButton");
		browseButton.addActionListener(listenForButton);
		//removeallButton.setActionCommand("removeallButton");
		//removeallButton.addActionListener(listenForButton);		
		
	}
	
	void displayErrorMessage(String errorMessage)
	{
		JOptionPane.showMessageDialog(this, errorMessage);
	}
}
