import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.Font;


public class New_Data_GUI extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel New_Data_GUI_contentPane;
	private JTextField Stock_ID_textField;
	private JTextField Stock_Commodity_textField;
	private JTextField Stock_price_textField;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					New_Data_GUI frame = new New_Data_GUI();
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
	public New_Data_GUI() {
		setResizable(false);
		this.setModal(true);//设置为模态窗体
		
		//窗体
		setTitle("\u6DFB\u52A0\u65B0\u5546\u54C1");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 498, 327);
		New_Data_GUI_contentPane = new JPanel();
		New_Data_GUI_contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(New_Data_GUI_contentPane);
		New_Data_GUI_contentPane.setLayout(null);
		this.setLocationRelativeTo(null);
		
		JLabel Stock_ID_Label = new JLabel("\u8D27\u53F7\uFF1A");
		Stock_ID_Label.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		Stock_ID_Label.setBounds(45, 112, 54, 15);
		New_Data_GUI_contentPane.add(Stock_ID_Label);
		
		Stock_ID_textField = new JTextField();
		Stock_ID_textField.setBounds(143, 109, 109, 21);
		New_Data_GUI_contentPane.add(Stock_ID_textField);
		Stock_ID_textField.setColumns(10);
		
		JLabel Stock_Commodity_Label = new JLabel("\u540D\u79F0\uFF1A");
		Stock_Commodity_Label.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		Stock_Commodity_Label.setBounds(45, 137, 54, 15);
		New_Data_GUI_contentPane.add(Stock_Commodity_Label);
		
		Stock_Commodity_textField = new JTextField();
		Stock_Commodity_textField.setBounds(143, 134, 109, 21);
		New_Data_GUI_contentPane.add(Stock_Commodity_textField);
		Stock_Commodity_textField.setColumns(10);
		
		JLabel Stock_price_Label = new JLabel("\u9500\u552E\u5355\u4EF7\uFF1A");
		Stock_price_Label.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		Stock_price_Label.setBounds(32, 162, 66, 15);
		New_Data_GUI_contentPane.add(Stock_price_Label);
		
		Stock_price_textField = new JTextField();
		Stock_price_textField.setBounds(143, 159, 66, 21);
		New_Data_GUI_contentPane.add(Stock_price_textField);
		Stock_price_textField.setColumns(10);
		
		JLabel Tip1_Label = new JLabel("TIP\uFF1A\u201C\u6DFB\u52A0\u65B0\u5546\u54C1\u201D\u4EC5\u80FD\u6DFB\u52A0\u5E93\u5B58\u4E2D\u6CA1\u6709\u7684\u65B0\u5546\u54C1\uFF0C\u82E5\u8981\u8C03\u6574\u5E93\u5B58\u6570\u91CF\uFF0C");
		Tip1_Label.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		Tip1_Label.setBounds(32, 10, 428, 15);
		New_Data_GUI_contentPane.add(Tip1_Label);
		
		JLabel lblNewLabel = new JLabel("\u8BF7\u4F7F\u7528\u201C\u8FDB\u8D27\u201D\u3002");
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		lblNewLabel.setBounds(32, 35, 126, 15);
		New_Data_GUI_contentPane.add(lblNewLabel);
		
		JButton Stock_ButtonOK = new JButton("\u786E\u5B9A");
		Stock_ButtonOK.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		Stock_ButtonOK.setBounds(196, 237, 93, 23);
		New_Data_GUI_contentPane.add(Stock_ButtonOK);
		
		//注册监听器
		Stock_ButtonOK.addActionListener(this);

	}

	//确定按钮事件
	public void actionPerformed(ActionEvent arg0) {
		Toolkit.getDefaultToolkit().beep();
		RE:
		try {
			//定义相关变量
			Connection con = LinkDataBase.Link(null);
			String sql_Text_repeat_ID,sql_Text_repeat_Commodity,sql_Text_price;//定义SQL语句
			int Count_ID,Count_Commodity;//查询结果计数
			int result;
			Find_Data co = new Find_Data();//创建Find对象
			Statement s = con.createStatement();// 创建SQL语句对象
		
			sql_Text_repeat_ID = "select * from Stock where ID="+Stock_ID_textField.getText();
			sql_Text_repeat_Commodity = "select * from Stock where Commodity='"+Stock_Commodity_textField.getText()+"'";
			sql_Text_price = "insert into Stock(ID,Commodity,price) values("+Stock_ID_textField.getText()+",'"+Stock_Commodity_textField.getText()+"',"+Stock_price_textField.getText()+")";
			Count_Commodity = co.Count(sql_Text_repeat_Commodity);//获取Commodity字段计数
			Count_ID = co.Count(sql_Text_repeat_ID);//获取ID字段计数
			
			//合法性判断
			if(Stock_ID_textField.getText().length() == 0){
				JOptionPane.showMessageDialog(null, "货号不能为空！", "添加新商品",JOptionPane.ERROR_MESSAGE);
				break RE;
			}
			if(Stock_Commodity_textField.getText().length() == 0 ){
				JOptionPane.showMessageDialog(null, "商品名不能为空！", "添加新商品",JOptionPane.ERROR_MESSAGE);
				break RE;
			}
			if (Count_Commodity != 0){
				JOptionPane.showMessageDialog(null, "此商品名已存在，不能重复录入！", "添加新商品",JOptionPane.ERROR_MESSAGE);
				Stock_Commodity_textField.setText(null); 
				break RE;
			}
			if (Count_ID != 0){
				JOptionPane.showMessageDialog(null, "此货号已存在，不能重复录入！", "添加新商品",JOptionPane.ERROR_MESSAGE);
				Stock_ID_textField.setText(null); 
				break RE;
			}
			if(Stock_price_textField.getText().length() == 0){
				JOptionPane.showMessageDialog(null, "单价不能为空！", "添加新商品",JOptionPane.ERROR_MESSAGE);
				break RE;
			}
			//执行SQL语句，添加新商品
			result=s.executeUpdate(sql_Text_price);  
    		if (result>0) {  
    			System.out.println("-------新添加商品成功-------");
    			JOptionPane.showMessageDialog(null, "商品列表更新成功！", "添加新商品",JOptionPane.INFORMATION_MESSAGE);
    			Stock_ID_textField.setText(null);
    			Stock_Commodity_textField.setText(null);
    			Stock_price_textField.setText(null);
    		} 
		}
		catch (Exception e) {  
	            e.printStackTrace();  
	    } 	
	}
}
