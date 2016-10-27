import java.awt.Toolkit;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.awt.Font;


@SuppressWarnings("serial")
public class Add_Data_GUI extends JDialog implements ItemListener,ActionListener{

	private JPanel Add_Data_GUI_contentPane;
	private JTextField Stock_ID_textField;
	private JTextField Stock_Commodity_textField;
	private JTextField Stock_Number_textField;
	private JRadioButton Stock_ID_RadioButton;
	private JRadioButton Stock_Commodity_RadioButton;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Add_Data_GUI frame = new Add_Data_GUI();
					frame.setLocationRelativeTo(null);//居中显示窗体
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public Add_Data_GUI() {
		setResizable(false);
		this.setModal(true);//设置为模态窗体
		
		
		//窗体
		setTitle("\u8FDB\u8D27");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 472, 338);
		Add_Data_GUI_contentPane = new JPanel();
		Add_Data_GUI_contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(Add_Data_GUI_contentPane);
		Add_Data_GUI_contentPane.setLayout(null);
		this.setLocationRelativeTo(null);
		//货号单选按钮
		Stock_ID_RadioButton = new JRadioButton("\u8D27\u53F7\uFF1A");
		Stock_ID_RadioButton.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		Stock_ID_RadioButton.setSelected(true);
		buttonGroup.add(Stock_ID_RadioButton);
		Stock_ID_RadioButton.setBounds(43, 102, 121, 23);
		Add_Data_GUI_contentPane.add(Stock_ID_RadioButton);
		//货号文本框
		Stock_ID_textField = new JTextField();
		Stock_ID_textField.setBounds(170, 103, 157, 21);
		Add_Data_GUI_contentPane.add(Stock_ID_textField);
		Stock_ID_textField.setColumns(10);
		//名称单选按钮
		Stock_Commodity_RadioButton = new JRadioButton("\u540D\u79F0\uFF1A");
		Stock_Commodity_RadioButton.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		buttonGroup.add(Stock_Commodity_RadioButton);
		Stock_Commodity_RadioButton.setBounds(43, 139, 121, 23);
		Add_Data_GUI_contentPane.add(Stock_Commodity_RadioButton);
		//名称文本框
		Stock_Commodity_textField = new JTextField();
		Stock_Commodity_textField.setEditable(false);
		Stock_Commodity_textField.setBounds(170, 140, 157, 21);
		Add_Data_GUI_contentPane.add(Stock_Commodity_textField);
		Stock_Commodity_textField.setColumns(10);
		//数量标签
		JLabel Stock_Number_Label = new JLabel("\u672C\u6B21\u8FDB\u8D27\u6570\u91CF\uFF1A");
		Stock_Number_Label.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		Stock_Number_Label.setBounds(10, 206, 98, 15);
		Add_Data_GUI_contentPane.add(Stock_Number_Label);
		//数量文本框
		Stock_Number_textField = new JTextField();
		Stock_Number_textField.setBounds(170, 203, 66, 21);
		Add_Data_GUI_contentPane.add(Stock_Number_textField);
		Stock_Number_textField.setColumns(10);
		//确定按钮
		JButton Stock_ButtonOK = new JButton("\u786E\u5B9A");
		Stock_ButtonOK.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		Stock_ButtonOK.setBounds(318, 225, 93, 23);
		Add_Data_GUI_contentPane.add(Stock_ButtonOK);
		//Tip:
		JLabel Tip1_Label = new JLabel("TIP\uFF1A\u201C\u8FDB\u8D27\u201D\u4EC5\u80FD\u6DFB\u52A0\u5E93\u5B58\u5546\u54C1\u7684\u6570\u91CF\uFF0C\u82E5\u8981\u6DFB\u52A0\u65B0\u7684\u5546\u54C1\uFF0C");
		Tip1_Label.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		Tip1_Label.setBounds(55, 10, 356, 15);
		Add_Data_GUI_contentPane.add(Tip1_Label);
		
		JLabel Tip2_Label = new JLabel("\u8BF7\u4F7F\u7528\u201C\u6DFB\u52A0\u65B0\u5546\u54C1\u201D\u3002");
		Tip2_Label.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		Tip2_Label.setBounds(55, 36, 157, 15);
		Add_Data_GUI_contentPane.add(Tip2_Label);
		
