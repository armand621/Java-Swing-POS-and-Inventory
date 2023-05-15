/*
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class date{

	public static void time()
         {
   DateTimeFormatter times =DateTimeFormatter .ofPattern("hh : mm a");
   LocalDateTime now =LocalDateTime.now();
   //time.setText(times.format(now));
System.out.println(times.format(now));
        }


 public static void date()
       {
   DateTimeFormatter dates =DateTimeFormatter .ofPattern("yyyy/MM/dd");
   LocalDateTime now =LocalDateTime.now();
   //date.setText(dates.format(now));
System.out.println(dates.format(now));
        }




	public static void main(String[] args){

	time();
	date();

	


}

}

*/






//-------------------



import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.util.Date;
//from   ww w.j  a  v a 2s. c  om
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;




public class date extends JFrame{
  JLabel time;
  JLabel date;

  void dateFrame(){

    setSize(200,200);
    setLocationRelativeTo(null);
    setLayout(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    time = new JLabel();

    time.setBounds(10,10,100,30);

     date = new JLabel();

   date.setBounds(10,70,100,30);


     tiktok();
   
    Timer timer = new Timer(500, new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        tiktok();
      }
    });
    timer.setRepeats(true);
    timer.setCoalesce(true);
    timer.setInitialDelay(0);
    timer.start();

    add(date);
    add(time);
    setVisible(true);
  }

  public void tiktok(){
    time.setText(DateFormat.getTimeInstance().format(new Date()));
    date.setText(DateFormat.getDateInstance().format(new Date()));
  }


}