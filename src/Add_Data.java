import java.sql.Connection;
import java.sql.Statement;

public class  Add_Data{
	public static void Add(String sql){
		Connection con = LinkDataBase.Link(null);
		//String sql = "insert into Stock(Commodity) values('�ǿ���')";
		try {  
            int result=0;  
            Statement s = con.createStatement();// ����SQL������  
            result=s.executeUpdate(sql);  
            if (result>0) {  
                System.out.println("-------����ɹ�-------");   
            }  
        } catch (Exception e) {
            e.printStackTrace();
        } 
	}
}