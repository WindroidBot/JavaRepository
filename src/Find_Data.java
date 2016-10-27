//Find_Data.Count(String sql)方法有返回值，返回查找的计数Count
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
	    	
	    	Statement  s = con.createStatement();// 创建SQL语句对象  
	    	result=s.executeQuery(sql);
	    	while (result.next()) { 
	    	Count++;	
            //System.out.println("编号："+result.getInt("ID")+"		商品名："+result.getString("Commodity")+"		数量："+result.getString("Number"));
        }  
        System.out.println("-------查找完毕-------");
	    } catch (Exception e) {  
            e.printStackTrace();  
        }
		return Count; 
	}
}