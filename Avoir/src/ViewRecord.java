import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JFrame;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Toolkit;



public class ViewRecord extends JFrame {
	
	static Connection objConn;			
	static PreparedStatement objPst;
	static ResultSet objRs = null;
	static Statement objSQLQuery; 
	static String objSql;
	
	static private JPanel contentPane;
	static private JScrollPane scrollPane;

	static DefaultTableModel tblModel;
	static private JTable table;
	static private JLabel lblTitleRe;
	static private JLabel lblTimeDate;
	static private JButton btnBack;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewRecord frame = new ViewRecord();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ViewRecord() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ViewRecord.class.getResource("/imgs/vLogo.png")));
		
		addWindowListener(new WindowAdapter() 
		{
			@Override
			public void windowOpened(WindowEvent e) 
			{
				ShowRecords();
			}
		});
			
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 966, 527);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(126,46,36));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setUndecorated(true);	
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 48, 966, 426);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setFillsViewportHeight(true);
		table.setEnabled(false);
		scrollPane.setViewportView(table);
		table.setBackground(new Color(255,255,240));
		
		lblTitleRe = new JLabel("VISITORS RECORDS");
		lblTitleRe.setForeground(Color.WHITE);
		lblTitleRe.setFont(new Font("Lato", Font.PLAIN, 17));
		lblTitleRe.setBounds(393, 10, 175, 28);
		contentPane.add(lblTitleRe);
		
		JButton btnNewButton = new JButton("Back");
		btnNewButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				Admin frame = new Admin(); // admin
				dispose();
			}
		});
		
		btnNewButton.setBackground(new Color(55,60,61));
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnNewButton.setBounds(10, 490, 85, 21);
		contentPane.add(btnNewButton);
		
		lblTimeDate = new JLabel("");
		lblTimeDate.setForeground(Color.WHITE);
		lblTimeDate.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTimeDate.setBounds(786, 500, 170, 26);
		contentPane.add(lblTimeDate);
		
		setLocationRelativeTo(null);
		curDateTime();
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
			
			}catch(Exception objE) {
			System.out.println("Connection failed" + objE);
			}			
			return null;			
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
}//View record class end









