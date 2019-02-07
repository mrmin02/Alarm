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
	
	JPanel panel1South,plus,plusSouth,panel1Center; // 알람 관련 패널
	// panel00 은 추가, 삭제 버튼을 포함하는 패널, panel1에 귀속 // plus는 추가 버튼을 눌렀을 경우에 나오는 패널
	//pluspanel은 완료버튼을 포함하는 패널, plus 패널에 귀속 , 완료버튼 누르면 나오는 
	JButton complete; //완료 버튼 (추가에서 ..)
	JButton testButton;

	JButton menu1Button1, menu1Button2; // 알람 관련 버튼 추가, 삭제
	
	
	JButton test2Button, tButton;
	static newAlramInfo[] AlButton = new newAlramInfo[10];
	JPanel plusCenter;
	
	JPanel dDay;
	JComboBox OOSelect, hourSelect, MinSelect;
	JComboBox toDay;
	JLabel hourLabel, MinLabel;
	Font f = new Font("돋움", Font.PLAIN, 23);
	int AlHour; //알람 시
	int AlMin;  // 분
	boolean AlOnOff; // 온 오프
	boolean OzOh; //오전 오후
	String OzOhString;
	static int i = 0;
	int j= 0;
	JButton[] NewAlram = new JButton[10];
	
	JLabel test0 = new JLabel();


	JButton change; 
	
	JPanel cont;
	
	JButton deleteOK;
	
	JRadioButton[] deleteCheck = new JRadioButton[10]; //삭제 체크박스
	ButtonGroup group = new ButtonGroup();
	
	public AlarmItem() {}
	
	public AlarmItem(alarm parent) {
		
	
		this.parent = parent;

		
		//---------------------------------------------------추가 삭제 버튼
		panel1South = new JPanel();
		parent.panel1.setLayout(new BorderLayout());
		menu1Button1 = new JButton("추가");
		menu1Button1.setSize(20,20);
		menu1Button2 = new JButton("삭제");
		menu1Button2.setSize(20,20);
		
		panel1South.add(menu1Button1);//추가 버튼
		panel1South.add(menu1Button2);//삭제버튼
		
		deleteOK = new JButton("확인");//삭제 확인 버튼
		panel1South.add(deleteOK);
		deleteOK.setVisible(false);
		
		deleteOK.addActionListener(e->{   //-------------삭제 확인 버튼 이벤트
			menu1Button1.setVisible(true);
			menu1Button2.setVisible(true);
			deleteOK.setVisible(false);
			
			for(int c=0; c<i;c++) {
				deleteCheck[c].setVisible(false);
				NewAlram[c].setEnabled(true);
			}
			
			//삭제 이벤트
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
									+AlButton[k].AlHour+" 시    "+AlButton[k].AlMin+"   분      "+AlButton[k].AlDay);
						}
				}
			}
				
		});
		
		parent.panel1.add("South",panel1South);
		
		//---------------------------------------------------추가 버튼 눌렀을 때 이동하는 plus 패널 관련
		plus = new JPanel();
		plus.setLayout(new BorderLayout());
		parent.panelAll.add(plus,"plus");
		menu1Button1.addActionListener(e->{
			
			OOSelect.setSelectedItem("오전");
			hourSelect.setSelectedItem(0);
			MinSelect.setSelectedItem(0);
			toDay.setSelectedItem("월요일");
			change.setVisible(false);
			complete.setVisible(true);
			parent.card.show(parent.panelAll,"plus");
		});
		menu1Button2.addActionListener(e->{//-------------삭제버튼 이벤트
			
			menu1Button1.setVisible(false);
			menu1Button2.setVisible(false);
			deleteOK.setVisible(true);
			
			for(int c=0; c<i;c++) {
				deleteCheck[c].setVisible(true);
				NewAlram[c].setEnabled(false);
			}
			
		});
		
		
		//----------------------------------------------------plus패널에 plusSouth 패널 추가, 완료(complete)버튼 추가
		complete = new JButton("완료");
		plusSouth = new JPanel();
		plusSouth.add(complete);
		
		plus.add("South",plusSouth);
		
		complete.addActionListener(e->{
			if(i<10) {
				OzOhString = (String) OOSelect.getSelectedItem();
				if(OzOhString.equals("오전")) {
					OzOh = true;
				}else OzOh = false;
				
				AlHour = (int)hourSelect.getSelectedItem();
				AlMin = (int)MinSelect.getSelectedItem();
				
				AlButton[i] = new newAlramInfo(OzOh,AlHour, AlMin,true,(String)toDay.getSelectedItem());
				
				NewAlram[i].setText(AlButton[i].OzOhString+"    "
				+AlButton[i].AlHour+" 시    "+AlButton[i].AlMin+"   분      "+AlButton[i].AlDay);
				NewAlram[i].setEnabled(true);
	
				plusSouth.add(change);
				
				i++;

			}else {
				errorDialog dialog = new errorDialog();
				dialog.setVisible(true);
			}
			parent.card.show(parent.panelAll, "panel1");// 완료 버튼 누를 경우 다시 panel 으로 돌아가는 이벤트
		});
		
		//-----------------------------------------------panel1Center 추가(Grid)
		panel1Center = new JPanel();
		panel1Center.setLayout(new GridLayout(10,1));
		
		
		parent.panel1.add("Center",panel1Center);
		
		for(int qq  =0 ;qq<10;qq++) {
			
				NewAlram[qq] = new JButton();
			
				NewAlram[qq].setFont(f);
				
				NewAlram[qq].setBorderPainted(false);
				
				panel1Center.add(NewAlram[qq]);
				NewAlram[qq].addActionListener(this);
				NewAlram[qq].setActionCommand("알람버튼"+qq);
				NewAlram[qq].setLayout(new BorderLayout());
				
				deleteCheck[qq] = new JRadioButton();
				NewAlram[qq].add("West", deleteCheck[qq]);
				deleteCheck[qq].setVisible(false);
				

				group.add(deleteCheck[qq]);
				NewAlram[qq].setEnabled(false);

				
		}
		
		//----------------------------------------------plus의 Center 영역에 plusCenter 패널 추가
		
		plusCenter = new JPanel();
		plus.add("Center",plusCenter);
		
		//----------------------------------------------plusCenter 패널에 정보를 입력 할 수 있게 체크 박스등을 추가
		dDay = new JPanel();
		dDay.setLayout(new GridLayout(0,5));
		plusCenter.add("Center",dDay);//"Center"
		
		OOSelect = new JComboBox();
		hourSelect = new JComboBox();
		MinSelect = new JComboBox();
		toDay = new JComboBox();
		
		OOSelect.addItem("오전");
		OOSelect.addItem("오후");
		
		
		for(int i=0;i<13;i++) {
			hourSelect.addItem(i);
		}
		for(int i=0; i<60;i++) {
			MinSelect.addItem(i);
		}
