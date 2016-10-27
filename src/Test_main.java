import java.sql.SQLException;


public class  Test_main{
	public static void main(String argv[]) throws SQLException{
		String a="admin";
		String sql = "select * from User where Login_name = '"+a+"'";
		Find_Data find_user = new Find_Data();
		int n = find_user.Count(sql);
		System.out.println(n);
	}
}