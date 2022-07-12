import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;


import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import javax.swing.border.MatteBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import java.awt.Toolkit;

public class Admin extends JFrame {

	private static Image img_adm = new ImageIcon(Admin.class.getResource("imgs/admin.png")).getImage().getScaledInstance(37,35, Image.SCALE_SMOOTH);

	private static	JPanel contentPane;
	private static JLabel lblIconAdmin;
	private static JLabel lblAdminT;
	private static JButton btnLogOut;
	private static JPanel pnlBoard;
	private static JPanel panelEdit;
	private static JButton btnEdit;
	private static JPanel pnlView; 
	private static JButton btnView;
	private static JLabel lblTitle;
	private static JPanel pnlAdd;
	private static JButton btnAdd;
	private static JPanel pnlPrint;
	private static JButton btnPrint;
	private static JLabel lblTimeDate;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Admin frame = new Admin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

		
	public Admin() {
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(Admin.class.getResource("/imgs/vLogo.png")));
		setResizable(false);
		
		setUndecorated(true);
		setBackground(Color.WHITE);
		setVisible(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 739, 440);
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(126,46,36));
		contentPane.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(0, 0, 0)));  
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
			pnlBoard = new JPanel();
			pnlBoard.setBackground(new Color(255,255,240));
			pnlBoard.setForeground(Color.BLACK);
			pnlBoard.setBounds(0, 35, 739, 405);
			contentPane.add(pnlBoard);
			pnlBoard.setLayout(null);
			
			panelEdit = new JPanel();
			panelEdit.setBorder(new LineBorder(new Color(0, 0, 0)));
			panelEdit.setLayout(null);
			panelEdit.setBackground(Color.WHITE);
			panelEdit.setBounds(238, 224, 194, 120);
			pnlBoard.add(panelEdit);
			
			btnEdit = new JButton("Delete Records");
			btnEdit.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e) {

					EditRecords frameEdit = new EditRecords(); 
					frameEdit.setVisible(true);
					dispose();
				}
			});
			btnEdit.setForeground(Color.WHITE);
			btnEdit.setFont(new Font("Lato", Font.PLAIN, 11));
			btnEdit.setBackground(new Color(126, 46, 36));
			btnEdit.setBounds(36, 72, 113, 25);
			panelEdit.add(btnEdit);
			
			lblTitle = new JLabel("VISTOR PASS");
			lblTitle.setFont(new Font("Lato", Font.BOLD, 24));
			lblTitle.setForeground(Color.BLACK);
			lblTitle.setToolTipText("");
			lblTitle.setBounds(34, 161, 162, 37);
			pnlBoard.add(lblTitle);
			
		    pnlAdd = new JPanel();
			pnlAdd.setBounds(238, 56, 194, 120);
			pnlBoard.add(pnlAdd);
			pnlAdd.setBorder(new LineBorder(new Color(0, 0, 0)));
			pnlAdd.setBackground(Color.WHITE);
			pnlAdd.setLayout(null);
			
			btnAdd = new JButton("Add record");
			btnAdd.setBounds(39, 68, 113, 25);
			pnlAdd.add(btnAdd);
			btnAdd.setForeground(Color.WHITE);
			btnAdd.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent e) {

					AddRecord frame = new AddRecord(); //back button
					frame.setVisible(true);
					dispose(); 		
				}
			});
			btnAdd.setFont(new Font("Lato", Font.PLAIN, 12));
			btnAdd.setBackground(new Color(126,46,36));
			
			pnlPrint = new JPanel();
			pnlPrint.setBounds(455, 224, 194, 120);
			pnlBoard.add(pnlPrint);
			pnlPrint.setBorder(new LineBorder(new Color(0, 0, 0)));
			pnlPrint.setLayout(null);
			pnlPrint.setBackground(Color.WHITE);
			
			 btnPrint = new JButton("Print Records"); 
			 btnPrint.addActionListener(new ActionListener() 
			 {
			 	public void actionPerformed(ActionEvent e) {
			 		
					PrintRecord framePrint = new PrintRecord(); 
					framePrint.setVisible(true);
					dispose();
			 	}
			 });
			btnPrint.setForeground(Color.WHITE);
			btnPrint.setFont(new Font("Lato", Font.PLAIN, 12));
			btnPrint.setBackground(new Color(126, 46, 36));
			btnPrint.setBounds(40, 72, 113, 25);
			pnlPrint.add(btnPrint);
			
			lblTimeDate = new JLabel("");
			lblTimeDate.setFont(new Font("Lato", Font.PLAIN, 12));
			lblTimeDate.setBounds(29, 205, 187, 26);
			pnlBoard.add(lblTimeDate);
			
			pnlView = new JPanel();
			pnlView.setBounds(455, 56, 194, 114);
			pnlBoard.add(pnlView);
			pnlView.setBorder(new LineBorder(new Color(0, 0, 0)));
			pnlView.setLayout(null);
			pnlView.setBackground(Color.WHITE);
			
			btnView = new JButton("View Records");
			btnView.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent e) {
				
				ViewRecord frameView = new  ViewRecord(); //back button
				frameView.setVisible(true);
				dispose();
				}
			});
			btnView.setForeground(Color.WHITE);
			btnView.setFont(new Font("Lato", Font.PLAIN, 12));
			btnView.setBackground(new Color(126, 46, 36));
			btnView.setBounds(38, 68, 113, 25);
			pnlView.add(btnView);
			
			btnLogOut = new JButton("Logout");
			btnLogOut.setBounds(660, 379, 79, 26);
			pnlBoard.add(btnLogOut);
			btnLogOut.setForeground(Color.WHITE);
			btnLogOut.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent e) {
				
					if(JOptionPane.showConfirmDialog(null, "Are you sure you want to sign out?") == 0)
					{
						FrameLogin framelogin = new FrameLogin();
						framelogin.setVisible(true);
						Admin.this.dispose();							
					}
			
				}
			});
			btnLogOut.setFont(new Font("Verdana", Font.PLAIN, 10));
			btnLogOut.setBackground(new Color(126,46,36));
			
			lblIconAdmin = new JLabel("");
			lblIconAdmin.setBounds(1, 0, 49, 35);
			contentPane.add(lblIconAdmin);
			lblIconAdmin.setHorizontalAlignment(SwingConstants.CENTER);
			lblIconAdmin.setIcon(new ImageIcon(img_adm));
			
			lblAdminT = new JLabel("ADMIN");
			lblAdminT.setBounds(45, 8, 62, 23);
			contentPane.add(lblAdminT);
			lblAdminT.setForeground(Color.WHITE);
			lblAdminT.setFont(new Font("Verdana", Font.BOLD, 12));
			lblAdminT.setBackground(Color.WHITE);
			lblAdminT.setHorizontalAlignment(SwingConstants.CENTER);
			
			curDateTime();
			
	}
	
	
		
	static void curDateTime() {
		
		Thread clock = new Thread()
		{
			public void run()
			{
				try {
				
					while(true) 
					{
						
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
}

