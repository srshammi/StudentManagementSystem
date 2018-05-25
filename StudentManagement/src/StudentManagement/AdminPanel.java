package StudentManagement;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.sql.Connection;
import net.proteanit.sql.DbUtils;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JComboBox;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.AbstractButton;
import javax.swing.DefaultComboBoxModel;

public class AdminPanel extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JTextField textFieldID;
	private JTextField textFieldName;
	private JTextField textFieldEmail;
	private JTextField textFieldMobile;
	private JTextField textFieldSerial;
	private JTextPane textPaneSearch;

	AbstractButton path;

	/**
	 * Launch the application.
	 */
	Connection conn = null;

	public void refreshTable() {

		try {
			String query = "select * from Students";
			PreparedStatement pst = conn.prepareStatement(query);
			ResultSet rs = pst.executeQuery();

			table.setModel(DbUtils.resultSetToTableModel(rs));

			pst.close();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	JComboBox<String> comboBoxSearch;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminPanel frame = new AdminPanel();
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
	public AdminPanel() {

		conn = SqliteConnection.dbConnector();
		// conn = SQLConnection.GetConnection();

		setIconImage(Toolkit.getDefaultToolkit().getImage(AdminPanel.class.getResource("/Image/Logo.jpg")));
		setResizable(false);

		setTitle("Admin Panel");
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
		button.setBounds(585, 7, 99, 49);
		contentPane.add(button);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

			}
		});
		scrollPane.setBounds(10, 50, 514, 119);
		contentPane.add(scrollPane);

		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		table.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					int row = table.getSelectedRow();
					String Serial_ = (table.getModel().getValueAt(row, 0)).toString();

					String query = "select * from Students where Serial='" + Serial_ + "'";
					PreparedStatement pst = conn.prepareStatement(query);

					ResultSet rs = pst.executeQuery();

					// pst.setString(13, (String)comboBox2.getSelectedItem());

					while (rs.next()) {
						textFieldSerial.setText(rs.getString("Serial"));
						textFieldID.setText(rs.getString("ID"));
						textFieldName.setText(rs.getString("Name"));
						textFieldEmail.setText(rs.getString("Email"));
						textFieldMobile.setText(rs.getString("Mobile"));

					}
					// pst.close();
				}

				catch (Exception ef) {
					ef.printStackTrace();
				}
			}
		});
		scrollPane.setViewportView(table);

		textFieldID = new JTextField();
		textFieldID.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textFieldID.setBounds(304, 199, 205, 34);
		contentPane.add(textFieldID);
		textFieldID.setColumns(10);

		textFieldName = new JTextField();
		textFieldName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textFieldName.setColumns(10);
		textFieldName.setBounds(86, 256, 423, 37);
		contentPane.add(textFieldName);

		textFieldEmail = new JTextField();
		textFieldEmail.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textFieldEmail.setColumns(10);
		textFieldEmail.setBounds(86, 311, 223, 34);
		contentPane.add(textFieldEmail);

		JButton btnNewButton = new JButton("Add");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {

					String query = " insert into Students (Serial,ID,Name,Email,Mobile) values (?,?,?,?,?)";
					PreparedStatement pst = conn.prepareStatement(query);

					if (textFieldSerial.getText().equals("")) {
						pst.setString(1, null);

					} else {
						pst = conn.prepareStatement(query);
						pst.setString(1, textFieldSerial.getText());
					}

					pst.setString(2, textFieldID.getText());
					pst.setString(3, textFieldName.getText());
					pst.setString(4, textFieldEmail.getText());
					pst.setString(5, textFieldMobile.getText());

					pst.execute();
					pst.close();

					JOptionPane.showMessageDialog(null, "Student Information Added Successfully");

				} catch (Exception es) {
					es.printStackTrace();
				}

				refreshTable();
			}
		});
		btnNewButton.setBounds(558, 193, 126, 41);
		contentPane.add(btnNewButton);

		JButton btnEdit = new JButton("Update");
		btnEdit.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {

					String query = "Update Students set Serial ='" + textFieldSerial.getText() + "',ID='"
							+ textFieldID.getText() + "',Name='" + textFieldName.getText() + "',Email='"
							+ textFieldEmail.getText() + "',Mobile='" + textFieldMobile.getText() + "' where Serial ='"
							+ textFieldSerial.getText() + "'";
					PreparedStatement pst = conn.prepareStatement(query);

					pst.execute();

					JOptionPane.showMessageDialog(null, "Student Information Updated Successfully");

					pst.close();

				} catch (Exception es) {
					es.printStackTrace();
				}

				refreshTable();
			}

		});
		btnEdit.setBounds(558, 245, 126, 49);
		contentPane.add(btnEdit);

		JButton btnDelete = new JButton("Delete");
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int action = JOptionPane.showConfirmDialog(null, "Do You Really Want To Delete This Information ?",
						"Delete", JOptionPane.YES_NO_CANCEL_OPTION);
				if (action == 0) {
					try {
						String query = "delete from Students where Serial='" + textFieldSerial.getText() + "'";
						PreparedStatement pst = conn.prepareStatement(query);
						pst.execute();

						JOptionPane.showMessageDialog(null, "Studend Information Deleted Successfully");

						pst.close();
					} catch (Exception ed) {
						ed.printStackTrace();
					}
					refreshTable();
				}
			}

		});
		btnDelete.setBounds(558, 301, 126, 49);
		contentPane.add(btnDelete);

		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(243, 199, 66, 35);
		contentPane.add(lblNewLabel);

		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblName.setBounds(10, 262, 66, 19);
		contentPane.add(lblName);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblEmail.setBounds(10, 323, 42, 22);
		contentPane.add(lblEmail);

		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnRefresh.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				try {
					String query = "select * from Students";
					PreparedStatement pst = conn.prepareStatement(query);
					ResultSet rs = pst.executeQuery();

					table.setModel(DbUtils.resultSetToTableModel(rs));

					textFieldSerial.setText("");
					textFieldID.setText("");
					textFieldName.setText("");
					textFieldEmail.setText("");
					textFieldMobile.setText("");
					textPaneSearch.setText("");

					// pst.close();
					// rs.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		btnRefresh.setBounds(585, 86, 99, 49);
		contentPane.add(btnRefresh);

		JLabel lblMobile = new JLabel("Mobile");
		lblMobile.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblMobile.setBounds(10, 365, 66, 32);
		contentPane.add(lblMobile);

		textFieldMobile = new JTextField();
		textFieldMobile.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textFieldMobile.setColumns(10);
		textFieldMobile.setBounds(86, 364, 223, 41);
		contentPane.add(textFieldMobile);

		JLabel label_1 = new JLabel("Search by");
		label_1.setForeground(Color.BLACK);
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_1.setBounds(10, 7, 107, 30);
		contentPane.add(label_1);

		JComboBox<String> comboBoxSearch = new JComboBox<String>();
		comboBoxSearch.setModel(new DefaultComboBoxModel<String>(new String[] { "Serial", "ID" }));
		comboBoxSearch.addKeyListener(new KeyAdapter() {
			// JTextComponent textPaneSearch;
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					String selection = (String) comboBoxSearch.getSelectedItem();
					String query = "select * from Students where " + selection + "=?";
					System.out.println(query);
					PreparedStatement pst = conn.prepareStatement(query);
					pst.setString(1, textPaneSearch.getText());
					ResultSet rs = pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
					pst.close();

				} catch (Exception ef) {
					ef.printStackTrace();
				}
			}
		});
		comboBoxSearch.setToolTipText("");
		comboBoxSearch.setFont(new Font("Tahoma", Font.PLAIN, 18));
		comboBoxSearch.setBounds(127, 7, 118, 31);
		contentPane.add(comboBoxSearch);

		textPaneSearch = new JTextPane();
		textPaneSearch.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textPaneSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {

				try {

					String selection = (String) comboBoxSearch.getSelectedItem();
					String query = "select * from Students where " + selection + "=?";
					System.out.println(query);
					PreparedStatement pst = conn.prepareStatement(query);
					pst.setString(1, textPaneSearch.getText());
					ResultSet rs = pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
					pst.close();

				} catch (Exception ef) {
					ef.printStackTrace();
				}

			}

		});
		textPaneSearch.setBounds(274, 7, 143, 32);
		contentPane.add(textPaneSearch);

		JLabel lblSerial = new JLabel("Serial");
		lblSerial.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblSerial.setBounds(10, 199, 66, 35);
		contentPane.add(lblSerial);

		textFieldSerial = new JTextField();
		textFieldSerial.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textFieldSerial.setColumns(10);
		textFieldSerial.setBounds(86, 199, 118, 37);
		contentPane.add(textFieldSerial);

		JButton button_1 = new JButton("Exit");
		button_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		button_1.setBounds(586, 371, 98, 34);
		contentPane.add(button_1);
	}
}
