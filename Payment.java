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

	static JTextField numPayment;

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

	

	@Override
	public void actionPerformed(ActionEvent e){

		if (e.getSource() == apply) {

			 if(numPayment.getText().equals("")){
		    	JOptionPane.showMessageDialog(this,"Please enter valid payment", "Payment Null", JOptionPane.WARNING_MESSAGE);		    	
		    }


			 else if(numPayment.getText().isEmpty()){
		    	JOptionPane.showMessageDialog(this,"Please enter valid payment", "Payment Null", JOptionPane.WARNING_MESSAGE);		    	
		    }

		     else if(numPayment.getText().isBlank()){
		    	JOptionPane.showMessageDialog(this,"Please enter valid payment.","Payment Null", JOptionPane.WARNING_MESSAGE);		    	
		    }



		    else{

		    	try{
					double parsedPayment = Double.parseDouble(numPayment.getText());


					if (parsedPayment < parseCut) {
						JOptionPane.showMessageDialog(null,"Insufficient payment, please try again", "Payment Error", JOptionPane.WARNING_MESSAGE);
					}


					else{
						double change = parsedPayment - parseCut;
						char peso = '\u20B1';
						parB = String.format( peso + " %,.2f",change);
						PmSucess ps = new PmSucess();
						ps.paymentSucess();

						PmSucess.labels[1].setSize(200,30);
						PmSucess.labels[2].setSize(200,30);
						PmSucess.labels[3].setSize(200,30);
						PmSucess.labels[5].setSize(200,30);

						PmSucess.labels[1].setText(POS.lblNumTotal.getText());
						
						dispose();

					}
				


				}

				catch(Exception a){
				JOptionPane.showMessageDialog(null,"Please enter valid payment.", "Payment Invalid", JOptionPane.WARNING_MESSAGE);
				}
		    }
			
				
		}


		else if(e.getSource() == cancel){
			// POS.srcBtn.setEnabled(true);
			// POS.transBtn[1].setEnabled(true);
			dispose();
		}




		// else if (e.getSource() == part2[0]) {
		// 	forExperiment[0].doClick();
		// }


		// else if (e.getSource() == part2[1]) {
		// 	forExperiment[1].doClick();
		// }


		// else if (e.getSource() == part2[2]) {
		// 	forExperiment[2].doClick();
		// }


		// else if (e.getSource() == part2[3]) {
		// 	forExperiment[3].doClick();
		// }








		// else if (e.getSource() == forExperiment[0]) {
		// 	double discountNew = Discount.parseCut * 0.03;
		// 	//this part is for setting the total of the receipt
		// 	Receipt.labels0 = ("TOTAL..........: " + String.valueOf(Discount.parseCut));
		// 	// labels[0].setText("TOTAL..........: " + String.valueOf(Discount.parseCut));
		
		// 	double samsam = Double.parseDouble(numPayment.getText());
		// 	char peso1 = '\u20B1';

		// 	// labels[1].setText("PAYMENT........: " + String.format(peso+" %,.2f ",samsam));
		// 	Receipt.labels1 = ("DISCOUNT.....: " + String.format(peso1+" %,.2f ",discountNew) +" (St-3%)");

		// 	// labels[2].setText("CHANGE........: " + Payment.parB);
		// 	Receipt.labels2 = ("PAYMENT........: " + String.format(peso1+" %,.2f ",samsam));

		// 	Receipt.labels3 = ("CHANGE........: " + String.format(peso1+" %,.2f ",samsam-Discount.parseCut));




			
		// }

		// else if (e.getSource() == forExperiment[1]) {
		// 	double discountNew = Discount.parseCut * 0.25;
		// 	//this part is for setting the total of the receipt
		// 	Receipt.labels0 = ("TOTAL..........: " + String.valueOf(Discount.parseCut));
		// 	// labels[0].setText("TOTAL..........: " + String.valueOf(Discount.parseCut));
		
		// 	double samsam = Double.parseDouble(numPayment.getText());
		// 	char peso1 = '\u20B1';

		// 	// labels[1].setText("PAYMENT........: " + String.format(peso+" %,.2f ",samsam));
		// 	Receipt.labels1 = ("DISCOUNT.....: " + String.format(peso1+" %,.2f ",discountNew) +" (RC-25%)");

		// 	// labels[2].setText("CHANGE........: " + Payment.parB);
		// 	Receipt.labels2 = ("PAYMENT........: " + String.format(peso1+" %,.2f ",samsam));

		// 	Receipt.labels3 = ("CHANGE........: " + String.format(peso1+" %,.2f ",samsam-Discount.parseCut));
			
		// }

		// else if (e.getSource() == forExperiment[2]) {
		// 	double discountNew = Discount.parseCut * 0.20;
		// 	//this part is for setting the total of the receipt
		// 	Receipt.labels0 = ("TOTAL..........: " + String.valueOf(Discount.parseCut));
		// 	// labels[0].setText("TOTAL..........: " + String.valueOf(Discount.parseCut));
		
		// 	double samsam = Double.parseDouble(numPayment.getText());
		// 	char peso1 = '\u20B1';

		// 	// labels[1].setText("PAYMENT........: " + String.format(peso+" %,.2f ",samsam));
		// 	Receipt.labels1 = ("DISCOUNT.....: " + String.format(peso1+" %,.2f ",discountNew) +" (Sc/Pwd-3%)");

		// 	// labels[2].setText("CHANGE........: " + Payment.parB);
		// 	Receipt.labels2 = ("PAYMENT........: " + String.format(peso1+" %,.2f ",samsam));

		// 	Receipt.labels3 = ("CHANGE........: " + String.format(peso1+" %,.2f ",samsam-Discount.parseCut));
			
		// }

		// else if (e.getSource() == forExperiment[3]) {
		// 	double discountNew = Discount.parseCut * 0.15;
		// 	//this part is for setting the total of the receipt
		// 	Receipt.labels0 = ("TOTAL..........: " + String.valueOf(Discount.parseCut));
		// 	// labels[0].setText("TOTAL..........: " + String.valueOf(Discount.parseCut));
		
		// 	double samsam = Double.parseDouble(numPayment.getText());
		// 	char peso1 = '\u20B1';

		// 	// labels[1].setText("PAYMENT........: " + String.format(peso+" %,.2f ",samsam));
		// 	Receipt.labels1 = ("DISCOUNT.....: " + String.format(peso1+" %,.2f ",discountNew) +" (Emp-15%)");

		// 	// labels[2].setText("CHANGE........: " + Payment.parB);
		// 	Receipt.labels2 = ("PAYMENT........: " + String.format(peso1+" %,.2f ",samsam));

		// 	Receipt.labels3 = ("CHANGE........: " + String.format(peso1+" %,.2f ",samsam-Discount.parseCut));
			
		// }






//do not delete the two curly braces below
	
			}

	}


