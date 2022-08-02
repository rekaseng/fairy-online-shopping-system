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
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

public class UpdateCustomer extends JFrame {

	private JPanel contentPane;
	private JTextField txtID;
	private JPasswordField txtPassword;
	private JPasswordField txtNewPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					int customer_id=0;
					UpdateCustomer frame = new UpdateCustomer(customer_id);
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
	public UpdateCustomer(int customer_id) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 726, 459);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Customers Update Info");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 35));
		lblNewLabel.setBounds(29, 10, 401, 60);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("ID : ");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel_1.setBounds(39, 100, 124, 60);
		contentPane.add(lblNewLabel_1);
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginGui frame = new LoginGui();
				frame.setVisible(true);
				dispose();
			}
		});
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnBack.setBounds(589, 348, 86, 35);
		contentPane.add(btnBack);
		
		txtID = new JTextField();
		txtID.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtID.setBounds(224, 119, 351, 35);
		contentPane.add(txtID);
		txtID.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Password : ");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel_1_1.setBounds(39, 199, 148, 60);
		contentPane.add(lblNewLabel_1_1);
		
		JButton btnNewButton = new JButton("Update");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String txtId=txtID.getText();
				int id=0;
				String password="";
				try {
					id=Integer.parseInt(txtId);
					password=new String(txtPassword.getPassword());
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
					System.out.println("success "+success);
					if(success) {
						String newPassword=new String(txtNewPassword.getPassword());
						System.out.println("customer id is "+customer_id);
						int successUpdate=customerController.updatePassword(customer_id, newPassword);
						
						if(successUpdate!=0)
							JOptionPane.showMessageDialog(btnNewButton, "Info is updated.");
						else 
							JOptionPane.showMessageDialog(btnNewButton, "Fail to update.");
						
					}
					else 
						JOptionPane.showMessageDialog(btnNewButton,"You need to insert ID and password first.");
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnNewButton.setBounds(289, 348, 95, 35);
		contentPane.add(btnNewButton);
		
		txtPassword = new JPasswordField();
		txtPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtPassword.setBounds(224, 210, 351, 35);
		contentPane.add(txtPassword);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("New Password : ");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel_1_1_1.setBounds(39, 289, 195, 60);
		contentPane.add(lblNewLabel_1_1_1);
		
		txtNewPassword = new JPasswordField();
		txtNewPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtNewPassword.setBounds(224, 303, 351, 35);
		contentPane.add(txtNewPassword);
	}
}
