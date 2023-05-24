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


public class Dashboard extends JFrame implements ActionListener{

	JButton closeBtn;

	ImageIcon bg;
	JLabel lblBg;


	JButton [] btn = new JButton[2];

	void userDashboard(){

		
		setSize(600,500);
		setLayout(null);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setUndecorated(true);
		



		 closeBtn = new JButton();
		 closeBtn.setBounds(550,0,50,20);
		 closeBtn.setBackground(new Color(0x800000));
		 closeBtn.setForeground(Color.WHITE);
		 closeBtn.setText("X");
		 closeBtn.setFocusable(false);
		 closeBtn.addActionListener(this);


		 bg = new ImageIcon(new ImageIcon("dashboardImg/bg.jpg").getImage().getScaledInstance(600,500, Image.SCALE_SMOOTH));
		 lblBg = new JLabel();
		 lblBg.setIcon(bg);
		 lblBg.setBounds(0,0,600,500);

		 int btnX = 48;
		 for(int a=0; a<=1; a++){
		 	btn[a] = new JButton();
		 	btn[a].setText("Go");
		 	btn[a].setBounds(btnX, 340, 200, 40);
		 	btn[a].setFocusable(false);
		 	btn[a].setBackground(new Color(0xFCCB06));
		 	btn[a].setForeground(new Color(0x222E50));
		 	btn[a].setFont(new Font("Arial", Font.BOLD, 15));
		 	btn[a].addActionListener(this);
		 	btnX+=310;
		 	add(btn[a]);
		 }


		add(closeBtn);
		add(lblBg);
		
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


		else if(e.getSource() == btn[1]){
			Frame fr = new Frame();
			fr.frameInventory();
			dispose();
		}


		else if(e.getSource() == btn[0]){
			POS ps = new POS();
			ps.pos();
			ShowInventory si = new ShowInventory();
			si.inventory();
			dispose();
		}

	}



}