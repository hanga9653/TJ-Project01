package shopping;

import java.sql.SQLException;
import java.util.Scanner;

public class SelectShop extends IConnect1Impl{

	public SelectShop() {
		super("education","1234");
	}
	
	@Override
	public void execute() {
		try {
			
			String scan  = scanValue("찾는 이름");
			stmt = con.createStatement();
			//sql과 java를 섞어서 문장 만들기
			String print = "SELECT g_idx, goods_name, trim(to_char(goods_price, 'L999,990')), "
					+ " to_char(regidate, 'yyyy-mm-dd hh:mi'),  p_code  "
					+ " FROM sh_goods "
					+ " WHERE goods_name LIKE '%"+ scan +"%'";
			
			rs = stmt.executeQuery(print);
			while(rs.next()) {
				String g_idx = rs.getString(1);
				String goods_name = rs.getString(2);
				String goods_price = rs.getString(3);
				String regidate = rs.getString(4);
				String p_code = rs.getString(5);
				
				System.out.printf("%s %s %s %s %s\n", g_idx, goods_name, goods_price, regidate, p_code);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			close();
		}
	}
	public static void main(String[] args) {
		
		new SelectShop().execute();
	}
}
