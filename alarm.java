import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class alarm extends JFrame{

	//----------------------------------------------------��������
	NowTime jikan;
	JPanel panelAll; //�Ŵ� �Ʒ��� �гε��� ���δ� �г� 
	JPanel panel1, panel2, panel3, panel4, menuPanel; //panelAll�� ���ϴ� �гε�
	
	// panel1 �� �˶�, panel2�� ����ð�, panel3�� �����ġ, panel4�� Ÿ�̸�
	
	JButton menu1, menu2, menu3, menu4; // �� ���� �Ŵ� ��ư
	JPanel time; //���� �ð��� ��Ÿ���� ���̺��� �����ϴ� �г�
	JPanel item; // ���� �ð� �̿��� �͵��� ��� �����ϴ� �г� (panelAll + menuPanel)
	CardLayout card;
	
	static JLabel timeLabel;
	
	//----------------------------------------------------���̾ƿ�, ��ư �̺�Ʈ
	
	
	public alarm() {
		
	setLayout(new BorderLayout()); //��ü���� JFrame�� ���̾ƿ��� BorderLayout��
	card = new CardLayout();		// ī�巹�̾ƿ� ����
	
	//---------------------------------------------------- �� ���� ���� �ð� 
	timeLabel = new JLabel("���� �ð�: "+NowTime.OsOs+" "+NowTime.hour+" �� "+NowTime.min+" �� "+NowTime.second+" ��    "+NowTime.day);
	time = new JPanel();
	time.setLayout(new GridLayout());
	
	time.add(timeLabel);
	add("North",time);  //��ü �г��� ���ʿ� ��ġ
	
	
	
	//---------------------------------------------------- �˶�,Ÿ�̸� ���� ����� ����Ǵ� �гο���
	
	
	panelAll = new JPanel();//���� ����
	panelAll.setLayout(card);   	// panelAll�� ���̾ƿ��� ī�� ���̾ƿ���.
	
	panel1 = new JPanel();//�˶�
//	panel1.setBackground(Color.WHITE);
	panel2 = new JPanel();//����ð�
//	panel2.setBackground(Color.WHITE);
	panel3 = new JPanel();//�����ġ
//	panel3.setBackground(Color.WHITE);
	panel4 = new JPanel();//Ÿ�̸�

	
	panelAll.add(panel1,"panel1");	//�� �гε��� ī�� ���̾ƿ��� panelAll�� �߰�
	panelAll.add(panel2,"panel2");  // �μ��δ� (������Ʈ,"���̵� ��")
	panelAll.add(panel3,"panel3");
	panelAll.add(panel4,"panel4");
	
	// panelAll�� ������ �Ǵ� �гε��� ���� �߰��� ��   panelAll�� item �гο� �߰���.
	item = new JPanel();
	item.setLayout(new BorderLayout());
	
	item.add("Center",panelAll);
	
	
	//------------------------------------------- �Ŵ�
	menuPanel = new JPanel(); //�޴�
	menuPanel.setLayout(new GridLayout(0,4));
	
	menu1 = new JButton("�˶�");
	menu2 = new JButton("����ð�");
	menu3 = new JButton("�����ġ");
	menu4 = new JButton("Ÿ�̸�");
	
	menuPanel.add(menu1);  //�޴����� menuPanel�� �߰�
	menuPanel.add(menu2);
	menuPanel.add(menu3);
	menuPanel.add(menu4);
	item.add("North",menuPanel); // ��� �޴����� �߰��� �г��� menuPanel�� item �гο� �߰�.
	add("Center",item); //��ü �г��� ��� ��ġ
	
	
	
	menu1.addActionListener(e->{
		card.show(panelAll, "panel1");  //��ư �̺�Ʈ (���ٽ�)
	});
	menu2.addActionListener(e->{
		card.show(panelAll, "panel2");
	});
	menu3.addActionListener(e->{
		card.show(panelAll, "panel3");
	});
	menu4.addActionListener(e->{
		card.show(panelAll, "panel4");
	});
	
	//------------------------------------------- JFrame ����
	
	setTitle("�ð�");
	setVisible(true);
	setSize(450,700);
	
	AlarmItem AlramIt = new AlarmItem(this); 
//	TimerTest tt = new TimerTest(this);
//	StopWatch sw = new StopWatch(this);
//	WorldTime wt = new WorldTime(this);
	

	}
	
	
	 //----------------------------------------------------���� 
   public static void main(String[] args) {
		// TODO Auto-generated method stub
	   
	   NowTime.OsChange(); //���� ����
	   alarm me = new alarm();
	   NowTime jikan =  new NowTime();//���� �ð�
	   

	   me.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				
				System.exit(0);
			}
		});
	   
	}
}

