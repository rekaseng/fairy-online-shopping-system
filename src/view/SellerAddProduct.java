package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.ProductController;
import model.Product;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class SellerAddProduct extends JFrame {

	private JPanel contentPane;
	private JTextField txtProductName;
	private JTextField txtPrice;
	private JTextField txtCategory;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					int seller_id=0;
					SellerAddProduct frame = new SellerAddProduct(seller_id);
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
	public SellerAddProduct(int seller_id) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 567, 494);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 182, 193));
		contentPane.setForeground(new Color(245, 245, 245));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Sell Your Product");
		lblNewLabel.setFont(new Font("Minion Pro", Font.ITALIC, 37));
		lblNewLabel.setBounds(74, 52, 357, 59);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Product Name : ");
		lblNewLabel_1.setFont(new Font("Minion Pro", Font.PLAIN, 26));
		lblNewLabel_1.setBounds(47, 148, 231, 59);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Price : ");
		lblNewLabel_1_1.setFont(new Font("Minion Pro", Font.PLAIN, 26));
		lblNewLabel_1_1.setBounds(47, 234, 231, 59);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Category : ");
		lblNewLabel_1_2.setFont(new Font("Minion Pro", Font.PLAIN, 26));
		lblNewLabel_1_2.setBounds(47, 315, 231, 59);
		contentPane.add(lblNewLabel_1_2);
		
		txtProductName = new JTextField();
		txtProductName.setFont(new Font("Tahoma", Font.PLAIN, 26));
		txtProductName.setBounds(236, 160, 195, 32);
		contentPane.add(txtProductName);
		txtProductName.setColumns(10);
		
		txtPrice = new JTextField();
		txtPrice.setFont(new Font("Tahoma", Font.PLAIN, 26));
		txtPrice.setColumns(10);
		txtPrice.setBounds(236, 242, 195, 32);
		contentPane.add(txtPrice);
		
		txtCategory = new JTextField();
		txtCategory.setFont(new Font("Tahoma", Font.PLAIN, 26));
		txtCategory.setColumns(10);
		txtCategory.setBounds(236, 334, 195, 26);
		contentPane.add(txtCategory);
		
		JButton btnOk = new JButton("Ok");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String productName=txtProductName.getText();
				double price=Double.parseDouble(txtPrice.getText());
				String category=txtCategory.getText();
				
				Product product=new Product();
				product.setName(productName);
				product.setPrice(price);
				product.setCategory(category);
				
				ProductController productController=new ProductController();
				int success=0;
				try {
					success=productController.addProduct(product, seller_id);
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				if(success==0) {
					JOptionPane.showMessageDialog(btnOk, "Fail to add product.");
				}
				else {
					JOptionPane.showMessageDialog(btnOk, "Success inserted the product.");
					txtProductName.setText("");
					txtPrice.setText("");
					txtCategory.setText("");
				}
				
			}
		});
		btnOk.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnOk.setBounds(63, 409, 85, 21);
		contentPane.add(btnOk);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtProductName.setText("");
				txtPrice.setText("");
				txtCategory.setText("");
			}
		});
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnCancel.setBounds(197, 409, 85, 21);
		contentPane.add(btnCancel);
		
		JButton btnGoBack = new JButton("Back");
		btnGoBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				SellerLoginGui frame = new SellerLoginGui();
			frame.setVisible(true);
			dispose();
			}
		});
		btnGoBack.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnGoBack.setBounds(349, 411, 101, 21);
		contentPane.add(btnGoBack);
		
		
		
	}
}
