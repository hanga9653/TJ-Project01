package banking;

public class NormalAccount extends Account{

	public int rate;
	public NormalAccount(String accountnumber, String name, int balance, int rate) {
		super(accountnumber, name, balance);
		this.rate = rate;
	}

	@Override
	public void deposit(int deposit) {
		balance = balance+(balance*rate/100)+deposit;
	}
	
	@Override
	public void showAccounts() {
		System.out.println("***계좌정보출력***");
		System.out.println("계좌번호: " + accountnumber);
		System.out.println("이름: " + name + " 잔고: " + balance);
		System.out.println("기본이율:" + rate+"%");
		System.out.println("******************");
		System.out.println();
	}
}
