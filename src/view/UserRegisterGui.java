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
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class UserRegisterGui extends JFrame {

	private JPanel contentPane;
	private JTextField txtName;
	private JPasswordField txtPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserRegisterGui frame = new UserRegisterGui();
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
	public UserRegisterGui() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 819, 479);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("SIGN UP as MEMBER");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 38));
		lblNewLabel.setBounds(76, 40, 489, 39);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Name:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel_1.setBounds(58, 136, 88, 60);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Password:");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel_1_1.setBounds(57, 236, 138, 60);
		contentPane.add(lblNewLabel_1_1);
		
		txtName = new JTextField();
		txtName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtName.setBounds(218, 155, 470, 39);
		contentPane.add(txtName);
		txtName.setColumns(10);
		
		JButton btnSignUp = new JButton("SIGN UP");
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = txtName.getText();
				String password = new String(txtPassword.getPassword()).trim();
				
				Customer customer = new Customer();
				customer.setName(name);
				customer.setPassword(password);
				
				CustomerController customerController = new CustomerController();
				try {
					int successCustomerId = customerController.register(customer);
					if(successCustomerId  != 0)
					{
						JOptionPane.showMessageDialog(btnSignUp, "Your account is sucessfully created. Your id is "+successCustomerId);
						ProductCategoriesGui frame = new ProductCategoriesGui(successCustomerId);
						frame.setVisible(true);
						dispose();
						
						
					}
					else
					{
						JOptionPane.showMessageDialog(btnSignUp, "Fail to sign up.");
						
					}
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnSignUp.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnSignUp.setBounds(146, 368, 139, 39);
		contentPane.add(btnSignUp);
		
		JButton btnLogIn = new JButton("LOG IN");
		btnLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginGui frame = new LoginGui();
				frame.setVisible(true);
				
				dispose();
			}
		});
		btnLogIn.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnLogIn.setBounds(384, 368, 139, 39);
		contentPane.add(btnLogIn);
		
		txtPassword = new JPasswordField();
		txtPassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtPassword.setBounds(218, 255, 470, 39);
		contentPane.add(txtPassword);
	}
}
