package banking;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;

abstract class Account implements Serializable{

	// 인스턴스 변수
	public String accountnumber;
	public String name;
	public int balance;
	
	public Account(HashSet<Account> accHashSet) {}
	
	// 매개변수 생성자 메서드
	public Account(String accountnumber, String name, int balance) {
		this.accountnumber = accountnumber;
		this.name = name;
		this.balance = balance;
	}
	
	@Override
	public int hashCode() {
		return accountnumber.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		Account acc = (Account)obj;
		if(this.accountnumber.equals(acc.accountnumber)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	// 전체 계좌 조회하기
	public void showAccounts() {
		System.out.println("***계좌정보출력***");
		System.out.println("계좌번호: " + accountnumber);
		System.out.println("이름: " + name);
		System.out.println("잔고: " + balance);
	}

	public void withdraw(int withdraw) {
		
	}
	
	public void deposit(int deposit) {
		
	}
}
