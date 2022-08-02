package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.CustomerController;
import model.Customer;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;

public class LoginGui extends JFrame {

	private JPanel contentPane;
	private JTextField textField_id;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginGui frame = new LoginGui();
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
	public LoginGui() {
		
		setTitle("Fairy Online Shopping");
		setIconImage(Toolkit.getDefaultToolkit().getImage("img\\download.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 873, 539);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("FAIRY");
		lblNewLabel.setForeground(new Color(255, 255, 0));
		lblNewLabel.setFont(new Font("Segoe Print", Font.PLAIN, 45));
		lblNewLabel.setBounds(357, 128, 160, 94);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("FAIRY");
		lblNewLabel_1.setFont(new Font("Segoe Print", Font.BOLD, 45));
		lblNewLabel_1.setBounds(357, 115, 160, 121);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Welcome To");
		lblNewLabel_2.setFont(new Font("MV Boli", Font.BOLD, 20));
		lblNewLabel_2.setBounds(197, 77, 126, 77);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("ONLINE SHOPPING");
		lblNewLabel_3.setFont(new Font("MV Boli", Font.BOLD, 20));
		lblNewLabel_3.setBounds(552, 169, 207, 33);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("ID:");
		lblNewLabel_4.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_4.setBounds(310, 290, 45, 50);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("PASSWORD:");
		lblNewLabel_5.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_5.setBounds(223, 350, 114, 24);
		contentPane.add(lblNewLabel_5);
		
		textField_id = new JTextField();
		textField_id.setBounds(347, 308, 133, 19);
		contentPane.add(textField_id);
		textField_id.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(347, 350, 133, 19);
		contentPane.add(passwordField);
		
		JButton btnNewButton = new JButton("LOGIN");
		btnNewButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{

				String txtId=textField_id.getText();
				int id=0;
				String password="";
				try {
					id=Integer.parseInt(txtId);
					password=new String(passwordField.getPassword());
				} catch (NumberFormatException e1) {
					// TODO: handle exception
					//System.err.println("Error in parseInt");
				}
				
				
			
				Customer customer=new Customer();
				customer.setId(id);
				customer.setPassword(password);
			
				
				CustomerController customerController=new CustomerController();
				
				
				try {
					boolean success=customerController.login(customer);
					
					if(success) {
						ProductCategoriesGui frame = new ProductCategoriesGui(customer.getId());
						frame.setVisible(true);
						dispose();
					}
					else 
						JOptionPane.showMessageDialog(btnNewButton,"You need to sign up first.");
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				
			
			}
		});
		btnNewButton.setBounds(285, 401, 85, 21);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("SIGN UP");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserRegisterGui frame = new UserRegisterGui();
				frame.setVisible(true);
				
				dispose();
			}
		});
		btnNewButton_1.setBounds(393, 401, 85, 21);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("BE SELLER");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				SignUpGui frame = new SignUpGui();
				frame.setVisible(true);
				dispose();
			}
		});
		btnNewButton_2.setBackground(Color.BLACK);
		btnNewButton_2.setForeground(Color.WHITE);
		btnNewButton_2.setBounds(747, 77, 102, 21);
		contentPane.add(btnNewButton_2);
		
		JLabel lblNewLabel_6 = new JLabel("Sign up for Seller HERE!");
		lblNewLabel_6.setFont(new Font("MV Boli", Font.PLAIN, 15));
		lblNewLabel_6.setBounds(661, 115, 188, 33);
		contentPane.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("");
		lblNewLabel_7.setIcon(new ImageIcon("img\\arrow.png"));
		lblNewLabel_7.setBounds(580, 0, 269, 110);
		contentPane.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("");
		lblNewLabel_8.setIcon(new ImageIcon("img\\download.png"));
		lblNewLabel_8.setBounds(333, 26, 207, 232);
		contentPane.add(lblNewLabel_8);
		
		JButton btnNewButton_3 = new JButton("Seller LOGIN");
		btnNewButton_3.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				SellerLoginGui frame = new SellerLoginGui();
				frame.setVisible(true);
				dispose();
			}
		});
		btnNewButton_3.setIcon(null);
		btnNewButton_3.setBounds(735, 468, 114, 24);
		contentPane.add(btnNewButton_3);
		
		JLabel lblNewLabel_9 = new JLabel("");
		lblNewLabel_9.setIcon(new ImageIcon("img\\white background.jpg"));
		lblNewLabel_9.setBounds(0, -27, 922, 582);
		contentPane.add(lblNewLabel_9);
	}
}
