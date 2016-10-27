import java.sql.Connection;
import java.sql.Statement;

public class  Add_Data{
	public static void Add(String sql){
		Connection con = LinkDataBase.Link(null);
		//String sql = "insert into Stock(Commodity) values('星空凛')";
		try {  
            int result=0;  
            Statement s = con.createStatement();// 创建SQL语句对象  
            result=s.executeUpdate(sql);  
            if (result>0) {  
                System.out.println("-------插入成功-------");   
            }  
        } catch (Exception e) {
            e.printStackTrace();
        } 
	}
}