package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DefaultStyledDocument;


import controller.OrderController;
import model.Order;
import model.Receipt;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.PrintJob;

import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.ServiceUIFactory;
import javax.print.StreamPrintService;
import javax.print.StreamPrintServiceFactory;
import javax.print.attribute.Attribute;
import javax.print.attribute.AttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintServiceAttribute;
import javax.print.attribute.PrintServiceAttributeSet;
import javax.print.attribute.standard.Destination;
import javax.print.event.PrintServiceAttributeListener;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Pageable;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class ReceiptGui extends JFrame {

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
					int order_Id=0;
					ReceiptGui frame = new ReceiptGui(order_Id,product_id,customer_id);
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
	public ReceiptGui(int order_Id,int product_id,int customer_id) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 476);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Receipt");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblNewLabel.setBounds(115, 0, 199, 69);
		contentPane.add(lblNewLabel);
		
		
		JTextArea textArea = new JTextArea();
		OrderController orderController=new OrderController();
		Receipt receipt=new Receipt();
		
		try {
			receipt=orderController.getReceiptDetails(order_Id);
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		textArea.append("\tReceipt\n"+ "\nOrder ID : "+receipt.getOrder_Id()+"\nName  : " + receipt.getCustName()+
				"\nProduct : "+receipt.getProductName()+ "\nQuantity : "+	receipt.getQuantity()+
				"\nAmount : "+ receipt.getAmount()	);
		textArea.setBounds(50, 71, 285, 307);
		contentPane.add(textArea);
		
		JButton btnPrint = new JButton("Print");
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String printData=textArea.getText();
				PrinterJob printerJob=PrinterJob.getPrinterJob();
				
				
				printerJob.setPrintable(new BillPrintable(order_Id));
				
				try {
					printerJob.print();
				}
				catch(PrinterException pe) {
					pe.printStackTrace();
				}
				
			}
			});	
				
		btnPrint.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnPrint.setBounds(55, 408, 85, 21);
		contentPane.add(btnPrint);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				CartGui frame = new CartGui(product_id,customer_id);
				frame.setVisible(true);
				dispose();
			}
		});
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnCancel.setBounds(198, 408, 85, 21);
		contentPane.add(btnCancel);
		
	
	}

	
}