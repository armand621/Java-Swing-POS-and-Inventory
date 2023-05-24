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


	static String[] str = {"","",""};
	static JLabel[] labels = new JLabel[str.length]; 

	


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


		int labelsY = 460;
		for(int g =0; g<=labels.length-1; g++){
			labels[g] = new JLabel();
			labels[g].setText(str[g]);
			labels[g].setBounds(0, labelsY, 400, 30);
			labels[g].setForeground(POS.darkBlue);
			labels[g].setFont(arial15b);
			labels[g].setHorizontalAlignment(JLabel.CENTER);
			add(labels[g]);
			labelsY+=40;
		
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
		
		// for(int c=0; c<recRow; c++){
		// 		Receipt rv = new Receipt();
		// 		for (int d =0; d<6 ;d++ ) {
		// 			POS cd = new POS();
		// 			// recVector.add(cd.posTable.getValueAt())
					
		// 			recVector.add(abc.posDefTableModel.getValueAt(c,d).toString());
		// 		}
		// 		recDefTableModel.addRow(recVector);
		// 		recVector = new Vector<String>();
		// 	}

	
		

		
			// for(int c=0; c<recRow; c++){
			// 	for (int d =0; d<6 ;d++ ) {
			// 		POS cd = new POS();
			// 		// recVector.add(cd.posTable.getValueAt())
			// 		System.out.println((cd.posVector.getDataVector().elementAt(c)).elementAt(d));
			// 	}
			// }

	
		



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
