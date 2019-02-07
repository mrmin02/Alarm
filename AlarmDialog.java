import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AlarmDialog extends JDialog {
   JPanel msg,msgSouth;
   JButton msgButton;
   JLabel msgLabel;
//   Mp3 oTo = new Mp3();
   
   public AlarmDialog() {
      setTitle("Error");
      setSize(240,190);
      
      this.setLayout(new BorderLayout());
      msg = new JPanel(); 
      msg.setLayout(new BorderLayout());
      
      msgSouth = new JPanel();
      msg.add(msgSouth);
      
      msgLabel = new JLabel("시간입니다"); 
      msg.add("Center",msgLabel);
      msgLabel.setBounds(25, 60, 200, 25);
      
      
      msgButton = new JButton("확인");
      msgSouth.add(msgButton);
      msgButton.setSize(20,20);
      
      this.add("South",msgSouth);
      this.add("Center",msg);

      
//      oTo.go();
      
      msgButton.addActionListener(e->{
//    	  oTo.stop();
         this.dispose();
      });
      
      
   }
}