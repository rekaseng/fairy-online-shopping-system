package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

import controller.ProductController;
import model.Product;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;

public class SelectedProductCategory extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					int customer_id=0;
					String selected="";
					SelectedProductCategory frame = new SelectedProductCategory(selected,customer_id);
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
	public SelectedProductCategory(String selected,int customer_id) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 897, 519);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 222, 179));
		contentPane.setForeground(new Color(192, 192, 192));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Category "+selected);
		lblNewLabel.setFont(new Font("Minion Pro", Font.PLAIN, 30));
		lblNewLabel.setBounds(68, 25, 447, 81);
		contentPane.add(lblNewLabel);
		
		ProductController productController=new ProductController();
		TreeMap<Integer,String> id_productNames=new TreeMap<Integer,String>();	
		try {
			id_productNames=productController.getNames_id(selected);
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	
		
		
		JScrollPane scrollPane=new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(467, 113, 305, 253);
		contentPane.add(scrollPane,BorderLayout.EAST);
		
		JList<Object> list = new JList<Object>();
		list.setFont(new Font("Tahoma", Font.PLAIN, 20));
		scrollPane.setViewportView(list);
	
		list.setBackground(Color.PINK);
		Vector<Integer> id=new Vector<Integer>();
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
		
		//contentPane.add(list);
		
		
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
		btnNewButton.setBounds(496, 440, 99, 21);
		contentPane.add(btnNewButton);
		
		JButton btnGoBack = new JButton("Go Back");
		btnGoBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ProductCategoriesGui frame = new ProductCategoriesGui(customer_id);
				frame.setVisible(true);
				dispose();
			}
		});
		btnGoBack.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnGoBack.setBounds(636, 442, 109, 21);
		contentPane.add(btnGoBack);
		
		
	}
}
