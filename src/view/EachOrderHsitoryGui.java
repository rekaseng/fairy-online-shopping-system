package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.OrderController;
import model.Receipt;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.SQLException;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EachOrderHsitoryGui extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					int order_id=0;
					int customer_id=0;
					EachOrderHsitoryGui frame = new EachOrderHsitoryGui(customer_id,order_id);
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
	public EachOrderHsitoryGui(int customer_id,int order_id) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 674, 472);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Order");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblNewLabel.setBounds(177, 37, 207, 49);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Order ID : ");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(46, 153, 126, 36);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Product Name : ");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_1.setBounds(46, 216, 161, 36);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Quantity : ");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_2.setBounds(46, 286, 126, 36);
		contentPane.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("Amount : ");
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1_3.setBounds(46, 343, 126, 36);
		contentPane.add(lblNewLabel_1_3);
		
		OrderController orderController=new OrderController();
		Receipt receipt=new Receipt();
		try {
			receipt=orderController.getReceiptDetails(order_id);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JLabel lblOrderId = new JLabel(""+receipt.getOrder_Id());
		lblOrderId.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblOrderId.setBounds(199, 161, 153, 20);
		contentPane.add(lblOrderId);
		
		JLabel lblProductName = new JLabel(""+receipt.getProductName());
		lblProductName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblProductName.setBounds(199, 220, 425, 28);
		contentPane.add(lblProductName);
		
		JLabel lblQuantity = new JLabel(""+receipt.getQuantity());
		lblQuantity.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblQuantity.setBounds(199, 290, 153, 20);
		contentPane.add(lblQuantity);
		
		JLabel lblAmount = new JLabel(""+receipt.getAmount());
		lblAmount.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblAmount.setBounds(199, 351, 153, 20);
		contentPane.add(lblAmount);
		
		JButton btnCancelOrder = new JButton("Cancel & Delete the Order");
		btnCancelOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int success =orderController.updateCancelOrderStatus(order_id);
					if(success!=0) {
						JOptionPane.showMessageDialog(btnCancelOrder, "Successful Deleted.");
					}
				
					
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnCancelOrder.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnCancelOrder.setBounds(399, 175, 225, 41);
		contentPane.add(btnCancelOrder);
		
		JButton btnGoBack = new JButton("Go Back");
		btnGoBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				OrderHistoryGui frame = new OrderHistoryGui(customer_id);
				frame.setVisible(true);
				dispose();
			}
		});
		btnGoBack.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnGoBack.setBounds(399, 286, 225, 36);
		contentPane.add(btnGoBack);
	}

}
