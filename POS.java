//Inventory System & POS
//Programmer: Armand Robin I. Tangonan
//github.com/armand621

import java.io.*;
import java.util.*;
import java.lang.*;
import java.util.regex.*;
import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.*;
import javax.swing.table.*;
import javax.swing.event.*;


import java.text.DateFormat;
import java.util.Date;
import javax.swing.Timer;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class POS extends JFrame implements ActionListener{

	JButton closeBtn;
	static JButton hideClose;

	static Color gold = new Color(0xFCCB06);
	static Color darkBlue = new Color(0x222E50);
	static Color darkOrange = new Color(0xFF8C00);

	Font arial12b = new Font("Arial", Font.BOLD,12);
	Font arial15b = new Font("Arial", Font.BOLD,15);
	Font arial18b = new Font("Arial", Font.BOLD,18);
	Font arial25b = new Font("Arial", Font.BOLD,25);
	Font arial30b = new Font("Arial", Font.BOLD,30);

	JButton back2Dash;

	Frame frObj = new Frame();


	//this part is for the background image of the jframe
	ImageIcon frameBg;

	JLabel lblBg;

	//this part is for the date and time label
	String[] strDate = {"Date", "Time"}; 
	JLabel [] lblDate = new JLabel[strDate.length];

	//this part is for the total amount label
	static JLabel lblTotal, lblNumTotal;

	//this part is for the transaction buttons
	static String [] strTransBtn = {"Remove Item", "Discount", "Payment", "Cancel"};
	static JButton [] transBtn = new JButton[strTransBtn.length];


	//this part is for labels
	String [] strMainLbl = {"Quantity", "Search Item", "Invoice No.", ""};
	JLabel [] mainLbl = new JLabel[strMainLbl.length];


	//this part is for the textfield of the quantity
	static JTextField quantity;

	//this part is for the button of the search item
	static JButton srcBtn;

	//this part is for the integer value of the invoice number
	int invoiceNum = 1000000;


	//this part is for the table
	String [][] posData = {};
	String[] posHeader = {"Item Code", "Item Name", "Size", "Price", "Quantity", "Total"};

	DefaultTableModel posDefTableModel = new DefaultTableModel(posData, posHeader);
	JTable posTable = new JTable(posDefTableModel);
	JScrollPane posScrollpane = new JScrollPane(posTable);

	Vector<String> posVector = new Vector<String>();

	DefaultTableCellRenderer rdr = new DefaultTableCellRenderer();


	//this part is for the reserved variables
	static String itmcd;
	static String itmnm;
	static String itmst;
	static String itmpc;

	//this part is for the experimental button
	static JButton adder;

	static JButton setter;


	//this part will be commented out since it was just for sample purpose only
	// JScrollPane scrollpane = new JScrollPane(frObj.table);

	static double b;
	static int posQuantity, parsedQuantityNum;

	static String[][] exp; 

	static int rmAdd;


	static int[] showStock = new int[20];

	void pos(){

		

		setSize(1250,600);
		setLocationRelativeTo(null);
		setLayout(null);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setResizable(false);
		setUndecorated(true);
		getContentPane().setBackground(darkBlue);
		

		frameBg = new ImageIcon(new ImageIcon("posImg/pos.jpg").getImage().getScaledInstance(1250,600, Image.SCALE_SMOOTH));
		lblBg = new JLabel();

		lblBg.setIcon(frameBg);
		lblBg.setBounds(0,0,1250,600);

		adder = new JButton();
		adder.setBounds(0,0,10,20);
		adder.setVisible(false);
		adder.addActionListener(this);
		add(adder);



		setter = new JButton();
		setter.setBounds(20,0,10,20);
		setter.setVisible(false);
		setter.addActionListener(this);
		add(setter);

		 closeBtn = new JButton();
		 closeBtn.setBounds(1200,0,50,20);
		 closeBtn.setBackground(new Color(0x800000));
		 closeBtn.setForeground(Color.WHITE);
		 closeBtn.setText("X");
		 closeBtn.setFocusable(false);
		 closeBtn.addActionListener(this);
		 add(closeBtn);


		 hideClose = new JButton();
		 hideClose.setBounds(1200,50,50,20);
		 hideClose.setBackground(new Color(0x800000));
		 hideClose.setForeground(Color.WHITE);
		 hideClose.addActionListener(this);
		 hideClose.setVisible(false);
		 
		 add(hideClose);




		 //this part is for the back to dashboard button
		back2Dash = new JButton();
		back2Dash.setBounds(15,570,180,20);
		back2Dash.setText("<html><u>Back to Dashboard</u></html>");
		back2Dash.setOpaque(true);
		back2Dash.setBackground(new Color(0xf3f0f0));
		back2Dash.setForeground(darkBlue);
		back2Dash.setBorder(null);
		back2Dash.addActionListener(this);
		back2Dash.setFocusable(false);
		add(back2Dash);


		// this part will be commmented out since it was just for sample purpose only
		JPanel expan = new JPanel();
		expan.setBounds(20,230,790,320);
		expan.setLayout(new BorderLayout());
		expan.add(posScrollpane);

		//this part is for the initialization of the date and time
		int lblDateY = 4;
		for(int a = 0; a<=strDate.length-1; a++ ){
			lblDate[a] = new JLabel();
			lblDate[a].setBounds(860,lblDateY,300,25);
			lblDate[a].setFont(arial15b);
			lblDate[a].setForeground(darkBlue);
			add(lblDate[a]);
			lblDateY+=30;
		}


		//this part is for the clock
		clockActive();
		Timer timer = new Timer(500, new ActionListener() {
      		@Override
      			public void actionPerformed(ActionEvent e) {
        			clockActive();
     			 }
    		});

   		timer.setRepeats(true);
    	timer.setCoalesce(true);
    	timer.setInitialDelay(0);
    	timer.start();


    	//this part is for the date
    	DateTimeFormatter dates =DateTimeFormatter .ofPattern("EEEE, MMMM dd, yyyy");
   		LocalDateTime now =LocalDateTime.now();
   		lblDate[0].setText("Date: " + dates.format(now));
	


   		//this part is for the initialization of the total amount label
   		lblTotal = new JLabel();
   		lblTotal.setText("Total Amount");
   		lblTotal.setBounds(830, 130, 420, 40);
   		lblTotal.setHorizontalAlignment(JLabel.CENTER);
   		lblTotal.setForeground(gold);
   		lblTotal.setFont(arial25b);

   		//this part is for the value of the total amount
   		lblNumTotal = new JLabel();
   		// lblNumTotal.setText("Total Amount");
   		lblNumTotal.setBounds(830, 180, 420, 40);
   		lblNumTotal.setHorizontalAlignment(JLabel.CENTER);
   		lblNumTotal.setForeground(new Color(0xED9D1F));
   		lblNumTotal.setFont(arial30b);

   		//this part is for the buttons for transaction
   		int transBtnY = 310;
   		for(int b = 0; b <= strTransBtn.length-1; b++){
   			transBtn[b] = new JButton();
   			transBtn[b].setText(strTransBtn[b]);
   			transBtn[b].setBounds(920, transBtnY, 250, 40);
   			transBtn[b].setFocusable(false);
   			transBtn[b].setOpaque(true);
   			transBtn[b].setBackground(gold);
   			transBtn[b].setForeground(darkBlue);
   			transBtn[b].setFont(arial18b);
   			transBtn[b].addActionListener(this);
   			add(transBtn[b]);
   			transBtnY+=60;
   		}

   		transBtn[1].setEnabled(false);
   		transBtn[2].setEnabled(false);



   		//this part is for the initialization of mainlabel
   		int mainLblX = 40;
   		for(int c = 0; c<=strMainLbl.length-1; c++){
   			mainLbl[c] = new JLabel();
   			mainLbl[c].setText(strMainLbl[c]);
   			mainLbl[c].setBounds(mainLblX, 110, 200, 40);
   			mainLbl[c].setFont(arial18b);
   			add(mainLbl[c]);
   			mainLblX+=290;
   		}


   		//this part is for the textfield of the quantity
   		quantity = new JTextField();
   		quantity.setBounds(40,150,200,35);
   		quantity.setOpaque(false);
   		quantity.setBorder(BorderFactory.createMatteBorder(0,0,2,0,darkBlue));
   		quantity.setFont(arial15b);
   		add(quantity);

   		//this part is for the button for the search item
   		srcBtn = new JButton();
   		srcBtn.setText("Search");
   		srcBtn.setFocusable(false);
   		srcBtn.setBounds(310,150,200,35);
   		srcBtn.setOpaque(true);
   		srcBtn.setBackground(darkBlue);
   		srcBtn.setForeground(gold);
   		srcBtn.setFont(arial18b);
   		srcBtn.addActionListener(this);
   		add(srcBtn);

   		//this part is for the invoice number

   		mainLbl[3].setBounds(620,150,200,35);
   		mainLbl[3].setText(String.valueOf(invoiceNum));
   		mainLbl[3].setFont(arial25b);
   		mainLbl[3].setForeground(darkBlue);



   		//this part is for the table
   		posTable.setFocusable(false);
		posTable.setRowSelectionAllowed(true);
		posTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		posTable.setRowHeight(25);

		posTable.setDefaultEditor(Object.class, null);
		posTable.getTableHeader().setEnabled(false);
		posTable.getTableHeader().setPreferredSize(new Dimension(50, 30));

		rdr.setHorizontalAlignment(JLabel.CENTER);


		for(int d=0; d<=posHeader.length-1; d++){
			posTable.getColumnModel().getColumn(d).setCellRenderer(rdr);
		}
		



   		//this part is for adding components to the frame
   		add(lblNumTotal);
   		add(lblTotal);
		add(expan); //this part has been commented out because it was just for sample purpose only
		add(lblBg);
		setVisible(true);
	}


	


	//this part is for the clock
	 public void clockActive(){
    	// lblDate[1].setText(DateFormat.getTimeInstance().format(new Date()));

    	lblDate[1].setText("Time: " + DateFormat.getTimeInstance().format(new Date()));
  	}

	



	@Override
	public void actionPerformed(ActionEvent e){

		if(e.getSource() == closeBtn){
			int closeOpt = JOptionPane.showConfirmDialog(this, "Are you sure to exit?", "Confirmation", JOptionPane.YES_NO_OPTION);
			if(closeOpt==0){
				System.exit(0);
			}
		}



		else if(e.getSource() == back2Dash){



			Dashboard dsh = new Dashboard();
			dsh.userDashboard();
			dispose();

			




		}


		else if (e.getSource() == srcBtn){

			 if(quantity.getText().equals("")){
		    	JOptionPane.showMessageDialog(this,"Please enter number to quantity.", "Invalid Input", JOptionPane.WARNING_MESSAGE);		    	
		    }


			 else if(quantity.getText().isEmpty()){
		    	JOptionPane.showMessageDialog(this,"Please enter number to quantity.", "Invalid Input", JOptionPane.WARNING_MESSAGE);		    	
		    }

		     else if(quantity.getText().isBlank()){
		    	JOptionPane.showMessageDialog(this,"Please enter number to quantity.", "Invalid Input", JOptionPane.WARNING_MESSAGE);		    	
		    }

		    else{
			//this is for the try and catch
				try{
					parsedQuantityNum = Integer.parseInt(quantity.getText());
					if (parsedQuantityNum == 0) {
						JOptionPane.showMessageDialog(this,"Quantity number cannot be zero.", "Zero Input", JOptionPane.ERROR_MESSAGE);
					}

					else if (parsedQuantityNum < 0) {
						JOptionPane.showMessageDialog(this,"Quantity number cannot be negative number.", "Negative Input", JOptionPane.ERROR_MESSAGE);
					}

					else{
						transBtn[0].setEnabled(false);
						transBtn[1].setEnabled(false);
						transBtn[2].setEnabled(false);
						transBtn[3].setEnabled(false);

						ShowInventory.sV=true;
						ShowInventory.expBtn.doClick();

						 
					// sh.expBtn.doClick();
					// ShowInventory.numQuanti = parsedQuantityNum;
						posQuantity = parsedQuantityNum;
					// ShowInventory.numQuanti = posQuantity;

					
						int aRowCount = posTable.getRowCount();
						ShowInventory.posRowCount = aRowCount;
						srcBtn.setEnabled(false);

						


					}
					


				}

				catch(Exception armand){
					JOptionPane.showMessageDialog(null, "Please enter valid input.", "Invalid Input", JOptionPane.WARNING_MESSAGE);

				}

			}

		}


		else if(e.getSource() == adder){

				
			posVector = new Vector<String>();
			posVector.add(itmcd);
			posVector.add(itmnm);
			posVector.add(itmst);
			posVector.add(itmpc);
			posVector.add(String.valueOf(posQuantity));

			double numPrice = Double.parseDouble(itmpc);
			double totalPc = numPrice*posQuantity;
			posVector.add(String.format("%.2f",totalPc));

			posDefTableModel.addRow(posVector);


			try{

				int intRc = posTable.getRowCount();

			int selRow = posTable.getSelectedRow();

			if(intRc == 0){}

			else{

				if(selRow == -1 && intRc == 0){}
				

				else{

					
					b = 0;
					
					for(int a = 0; a<=Integer.parseInt(String.valueOf(posTable.getRowCount())); a++){
					String strTotal = posTable.getValueAt(a,5).toString();
					double parNumTotal = Double.parseDouble(strTotal);

					b+=parNumTotal;
					String parB = String.format("\u20B1 %,.2f",b);
					byte[] charset = parB.getBytes("UTF-8"); 
					String newstr = new String(charset, "UTF-8");   
					lblNumTotal.setText(newstr);
					

						


					
					}

				}

				
				
			}

			}


			catch(Exception mand){
				
			}
			
			
			
		}


		else if(e.getSource() == transBtn[0]){
				int mm = posTable.getSelectedRow();

			if(posTable.getSelectedRow() == -1){
				JOptionPane.showMessageDialog(this,"Please Select row to delete.", "No Selected Row", JOptionPane.ERROR_MESSAGE);
			}
			else if(posTable.isRowSelected(mm)){
				int mainOption = JOptionPane.showConfirmDialog(this, "Are you sure to delete the selected row?", "Confirmation", JOptionPane.YES_NO_OPTION);

				if(mainOption == 0){
					String zzz = posTable.getValueAt(mm,5).toString();
					Double individual = Double.parseDouble(zzz);

					int posTblStock = Integer.parseInt(posTable.getValueAt(mm,4).toString());
					  

					String posItemCd = posTable.getValueAt(mm,0).toString();
				
					ShowInventory show = new ShowInventory();

					switch(posItemCd){
						case "00001":
							ShowInventory.stcPasser[0].doClick();
							rmAdd = showStock[0] + posTblStock;
							ShowInventory.ckBtn[0].doClick();
							break;

						case "00002":
							ShowInventory.stcPasser[1].doClick();
							rmAdd = showStock[1] + posTblStock;
							ShowInventory.ckBtn[1].doClick();
							break;

						case "00003":
							ShowInventory.stcPasser[2].doClick();
							rmAdd = showStock[2] + posTblStock;
							ShowInventory.ckBtn[2].doClick();
							break;



						case "00004":
							ShowInventory.stcPasser[3].doClick();
							rmAdd = showStock[3] + posTblStock;
							ShowInventory.ckBtn[3].doClick();
							break;



						case "00005":
							ShowInventory.stcPasser[4].doClick();
							rmAdd = showStock[4] + posTblStock;
							ShowInventory.ckBtn[4].doClick();
							break;


						case "00006":
							ShowInventory.stcPasser[5].doClick();
							rmAdd = showStock[5] + posTblStock;
							ShowInventory.ckBtn[5].doClick();
							break;



						case "00007":
							ShowInventory.stcPasser[6].doClick();
							rmAdd = showStock[6] + posTblStock;
							ShowInventory.ckBtn[6].doClick();
							break;


						case "00008":
							ShowInventory.stcPasser[7].doClick();
							rmAdd = showStock[7] + posTblStock;
							ShowInventory.ckBtn[7].doClick();
							break;



						case "00009":
							ShowInventory.stcPasser[8].doClick();
							rmAdd = showStock[8] + posTblStock;
							ShowInventory.ckBtn[8].doClick();
							break;



						case "00010":
							ShowInventory.stcPasser[9].doClick();
							rmAdd = showStock[9] + posTblStock;
							ShowInventory.ckBtn[9].doClick();
							break;



						case "00011":
							ShowInventory.stcPasser[10].doClick();
							rmAdd = showStock[10] + posTblStock;
							ShowInventory.ckBtn[10].doClick();
							break;


						case "00012":
							ShowInventory.stcPasser[11].doClick();
							rmAdd = showStock[11] + posTblStock;
							ShowInventory.ckBtn[11].doClick();
							break;




						case "00013":
							ShowInventory.stcPasser[12].doClick();
							rmAdd = showStock[12] + posTblStock;
							ShowInventory.ckBtn[12].doClick();
							break;




						case "00014":
							ShowInventory.stcPasser[13].doClick();
							rmAdd = showStock[13] + posTblStock;
							ShowInventory.ckBtn[13].doClick();
							break;


						case "00015":
							ShowInventory.stcPasser[14].doClick();
							rmAdd = showStock[14] + posTblStock;
							ShowInventory.ckBtn[14].doClick();
							break;



						case "00016":
							
							ShowInventory.stcPasser[15].doClick();
							rmAdd = showStock[15] + posTblStock;
							ShowInventory.ckBtn[15].doClick();
							break;



						case "00017":
							ShowInventory.stcPasser[16].doClick();
							rmAdd = showStock[16] + posTblStock;
							ShowInventory.ckBtn[16].doClick();
							break;


						case "00018":
							
							ShowInventory.stcPasser[17].doClick();

							rmAdd = showStock[17] + posTblStock;
							ShowInventory.ckBtn[17].doClick();
							break;


						case "00019":
							ShowInventory.stcPasser[18].doClick();
							

							rmAdd = showStock[18] + posTblStock;
							ShowInventory.ckBtn[18].doClick();
							break;


						case "00020":
							ShowInventory.stcPasser[19].doClick();
							

							rmAdd = showStock[19] + posTblStock;
							ShowInventory.ckBtn[19].doClick();
							break;





					}

					char peso = '\u20B1';
					double yyy = b-individual;
					String parB = String.format(peso + " %,.2f", yyy);
					lblNumTotal.setText(parB);

					ShowInventory showInventory = new ShowInventory();


					posDefTableModel.removeRow(mm);
					JOptionPane.showMessageDialog(this,"Successfully Deleted!", "Sucess!", JOptionPane.INFORMATION_MESSAGE);
					posTable.getSelectionModel().clearSelection();


					b-=individual;
					setter.doClick();

					int ctRow = posTable.getRowCount();
					if (ctRow<=0) {
						srcBtn.setEnabled(true);
					}

					
				}

			 }
		}



		else if(e.getSource() == transBtn[1]){
			Discount cd = new Discount();
			cd.userDiscount();
			transBtn[1].setEnabled(false);
			srcBtn.setEnabled(false);
		}


		else if (e.getSource() == transBtn[2]) {
			Payment pm = new Payment();
			pm.userPayment();


			Receipt.recRow = posTable.getRowCount();

			exp = new String [posTable.getRowCount()][6];

			for(int c=0; c<posTable.getRowCount(); c++){
			
				for (int d=0; d<6; d++) {
					
					exp[c][d] = posTable.getValueAt(c,d).toString();
					
				}
			}

		}

		else if (e.getSource() == transBtn[3]) {
			int closeOpt = JOptionPane.showConfirmDialog(this, "Are you sure to cancel? Pressing YES will return you to dashboard.", "Confirmation", JOptionPane.YES_NO_OPTION);
			if (closeOpt == 0) {
				
			dispose();
			ShowInventory sm = new ShowInventory();
			sm.dispose();

			Dashboard db = new Dashboard();
			db.userDashboard();

		}

			
		}



		else if(e.getSource() == setter){
			int rc = posTable.getRowCount();

			if(rc == 0){
							transBtn[1].setEnabled(false);
							transBtn[2].setEnabled(false);
					}

					else{
							transBtn[1].setEnabled(true);
							transBtn[2].setEnabled(true);
					}
		}


		else if(e.getSource() == hideClose){
			dispose();
		}
	}



}