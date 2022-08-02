package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.ProductController;

import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.SQLException;
import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SearchProductGui extends JFrame {

	private JPanel contentPane;
	private JTextField txtSearch;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					int customer_id=0;
					SearchProductGui frame = new SearchProductGui(customer_id);
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
	public SearchProductGui(int customer_id) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 729, 612);
		contentPane = new JPanel();
		contentPane.setBackground(Color.PINK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtSearch = new JTextField();
		txtSearch.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtSearch.setBounds(246, 73, 96, 19);
		contentPane.add(txtSearch);
		txtSearch.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Search Here : ");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(54, 50, 136, 63);
		contentPane.add(lblNewLabel);
		
		JList<Object> list = new JList<Object>();
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(87, 200, 304, 288);
		contentPane.add(scrollPane);
		
		Vector<Integer> id=new Vector<Integer>();
		
		JButton btnOk = new JButton("Ok");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProductController productController=new ProductController();
				TreeMap<Integer,String> id_productNames=new TreeMap<Integer,String>();	
				try {
					 id_productNames=productController.getSearchProduct(txtSearch.getText());
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				
				
				list.setFont(new Font("Tahoma", Font.PLAIN, 20));
				scrollPane.setViewportView(list);
				
				
				for(Map.Entry mEntry: id_productNames.entrySet())
					{
					
					id.add((Integer)mEntry.getKey());
					}
				
				Vector<String>names=new Vector<String>();
				for(Map.Entry mEntry: id_productNames.entrySet())
				{
					names.add((String)mEntry.getValue());
				
				}
				list.setListData(names);
			}
		});
		btnOk.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnOk.setBounds(362, 73, 85, 21);
		contentPane.add(btnOk);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			txtSearch.setText("");
			}
		});
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnCancel.setBounds(457, 73, 85, 21);
		contentPane.add(btnCancel);
		
		JButton btnGoBack = new JButton("Go Back");
		btnGoBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProductCategoriesGui frame = new ProductCategoriesGui(customer_id);
				frame.setVisible(true);
				dispose();
			}
		});
		btnGoBack.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnGoBack.setBounds(552, 73, 96, 21);
		contentPane.add(btnGoBack);
		
		JButton btnNewButton = new JButton("Select");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Object obj=list.getSelectedIndex();
			
			
				
				if((Integer)obj==-1) {
					JOptionPane.showMessageDialog(btnNewButton, "Please select 1 product.");
				  }
				else {
					
					Integer selected_id=id.get(((Integer)obj));
						
					ProductGui frame = new ProductGui(selected_id,customer_id);
					frame.setVisible(true);
					dispose();
				}
	}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.setBounds(416, 467, 99, 21);
		contentPane.add(btnNewButton);
		
	}
}
