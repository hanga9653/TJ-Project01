package banking;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AccountManager {

	HashSet<Account> hacc = new HashSet<Account>();
	
	// 메뉴출력
	public void showMenu() {
		System.out.println("===================Menu===================");
		System.out.println("1. 계좌개설,         2. 입금,     3. 출금");
		System.out.println("4. 전체계좌정보출력, 5. 저장옵션, 6. 프로그램종료");
		System.out.println("==========================================");
		System.out.print("선택: ");
	}
	// 계좌계설
	public void makeAccount() {
		Scanner scan = new Scanner(System.in);
		String accountnumber, name, grade;
		int balance, rate;
		
		System.out.println("***신규계좌개설***");
		System.out.println("1.보통계좌 2.신용신뢰계좌");
		int select = scan.nextInt();
		scan.nextLine();
		
		System.out.print("계좌번호:");
		accountnumber = scan.nextLine();

		System.out.print("이름:");
		name = scan.nextLine();

		System.out.print("잔고:");
		balance = scan.nextInt();
		//1번을 입력하면 보통계좌를 생성
		if (select == 1) {
			System.out.print("기본이자%(정수형태로입력):");
			rate = scan.nextInt();
			scan.nextLine();
			//보통계좌 생성자
			NormalAccount NA = new NormalAccount(accountnumber, name, balance, rate);
			//HashSet에 보통계좌를 추가
			if (hacc.add(NA))
				hacc.add(NA);
			else {
				System.out.println("이미 있는 계좌입니다. 갱신할까요?? y or n");
				try {
					String yn = scan.nextLine();
					if(!(yn.equalsIgnoreCase("y"))) {
						System.out.println("Y(y) 또는 N(n)을 입력하세요");
					}
					else if (yn.equalsIgnoreCase("y")) {
						System.out.println("갱신했습니다.");
						//HashSet에 있는 보통계좌를 꺼내고
						hacc.remove(NA);
						//HashSet에 보통계좌를 추가
						hacc.add(NA);
						System.out.println("계좌 개설이 완료되었습니다.");
					} else if (yn.equalsIgnoreCase("n")) {
						System.out.println("계좌개설을 취소합니다.");
					}
				}
				catch (Exception e) {
				
				}
			}
		}
		//2번을 입력하면 신용계좌를 생성
		else if (select == 2) {
			System.out.print("기본이자%(정수형태로입력):");
			rate = scan.nextInt();
			scan.nextLine();
			System.out.print("신용등급(A,B,C등급):");
			grade = scan.nextLine();
			//신용등급을 입력할때 A,B,C가 아닌 다른 문자이거나 숫자이면 예외처리를 한다.
			try {
				if(!("A".equalsIgnoreCase(grade)||"B".equalsIgnoreCase(grade)||"C".equalsIgnoreCase(grade))) {
					MenuSelectException ex = new MenuSelectException();
					throw ex;
				}
				HighCreditAccount HA = new HighCreditAccount(accountnumber, name, balance, rate, grade);
				if (hacc.add(HA))
					hacc.add(HA);
				else {
					System.out.println("이미 있는 계좌입니다. 갱신할까요?? y or n");
					try {
						String yn = scan.nextLine();
						if(!(yn.equalsIgnoreCase("y"))) {
							System.out.println("Y(y) 또는 N(n)을 입력하세요");
						}
						if (yn.equalsIgnoreCase("y")) {
							System.out.println("갱신했습니다.");
							hacc.remove(HA);
							hacc.add(HA);
							System.out.println("계좌 개설이 완료되었습니다.");
						} else if (yn.equalsIgnoreCase("n")) {
							System.out.println("계좌개설을 취소합니다.");
						}
					} catch (Exception e) {

					}
				}
			}
			catch (MenuSelectException e) {
				System.out.println("신용등급 입력이 잘못되었습니다ㅏ.");
			}
		}
	}

	// 입금
	public void depositMoney() {
		Scanner scan = new Scanner(System.in);
		System.out.println("***입금***");
		System.out.println("계좌번호와 입금할 금액을 입력하세요.");
		System.out.println("계좌번호 :");
		String accountnum = scan.nextLine();
		try {
			System.out.print("입금액 :");
			int deposit = scan.nextInt();
			scan.nextLine();

			if (deposit < 0) {
				System.out.println("음수는 입력할 수 없습니다.");
			}
			if (deposit % 500 != 0) {
				System.out.println("500원 단위로 입력하세요.");
			}
			else if (deposit > 0) {
				for(Account depo : hacc) {
					if(depo.accountnumber.equals(accountnum))
					depo.deposit(deposit);
				}
				System.out.println("입금이 완료되었습니다.");
			}
			else if (deposit == 0) {
				System.out.println("0원 이상의 금액을 입력해주세요.");
			}
		} catch (InputMismatchException e) {
			System.out.println("숫자로 입력해주세요");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 출금하기
	public void withdrawMoney() {
		Scanner scan = new Scanner(System.in);
		System.out.println("***출금***");
		System.out.println("계좌번호와 출금할 금액을 입력하세요.");
		System.out.print("계좌번호 :");
		String accountnum = scan.nextLine();
		try {
			System.out.print("출금액 :");
			int withdraw = scan.nextInt();
			scan.nextLine();
			if (withdraw < 0) {
				System.out.println("음수는 입력할 수 없습니다.");
			} else {
				if (withdraw % 1000 != 0) {
					System.out.println("1000원 단위로만 출금가능합니다.");
				} else {
					for(Account with : hacc) {
						if(with.accountnumber.equals(accountnum))
						if(with.balance > withdraw) {
							with.balance -= withdraw;
							System.out.println("출금이 완료되었습니다.");
						}
						else {
							System.out.println("잔고가 부족합니다. 전체 잔액을 출금할까요? y or n");
							System.out.println("y: 금액전체 출금처리");
							System.out.println("n: 출금요청취소");
							String yn = scan.nextLine();
							if (yn.equalsIgnoreCase("y")) {
								System.out.println("전체 잔액을 출금합니다.");
								with.balance -= with.balance;
							} else if (yn.equalsIgnoreCase("n")) {
								System.out.println("출금요청이 취소되었습니다.");
							}
						}
					}
				}
			}
		}
			catch (InputMismatchException e) {
			System.out.println("숫자로 입력해주세요.");
		} catch (Exception e) {
			System.out.println("예외");
		}
	}

	// 전체 계좌 조회하기
	public void showAccInfo() {
		for (Account acc : hacc) {
			acc.showAccounts();
		}
		System.out.println("전체계좌정보 출력이 완료되었습니다.");
	}
	
	//프로그램 종료시 정보를 저장한다.
	public void saveAccountInfo() {
		try {
			ObjectOutputStream out =
					new ObjectOutputStream(
							new FileOutputStream("src/banking/AccountInfo.obj"));
			for(Account ac : hacc) {
				out.writeObject(ac);
			}
			out.close();
		}
		catch (Exception e) {
			System.out.println("정보 직렬화 중 예외발생");
			e.printStackTrace();
		}
		
	}
	
	public void readAccoountInfo() {
		try {
			ObjectInputStream in =
					new ObjectInputStream(
							new FileInputStream("src/banking/AccountInfo.obj"));
			while(true) {
				Account ac = (Account)in.readObject();
				hacc.add(ac);
			}
		}
		catch (Exception e) {
			
		}
		
	}
	
	public void daemon(AutoSaver auto) {
		
		System.out.println("1.자동저장On  2.자동저장Off");
		Scanner scan = new Scanner(System.in);
		int select = scan.nextInt();
		scan.nextLine();
		if(select == 1) {
			if(auto.isAlive()) {
				System.out.println("이미 실행중입니다.");
			}else{
				System.out.println("자동저장On");
				auto.start();
			}
		}
		else if(select == 2) {
			System.out.println("자동저장Off");
			auto.interrupt();
		}
			
	}
	
	public void AutoSaveAccountFile() {
		try {
			PrintWriter out = new PrintWriter(
					new FileWriter("src/banking/AutoSaveAccount.txt"));
			
			for(Account ac1 : hacc) {
				if(ac1 instanceof NormalAccount) {
					out.printf("계좌번호: %s, 이름: %s, 잔금: %d, 이율: %d",
							((NormalAccount)ac1).accountnumber,
							((NormalAccount)ac1).name,
							((NormalAccount)ac1).balance,
							((NormalAccount)ac1).rate);
					out.println();
				}
				else if(ac1 instanceof HighCreditAccount) {
					out.printf("계좌번호: %s, 이름: %s, 잔금: %d, 이율: %d, 신용등급: %s",
							((HighCreditAccount)ac1).accountnumber,
							((HighCreditAccount)ac1).name,
							((HighCreditAccount)ac1).balance,
							((HighCreditAccount)ac1).rate,
							((HighCreditAccount)ac1).grade);
					out.println();
				}
			}
			out.close();
			System.out.println("파일이 생성되었습니다.");
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
