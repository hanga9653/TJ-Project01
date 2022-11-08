package shopping;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class IConnect1Impl implements IConnect1{
	
	public Connection con;
	public PreparedStatement psmt;
	public Statement stmt;
	public CallableStatement csmt;
	public ResultSet rs;
	
	public IConnect1Impl(String user, String pass) {
		try {
			Class.forName(ORACLE_DRIVER);
			connect(user, pass);
		}
		catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
			e.printStackTrace();
		}
	}
	
	public IConnect1Impl() {}

	@Override
	public void connect(String user, String pass) {
		try {
			con = DriverManager.getConnection(ORACLE_URL, user, pass);
		}
		catch (SQLException e) {
			System.out.println("데이터베이스 연결 오류");
			e.printStackTrace();
		}
	}
	
	public void execute() {}
	
	public void close() {
		try {
			if(con!=null) con.close();
			if(psmt!=null) csmt.close();
			if(rs!=null) rs.close();
			if(stmt!=null)stmt.close();
			if(csmt!=null) csmt.close();
			System.out.println("자원 반납 완료");
		}
		catch (Exception e) {
//			e.printStackTrace();
		}
	}//자원 반납
	
	//사용자로부터 입력을 받기 위해 정의
	@Override
	public String scanValue(String title) {
		
		Scanner scan = new Scanner(System.in);
		System.out.println(title +"을(를) 입력(exit->종료):");
		String inputStr = scan.nextLine();
		
		if("EXIT".equalsIgnoreCase(inputStr)){
			System.out.println("프로그램을 종료합니다.");
			close();
			System.exit(0);
		}
		return inputStr;
	}
}
