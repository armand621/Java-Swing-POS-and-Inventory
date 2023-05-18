//Inventory System & POS
//Programmer: Armand Robin I. Tangonan
//github.com/armand621


import java.util.regex.*;
import java.util.*;
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

	Color gold = new Color(0xFCCB06);
	Color darkBlue = new Color(0x222E50);
	Color darkOrange = new Color(0xFF8C00);

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
	JLabel lblTotal, lblNumTotal;

	//this part is for the transaction buttons
	String [] strTransBtn = {"Remove Item", "Discount", "Payment", "Cancel"};
	JButton [] transBtn = new JButton[strTransBtn.length];


	//this part is for labels
	String [] strMainLbl = {"Quantity", "Search Item", "Invoice No.", ""};
	JLabel [] mainLbl = new JLabel[strMainLbl.length];


	//this part is for the textfield of the quantity
	JTextField quantity;

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

	// static JButton setter;


	//this part will be commented out since it was just for sample purpose only
	// JScrollPane scrollpane = new JScrollPane(frObj.table);

	int b;

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



		// setter = new JButton();
		// setter.setBounds(20,0,10,20);
		// setter.setVisible(false);
		// setter.addActionListener(this);
		// add(setter);

		 closeBtn = new JButton();
		 closeBtn.setBounds(1200,0,50,20);
		 closeBtn.setBackground(new Color(0x800000));
		 closeBtn.setForeground(Color.WHITE);
		 closeBtn.setText("X");
		 closeBtn.setFocusable(false);
		 closeBtn.addActionListener(this);
		 add(closeBtn);




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


 	int posQuantity;
	

	@Override
	public void actionPerformed(ActionEvent e){

		if(e.getSource() == closeBtn){
			int closeOpt = JOptionPane.showConfirmDialog(this, "Are you sure to exit?", "Confirmation", JOptionPane.YES_NO_OPTION);
			if(closeOpt==0){
				dispose();
			}
		}



		else if(e.getSource() == back2Dash){



			Dashboard dsh = new Dashboard();
			dsh.userDashboard();
			dispose();

			




		}


		else if (e.getSource() == srcBtn){

			 if(quantity.getText().equals("")){
		    	JOptionPane.showMessageDialog(this,"Please enter number into quantity.");		    	
		    }


			 else if(quantity.getText().isEmpty()){
		    	JOptionPane.showMessageDialog(this,"Please enter number into quantity.");		    	
		    }

		     else if(quantity.getText().isBlank()){
		    	JOptionPane.showMessageDialog(this,"Please enter number into quantity.");		    	
		    }

		    else{
			//this is for the try and catch
				try{
					int parsedQuantityNum = Integer.parseInt(quantity.getText());
					ShowInventory sh = new ShowInventory();
					sh.inventory();
					// sh.expBtn.doClick();
					sh.numQuanti = parsedQuantityNum;
					posQuantity = parsedQuantityNum;

					
					int aRowCount = posTable.getRowCount();
					ShowInventory.posRowCount = aRowCount;
					srcBtn.setEnabled(false);

					// ShowInventory.code = Integer.parseInt(String.valueOf(posTable.getValueAt(0,0)));
					// // setter.doClick();


					// sh.setVisible(true);


				}

				catch(Exception armand){
					JOptionPane.showMessageDialog(null, "Please enter number");

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

			int numPrice = Integer.parseInt(itmpc);
			int totalPc = numPrice*posQuantity;
			posVector.add(String.valueOf(totalPc));

			posDefTableModel.addRow(posVector);

			// int b = 0;
			// for(int a = 0; a<=Integer.parseInt(String.valueOf(posTable.getRowCount())); a++){
			// int valueTotal = Integer.parseInt(String.valueOf(posDefTableModel.getValueAt(a,5)));
			// b+=valueTotal;
			// lblNumTotal.setText(String.valueOf(b));
			// }

			try{

				int intRc = posTable.getRowCount();

			int selRow = posTable.getSelectedRow();

			if(intRc == 0){}

			else{

				if(selRow == -1 && intRc == 0){System.out.println("Yes");}
				

				else{

					
					b = 0;
					
					for(int a = 0; a<=Integer.parseInt(String.valueOf(posTable.getRowCount())); a++){
					String strTotal = posTable.getValueAt(a,5).toString();
					int parNumTotal = Integer.parseInt(strTotal);

					// int valueTotal = Integer.parseInt(String.valueOf(posDefTableModel.getValueAt(a,5)));
					b+=parNumTotal;
					lblNumTotal.setText(String.valueOf(b));
					

						


					
					}

				}

				
				
			}

			}


			catch(Exception mand){
				
			}
			
			
			



			// if(Integer.parseInt(String.valueOf(posTable.getSelectedRow())) == -1){
			// 	if(Integer.parseInt(String.valueOf(posTable.getRowCount())) ==0){
			// 		System.out.println("0");
			// 	}

			// 	else{
			// 		System.out.println("1");

			// 		int b = 0;
			// 		int c = 0;
			// 		for(int a = 0; a<=Integer.parseInt(String.valueOf(posTable.getRowCount())); a++){
			// 		String strTotal = posDefTableModel.getValueAt(c,5).toString();
			// 		int parNumTotal = Integer.parseInt(strTotal);

			// 		// int valueTotal = Integer.parseInt(String.valueOf(posDefTableModel.getValueAt(a,5)));
			// 		b+=parNumTotal;
			// 		lblNumTotal.setText(String.valueOf(b));
			// 		c++;
			// 		}
			// 	}


			// }

			
				
					// int b = 0;
					// for(int a = 0; a<=Integer.parseInt(String.valueOf(posTable.getRowCount())); a++){
					// int valueTotal = Integer.parseInt(posDefTableModel.getValueAt(a,5).toString());
					// b+=valueTotal;
					// lblNumTotal.setText(String.valueOf(b));
					// }

				
				

			

			// if(Integer.parseInt(String.valueOf(posTable.getRowCount())) ==0){}

			// else{
			// 	if (Integer.parseInt(String.valueOf(posTable.getSelectedRow())) == -1){

			// 	}


			// 	else{
			// 		int b = 0;
			// 		for(int a = 0; a<=Integer.parseInt(String.valueOf(posTable.getRowCount())); a++){
			// 		int valueTotal = Integer.parseInt(posDefTableModel.getValueAt(a,5).toString());
			// 		b+=valueTotal;
			// 		lblNumTotal.setText(String.valueOf(b));
			// 		}
			// 	}
			// }

			// else if (Integer.parseInt(String.valueOf(posTable.getSelectedRow())) == -1){

			// }

			// else{
			// 	int b = 0;
			// 	for(int a = 0; a<=Integer.parseInt(String.valueOf(posTable.getRowCount())); a++){
			// 	int valueTotal = Integer.parseInt(posDefTableModel.getValueAt(a,5).toString());
			// 	b+=valueTotal;
			// 	lblNumTotal.setText(String.valueOf(b));
			// 	}
			// }


			









			// lblNumTotal.setText(String.valueOf(totalPc));
			// System.out.println(itmcd);
			// System.out.println(itmnm);
			// System.out.println(itmst);
		}


		else if(e.getSource() == transBtn[0]){
				int mm = posTable.getSelectedRow();

			if(posTable.getSelectedRow() == -1){
				JOptionPane.showMessageDialog(this,"Select row to delete.");
			}
			else if(posTable.isRowSelected(mm)){
				int mainOption = JOptionPane.showConfirmDialog(this, "Are you sure to delete the selected row?", "Confirmation", JOptionPane.YES_NO_OPTION);

				if(mainOption == 0){
					int individual = Integer.parseInt(posTable.getValueAt(mm,5).toString());
					lblNumTotal.setText(String.valueOf(b-individual));

					posDefTableModel.removeRow(mm);
					JOptionPane.showMessageDialog(this,"Successfully Deleted!");
					posTable.getSelectionModel().clearSelection();

					b-=individual;

					
				}

			 }
		}


	
	}



}