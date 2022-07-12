import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class FrameLogin extends JFrame {

	Connection objConn = null;				
	PreparedStatement objPst = null;
	ResultSet objRs = null;
	Statement objSQLQuery; 

	static private Image img_usr = new ImageIcon(FrameLogin.class.getResource("imgs/user.png")).getImage().getScaledInstance(25,25, Image.SCALE_SMOOTH);
	static private Image img_passwordarrow	 = new ImageIcon(FrameLogin.class.getResource("imgs/passwordarrow.png")).getImage().getScaledInstance(25,25, Image.SCALE_SMOOTH);
	static private Image img_vlogo = new ImageIcon(FrameLogin.class.getResource("imgs/vLogo.png")).getImage().getScaledInstance(300,425, Image.SCALE_SMOOTH);
	
	static private JPanel contentPane;
	static private JPanel pnlBg; 
	static private JLabel lblTitle;
	static private JLabel lblIconUsr;
	static private JLabel lblIconPw; 
	static private JLabel lblLogin;
	static private JLabel lblLogo;
	
	static private JTextField txtUsn;
	static private JPasswordField txtPassword;
	static private JCheckBox chckbxShowPw;
	static private JPanel pnlBtnLogIn;

	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run()
			{
				try {
					FrameLogin frame = new FrameLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	} 
	
	
	public FrameLogin() {
		
				setResizable(false);
				setTitle("Avoir");
			
				setUndecorated(false);		
				setBackground(Color.WHITE);
				setVisible(true);
			
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				setBounds(100, 100, 631, 374);
				contentPane = 	new JPanel();
				contentPane.setBackground(Color.WHITE); 
				contentPane.setBorder(null);  
	
				setLocationRelativeTo(null); 
				setContentPane(contentPane);
				contentPane.setLayout(null);	
		
				pnlBg = new JPanel();
				pnlBg.setBorder(null);
				pnlBg.setBackground(new Color(255, 255, 240));
				pnlBg.setBounds(0, 0, 322, 346);
				contentPane.add(pnlBg);
				pnlBg.setLayout(null);
			
				lblTitle = new JLabel("MEMBER LOGIN");
				lblTitle.setBounds(77, 44, 188, 23);
				pnlBg.add(lblTitle);
				lblTitle.setFont(new Font("Leelawadee", Font.BOLD, 20));
				lblTitle.setForeground(Color.BLACK);
		
				txtUsn = new JTextField("");
				txtUsn.setBounds(66, 124, 199, 29);
				pnlBg.add(txtUsn);
				txtUsn.setFont(new Font("Verdana", Font.PLAIN, 12));
				txtUsn.setForeground(Color.BLACK);
				txtUsn.setBackground(new Color(255, 255, 240));
			
				txtUsn.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.BLACK));
				txtUsn.setText("Username ");
				txtUsn.setColumns(10);
				
				txtPassword = new JPasswordField("");
				txtPassword.setBounds(65, 197, 199, 29);
				pnlBg.add(txtPassword);
				txtPassword.setForeground(Color.BLACK);
				txtPassword.setBackground(new Color(255, 255, 240));
		
				txtPassword.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 0, 0)));
				txtPassword.setFont(new Font("Arial",Font.PLAIN,12));
				txtPassword.setText("password");
					
				lblIconUsr = new JLabel("");
				lblIconUsr.setBounds(31, 122, 25, 30);
				pnlBg.add(lblIconUsr);
				lblIconUsr.setIcon(new ImageIcon(img_usr));
					
				lblIconPw = new JLabel("");
				lblIconPw.setBounds(31, 196, 25, 30);
				pnlBg.add(lblIconPw);
				lblIconPw.setForeground(Color.BLACK);
				lblIconPw.setIcon(new ImageIcon(img_passwordarrow));
				
				chckbxShowPw = new JCheckBox("Show Password");
				chckbxShowPw.setBounds(45, 242, 145, 21);
				pnlBg.add(chckbxShowPw);
				chckbxShowPw.setFont(new Font("Verdana", Font.PLAIN, 11));
				chckbxShowPw.setForeground(Color.DARK_GRAY);
			    chckbxShowPw.setOpaque(false); 
					
				pnlBtnLogIn = new JPanel();
				pnlBtnLogIn.setBounds(209, 276, 69, 29);
				pnlBg.add(pnlBtnLogIn);
				pnlBtnLogIn.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
				pnlBtnLogIn.setBackground(new Color(55,60,61));
				
				lblLogin = new JLabel("LOG IN");
				lblLogin.setForeground(Color.WHITE);
				lblLogin.setFont(new Font("Verdana", Font.PLAIN, 12));
				lblLogin.setBounds(10, 8, 50, 13);
				pnlBtnLogIn.add(lblLogin);
	
				lblLogo = new JLabel("");
				lblLogo.setBounds(318, 0, 309, 346);
				lblLogo.setIcon(new ImageIcon(img_vlogo));
				contentPane.add(lblLogo);
			
				pnlBtnLogIn.addMouseListener(new MouseAdapter() 
				{	
						@SuppressWarnings("deprecation")
						@Override
						public void mouseClicked(MouseEvent e) {
							
							try { 
								Class.forName("com.mysql.cj.jdbc.Driver");
								objConn	= DriverManager.getConnection("jdbc:mysql://localhost:3306/dbVisitPass","admin1","Admpw2021"); // user in mysql database
								objSQLQuery = objConn.createStatement();
								String objSql = "SELECT * FROM tblogin WHERE Username ='"+txtUsn.getText()+"' and Password = '"+txtPassword.getText().toString()+"'"; //username and password sa table ng mysql workbench 
								objRs = objSQLQuery.executeQuery(objSql);		
								
								if(objRs.next())	
								{
									System.out.println("Database Successfully connected");
									JOptionPane.showMessageDialog(null,"Login Successfully");
							
									Admin frmAdmin =  new Admin(); 
									frmAdmin.setVisible(true);
									dispose();
								}
								else
								{
									JOptionPane.showMessageDialog(null,"Username or password didn't match");
								
								}
								objConn.close();
							}catch(Exception objE) {
								System.out.println(objE);
							}
						
							
						}
					});
					pnlBtnLogIn.setLayout(null);	
					
					setUpListeners();
}
	
		static void setUpListeners() {
		
		txtUsn.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) 
			{
				if(txtUsn.getText().equals("Username"))
				{
					txtUsn.setText("");
					
				}
				else
				{
					txtUsn.selectAll();
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(txtUsn.getText().equals(""))
				{
					txtUsn.setText("Username");
				}
			}
		});		//txtUsn end
		
		txtPassword.addFocusListener(new FocusAdapter() 
			{
			@SuppressWarnings("deprecation")
			@Override
			public void focusGained(FocusEvent e)
			{
				
				if (txtPassword.getText().equals("Password"))
				{
				txtPassword.setEchoChar('●');
				txtPassword.setText("");
			    }
				else
				{
					txtPassword.selectAll();
				}
				
			}			
			@SuppressWarnings("deprecation")
			@Override
			public void focusLost(FocusEvent arg0) {
				if(txtPassword.getText().equals(""))
				{
					txtPassword.setText("Password");
					txtPassword.setEchoChar((char)0);
				}
			}
		}); //txtPassword end
		
		chckbxShowPw.addActionListener(new ActionListener(){	
			public void actionPerformed(ActionEvent e)
				{
					if(chckbxShowPw.isSelected())
					 {
						txtPassword.setEchoChar((char)0);
					 }
					else
					 {
						txtPassword.setEchoChar('●');
					 } 
						
				}
		}); //checkbxShowPw end
		
	} //setUpListers end
}	//FrameLogin

			
