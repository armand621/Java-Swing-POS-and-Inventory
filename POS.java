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


import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class POS extends JFrame implements ActionListener{

	JButton closeBtn;

	Color gold = new Color(0xFCCB06);
	Color darkBlue = new Color(0x222E50);
	Color darkOrange = new Color(0xFF8C00);

	JButton back2Dash;

	Frame frObj = new Frame();


	JLabel date, time;





	JScrollPane scrollpane = new JScrollPane(frObj.table);

	void pos(){

		setSize(1250,600);
		setLocationRelativeTo(null);
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setUndecorated(true);
		getContentPane().setBackground(darkBlue);


		date = new JLabel();
		time = new JLabel();

		date.setBounds(10,10,200,40);
		time.setBounds(50,70,200,40);

		date.setForeground(Color.BLACK);
		time.setForeground(Color.BLACK);

		


		add(date);
		add(time);


		 closeBtn = new JButton();
		 closeBtn.setBounds(1200,0,50,20);
		 closeBtn.setBackground(new Color(0x800000));
		 closeBtn.setForeground(Color.WHITE);
		 closeBtn.setText("X");
		 closeBtn.setFocusable(false);
		 closeBtn.addActionListener(this);
		 add(closeBtn);




		 //this part is for the back to dashboard button
		back2Dash = new JButton();
		back2Dash.setBounds(15,570,180,20);
		back2Dash.setText("<html><u>Back to Dashboard</u></html>");
		back2Dash.setOpaque(true);
		back2Dash.setForeground(gold);
		back2Dash.setBackground(darkBlue);
		back2Dash.setBorder(null);
		back2Dash.addActionListener(this);
		back2Dash.setFocusable(false);
		add(back2Dash);


		JPanel expan = new JPanel();
		expan.setBounds(450,100,790,420);
		expan.setLayout(new BorderLayout());
		expan.add(scrollpane);



		add(expan);
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



		else if(e.getSource() == back2Dash){



			Dashboard dsh = new Dashboard();
			dsh.userDashboard();
			dispose();

			ShowInventory sh = new ShowInventory();
			sh.inventory();




		}


	}



}