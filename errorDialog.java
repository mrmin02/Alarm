import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class errorDialog extends JDialog {// ���� �޼��� ��Ÿ����.
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
		
		msgLabel = new JLabel("  �˶��� 11�� �̻� ���� �� �����ϴ�."); 
		msg.add("Center",msgLabel);
		msgLabel.setBounds(25, 60, 200, 25);
		
		
		msgButton = new JButton("Ȯ��");
		msgSouth.add(msgButton);
		msgButton.setSize(20,20);
		
		this.add("South",msgSouth);
		this.add("Center",msg);
		msgButton.addActionListener(e->{
			this.dispose();
		});
		
		
	}
}
