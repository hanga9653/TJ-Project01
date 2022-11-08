package banking;

import java.util.Scanner;

public class AutoSaver extends Thread{
	AccountManager am;
	public AutoSaver(AccountManager auto) {
		am = auto;
	}
	
	
	
	@Override
	public void run() {
		
		while(true) {
			try {
				sleep(5000);
				System.out.println("정보 저장중");
				am.AutoSaveAccountFile();
			}
			catch (InterruptedException e) {
				System.out.println("자동저장종료");
				break;
			}
		}
	}
}
