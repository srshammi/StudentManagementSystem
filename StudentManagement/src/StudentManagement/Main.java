package StudentManagement;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.Color;
import java.awt.Window.Type;

public class Main {

	JFrame frmStudentMangement;

	/**
	 * Launch the application.
	 */
	private JLabel lblClock;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					Main window = new Main();
					window.frmStudentMangement.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		
		});
	}
	
	public void clock()
	{
		Thread clock = new  Thread()
		{
			public void run()
			{
				try {
					
					for(;;)
						
					//while(true)
						
					{
						
					Calendar cal = new GregorianCalendar();
					SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy  hh:mm a");  
		            Date date = cal.getTime();  
		            String timeString = formatter.format(date);  
		            lblClock.setText(timeString);  
					//lblClock.setText("Time = "+hour+":"+minute+":"+second+"   Date: "+day+"/"+(month+1)+"/"+year+"");
					
					sleep(1000);
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		
		clock.start();
		
	}

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
		clock();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frmStudentMangement = new JFrame();
		frmStudentMangement.setType(Type.POPUP);
		frmStudentMangement.setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/Image/Logo.jpg")));
		frmStudentMangement.setResizable(false);
		frmStudentMangement.setTitle("Student Mangement");
		frmStudentMangement.setBounds(100, 100, 700, 450);
		frmStudentMangement.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmStudentMangement.getContentPane().setLayout(null);
		
		JButton btnStudentDatabase = new JButton("Student Database");
		btnStudentDatabase.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnStudentDatabase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					
					frmStudentMangement.setVisible(false);
					StudentData frame = new StudentData();
					frame.setVisible(true);
			}
		});
		
		
		btnStudentDatabase.setBounds(261, 110, 175, 69);
		frmStudentMangement.getContentPane().add(btnStudentDatabase);
		
		
		
	
		JButton btnAdminLogin = new JButton("Admin Login");
		btnAdminLogin.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnAdminLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frmStudentMangement.setVisible(false);
				Login frame = new Login();
				frame.setVisible(true);
				
			}
		});
		btnAdminLogin.setBounds(261, 204, 175, 69);
		frmStudentMangement.getContentPane().add(btnAdminLogin);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBounds(261, 296, 175, 69);
		frmStudentMangement.getContentPane().add(btnExit);
		
		JLabel lblNewLabel = new JLabel("Student Management");
		lblNewLabel.setForeground(Color.GRAY);
		lblNewLabel.setFont(new Font("Tw Cen MT", Font.PLAIN, 50));
		lblNewLabel.setBounds(134, 30, 428, 55);
		frmStudentMangement.getContentPane().add(lblNewLabel);
		
		lblClock = new JLabel("Clock");
		lblClock.setForeground(Color.BLUE);
		lblClock.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblClock.setBounds(432, 11, 252, 30);
		frmStudentMangement.getContentPane().add(lblClock);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(0, 0, 694, 421);
		frmStudentMangement.getContentPane().add(lblNewLabel_1);
	}
}
