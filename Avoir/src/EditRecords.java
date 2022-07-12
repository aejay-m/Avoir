import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import java.sql.Statement;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import java.util.Vector;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Toolkit;


public class EditRecords extends JFrame {

	static Connection objConn;			
	static PreparedStatement objPst;
	static ResultSet objRs = null;
	static Statement objSQLQuery; 
	static String objSql;
	static int strvalue,deleteItem,q,i;
	
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
	static private JLabel lblTitleV;
	static private JLabel lblGender;

	static private ButtonGroup btnBg;
	static private JRadioButton rdbtnMale;
	static private JRadioButton rdbtnFemale;
	static private JButton btnDelete;
	static private JButton btnClear;
	static private JButton btnBack;
	static private JTable tblRecords;
	static private JScrollPane scrollPane;
	static DefaultTableModel tblDefault;


	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try {
					EditRecords frame = new EditRecords();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public EditRecords() {
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(EditRecords.class.getResource("/imgs/vLogo.png")));
		setResizable(false);

		setUndecorated(true);
		setBackground(Color.WHITE);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1063, 640);
		
		contentPane = new JPanel();
		contentPane.setBounds(new Rectangle(5, 0, 0, 0));
		contentPane.setBackground(new Color(126,46,36));
		contentPane.setBorder(new LineBorder(new Color(0, 0, 28), 1, true));  
		setLocationRelativeTo(null); 	
		setContentPane(contentPane);
		contentPane.setLayout(null);  
		
		pnlInfo = new JPanel();
		pnlInfo.setBackground(new Color(255,255,240));
		pnlInfo.setBounds(0, 34, 1063, 606);
		contentPane.add(pnlInfo);
		pnlInfo.setLayout(null);
		
		lblTitleInfo = new JLabel("RECORDS");
		lblTitleInfo.setBounds(61, 40, 130, 26);
		pnlInfo.add(lblTitleInfo);
		lblTitleInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitleInfo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		lblTitleV = new JLabel("VISITORS");
		lblTitleV.setBounds(100, 20, 50, 21);
		pnlInfo.add(lblTitleV);
		lblTitleV.setFont(new Font("Nirmala UI", Font.BOLD, 10));
	
		txtID = new JTextField();
		txtID.setBounds(32, 76, 180, 26);
		pnlInfo.add(txtID);
		txtID.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtID.setColumns(10);
		
		txtFName = new JTextField();
		txtFName.setBounds(32, 112, 180, 26);
		pnlInfo.add(txtFName);
		txtFName.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtFName.setColumns(10);
		
		txtLName = new JTextField();
		txtLName.setBounds(32, 148, 179, 26);
		pnlInfo.add(txtLName);
		txtLName.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtLName.setColumns(10);
		
		lblGender = new JLabel("Gender:");
		lblGender.setBounds(32, 184, 50, 10);
		pnlInfo.add(lblGender);
		lblGender.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		rdbtnMale = new JRadioButton("Male");
		rdbtnMale.setBackground(new Color(255,255,240));
		rdbtnMale.setFont(new Font("Tahoma", Font.PLAIN, 9));
		rdbtnMale.setBounds(79, 179, 55, 21);
		pnlInfo.add(rdbtnMale);
	
	    rdbtnFemale = new JRadioButton("Female");
	    rdbtnFemale.setBackground(new Color(255,255,240));
	    rdbtnFemale.setFont(new Font("Tahoma", Font.PLAIN, 9));
		rdbtnFemale.setBounds(136, 179, 76, 21);
		pnlInfo.add(rdbtnFemale);
		
        btnBg = new ButtonGroup();
		btnBg.add(rdbtnMale);
		btnBg.add(rdbtnFemale);
		
		txtNum = new JTextField();
		txtNum.setBounds(32, 204, 179, 26);
		pnlInfo.add(txtNum);
		txtNum.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtNum.setColumns(10);
		
		txtPerVis = new JTextField();
		txtPerVis.setBounds(32, 240, 179, 26);
		pnlInfo.add(txtPerVis);
		txtPerVis.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtPerVis.setColumns(10);
		
