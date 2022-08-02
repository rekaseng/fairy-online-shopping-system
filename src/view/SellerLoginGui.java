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
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class SellerLoginGui extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SellerLoginGui frame = new SellerLoginGui();
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
	public SellerLoginGui() {
		setTitle("Fairy Online Shopping-Seller Login Page");
		setIconImage(Toolkit.getDefaultToolkit().getImage("img\\download.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 902, 545);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ID:");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel.setBounds(101, 223, 26, 38);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("PASSWORD:");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(20, 271, 120, 55);
		contentPane.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(148, 235, 189, 19);
		contentPane.add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(148, 291, 189, 19);
		contentPane.add(passwordField);
		
		JButton btnNewButton = new JButton("LOGIN");
		btnNewButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				String txtId=textField.getText();
				int id=0;
				String password="";
				try 
				{
					id=Integer.parseInt(txtId);
					password=new String(passwordField.getPassword());
				} catch (NumberFormatException e1) 
				{
					// TODO: handle exception
					//System.err.println("Error in parseInt");
				}
				
				
			
				Seller seller = new Seller();
				seller.setId(id);
				seller.setPassword(password);
			
				
				SellerController sellerController=new SellerController();
				
				
				try {
					boolean success=sellerController.doLogin(seller);
					System.out.println("success "+success);
					if(success) {
						SellerAddProduct frame = new SellerAddProduct(seller.getId());
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
		btnNewButton.setBounds(115, 336, 73, 21);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("MAIN MENU");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				LoginGui frame = new LoginGui();
				frame.setVisible(true);
				dispose();
			}
		});
		btnNewButton_1.setBounds(211, 336, 116, 21);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel_4 = new JLabel("SELLER LOGIN PAGE");
		lblNewLabel_4.setForeground(Color.WHITE);
		lblNewLabel_4.setFont(new Font("Segoe UI Black", Font.BOLD, 30));
		lblNewLabel_4.setBounds(76, 66, 346, 88);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setIcon(new ImageIcon("img\\k.jpg"));
		lblNewLabel_2.setBounds(-139, -45, 1119, 553);
		contentPane.add(lblNewLabel_2);
		
	}

}
