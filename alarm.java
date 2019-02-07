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

	//----------------------------------------------------전역변수
	NowTime jikan;
	JPanel panelAll; //매뉴 아래의 패널들을 감싸는 패널 
	JPanel panel1, panel2, panel3, panel4, menuPanel; //panelAll에 속하는 패널들
	
	// panel1 은 알람, panel2는 세계시각, panel3은 스톱워치, panel4는 타이머
	
	JButton menu1, menu2, menu3, menu4; // 맨 위의 매뉴 버튼
	JPanel time; //현재 시각을 나타내는 레이블을 포함하는 패널
	JPanel item; // 현재 시각 이외의 것들을 모두 포함하는 패널 (panelAll + menuPanel)
	CardLayout card;
	
	static JLabel timeLabel;
	
	//----------------------------------------------------레이아웃, 버튼 이벤트
	
	
	public alarm() {
		
	setLayout(new BorderLayout()); //전체적인 JFrame의 레이아웃은 BorderLayout임
	card = new CardLayout();		// 카드레이아웃 생성
	
	//---------------------------------------------------- 맨 위의 현재 시각 
	timeLabel = new JLabel("현재 시각: "+NowTime.OsOs+" "+NowTime.hour+" 시 "+NowTime.min+" 분 "+NowTime.second+" 초    "+NowTime.day);
	time = new JPanel();
	time.setLayout(new GridLayout());
	
	time.add(timeLabel);
	add("North",time);  //전체 패널의 윗쪽에 배치
	
	
	
	//---------------------------------------------------- 알람,타이머 등의 기능이 실행되는 패널영역
	
	
	panelAll = new JPanel();//전부 모음
	panelAll.setLayout(card);   	// panelAll의 레이아웃이 카드 레이아웃임.
	
	panel1 = new JPanel();//알람
//	panel1.setBackground(Color.WHITE);
	panel2 = new JPanel();//세계시각
//	panel2.setBackground(Color.WHITE);
	panel3 = new JPanel();//스톱워치
//	panel3.setBackground(Color.WHITE);
	panel4 = new JPanel();//타이머

	
	panelAll.add(panel1,"panel1");	//각 패널들을 카드 레이아웃인 panelAll에 추가
	panelAll.add(panel2,"panel2");  // 인수로는 (컴포넌트,"아이디 값")
	panelAll.add(panel3,"panel3");
	panelAll.add(panel4,"panel4");
	
	// panelAll에 내용이 되는 패널들을 전부 추가한 후   panelAll을 item 패널에 추가함.
	item = new JPanel();
	item.setLayout(new BorderLayout());
	
	item.add("Center",panelAll);
	
	
	//------------------------------------------- 매뉴
	menuPanel = new JPanel(); //메뉴
	menuPanel.setLayout(new GridLayout(0,4));
	
	menu1 = new JButton("알람");
	menu2 = new JButton("세계시각");
	menu3 = new JButton("스톱워치");
	menu4 = new JButton("타이머");
	
	menuPanel.add(menu1);  //메뉴들을 menuPanel에 추가
	menuPanel.add(menu2);
	menuPanel.add(menu3);
	menuPanel.add(menu4);
	item.add("North",menuPanel); // 모든 메뉴들이 추가된 패널인 menuPanel을 item 패널에 추가.
	add("Center",item); //전체 패널의 가운데 배치
	
	
	
	menu1.addActionListener(e->{
		card.show(panelAll, "panel1");  //버튼 이벤트 (람다식)
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
	
	//------------------------------------------- JFrame 설정
	
	setTitle("시계");
	setVisible(true);
	setSize(450,700);
	
	AlarmItem AlramIt = new AlarmItem(this); 
//	TimerTest tt = new TimerTest(this);
//	StopWatch sw = new StopWatch(this);
//	WorldTime wt = new WorldTime(this);
	

	}
	
	
	 //----------------------------------------------------메인 
   public static void main(String[] args) {
		// TODO Auto-generated method stub
	   
	   NowTime.OsChange(); //오전 오후
	   alarm me = new alarm();
	   NowTime jikan =  new NowTime();//현재 시간
	   

	   me.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				
				System.exit(0);
			}
		});
	   
	}
}

