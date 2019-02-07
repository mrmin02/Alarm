import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class errorDialog extends JDialog {// 에러 메세지 나타내기.
	JPanel msg,msgSouth;
	JButton msgButton;
	JLabel msgLabel;
	
	public errorDialog() {
		setTitle("Error");
		setSize(240,190);
		
		this.setLayout(new BorderLayout());
		msg = new JPanel(); 
		msg.setLayout(new BorderLayout());
		
		msgSouth = new JPanel();
		msg.add(msgSouth);
		
		msgLabel = new JLabel("  알람은 11개 이상 만들 수 없습니다."); 
		msg.add("Center",msgLabel);
		msgLabel.setBounds(25, 60, 200, 25);
		
		
		msgButton = new JButton("확인");
		msgSouth.add(msgButton);
		msgButton.setSize(20,20);
		
		this.add("South",msgSouth);
		this.add("Center",msg);
		msgButton.addActionListener(e->{
			this.dispose();
		});
		
		
	}
}
