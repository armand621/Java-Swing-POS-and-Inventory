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

public class Frame extends JFrame implements ActionListener{
	JButton close;

	String[] txtlabels= {"Inventory", "Item Code:","Item Name", "Item Description:", "Price:", "Size:", "Stocks:", "Re-Order Point:"};
	JLabel[] labels = new JLabel[txtlabels.length];

	JTextField [] fields = new JTextField[labels.length-1];

	String[] txtbtn = {"Stock In", "Add", "Edit", "Delete", "Save", "Cancel"};
	JButton[] btn = new JButton[txtbtn.length];

	String[][] permData = {

		{"00001", "Argentina Corn Beef", "In-can Corn Beef", "P50", "175 grams", "100", "10", "High Stocks"},
		{"00002", "Rebisco Crackers", "Biscuit", "P7", "30 grams", "50", "10", "High Stocks"},
		{"00003", "Nature Spring Mineral Water", "Mineral Water", "P10", "500 Ml", "60", "10", "High Stocks"},
		{"00004", "LM Pancit Canton", "Noodles", "P18", "100 grams", "90", "10", "High Stocks"},
		{"00005", "Fiesta Cooking Oil", "Palm-oil for cooking", "P70", "300 Ml", "100", "10", "High Stocks"},
		{"00006", "555 Sardines", "In-can Sardines", "P23", "130 grams", "70", "10", "High Stocks"},
		{"00007", "Pocari Sweat", "Electrolyte Water", "P60", "500 Ml", "50", "10", "High Stocks"},
		{"00008", "Pinoy Bread", "Locally made bread", "P50", "800 grams", "90", "10", "High Stocks"},
		{"00009", "Coca Cola", "Carbonated Drink", "P20", "200 grams", "45", "10", "High Stocks"},
		{"00010", "Nissin Wafer", "Chocolate Wafer", "P8", "100 grams", "70", "10", "High Stocks"},
		{"00011", "Nescafe Creamy White", "3 in 1 Coffee", "P10", "95 grams", "100", "10", "High Stocks"},
		{"00012", "LM Beef", "Noodles", "P12", "70 grams", "80", "10", "High Stocks"},
		{"00013", "Dowee Donut", "Donut bread", "P15", "30 grams", "50", "10", "High Stocks"},
		{"00014", "Milo Champion", "Chocolate Drink", "P10", "20 grams", "60", "10", "High Stocks"},
		{"00015", "Zesto", "Fruit Flavored Drink", "P12", "200 grams", "100", "10", "High Stocks"},
		{"00016", "Cheese Ring", "Cheese crackers", "P25", "100 grams", "80", "10", "High Stocks"},
		{"00017", "Clover Chips", "Cheese crackers", "P10", "80 grams", "70", "10", "High Stocks"},
		{"00018", "Selecta Cornetto", "Ice cream", "P25", "70 grams", "60", "10", "High Stocks"},
		{"00019", "Blue Drinking Water", "Flavored Water", "P30", "500 Ml", "40", "10", "High Stocks"},
		{"00020", "Sky Flakes", "Crackers", "P8", "40 grams", "100", "10", "High Stocks"},


	};

	Font arial12b = new Font("Arial", Font.BOLD,12);
	Font arial15b = new Font("Arial", Font.BOLD,15);
	Font arial18b = new Font("Arial", Font.BOLD,18);
	int fieldWidth = 200;
	int fieldHeight = 40;
	int itcd = 21;


	DecimalFormat decfor = new DecimalFormat("00000");

	String[] header = {"Item Code", "Item Name", "Item Description", "Price", "Size", "Stocks", "Re-Order Point", "Remarks"};

	DefaultTableModel defTableModel = new DefaultTableModel(permData,header);
	JTable table = new JTable(defTableModel);
	JScrollPane scrollpane = new JScrollPane(table);

	Vector<String> vector = new Vector<String>();

	JPanel tblPanel;

	DefaultTableCellRenderer rdr = new DefaultTableCellRenderer();

	JButton pass = new JButton();
	JButton editSave = new JButton();
	JButton cancel2 = new JButton();

	JTextField txtSearch;
	JLabel lblSearch;
	JButton srcClear;

	Color gold = new Color(0xFCCB06);
	Color darkBlue = new Color(0x222E50);
	Color darkOrange = new Color(0xFF8C00);

