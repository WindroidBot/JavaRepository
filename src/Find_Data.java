//Find_Data.Count(String sql)�����з���ֵ�����ز��ҵļ���Count
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class  Find_Data{
	public int Count(String sql){
		Connection con = LinkDataBase.Link(null);
		int Count = 0;
		//String sql = "select * from Student"; 
	    ResultSet result = null;
	    try{
	    	
	    	Statement  s = con.createStatement();// ����SQL������  
	    	result=s.executeQuery(sql);
	    	while (result.next()) { 
	    	Count++;	
            //System.out.println("��ţ�"+result.getInt("ID")+"		��Ʒ����"+result.getString("Commodity")+"		������"+result.getString("Number"));
        }  
        System.out.println("-------�������-------");
	    } catch (Exception e) {  
            e.printStackTrace();  
        }
		return Count; 
	}
}