import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class  Update_Data{
	public static void Update(String sql){
		Connection con = LinkDataBase.Link(null);
		//String sql = "update student set Student_Name='张全蛋' where ID='2'";
		try { 
				int result=0;
				Statement s = con.createStatement();// 创建SQL语句对象  
				result = s.executeUpdate(sql); 
	            if(result > 0) { 
	                System.out.println("-------修改成功！-------"); 
	            } else { 
	                System.out.println("-------修改失败！-------"); 
	            } 
	        } catch(SQLException e) { 
	            System.out.println("-------修改失败！-------"); 
	        }
	}
	/*public static void main(String[] args){
		Update("update Stock set Commodity='张全蛋' where ID='2'");
	}*/
}