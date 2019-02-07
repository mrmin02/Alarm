import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class AlarmItem extends JFrame 
implements ActionListener
{
	
	alarm parent;
	
	JPanel panel1South,plus,plusSouth,panel1Center; // �˶� ���� �г�
	// panel00 �� �߰�, ���� ��ư�� �����ϴ� �г�, panel1�� �ͼ� // plus�� �߰� ��ư�� ������ ��쿡 ������ �г�
	//pluspanel�� �Ϸ��ư�� �����ϴ� �г�, plus �гο� �ͼ� , �Ϸ��ư ������ ������ 
	JButton complete; //�Ϸ� ��ư (�߰����� ..)
	JButton testButton;

	JButton menu1Button1, menu1Button2; // �˶� ���� ��ư �߰�, ����
	
	
	JButton test2Button, tButton;
	static newAlramInfo[] AlButton = new newAlramInfo[10];
	JPanel plusCenter;
	
	JPanel dDay;
	JComboBox OOSelect, hourSelect, MinSelect;
	JComboBox toDay;
	JLabel hourLabel, MinLabel;
	Font f = new Font("����", Font.PLAIN, 23);
	int AlHour; //�˶� ��
	int AlMin;  // ��
	boolean AlOnOff; // �� ����
	boolean OzOh; //���� ����
	String OzOhString;
	static int i = 0;
	int j= 0;
	JButton[] NewAlram = new JButton[10];
	
	JLabel test0 = new JLabel();


	JButton change; 
	
	JPanel cont;
	
	JButton deleteOK;
	
	JRadioButton[] deleteCheck = new JRadioButton[10]; //���� üũ�ڽ�
	ButtonGroup group = new ButtonGroup();
	
	public AlarmItem() {}
	
	public AlarmItem(alarm parent) {
		
	
		this.parent = parent;

		
		//---------------------------------------------------�߰� ���� ��ư
		panel1South = new JPanel();
		parent.panel1.setLayout(new BorderLayout());
		menu1Button1 = new JButton("�߰�");
		menu1Button1.setSize(20,20);
		menu1Button2 = new JButton("����");
		menu1Button2.setSize(20,20);
		
		panel1South.add(menu1Button1);//�߰� ��ư
		panel1South.add(menu1Button2);//������ư
		
		deleteOK = new JButton("Ȯ��");//���� Ȯ�� ��ư
		panel1South.add(deleteOK);
		deleteOK.setVisible(false);
		
		deleteOK.addActionListener(e->{   //-------------���� Ȯ�� ��ư �̺�Ʈ
			menu1Button1.setVisible(true);
			menu1Button2.setVisible(true);
			deleteOK.setVisible(false);
			
			for(int c=0; c<i;c++) {
				deleteCheck[c].setVisible(false);
				NewAlram[c].setEnabled(true);
			}
			
			//���� �̺�Ʈ
			for(int c=0; c<i; c++) {
				if(deleteCheck[c].isSelected()) {

						for(int k=c;k+1<=i;k++) {
							
								
									AlButton[k] = AlButton[k+1];
									
									if(k==i-1) {
										AlButton[k] = null;
										
										i--;
									}
									if(k+1== i-1) {
																	
									AlButton[k+1] = null;
									
									i--;
									}		
	
						}
						
						for(int k =0;k<i+1;k++) {
								if(AlButton[k]==null) {
									NewAlram[k].setText("");
									NewAlram[k].setEnabled(false);
									
								}
						}
						for(int k =0; k<i; k++) {
							NewAlram[k].setText(AlButton[k].OzOhString+"    "
									+AlButton[k].AlHour+" ��    "+AlButton[k].AlMin+"   ��      "+AlButton[k].AlDay);
						}
				}
			}
				
		});
		
		parent.panel1.add("South",panel1South);
		
		//---------------------------------------------------�߰� ��ư ������ �� �̵��ϴ� plus �г� ����
		plus = new JPanel();
		plus.setLayout(new BorderLayout());
		parent.panelAll.add(plus,"plus");
		menu1Button1.addActionListener(e->{
			
			OOSelect.setSelectedItem("����");
			hourSelect.setSelectedItem(0);
			MinSelect.setSelectedItem(0);
			toDay.setSelectedItem("������");
			change.setVisible(false);
			complete.setVisible(true);
			parent.card.show(parent.panelAll,"plus");
		});
		menu1Button2.addActionListener(e->{//-------------������ư �̺�Ʈ
			
			menu1Button1.setVisible(false);
			menu1Button2.setVisible(false);
			deleteOK.setVisible(true);
			
			for(int c=0; c<i;c++) {
				deleteCheck[c].setVisible(true);
				NewAlram[c].setEnabled(false);
			}
			
		});
		
		
		//----------------------------------------------------plus�гο� plusSouth �г� �߰�, �Ϸ�(complete)��ư �߰�
		complete = new JButton("�Ϸ�");
		plusSouth = new JPanel();
		plusSouth.add(complete);
		
		plus.add("South",plusSouth);
		
		complete.addActionListener(e->{
			if(i<10) {
				OzOhString = (String) OOSelect.getSelectedItem();
				if(OzOhString.equals("����")) {
					OzOh = true;
				}else OzOh = false;
				
				AlHour = (int)hourSelect.getSelectedItem();
				AlMin = (int)MinSelect.getSelectedItem();
				
				AlButton[i] = new newAlramInfo(OzOh,AlHour, AlMin,true,(String)toDay.getSelectedItem());
				
				NewAlram[i].setText(AlButton[i].OzOhString+"    "
				+AlButton[i].AlHour+" ��    "+AlButton[i].AlMin+"   ��      "+AlButton[i].AlDay);
				NewAlram[i].setEnabled(true);
	
				plusSouth.add(change);
				
				i++;

			}else {
				errorDialog dialog = new errorDialog();
				dialog.setVisible(true);
			}
			parent.card.show(parent.panelAll, "panel1");// �Ϸ� ��ư ���� ��� �ٽ� panel ���� ���ư��� �̺�Ʈ
		});
		
		//-----------------------------------------------panel1Center �߰�(Grid)
		panel1Center = new JPanel();
		panel1Center.setLayout(new GridLayout(10,1));
		
		
		parent.panel1.add("Center",panel1Center);
		
		for(int qq  =0 ;qq<10;qq++) {
			
				NewAlram[qq] = new JButton();
			
				NewAlram[qq].setFont(f);
				
				NewAlram[qq].setBorderPainted(false);
				
				panel1Center.add(NewAlram[qq]);
				NewAlram[qq].addActionListener(this);
				NewAlram[qq].setActionCommand("�˶���ư"+qq);
				NewAlram[qq].setLayout(new BorderLayout());
				
				deleteCheck[qq] = new JRadioButton();
				NewAlram[qq].add("West", deleteCheck[qq]);
				deleteCheck[qq].setVisible(false);
				

				group.add(deleteCheck[qq]);
				NewAlram[qq].setEnabled(false);

				
		}
		
		//----------------------------------------------plus�� Center ������ plusCenter �г� �߰�
		
		plusCenter = new JPanel();
		plus.add("Center",plusCenter);
		
		//----------------------------------------------plusCenter �гο� ������ �Է� �� �� �ְ� üũ �ڽ����� �߰�
		dDay = new JPanel();
		dDay.setLayout(new GridLayout(0,5));
		plusCenter.add("Center",dDay);//"Center"
		
		OOSelect = new JComboBox();
		hourSelect = new JComboBox();
		MinSelect = new JComboBox();
		toDay = new JComboBox();
		
		OOSelect.addItem("����");
		OOSelect.addItem("����");
		
		
		for(int i=0;i<13;i++) {
			hourSelect.addItem(i);
		}
		for(int i=0; i<60;i++) {
			MinSelect.addItem(i);
		}
//			toDay.addItem(" ");
			toDay.addItem("������");
			toDay.addItem("ȭ����");
			toDay.addItem("������");
			toDay.addItem("�����");
			toDay.addItem("�ݿ���");
			toDay.addItem("�����");
			toDay.addItem("�Ͽ���");
			toDay.setFont(f);
		plusCenter.add(toDay);
		
		hourLabel = new JLabel("  ��");
		MinLabel = new JLabel("  ��");
		hourLabel.setFont(f);
		MinLabel.setFont(f);
		OOSelect.setFont(f);
		hourSelect.setFont(f);
		MinSelect.setFont(f);
		
		dDay.add(OOSelect);
		dDay.add(hourSelect);
		dDay.add(hourLabel);
		dDay.add(MinSelect);
		dDay.add(MinLabel);
		dDay.setFont(f);
		
		
		// - ---------------------------------������ư/ ��ư Ŭ��, ���� -> Ȯ�ι�ư
		change = new JButton("����");
		change.addActionListener(e->{ //j ���� ��ư�� ��ȣ
			
			AlButton[j].OzOhString = (String) OOSelect.getSelectedItem();
			if(AlButton[j].OzOhString.equals("����")) {
				OzOh = true;
			}else OzOh = false;
			AlButton[j].OzOh = OzOh;
			AlButton[j].AlHour = (int)hourSelect.getSelectedItem();
			AlButton[j].AlMin = (int)MinSelect.getSelectedItem();
			AlButton[j].AlDay = (String)toDay.getSelectedItem();
			
			NewAlram[j].setText(AlButton[j].OzOhString+"    "
			+AlButton[j].AlHour+" ��    "+AlButton[j].AlMin+"   ��      "+AlButton[j].AlDay);
			parent.card.show(parent.panelAll,"panel1");
			
		});
		
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String cmd = e.getActionCommand();
		for(j = 0; j<i;j++) {
			if(cmd.equals("�˶���ư"+j)) {
//				AlButton[i].
				OOSelect.setSelectedItem(AlButton[j].OzOhString);
				hourSelect.setSelectedItem(AlButton[j].AlHour);
				MinSelect.setSelectedItem(AlButton[j].AlMin);
				toDay.setSelectedItem(AlButton[j].AlDay);

				change.setVisible(true);
				complete.setVisible(false);
				parent.card.show(parent.panelAll,"plus");
				test0.setText(""+j);
				break;
			}
		}
		
	}
	
}


class newAlramInfo extends JFrame{
	int AlHour; //�˶� ��
	int AlMin;  // ��
	String AlDay;  // ����
	boolean AlOnOff; // �� ����
	boolean OzOh; //���� ����
	String OzOhString;
	
	public newAlramInfo( boolean OzOh, int AlHour, int AlMin, boolean AlOnOff, String AlDay) {
		this.AlHour = AlHour; 
		this.AlMin = AlMin; 
		this.AlDay = AlDay; 
		this.AlOnOff = AlOnOff;
		this.OzOh = OzOh;
		if(OzOh)OzOhString = "����";
		else OzOhString = "����";
		
	}
	
}

