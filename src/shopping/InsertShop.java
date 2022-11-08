package shopping;

import java.util.Scanner;


public class InsertShop extends IConnect1Impl{

	public InsertShop() {
		super("education", "1234");
	}
		
	@Override
	public void execute() {
		String query = "INSERT INTO sh_goods " 
				+ " (g_idx, goods_name, goods_price, p_code)VALUES "
				+ " (seq_total_idx.nextval, ?, ?, ?) " ;
		try {
			psmt = con.prepareStatement(query);
			
			Scanner scan = new Scanner(System.in);
			System.out.println("상품명");
			String goods_name = scan.nextLine();
			System.out.println("상품가격");
			String goods_price = scan.nextLine();
			System.out.println("상품코드");
			String p_code = scan.nextLine();
			
			psmt.setString(1, goods_name);
			psmt.setString(2, goods_price);
			psmt.setString(3, p_code);
			
			int affected = psmt.executeUpdate();
			System.out.println(affected + "행이 입력되었습니다.");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			close();
		}
	}
	public static void main(String[] args) {
		new InsertShop().execute();
	}

}
