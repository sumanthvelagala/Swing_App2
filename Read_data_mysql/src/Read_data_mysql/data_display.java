package Read_data_mysql;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class data_display {

	private JFrame frame;
	private JTextField t1;
	private JTextField t2;
	private JTable table;
	private JTextField t4;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					data_display window = new data_display();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public data_display() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(240, 240, 240));
		frame.setBounds(100, 100, 592, 547);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Name");
		lblNewLabel_1.setFont(new Font("Sitka Subheading", Font.BOLD, 16));
		lblNewLabel_1.setBounds(10, 61, 126, 23);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Marks");
		lblNewLabel_2.setFont(new Font("Sitka Subheading", Font.BOLD, 16));
		lblNewLabel_2.setBounds(10, 106, 75, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		t1 = new JTextField();
		t1.setBounds(117, 60, 138, 20);
		frame.getContentPane().add(t1);
		t1.setColumns(10);
		
		t2 = new JTextField();
		t2.setColumns(10);
		t2.setBounds(117, 101, 138, 20);
		frame.getContentPane().add(t2);
		
		JButton btnNewButton = new JButton("Submit\r\n");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String n=t1.getText();
				String m=t2.getText();
				float mark=Float.parseFloat(m);
				try {
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/aiml","root","mrec");
					String q="insert into student values('"+n+"','"+mark+"')";
					Statement sta=con.createStatement();
					sta.execute(q);
					con.close();
					JOptionPane.showMessageDialog(btnNewButton, "Done");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setFont(new Font("Sitka Subheading", Font.BOLD, 16));
		btnNewButton.setBounds(53, 163, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(320, 11, 219, 265);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnNewButton_1 = new JButton("Load");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/aiml","root","mrec");
					String q="select * from student";
					Statement sta=con.createStatement();
					ResultSet rs=sta.executeQuery(q);
					ResultSetMetaData rsmd=rs.getMetaData();
					DefaultTableModel model=(DefaultTableModel) table.getModel();
					int cols=rsmd.getColumnCount();
					String[] colName=new String[cols];
					for(int i=0;i<=cols;i++)
					{
					colName[i]=rsmd.getColumnName(i+1);
					model.setColumnIdentifiers(colName);
					String n1,n2;
					while(rs.next())
					{
					n1=rs.getString(1);
					n2=rs.getString(2);
					String[] row= {n1,n2};
					model.addRow(row);
					
					}
					}
					con.close();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_1.setFont(new Font("Sitka Subheading", Font.BOLD, 16));
		btnNewButton_1.setBounds(320, 305, 89, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("Clear");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table.setModel(new DefaultTableModel());
			}
		});
		btnNewButton_1_1.setFont(new Font("Sitka Subheading", Font.BOLD, 16));
		btnNewButton_1_1.setBounds(450, 305, 89, 23);
		frame.getContentPane().add(btnNewButton_1_1);
		
		t4 = new JTextField();
		t4.setColumns(10);
		t4.setBounds(53, 275, 138, 20);
		frame.getContentPane().add(t4);
		
		JButton btnNewButton_1_2 = new JButton("check");
		btnNewButton_1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String c=t4.getText();
				
			}
		});
		btnNewButton_1_2.setFont(new Font("Sitka Subheading", Font.BOLD, 16));
		btnNewButton_1_2.setBounds(79, 317, 89, 23);
		frame.getContentPane().add(btnNewButton_1_2);
	}
}
