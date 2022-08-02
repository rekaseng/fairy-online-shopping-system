	package view;

	import java.awt.BorderLayout;
	import java.awt.EventQueue;

	import javax.swing.JFrame;
	import javax.swing.JPanel;
	import javax.swing.border.EmptyBorder;

import controller.OrderController;
import controller.ProductController;
import model.Order;
import model.Product;

	import javax.swing.JLabel;
	import java.awt.Color;
	import java.awt.Font;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;


import javax.swing.JScrollBar;
	import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
	import javax.swing.SpinnerNumberModel;
	import javax.swing.JButton;
	import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;


public class CartGui extends JFrame {

		private JPanel contentPane;

		/**
		 * Launch the application.
		 */
		public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						int product_id=0;
						int customer_id=0;
						 CartGui frame = new CartGui(product_id,customer_id);
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
		public  CartGui(int product_id,int customer_id) {
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 619, 552);
			contentPane = new JPanel();
			contentPane.setBackground(Color.PINK);
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(null);
			
			
			ProductController productController=new ProductController();
			Product product=new Product();
			try {
				product=productController.getProductDetails(product_id);
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			JLabel lblNewLabel = new JLabel("Product Name : "+product.getName());
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblNewLabel.setBounds(47, 222, 509, 50);
			contentPane.add(lblNewLabel);
			
			
			
			JLabel lblNewLabel_1 = new JLabel("Customer id is "+customer_id);
			lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblNewLabel_1.setBounds(37, 398, 293, 50);
			contentPane.add(lblNewLabel_1);
			
			JLabel lblNewLabel_2 = new JLabel("Shopping Cart");
			lblNewLabel_2.setFont(new Font("Minion Pro", Font.PLAIN, 30));
			lblNewLabel_2.setBounds(57, 35, 246, 50);
			contentPane.add(lblNewLabel_2);
			
			JLabel lblNewLabel_3 = new JLabel("You have selected product below");
			lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 26));
			lblNewLabel_3.setBounds(37, 124, 479, 50);
			contentPane.add(lblNewLabel_3);
			
			JLabel lblNewLabel_4 = new JLabel("Quantity : ");
			lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblNewLabel_4.setBounds(47, 271, 120, 43);
			contentPane.add(lblNewLabel_4);
			
			
			JLabel lblNewLabel_5 = new JLabel("Subtotal Price : ");
			lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblNewLabel_5.setBounds(47, 311, 170, 56);
			contentPane.add(lblNewLabel_5);
				
			
			final double price=product.getPrice();
			JLabel lblNewLabel_6 = new JLabel(""+price);
			lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblNewLabel_6.setBounds(214, 324, 114, 30);
			contentPane.add(lblNewLabel_6);
			
			
			JSpinner spinner = new JSpinner();
			spinner.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
			spinner.setFont(new Font("Tahoma", Font.PLAIN, 16));
			spinner.setBounds(143, 287, 74, 20);
			
			spinner.getComponent(1).addMouseListener(new MouseListener() {
				
				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseExited(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
					 double subtotal=(Integer)spinner.getValue()*price;
				
						lblNewLabel_6.setText(""+subtotal);
				}
			});
			

			spinner.getComponent(0).addMouseListener(new MouseListener() {
				
				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseExited(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
					 double subtotal=(Integer)spinner.getValue()*price;
						lblNewLabel_6.setText(""+subtotal);
				}
			});
			contentPane.add(spinner);
			
			
			JButton btn_PlaceOrder = new JButton("Place Order");
			btn_PlaceOrder.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
				 double subtotal=(Integer)spinner.getValue()*price;
					lblNewLabel_6.setText(""+subtotal);
					OrderController orderController=new OrderController();
					Order order=new Order();
					
					Date date=new Date();
					order.setDateCreated(date);
					
					order.setStatus("Placed Order");
					
					ProductController productController=new ProductController();
					Product product=new Product();
					try {
						 product =productController.getProductDetails(product_id);
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					HashMap<Product,Integer> product_quantity=new HashMap<Product,Integer>();
					
					product_quantity.put((Product)product,((Integer)spinner.getValue()));
					order.setProduct_quantity(product_quantity);
					
					order.setSubtotal(subtotal);
					
					
					try {
					int last =orderController.setOrderDetails(order, customer_id);//last id from order
					if(last!=0)
					{
						JOptionPane.showMessageDialog(btn_PlaceOrder, "Order successfully placed");
						ReceiptGui frame = new ReceiptGui(last,product_id,customer_id);
						frame.setVisible(true);
						dispose();
					}
					
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				
				}
			});
		
			
			btn_PlaceOrder.setFont(new Font("Tahoma", Font.PLAIN, 16));
			btn_PlaceOrder.setBounds(288, 417, 133, 21);
			contentPane.add(btn_PlaceOrder);
			
			final int selected_id=product.getCode();
			JButton btn_Cancel = new JButton("Cancel");
			btn_Cancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ProductGui frame = new ProductGui(selected_id,customer_id);
					frame.setVisible(true);
					dispose();
				}
			});
			btn_Cancel.setFont(new Font("Tahoma", Font.PLAIN, 16));
			btn_Cancel.setBounds(431, 417, 85, 21);
			contentPane.add(btn_Cancel);
			
			
			
		}
}
