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


public class Receipt extends JFrame implements ActionListener{
	static int recRow;

	JButton closeBtn;

	Font arial0b = new Font("Arial", Font.BOLD,10);
	Font arial12b = new Font("Arial", Font.BOLD,12);
	Font arial15b = new Font("Arial", Font.BOLD,16);
	Font arial13b = new Font("Arial", Font.BOLD,13);
	Font arial18b = new Font("Arial", Font.BOLD,18);
	Font arial23b = new Font("Arial", Font.BOLD,23);

	

	//this part is for the table
	String [][] recData = {
	};
	static String[] recHeader = {"Itm Cd", "Item Name", "Size", "Price", "Quantity", "Total"};


	DefaultTableModel recDefTableModel = new DefaultTableModel(POS.exp, recHeader);
	JTable recTable = new JTable(recDefTableModel);
	JScrollPane recScrollpane = new JScrollPane(recTable);

	Vector<String> recVector = new Vector<String>();

	DefaultTableCellRenderer rdr = new DefaultTableCellRenderer();

	JPanel expan;

	
	

	String [] texts = {"West Fortune Trading Corp.", "O'Donnell Capas, Tarlac Philippines 2315","-----Official Receipt-----","",""};
	JLabel[] lbl = new JLabel[texts.length];


	static String[] str = {"","","","",""};
	static JLabel[] labels = new JLabel[str.length]; 

	JLabel invoice;

