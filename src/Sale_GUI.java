import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ButtonGroup;
import javax.swing.JScrollPane;

import java.awt.Font;


@SuppressWarnings("serial")
public class Sale_GUI extends JDialog implements ItemListener,ActionListener{

	private JPanel Sale_GUI_contentPane;
	private JRadioButton Stock_ID_RadioButton;
	private JRadioButton Stock_Commodity_RadioButton;
	private JButton Stock_ButtonClear;
	private JTextField Stock_ID_textField;
	private JTextField Stock_Commodity_textField;
	private JTextField Stock_Number_textField;
	private JButton Stock_ButtonOK;
	private JButton Stock_ButtonSALE;
	private JLabel Stock_Number_Label;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTable Stock_Sale_Table;
	
	private int i = 0;
	private int sum_price = 0;//记录总价
	public int Final_Num;
	public int Old_Num[] = new int[50];//保存Stock表里的Number字段值
	public int Buy_Num[] = new int[50];//保存BuyList表里的Buy_Number字段值
	public int Old_ID[] = new int[50];//保存改变库存的商品的ID号
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args){
		
		Sale_GUI frame = new Sale_GUI();
		frame.setLocationRelativeTo(null);//居中显示窗体
		frame.setVisible(true);
				
	}

	/**
	 * Create the frame.
	 */
	public Sale_GUI() {
		setResizable(false);
		this.setModal(true);//设置为模态窗体
		//窗体
		setTitle("\u9500\u552E");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 579, 354);
		Sale_GUI_contentPane = new JPanel();
		Sale_GUI_contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(Sale_GUI_contentPane);
		Sale_GUI_contentPane.setLayout(null);
		this.setLocationRelativeTo(null);
		//货号单选按钮
		Stock_ID_RadioButton = new JRadioButton("\u8D27\u53F7\uFF1A");
		Stock_ID_RadioButton.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		buttonGroup.add(Stock_ID_RadioButton);
		Stock_ID_RadioButton.setSelected(true);
		Stock_ID_RadioButton.setBounds(35, 28, 121, 23);
		Sale_GUI_contentPane.add(Stock_ID_RadioButton);
		//货号文本框
		Stock_ID_textField = new JTextField();
		Stock_ID_textField.setBounds(214, 29, 66, 21);
		Sale_GUI_contentPane.add(Stock_ID_textField);
		Stock_ID_textField.setColumns(10);
		//名称单选按钮
		Stock_Commodity_RadioButton = new JRadioButton("\u540D\u79F0\uFF1A");
		Stock_Commodity_RadioButton.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		buttonGroup.add(Stock_Commodity_RadioButton);
		Stock_Commodity_RadioButton.setBounds(35, 75, 121, 23);
		Sale_GUI_contentPane.add(Stock_Commodity_RadioButton);
		//名称文本框
		Stock_Commodity_textField = new JTextField();
		Stock_Commodity_textField.setEditable(false);
		Stock_Commodity_textField.setBounds(214, 76, 66, 21);
		Sale_GUI_contentPane.add(Stock_Commodity_textField);
		Stock_Commodity_textField.setColumns(10);
		//数量标签
		Stock_Number_Label = new JLabel("\u6570\u91CF\uFF1A");
		Stock_Number_Label.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		Stock_Number_Label.setBounds(35, 125, 54, 15);
		Sale_GUI_contentPane.add(Stock_Number_Label);
		//数量文本框
		Stock_Number_textField = new JTextField();
		Stock_Number_textField.setBounds(214, 122, 66, 21);
		Sale_GUI_contentPane.add(Stock_Number_textField);
		Stock_Number_textField.setColumns(10);
		//确定按钮
		Stock_ButtonOK = new JButton("\u6DFB\u52A0");
		Stock_ButtonOK.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		Stock_ButtonOK.setBounds(343, 121, 93, 23);
		Sale_GUI_contentPane.add(Stock_ButtonOK);
		//结账按钮
		Stock_ButtonSALE = new JButton("\u7ED3\u8D26");
		Stock_ButtonSALE.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		Stock_ButtonSALE.setBounds(415, 273, 93, 23);
		Sale_GUI_contentPane.add(Stock_ButtonSALE);
		//清空按钮
		Stock_ButtonClear = new JButton("\u6E05\u7A7A");
		Stock_ButtonClear.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		Stock_ButtonClear.setBounds(343, 53, 93, 23);
		Sale_GUI_contentPane.add(Stock_ButtonClear);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 153, 390, 162);
		Sale_GUI_contentPane.add(scrollPane);
		
		Stock_Sale_Table = new JTable();
		Stock_Sale_Table.setEnabled(false);
		scrollPane.setColumnHeaderView(Stock_Sale_Table);
		
		//注册监听器
		Stock_ID_RadioButton.addItemListener(this);
		Stock_Commodity_RadioButton.addItemListener(this);
		Stock_ButtonOK.addActionListener(this);
		Stock_ButtonSALE.addActionListener(this);
		Stock_ButtonClear.addActionListener(this);
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

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void actionPerformed(ActionEvent e) {
		Toolkit.getDefaultToolkit().beep();
		RE:
		try {
			//定义相关变量
			Connection con = LinkDataBase.Link(null);
			String sql_Find,sql_Add,sql_Del,sql_Del_2,sql_Update;//定义SQL语句
			
			ResultSet result;
			ResultSet rs;//获取Number字段内容
			Find_Data co = new Find_Data();//创建Find对象
			
		//清空按钮事件
		if (e.getSource() == Stock_ButtonClear ){
			sql_Find = "select * from BuyList";
			sql_Del = "delete from BuyList";
			sql_Del_2 = "delete from BuyList2";
			//清空列表
			Delete_Data.Delete(sql_Del);
			Delete_Data.Delete(sql_Del_2);
			Statement s = con.createStatement();// 创建SQL语句对象  
			result = s.executeQuery(sql_Find);
			Vector values =new Vector();
	    	while (result.next()) { 
	    		Vector temp=new Vector();
	    		temp.add(result.getString("ID"));
	    		temp.add(result.getString("Commodity"));
	    		temp.add(result.getString("Buy_Number"));
	    		//temp.add(result.getString("price"));
	    		values.add(temp);
	    	}
	    	Vector item=new Vector();
	    	item.add("货号");
	    	item.add("名称");
	    	item.add("数量");
	    	//item.add("price");
	    	DefaultTableModel model=new DefaultTableModel(values,item);
	    	Stock_Sale_Table.setModel(model);
	    	//清空临时数组
	    	Stock_Commodity_textField.setText(null);
	    	Stock_ID_textField.setText(null);
	    	Stock_Number_textField.setText(null);
			Arrays.fill(Old_Num, -1);
			Arrays.fill(Buy_Num, -1);
			Arrays.fill(Old_ID, -1);
			i = 0;
			sum_price = 0;
		}
		
		//确定按钮事件
		if ( e.getSource() == Stock_ButtonOK ){
			
//-----------------------------------------------选择以ID号录入物品数量-----------------------------------------
			if(Stock_ID_RadioButton.isSelected()){
				String sql_Text_repeat_Buy_ID = "select * from BuyList where ID="+Stock_ID_textField.getText();
				//获取库存数量的数值
				String sql_get_Buy_ID = "select * from Stock where ID='"+Stock_ID_textField.getText()+"'";
				if(Stock_Number_textField.getText().length() == 0)
					JOptionPane.showMessageDialog(null, "购买数量不能为空！", "销售",JOptionPane.ERROR_MESSAGE);
				int Count = co.Count(sql_get_Buy_ID);//保存Stock ID字段计数
				//判断ID号录入是否重复
				int Count_Buy_ID= co.Count(sql_Text_repeat_Buy_ID);//获取BuyList ID字段计数
				if (Count_Buy_ID != 0){
					JOptionPane.showMessageDialog(null, "此货号已存在清单内，不能重复录入！", "销售",JOptionPane.ERROR_MESSAGE);
					Stock_ID_textField.setText(null); 
					break RE;
				}
				//判断是否存在ID号
				if (Count > 0){
					Statement s = con.createStatement();
            		rs = s.executeQuery(sql_get_Buy_ID);
            		if(rs.next()) {
            			int temp_Stock_Num = Integer.parseInt(rs.getString("Number"));//获取	Stock表中Number字段的值
            			int price = Integer.parseInt(rs.getString("price"));
            			int temp_BuyList_Num = Integer.parseInt(Stock_Number_textField.getText());//获取输入数量Buy_Num
            			//判断库存与购买量合法性
            			if( temp_Stock_Num >= temp_BuyList_Num ){
            				Old_Num[i] = temp_Stock_Num;
            				Buy_Num[i] = temp_BuyList_Num;
            				sum_price += Buy_Num[i] * price ;//计算总价
            				//获取此时的货号
            				Old_ID[i] = Integer.parseInt(Stock_ID_textField.getText());
            				//System.out.println("获取到的Old_Num[i]值为："+Old_Num[i]);
            				//System.out.println("获取到的Buy_Num[i]值为："+Buy_Num[i]);
            				//System.out.println("现在改变的ID为："+Old_ID[i]);
            				i++;
            			}
            			else{
            				JOptionPane.showMessageDialog(null, "该商品库存不足！", "销售",JOptionPane.ERROR_MESSAGE);
            				Stock_Number_textField.setText(null);
            				break RE;
            			}
            		}
				}
				else{
					JOptionPane.showMessageDialog(null, "没有找到对应的货号！", "销售",JOptionPane.ERROR_MESSAGE);
					Stock_ID_textField.setText(null);
					break RE;
				}
				sql_Add = "INSERT INTO BuyList(ID,Buy_Number) VALUES ("+Stock_ID_textField.getText()+","+Integer.parseInt(Stock_Number_textField.getText())+")";
				sql_Find = "select * from BuyList";
				Add_Data.Add(sql_Add);
				Statement s = con.createStatement();// 创建SQL语句对象  
				result = s.executeQuery(sql_Find);
				Vector values =new Vector();
		    	while (result.next()) { 
		    		Vector temp=new Vector();
		    		temp.add(result.getString("ID"));
		    		//temp.add(result.getString("Commodity"));
		    		temp.add(result.getString("Buy_Number"));
		    		values.add(temp);
		    	}
		    	Vector item=new Vector();
		    	item.add("货号");
		    	//item.add("名称");
		    	item.add("数量");
		    	DefaultTableModel model=new DefaultTableModel(values,item);
		    	Stock_Sale_Table.setModel(model);
			}		
			
//----------------------------------选择以物品名录入物品数量-----------------------------------------
			if(Stock_Commodity_RadioButton.isSelected()){
				String sql_Text_repeat_Buy_Commodity = "select * from BuyList where Commodity="+Stock_Commodity_textField.getText();
				//获取库存数量的数值
				String sql_get_Buy_Commodity = "select * from Stock where Commodity='"+Stock_Commodity_textField.getText()+"'";
				if(Stock_Number_textField.getText().length() == 0)
					JOptionPane.showMessageDialog(null, "购买数量不能为空！", "销售",JOptionPane.ERROR_MESSAGE);
				int Count = co.Count(sql_get_Buy_Commodity);//保存Stock ID字段计数
				//判断ID号录入是否重复
				int Count_Buy_Commodity= co.Count(sql_Text_repeat_Buy_Commodity);//获取BuyList ID字段计数
				if (Count_Buy_Commodity != 0){
					JOptionPane.showMessageDialog(null, "此货号已存在清单内，不能重复录入！", "销售",JOptionPane.ERROR_MESSAGE);
					Stock_ID_textField.setText(null); 
					break RE;
				}
				//判断是否存在ID号
				if (Count > 0){
					Statement s = con.createStatement();
					rs = s.executeQuery(sql_get_Buy_Commodity);
					if(rs.next()) {
						int temp_Stock_Num = Integer.parseInt(rs.getString("Number"));//获取	Stock表中Number字段的值
						//System.out.println("获取	Stock表中Number字段的值成功");
						int Convert_Stock_ID = Integer.parseInt(rs.getString("ID"));
						//System.out.println("获取	Stock表中ID字段的值成功");
						int price = Integer.parseInt(rs.getString("price"));
						//System.out.println("获取	Stock表中price字段的值成功");
						int temp_BuyList_Num = Integer.parseInt(Stock_Number_textField.getText());//获取输入数量Buy_Num
						//判断库存与购买量合法性
						if( temp_Stock_Num >= temp_BuyList_Num ){
							Old_Num[i] = temp_Stock_Num;
							Buy_Num[i] = temp_BuyList_Num;
							sum_price += Buy_Num[i] * price ;//计算总价
							//获取此时的货号
							Old_ID[i] = Convert_Stock_ID;
							//System.out.println("获取到的Old_Num[i]值为："+Old_Num[i]);
							//System.out.println("获取到的Buy_Num[i]值为："+Buy_Num[i]);
							//System.out.println("现在改变的ID为："+Old_ID[i]);
							i++;
						}
						else{
							JOptionPane.showMessageDialog(null, "该商品库存不足！", "销售",JOptionPane.ERROR_MESSAGE);
							Stock_Number_textField.setText(null);
							break RE;
        			}
        		}
			}
				else{
					JOptionPane.showMessageDialog(null, "没有找到对应的商品！", "销售",JOptionPane.ERROR_MESSAGE);
					Stock_Commodity_textField.setText(null);
					break RE;
					}
				sql_Add = "INSERT INTO BuyList2(Commodity,Buy_Number) VALUES ('"+Stock_Commodity_textField.getText()+"',"+Integer.parseInt(Stock_Number_textField.getText())+")";
				sql_Find = "select * from BuyList2";
				Add_Data.Add(sql_Add);
				System.out.println("插入数据成功！");
				Statement s = con.createStatement();// 创建SQL语句对象  
				result = s.executeQuery(sql_Find);
				Vector values =new Vector();
				while (result.next()) { 
					Vector temp=new Vector();
					temp.add(result.getString("Commodity"));
					//temp.add(result.getString("Commodity"));
					temp.add(result.getString("Buy_Number"));
					//temp.add(result.getString("price"));
					values.add(temp);
				}
				Vector item=new Vector();
				item.add("商品");
				//item.add("Commodity");
				item.add("数量");
				//item.add("price");
				DefaultTableModel model=new DefaultTableModel(values,item);
				Stock_Sale_Table.setModel(model);
			}
		}		

		//结账按钮事件 
			if (e.getSource() == Stock_ButtonSALE ){
				int Count;
				if(Stock_ID_RadioButton.isSelected())
					Count = co.Count("select * from BuyList");
				else  Count = co.Count("select * from BuyList2");
				System.out.println("Count计数："+Count);
				for( int j = 0; j < Count ; j++ ){
					Final_Num = Old_Num[j]-Buy_Num[j];
					sql_Update = "update Stock set Number="+Final_Num+" where ID='"+Old_ID[j]+"'";
					Update_Data.Update(sql_Update);
				}
				JOptionPane.showMessageDialog(null, "结账成功！总金额："+sum_price, "销售",JOptionPane.INFORMATION_MESSAGE);
			}
		}	catch (Exception e1) {  
			e1.printStackTrace(); 
			}		
	}
}