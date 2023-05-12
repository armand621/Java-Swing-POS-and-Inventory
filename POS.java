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


public class POS extends JFrame implements ActionListener{

	JButton closeBtn;

	Color gold = new Color(0xFCCB06);
	Color darkBlue = new Color(0x222E50);
	Color darkOrange = new Color(0xFF8C00);

	void pos(){

		setSize(1250,600);
		setLocationRelativeTo(null);
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setUndecorated(true);
		getContentPane().setBackground(darkBlue);



		 closeBtn = new JButton();
		 closeBtn.setBounds(1200,0,50,20);
		 closeBtn.setBackground(new Color(0x800000));
		 closeBtn.setForeground(Color.WHITE);
		 closeBtn.setText("X");
		 closeBtn.setFocusable(false);
		 closeBtn.addActionListener(this);
		 add(closeBtn);

		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e){

		if(e.getSource() == closeBtn){
			int closeOpt = JOptionPane.showConfirmDialog(this, "Are you sure to exit?", "Confirmation", JOptionPane.YES_NO_OPTION);
			if(closeOpt==0){
				dispose();
			}
		}


	}



}