		//注册监听器
		Stock_ID_RadioButton.addItemListener(this);
		Stock_Commodity_RadioButton.addItemListener(this);
		Stock_ButtonOK.addActionListener(this);
	}

	//单选按钮事件
	public void itemStateChanged(ItemEvent arg0) {
		if(Stock_ID_RadioButton.isSelected()){
			Stock_Commodity_textField.setEditable(false);
			Stock_Commodity_textField.setText(null);
			Stock_ID_textField.setEditable(true);
		}
		if(Stock_Commodity_RadioButton.isSelected()){
			Stock_ID_textField.setEditable(false);
			Stock_ID_textField.setText(null);
			Stock_Commodity_textField.setEditable(true);
		}
	}

	//确定按钮事件
	public void actionPerformed(ActionEvent arg0) {
		Toolkit.getDefaultToolkit().beep();
		try {
			//定义相关变量
			Connection con = LinkDataBase.Link(null);
			String sql_Find,sql_Add;//定义SQL语句
			int Count;//查询结果计数
			int result;
			ResultSet rs;//获取Number字段内容
			int int_old_Number = 0;//用于存储旧的Number值
			int int_Stock_Number_textField = 0;//用于存储输入的Number值
			Find_Data co = new Find_Data();//创建Find对象
			
			//选择以ID号录入物品数量
			if(Stock_ID_RadioButton.isSelected()){
				sql_Find = "select * from Stock where ID="+Stock_ID_textField.getText();
				Statement s = con.createStatement();// 创建SQL语句对象  
				Count = co.Count(sql_Find);//获取字段计数
	            	if (Count > 0){
	            		//获取Number字段的初始值
	            		String priceType = null;
	            		rs = s.executeQuery(sql_Find);
	            		if(rs.next()) {
	            		   priceType = rs.getString("Number");
	            		   int_old_Number = Integer.parseInt(priceType); 
	            		}
	            		if(Stock_Number_textField.getText().length() == 0 )
	            			JOptionPane.showMessageDialog(null, "请输入商品数量！", "进货",JOptionPane.ERROR_MESSAGE);
	            		int_Stock_Number_textField = Integer.parseInt(Stock_Number_textField.getText());
	            		int NewNumber = int_Stock_Number_textField+int_old_Number;
	            		sql_Add = "update Stock set Number="+NewNumber+" where ID="+Stock_ID_textField.getText();
	            		//执行SQL语句，更新数据
	            		result=s.executeUpdate(sql_Add);  
	            		if (result>0) {  
	            			System.out.println("-------插入成功-------");
	            			JOptionPane.showMessageDialog(null, "商品数量更新成功！", "进货",JOptionPane.INFORMATION_MESSAGE);
	            			Count = co.Count(sql_Find);
	            		}  
	            	} 
	            	else {
	            		System.out.println("ID号不存在！");
	            		JOptionPane.showMessageDialog(null, "货号不存在！", "进货",JOptionPane.ERROR_MESSAGE);
	            	}
	            	Stock_ID_textField.setText(null);
	            	Stock_Number_textField.setText(null);
	         }
		
			//选择以物品名录入物品数量
			if(Stock_Commodity_RadioButton.isSelected()){
				sql_Find = "select * from Stock where Commodity='"+Stock_Commodity_textField.getText()+"'";
				Statement s = con.createStatement();// 创建SQL语句对象  
				Count = co.Count(sql_Find);//获取字段计数
	            	if (Count > 0){
	            		//获取Number字段的初始值
	            		String priceType = null;
	            		rs = s.executeQuery(sql_Find);
	            		if(rs.next()) {
	            		   priceType = rs.getString("Number");
	            		   int_old_Number = Integer.parseInt(priceType); 
	            		}
	            		if(Stock_Number_textField.getText().length() == 0 )
            				JOptionPane.showMessageDialog(null, "请输入商品数量！", "进货",JOptionPane.ERROR_MESSAGE);
	            		int_Stock_Number_textField = Integer.parseInt(Stock_Number_textField.getText());
	            		int NewNumber = int_Stock_Number_textField+int_old_Number;
	            		sql_Add = "update Stock set Number="+NewNumber+" where Commodity='"+Stock_Commodity_textField.getText()+"'";
	            		//执行SQL语句，更新数据
	            		result=s.executeUpdate(sql_Add);  
	            		if (result>0) {  
	            			System.out.println("-------插入成功-------");
	            			JOptionPane.showMessageDialog(null, "商品数量更新成功！", "进货",JOptionPane.INFORMATION_MESSAGE);
	            			Count = co.Count(sql_Find);
	            		}  
	            	} 
	            	else {
	            		System.out.println("商品名不存在！");
	            		JOptionPane.showMessageDialog(null, "此商品不存在！", "进货",JOptionPane.ERROR_MESSAGE);
	            	}
	            	Stock_Commodity_textField.setText(null);
	            	Stock_Number_textField.setText(null);
			}
		}
		catch (Exception e) {  
	            e.printStackTrace();  
	    } 
	}
}
