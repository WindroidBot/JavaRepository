import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class  Update_Data{
	public static void Update(String sql){
		Connection con = LinkDataBase.Link(null);
		//String sql = "update student set Student_Name='��ȫ��' where ID='2'";
		try { 
				int result=0;
				Statement s = con.createStatement();// ����SQL������  
				result = s.executeUpdate(sql); 
	            if(result > 0) { 
	                System.out.println("-------�޸ĳɹ���-------"); 
	            } else { 
	                System.out.println("-------�޸�ʧ�ܣ�-------"); 
	            } 
	        } catch(SQLException e) { 
	            System.out.println("-------�޸�ʧ�ܣ�-------"); 
	        }
	}
	/*public static void main(String[] args){
		Update("update Stock set Commodity='��ȫ��' where ID='2'");
	}*/
}