	JButton back2Dash = new JButton();



//This is the declaration for the main frame
	void frameInventory(){

		

		//the main frame properties
		setSize(1250,600);
		setLocationRelativeTo(null);
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setUndecorated(true);
		getContentPane().setBackground(gold);

		//declaring the properties for the close button
		close = new JButton();
		close.setText("X");
		close.setBounds(1200,0,50,20);
		close.setOpaque(true);
		close.setBackground(Color.RED);
		close.setForeground(Color.WHITE);
		close.setFocusable(false);
		close.addActionListener(this);

		//experimental button
		tryclick.addActionListener(this);
		add(tryclick);


		//declaring the properties for the table panel
		tblPanel = new JPanel();
		tblPanel.setBounds(450,100,790,420);
		tblPanel.setLayout(new BorderLayout());
		tblPanel.add(scrollpane);


		//iteration for the labels
		int y = 50;
		for(int i = 0; i<=labels.length-1; i++){
			labels[i] = new JLabel();
			labels[i].setBounds(50,y,130,30);
			labels[i].setText(txtlabels[i]);
			labels[i].setFont(arial15b);
			y+=50;
			add(labels[i]);

		}

		labels[0].setBounds(10,20,415,40);
		labels[0].setHorizontalAlignment(JLabel.CENTER);
		labels[0].setForeground(gold);
		labels[0].setBackground(darkBlue);
		labels[0].setOpaque(true);
		labels[0].setFont(arial18b);


		//iteration for the text fields
		int fieldY = 95;
		for(int a = 0; a<=fields.length-1; a++){
			fields[a] = new JTextField();
			fields[a].setBounds(220,fieldY,fieldWidth,fieldHeight);
			fields[a].setFont(arial15b);
			fields[a].setEditable(false);
			fields[a].setOpaque(false);
			fields[a].setBorder(BorderFactory.createMatteBorder(0,0,2,0, maroon));
			fieldY +=50;
			add(fields[a]);
		}

		

		//iteration for the buttons
		int btnX = 60;
		int btnX1 = 60;
		for(int b=0; b<=btn.length-1; b++){
			btn[b] = new JButton();
			btn[b].setText(txtbtn[b]);
			btn[b].setBounds(btnX,480,120,30);
			btn[b].setFont(arial15b);
			btn[b].setFocusable(false);
			
			btn[b].addActionListener(this);
			add(btn[b]);
			if(b >= 3){
				btn[b].setBounds(btnX1,530,120,30);
				btnX1+=121;
				add(btn[b]);
			}
			btnX+=121;
			
		}

		btn[4].setEnabled(false);
		btn[5].setEnabled(false);

		//setting the color of the buttons

		for(int aa=0; aa<=5; aa++){
			btn[aa].setBackground(darkBlue);
			btn[aa].setForeground(gold);
			btn[aa].setBorder(BorderFactory.createLineBorder(darkOrange,1));

		}

		btn[3].setBackground(maroon);
		btn[3].setForeground(gold);


		btn[4].setBackground(new Color(0x028A0F));
		btn[4].setForeground(gold);

		editSave.setBackground(new Color(0x028A0F));
		editSave.setForeground(gold);

		btn[5].setBackground(darkOrange);
		btn[5].setForeground(darkBlue);

		//this button is for the 2nd cancel button
		cancel2.setBackground(darkOrange);
		cancel2.setForeground(darkBlue);
		cancel2.setBounds(302,530,120,30);
		cancel2.setText("Cancel");
		cancel2.setFont(arial15b);
		cancel2.addActionListener(this);
		cancel2.setVisible(false);
		add(cancel2);

		//this part is for the back to dashboard button
		back2Dash.setBounds(15,570,180,20);
		back2Dash.setText("<html><u>Back to Dashboard</u></html>");
		back2Dash.setOpaque(true);
		back2Dash.setForeground(darkBlue);
		back2Dash.setBackground(gold);
		back2Dash.setBorder(null);
		back2Dash.setFocusable(false);
		back2Dash.addActionListener(this);
		add(back2Dash);

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

		//setting up some properties for the hidden butons
		editSave.addActionListener(this);
		editSave.setBounds(181,530,120,30);
		editSave.setText("Save");
		editSave.setVisible(false);
		editSave.setFont(arial15b);
		

		//adding list listener to the table
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){

			@Override
			public void valueChanged(ListSelectionEvent e){

				int a = table.getSelectedRow();
			if(a == -1){
				
			}
			else{
				for(int z =0; z<=fields.length-1; z++){
				fields[z].setText((String) table.getValueAt(a,z));
				
				}
			
			

			}
			}

		});


		//this part is for the txtSeach
		txtSearch = new JTextField();
		txtSearch.setBounds(480,50,600,35);
		txtSearch.setOpaque(false);
		txtSearch.setBorder(BorderFactory.createMatteBorder(0,0,2,0, darkBlue));
		txtSearch.setForeground(darkBlue);
		txtSearch.setFont(arial15b);

		//this part is for the label of the search item
		lblSearch = new JLabel();
		lblSearch.setBounds(450,20,300,30);
		lblSearch.setText("Search for item/s:");
		lblSearch.setFont(arial15b);
		lblSearch.setForeground(darkBlue);

		//this part is for the search clear button
		srcClear = new JButton();
		srcClear.setText("Clear");
		srcClear.setBounds(1100,57, 100,30);
		srcClear.addActionListener(this);
		srcClear.setOpaque(true);
		srcClear.setBackground(darkBlue);
		srcClear.setForeground(gold);
		srcClear.setFocusable(false);
		srcClear.setFont(arial15b);

		add(srcClear);


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


		

		//this part is for adding elements to the frame
		add(editSave);
		add(close);
		add(tblPanel);
		add(txtSearch);
		add(lblSearch);


		//this property must be always at the end to prevent bugs 
		setVisible(true);

	}


	//declaring two int with empty value at this moment
	int fld5Num, fld4Num;
	Color maroon = new Color(0x800000);
	int mmm;
	



	//implementing the action listener for the buttons
	@Override
	public void actionPerformed(ActionEvent e){

		if(e.getSource() == close){

			int closeOpt = JOptionPane.showConfirmDialog(this, "Are you sure to exit?", "Confirmation", JOptionPane.YES_NO_OPTION);
			if(closeOpt==0){
				dispose();
			}
		}



		//this part is for the Stock In button
		else if(e.getSource() == btn[0]){
			int row = table.getSelectedRow();
			if(row == -1){
				JOptionPane.showMessageDialog(this, "Please select row to add stocks.", "Row Selection Invalid", JOptionPane.ERROR_MESSAGE);
			}

			else{

				try{

					String stock = JOptionPane.showInputDialog("Enter Stocks:");
					String stockval = table.getValueAt(row,5).toString();
					int intStock = Integer.parseInt(stockval);
					int totalStock = Integer.parseInt(stock) + intStock;

					JOptionPane.showMessageDialog(this, "Successfully Added Stocks!");
					defTableModel.setValueAt(String.valueOf(totalStock), row,5);
					String val = table.getValueAt(row,6).toString();

					if(totalStock<Integer.parseInt(val)){
						defTableModel.setValueAt("Low Stock", row,7);
					}

					else if(totalStock==Integer.parseInt(val)){
						defTableModel.setValueAt("Low Stock", row,7);
					}

					else if(totalStock>Integer.parseInt(val)){
						defTableModel.setValueAt("High Stock", row,7);
					}

					for(int a=0; a<=fields.length-1; a++){
						fields[a].setText("");
				
					}
					table.getSelectionModel().clearSelection();

				}

				catch(Exception k){
					JOptionPane.showMessageDialog(this, "Please enter a number.", "Invalid Input", JOptionPane.WARNING_MESSAGE);
					table.getSelectionModel().clearSelection();
					for(int a=0; a<=fields.length-1; a++){
						fields[a].setText("");
				
					}
				}


			}
		}




		//this part is for the Add button
		else if(e.getSource() == btn[1]){

			fields[0].setText(decfor.format(itcd));

			itcd+=1;

			for(int a=1; a<=fields.length-1; a++){
				fields[a].setEditable(true);
				fields[a].setText("");
				fields[a].setBorder(BorderFactory.createMatteBorder(0,0,2,0, darkBlue));
				fields[a].setForeground(darkBlue);
			}

			fields[0].setForeground(maroon);

			for(int b=0; b<=3; b++){
				btn[b].setEnabled(false);
			}

			vector = new Vector<String>();
			btn[4].setEnabled(true);
			btn[5].setEnabled(true);
	 
		}


		//this part is for the edit button
		else if(e.getSource() == btn[2]){

			if(table.getSelectedRow() == -1){
				JOptionPane.showMessageDialog(this,"Select row to edit.");
			}

			else{

				for(int a=0; a<=fields.length-1;a++){
					fields[a].setEditable(true);
					fields[a].setBorder(BorderFactory.createMatteBorder(0,0,2,0, darkBlue));
					fields[a].setForeground(darkBlue);
				}

				fields[0].setEditable(false);
				fields[0].setForeground(maroon);
				fields[0].setBorder(BorderFactory.createMatteBorder(0,0,2,0, maroon));

				fields[5].setEditable(false);
				fields[5].setForeground(maroon);
				fields[5].setBorder(BorderFactory.createMatteBorder(0,0,2,0, maroon));
				
				btn[4].setVisible(false);
				editSave.setVisible(true);

				for(int r=0; r<=btn.length-1; r++){
					btn[r].setEnabled(false);
				}
				btn[5].setVisible(false);
				btn[5].setEnabled(true);

				cancel2.setVisible(true);


			}

		}


		//this part is for the 2nd save button, which will show up after you click the edit button
		else if(e.getSource() == editSave){
			

			try{

				 if(fields[0].getText().isBlank() || fields[1].getText().isBlank() || fields[2].getText().isBlank() || fields[3].getText().isBlank() || fields[4].getText().isBlank() || fields[5].getText().isBlank() || fields[6].getText().isBlank()){
		    		JOptionPane.showMessageDialog(this,"Please fill up all the inputs correctly.");		    	
		    		}

		    	else{
				int row = table.getSelectedRow();
				int fld4 = Integer.parseInt(fields[5].getText());
				int fld5 = Integer.parseInt(fields[6].getText());

				

				for(int b=0; b<= 3; b++){

					defTableModel.setValueAt(fields[b].getText(), row, b);
				}

				defTableModel.setValueAt(String.valueOf(fld4), row, 5);
				defTableModel.setValueAt(String.valueOf(fld5), row, 6);


					if(fld4<fld5){
						defTableModel.setValueAt("Low Stock", row,7);
					}

					else if(fld4==fld5){
						defTableModel.setValueAt("Low Stock", row,7);
					}

					else if(fld4>fld5){
						defTableModel.setValueAt("High Stock", row,7);
					}

					JOptionPane.showMessageDialog(this,"Successfully Edited Row!");
					table.getSelectionModel().clearSelection();
					for(int h = 0; h<=3; h++){
						btn[h].setEnabled(true);
					}
					btn[5].setEnabled(false);
					// btn[2].setVisible(true);
					editSave.setVisible(false);
					btn[4].setVisible(true);
					btn[4].setEnabled(false);

					for(int i=0; i<=fields.length-1; i++){
					fields[i].setText("");
					fields[i].setEditable(false);
					fields[i].setBorder(BorderFactory.createMatteBorder(0,0,2,0, maroon));
					fields[i].setForeground(maroon);
					}
				}

			}
			catch(Exception r){
				JOptionPane.showMessageDialog(this,"Stocks and Re-Order Point must be number only.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
					
			}
			
			


		}


		//this part is for the delete button
		else if(e.getSource() == btn[3]){

			int mm = table.getSelectedRow();

			if(table.getSelectedRow() == -1){
				JOptionPane.showMessageDialog(this,"Select row to delete.");
			}
			else if(table.isRowSelected(mm)){
				int mainOption = JOptionPane.showConfirmDialog(this, "Are you sure to delete the selected row?", "Confirmation", JOptionPane.YES_NO_OPTION);

				if(mainOption == 0){
					defTableModel.removeRow(mm);
					JOptionPane.showMessageDialog(this,"Successfully Deleted!");
					for(int i=0; i<=fields.length-1; i++){
					fields[i].setText("");
					}
					table.getSelectionModel().clearSelection();
				}

			 }


		}


		//this part is for the Save button
		else if(e.getSource() == btn[4]){

			 if(fields[0].getText().equals("") || fields[1].getText().equals("") || fields[2].getText().equals("") || fields[3].getText().equals("") || fields[4].getText().equals("") || fields[5].getText().equals("") || fields[6].getText().equals("")){
		    	JOptionPane.showMessageDialog(this,"Please fill up all the inputs correctly.");		    	
		    }


			 else if(fields[0].getText().isEmpty() || fields[1].getText().isEmpty() || fields[2].getText().isEmpty() || fields[3].getText().isEmpty() || fields[4].getText().isEmpty() || fields[5].getText().isEmpty()|| fields[6].getText().isEmpty()){
		    	JOptionPane.showMessageDialog(this,"Please fill up all the inputs correctly.");		    	
		    }

		     else if(fields[0].getText().isBlank() || fields[1].getText().isBlank() || fields[2].getText().isBlank() || fields[3].getText().isBlank() || fields[4].getText().isBlank() || fields[5].getText().isBlank() || fields[6].getText().isBlank()){
		    	JOptionPane.showMessageDialog(this,"Please fill up all the inputs correctly.");		    	
		    }

		    else{


				try{
					int p1 = Integer.parseInt(fields[5].getText());
					int p2 = Integer.parseInt(fields[6].getText());

					for(int f=0; f<=4; f++){
					vector.add(fields[f].getText());
					}

					
					vector.add(String.valueOf(p1));
					vector.add(String.valueOf(p2));

					if(p1>p2){
						vector.add("High Stock");
					}

					else if(p1==p2){
						vector.add("Low Stock");
					}

					else if(p1<p2){
						vector.add("Low Stock");
					}


				int mainOption = JOptionPane.showConfirmDialog(this, "Do you want to save your inputs?", "Confirmation", JOptionPane.YES_NO_OPTION);
				
				if(mainOption == 0){
					defTableModel.addRow(vector);


					JOptionPane.showMessageDialog(this,"Successfully Added!");
					table.getSelectionModel().clearSelection();
					for(int h = 0; h<=3; h++){
						btn[h].setEnabled(true);
					}

					for(int i=0; i<=fields.length-1; i++){
						fields[i].setText("");
						fields[i].setEditable(false);
						fields[i].setBorder(BorderFactory.createMatteBorder(0,0,2,0, maroon));
						fields[i].setForeground(maroon);
					}

					btn[4].setEnabled(false);
					btn[5].setEnabled(false);
					table.setRowSelectionAllowed(true);
					
					

	 			
			}

				}
				catch(Exception g){
					JOptionPane.showMessageDialog(this,"Stocks and Re-Order Point must be number only.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
					
				}

			}
		 

		}



		//this part is for the cancel button
		else if(e.getSource() == btn[5]){
			int cancelRes = JOptionPane.showConfirmDialog(this, "Do you want to cancel the operation?", "Confirmation", JOptionPane.YES_NO_OPTION);

			if(cancelRes == 0){
				table.getSelectionModel().clearSelection();
				for(int h = 0; h<=3; h++){
					btn[h].setEnabled(true);
				}

				for(int i=0; i<=fields.length-1; i++){
					fields[i].setText("");
					fields[i].setEditable(false);
					fields[i].setBorder(BorderFactory.createMatteBorder(0,0,2,0, maroon));
					fields[i].setForeground(maroon);
				}
				btn[4].setVisible(true);
				btn[4].setEnabled(false);
				btn[5].setEnabled(false);
				editSave.setVisible(false);
				itcd--;

				 
				}
						
		}

		//this part is for the 2nd cancel button
		else if(e.getSource() == cancel2){
			int cancelRes = JOptionPane.showConfirmDialog(this, "Do you want to cancel the operation?", "Confirmation", JOptionPane.YES_NO_OPTION);

			if(cancelRes == 0){
				table.getSelectionModel().clearSelection();
				for(int h = 0; h<=3; h++){
					btn[h].setEnabled(true);
				}

				for(int i=0; i<=fields.length-1; i++){
					fields[i].setText("");
					fields[i].setEditable(false);
					fields[i].setBorder(BorderFactory.createMatteBorder(0,0,2,0, maroon));
					fields[i].setForeground(maroon);
				}
				btn[4].setVisible(true);
				btn[4].setEnabled(false);
				btn[5].setVisible(true);
				btn[5].setEnabled(false);
				editSave.setVisible(false);
				cancel2.setVisible(false);
				

				 
				}
						
		}


		else if(e.getSource() == back2Dash){



			Dashboard dsh = new Dashboard();
			dsh.userDashboard();
			dispose();




		}

		else if(e.getSource() == srcClear){

			txtSearch.setText("");
		}




	}

}