import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.ButtonGroup;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Rectangle;
import java.awt.print.PrinterException;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import java.awt.Toolkit;
;

public class AddRecord extends JFrame {

	static Connection objConn;			
	static PreparedStatement objPst;
	static ResultSet objRs = null;
	static Statement objSQLQuery; 
	static String objSql;
	
	static private JPanel contentPane;
	static private JPanel pnlInfo ;
	static private JTextField txtID;
	static private JTextField txtFName;
	static private JTextField txtLName;
	static private String strGender;
	static private JTextField txtNum;
	static private JTextField txtPerVis;
	static private JTextField txtPurpose;
	static private JTextField txtTimeIn;
	static private JTextField txtTimeOut;
	static private JTextField txtDate;

	static private JLabel lblTitleInfo;
	static private JLabel lblTitleGate;
	static private JLabel lblGender;
	static private JLabel lblTimeDate;
	
	static private ButtonGroup btnBg;
	static private JRadioButton rdbtnMale;
	static private JRadioButton rdbtnFemale;
	static private JButton btnAdd;     
	static private JButton btnClear;
	static private JButton btnShow;
	static private JButton btnBack;
	static DefaultTableModel tblModel;
	static private JTable table;
	private	static	 JButton btnPrints;
	private static JButton btnRecipt;
	private JTabbedPane tabbedPane;
	private JPanel pnlPrint;
	private JPanel pnlTable;
	private JScrollPane scrollPane2;
	private JScrollPane scrollPane;
	private static JTextArea txtArea;

	
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try {
					AddRecord frame = new AddRecord();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public AddRecord() {
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(AddRecord.class.getResource("/imgs/vLogo.png")));

		setUndecorated(true);// if true may exit and minimize tab
		setBackground(Color.WHITE);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 1077, 640);
		contentPane = new JPanel();
		contentPane.setBounds(new Rectangle(5, 0, 0, 0));
		contentPane.setBackground(new Color(126,46,36));
		contentPane.setBorder(new LineBorder(new Color(0, 0, 28), 1, true));  // 235 192 27-yellow  // 0 0 128-darkblue //  rgb-to-thickness
		setLocationRelativeTo(null); 	// set window tab in center
		setContentPane(contentPane);
		contentPane.setLayout(null);  
		
		pnlInfo = new JPanel();
		pnlInfo.setBackground(new Color(255,255,240));
		pnlInfo.setBounds(0, 34, 1077, 606);
		contentPane.add(pnlInfo);
		pnlInfo.setLayout(null);
		
		lblTitleInfo = new JLabel("INFORMATION");
		lblTitleInfo.setBounds(53, 30, 130, 26);
		pnlInfo.add(lblTitleInfo);
		lblTitleInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitleInfo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		lblTitleGate = new JLabel("VISITORS");
		lblTitleGate.setBounds(97, 10, 50, 21);
		pnlInfo.add(lblTitleGate);
		lblTitleGate.setFont(new Font("Nirmala UI", Font.BOLD, 10));
	
		txtID = new JTextField();
		txtID.setBounds(32, 67, 180, 26);
		pnlInfo.add(txtID);
		txtID.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtID.setText("ID Number");
		txtID.setColumns(10);
		
		txtFName = new JTextField();
		txtFName.setBounds(32, 102, 180, 26);
		pnlInfo.add(txtFName);
		txtFName.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtFName.setText("First Name");
		txtFName.setColumns(10);
		
		txtLName = new JTextField();
		txtLName.setBounds(32, 138, 179, 26);
		pnlInfo.add(txtLName);
		txtLName.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtLName.setColumns(10);
		txtLName.setText("Last Name");
		
		lblGender = new JLabel("Gender:");
		lblGender.setBounds(32, 174, 50, 10);
		pnlInfo.add(lblGender);
		lblGender.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		rdbtnMale = new JRadioButton("Male");
		rdbtnMale.setBackground(new Color(255,255,240));
		rdbtnMale.setFont(new Font("Tahoma", Font.PLAIN, 9));
		rdbtnMale.setBounds(79, 169, 55, 21);
		pnlInfo.add(rdbtnMale);
	