		txtPurpose = new JTextField();
		txtPurpose.setBounds(32, 276, 179, 26);
		pnlInfo.add(txtPurpose);
		txtPurpose.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtPurpose.setColumns(10);
	
		txtTimeIn = new JTextField();
		txtTimeIn.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtTimeIn.setColumns(10);
		txtTimeIn.setBounds(32, 312, 179, 26);
		pnlInfo.add(txtTimeIn);
		
		txtTimeOut = new JTextField();
		txtTimeOut.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtTimeOut.setColumns(10);
		txtTimeOut.setBounds(33, 348, 179, 26);
		pnlInfo.add(txtTimeOut);
		
		txtDate = new JTextField();
		txtDate.setBounds(33, 384, 179, 26);
		pnlInfo.add(txtDate);
		txtDate.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtDate.setColumns(10);
		
		btnDelete = new JButton("Delete");
		btnDelete.setBounds(32, 434, 175, 21);
		btnDelete.setBackground(new Color(255,255,255));
		pnlInfo.add(btnDelete);
		
		btnClear = new JButton("Clear");		
		btnClear.setBounds(32, 465, 175, 21);
		btnClear.setBackground(new Color(255,255,255));
		pnlInfo.add(btnClear);
		
		btnBack = new JButton("Back");
		btnBack.setBounds(135, 547, 72, 21);
		btnBack.setBackground(new Color(255,255,255));
		btnBack.addActionListener(new ActionListener() 
		{	
			public void actionPerformed(ActionEvent e)
			{	
				Admin frame = new Admin(); 
				dispose();
			}
		});
		pnlInfo.add(btnBack);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(245, 48, 785, 516);
		pnlInfo.add(scrollPane);
		
		tblRecords = new JTable();		
		scrollPane.setViewportView(tblRecords);
		
