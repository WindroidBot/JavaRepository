import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class  Delete_Data{
	public static void Delete(String sql){
		Connection con = LinkDataBase.Link(null);
		//String sql = "delete from student where ID='9'"; 
        try { 
        	int result=0;
        	Statement s = con.createStatement();// ����SQL������  
            result=s.executeUpdate(sql); 
            if(result > 0) { 
                System.out.println("-------ɾ���ɹ���-------"); 
            } else { 
                System.out.println("-------ɾ��ʧ�ܣ�-------"); 
            } 
        } catch(SQLException e) { 
            System.out.println("-------ɾ��ʧ�ܣ�-------"); 
        } 
	}
}