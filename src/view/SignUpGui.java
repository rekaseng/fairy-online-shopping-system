package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.CustomerController;
import controller.SellerController;
import model.Customer;
import model.Seller;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class SignUpGui extends JFrame {

	private JPanel contentPane;
	private JTextField textField_name;
	private JPasswordField passwordField;
	private JTextField textField_phone;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignUpGui frame = new SignUpGui();
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
	public SignUpGui() {
		setTitle("Fairy Online Shopping-Seller Sign Up page");
		setIconImage(Toolkit.getDefaultToolkit().getImage("img\\download.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 969, 599);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Start Your Business on Fairy . . .");
		lblNewLabel.setFont(new Font("Bookman Old Style", Font.BOLD, 35));
		lblNewLabel.setBounds(355, 20, 590, 74);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Name:");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 21));
		lblNewLabel_1.setBounds(628, 231, 66, 60);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Password:");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.PLAIN, 21));
		lblNewLabel_2.setBounds(611, 279, 94, 60);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Contact No:");
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.PLAIN, 21));
		lblNewLabel_3.setBounds(604, 329, 101, 48);
		contentPane.add(lblNewLabel_3);
		
		textField_name = new JTextField();
		textField_name.setBounds(722, 256, 167, 19);
		contentPane.add(textField_name);
		textField_name.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(722, 303, 167, 19);
		contentPane.add(passwordField);
		
		textField_phone = new JTextField();
		textField_phone.setBounds(722, 347, 167, 19);
		contentPane.add(textField_phone);
		textField_phone.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Let's become a seller !");
		lblNewLabel_4.setFont(new Font("Segoe Script", Font.BOLD, 26));
		lblNewLabel_4.setBounds(578, 433, 367, 119);
		contentPane.add(lblNewLabel_4);
		
		JButton btnNewButton = new JButton("Sign Up");
		btnNewButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				String name = textField_name.getText();
				String password = new String(passwordField.getPassword()).trim();
				String phone = textField_phone.getText();
				
				Seller seller = new Seller();
				seller.setName(name);
				seller.setPassword(password);
				seller.setNo_tel(phone);
				
				SellerController sellerController = new SellerController();
				try
				{
					int success = sellerController.insertSeller(seller);
					if(success == 0)
					{
						JOptionPane.showMessageDialog(btnNewButton, "This is alredy exist");
						
					}
					else
					{
						JOptionPane.showMessageDialog(btnNewButton, "Your account is sucessfully created");
						
					}

				} catch (ClassNotFoundException | SQLException e1) 
				{
					
					e1.printStackTrace();
				}
			
				
			}
		});
		btnNewButton.setBounds(675, 390, 85, 21);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				LoginGui frame = new LoginGui();
				frame.setVisible(true);
				dispose();
			}
		});
		btnNewButton_1.setBounds(804, 390, 85, 21);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel_6 = new JLabel("SIGN UP");
		lblNewLabel_6.setFont(new Font("ROG Fonts", Font.BOLD, 30));
		lblNewLabel_6.setBounds(674, 117, 167, 121);
		contentPane.add(lblNewLabel_6);
		
		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setIcon(new ImageIcon("img\\blue background.png"));
		lblNewLabel_5.setBounds(-451, -36, 1491, 640);
		contentPane.add(lblNewLabel_5);
	}
}
