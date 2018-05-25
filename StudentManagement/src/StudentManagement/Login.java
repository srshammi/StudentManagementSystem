package StudentManagement;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.sql.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Login extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldUsername;
	private JPasswordField passwordField;
	private JButton button;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	
	Connection conn=null;
	private JButton btnBack;
	private JTextField textFieldUN;
	private JPasswordField passwordField_1;
	
	
	public Login() {
		
		conn = SqliteConnection.dbConnector();
		//conn = SQLConnection.ConnecrDb();
		
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/Image/Logo.jpg")));
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textFieldUsername = new JTextField();
		textFieldUsername.setForeground(Color.BLACK);
		textFieldUsername.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textFieldUsername.setColumns(10);
		textFieldUsername.setBounds(349, 131, 164, 52);
		contentPane.add(textFieldUsername);
		
		passwordField = new JPasswordField();
		passwordField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode()==KeyEvent.VK_ENTER){
			        
					button.doClick();
				}
			}
		});
		passwordField.setToolTipText("");
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		passwordField.setBounds(349, 198, 164, 42);
		contentPane.add(passwordField);
		
		button = new JButton("Login");
		button.setFont(new Font("Siyam Rupali", Font.PLAIN, 18));
		button.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				try{
					String query="select * from Admin where Username=? and Password=?";
					
					PreparedStatement pst=conn.prepareStatement(query);
					pst.setString(1,textFieldUsername.getText());
					pst.setString(2,passwordField.getText());
					
					ResultSet rs = pst.executeQuery();
					int count = 0;
					while(rs.next()){
						count=count+1;
					}
					if(count == 1)
					{
						//JOptionPane.showMessageDialog(null,"Logged in successfully");
						
						
						AdminPanel frame = new AdminPanel();
						frame.setVisible(true);
						dispose();
						
						
					}
					else if (count > 1)
					{
						JOptionPane.showMessageDialog(null,"Duplicate username and Password");
					}
					else
					{
						JOptionPane.showMessageDialog(null,"Username and Password is not Correct");
					}
					
					rs.close();
					pst.close();
					
					
					
				}catch(Exception e1)
				{
					JOptionPane.showMessageDialog(null,e1);
					
				}
				
				

			}
		});
		button.setBounds(385, 260, 114, 60);
		contentPane.add(button);
		
		btnBack = new JButton("Back");
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Main window = new Main();
				window.frmStudentMangement.setVisible(true);
				dispose();
				
			}
		});
		btnBack.setBounds(563, 21, 105, 31);
		contentPane.add(btnBack);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(Login.class.getResource("/Image/UP.jpg")));
		label.setBounds(52, 123, 256, 197);
		contentPane.add(label);
		
		JButton buttonSignup = new JButton("Sign up");
		buttonSignup.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				
				try{
					String query = "insert into Admin (Username,Password) values (?,?)";
					PreparedStatement pst = conn.prepareStatement(query);
					pst.setString(1, textFieldUN.getText());
					pst.setString(2, passwordField_1.getText());
					pst.execute();
					pst.close();
					JOptionPane.showMessageDialog(null, "Signup Successfully");
					textFieldUN.setText(null);
					passwordField_1.setText(null);
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				
			}
		});
		buttonSignup.setFont(new Font("Siyam Rupali", Font.PLAIN, 18));
		buttonSignup.setBounds(399, 21, 114, 31);
		contentPane.add(buttonSignup);
		
		textFieldUN = new JTextField();
		textFieldUN.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textFieldUN.setColumns(10);
		textFieldUN.setBounds(65, 21, 164, 31);
		contentPane.add(textFieldUN);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setToolTipText("");
		passwordField_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		passwordField_1.setBounds(251, 21, 130, 31);
		contentPane.add(passwordField_1);
	}


}
