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


public class Discount extends JFrame implements ActionListener{

	String[] strRd = {"Student--> 3%", "Regular Customer--> 25%", "Senior/PWD--> 20%", "Employee--> 15%"};
	JRadioButton[] rdBtn = new JRadioButton[strRd.length];
	ButtonGroup btnGr = new ButtonGroup();

	JLabel lblDisc = new JLabel();

	Font arial12b = new Font("Arial", Font.BOLD,12);
	Font arial15b = new Font("Arial", Font.BOLD,16);
	Font arial18b = new Font("Arial", Font.BOLD,18);

	JButton apply,cancel;


	String totalStr = POS.lblNumTotal.getText();
	String total1 = totalStr.replace(',',' ');
	String total2 = total1.replace(" ", "");
	int strLn = total2.length();
	String cutted = total2.substring(1,strLn).trim();
	double parseCut = Double.parseDouble(cutted);


	void userDiscount(){
		

		setSize(500,300);
		setLocationRelativeTo(null);
		setLayout(null);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setResizable(false);
		setUndecorated(true);
		getContentPane().setBackground(POS.darkBlue);
		

		int btnX = 50;
		for(int a=0; a<=rdBtn.length-1; a++){
			rdBtn[a] = new JRadioButton();
			rdBtn[a].setText(strRd[a]);
			rdBtn[a].setLocation(btnX,70);
			rdBtn[a].setFont(arial15b);
			rdBtn[a].setForeground(POS.gold);
			rdBtn[a].setOpaque(false);
			rdBtn[a].addActionListener(this);
			rdBtn[a].setFocusable(false);
			btnGr.add(rdBtn[a]);
			add(rdBtn[a]);
			btnX+=200;
		}

		rdBtn[0].setSize(160,30);
		rdBtn[1].setSize(240,30);
		rdBtn[2].setBounds(50,150,200,30);
		rdBtn[3].setBounds(250,150,250,30);

		lblDisc.setText("Select Discount:");
		lblDisc.setFont(arial18b);
		lblDisc.setBounds(0,0,500,40);
		lblDisc.setHorizontalAlignment(JLabel.CENTER);
		lblDisc.setOpaque(true);
		lblDisc.setBackground(POS.gold);
		lblDisc.setForeground(POS.darkBlue);
		add(lblDisc);

		apply = new JButton();
		apply.setBounds(100,220,120,30);
		apply.setOpaque(true);
		apply.setHorizontalAlignment(SwingConstants.CENTER);
		apply.setBackground(POS.gold);
		apply.setForeground(POS.darkBlue);
		apply.setFocusable(false);
		apply.setText("Apply");
		apply.setFont(arial18b);
		apply.addActionListener(this);
		add(apply);
		
		cancel = new JButton();
		cancel.setBounds(280,220,120,30);
		cancel.setOpaque(true);
		cancel.setHorizontalAlignment(SwingConstants.CENTER);
		cancel.setBackground(POS.darkOrange);
		cancel.setForeground(POS.darkBlue);
		cancel.setFocusable(false);
		cancel.setText("Cancel");
		cancel.setFont(arial18b);
		cancel.addActionListener(this);
		add(cancel);		

		setVisible(true);
	}

	
	@Override
	public void actionPerformed(ActionEvent e){

		if(e.getSource() == apply){


		if(rdBtn[0].isSelected() == true){
			double discountNew = parseCut * 0.03;
			char peso = '\u20B1';
			String parB = String.format( peso + " %,.2f",parseCut-discountNew);
			POS.lblNumTotal.setText(parB);


			POS.srcBtn.setEnabled(false);
			dispose();
			
		}

		else if(rdBtn[1].isSelected() == true){
			double discountNew = parseCut * 0.25;
			char peso = '\u20B1';
			String parB = String.format( peso + " %,.2f",parseCut-discountNew);
			POS.lblNumTotal.setText(parB);

			
			POS.srcBtn.setEnabled(false);
			dispose();
			

		}

		else if(rdBtn[2].isSelected() == true){
			double discountNew = parseCut * 0.20;
			char peso = '\u20B1';
			String parB = String.format( peso + " %,.2f",parseCut-discountNew);
			POS.lblNumTotal.setText(parB);
			
			
			POS.srcBtn.setEnabled(false);
			dispose();
		}

		else if(rdBtn[3].isSelected() == true){
			double discountNew = parseCut * 0.15;
			char peso = '\u20B1';
			String parB = String.format( peso + " %,.2f",parseCut-discountNew);
			POS.lblNumTotal.setText(parB);
			
			
			POS.srcBtn.setEnabled(false);
			dispose();
		}

		else{
			JOptionPane.showMessageDialog(null,"No discount selected.","Error F01", JOptionPane.INFORMATION_MESSAGE);
		}


			// POS.srcBtn.setEnabled(false);
			// dispose();

		}




		else if(e.getSource() == cancel){
			POS.srcBtn.setEnabled(true);
			POS.transBtn[1].setEnabled(true);
			dispose();
		}


	}


}