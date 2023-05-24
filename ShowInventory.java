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

public class ShowInventory extends JFrame implements ActionListener{
	Font arial12b = new Font("Arial", Font.BOLD,12);
	Font arial15b = new Font("Arial", Font.BOLD,15);
	Font arial18b = new Font("Arial", Font.BOLD,18);


	Color gold = new Color(0xFCCB06);
	Color darkBlue = new Color(0x222E50);
	Color darkOrange = new Color(0xFF8C00);

	//this part was commented out because I am testing for the other process
	JButton closeBtn;

	//this part is for making an object of the class Frame, which consist of the table
	Frame objFr = new Frame();

	//this part is for making an object of the class POS, which contains the pos system
	POS objPos = new POS();


	//this part is for the header of the table
	String[] header = {"Item Code", "Item Name", "Item Description", "Price", "Size", "Stocks", "Re-Order Point", "Remarks"};



	//this part is for the data of the table
	String[][] permData = {

		{"00001", "Argentina Corn Beef", "In-can Corn Beef", "50", "175 grams", "100", "10", "High Stocks"},
		{"00002", "Rebisco Crackers", "Biscuit", "7", "30 grams", "50", "10", "High Stocks"},
		{"00003", "Nature Spring Mineral Water", "Mineral Water", "10", "500 Ml", "60", "10", "High Stocks"},
		{"00004", "LM Pancit Canton", "Noodles", "18", "100 grams", "90", "10", "High Stocks"},
		{"00005", "Fiesta Cooking Oil", "Palm-oil for cooking", "70", "300 Ml", "100", "10", "High Stocks"},
		{"00006", "555 Sardines", "In-can Sardines", "23", "130 grams", "70", "10", "High Stocks"},
		{"00007", "Pocari Sweat", "Electrolyte Water", "60", "500 Ml", "50", "10", "High Stocks"},
		{"00008", "Pinoy Bread", "Locally made bread", "50", "800 grams", "90", "10", "High Stocks"},
		{"00009", "Coca Cola", "Carbonated Drink", "20", "200 grams", "45", "10", "High Stocks"},
		{"00010", "Nissin Wafer", "Chocolate Wafer", "8", "100 grams", "70", "10", "High Stocks"},
		{"00011", "Nescafe Creamy White", "3 in 1 Coffee", "10", "95 grams", "100", "10", "High Stocks"},
		{"00012", "LM Beef", "Noodles", "12", "70 grams", "80", "10", "High Stocks"},
		{"00013", "Dowee Donut", "Donut bread", "15", "30 grams", "50", "10", "High Stocks"},
		{"00014", "Milo Champion", "Chocolate Drink", "10", "20 grams", "60", "10", "High Stocks"},
		{"00015", "Zesto", "Fruit Flavored Drink", "12", "200 grams", "100", "10", "High Stocks"},
		{"00016", "Cheese Ring", "Cheese crackers", "25", "100 grams", "80", "10", "High Stocks"},
		{"00017", "Clover Chips", "Cheese crackers", "10", "80 grams", "70", "10", "High Stocks"},
		{"00018", "Selecta Cornetto", "Ice cream", "25", "70 grams", "60", "10", "High Stocks"},
		{"00019", "Blue Drinking Water", "Flavored Water", "30", "500 Ml", "40", "10", "High Stocks"},
		{"00020", "Sky Flakes", "Crackers", "8", "40 grams", "100", "10", "High Stocks"},


	};

	//this part is for the defining of the table
	DefaultTableModel defTableModel = new DefaultTableModel(permData,header);
	JTable table = new JTable(defTableModel);
	JScrollPane scrollpane = new JScrollPane(table);

	Vector<String> vector = new Vector<String>();

	DefaultTableCellRenderer rdr = new DefaultTableCellRenderer();


	//this part is for the search component of the table
	JTextField txtSearch;
	JLabel lblSearch;
	JButton srcClear, btnAdd;

	//this part is for the experiment button
	static JButton expBtn;

	static boolean sV;


	static JButton gd,sd;

