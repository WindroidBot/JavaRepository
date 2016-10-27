import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class  Delete_Data{
	public static void Delete(String sql){
		Connection con = LinkDataBase.Link(null);
		//String sql = "delete from student where ID='9'"; 
        try { 
        	int result=0;
        	Statement s = con.createStatement();// ´´½¨SQLÓï¾ä¶ÔÏó  
            result=s.executeUpdate(sql); 
            if(result > 0) { 
                System.out.println("-------É¾³ý³É¹¦£¡-------"); 
            } else { 
                System.out.println("-------É¾³ýÊ§°Ü£¡-------"); 
            } 
        } catch(SQLException e) { 
            System.out.println("-------É¾³ýÊ§°Ü£¡-------"); 
        } 
	}
}