		btnActions();	
		ShowRecords(); 
	}
	
	
	static void update() {
	
		try {		
				Connection objConn = con();
				objPst = objConn.prepareStatement("select * from tblInfo");
				
				objRs = objPst.executeQuery();
				ResultSetMetaData stData = objRs.getMetaData();
				
				q = stData.getColumnCount();
				tblDefault = (DefaultTableModel)tblRecords.getModel();
				tblDefault.setRowCount(0);
					
				while(objRs.next())
				{
			
					Vector<String> columnData = new Vector<String>();
					for(i = 1; i <=q; i++)
					{
						columnData.add(objRs.getString("IDNumber"));
						columnData.add(objRs.getString("FirstName"));
						columnData.add(objRs.getString("LastName"));
						columnData.add(objRs.getString("Gender"));
						columnData.add(objRs.getString("MobileNumber"));
						columnData.add(objRs.getString("PersonToVisit"));
						columnData.add(objRs.getString("PurposeOfVisit"));
						columnData.add(objRs.getString("TimeIn"));
						columnData.add(objRs.getString("TimeIn"));
						columnData.add(objRs.getString("Date"));

					}
					tblDefault.addRow(columnData);

				}
				
	}catch(Exception objE) {
	 System.out.println("Failed to delete data" + objE);
	}				  	
	
	}
		
	static void ShowRecords() {
		
		Connection objConn = con();
		tblDefault = new DefaultTableModel();
		
		tblDefault.addColumn("ID NUMBER");
		tblDefault.addColumn("FIRST NAME");
		tblDefault.addColumn("LAST NAME");
		tblDefault.addColumn("GENDER");
		tblDefault.addColumn("MOBILE NUMBER");
		tblDefault.addColumn("PERSON TO VISIT");
		tblDefault.addColumn("PURPOSE OF VISIT");
		tblDefault.addColumn("TIME-IN");
		tblDefault.addColumn("TIME-OUT");
		tblDefault.addColumn("DATE");
		
		try {	

	    objSql = "SELECT * FROM tblInfo";
		objSQLQuery = objConn.createStatement();
		objRs = objSQLQuery.executeQuery(objSql);
		
			while(objRs.next())
			{
				tblDefault.addRow(new Object[]
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
			
			tblRecords.setModel(tblDefault);
			tblRecords.setAutoResizeMode(0);
			tblRecords.getColumnModel().getColumn(0).setPreferredWidth(90);
			tblRecords.getColumnModel().getColumn(1).setPreferredWidth(90);
			tblRecords.getColumnModel().getColumn(2).setPreferredWidth(90);
			tblRecords.getColumnModel().getColumn(3).setPreferredWidth(70);
			tblRecords.getColumnModel().getColumn(4).setPreferredWidth(110);
			tblRecords.getColumnModel().getColumn(5).setPreferredWidth(170);
			tblRecords.getColumnModel().getColumn(6).setPreferredWidth(170);
			tblRecords.getColumnModel().getColumn(7).setPreferredWidth(75);
			tblRecords.getColumnModel().getColumn(8).setPreferredWidth(75);
			tblRecords.getColumnModel().getColumn(9).setPreferredWidth(100);
			tblRecords.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);	
			
		} catch(Exception objE) {
		 System.out.println("Failed to load data" + objE);
		}
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
		
		
		static void btnActions() {
			
			btnClear.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent e)
				{
					txtID.setText("");
					txtFName.setText("");
					txtLName.setText("");
					btnBg.clearSelection();
					txtNum.setText("");
					txtPerVis.setText("");
					txtPurpose.setText("");
					txtTimeIn.setText("");
					txtTimeOut.setText("");
					txtDate.setText("");
				}
			}); //btClear end
				
			btnDelete.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent e) {
							
					Connection objConn = con();	
					
					tblDefault = (DefaultTableModel)tblRecords.getModel();
					int selectedRows = tblRecords.getSelectedRow();
					
					if(txtID.getText().isEmpty())
					{
						JOptionPane.showMessageDialog(null,"Field is empty, select a column");
					}
					else
					try {
							
							strvalue = Integer.parseInt(tblDefault.getValueAt(selectedRows, 0).toString());
							
							deleteItem = JOptionPane.showConfirmDialog(null, "Confirm if you want to delete item",
																	    	    "Warning",JOptionPane.YES_NO_OPTION);		
								if(deleteItem == JOptionPane.YES_OPTION)
								{		
									objPst = objConn.prepareStatement("delete from tblInfo where IDNumber=?"); // ID Number changed
									objPst.setInt(1, Integer.parseInt(txtID.getText()));
									objPst.executeUpdate(); 
									JOptionPane.showMessageDialog(null,"Deleted Sucessfully");
									JOptionPane.showMessageDialog(null,"Click the 'clear button' and select a column to delete again");
			
									update();						
									objConn.close();
									
								}
											
					}catch(Exception objE) {
					 System.out.println("Failed to delete data" + objE);
					}
					
				}
			}); //delete end
			
			tblRecords.addMouseListener(new MouseAdapter()
			{
				@Override
				public void mouseClicked(MouseEvent e) {
					
					tblDefault = (DefaultTableModel)tblRecords.getModel();
					int row = tblRecords.getSelectedRow();
					
					txtID.setText(tblDefault.getValueAt(row, 0).toString());
					txtFName.setText(tblDefault.getValueAt(row, 1).toString());
					txtLName.setText(tblDefault.getValueAt(row, 2).toString());
					strGender = tblDefault.getValueAt(row,3).toString();
					
						if(strGender.equals("Male")) {
							rdbtnMale.setSelected(true);
						}
						else {
							rdbtnFemale.setSelected(true);
						}
						
					txtNum.setText(tblDefault.getValueAt(row, 4).toString());
					txtPerVis.setText(tblDefault.getValueAt(row, 5).toString());
					txtPurpose.setText(tblDefault.getValueAt(row, 6).toString());
					txtTimeIn.setText(tblDefault.getValueAt(row, 7).toString());
					txtTimeOut.setText(tblDefault.getValueAt(row, 8).toString());
					txtDate.setText(tblDefault.getValueAt(row, 9).toString());
					}
			}); //tblRecords end
		
		}		
}

