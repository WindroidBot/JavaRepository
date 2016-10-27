//Find_Data.Count(String sql)方法有返回值，返回查找的计数Count
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class  g extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7522671696562169103L;
	private JTable table_1;
	public g() {
		getContentPane().setBounds(300,300,450,500);
		getContentPane().setLayout(null);
		setVisible(true);
		//btnNewButton.setBounds(41, 10, 93, 23);
		//getContentPane().add(btnNewButton);
		
		JButton btn = new JButton("New button");
		btn.setBounds(67, 23, 93, 23);
		getContentPane().add(btn);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(26, 67, 398, 184);
		getContentPane().add(scrollPane);
		
		table_1 = new JTable();
		scrollPane.setViewportView(table_1);
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Connection con = LinkDataBase.Link(null);
				int Count = 0;
				String sql = "select * from Stock"; 
			    ResultSet result = 	null;
			    try{
			    	
			    	Statement  s = con.createStatement();// 创建SQL语句对象  
			    	result=s.executeQuery(sql);
			    	Vector values =new Vector();
			    	while (result.next()) { 
			    	Vector temp=new Vector();
			    	temp.add(result.getString("ID"));
			    	temp.add(result.getString("Commodity"));
			    	temp.add(result.getString("Number"));
			    	temp.add(result.getString("price"));
			    	values.add(temp);
			    	
		           // System.out.println("编号："+result.getInt("ID")+"		商品名："+result.getString("Commodity")+"		数量："+result.getString("Number"));
			    	}	
			    	Vector item=new Vector();
			    	item.add("ID");
			    	item.add("Commodity");
			    	item.add("Number");
			    	item.add("price");
			    	DefaultTableModel model=new DefaultTableModel(values,item);
			    	table_1.setModel(model);
			}catch(Exception e){
				e.printStackTrace();
			}
			}
		});
		
		//JButton btnNewButton = new JButton("New button");
		//btnNewButton.addActionListener(new ActionListener() {
			//public void actionPerformed(ActionEvent arg0) {
				
		//});
		
	}
	public static void main(String[] args){
		new g();
	}
}