	static JButton[] stcPasser = new JButton[20];
	static JButton []ckBtn = new JButton[20];


	void inventory(){

		setSize(740,510);
		setLocationRelativeTo(null);
		setLayout(null);
		// setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setResizable(false);
		setUndecorated(true);
		getContentPane().setBackground(new Color(0xf3f0f0));
		


		JPanel expan = new JPanel();
		expan.setBounds(0,140,710,300);
		expan.setLayout(new BorderLayout());
		expan.add(scrollpane);

		//this part is for the close button
		closeBtn = new JButton();
		closeBtn.setBounds(690,0,50,20);
		closeBtn.setBackground(new Color(0x800000));
		closeBtn.setForeground(Color.WHITE);
		closeBtn.setText("X");
		closeBtn.setFocusable(false);
		closeBtn.addActionListener(this);

		//this part is for the experimental button
		expBtn = new JButton();
		expBtn.setBounds(10,10,100,20);
		expBtn.setVisible(false);
		expBtn.addActionListener(this);
		add(expBtn);


		gd = new JButton();
		gd.setBounds(122,10,100,20);
		gd.setVisible(false);
		gd.addActionListener(this);
		add(gd);


		
		for(int zx=0; zx<stcPasser.length; zx++){
			stcPasser[zx] = new JButton();
			stcPasser[zx].setBounds(122,10,100,20);
			stcPasser[zx].setVisible(false);
			stcPasser[zx].addActionListener(this);
			add(stcPasser[zx]);
			 

		}

		
		for(int vw =0; vw<stcPasser.length; vw++){
			ckBtn[vw] = new JButton();
			ckBtn[vw].setBounds(48,10,100,20);
			ckBtn[vw].setVisible(false);
			ckBtn[vw].addActionListener(this);
			add(ckBtn[vw]);
			 
			

		}





		sd = new JButton();
		sd.setBounds(122,10,100,20);
		sd.setVisible(false);
		sd.addActionListener(this);
		add(sd);




		//setting the properties for the table
		table.setDefaultEditor(Object.class, null);
		table.getTableHeader().setEnabled(false);
		table.getTableHeader().setPreferredSize(new Dimension(50, 30));

		rdr.setHorizontalAlignment(JLabel.CENTER);


		//declaring the table model property
		TableColumnModel tblmodel = table.getColumnModel();
		

		//setting up some properties for the table model
		for(int z=2; z<=5; z++){
			tblmodel.getColumn(z).setPreferredWidth(50);
		}
		tblmodel.getColumn(0).setPreferredWidth(50);
		tblmodel.getColumn(1).setPreferredWidth(130);
		tblmodel.getColumn(2).setPreferredWidth(80);
		tblmodel.getColumn(3).setPreferredWidth(40);

		 

		for(int d=0; d<=header.length-1; d++){
			table.getColumnModel().getColumn(d).setCellRenderer(rdr);
		}
		

		//setting up the table
		table.setFocusable(false);
		table.setRowSelectionAllowed(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setRowHeight(25);



		//this part is for the search
		//this part is for the txtSeach
		txtSearch = new JTextField();
		txtSearch.setBounds(50,60,300,35);
		txtSearch.setOpaque(false);
		txtSearch.setBorder(BorderFactory.createMatteBorder(0,0,2,0, darkBlue));
		txtSearch.setForeground(darkBlue);
		txtSearch.setFont(arial15b);

		//this part is for the label of the search item
		lblSearch = new JLabel();
		lblSearch.setBounds(50,20,300,30);
		lblSearch.setText("Search for item/s:");
		lblSearch.setFont(arial15b);
		lblSearch.setForeground(darkBlue);


		//this part is for the row sorter of the table

		TableRowSorter<TableModel> sort = new TableRowSorter<>(table.getModel());
		table.setRowSorter(sort);
		txtSearch.getDocument().addDocumentListener(new DocumentListener()
		    {
		            @Override
		            public void insertUpdate(DocumentEvent e) {
		                String str = txtSearch.getText();
		                if (str.trim().length() == 0) {
		                    sort.setRowFilter(null);
		                } else {
		                    //(?i) means case insensitive search
		                    sort.setRowFilter(RowFilter.regexFilter("(?i)" + str));
		                }
		            }
		            @Override
		            public void removeUpdate(DocumentEvent e) {
		                String str = txtSearch.getText();
		                if (str.trim().length() == 0) {
		                    sort.setRowFilter(null);
		                } else {
		                    sort.setRowFilter(RowFilter.regexFilter("(?i)" + str));
		                }
		            }
		            @Override
		            public void changedUpdate(DocumentEvent e) {}
		        });




		//this part is for the add item
		btnAdd = new JButton();
		btnAdd.setText("Add Item");
		btnAdd.setBounds(370,60, 100,30);
		btnAdd.addActionListener(this);
		btnAdd.setOpaque(true);
		btnAdd.setBackground(darkBlue);
		btnAdd.setForeground(gold);
		btnAdd.setFocusable(false);
		btnAdd.setFont(arial15b);

		//this part is for the search clear button
		srcClear = new JButton();
		srcClear.setText("Clear");
		srcClear.setBounds(490,60, 100,30);
		srcClear.addActionListener(this);
		srcClear.setOpaque(true);
		srcClear.setBackground(gold);
		srcClear.setForeground(darkBlue);
		srcClear.setFocusable(false);
		srcClear.setFont(arial15b);


		add(btnAdd);
		add(txtSearch);
		add(lblSearch);
		add(srcClear);
		add(closeBtn);
		add(expan);
		
		sV = false;
	

	}

	
	static int code;
	static int posRowCount;

	@Override
	public void actionPerformed(ActionEvent e){

		if(e.getSource() == closeBtn){
			int closeOpt = JOptionPane.showConfirmDialog(null, "Are you sure to exit?", "Confirmation", JOptionPane.YES_NO_OPTION);
			if(closeOpt==0){
				setVisible(false);
				POS.srcBtn.setEnabled(true);

				POS.transBtn[0].setEnabled(true);
				POS.transBtn[1].setEnabled(true);
				POS.transBtn[2].setEnabled(true);
				POS.transBtn[3].setEnabled(true);
			}
		}


		else if(e.getSource() == btnAdd){

			if(table.getSelectedRow() == -1){
				JOptionPane.showMessageDialog(null,"Please select item to add", "No Row Selected", JOptionPane.WARNING_MESSAGE);
			}

			else{
				
				int mm = table.getSelectedRow();

				String strStock = table.getValueAt(mm,5).toString();
				int parseStock = Integer.parseInt(strStock);

				if (parseStock < Integer.parseInt(POS.quantity.getText())) {
					JOptionPane.showMessageDialog(null,"Can't select item due to stock is less than quantity.", "Stock Problem",JOptionPane.ERROR_MESSAGE);
				}



				else{
				
				int newStock = parseStock - Integer.parseInt(POS.quantity.getText()) ;


				
				defTableModel.setValueAt(String.valueOf(newStock),mm,5);

					int reOrd = Integer.parseInt(table.getValueAt(0,6).toString());

					if(newStock<reOrd){
				
						defTableModel.setValueAt("Low Stocks",mm,7);
					}

					else{
						defTableModel.setValueAt("High Stocks",mm,7);
						
					}




				POS.itmcd = table.getValueAt(mm,0).toString();
				POS.itmnm = table.getValueAt(mm,1).toString();
				POS.itmst = table.getValueAt(mm,4).toString();
				POS.itmpc = table.getValueAt(mm,3).toString();

				POS.adder.doClick();
				setVisible(false);
				JOptionPane.showMessageDialog(null,"Successfully Added!","Sucess!", JOptionPane.INFORMATION_MESSAGE);
				table.getSelectionModel().clearSelection();
				
				POS.srcBtn.setEnabled(true);
				POS.setter.doClick();
				

				POS.quantity.setText("");
				POS.transBtn[0].setEnabled(true);
				POS.transBtn[1].setEnabled(true);
				POS.transBtn[2].setEnabled(true);
				POS.transBtn[3].setEnabled(true);

				
			}



			}





		}





		else if(e.getSource() == srcClear){
			txtSearch.setText("");
		}


		else if (e.getSource() == expBtn){
			if (sV == false) {
				setVisible(false);
				
			}

			else{
				setVisible(true);
			}
		
		}




		else if (e.getSource() == stcPasser[0]) {
			POS.showStock[0] = Integer.parseInt(table.getValueAt(0,5).toString());
		}

		else if (e.getSource() == stcPasser[1]) {
			POS.showStock[1] = Integer.parseInt(table.getValueAt(1,5).toString());
		}

		else if (e.getSource() == stcPasser[2]) {
			POS.showStock[2] = Integer.parseInt(table.getValueAt(2,5).toString());
		}

		else if (e.getSource() == stcPasser[3]) {
			POS.showStock[3] = Integer.parseInt(table.getValueAt(3,5).toString());
		}

		else if (e.getSource() == stcPasser[4]) {
			POS.showStock[4] = Integer.parseInt(table.getValueAt(4,5).toString());
		}

		else if (e.getSource() == stcPasser[5]) {
			POS.showStock[5] = Integer.parseInt(table.getValueAt(5,5).toString());
		}


		else if (e.getSource() == stcPasser[6]) {
			POS.showStock[6] = Integer.parseInt(table.getValueAt(6,5).toString());
		}


		else if (e.getSource() == stcPasser[7]) {
			POS.showStock[7] = Integer.parseInt(table.getValueAt(7,5).toString());
		}


		else if (e.getSource() == stcPasser[8]) {
			POS.showStock[8] = Integer.parseInt(table.getValueAt(8,5).toString());
		}


		else if (e.getSource() == stcPasser[9]) {
			POS.showStock[9] = Integer.parseInt(table.getValueAt(9,5).toString());
		}


		else if (e.getSource() == stcPasser[10]) {
			POS.showStock[10] = Integer.parseInt(table.getValueAt(10,5).toString());
		}


		else if (e.getSource() == stcPasser[11]) {
			POS.showStock[11] = Integer.parseInt(table.getValueAt(11,5).toString());
		}


		else if (e.getSource() == stcPasser[12]) {
			POS.showStock[12] = Integer.parseInt(table.getValueAt(12,5).toString());
		}


		else if (e.getSource() == stcPasser[13]) {
			POS.showStock[13] = Integer.parseInt(table.getValueAt(13,5).toString());
		}


		else if (e.getSource() == stcPasser[14]) {
			POS.showStock[14] = Integer.parseInt(table.getValueAt(14,5).toString());
		}


		else if (e.getSource() == stcPasser[15]) {
			POS.showStock[15] = Integer.parseInt(table.getValueAt(15,5).toString());
		}


		else if (e.getSource() == stcPasser[16]) {
			POS.showStock[16] = Integer.parseInt(table.getValueAt(16,5).toString());
		}


		else if (e.getSource() == stcPasser[17]) {
			POS.showStock[17] = Integer.parseInt(table.getValueAt(17,5).toString());
		}


		else if (e.getSource() == stcPasser[18]) {
			POS.showStock[18] = Integer.parseInt(table.getValueAt(18,5).toString());
		}

		else if (e.getSource() == stcPasser[19]) {
			POS.showStock[19] = Integer.parseInt(table.getValueAt(19,5).toString());
		}










		else if (e.getSource() == ckBtn[0]) {
			defTableModel.setValueAt(String.valueOf(POS.rmAdd),0,5);

			int reOrd = Integer.parseInt(table.getValueAt(0,6).toString());

					if(POS.rmAdd<reOrd){
				
						defTableModel.setValueAt("Low Stocks",0,7);
					}

					else{
						defTableModel.setValueAt("High Stocks",0,7);
						
					}


		}

		else if (e.getSource() == ckBtn[1]) {
			defTableModel.setValueAt(String.valueOf(POS.rmAdd),1,5);

			int reOrd = Integer.parseInt(table.getValueAt(1,6).toString());

					if(POS.rmAdd<reOrd){
				
						defTableModel.setValueAt("Low Stocks",1,7);
					}

					else{
						defTableModel.setValueAt("High Stocks",1,7);
						
					}
		}

		else if (e.getSource() == ckBtn[2]) {
			defTableModel.setValueAt(String.valueOf(POS.rmAdd),2,5);

			int reOrd = Integer.parseInt(table.getValueAt(2,6).toString());

					if(POS.rmAdd<reOrd){
				
						defTableModel.setValueAt("Low Stocks",2,7);
					}

					else{
						defTableModel.setValueAt("High Stocks",2,7);
						
					}
		}

		else if (e.getSource() == ckBtn[3]) {
			defTableModel.setValueAt(String.valueOf(POS.rmAdd),3,5);

			int reOrd = Integer.parseInt(table.getValueAt(3,6).toString());

					if(POS.rmAdd<reOrd){
				
						defTableModel.setValueAt("Low Stocks",3,7);
					}

					else{
						defTableModel.setValueAt("High Stocks",3,7);
						
					}
		}

		else if (e.getSource() == ckBtn[4]) {
			defTableModel.setValueAt(String.valueOf(POS.rmAdd),4,5);

			int reOrd = Integer.parseInt(table.getValueAt(4,6).toString());

					if(POS.rmAdd<reOrd){
				
						defTableModel.setValueAt("Low Stocks",4,7);
					}

					else{
						defTableModel.setValueAt("High Stocks",4,7);
						
					}
		}

		else if (e.getSource() == ckBtn[5]) {
			defTableModel.setValueAt(String.valueOf(POS.rmAdd),5,5);

			int reOrd = Integer.parseInt(table.getValueAt(5,6).toString());

					if(POS.rmAdd<reOrd){
				
						defTableModel.setValueAt("Low Stocks",5,7);
					}

					else{
						defTableModel.setValueAt("High Stocks",5,7);
						
					}
		}


		else if (e.getSource() == ckBtn[6]) {
			defTableModel.setValueAt(String.valueOf(POS.rmAdd),6,5);

			int reOrd = Integer.parseInt(table.getValueAt(6,6).toString());

					if(POS.rmAdd<reOrd){
				
						defTableModel.setValueAt("Low Stocks",6,7);
					}

					else{
						defTableModel.setValueAt("High Stocks",6,7);
						
					}
		}


		else if (e.getSource() == ckBtn[7]) {
			defTableModel.setValueAt(String.valueOf(POS.rmAdd),7,5);

			int reOrd = Integer.parseInt(table.getValueAt(7,6).toString());

					if(POS.rmAdd<reOrd){
				
						defTableModel.setValueAt("Low Stocks",7,7);
					}

					else{
						defTableModel.setValueAt("High Stocks",7,7);
						
					}
		}


		else if (e.getSource() == ckBtn[8]) {
			defTableModel.setValueAt(String.valueOf(POS.rmAdd),8,5);

			int reOrd = Integer.parseInt(table.getValueAt(8,6).toString());

					if(POS.rmAdd<reOrd){
				
						defTableModel.setValueAt("Low Stocks",8,7);
					}

					else{
						defTableModel.setValueAt("High Stocks",8,7);
						
					}
		}


		else if (e.getSource() == ckBtn[9]) {
			defTableModel.setValueAt(String.valueOf(POS.rmAdd),9,5);

			int reOrd = Integer.parseInt(table.getValueAt(9,6).toString());

					if(POS.rmAdd<reOrd){
				
						defTableModel.setValueAt("Low Stocks",9,7);
					}

					else{
						defTableModel.setValueAt("High Stocks",9,7);
						
					}
		}


		else if (e.getSource() == ckBtn[10]) {
			defTableModel.setValueAt(String.valueOf(POS.rmAdd),10,5);

			int reOrd = Integer.parseInt(table.getValueAt(10,6).toString());

					if(POS.rmAdd<reOrd){
				
						defTableModel.setValueAt("Low Stocks",10,7);
					}

					else{
						defTableModel.setValueAt("High Stocks",10,7);
						
					}
		}


		else if (e.getSource() == ckBtn[11]) {
			defTableModel.setValueAt(String.valueOf(POS.rmAdd),11,5);

			int reOrd = Integer.parseInt(table.getValueAt(11,6).toString());

					if(POS.rmAdd<reOrd){
				
						defTableModel.setValueAt("Low Stocks",11,7);
					}

					else{
						defTableModel.setValueAt("High Stocks",11,7);
						
					}
		}


		else if (e.getSource() == ckBtn[12]) {
			defTableModel.setValueAt(String.valueOf(POS.rmAdd),12,5);

			int reOrd = Integer.parseInt(table.getValueAt(12,6).toString());

					if(POS.rmAdd<reOrd){
				
						defTableModel.setValueAt("Low Stocks",12,7);
					}

					else{
						defTableModel.setValueAt("High Stocks",12,7);
						
					}
		}


		else if (e.getSource() == ckBtn[13]) {
			defTableModel.setValueAt(String.valueOf(POS.rmAdd),13,5);

			int reOrd = Integer.parseInt(table.getValueAt(13,6).toString());

					if(POS.rmAdd<reOrd){
				
						defTableModel.setValueAt("Low Stocks",13,7);
					}

					else{
						defTableModel.setValueAt("High Stocks",13,7);
						
					}
		}


		else if (e.getSource() == ckBtn[14]) {
			defTableModel.setValueAt(String.valueOf(POS.rmAdd),14,5);

			int reOrd = Integer.parseInt(table.getValueAt(14,6).toString());

					if(POS.rmAdd<reOrd){
				
						defTableModel.setValueAt("Low Stocks",14,7);
					}

					else{
						defTableModel.setValueAt("High Stocks",14,7);
						
					}
		}


		else if (e.getSource() == ckBtn[15]) {
			defTableModel.setValueAt(String.valueOf(POS.rmAdd),15,5);

			int reOrd = Integer.parseInt(table.getValueAt(15,6).toString());

					if(POS.rmAdd<reOrd){
				
						defTableModel.setValueAt("Low Stocks",15,7);
					}

					else{
						defTableModel.setValueAt("High Stocks",15,7);
						
					}
		}


		else if (e.getSource() == ckBtn[16]) {
			defTableModel.setValueAt(String.valueOf(POS.rmAdd),16,5);

			int reOrd = Integer.parseInt(table.getValueAt(16,6).toString());

					if(POS.rmAdd<reOrd){
				
						defTableModel.setValueAt("Low Stocks",16,7);
					}

					else{
						defTableModel.setValueAt("High Stocks",16,7);
						
					}
		}


		else if (e.getSource() == ckBtn[17]) {
			defTableModel.setValueAt(String.valueOf(POS.rmAdd),17,5);

			int reOrd = Integer.parseInt(table.getValueAt(17,6).toString());

					if(POS.rmAdd<reOrd){
				
						defTableModel.setValueAt("Low Stocks",17,7);
					}

					else{
						defTableModel.setValueAt("High Stocks",17,7);
						
					}
		}


		else if (e.getSource() == ckBtn[18]) {
			defTableModel.setValueAt(String.valueOf(POS.rmAdd),18,5);

			int reOrd = Integer.parseInt(table.getValueAt(18,6).toString());

					if(POS.rmAdd<reOrd){
				
						defTableModel.setValueAt("Low Stocks",18,7);
					}

					else{
						defTableModel.setValueAt("High Stocks",18,7);
						
					}
		}

		else if (e.getSource() == ckBtn[19]) {
			defTableModel.setValueAt(String.valueOf(POS.rmAdd),19,5);

			int reOrd = Integer.parseInt(table.getValueAt(19,6).toString());

					if(POS.rmAdd<reOrd){
				
						defTableModel.setValueAt("Low Stocks",19,7);
					}

					else{
						defTableModel.setValueAt("High Stocks",19,7);
						
					}
		}


		//do not delete the two braces below
	}
}