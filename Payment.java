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


public class Payment extends JFrame implements ActionListener{


	JLabel lblDisc = new JLabel();

	Font arial12b = new Font("Arial", Font.BOLD,12);
	Font arial15b = new Font("Arial", Font.BOLD,16);
	Font arial18b = new Font("Arial", Font.BOLD,18);

	JTextField numPayment;

	JButton apply, cancel;


	String totalStr = POS.lblNumTotal.getText();
	String total1 = totalStr.replace(',',' ');
	String total2 = total1.replace(" ", "");
	int strLn = total2.length();
	String cutted = total2.substring(1,strLn).trim();
	double parseCut = Double.parseDouble(cutted);

	static String parB;


	void userPayment(){
		setSize(500,300);
		setLocationRelativeTo(null);
		setLayout(null);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setResizable(false);
		setUndecorated(true);
		getContentPane().setBackground(POS.darkBlue);

		
		lblDisc.setText("Payment");
		lblDisc.setFont(arial18b);
		lblDisc.setBounds(0,0,500,40);
		lblDisc.setHorizontalAlignment(JLabel.CENTER);
		lblDisc.setOpaque(true);
		lblDisc.setBackground(POS.gold);
		lblDisc.setForeground(POS.darkBlue);
		add(lblDisc);

		numPayment = new JTextField();
		numPayment.setFont(arial18b);
		numPayment.setBounds(150,130,200,40);
		numPayment.setHorizontalAlignment(JTextField.CENTER);
		numPayment.setOpaque(false);
		numPayment.setBorder(BorderFactory.createMatteBorder(0,0,2,0, POS.gold));
		numPayment.setForeground(POS.gold);
		numPayment.setCaretColor(POS.gold);
		add(numPayment);

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

	

	// double total = Double.parseDouble(POS.lblNumTotal.getText());

	@Override
	public void actionPerformed(ActionEvent e){

		if (e.getSource() == apply) {

			 if(numPayment.getText().equals("")){
		    	JOptionPane.showMessageDialog(this,"Please enter number into payment.");		    	
		    }


			 else if(numPayment.getText().isEmpty()){
		    	JOptionPane.showMessageDialog(this,"Please enter number into payment.");		    	
		    }

		     else if(numPayment.getText().isBlank()){
		    	JOptionPane.showMessageDialog(this,"Please enter number into payment.");		    	
		    }



		    else{

		    	try{
					// int parsedPayment = Integer.parseInt(numPayment.getText());
					double parsedPayment = Double.parseDouble(numPayment.getText());


					if (parsedPayment < parseCut) {
						JOptionPane.showMessageDialog(null,"Insufficient payment, please try again");
					}


					else{
						double change = parsedPayment - parseCut;
						char peso = '\u20B1';
						parB = String.format( peso + " %,.2f",change);
						// POS.lblNumTotal.setText(parB);
						PmSucess ps = new PmSucess();
						ps.paymentSucess();

						PmSucess.labels[1].setSize(200,30);
						PmSucess.labels[3].setSize(200,30);

						PmSucess.labels[1].setText(POS.lblNumTotal.getText());

						dispose();

					}
				


				}

				catch(Exception a){
				JOptionPane.showMessageDialog(null,String.format("This is the error %s",a));
				}
		    }
			
				
		}


		else if(e.getSource() == cancel){
			POS.srcBtn.setEnabled(true);
			POS.transBtn[1].setEnabled(true);
			dispose();
		}

		// if(e.getSource() == apply){


		// if(rdBtn[0].isSelected() == true){
		// 	double discountNew = parseCut * 0.03;
		// 	char peso = '\u20B1';
		// 	String parB = String.format( peso + " %,.2f",parseCut-discountNew);
		// 	POS.lblNumTotal.setText(parB);
			
		// }

		// else if(rdBtn[1].isSelected() == true){
		// 	double discountNew = parseCut * 0.25;
		// 	char peso = '\u20B1';
		// 	String parB = String.format( peso + " %,.2f",parseCut-discountNew);
		// 	POS.lblNumTotal.setText(parB);
			

		// }

		// else if(rdBtn[2].isSelected() == true){
		// 	double discountNew = parseCut * 0.20;
		// 	char peso = '\u20B1';
		// 	String parB = String.format( peso + " %,.2f",parseCut-discountNew);
		// 	POS.lblNumTotal.setText(parB);
			

		// }

		// else if(rdBtn[3].isSelected() == true){
		// 	double discountNew = parseCut * 0.15;
		// 	char peso = '\u20B1';
		// 	String parB = String.format( peso + " %,.2f",parseCut-discountNew);
		// 	POS.lblNumTotal.setText(parB);
			

		// }

		// else{
		// 	JOptionPane.showMessageDialog(null,"No discount selected.");
		// }


		// 	POS.srcBtn.setEnabled(false);
		// 	dispose();

		// }

		// else if(e.getSource() == rdBtn[0]){
		// 	double discountNew = parseCut * 0.03;
		// 	POS.lblNumTotal.setText(String.valueOf(parseCut-discountNew));
			
		// }

		// else if(e.getSource() == rdBtn[1]){
		// 	double discountNew = parseCut * 0.25;
		// 	POS.lblNumTotal.setText(String.valueOf(parseCut-discountNew));
			
		// }

		// else if(e.getSource() == rdBtn[2]){
		// 	double discountNew = parseCut * 0.20;
		// 	POS.lblNumTotal.setText(String.valueOf(parseCut-discountNew));
			
		// }

		// else if(e.getSource() == rdBtn[3]){
		// 	double discountNew = parseCut * 0.15;
		// 	POS.lblNumTotal.setText(String.valueOf(parseCut-discountNew));
			
		// }
			}

	}