	void clientReceipt(){

		setSize(400,600);
		setLocationRelativeTo(null);
		setLayout(null);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setResizable(false);
		setUndecorated(true);
		getContentPane().setBackground(Color.WHITE);

		 closeBtn = new JButton();
		 closeBtn.setBounds(350,0,50,20);
		 closeBtn.setBackground(new Color(0x800000));
		 closeBtn.setForeground(Color.WHITE);
		 closeBtn.setText("X");
		 closeBtn.setFocusable(false);
		 closeBtn.addActionListener(this);
		 add(closeBtn);


		JPanel expan = new JPanel();
		expan.setBounds(0,140,400,300);
		expan.setLayout(new BorderLayout());
		expan.add(recScrollpane);

		int lblY = 5;
		for(int a=0; a<=lbl.length-1;a++){
			lbl[a] = new JLabel();
			lbl[a].setText(texts[a]);
			lbl[a].setFont(arial15b);
			lbl[a].setBounds(0,lblY,400,20);
			lbl[a].setHorizontalAlignment(JLabel.CENTER);
			lbl[a].setForeground(POS.darkBlue);
			add(lbl[a]);
			lblY+=24;
		}

		 DateTimeFormatter times =DateTimeFormatter .ofPattern("hh : mm : ss a");
   		 LocalDateTime now =LocalDateTime.now();

   		  DateTimeFormatter dates =DateTimeFormatter .ofPattern("EEEE, MMMM dd, yyyy");
   		  LocalDateTime now1 =LocalDateTime.now();

		lbl[1].setFont(arial12b);
		lbl[2].setLocation(0,65);
		lbl[3].setBounds(10,95,200,20);
		lbl[3].setFont(arial0b);
		lbl[3].setText("DATE: "+dates.format(now1));

		lbl[4].setBounds(205,95,190,20);
		lbl[4].setFont(arial0b);
		lbl[4].setText("TIME: "+times.format(now));

		invoice = new JLabel();
		invoice.setText("Invoie Number: 1000000");
		invoice.setFont(arial0b);
		invoice.setBounds(0,115,400,20);
		invoice.setHorizontalAlignment(JLabel.CENTER);
		invoice.setForeground(POS.darkBlue);
		add(invoice);




		//this part is for the table
   		recTable.setFocusable(false);
		recTable.setRowSelectionAllowed(true);
		recTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		recTable.setRowHeight(25);

		recTable.setDefaultEditor(Object.class, null);
		recTable.getTableHeader().setEnabled(false);
		recTable.getTableHeader().setPreferredSize(new Dimension(50, 30));

		rdr.setHorizontalAlignment(JLabel.CENTER);


		//this are for experiment purppose only
		recTable.setShowHorizontalLines(false);
		recTable.setShowVerticalLines(false);
		// recTable.setTableHeader(null);

		recTable.getTableHeader().setBackground(Color.WHITE);


		for(int d=0; d<=recHeader.length-1; d++){
			recTable.getColumnModel().getColumn(d).setCellRenderer(rdr);
		}

		


		POS abc = new POS();


		int labelsY = 444;
		for(int g =0; g<=labels.length-1; g++){
			labels[g] = new JLabel();
			labels[g].setText(str[g]);
			labels[g].setBounds(0, labelsY, 400, 20);
			labels[g].setForeground(POS.darkBlue);
			labels[g].setFont(arial13b);
			labels[g].setHorizontalAlignment(JLabel.CENTER);
			add(labels[g]);
			labelsY+=32;
		
		}



		labels[0].setText("TOTAL..........: " + POS.lblNumTotal.getText());
		
		double samsam = Double.parseDouble(Payment.numPayment.getText());
		char peso = '\u20B1';

		labels[1].setText("PAYMENT........: " + String.format(peso+" %,.2f ",samsam));

		labels[2].setText("CHANGE........: " + Payment.parB);

		TableColumnModel tblmodel = recTable.getColumnModel();
		tblmodel.getColumn(0).setPreferredWidth(45);
		tblmodel.getColumn(1).setPreferredWidth(120);
		tblmodel.getColumn(2).setPreferredWidth(60);
		tblmodel.getColumn(3).setPreferredWidth(45);
		tblmodel.getColumn(4).setPreferredWidth(45);
		
		
		if (Discount.other == 0) {

			char peso1 = '\u20B1';
			double discountNew00 = Discount.parseCut * 0.03;
			labels[0].setText("TOTAL..........: " + String.format(peso1 + " %,.2f", Discount.parseCut));
			double samsam00 = Double.parseDouble(Payment.numPayment.getText());
			labels[1].setText("DISCOUNT.....: " + String.format(peso1+" %,.2f ",discountNew00) +" (St-3%)");
			labels[2].setText("NEW TOTAL........: " + String.format(peso1+" %,.2f ",Discount.parseCut - discountNew00));
			labels[3].setText("PAYMENT........: " + String.format(peso1+" %,.2f ",samsam00));

			labels[4].setText("CHANGE........: " + String.format(peso1+" %,.2f ",samsam00 - (Discount.parseCut - discountNew00)));
			
		}

		else if (Discount.other == 1) {
			char peso1 = '\u20B1';
			double discountNew1 = Discount.parseCut * 0.25;
			labels[0].setText("TOTAL..........: " + String.format(peso1 + " %,.2f", Discount.parseCut));
			double samsam1 = Double.parseDouble(Payment.numPayment.getText());
			labels[1].setText("DISCOUNT.....: " + String.format(peso1+" %,.2f ",discountNew1) +" (RC-25%)");
			labels[2].setText("NEW TOTAL........: " + String.format(peso1+" %,.2f ",Discount.parseCut - discountNew1));
			labels[3].setText("PAYMENT........: " + String.format(peso1+" %,.2f ",samsam1));

			labels[4].setText("CHANGE........: " + String.format(peso1+" %,.2f ",samsam1 - (Discount.parseCut - discountNew1)));
			
		}
	

	else if (Discount.other == 2) {
			char peso1 = '\u20B1';
			double discountNew2 = Discount.parseCut * 0.20;
			labels[0].setText("TOTAL..........: " + String.format(peso1 + " %,.2f", Discount.parseCut));
			double samsam2 = Double.parseDouble(Payment.numPayment.getText());
			labels[1].setText("DISCOUNT.....: " + String.format(peso1+" %,.2f ",discountNew2) +" (Sc/Pwd-3%)");

			labels[2].setText("NEW TOTAL........: " + String.format(peso1+" %,.2f ",Discount.parseCut - discountNew2));
			labels[3].setText("PAYMENT........: " + String.format(peso1+" %,.2f ",samsam2));

			labels[4].setText("CHANGE........: " + String.format(peso1+" %,.2f ",samsam2-(Discount.parseCut - discountNew2)));
			
		}
	

	else if (Discount.other == 3) {
			char peso1 = '\u20B1';
			double discountNew3 = Discount.parseCut * 0.15;
			labels[0].setText("TOTAL..........: " + String.format(peso1 + " %,.2f", Discount.parseCut));
			double samsam3 = Double.parseDouble(Payment.numPayment.getText());
			labels[1].setText("DISCOUNT.....: " + String.format(peso1+" %,.2f ",discountNew3) +" (Emp-15%)");

			labels[2].setText("NEW TOTAL........: " + String.format(peso1+" %,.2f ",Discount.parseCut - discountNew3));
			labels[3].setText("PAYMENT........: " + String.format(peso1+" %,.2f ",samsam3));

			labels[4].setText("CHANGE........: " + String.format(peso1+" %,.2f ",samsam3-(Discount.parseCut - discountNew3)));
			
		}
	
	

		add(expan);

		setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent gt){

		if(gt.getSource() == closeBtn){
			System.exit(0);

		}


	}


}
