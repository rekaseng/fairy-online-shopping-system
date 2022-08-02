package view;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;

import controller.OrderController;
import model.Receipt;

public class BillPrintable implements Printable{

	private int order_Id;
	public BillPrintable(int order_Id) {
		this.order_Id=order_Id;
	}
	public int print(Graphics graphics, PageFormat pageFormat,int pageIndex) 
			  throws PrinterException 
			  {    
			      
			      
			     int result = NO_SUCH_PAGE;    
			        if (pageIndex == 0) {                    
			        
			            Graphics2D g2d = (Graphics2D) graphics;                    
			            double width = pageFormat.getImageableWidth();                               
			            g2d.translate((int) pageFormat.getImageableX(),(int) pageFormat.getImageableY()); 



			          //  FontMetrics metrics=g2d.getFontMetrics(new Font("Arial",Font.BOLD,7));
			        
			        try{
			            int y=20;
			            int yShift = 10;
			            int headerRectHeight=15;
			           // int headerRectHeighta=40;
			            
			                
			            g2d.setFont(new Font("Monospaced",Font.PLAIN,9));
			            Object rootPane;
						
						
			            g2d.drawString("-------------------------------------",12,y);y+=yShift;
			            g2d.drawString("         Fairy Online Shopping       ",12,y);y+=yShift;
			            g2d.drawString("         No 11 Jalan UTeM ",12,y);y+=yShift;
			            g2d.drawString("            UTeM  ",12,y);y+=yShift;
			            g2d.drawString("   www.facebook.com/fairyOnlineShopping ",12,y);y+=yShift;
			            g2d.drawString("-------------------------------------",12,y);y+=headerRectHeight;

			         
			     OrderController orderController=new OrderController();
			   
			            Receipt receipt=new Receipt();
			     receipt=  orderController.getReceiptDetails( order_Id);
			            g2d.drawString("\tReceipt",12,y);y+=yShift;
			            g2d.drawString( "\nOrder ID : "+receipt.getOrder_Id(),12,y);y+=yShift;
			            g2d.drawString("\nName  : " + receipt.getCustName(),12,y);y+=yShift;
			            g2d.drawString(	"\nProduct : "+receipt.getProductName(),12,y);y+=yShift;
			            g2d.drawString("\nQuantity : "+	receipt.getQuantity(),12,y);y+=yShift;
			            g2d.drawString("\nAmount : "+ receipt.getAmount(),12,y);
			          
			      
			           

			    }
			    catch(Exception e){
			    e.printStackTrace();
			    }

			              result = PAGE_EXISTS;    
			          }    
			          return result;    
}
}
