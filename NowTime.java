import java.applet.Applet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class NowTime  implements Runnable{
	
	static Thread clock;
	boolean clockLive = true;
	boolean OO; // 오전 오후인지 판별하는 논리형 변수
	static String OsOs = "오전";
	
	static Calendar now = Calendar.getInstance();
	static int hour_of_day = now.get(Calendar.HOUR_OF_DAY);  	  //시 (0~24)
	static int hour = hour_of_day;   //오전 오후 구별해서 나타내기 위한 변수 
	static int min = now.get(Calendar.MINUTE); 	  //분
	static int second = now.get(Calendar.SECOND); //초
	
	static Date today = new Date();
	static SimpleDateFormat dateForm = new SimpleDateFormat("EE요일");
	static String day = dateForm.format(today);
	AlarmItem tttt = new AlarmItem();
	boolean OsOsName;
	public static void OsChange() {
		
		hour_of_day = now.get(Calendar.HOUR_OF_DAY);
		hour = hour_of_day;
		day = dateForm.format(today);
		
		if(hour_of_day>12 && hour_of_day<24) {
			hour -=12;
			OsOs = "오후";
		}else if(hour_of_day ==12) {
			OsOs ="오후";
		}else {
			OsOs = "오전";
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
			alarm.timeLabel.setText("현재 시각: "+OsOs+" "+NowTime.hour+" 시 "+NowTime.min+" 분 "+NowTime.second+" 초    "+day);
			
			if(OsOs.equals("오전")) OsOsName=true; else OsOsName = false;
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
	         clock=null; //시계정지 (없앰)
	      }
	}
	public void destory() {
		
	}


	
}
