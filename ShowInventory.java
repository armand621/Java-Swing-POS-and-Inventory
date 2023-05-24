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
	JButton expBtn;

	// static double numQuanti = POS.parsedQuantityNum;

	void inventory(){

		setSize(740,510);
		setLocationRelativeTo(null);
		setLayout(null);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
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

		// System.out.println(numQuanti);

		add(btnAdd);
		add(txtSearch);
		add(lblSearch);
		add(srcClear);
		add(closeBtn);
		add(expan);
		
		setVisible(false);

	}

	
	// int mm = table.getSelectedRow();

	void getTableValue(){
		int mm = table.getSelectedRow();
		if(table.getRowCount() == -1){
			String itmcd = table.getValueAt(mm,0).toString();
			String itmnm = table.getValueAt(mm,1).toString();
			String itmst = table.getValueAt(mm,4).toString();
		
			objPos.posDefTableModel.setValueAt(itmcd,0,0);
			objPos.posDefTableModel.setValueAt(itmcd,0,1);
			objPos.posDefTableModel.setValueAt(itmcd,0,2);
		}	
	}


	// int mm;
	static int code;
	static int posRowCount;

	@Override
	public void actionPerformed(ActionEvent e){

		if(e.getSource() == closeBtn){
			int closeOpt = JOptionPane.showConfirmDialog(null, "Are you sure to exit?", "Confirmation", JOptionPane.YES_NO_OPTION);
			if(closeOpt==0){
				setVisible(false);
				POS.srcBtn.setEnabled(true);
				// dispose();
				// System.exit(0);
			}
		}


		else if(e.getSource() == btnAdd){

			// int mm = table.getSelectedRow();

			if(table.getSelectedRow() == -1){
				JOptionPane.showMessageDialog(null,"Please select item to add");
			}

			else{
				// String stock = table.getValueAt(mm,5).toString();
				// int intStock = Integer.parseInt(stock);
				int mm = table.getSelectedRow();

				String strStock = table.getValueAt(mm,5).toString();
				System.out.println(strStock);
				// double oldStock = Double.parseDouble(strStock);

				System.out.println(POS.quantity.getText());


				int parseStock = Integer.parseInt(strStock);

				int newStock = parseStock - Integer.parseInt(POS.quantity.getText()) ;

				System.out.println(newStock);

				defTableModel.setValueAt(String.valueOf(newStock),0,0);
				// defTableModel.setValueAt(String.valueOf(oldStock-numQuanti),mm,5);



				// int stkVal = Integer.parseInt(permData[mm][5]);
				// permData[mm][5] = String.valueOf(stkVal - numQuanti);




				POS.itmcd = table.getValueAt(mm,0).toString();
				POS.itmnm = table.getValueAt(mm,1).toString();
				POS.itmst = table.getValueAt(mm,4).toString();
				POS.itmpc = table.getValueAt(mm,3).toString();

				POS.adder.doClick();
				setVisible(false);
				JOptionPane.showMessageDialog(null,"Successfully Added!");
				
				POS.srcBtn.setEnabled(true);
				POS.setter.doClick();
				POS.quantity.setText("");
				// numQuanti = POS.posQuantity;
				


				
				// numQuanti = 0;
				// srcClear.doClick();
				// expBtn.doClick();
				// int newStock = intStock - numQuanti;
				// defTableModel.setValueAt(String.valueOf(newStock), mm,5);
				// // setVisible(false);
				// System.out.println(newStock);
			}





		}





		else if(e.getSource() == srcClear){
			txtSearch.setText("");
		}


		else if (e.getSource() == expBtn){
			POS j = new POS();
			// System.out.println(code);
			
		

			
				// System.out.println("true");
				// System.out.println(ps.posDefTableModel.getRowCount());
				// System.out.println(posRowCount);

			

			
				// System.out.println("edadf");
			// System.out.println(POS.posTable.getValueAt(0,0).toString());
	

			// System.out.println("Successfully Clicked");

			// switch(code){
			// 	case "00001":
			// 		String next = ps.posTable.getValueAt(0,4).toString();
			// 		defTableModel.setValueAt(Integer.parseInt(next)-numQuanti,0,5);
			// }
			

	

		}












		//do not delete the two braces below
	}
}