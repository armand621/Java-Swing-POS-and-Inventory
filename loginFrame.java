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


public class loginFrame extends JFrame implements ActionListener{

	// // String[] strLabel = {"Username", "Password"};
	String[] btnLabel = {"Clear", "Login"};

	// JTextField [] txtField = new JTextField[btnLabel.length];

	JTextField fldUsername;
	JPasswordField fldPsk;

	// JLabel [] labels = new JLabel[strLabel.length];
	JButton [] btn = new JButton[btnLabel.length];

	JButton closeBtn;
	JButton[] pskBtn = new JButton[2];

	ImageIcon loginBg, showPsk, hidePsk;
	JLabel lblLoginBg;
	 

	void login(){

		setSize(500,320);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setUndecorated(true);
		getContentPane().setBackground(new Color(0x222E50));


		lblLoginBg = new JLabel();
		loginBg = new ImageIcon(new ImageIcon("loginImages/loginBg.jpg").getImage().getScaledInstance(500,300, Image.SCALE_SMOOTH));
		lblLoginBg.setIcon(loginBg);
		lblLoginBg.setBounds(0,20,500,300);


		showPsk = new ImageIcon(new ImageIcon("loginImages/showPsk.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
		hidePsk = new ImageIcon(new ImageIcon("loginImages/hidePsk.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));

		for(int d=0; d<=1; d++){
			pskBtn[d] = new JButton();
			pskBtn[d].setBounds(378,188,30,30);
			pskBtn[d].setOpaque(true);
			pskBtn[d].setFocusable(false);
			pskBtn[d].setSelected(false);
			pskBtn[d].setBorder(BorderFactory.createLineBorder(Color.WHITE, 0));
			pskBtn[d].setBackground(Color.WHITE);
			pskBtn[d].addActionListener(this);

		}

		pskBtn[0].setIcon(showPsk);
		pskBtn[1].setIcon(hidePsk);


		 
		 closeBtn = new JButton();
		 closeBtn.setBounds(450,0,50,20);
		 closeBtn.setBackground(new Color(0x800000));
		 closeBtn.setForeground(Color.WHITE);
		 closeBtn.setText("X");
		 closeBtn.setFocusable(false);
		 closeBtn.addActionListener(this);

		
		// int txtFieldY = 123;
		// for(int a = 0; a<=btnLabel.length-1; a++){
		// 	txtField[a] = new JTextField();
		// 	txtField[a].setBounds(190,txtFieldY, 180,35);
		// 	txtField[a].setBorder(BorderFactory.createMatteBorder(0,0,2,0, new Color(0x222E50)));
		// 	txtField[a].setFont(new Font("Arial", Font.BOLD, 15));
		// 	txtField[a].setHorizontalAlignment(JTextField.CENTER);
		// 	txtFieldY+=62;
		// 	add(txtField[a]);
		// }

		 
	 
			fldUsername = new JTextField();
			fldUsername.setBounds(190,123, 180,35);
			fldUsername.setBorder(BorderFactory.createMatteBorder(0,0,2,0, new Color(0x222E50)));
			fldUsername.setFont(new Font("Arial", Font.BOLD, 15));
			fldUsername.setHorizontalAlignment(JTextField.CENTER);

			fldPsk = new JPasswordField();
			fldPsk.setBounds(190,185,180,35);
			fldPsk.setBorder(BorderFactory.createMatteBorder(0,0,2,0, new Color(0x222E50)));
			fldPsk.setFont(new Font("Arial", Font.BOLD, 15));
			fldPsk.setHorizontalAlignment(JTextField.CENTER);
			 
		 





		// int lblY = 100;
		// for(int b = 0; b<=strLabel.length-1; b++){
		// 	labels[b] = new JLabel();
		// 	labels[b].setBounds(20,lblY, 120,35);
		// 	labels[b].setText(strLabel[b]);
		// 	lblY+=70;
		// 	add(labels[b]);
		// }


		int btnX = 120;
		for(int c=0; c<=btnLabel.length-1; c++){
			btn[c] = new JButton();
			btn[c].setBounds(btnX, 260, 110, 35);
			btn[c].setText(btnLabel[c]);
			btn[c].addActionListener(this);
			btn[c].setFocusable(false);
			btn[c].setFont(new Font("Arial", Font.BOLD, 15));
			btnX+=150;
			add(btn[c]);
		}

		btn[0].setBackground(new Color(0x222E50));
		btn[0].setForeground(new Color(0xFCCB06));
		btn[1].setBackground(new Color(0xFCCB06));
		btn[1].setForeground(new Color(0x222E50));

		add(pskBtn[1]);
		add(fldUsername);
		add(fldPsk);
		add(closeBtn);
		add(lblLoginBg);
		setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e){

		if(e.getSource() == btn[0]){
			
			fldUsername.setText("");
			fldPsk.setText("");



		}


		// else if(e.getSource() == btn[1]){
			
		// 	String defUsername = "admin";
		// 	String defPsk = "admin";

		// 	String userInput = txtField[0].getText();
		// 	String pskInput = txtField[1].getText();

		// 	if(txtField[0].getText().isBlank() || txtField[1].getText().isBlank()){
		// 		JOptionPane.showMessageDialog(null,"Please enter credentials");
		// 	}

		// 	else if(txtField[0].getText().isEmpty() || txtField[1].getText().isEmpty()){
		// 		JOptionPane.showMessageDialog(null,"Please enter credentials");
		// 	}

		// 	else{
		// 		if(userInput.equals(defUsername) && pskInput.equals(defPsk)){
		// 			JOptionPane.showMessageDialog(null,"Sucessful!");
		// 		}

		// 		else{
		// 			JOptionPane.showMessageDialog(null,"Username or Password is incorrect. Please try again.");
		// 		}
		// 	}

		// }

		else if(e.getSource() == btn[1]){
				String defUsername = "admin";
				String defPsk = "admin";


				String inputUn = fldUsername.getText();
				String inputPsk = new String(fldPsk.getText());

				if(fldUsername.getText().isBlank() || fldPsk.getText().isBlank()){
					JOptionPane.showMessageDialog(null,"Please enter required credentials", "Empty Input", JOptionPane.WARNING_MESSAGE);
				}

				else if(fldUsername.getText().isEmpty() || fldPsk.getText().isEmpty()){
					JOptionPane.showMessageDialog(null,"Please enter required credentials", "Empty Input", JOptionPane.WARNING_MESSAGE);
				}

				else{
					if(inputUn.equals(defUsername) && inputPsk.equals(defPsk)){
						JOptionPane.showMessageDialog(null,"Log In Sucessful!", "Access Granted", JOptionPane.INFORMATION_MESSAGE);
						Dashboard dsh = new Dashboard();
						dsh.userDashboard();

						dispose();
					}

					else{
						JOptionPane.showMessageDialog(null,"Username or Password is incorrect. Please try again.", "Credential Error", JOptionPane.WARNING_MESSAGE);
				}

			}
		}


		else if(e.getSource() == pskBtn[0]){
			this.remove(pskBtn[0]);
			this.add(pskBtn[1]);
			fldPsk.setEchoChar('*');
			pskBtn[0].setFocusable(false);
			pskBtn[1].setFocusable(false);
		}


		else if(e.getSource() == pskBtn[1]){
			this.remove(pskBtn[1]);
			this.add(pskBtn[0]);
			fldPsk.setEchoChar((char)0);
			pskBtn[0].setFocusable(false);
			pskBtn[1].setFocusable(false);
		}

		

		else if(e.getSource() == closeBtn){
			int closeOpt = JOptionPane.showConfirmDialog(this, "Are you sure to exit?", "Confirmation", JOptionPane.YES_NO_OPTION);
			if(closeOpt==0){
				dispose();
			}
		}

	}




}