//			toDay.addItem(" ");
			toDay.addItem("월요일");
			toDay.addItem("화요일");
			toDay.addItem("수요일");
			toDay.addItem("목요일");
			toDay.addItem("금요일");
			toDay.addItem("토요일");
			toDay.addItem("일요일");
			toDay.setFont(f);
		plusCenter.add(toDay);
		
		hourLabel = new JLabel("  시");
		MinLabel = new JLabel("  분");
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
		
		
		// - ---------------------------------수정버튼/ 버튼 클릭, 수정 -> 확인버튼
		change = new JButton("수정");
		change.addActionListener(e->{ //j 누른 버튼의 번호
			
			AlButton[j].OzOhString = (String) OOSelect.getSelectedItem();
			if(AlButton[j].OzOhString.equals("오전")) {
				OzOh = true;
			}else OzOh = false;
			AlButton[j].OzOh = OzOh;
			AlButton[j].AlHour = (int)hourSelect.getSelectedItem();
			AlButton[j].AlMin = (int)MinSelect.getSelectedItem();
			AlButton[j].AlDay = (String)toDay.getSelectedItem();
			
			NewAlram[j].setText(AlButton[j].OzOhString+"    "
			+AlButton[j].AlHour+" 시    "+AlButton[j].AlMin+"   분      "+AlButton[j].AlDay);
			parent.card.show(parent.panelAll,"panel1");
			
		});
		
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String cmd = e.getActionCommand();
		for(j = 0; j<i;j++) {
			if(cmd.equals("알람버튼"+j)) {
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
	int AlHour; //알람 시
	int AlMin;  // 분
	String AlDay;  // 요일
	boolean AlOnOff; // 온 오프
	boolean OzOh; //오전 오후
	String OzOhString;
	
	public newAlramInfo( boolean OzOh, int AlHour, int AlMin, boolean AlOnOff, String AlDay) {
		this.AlHour = AlHour; 
		this.AlMin = AlMin; 
		this.AlDay = AlDay; 
		this.AlOnOff = AlOnOff;
		this.OzOh = OzOh;
		if(OzOh)OzOhString = "오전";
		else OzOhString = "오후";
		
	}
	
}

