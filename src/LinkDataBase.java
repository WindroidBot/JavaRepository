//�����������ݿ������
//ʹ��Connection con = LinkDataBase.Link(argv);�����ݿ����ӣ�������databaseURL���Ե�ֵ
//ʹ��LinkDataBase.CloseLink(argv);�ر����ݿ�����
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class LinkDataBase{
		//����������
	static String driveName = "com.hxtt.sql.access.AccessDriver";
		//URLָ��Ҫ���ʵ����ݿ���
	static String databaseURL = "Jdbc:Access:///C:/Users/xiexy/Documents/TestDataBase.mdb";
	static Connection con;
	public static Connection Link(String argv[]){
		try
        {
			//��������������
            Class.forName(driveName);
            	System.out.println("---�ɹ��������ݿ���������---");
            con = DriverManager.getConnection(databaseURL);
            	System.out.println("-------�������ݿ�ɹ�-------");
        }
		catch (java.lang.ClassNotFoundException | SQLException e)
        {
            System.out.println("-------�������ݿ���������ʧ��-------");
            System.out.println(e.getMessage());
        }
		return con;
	}
	
	public static void CloseLink(String argv[]){
		 try {           
			 con.close();  
			 System.out.println("-------�Ͽ����ݿ����ӳɹ�-------");
	         } catch (SQLException e) {  
	            e.printStackTrace();  
	         }
	}
}

