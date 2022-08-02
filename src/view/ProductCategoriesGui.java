package view;

	import java.awt.BorderLayout;
	import java.awt.EventQueue;

	import javax.swing.JFrame;
	import javax.swing.JPanel;
	import javax.swing.border.EmptyBorder;

import controller.CustomerController;
import controller.ProductController;
import model.Customer;
import model.Product;

	import javax.swing.JTable;
	import javax.swing.JList;
	import javax.swing.JOptionPane;
	import javax.swing.AbstractListModel;
	import javax.swing.BorderFactory;

	import java.awt.Font;
	import java.sql.SQLException;
	import java.awt.Color;
	import java.awt.Dimension;

	import javax.swing.JLabel;

	import java.util.ArrayList;
	import java.util.Vector;

	import javax.swing.JScrollPane;
	import javax.swing.border.MatteBorder;
	import javax.swing.event.ListSelectionEvent;
	import javax.swing.event.ListSelectionListener;
	import javax.swing.ScrollPaneConstants;
	import javax.swing.ImageIcon;
	import javax.swing.JScrollBar;
	import javax.swing.JButton;
	import java.awt.event.ActionListener;
	import java.awt.event.ActionEvent;
	

public class ProductCategoriesGui extends JFrame{
	

		private JPanel contentPane;

		/**
		 * Launch the application.
		 */
		public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						int customer_id=0;
						ProductCategoriesGui frame = new ProductCategoriesGui(customer_id);
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
		public ProductCategoriesGui(int customer_id) {
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 871, 558);
			contentPane = new JPanel();
			contentPane.setBackground(new Color(255, 250, 205));
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(null);
			
			ProductController productController=new ProductController();
			
				ArrayList<String> categories=new ArrayList<String>();
				try {
					categories = productController.getAllCategory();
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			JLabel lblNewLabel = new JLabel("Product Category");
			lblNewLabel.setFont(new Font("Minion Pro Cond", Font.PLAIN, 30));
			lblNewLabel.setBounds(516, 44, 218, 45);
			contentPane.add(lblNewLabel);
			
			JScrollPane scrollPane=new JScrollPane();
			scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			scrollPane.setBounds(467, 113, 305, 253);
			contentPane.add(scrollPane,BorderLayout.EAST);
			
			//String[] categoriesS=new String [categories.size()];
			JList<Object> list = new JList<Object>();
			list.setFont(new Font("Tahoma", Font.PLAIN, 20));
			scrollPane.setViewportView(list);
			list.setBackground(Color.PINK);
			
			
			list.setListData(categories.toArray());
			
			JLabel lblNewLabel_1 = new JLabel("New label");
			lblNewLabel_1.setIcon(new ImageIcon("img\\fairy.jpg"));
			lblNewLabel_1.setBounds(38, 72, 419, 439);
			contentPane.add(lblNewLabel_1);
			
			JButton btnNewButton = new JButton("Select");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Object obj=list.getSelectedValue();
					String choice;
					choice="";
					choice=(String)obj;
					
					if(choice==null) {
						JOptionPane.showMessageDialog(btnNewButton, "Please select 1 category.");
					  }
					else 
					{
						SelectedProductCategory frame = new SelectedProductCategory(choice,customer_id);
						frame.setVisible(true);
						dispose();
					}
					
				}
			});
			btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
			btnNewButton.setBounds(496, 440, 99, 21);
			contentPane.add(btnNewButton);
			
			JButton btnGoBack = new JButton("Log Out");
			btnGoBack.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					LoginGui frame = new LoginGui();
					frame.setVisible(true);
					dispose();
				}
			});
			btnGoBack.setFont(new Font("Tahoma", Font.PLAIN, 16));
			btnGoBack.setBounds(651, 440, 99, 21);
			contentPane.add(btnGoBack);
			Customer customer=new Customer();
			CustomerController customerController =new CustomerController();
			
			try {
				customer =customerController.getCustomer_Name(customer_id);
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			JLabel lblNewLabel_2 = new JLabel("Welcome, "+customer.getName());
			lblNewLabel_2.setFont(new Font("Lucida Handwriting", Font.BOLD, 26));
			lblNewLabel_2.setBounds(38, 10, 485, 45);
			contentPane.add(lblNewLabel_2);
			
			JButton btnSearch = new JButton("Search Product");
			btnSearch.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					SearchProductGui frame = new SearchProductGui(customer_id);
					frame.setVisible(true);
					dispose();
				}
			});
			btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 16));
			btnSearch.setBounds(699, 5, 148, 29);
			contentPane.add(btnSearch);
			
			JButton btnOrderHistory = new JButton("Order History");
			btnOrderHistory.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					OrderHistoryGui frame = new OrderHistoryGui(customer_id);
					frame.setVisible(true);
					dispose();
				}
			});
			btnOrderHistory.setFont(new Font("Tahoma", Font.PLAIN, 16));
			btnOrderHistory.setBounds(533, 5, 148, 29);
			contentPane.add(btnOrderHistory);
			
			JButton btnUpdatePassword = new JButton("Update Password");
			btnUpdatePassword.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					UpdateCustomer frame = new UpdateCustomer(customer_id);
					frame.setVisible(true);
					dispose();
				}
			});
			btnUpdatePassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
			btnUpdatePassword.setBounds(358, 5, 165, 29);
			contentPane.add(btnUpdatePassword);
			
		
			
		}
	}


