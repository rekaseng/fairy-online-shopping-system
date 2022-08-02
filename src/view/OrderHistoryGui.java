package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

import controller.CustomerController;
import controller.OrderController;
import model.Customer;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.SQLException;
import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class OrderHistoryGui extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					int customer_id=0;
					OrderHistoryGui frame = new OrderHistoryGui(customer_id);
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
	public OrderHistoryGui(int customer_id) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 561, 447);
		contentPane = new JPanel();
		contentPane.setBackground(Color.PINK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		CustomerController customerController=new CustomerController();
		Customer customer=new Customer();
		JLabel lblNewLabel;
		
		try {
			customer =customerController.getCustomer_Name(customer_id);
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		lblNewLabel = new JLabel("Order History For "+customer.getName());
		lblNewLabel.setFont(new Font("Minion Pro SmBd", Font.PLAIN, 26));
		lblNewLabel.setBounds(57, 10, 325, 47);
		contentPane.add(lblNewLabel);
		
		JScrollPane scrollPane=new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(57, 104, 305, 253);
		contentPane.add(scrollPane,BorderLayout.EAST);
		
		JList<Object> list = new JList<Object>();
		list.setFont(new Font("Tahoma", Font.PLAIN, 20));
		scrollPane.setViewportView(list);
		
		OrderController orderController=new OrderController();
		TreeMap<Integer, String>orderId_productName=new TreeMap<Integer,String>();
		
		try {
			orderId_productName=orderController.getOrderId_ProductName(customer_id);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Vector<Integer> id=new Vector<Integer>();
		for(Map.Entry mEntry: orderId_productName.entrySet())
			{
			
			id.add((Integer)mEntry.getKey());
			}
			Vector<String>names=new Vector<String>();
			for(Map.Entry mEntry: orderId_productName.entrySet())
			{
				names.add((Integer)mEntry.getKey()+" : "+(String)mEntry.getValue());
			
			}
			
		
			list.setListData(names);
			
			JLabel lblNewLabel_1 = new JLabel("Order ID : Product in the Order");
			lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblNewLabel_1.setBounds(57, 67, 325, 27);
			contentPane.add(lblNewLabel_1);
			
			JButton btnGoBack = new JButton("Go Back");
			btnGoBack.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ProductCategoriesGui frame = new ProductCategoriesGui(customer_id);
					frame.setVisible(true);
					dispose();
				}
			});
			btnGoBack.setFont(new Font("Tahoma", Font.PLAIN, 16));
			btnGoBack.setBounds(403, 330, 101, 27);
			contentPane.add(btnGoBack);
			
			JButton btnSelect = new JButton("Select");
			btnSelect.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Object obj=list.getSelectedIndex();
			
					
					if((Integer)obj==-1) {
						JOptionPane.showMessageDialog(btnSelect, "Please select 1 order.");
					  }
					else 
					{
						Integer order_id=id.get((Integer)obj);
						EachOrderHsitoryGui frame = new EachOrderHsitoryGui(customer_id,order_id);
						frame.setVisible(true);
						dispose();
					}
				}
			});
			btnSelect.setFont(new Font("Tahoma", Font.PLAIN, 16));
			btnSelect.setBounds(403, 274, 101, 27);
			contentPane.add(btnSelect);
	}
}
