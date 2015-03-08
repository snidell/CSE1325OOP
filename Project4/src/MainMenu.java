import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.UIManager.*;


public class MainMenu extends JFrame implements Proj3Constants,DateConstants,ActionListener {
	Enterprise myEnt = new Enterprise("");
	JLabel lockedLabel= new JLabel("");
	String [] empNames={"Bill","John","Cathy","Hernando","Da Vinci","Riario","Amerigo"}; 
    JList employeeList= new JList(empNames);
    JList employeeList1=new JList(empNames);
    JList itemList=new JList();
    JList unSoldItemList=new JList();
    JScrollPane empScroll= new JScrollPane(employeeList);
    JTextArea empTA;
    JTextArea custTA;
    JTextArea itemTA;
    JList custList;
    JFrame empFrame= new JFrame();
    JFrame mainFrame= new JFrame();
    JFrame customerFrame= new JFrame();
    JFrame itemFrame=new JFrame();
    JComboBox yearCombo1;
    Font myFont =new Font("SansSerif",Font.BOLD, 16);
 
	MainMenu(Enterprise ent){
		this.myEnt=ent; 	
		this.loadMainWindow();		
		this.mainFrame.validate();		
	}
	/**
	 * 
	 * 
	 * loads Main Window
	 */
	public void loadMainWindow(){
		mainFrame = new JFrame();    
		mainFrame.setTitle("Mav Bay Auctionhouse");
		mainFrame.setSize(1200,600);//making the size of the window
		mainFrame.setLocation(150,150);//window placed at 100 pixel x 100 pixel
		mainFrame.setVisible(true);//make the window visable
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	       
	    JPanel panel1 = new JPanel();
	    panel1.setLayout(new GridBagLayout());//creating a grid layout for panel one
	    panel1.setBackground(new Color(169,169,169));
	    GridBagConstraints c = new GridBagConstraints();//Constraints for products being added
	    
	    
	    
	    
	    
	    JPanel panel2 = new JPanel();//panel for "Locked" label
	    panel2.setBackground(new Color(169,169,169));
	    
	    JPanel panel3 = new JPanel();//panel for "Start" and "Okay" buttons
	    panel3.setBackground(new Color(169,169,169));
	    
	    
	    //Adding Pad top left
	    JPanel panel4=new JPanel(); //Top Left Pad
	    panel4.setBackground(new Color(169,169,169));
	    c.fill=GridBagConstraints.HORIZONTAL;
	    c.gridx=0;
	    c.gridy=0;
	    c.gridwidth = 1;	    
	    c.ipady=350;
	    c.ipadx=150;
	    panel1.add(panel4,c);
	    
	    JLabel empL= new JLabel("Select Year");
	    c.fill = GridBagConstraints.HORIZONTAL;
	    c.ipady = 10;      //make this component tall
	    c.ipadx= 150;
	    c.gridwidth = 1;  
	    c.gridx = 0;
	    c.gridy = 1;	    
	    panel1.add(empL,c);
	    
	    
	    String [] years={"All","2014","2013","2012","2011","2010"};
	    final JComboBox yearCombo= new JComboBox(years);
	    c.fill = GridBagConstraints.HORIZONTAL;
	    c.ipady = 20;      //make this component tall
	    c.ipadx= 150;
	    c.gridwidth = 1;  
	    c.gridx = 0;
	    c.gridy = 2;
	    panel1.add(yearCombo, c);
	    
	    
	    JButton button;
	    button = new JButton("Get Revenue");
	    button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	empTA.setText("");
            	String myString= (String)yearCombo.getSelectedItem();
                empTA.append(myEnt.sYearRevenue(myString));
            }
        });
	    button.setBackground(new Color(47,92,180));
	    c.fill = GridBagConstraints.HORIZONTAL;
	    c.ipady = 20;      //make this component tall
	    c.ipadx= 150;
	    c.weightx = 0.0;
	    c.gridwidth = 1;
	    c.gridheight= 1;	    		 
	    c.gridx = 0;
	    c.gridy = 3;	    
	    panel1.add(button, c);
	    
	    
	     
	    JPanel panel6=new JPanel();//Bottom left panel
		panel6.setBackground(new Color(169,169,169));
		c.fill=GridBagConstraints.HORIZONTAL;
		c.gridx=0;
		c.gridy=7;
		c.gridwidth = 1;
		c.ipady=350;
		c.ipadx=150;
	    panel1.add(panel6,c);    
	    
	    empTA= new JTextArea();
	    empTA.setEditable(false);
	    c.fill=GridBagConstraints.HORIZONTAL;
		c.gridx=1;
		c.gridy=0;
		c.gridwidth = 5;
		c.ipady=480;
		c.ipadx=1;
		c.gridwidth = 1;
		c.gridheight=8;
		empScroll= new JScrollPane(empTA);
		panel1.add(empScroll,c);
	     
		JButton b9 = new JButton("9");
	    b9.setForeground(Color.YELLOW);
	    b9.setBackground(Color.BLACK);
	    b9.setActionCommand("9");
	    b9.addActionListener(this);
	    c.fill=GridBagConstraints.HORIZONTAL;
	    c.gridx=1;
		c.gridy=0;
		c.gridwidth = 1;
		c.gridheight=5;
		c.ipady=500;
		c.ipadx=900;
	    panel1.add(b9,c);
	     	     
	    JButton buttonStart = new JButton("Main");
	    buttonStart = new JButton("Main");
	    buttonStart.setForeground(Color.BLACK);
	    buttonStart.setBackground(new Color(0,128,0));
	    buttonStart.setActionCommand("Main");
        buttonStart.addActionListener(this);
	    panel3.add(buttonStart);
	     
	    JButton buttonOkay=new JButton("Employee");
	    buttonOkay.setForeground(Color.BLACK);
	    buttonOkay.setBackground(Color.GRAY);
	    buttonOkay.setActionCommand("Employee");
	    buttonOkay.addActionListener(this);
	    panel3.add(buttonOkay);
	     
	    JButton customerButton = new JButton("Customers");
	    customerButton = new JButton("Customers");
	    customerButton.setForeground(Color.BLACK);
	    customerButton.setBackground(Color.GRAY);
	    customerButton.setActionCommand("Customers");
	    customerButton.addActionListener(this);
	    panel3.add(customerButton);
	     
	    JButton itemButton = new JButton("Item");
	    customerButton = new JButton("Item");
	    customerButton.setForeground(Color.BLACK);
	    customerButton.setBackground(Color.GRAY);
	    customerButton.setActionCommand("Item");
	    customerButton.addActionListener(this);
	    panel3.add(customerButton);
	     
	    mainFrame.add(panel1,BorderLayout.CENTER);//add first panel to the screen
	     
	    lockedLabel = new JLabel("Main Menu");
	    lockedLabel.setFont(myFont);
	     
	    lockedLabel.setForeground(Color.RED);
	    lockedLabel.setBackground(Color.BLACK);
	    panel2.add(lockedLabel);
	    mainFrame.add(panel2,BorderLayout.NORTH);//add second panel to the screen
	    mainFrame.add(panel3,BorderLayout.SOUTH);
	}
	
	/**
	 * 
	 * 
	 * loads Employee Window
	 */
	public void loadEmployeeWindow(){
		
		empFrame = new JFrame();    
		empFrame.setTitle("Mav Bay Auctionhouse");
		empFrame.setSize(1200,600);//making the size of the window
		empFrame.setLocation(150,150);//window placed at 100 pixel x 100 pixel
		empFrame.setVisible(true);//make the window visable
		empFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	       
	    JPanel panel1 = new JPanel();
	    panel1.setLayout(new GridBagLayout());//creating a grid layout for panel one
	    panel1.setBackground(new Color(169,169,169));
	    GridBagConstraints c = new GridBagConstraints();//Constraints for products being added
	    
	    
	    
	    
	    
	    JPanel panel2 = new JPanel();//panel for "Locked" label
	    panel2.setBackground(new Color(169,169,169));
	    
	    JPanel panel3 = new JPanel();//panel for "Start" and "Okay" buttons
	    panel3.setBackground(new Color(169,169,169));
	    
	    
	    //Adding Pad top left
	    JPanel panel4=new JPanel(); //Top Left Pad
	    panel4.setBackground(new Color(169,169,169));
	    c.fill=GridBagConstraints.HORIZONTAL;
	    c.gridx=0;
	    c.gridy=0;
	    c.gridwidth = 1;	    
	    c.ipady=350;
	    c.ipadx=100;
	    panel1.add(panel4,c);
	    
	    JLabel empL= new JLabel("Released Employees");
	    c.fill = GridBagConstraints.HORIZONTAL;
	    c.ipady = 10;      //make this component tall
	    c.ipadx= 50;
	    c.gridwidth = 1;  
	    c.gridx = 0;
	    c.gridy = 1;	    
	    panel1.add(empL,c);
	    
	    
	    //Adding ScrolPane	    
	    String [] empNames={"John Snow","Bill","John","Cathy","Hernando","Da Vinci","Riario","Amerigo"};
	    employeeList1= new JList(myEnt.empList(myEnt.getPastEmployees()));
	    employeeList1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    empScroll= new JScrollPane(employeeList1);	    
	    
	    c.fill = GridBagConstraints.HORIZONTAL;
	    c.ipady = 80;      //make this component tall
	    c.ipadx= 50;
	    c.gridwidth = 1;  
	    c.gridx = 0;
	    c.gridy = 2;
	    panel1.add(empScroll, c);
	    
	    JButton button;
	    button = new JButton("Released");
	    button.setBackground(new Color(47,92,180));
	    c.fill = GridBagConstraints.HORIZONTAL;
	    c.ipady = 20;      //make this component tall
	    c.ipadx= 50;
	    c.weightx = 0.0;
	    c.gridwidth = 1;
	    c.gridheight= 1;	    		 
	    c.gridx = 0;
	    c.gridy = 3;
	    button.addActionListener(this);
	    panel1.add(button, c);
	    
	    /*-------------------------*/
	    JLabel empL1= new JLabel("Currently Employed Employees");
	    c.fill = GridBagConstraints.HORIZONTAL;
	    c.ipady = 10;      //make this component tall
	    c.ipadx= 50;
	    c.gridwidth = 1;  
	    c.gridx = 0;
	    c.gridy = 4;
	    
	    panel1.add(empL1,c);
	    
	    
	    //Adding ScrolPane	    
	    String [] empNames1={"John Snow","Bill","John","Cathy","Hernando","Da Vinci","Riario","Amerigo"};
	    employeeList= new JList(myEnt.empList(myEnt.getEmployees()));
	    //employeeList= new JList(empNames);
	    employeeList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    empScroll= new JScrollPane(employeeList);	    
	    
	    c.fill = GridBagConstraints.HORIZONTAL;
	    c.ipady = 80;      //make this component tall
	    c.ipadx= 50;
	    c.gridwidth = 1;  
	    c.gridx = 0;
	    c.gridy = 5;
	    panel1.add(empScroll, c);
	    
	    
	    JButton button1;
	    button1 = new JButton("Get Employee Details");
	    button1.setBackground(new Color(47,92,180));
	    c.fill = GridBagConstraints.HORIZONTAL;
	    c.ipady = 20;      //make this component tall
	    c.ipadx= 50;
	    c.weightx = 0.0;
	    c.gridwidth = 1;
	    c.gridheight= 1;	    		 
	    c.gridx = 0;
	    c.gridy = 6;
	    button1.addActionListener(this);
	    panel1.add(button1, c);
	     
	    JPanel panel6=new JPanel();//Bottom left panel
		panel6.setBackground(new Color(169,169,169));
		c.fill=GridBagConstraints.HORIZONTAL;
		c.gridx=0;
		c.gridy=7;
		c.gridwidth = 1;
		c.ipady=350;
		c.ipadx=100;
		button=new JButton("Get Oldest Associate");
		button.addActionListener(this);
		panel6.add(button);
	    panel1.add(panel6,c);    
	    
	    empTA= new JTextArea();
	    empTA.setEditable(false);
	    c.fill=GridBagConstraints.HORIZONTAL;
		c.gridx=1;
		c.gridy=0;
		c.gridwidth = 5;
		c.ipady=480;
		c.ipadx=1;
		c.gridwidth = 1;
		c.gridheight=8;
		panel1.add(empTA,c);
	     
		JButton b9 = new JButton("9");
	    b9.setForeground(new Color(169,169,169));
	    b9.setBackground(new Color(169,169,169));
	    b9.setActionCommand("9");
	    b9.addActionListener(this);
	    c.fill=GridBagConstraints.HORIZONTAL;
	    c.gridx=1;
		c.gridy=0;
		c.gridwidth = 1;
		c.gridheight=5;
		c.ipady=500;
		c.ipadx=900;
	    panel1.add(b9,c);
	     	     
	    JButton buttonStart = new JButton("Main");
	    buttonStart = new JButton("Main");
	    buttonStart.setForeground(Color.BLACK);
	    buttonStart.setBackground(Color.GRAY);
	    buttonStart.setActionCommand("Main");
        buttonStart.addActionListener(this);
	    panel3.add(buttonStart);
	     
	    JButton buttonOkay=new JButton("Employee");
	    buttonOkay.setForeground(Color.BLACK);
	    buttonOkay.setBackground(new Color(0,128,0));
	    buttonOkay.setActionCommand("Employee");
	    buttonOkay.addActionListener(this);
	    panel3.add(buttonOkay);
	     
	    JButton customerButton = new JButton("Customers");
	    customerButton = new JButton("Customers");
	    customerButton.setForeground(Color.BLACK);
	    customerButton.setBackground(Color.GRAY);
	    customerButton.setActionCommand("Customers");
	    customerButton.addActionListener(this);
	    panel3.add(customerButton);
	     
	    JButton itemButton = new JButton("Item");
	    customerButton = new JButton("Item");
	    customerButton.setForeground(Color.BLACK);
	    customerButton.setBackground(Color.GRAY);
	    customerButton.setActionCommand("Item");
	    customerButton.addActionListener(this);
	    panel3.add(customerButton);
	     
	    empFrame.add(panel1,BorderLayout.CENTER);//add first panel to the screen
	     
	    lockedLabel = new JLabel("Employee Menu");
	    lockedLabel.setFont(myFont); 
	    lockedLabel.setForeground(Color.RED);
	    lockedLabel.setBackground(Color.BLACK);
	    panel2.add(lockedLabel);
	    empFrame.add(panel2,BorderLayout.NORTH);//add second panel to the screen
	    empFrame.add(panel3,BorderLayout.SOUTH);
	}
	
	/**
	 * 
	 * 
	 * loads Customer Window 
	 */
	public void loadCustomerWindow(){
		customerFrame = new JFrame();    
		customerFrame.setTitle("Mav Bay Auctionhouse");
		customerFrame.setSize(1200,600);//making the size of the window
		customerFrame.setLocation(150,150);//window placed at 100 pixel x 100 pixel
		customerFrame.setVisible(true);//make the window visable
		customerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	       
	    JPanel panel1 = new JPanel();
	    panel1.setLayout(new GridBagLayout());//creating a grid layout for panel one
	    panel1.setBackground(new Color(169,169,169));
	    GridBagConstraints c = new GridBagConstraints();//Constraints for products being added	    
	    
	    JPanel panel2 = new JPanel();//panel for "Locked" label
	    panel2.setBackground(new Color(169,169,169));
	    
	    JPanel panel3 = new JPanel();//panel for "Start" and "Okay" buttons
	    panel3.setBackground(new Color(169,169,169));    
	    
	    //Adding Pad top left
	    JPanel panel4=new JPanel(); //Top Left Pad
	    panel4.setBackground(new Color(169,169,169));
	    c.fill=GridBagConstraints.HORIZONTAL;
	    c.gridx=0;
	    c.gridy=0;
	    c.gridwidth = 1;	    
	    c.ipady=350;
	    c.ipadx=100;
	    panel1.add(panel4,c);
	    
	    JLabel empL= new JLabel("Customers");
	    c.fill = GridBagConstraints.HORIZONTAL;
	    c.ipady = 10;      //make this component tall
	    c.ipadx= 200;
	    c.gridwidth = 1;  
	    c.gridx = 0;
	    c.gridy = 1;	    
	    panel1.add(empL,c);
	    
	    
	    //Adding ScrolPane	    
	    String [] customerNames={"John Snow","Bill","John","Cathy","Hernando","Da Vinci","Riario","Amerigo"};
	    custList= new JList(myEnt.custList(myEnt.getCustomers()));	    
	    custList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    empScroll= new JScrollPane(custList);	    
	    c.fill = GridBagConstraints.HORIZONTAL;
	    c.ipadx= 200;
	    c.ipady=100;
	    c.gridwidth = 1;  
	    c.gridx = 0;
	    c.gridy = 2;
	    panel1.add(empScroll, c);
	    
	    String [] years={"All","2014","2013","2012","2011","2010"};
	    final JComboBox yearCombo1= new JComboBox(years);
	    c.fill = GridBagConstraints.HORIZONTAL;
	    c.ipady = 20;      //make this component tall
	    c.ipadx= 200;
	    c.gridwidth = 1;  
	    c.gridx = 0;
	    c.gridy = 3;
	    panel1.add(yearCombo1, c);
	    
	    JButton button;
	    button = new JButton("Get Bids");
	    button.setBackground(new Color(47,92,180));
	    c.fill = GridBagConstraints.HORIZONTAL;
	    c.ipady = 20;      //make this component tall
	    c.ipadx= 200;
	    c.weightx = 0.0;
	    c.gridwidth = 1;
	    c.gridheight= 1;	    		 
	    c.gridx = 0;
	    c.gridy = 4;
	    button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	custTA.setText("");
            	int index=custList.getSelectedIndex();
            	String myString= (String)yearCombo1.getSelectedItem();
            	custTA.append(myEnt.sCustBids(index, myString));
            }
        });
	    
	    panel1.add(button, c);
	    
	    JButton button1;
	    button1 = new JButton("High/Low");
	    button1.setBackground(new Color(47,92,180));
	    c.fill = GridBagConstraints.HORIZONTAL;
	    c.ipady = 20;      //make this component tall
	    c.ipadx= 200;
	    c.weightx = 0.0;
	    c.gridwidth = 1;
	    c.gridheight= 1;	    		 
	    c.gridx = 0;
	    c.gridy = 5;
	    button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	JFrame frame=new JFrame();
    	    	frame.setSize(250,250);
    	    	String year= (String)yearCombo1.getSelectedItem();
    	    	if(year.equals("All")){
    	    		JOptionPane.showMessageDialog(null,myEnt.sCustMM());
    	    	}else{
    	    		JOptionPane.showMessageDialog(null,myEnt.sCustMM(year));    	    		
    	    	}
    	    	
            }
        });
	    panel1.add(button1, c);
	     
	    JPanel panel6=new JPanel();//Bottom left panel
		panel6.setBackground(new Color(169,169,169));
		c.fill=GridBagConstraints.HORIZONTAL;
		c.gridx=0;
		c.gridy=7;
		c.gridwidth = 1;
		c.ipady=350;
		c.ipadx=200;		
	    panel1.add(panel6,c);    
	    
	    custTA= new JTextArea();
	    custTA.setEditable(false);
	    c.fill=GridBagConstraints.HORIZONTAL;
		c.gridx=1;
		c.gridy=0;
		c.gridwidth = 5;
		c.ipady=480;
		c.ipadx=1;
		c.gridwidth = 1;
		c.gridheight=8;
		panel1.add(custTA,c);
	     
		JButton b9 = new JButton("9");
	    b9.setForeground(new Color(169,169,169));
	    b9.setBackground(new Color(169,169,169));
	    b9.setActionCommand("9");
	    b9.addActionListener(this);
	    c.fill=GridBagConstraints.HORIZONTAL;
	    c.gridx=1;
		c.gridy=0;
		c.gridwidth = 1;
		c.gridheight=5;
		c.ipady=500;
		c.ipadx=900;
	    panel1.add(b9,c);
	     	     
	    JButton buttonStart = new JButton("Main");
	    buttonStart = new JButton("Main");
	    buttonStart.setForeground(Color.BLACK);
	    buttonStart.setBackground(Color.GRAY);
	    buttonStart.setActionCommand("Main");
        buttonStart.addActionListener(this);
	    panel3.add(buttonStart);
	     
	    JButton buttonOkay=new JButton("Employee");
	    buttonOkay.setForeground(Color.BLACK);
	    buttonOkay.setBackground(Color.GRAY);
	    buttonOkay.setActionCommand("Employee");
	    buttonOkay.addActionListener(this);
	    panel3.add(buttonOkay);
	     
	    JButton customerButton = new JButton("Customers");
	    customerButton = new JButton("Customers");
	    customerButton.setForeground(Color.BLACK);
	    customerButton.setBackground(new Color(0,128,0));
	    customerButton.setActionCommand("Customers");
	    customerButton.addActionListener(this);
	    panel3.add(customerButton);
	     
	    JButton itemButton = new JButton("Item");
	    customerButton = new JButton("Item");
	    customerButton.setForeground(Color.BLACK);
	    customerButton.setBackground(Color.GRAY);
	    customerButton.setActionCommand("Item");
	    customerButton.addActionListener(this);
	    panel3.add(customerButton);
	     
	    customerFrame.add(panel1,BorderLayout.CENTER);//add first panel to the screen
	     
	    lockedLabel = new JLabel("Customer Menu");
	    lockedLabel.setFont(myFont); 
	    lockedLabel.setForeground(Color.RED);
	    lockedLabel.setBackground(Color.BLACK);
	    panel2.add(lockedLabel);
	    customerFrame.add(panel2,BorderLayout.NORTH);//add second panel to the screen
	    customerFrame.add(panel3,BorderLayout.SOUTH);
	}
	
	/**
	 * 
	 * 
	 * loads Item Window
	 */
	public void loadItemWindow(){

		
		itemFrame = new JFrame();    
		itemFrame.setTitle("Mav Bay Auctionhouse");
		itemFrame.setSize(1200,600);//making the size of the window
		itemFrame.setLocation(150,150);//window placed at 100 pixel x 100 pixel
		itemFrame.setVisible(true);//make the window visable
		itemFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	       
	    JPanel panel1 = new JPanel();
	    panel1.setLayout(new GridBagLayout());//creating a grid layout for panel one
	    panel1.setBackground(new Color(169,169,169));
	    GridBagConstraints c = new GridBagConstraints();//Constraints for products being added
	    
	    
	    
	    
	    
	    JPanel panel2 = new JPanel();//panel for "Locked" label
	    panel2.setBackground(new Color(169,169,169));
	    
	    JPanel panel3 = new JPanel();//panel for "Start" and "Okay" buttons
	    panel3.setBackground(new Color(169,169,169));
	    
	    
	    //Adding Pad top left
	    JPanel panel4=new JPanel(); //Top Left Pad
	    panel4.setBackground(new Color(169,169,169));
	    c.fill=GridBagConstraints.HORIZONTAL;
	    c.gridx=0;
	    c.gridy=0;
	    c.gridwidth = 1;	    
	    c.ipady=350;
	    c.ipadx=100;
	    panel1.add(panel4,c);
	    
	    JLabel empL= new JLabel("Items Unsold");
	    c.fill = GridBagConstraints.HORIZONTAL;
	    c.ipady = 10;      //make this component tall
	    c.ipadx= 100;
	    c.gridwidth = 1;  
	    c.gridx = 0;
	    c.gridy = 1;	    
	    panel1.add(empL,c);
	    
	    
	    //Adding ScrolPane	    
	    String [] empNames={"John Snow","Bill","John","Cathy","Hernando","Da Vinci","Riario","Amerigo"};
	    itemList= new JList(myEnt.itemList(myEnt.getItems()));
	    itemList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    empScroll= new JScrollPane(itemList);	    
	    
	    c.fill = GridBagConstraints.HORIZONTAL;
	    c.ipady = 80;      //make this component tall
	    c.ipadx= 100;
	    c.gridwidth = 1;  
	    c.gridx = 0;
	    c.gridy = 2;
	    panel1.add(empScroll, c);
	    
	    JButton button;
	    button = new JButton("Get Unsold Item Details");
	    button.setBackground(new Color(47,92,180));
	    c.fill = GridBagConstraints.HORIZONTAL;
	    c.ipady = 20;      //make this component tall
	    c.ipadx= 100;
	    c.weightx = 0.0;
	    c.gridwidth = 1;
	    c.gridheight= 1;	    		 
	    c.gridx = 0;
	    c.gridy = 3;
	    button.addActionListener(this);
	    panel1.add(button, c);
	    
	    /*-------------------------*/
	    JLabel empL1= new JLabel("Sold Items");
	    c.fill = GridBagConstraints.HORIZONTAL;
	    c.ipady = 10;      //make this component tall
	    c.ipadx= 100;
	    c.gridwidth = 1;  
	    c.gridx = 0;
	    c.gridy = 4;	    
	    panel1.add(empL1,c);
	    
	    
	    //Adding ScrolPane	    
	    String [] empNames1={"John Snow","Bill","John","Cathy","Hernando","Da Vinci","Riario","Amerigo"};
	    unSoldItemList= new JList(myEnt.itemList(myEnt.getItemsSold()));
	    unSoldItemList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    empScroll= new JScrollPane(unSoldItemList);	    
	    
	    c.fill = GridBagConstraints.HORIZONTAL;
	    c.ipady = 80;      //make this component tall
	    c.ipadx= 100;
	    c.gridwidth = 1;  
	    c.gridx = 0;
	    c.gridy = 5;
	    panel1.add(empScroll, c);
	    
	    
	    JButton button1;
	    button1 = new JButton("Get Sold Item Details");
	    button1.setBackground(new Color(47,92,180));
	    c.fill = GridBagConstraints.HORIZONTAL;
	    c.ipady = 20;      //make this component tall
	    c.ipadx= 100;
	    c.weightx = 0.0;
	    c.gridwidth = 1;
	    c.gridheight= 1;	    		 
	    c.gridx = 0;
	    c.gridy = 6;
	    button1.addActionListener(this);
	    panel1.add(button1, c);
	     
	    JPanel panel6=new JPanel();//Bottom left panel
		panel6.setBackground(new Color(169,169,169));
		c.fill=GridBagConstraints.HORIZONTAL;
		c.gridx=0;
		c.gridy=7;
		c.gridwidth = 1;
		c.ipady=350;
		c.ipadx=100;
	    panel1.add(panel6,c);    
	    
	    itemTA= new JTextArea();
	    itemTA.setEditable(false);
	    empScroll= new JScrollPane(itemTA);
	    c.fill=GridBagConstraints.HORIZONTAL;
		c.gridx=1;
		c.gridy=0;
		c.gridwidth = 5;
		c.ipady=480;
		c.ipadx=1;
		c.gridwidth = 1;
		c.gridheight=8;
		panel1.add(empScroll,c);
	     
		JButton b9 = new JButton("9");
	    b9.setForeground(new Color(169,169,169));
	    b9.setBackground(new Color(169,169,169));
	    b9.setActionCommand("9");
	    b9.addActionListener(this);
	    c.fill=GridBagConstraints.HORIZONTAL;
	    c.gridx=1;
		c.gridy=0;
		c.gridwidth = 1;
		c.gridheight=5;
		c.ipady=500;
		c.ipadx=900;
	    panel1.add(b9,c);
	     	     
	    JButton buttonStart = new JButton("Main");
	    buttonStart = new JButton("Main");
	    buttonStart.setForeground(Color.BLACK);
	    buttonStart.setBackground(Color.GRAY);
	    buttonStart.setActionCommand("Main");
        buttonStart.addActionListener(this);
	    panel3.add(buttonStart);
	     
	    JButton buttonOkay=new JButton("Employee");
	    buttonOkay.setForeground(Color.BLACK);
	    buttonOkay.setBackground(Color.GRAY);
	    buttonOkay.setActionCommand("Employee");
	    buttonOkay.addActionListener(this);
	    panel3.add(buttonOkay);
	     
	    JButton customerButton = new JButton("Customers");
	    customerButton = new JButton("Customers");
	    customerButton.setForeground(Color.BLACK);
	    customerButton.setBackground(Color.GRAY);
	    customerButton.setActionCommand("Customers");
	    customerButton.addActionListener(this);
	    panel3.add(customerButton);
	     
	    JButton itemButton = new JButton("Item");
	    customerButton = new JButton("Item");
	    customerButton.setForeground(Color.BLACK);
	    customerButton.setBackground(new Color(0,128,0));
	    customerButton.setActionCommand("Item");
	    customerButton.addActionListener(this);
	    panel3.add(customerButton);
	     
	    itemFrame.add(panel1,BorderLayout.CENTER);//add first panel to the screen
	     
	    lockedLabel = new JLabel("Item Menu");
	    lockedLabel.setFont(myFont); 
	    lockedLabel.setForeground(Color.RED);
	    lockedLabel.setBackground(Color.BLACK);
	    panel2.add(lockedLabel);
	    itemFrame.add(panel2,BorderLayout.NORTH);//add second panel to the screen
	    itemFrame.add(panel3,BorderLayout.SOUTH);
	}
	public void actionPerformed(ActionEvent e) {
	    
	    String command = e.getActionCommand();
	    if(command.equals("Get Employee Details")){
	     int index=employeeList.getSelectedIndex();
	     empTA.setText("");
	     empTA.append("------Employee Details-----\n");
	     empTA.append(myEnt.sEmpInfo(index));
	     
	    }
	    if(command.equals("Get Oldest Associate")){
	    	JFrame frame= new JFrame();
	    	frame.setSize(200,200);
	    	JOptionPane.showMessageDialog(null,myEnt.empLength());
	    }
	    
	    if(command.equals("Released")){
	    	int index=employeeList1.getSelectedIndex();
		    String s= (String)employeeList1.getSelectedValue();
		    empTA.setText("");
	        empTA.append("------Employee Details-----\n");
    	    empTA.append(myEnt.sRelEmpInfo(index));
	    }
        if(command.equals("Get Unsold Item Details")){
            int index=itemList.getSelectedIndex();
            itemTA.setText("");
            itemTA.setText(myEnt.sGetItemBids(index));            
        }
        if(command.equals("Get Sold Item Details")){
        	int index=unSoldItemList.getSelectedIndex();
        	itemTA.setText("");
        	itemTA.setText(myEnt.sGetSoldItemBids(index));        	
        }
	    
	    if(command.equals("Main")){	    	
	    	this.empFrame.setVisible(false);
	    	this.customerFrame.setVisible(false);
	    	this.itemFrame.setVisible(false);
	    	this.loadMainWindow();	    	
	    }
	    if(command.equals("Employee")){
	    	this.mainFrame.setVisible(false);
	    	this.customerFrame.setVisible(false);
	    	this.itemFrame.setVisible(false);
	    	this.loadEmployeeWindow();	    	
	    }
	    if(command.equals("Customers")){
	    	this.empFrame.setVisible(false);
	    	this.mainFrame.setVisible(false);
	    	this.itemFrame.setVisible(false);
	    	this.loadCustomerWindow();
	    }
        if(command.equals("Item")){
        	this.empFrame.setVisible(false);
	    	this.mainFrame.setVisible(false);
	    	this.customerFrame.setVisible(false);
	    	this.loadItemWindow();
	    }
        
	    
	    
	  }//end of action performed
	
	
	public static void main(String []args){
		
		Enterprise myEnt= new Enterprise("Dookie and the Crew");
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) {
		    // If Nimbus is not available, fall back to cross-platform
		    try {
		        UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		    } catch (Exception ex) {
		        // not worth my time
		    }
		}
		MainMenu myMenu= new MainMenu(myEnt);
	    
		
	
	}
}
