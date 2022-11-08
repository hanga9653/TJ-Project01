package banking;

import java.util.InputMismatchException;
import java.util.Scanner;

public class BankingSystemMain {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		
		AccountManager manager = new AccountManager();
		AutoSaver auto = new AutoSaver(manager);
		auto.setDaemon(true);
		manager.readAccoountInfo();
		
		while (true) {
			manager.showMenu();

			try {
				int choice = scan.nextInt();
				if((choice<1||choice>6)) {
					MenuSelectException mse = new MenuSelectException();
					throw mse;
				}
					switch (choice) {
					case ICustomDefine.Make:
						manager.makeAccount();
						break;
					case ICustomDefine.Deposit:
						manager.depositMoney();
						break;
					case ICustomDefine.Withdraw:
						manager.withdrawMoney();
						break;
					case ICustomDefine.Inquire:
						manager.showAccInfo();
						break;
					case ICustomDefine.Saving:
						manager.daemon(auto);
						break;
					case ICustomDefine.Exit:
						System.out.println("프로그램종료");
						manager.saveAccountInfo();
						return;
					}
				}
			catch (MenuSelectException mse) {
				System.out.println("해당메뉴가 없습니다.");
			}
			
			catch (InputMismatchException e) {
				scan.nextLine();
				System.out.println("문자는 입력할 수 없습니다.");
			}
		}
	}
}