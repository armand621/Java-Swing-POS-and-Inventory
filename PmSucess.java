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


public class PmSucess extends JFrame implements ActionListener{


	JLabel lblDisc = new JLabel();

	Font arial12b = new Font("Arial", Font.BOLD,12);
	Font arial15b = new Font("Arial", Font.BOLD,16);
	Font arial18b = new Font("Arial", Font.BOLD,18);
	Font arial23b = new Font("Arial", Font.BOLD,23);

	JButton apply;

	static String[] str = {"Total: ", "", "Change: ", ""};
	static JLabel[] labels = new JLabel[str.length];


	String totalStr = POS.lblNumTotal.getText();
	String total1 = totalStr.replace(',',' ');
	String total2 = total1.replace(" ", "");
	int strLn = total2.length();
	String cutted = total2.substring(1,strLn).trim();
	double parseCut = Double.parseDouble(cutted);


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

	

		int labelsX = 120;
		int labelsX1 = 120;

		for(int g =0; g<=labels.length-1; g++){
			labels[g] = new JLabel();
			labels[g].setText(str[g]);
			labels[g].setBounds(labelsX, 80, 100, 30);
			labels[g].setForeground(POS.gold);
			labels[g].setFont(arial23b);
			add(labels[g]);
			if(g>=2){
				labels[g].setBounds(labelsX1, 150, 100, 30);
				labelsX1+=120;
			}
			labelsX+=120;
		}
		
		labels[3].setText(Payment.parB);

		apply = new JButton();
		apply.setBounds(165,220,160,30);
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

	

	// double total = Double.parseDouble(POS.lblNumTotal.getText());

	@Override
	public void actionPerformed(ActionEvent e){

		if (e.getSource() == apply) {
			JOptionPane.showMessageDialog(null,String.format("this is the sample change %s", Payment.parB));
			
		}

		// if (e.getSource() == apply) {

		// 	 if(numPayment.getText().equals("")){
		//     	JOptionPane.showMessageDialog(this,"Please enter number into payment.");		    	
		//     }


		// 	 else if(numPayment.getText().isEmpty()){
		//     	JOptionPane.showMessageDialog(this,"Please enter number into payment.");		    	
		//     }

		//      else if(numPayment.getText().isBlank()){
		//     	JOptionPane.showMessageDialog(this,"Please enter number into payment.");		    	
		//     }



		//     else{

		//     	try{
		// 			// int parsedPayment = Integer.parseInt(numPayment.getText());
		// 			double parsedPayment = Double.parseDouble(numPayment.getText());


		// 			if (parsedPayment < parseCut) {
		// 				JOptionPane.showMessageDialog(null,"Insufficient payment, please try again");
		// 			}


		// 			else{
		// 				double change = parsedPayment - parseCut;
		// 				char peso = '\u20B1';
		// 				String parB = String.format( peso + " %,.2f",change);
		// 				// POS.lblNumTotal.setText(parB);
		// 				System.out.println("Your change is " + parB);

		// 			}
				


		// 		}

		// 		catch(Exception a){
		// 		JOptionPane.showMessageDialog(null,String.format("This is the error %s",a));
		// 		}
		//     }
			
				
		// }

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


