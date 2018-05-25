package StudentManagement;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.proteanit.sql.DbUtils;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JComboBox;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.DefaultComboBoxModel;

public class StudentData extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public JTextField textFieldID0;
	JTextField textFieldName0;
	JTextField textFieldEmail0;
	JTextField textFieldMobile0;
	JTextField textFieldSerial0;
	JTextPane textPaneSearch0;
	private JTable table0;
	private JComboBox<String> comboBoxSearch00;

	Connection conn = null;

	public void refreshTable() {

		try {
			String query = "select * from Students";
			PreparedStatement pst = conn.prepareStatement(query);
			ResultSet rs = pst.executeQuery();

			table0.setModel(DbUtils.resultSetToTableModel(rs));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void fillComboBox()

	{

		try {
			String query = "select ID from Students";
			PreparedStatement pst = conn.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				comboBoxSearch00.addItem(rs.getString("ID"));
			}
		} catch (Exception e) {
			e.printStackTrace();

		}

		refreshTable();
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentData frame = new StudentData();
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
	public StudentData() {
		initialize();

	}

	private void initialize() {

		conn = SqliteConnection.dbConnector();
		// conn = SQLConnection.GetConnection();

		setIconImage(Toolkit.getDefaultToolkit().getImage(StudentData.class.getResource("/Image/Logo.jpg")));
		setResizable(false);
		setTitle("Student Data");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton button = new JButton("Back");
		button.setFont(new Font("Tahoma", Font.PLAIN, 18));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main window = new Main();
				window.frmStudentMangement.setVisible(true);
				dispose();
			}
		});
		button.setBounds(568, 10, 106, 31);
		contentPane.add(button);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 56, 526, 133);
		contentPane.add(scrollPane);

		table0 = new JTable();
		table0.setFont(new Font("Tahoma", Font.PLAIN, 14));
		table0.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					int row = table0.getSelectedRow();
					String Serial_ = (table0.getModel().getValueAt(row, 0)).toString();

					String query = "select * from Students where Serial='" + Serial_ + "'";
					PreparedStatement pst = conn.prepareStatement(query);

					ResultSet rs = pst.executeQuery();

					while (rs.next()) {
						textFieldSerial0.setText(rs.getString("Serial"));
						textFieldID0.setText(rs.getString("ID"));
						textFieldName0.setText(rs.getString("Name"));
						textFieldEmail0.setText(rs.getString("Email"));
						textFieldMobile0.setText(rs.getString("Mobile"));

					}
					pst.close();
				}

				catch (Exception ef) {
					ef.printStackTrace();
				}
			}

		});
		scrollPane.setViewportView(table0);

		textFieldID0 = new JTextField();
		textFieldID0.setEditable(false);
		textFieldID0.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textFieldID0.setColumns(10);
		textFieldID0.setBounds(324, 200, 212, 43);
		contentPane.add(textFieldID0);

		textFieldName0 = new JTextField();
		textFieldName0.setBounds(113, 266, 451, 52);
		contentPane.add(textFieldName0);
		textFieldName0.setEditable(false);
		textFieldName0.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textFieldName0.setColumns(10);

		textFieldEmail0 = new JTextField();
		textFieldEmail0.setBounds(113, 336, 225, 56);
		contentPane.add(textFieldEmail0);
		textFieldEmail0.setEditable(false);
		textFieldEmail0.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textFieldEmail0.setColumns(10);

		JLabel label = new JLabel("ID");
		label.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label.setBounds(295, 209, 19, 22);
		contentPane.add(label);

		JLabel label_1 = new JLabel("Name");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_1.setBounds(20, 272, 45, 22);
		contentPane.add(label_1);

		JLabel label_6 = new JLabel("Email");
		label_6.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_6.setBounds(24, 353, 42, 22);
		contentPane.add(label_6);

		JButton buttonRefresh0 = new JButton("Refresh");
		buttonRefresh0.setFont(new Font("Tahoma", Font.PLAIN, 18));
		buttonRefresh0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query = "select * from Students";
					PreparedStatement pst = conn.prepareStatement(query);
					ResultSet rs = pst.executeQuery();

					table0.setModel(DbUtils.resultSetToTableModel(rs));

					textFieldSerial0.setText("");
					textFieldID0.setText("");
					textFieldName0.setText("");
					textFieldEmail0.setText("");
					textFieldMobile0.setText("");
					textPaneSearch0.setText("");

					pst.close();
					rs.close();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		buttonRefresh0.setBounds(568, 52, 106, 31);
		contentPane.add(buttonRefresh0);

		JLabel label_10 = new JLabel("Mobile");
		label_10.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_10.setBounds(349, 352, 46, 20);
		contentPane.add(label_10);

		textFieldMobile0 = new JTextField();
		textFieldMobile0.setBounds(405, 334, 159, 56);
		contentPane.add(textFieldMobile0);
		textFieldMobile0.setEditable(false);
		textFieldMobile0.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textFieldMobile0.setColumns(10);

		JLabel label_12 = new JLabel("Search by");
		label_12.setForeground(Color.BLACK);
		label_12.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_12.setBounds(10, 14, 77, 22);
		contentPane.add(label_12);

		JComboBox<String> comboBoxSearch0 = new JComboBox<String>();
		comboBoxSearch0.addKeyListener(new KeyAdapter() {
			AbstractButton textPaneSearch0;

			@Override
			public void keyReleased(KeyEvent e) {

				try {

					String selection = (String) comboBoxSearch0.getSelectedItem();
					String query = "select * from Students where " + selection + "=?";
					System.out.println(query);
					PreparedStatement pst = conn.prepareStatement(query);
					pst.setString(0, textPaneSearch0.getText());
					ResultSet rs = pst.executeQuery();
					table0.setModel(DbUtils.resultSetToTableModel(rs));
					pst.close();

				} catch (Exception ef) {
					ef.printStackTrace();
				}

			}
		});
		comboBoxSearch0.setModel(new DefaultComboBoxModel<String>(new String[] { "Serial", "ID" }));
		comboBoxSearch0.setToolTipText("");
		comboBoxSearch0.setFont(new Font("Tahoma", Font.PLAIN, 18));
		comboBoxSearch0.setBounds(113, 11, 118, 31);
		contentPane.add(comboBoxSearch0);

		textPaneSearch0 = new JTextPane();
		textPaneSearch0.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textPaneSearch0.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {

				try {

					String selection = (String) comboBoxSearch0.getSelectedItem();
					String query = "select * from Students where " + selection + "=?";
					System.out.println(query);
					PreparedStatement pst = conn.prepareStatement(query);
					pst.setString(1, textPaneSearch0.getText());
					ResultSet rs = pst.executeQuery();
					table0.setModel(DbUtils.resultSetToTableModel(rs));
					pst.close();

				} catch (Exception ef) {
					ef.printStackTrace();
				}
			}
		});
		textPaneSearch0.setBounds(263, 12, 149, 29);
		contentPane.add(textPaneSearch0);

		JLabel label_13 = new JLabel("Selection Box");
		label_13.setForeground(Color.BLACK);
		label_13.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_13.setBounds(568, 116, 106, 22);
		contentPane.add(label_13);

		comboBoxSearch00 = new JComboBox<String>();
		comboBoxSearch00.setFont(new Font("Tahoma", Font.PLAIN, 18));
		comboBoxSearch00.setModel(new DefaultComboBoxModel<String>(new String[] { "Select ID" }));
		comboBoxSearch00.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e1) {
				try {
					String query = "select * from Students Where ID=?";
					PreparedStatement pst = conn.prepareStatement(query);
					pst.setString(1, (String) comboBoxSearch00.getSelectedItem());
					ResultSet rs = pst.executeQuery();

					while (rs.next()) {

						textFieldSerial0.setText(rs.getString("Serial"));
						textFieldID0.setText(rs.getString("ID"));
						textFieldName0.setText(rs.getString("Name"));
						textFieldEmail0.setText(rs.getString("Email"));
						textFieldMobile0.setText(rs.getString("Mobile"));

					}
					pst.close();

				} catch (Exception ef) {
					ef.printStackTrace();
				}
			}
		});
		comboBoxSearch00.setToolTipText("");
		comboBoxSearch00.setBounds(558, 149, 126, 31);
		contentPane.add(comboBoxSearch00);

		JLabel label_14 = new JLabel("Serial");
		label_14.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_14.setBounds(23, 207, 42, 22);
		contentPane.add(label_14);

		textFieldSerial0 = new JTextField();
		textFieldSerial0.setBounds(113, 200, 111, 43);
		contentPane.add(textFieldSerial0);
		textFieldSerial0.setEditable(false);
		textFieldSerial0.setFont(new Font("Tahoma", Font.ITALIC, 18));
		textFieldSerial0.setColumns(10);

		JButton buttonExit = new JButton("Exit");
		buttonExit.setFont(new Font("Tahoma", Font.PLAIN, 18));
		buttonExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		buttonExit.setBounds(604, 367, 70, 43);
		contentPane.add(buttonExit);
		fillComboBox();
	}
}
