//此类用于数据库的链接
//使用Connection con = LinkDataBase.Link(argv);与数据库链接，并返回databaseURL属性的值
//使用LinkDataBase.CloseLink(argv);关闭数据库连接
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class LinkDataBase{
		//驱动程序名
	static String driveName = "com.hxtt.sql.access.AccessDriver";
		//URL指向要访问的数据库名
	static String databaseURL = "Jdbc:Access:///C:/Users/xiexy/Documents/TestDataBase.mdb";
	static Connection con;
	public static Connection Link(String argv[]){
		try
        {
			//加载驱动并链接
            Class.forName(driveName);
            	System.out.println("---成功加载数据库驱动程序---");
            con = DriverManager.getConnection(databaseURL);
            	System.out.println("-------连接数据库成功-------");
        }
		catch (java.lang.ClassNotFoundException | SQLException e)
        {
            System.out.println("-------加载数据库驱动程序失败-------");
            System.out.println(e.getMessage());
        }
		return con;
	}
	
	public static void CloseLink(String argv[]){
		 try {           
			 con.close();  
			 System.out.println("-------断开数据库连接成功-------");
	         } catch (SQLException e) {  
	            e.printStackTrace();  
	         }
	}
}

