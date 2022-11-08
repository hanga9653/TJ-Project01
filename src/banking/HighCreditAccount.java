package banking;

public class HighCreditAccount extends Account implements ICustomDefine{

	public int rate;
	public String grade;
	
	public HighCreditAccount(String accountnumber, String name, int balance, int rate, String grade) {
		super(accountnumber, name, balance);
		this.rate = rate;
		this.grade = grade;
	}
	
	@Override
	public void deposit(int money) {
		if(grade.equalsIgnoreCase("A")) {
			balance = balance+(balance*rate/100)+(balance*A/100)+money;
		}
		else if(grade.equalsIgnoreCase("B")) {
			balance = balance+(balance*rate/100)+(balance*B/100)+money;
		}
		else if(grade.equalsIgnoreCase("C")){
			balance = balance+(balance*rate/100)+(balance*C/100)+money;
		}
	}

	@Override
	public void showAccounts() {
		System.out.println("***계좌정보출력***");
		System.out.println("계좌번호: " + accountnumber);
		System.out.println("이름: " + name + " 잔고: " + balance);
		System.out.println("기본이율:" + rate+"%" + " 신용등급:" + grade);
		System.out.println("******************");
		System.out.println();
	}
}
