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

	JButton closeBtn;

	//this part is for making an object of the class Frame, which consist of the table
	Frame objFr = new Frame();

	//this part is for making an object of the class POS, which contains the pos system
	POS objPos = new POS();

	JScrollPane scrollpane = new JScrollPane(objFr.table);

	//this part is for the search component of the table
	JTextField txtSearch;
	JLabel lblSearch;
	JButton srcClear, btnAdd;

	void inventory(){

		setSize(800,490);
		setLocationRelativeTo(null);
		setLayout(null);
		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setUndecorated(true);
		getContentPane().setBackground(new Color(0xf3f0f0));


		JPanel expan = new JPanel();
		expan.setBounds(0,140,790,300);
		expan.setLayout(new BorderLayout());
		expan.add(scrollpane);

		//this part is for the close button
		closeBtn = new JButton();
		closeBtn.setBounds(750,0,50,20);
		closeBtn.setBackground(new Color(0x800000));
		closeBtn.setForeground(Color.WHITE);
		closeBtn.setText("X");
		closeBtn.setFocusable(false);
		closeBtn.addActionListener(this);


		//setting the properties for the table
		objFr.table.setDefaultEditor(Object.class, null);
		objFr.table.getTableHeader().setEnabled(false);
		objFr.table.getTableHeader().setPreferredSize(new Dimension(50, 30));

		objFr.rdr.setHorizontalAlignment(JLabel.CENTER);


		//declaring the table model property
		TableColumnModel tblmodel = objFr.table.getColumnModel();
		

		//setting up some properties for the table model
		for(int z=2; z<=5; z++){
			tblmodel.getColumn(z).setPreferredWidth(50);
		}
		tblmodel.getColumn(0).setPreferredWidth(50);
		tblmodel.getColumn(1).setPreferredWidth(130);
		tblmodel.getColumn(2).setPreferredWidth(80);
		tblmodel.getColumn(3).setPreferredWidth(40);

		 

		for(int d=0; d<=objFr.header.length-1; d++){
			objFr.table.getColumnModel().getColumn(d).setCellRenderer(objFr.rdr);
		}
		

		//setting up the table
		objFr.table.setFocusable(false);
		objFr.table.setRowSelectionAllowed(true);
		objFr.table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		objFr.table.setRowHeight(25);

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

		TableRowSorter<TableModel> sort = new TableRowSorter<>(objFr.table.getModel());
		objFr.table.setRowSorter(sort);
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
		
		setVisible(true);

	}

	int numQuanti;
	String forTryClick;

	@Override
	public void actionPerformed(ActionEvent e){

		if(e.getSource() == closeBtn){
			int closeOpt = JOptionPane.showConfirmDialog(null, "Are you sure to exit?", "Confirmation", JOptionPane.YES_NO_OPTION);
			if(closeOpt==0){
				dispose();
			}
		}


		else if(e.getSource() == btnAdd){

			int mm = objFr.table.getSelectedRow();

			if(objFr.table.getSelectedRow() == -1){
				JOptionPane.showMessageDialog(null,"Please select item to add");
			}

			else{
				int numRow = objFr.table.getSelectedRow();
				String stockNum = objFr.table.getValueAt(numRow,5).toString();
				int parsedStockNum = Integer.parseInt(stockNum);
				int newStock = parsedStockNum - numQuanti;

				// objFr.permData[numRow][5] = String.valueOf(newStock);
				objFr.frameInventory();
				objFr.tryclick.doClick();
				// objFr.defTableModel.setValueAt(String.valueOf(newStock), numRow,5);
				setVisible(false);

				System.out.println("Quantity of items: " + numQuanti);
				System.out.println("Row selected: " + numRow);
				System.out.println("Number of new Stock: " + newStock);
				System.out.println("Value of stock of selected row: " + parsedStockNum+"\n");

				System.out.println(forTryClick);
				


				// numQuanti = 0;
			}





		}





		else if(e.getSource() == srcClear){

			txtSearch.setText("");
		}












		//do not delete the two braces below
	}
}