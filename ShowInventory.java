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

public class ShowInventory extends JFrame{

	Frame objFr = new Frame();

	JScrollPane scrollpane = new JScrollPane(objFr.table);

	void inventory(){

		setSize(800,490);
		setLocationRelativeTo(null);
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		// getContentPane().setBackground(darkBlue);


		JPanel expan = new JPanel();
		expan.setBounds(0,0,790,420);
		expan.setLayout(new BorderLayout());
		expan.add(scrollpane);



		add(expan);
		
		setVisible(true);

	}
}