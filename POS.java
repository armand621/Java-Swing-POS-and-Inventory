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


import java.text.DateFormat;
import java.util.Date;
import javax.swing.Timer;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class POS extends JFrame implements ActionListener{

	JButton closeBtn;

	Color gold = new Color(0xFCCB06);
	Color darkBlue = new Color(0x222E50);
	Color darkOrange = new Color(0xFF8C00);

	Font arial12b = new Font("Arial", Font.BOLD,12);
	Font arial15b = new Font("Arial", Font.BOLD,15);
	Font arial18b = new Font("Arial", Font.BOLD,18);
	Font arial25b = new Font("Arial", Font.BOLD,25);
	Font arial30b = new Font("Arial", Font.BOLD,30);

	JButton back2Dash;

	Frame frObj = new Frame();


	//this part is for the background image of the jframe
	ImageIcon frameBg;

	JLabel lblBg;

	//this part is for the date and time label
	String[] strDate = {"Date", "Time"}; 
	JLabel [] lblDate = new JLabel[strDate.length];

	//this part is for the total amount label
	JLabel lblTotal, lblNumTotal;

	//this part is for the transaction buttons
	String [] strTransBtn = {"Remove Item", "Discount", "Payment", "Cancel"};
	JButton [] transBtn = new JButton[strTransBtn.length];


	//this part is for labels
	String [] strMainLbl = {"Quantity", "Search Item", "Invoice No.", ""};
	JLabel [] mainLbl = new JLabel[strMainLbl.length];





	JScrollPane scrollpane = new JScrollPane(frObj.table);

	void pos(){

		setSize(1250,600);
		setLocationRelativeTo(null);
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setUndecorated(true);
		getContentPane().setBackground(darkBlue);

		frameBg = new ImageIcon(new ImageIcon("posImg/pos.jpg").getImage().getScaledInstance(1250,600, Image.SCALE_SMOOTH));
		lblBg = new JLabel();

		lblBg.setIcon(frameBg);
		lblBg.setBounds(0,0,1250,600);


		

 


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
		back2Dash.setOpaque(false);
		back2Dash.setForeground(gold);
		back2Dash.setBorder(null);
		back2Dash.addActionListener(this);
		back2Dash.setFocusable(false);
		add(back2Dash);


		JPanel expan = new JPanel();
		expan.setBounds(0,150,790,420);
		expan.setLayout(new BorderLayout());
		expan.add(scrollpane);

		//this part is for the initialization of the date and time
		int lblDateY = 4;
		for(int a = 0; a<=strDate.length-1; a++ ){
			lblDate[a] = new JLabel();
			lblDate[a].setBounds(860,lblDateY,300,25);
			lblDate[a].setFont(arial15b);
			lblDate[a].setForeground(darkBlue);
			add(lblDate[a]);
			lblDateY+=30;
		}


		//this part is for the clock
		clockActive();
		Timer timer = new Timer(500, new ActionListener() {
      		@Override
      			public void actionPerformed(ActionEvent e) {
        			clockActive();
     			 }
    		});

   		timer.setRepeats(true);
    	timer.setCoalesce(true);
    	timer.setInitialDelay(0);
    	timer.start();


    	//this part is for the date
    	DateTimeFormatter dates =DateTimeFormatter .ofPattern("EEEE, MMMM dd, yyyy");
   		LocalDateTime now =LocalDateTime.now();
   		lblDate[0].setText("Date: " + dates.format(now));
	


   		//this part is for the initialization of the total amount label
   		lblTotal = new JLabel();
   		lblTotal.setText("Total Amount");
   		lblTotal.setBounds(830, 130, 420, 40);
   		lblTotal.setHorizontalAlignment(JLabel.CENTER);
   		lblTotal.setForeground(gold);
   		lblTotal.setFont(arial25b);

   		//this part is for the value of the total amount
   		lblNumTotal = new JLabel();
   		lblNumTotal.setText("Total Amount");
   		lblNumTotal.setBounds(830, 180, 420, 40);
   		lblNumTotal.setHorizontalAlignment(JLabel.CENTER);
   		lblNumTotal.setForeground(new Color(0xED9D1F));
   		lblNumTotal.setFont(arial30b);

   		//this part is for the buttons for transaction
   		int transBtnY = 310;
   		for(int b = 0; b <= strTransBtn.length-1; b++){
   			transBtn[b] = new JButton();
   			transBtn[b].setText(strTransBtn[b]);
   			transBtn[b].setBounds(920, transBtnY, 250, 40);
   			transBtn[b].setFocusable(false);
   			transBtn[b].setOpaque(true);
   			transBtn[b].setBackground(gold);
   			transBtn[b].setForeground(darkBlue);
   			transBtn[b].setFont(arial18b);
   			add(transBtn[b]);
   			transBtnY+=60;
   		}


   		//this part is for the initialization of mainlabel
   		int mainLblX = 40;
   		for(int c = 0; c<=strMainLbl.length-1; c++){
   			mainLbl[c] = new JLabel();
   			mainLbl[c].setText(strMainLbl[c]);
   			mainLbl[c].setBounds(mainLblX, 80, 200, 40);
   			mainLbl[c].setFont(arial18b);
   			add(mainLbl[c]);
   			mainLblX+=250;
   		}

   		add(lblNumTotal);
   		add(lblTotal);
		add(expan);
		add(lblBg);
		setVisible(true);
	}


	//this part is for the clock
	 public void clockActive(){
    	// lblDate[1].setText(DateFormat.getTimeInstance().format(new Date()));

    	lblDate[1].setText("Time: " + DateFormat.getTimeInstance().format(new Date()));
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