	    rdbtnFemale = new JRadioButton("Female");
	    rdbtnFemale.setBackground(new Color(255,255,240));
	    rdbtnFemale.setFont(new Font("Tahoma", Font.PLAIN, 9));
		rdbtnFemale.setBounds(136, 169, 76, 21);
		pnlInfo.add(rdbtnFemale);
		
        btnBg = new ButtonGroup();
		btnBg.add(rdbtnMale);
		btnBg.add(rdbtnFemale);
		
		txtNum = new JTextField();
		txtNum.setBounds(32, 194, 179, 26);
		pnlInfo.add(txtNum);
		txtNum.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtNum.setText("Mobile Number");
		txtNum.setColumns(10);
		
		txtPerVis = new JTextField();
		txtPerVis.setBounds(32, 230, 179, 26);
		pnlInfo.add(txtPerVis);
		txtPerVis.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtPerVis.setText("Person to Visit");
		txtPerVis.setColumns(10);
		
		txtPurpose = new JTextField();
		txtPurpose.setBounds(32, 266, 179, 26);
		pnlInfo.add(txtPurpose);
		txtPurpose.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtPurpose.setText("Purpose of Visit");
		txtPurpose.setColumns(10);
	
		txtTimeIn = new JTextField();
		txtTimeIn.setText("Time in");
		txtTimeIn.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtTimeIn.setColumns(10);
		txtTimeIn.setBounds(32, 302, 179, 26);
		pnlInfo.add(txtTimeIn);
		
		txtTimeOut = new JTextField();
		txtTimeOut.setText("Time out");
		txtTimeOut.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtTimeOut.setColumns(10);
		txtTimeOut.setBounds(33, 338, 179, 26);
		pnlInfo.add(txtTimeOut);
		
		txtDate = new JTextField();
		txtDate.setBounds(33, 374, 179, 26);
		pnlInfo.add(txtDate);
		txtDate.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtDate.setText("Date");
		txtDate.setColumns(10);
		
		btnAdd = new JButton("Add");
		
		btnAdd.setBounds(32, 420, 81, 21);
		btnAdd.setBackground(new Color(255,255,255));
		pnlInfo.add(btnAdd);
		
		btnClear = new JButton("Clear");		//clear inserted infos register
		
		btnClear.setBounds(32, 504, 180, 21);
		btnClear.setBackground(new Color(255,255,255));
		pnlInfo.add(btnClear);
		
		btnShow = new JButton("Show Records");
		
		btnShow.setBounds(32, 477, 180, 21);
		btnShow.setBackground(new Color(255,255,255));
		pnlInfo.add(btnShow);
		
		btnBack = new JButton("Back");
		btnBack.setBounds(140, 546, 72, 21);
		btnBack.setBackground(new Color(255,255,255));
		btnBack.addActionListener(new ActionListener() 
		{	
			public void actionPerformed(ActionEvent e)
			{	
				Admin frame = new Admin(); // admin
				dispose();
			}
		});
		pnlInfo.add(btnBack);
		
		btnPrints = new JButton("Print");
		btnPrints.setBackground(Color.WHITE);
		btnPrints.setBounds(131, 420, 81, 21);
		pnlInfo.add(btnPrints);
		
		btnRecipt = new JButton("Show Print");
		btnRecipt.setBackground(Color.WHITE);
		btnRecipt.setBounds(32, 451, 179, 21);
		pnlInfo.add(btnRecipt);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(new Color(255,255,240));
		tabbedPane.setForeground(Color.BLACK);
		tabbedPane.setFont(new Font("Lato", Font.PLAIN, 10));
		tabbedPane.setBounds(238, 30, 813, 538);
		pnlInfo.add(tabbedPane);
		
