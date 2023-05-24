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



public class PmSucess extends JFrame implements ActionListener{


	JLabel lblDisc = new JLabel();

	Font arial12b = new Font("Arial", Font.BOLD,12);
	Font arial15b = new Font("Arial", Font.BOLD,16);
	Font arial18b = new Font("Arial", Font.BOLD,18);
	Font arial23b = new Font("Arial", Font.BOLD,23);

	JButton apply;

	static String[] str = {"Total: ", "", "Payment: ", "", "Change: ", ""};
	static JLabel[] labels = new JLabel[str.length]; 


	String totalStr = POS.lblNumTotal.getText();
	String total1 = totalStr.replace(',',' ');
	String total2 = total1.replace(" ", "");
	int strLn = total2.length();
	String cutted = total2.substring(1,strLn).trim();
	double parseCut = Double.parseDouble(cutted);
	 
	String txtPy;

	void paymentSucess(){
		

		setSize(500,300);
		setLocationRelativeTo(null);
		setLayout(null);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setResizable(false);
		setUndecorated(true);
		getContentPane().setBackground(POS.darkBlue);
		

		
		lblDisc.setText("Successful Transaction");
		lblDisc.setFont(arial18b);
		lblDisc.setBounds(0,0,500,40);
		lblDisc.setHorizontalAlignment(JLabel.CENTER);
		lblDisc.setOpaque(true);
		lblDisc.setBackground(POS.gold);
		lblDisc.setForeground(POS.darkBlue);
		add(lblDisc);

	

		int labelsX = 100;
		int labelsX1 = 100;
		int labelsX2 = 100;

		for(int g =0; g<=labels.length-1; g++){
			labels[g] = new JLabel();
			labels[g].setText(str[g]);
			labels[g].setBounds(labelsX, 70, 100, 30);
			labels[g].setForeground(POS.gold);
			labels[g].setFont(arial23b);
			add(labels[g]);
			if(g>=2){
				labels[g].setBounds(labelsX1, 120, 100, 30);
				labelsX1+=150;
			}

			if(g>=4){
				labels[g].setBounds(labelsX2, 170, 100, 30);
				labelsX2+=150;
			}

			labelsX+=150;
		}
		
		labels[5].setText(Payment.parB);


		double samsam = Double.parseDouble(Payment.numPayment.getText());
		char peso = '\u20B1';

		labels[3].setText(String.format(peso+" %,.2f ",samsam));
	

		apply = new JButton();
		apply.setBounds(165,250,160,30);
		apply.setOpaque(true);
		apply.setHorizontalAlignment(SwingConstants.CENTER);
		apply.setBackground(POS.gold);
		apply.setForeground(POS.darkBlue);
		apply.setFocusable(false);
		apply.setText("View Receipt");
		apply.setFont(arial15b);
		apply.addActionListener(this);
		add(apply);
		

		setVisible(true);
	}

	
	@Override
	public void actionPerformed(ActionEvent e){

		if (e.getSource() == apply) {
			Receipt rp = new Receipt();
			rp.clientReceipt();

			POS.hideClose.doClick();
			dispose();
			
		}

			}

	}


