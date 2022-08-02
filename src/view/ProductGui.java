package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.ProductController;
import model.Product;

import javax.swing.JLabel;
import java.awt.Font;
import java.sql.SQLException;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ProductGui extends JFrame {

	private JPanel contentPane;

	/**
	 *
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					int customer_id=0;
					Integer selected_id=0;
					ProductGui frame = new ProductGui(selected_id,customer_id);
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
	public ProductGui(int selected_id,int customer_id) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 729, 495);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 182, 193));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		Product product=new Product();
		ProductController productController=new ProductController();
		try {
			product=productController.getProductDetails(selected_id);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		JLabel lblNewLabel = new JLabel("Product : "+product.getName());
		lblNewLabel.setFont(new Font("Minion Pro", Font.PLAIN, 30));
		lblNewLabel.setBounds(54, 10, 606, 98);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Price : "+product.getPrice());
		lblNewLabel_1.setFont(new Font("Minion Pro", Font.PLAIN, 26));
		lblNewLabel_1.setBounds(54, 254, 212, 77);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Product Code : "+product.getCode());
		lblNewLabel_2.setFont(new Font("Minion Pro", Font.PLAIN, 26));
		lblNewLabel_2.setBounds(54, 195, 288, 62);
		contentPane.add(lblNewLabel_2);
		
		JButton btnNewButton = new JButton("Add to Cart");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
			CartGui frame = new CartGui(selected_id,customer_id);
			frame.setVisible(true);
			dispose();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.setBounds(401, 399, 126, 21);
		contentPane.add(btnNewButton);
		
		String category=product.getCategory();
		
		JButton btnGoBack = new JButton("Go Back");
		btnGoBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				SelectedProductCategory frame = new SelectedProductCategory(category,customer_id);
				frame.setVisible(true);
				dispose();
			}
		});
		btnGoBack.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnGoBack.setBounds(551, 399, 126, 21);
		contentPane.add(btnGoBack);
		
	
	
		
		
	}
}