		pnlTable = new JPanel();
		tabbedPane.addTab("records", null, pnlTable, null);
		tabbedPane.setBackgroundAt(0, Color.YELLOW);
		pnlTable.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 808, 512);
		pnlTable.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setEnabled(false);
		table.setFillsViewportHeight(true);
		table.setSelectionBackground(Color.DARK_GRAY);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null, null, null, null},
			},
			new String[] {
				"ID NUMBER", "FIRST NAME", "LAST NAME", "GENDER", "MOBILE NUMBER", "PERSON TO VISIT", "PURPOSE OF VISIT", "TIME-IN", "TIME-OUT", "DATE"
			}
		));
		
		pnlPrint = new JPanel();
		tabbedPane.addTab("print", null, pnlPrint, null);
		tabbedPane.setBackgroundAt(1, new Color(255,255,240));
		pnlPrint.setLayout(null);
		
		scrollPane2 = new JScrollPane();
		scrollPane2.setBounds(297, 5, 2, 2);
		scrollPane2.setBackground(Color.WHITE);
		pnlPrint.add(scrollPane2);
		
		txtArea = new JTextArea();
		txtArea.setBounds(198, 89, 459, 310);
		txtArea.setWrapStyleWord(true);
		txtArea.setFont(new Font("Arial", Font.PLAIN, 12));
		txtArea.setEditable(false);
		pnlPrint.add(txtArea);
		
		lblTimeDate = new JLabel("");
		lblTimeDate.setBounds(881, 11, 170, 26);
		pnlInfo.add(lblTimeDate);
		
		curDateTime();
		setupListeners();
		btnActions();
	}
	
		static void curDateTime() {
			
			Thread clock = new Thread()
			{
				public void run()
				{
					try {
					
						while(true) {
							
							Calendar cal = new GregorianCalendar();
							int intDay = cal.get(Calendar.DAY_OF_MONTH);
							int intMonth=cal.get(Calendar.MONTH)+1; 
							int intYear = cal.get(Calendar.YEAR);
							int intSecond = cal.get(Calendar.SECOND);
							int intMinute = cal.get(Calendar.MINUTE);
							int intHour = cal.get(Calendar.HOUR);

							lblTimeDate.setText("Time "+intHour+":"+intMinute+":"+intSecond+ " " + " " +	
									"Date "+intYear+"/"+intMonth+"/"+intDay); 
							sleep(1000);
					}
					
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			};

			clock.start();
		}
	
		static Connection con() {
			try {
							
				String strDriver = "com.mysql.cj.jdbc.Driver";
				String strUrl = "jdbc:mysql://localhost:3306/dbVisitPass";
				Class.forName(strDriver);
				System.out.println("connected to records successfully");
				
				return DriverManager.getConnection(strUrl, "admin1","Admpw2021"); 
				
			}	catch(Exception objE) {
				System.out.println("Connection failed" + objE);
			}			
				return null;			
		}
		
		static void SaveToDatabase() { // add button
			
			
			Connection objConn = con();

			if(txtID.getText().equals("ID Number") || txtFName.getText().equals("ID Number") || txtLName.getText().equals("Last Name") || txtNum.getText().equals("Mobile Number") ||
			  (txtPerVis.getText().equals("Person to Visit") || txtPurpose.getText().equals("Purpose of	Visit")  || txtTimeIn.getText().equals("Time in") || txtDate.getText().equals("Date")));
			{
				JOptionPane.showMessageDialog(null,"Please fill all informations");
			}
			
			try {	

			String objSql = "INSERT INTO tblInfo VALUES (?,?,?,?,?,?,?,?,?,?)";
			objPst = objConn.prepareStatement(objSql);
			
			objPst.setInt(1, Integer.parseInt(txtID.getText()));
			objPst.setString(2, txtFName.getText());
			objPst.setString(3, txtLName.getText());					
			objPst.setString(4, strGender); //gender
			objPst.setString(5, txtNum.getText());
			objPst.setString(6, txtPerVis.getText());
			objPst.setString(7, txtPurpose.getText());
			objPst.setString(8, txtTimeIn.getText());
			objPst.setString(9, txtTimeOut.getText());
			objPst.setString(10,txtDate.getText());
			
			objPst.executeUpdate();
		
			JOptionPane.showMessageDialog(null,"Saved sucessfully");
			
			}	catch(Exception objE) {
				System.out.printf("ERROR Saving failed " +objE);
			}
		}
		
		static void ShowRecords() {
			
			Connection objConn = con();
			tblModel = new DefaultTableModel();
			
			tblModel.addColumn("ID NUMBER");
			tblModel.addColumn("FIRST NAME");
			tblModel.addColumn("LAST NAME");
			tblModel.addColumn("GENDER");
			tblModel.addColumn("MOBILE NUMBER");
			tblModel.addColumn("PERSON TO VISIT");
			tblModel.addColumn("PURPOSE OF VISIT");
			tblModel.addColumn("TIME-IN");
			tblModel.addColumn("TIME-OUT");
			tblModel.addColumn("DATE");
			
			try {	

		    objSql = "SELECT * FROM tblInfo";
			objSQLQuery = objConn.createStatement();
			objRs = objSQLQuery.executeQuery(objSql);
			
				while(objRs.next())
				{
					tblModel.addRow(new Object[]
					{
						objRs.getString("IDNumber"),	
						objRs.getString("FirstName"),
						objRs.getString("LastName"),
						objRs.getString("Gender"),
						objRs.getString("MobileNumber"),
						objRs.getString("PersonToVisit"),
						objRs.getString("PurposeOfVisit"),
						objRs.getString("TimeIn"),
						objRs.getString("TimeOut"),
						objRs.getString("Date")
					});
				}
				
				objRs.close();
				objSQLQuery.close();
				objConn.close();
				
				table.setModel(tblModel);
				table.setAutoResizeMode(0);
				table.getColumnModel().getColumn(0).setPreferredWidth(90);
				table.getColumnModel().getColumn(1).setPreferredWidth(90);
				table.getColumnModel().getColumn(2).setPreferredWidth(90);
				table.getColumnModel().getColumn(3).setPreferredWidth(70);
				table.getColumnModel().getColumn(4).setPreferredWidth(110);
				table.getColumnModel().getColumn(5).setPreferredWidth(120);
				table.getColumnModel().getColumn(6).setPreferredWidth(120);
				table.getColumnModel().getColumn(7).setPreferredWidth(75);
				table.getColumnModel().getColumn(8).setPreferredWidth(75);
				table.getColumnModel().getColumn(9).setPreferredWidth(145);
				table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);	
				
			} catch(Exception objE) {
			 System.out.println("Failed to load data" + objE);
			}
		}
		
		static void btnActions() {
			
			btnAdd.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent e)
				{
					SaveToDatabase();			
				}
			});	//btnadd end
			
			btnClear.addActionListener(new ActionListener() // clear infos
			{
				public void actionPerformed(ActionEvent e)
				{
					
					 txtArea.setText(null);
					txtID.setText("ID number");
					txtFName.setText("First Name");
					txtLName.setText("Last Name");
					btnBg.clearSelection(); //btnclear
					txtNum.setText("Mobile Number");
					txtPerVis.setText("Person to Visit");
					txtPurpose.setText("Purpose of Visit");
					txtTimeIn.setText("Time in");
					txtTimeOut.setText("Time out");
					txtDate.setText("Date");
				}
			}); //btClear end
			
			btnShow.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e)
				{
					ShowRecords();
				}
			}); // btnShow end
			
			btnRecipt.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
	
					txtArea.append("\n\n\t\t\n" +
					("---------------------------------------------VISITOR BADGE-------------------------------------------------------"+"\n\n" +
					"ID Number:\t\t" + txtID.getText()+ "\n\n" +
					"Name:\t\t" + txtFName.getText()+" "+txtLName.getText()+"\n\n"+
					
					"Purpose of Visit:\t" + txtPurpose.getText()+ "\n\n"+
					
					"Date:\t\t" + txtDate.getText()+	"\n\n\n" +
					"Signiture:_______________________"+" \n\n\n" +
					"Admin Signiture:_______________________"+ "\n\n" +
					".............................................Please return badge when leaving......................................" + "\n\n"));
			
				}
			}); //btnPass end
			
			btnPrints.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
				
						try {
							txtArea.print();
						} catch (PrinterException e1) {
					
							e1.printStackTrace();
						}			
				}			
			}); //btnprint end
			
		}
		
		static void setupListeners() {

	
		txtID.addFocusListener(new FocusAdapter() 
		{
			@Override
				public void focusGained(FocusEvent e)	
				{
				
					if(txtID.getText().equals("ID Number"))
					{
						txtID.setText("");
					}
					else
					{
					txtID.selectAll();
					}
				}
			@Override
				public void focusLost(FocusEvent e) 
				{
				
					if(txtID.getText().equals(""))
					{
					txtID.setText("ID Number");
					}
				}
		}); //txtID end
		
		txtFName.addFocusListener(new FocusAdapter()
		{
			@Override
				public void focusGained(FocusEvent e1)
				{
			
					if(txtFName.getText().equals("First Name"))
					{
						txtFName.setText(""); 
					}
					else
					{
						txtFName.selectAll(); 
					}
				}	
			@Override
				public void focusLost(FocusEvent e1)
				{
					if(txtFName.getText().equals(""))
					{
						txtFName.setText("First Name"); 
					}
				}					
		});	//txtFname end
		
		txtLName.addFocusListener(new FocusAdapter() 
		{
			@Override
				public void focusGained(FocusEvent e2)
				{
				
					if(txtLName.getText().equals("Last Name"))
					{
						txtLName.setText("");
					}
					else
					{
						txtLName.selectAll();
					}			
				}
				@Override
				public void focusLost(FocusEvent e2) 
				{	
					
					if(txtLName.getText().equals(""))
					{
					txtLName.setText("Last Name");
					}
				}
		});	//txtLname end
		
		txtNum.addFocusListener(new FocusAdapter() 
			{
				@Override
				public void focusGained(FocusEvent e3)
				{
					
					if(txtNum.getText().equals("Mobile Number"))
					{
						txtNum.setText("");
					}
					else
					{
						txtNum.selectAll();
					}		
				}
				@Override
					public void focusLost(FocusEvent e3) 
					{
			
					if(txtNum.getText().equals(""))
					{
						txtNum.setText("Mobile Number");
					}
				}
		}); //txtNum end
		
		txtPerVis.addFocusListener(new FocusAdapter() 
		{
			@Override
				public void focusGained(FocusEvent e4)
				{
					
					if(txtPerVis.getText().equals("Person to Visit"))
					{
						txtPerVis.setText("");
					}
					else
					{
						txtPerVis.selectAll();
					}
					
				}
			@Override
			public void focusLost(FocusEvent e4) 
			{
				
				if(txtPerVis.getText().equals(""))
				{
					txtPerVis.setText("Person to Visit");
				}
				
			}
		});	//txtPerVis end
		
		txtPurpose.addFocusListener(new FocusAdapter() 
		{		
			@Override
			public void focusGained(FocusEvent e5) {
				
				if(txtPurpose.getText().equals("Purpose of Visit"))
				{
					txtPurpose.setText("");
				}
				else
				{
					txtPurpose.selectAll();
				}
				
			}
			@Override
			public void focusLost(FocusEvent e5) {
				
				if(txtPurpose.getText().equals(""))
				{
					txtPurpose.setText("Purpose of Visit");
				}
				
			}
		});	//txtPurpose end
		
		txtDate.addFocusListener(new FocusAdapter() 
		{
			@Override
			public void focusGained(FocusEvent e6) {
				
				if(txtDate.getText().equals("Date"))
				{
					txtDate.setText("");
				}
				else
				{
					txtDate.selectAll();
				}
				
			}
			@Override
			public void focusLost(FocusEvent e6) {
				
				if(txtDate.getText().equals(""))
				{
					txtDate.setText("Date");
				}
			}
		}); //txtDate end	
		
		rdbtnMale.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) {
				strGender = "Male"; 
			}
		}); //rdbtnMale end
		
		rdbtnFemale.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				strGender = "Female";
			}
		}); //rdbtnFemale end
		
		txtTimeIn.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
			
				
				if(txtTimeIn.getText().equals("Time in"))
				{
					txtTimeIn.setText("");
				}
				else
				{
					txtTimeIn.selectAll();
				}		
				
			}
			@Override
			public void focusLost(FocusEvent e) {
				
				if(txtTimeIn.getText().equals(""))
				{
					txtTimeIn.setText("Time in");
				}
				
			}
		}); // txtTimein end
		
		txtTimeOut.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				
				if(txtTimeOut.getText().equals("Time out"))
				{
					txtTimeOut.setText("");
				}
				else
				{
					txtTimeOut.selectAll();
				}
			}
		}); //txtTime Out end
	
	} //setupListeners end
}
