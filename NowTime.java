import java.applet.Applet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class NowTime  implements Runnable{
	
	static Thread clock;
	boolean clockLive = true;
	boolean OO; // ���� �������� �Ǻ��ϴ� ���� ����
	static String OsOs = "����";
	
	static Calendar now = Calendar.getInstance();
	static int hour_of_day = now.get(Calendar.HOUR_OF_DAY);  	  //�� (0~24)
	static int hour = hour_of_day;   //���� ���� �����ؼ� ��Ÿ���� ���� ���� 
	static int min = now.get(Calendar.MINUTE); 	  //��
	static int second = now.get(Calendar.SECOND); //��
	
	static Date today = new Date();
	static SimpleDateFormat dateForm = new SimpleDateFormat("EE����");
	static String day = dateForm.format(today);
	AlarmItem tttt = new AlarmItem();
	boolean OsOsName;
	public static void OsChange() {
		
		hour_of_day = now.get(Calendar.HOUR_OF_DAY);
		hour = hour_of_day;
		day = dateForm.format(today);
		
		if(hour_of_day>12 && hour_of_day<24) {
			hour -=12;
			OsOs = "����";
		}else if(hour_of_day ==12) {
			OsOs ="����";
		}else {
			OsOs = "����";
		}
	}
	
	
	public NowTime() {
		clock = new Thread(this);
		clock.setDaemon(true);
		clock.start();
	}
	public void init() {
		if(clock==null) {
//			
			clock.start();
		}
	}
	public void start() {
	}
	public void run() {
		while(clockLive) {
			try {
				clock.sleep(1000);
			} catch (InterruptedException e) {}
			
			second++;
			if(second==60) {
				min++;
				second=0;
			}
			if(min==60) {
				hour_of_day++;
				hour++;
				min=0;
			}
			if(hour_of_day==13||hour_of_day==24||hour_of_day ==12) {OsChange();}
			alarm.timeLabel.setText("���� �ð�: "+OsOs+" "+NowTime.hour+" �� "+NowTime.min+" �� "+NowTime.second+" ��    "+day);
			
			if(OsOs.equals("����")) OsOsName=true; else OsOsName = false;
			for(int h = 0;h< AlarmItem.i;h++) {
				if((AlarmItem.AlButton[h].AlHour == hour)&&(AlarmItem.AlButton[h].AlMin == min)
						&&(AlarmItem.AlButton[h].OzOh==OsOsName)&&(AlarmItem.AlButton[h].AlDay.equals(day)&&(second==0)) ) {
					AlarmDialog bell = new AlarmDialog();
					bell.setVisible(true);
				}
			}
			
		}
	}
	public void stop() {
		if((clock!=null)&&(clock.isAlive())) {
	         clock=null; //�ð����� (����)
	      }
	}
	public void destory() {
		
	}


	
}
