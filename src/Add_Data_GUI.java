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
					frame.setLocationRelativeTo(null);//������ʾ����
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
		this.setModal(true);//����Ϊģ̬����
		
		
		//����
		setTitle("\u8FDB\u8D27");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 472, 338);
		Add_Data_GUI_contentPane = new JPanel();
		Add_Data_GUI_contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(Add_Data_GUI_contentPane);
		Add_Data_GUI_contentPane.setLayout(null);
		this.setLocationRelativeTo(null);
		//���ŵ�ѡ��ť
		Stock_ID_RadioButton = new JRadioButton("\u8D27\u53F7\uFF1A");
		Stock_ID_RadioButton.setFont(new Font("΢���ź�", Font.PLAIN, 12));
		Stock_ID_RadioButton.setSelected(true);
		buttonGroup.add(Stock_ID_RadioButton);
		Stock_ID_RadioButton.setBounds(43, 102, 121, 23);
		Add_Data_GUI_contentPane.add(Stock_ID_RadioButton);
		//�����ı���
		Stock_ID_textField = new JTextField();
		Stock_ID_textField.setBounds(170, 103, 157, 21);
		Add_Data_GUI_contentPane.add(Stock_ID_textField);
		Stock_ID_textField.setColumns(10);
		//���Ƶ�ѡ��ť
		Stock_Commodity_RadioButton = new JRadioButton("\u540D\u79F0\uFF1A");
		Stock_Commodity_RadioButton.setFont(new Font("΢���ź�", Font.PLAIN, 12));
		buttonGroup.add(Stock_Commodity_RadioButton);
		Stock_Commodity_RadioButton.setBounds(43, 139, 121, 23);
		Add_Data_GUI_contentPane.add(Stock_Commodity_RadioButton);
		//�����ı���
		Stock_Commodity_textField = new JTextField();
		Stock_Commodity_textField.setEditable(false);
		Stock_Commodity_textField.setBounds(170, 140, 157, 21);
		Add_Data_GUI_contentPane.add(Stock_Commodity_textField);
		Stock_Commodity_textField.setColumns(10);
		//������ǩ
		JLabel Stock_Number_Label = new JLabel("\u672C\u6B21\u8FDB\u8D27\u6570\u91CF\uFF1A");
		Stock_Number_Label.setFont(new Font("΢���ź�", Font.PLAIN, 12));
		Stock_Number_Label.setBounds(10, 206, 98, 15);
		Add_Data_GUI_contentPane.add(Stock_Number_Label);
		//�����ı���
		Stock_Number_textField = new JTextField();
		Stock_Number_textField.setBounds(170, 203, 66, 21);
		Add_Data_GUI_contentPane.add(Stock_Number_textField);
		Stock_Number_textField.setColumns(10);
		//ȷ����ť
		JButton Stock_ButtonOK = new JButton("\u786E\u5B9A");
		Stock_ButtonOK.setFont(new Font("΢���ź�", Font.PLAIN, 12));
		Stock_ButtonOK.setBounds(318, 225, 93, 23);
		Add_Data_GUI_contentPane.add(Stock_ButtonOK);
		//Tip:
		JLabel Tip1_Label = new JLabel("TIP\uFF1A\u201C\u8FDB\u8D27\u201D\u4EC5\u80FD\u6DFB\u52A0\u5E93\u5B58\u5546\u54C1\u7684\u6570\u91CF\uFF0C\u82E5\u8981\u6DFB\u52A0\u65B0\u7684\u5546\u54C1\uFF0C");
		Tip1_Label.setFont(new Font("΢���ź�", Font.PLAIN, 12));
		Tip1_Label.setBounds(55, 10, 356, 15);
		Add_Data_GUI_contentPane.add(Tip1_Label);
		
		JLabel Tip2_Label = new JLabel("\u8BF7\u4F7F\u7528\u201C\u6DFB\u52A0\u65B0\u5546\u54C1\u201D\u3002");
		Tip2_Label.setFont(new Font("΢���ź�", Font.PLAIN, 12));
		Tip2_Label.setBounds(55, 36, 157, 15);
		Add_Data_GUI_contentPane.add(Tip2_Label);
		
		//ע�������
		Stock_ID_RadioButton.addItemListener(this);
		Stock_Commodity_RadioButton.addItemListener(this);
		Stock_ButtonOK.addActionListener(this);
	}

	//��ѡ��ť�¼�
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

	//ȷ����ť�¼�
	public void actionPerformed(ActionEvent arg0) {
		Toolkit.getDefaultToolkit().beep();
		try {
			//������ر���
			Connection con = LinkDataBase.Link(null);
			String sql_Find,sql_Add;//����SQL���
			int Count;//��ѯ�������
			int result;
			ResultSet rs;//��ȡNumber�ֶ�����
			int int_old_Number = 0;//���ڴ洢�ɵ�Numberֵ
			int int_Stock_Number_textField = 0;//���ڴ洢�����Numberֵ
			Find_Data co = new Find_Data();//����Find����
			
			//ѡ����ID��¼����Ʒ����
			if(Stock_ID_RadioButton.isSelected()){
				sql_Find = "select * from Stock where ID="+Stock_ID_textField.getText();
				Statement s = con.createStatement();// ����SQL������  
				Count = co.Count(sql_Find);//��ȡ�ֶμ���
	            	if (Count > 0){
	            		//��ȡNumber�ֶεĳ�ʼֵ
	            		String priceType = null;
	            		rs = s.executeQuery(sql_Find);
	            		if(rs.next()) {
	            		   priceType = rs.getString("Number");
	            		   int_old_Number = Integer.parseInt(priceType); 
	            		}
	            		if(Stock_Number_textField.getText().length() == 0 )
	            			JOptionPane.showMessageDialog(null, "��������Ʒ������", "����",JOptionPane.ERROR_MESSAGE);
	            		int_Stock_Number_textField = Integer.parseInt(Stock_Number_textField.getText());
	            		int NewNumber = int_Stock_Number_textField+int_old_Number;
	            		sql_Add = "update Stock set Number="+NewNumber+" where ID="+Stock_ID_textField.getText();
	            		//ִ��SQL��䣬��������
	            		result=s.executeUpdate(sql_Add);  
	            		if (result>0) {  
	            			System.out.println("-------����ɹ�-------");
	            			JOptionPane.showMessageDialog(null, "��Ʒ�������³ɹ���", "����",JOptionPane.INFORMATION_MESSAGE);
	            			Count = co.Count(sql_Find);
	            		}  
	            	} 
	            	else {
	            		System.out.println("ID�Ų����ڣ�");
	            		JOptionPane.showMessageDialog(null, "���Ų����ڣ�", "����",JOptionPane.ERROR_MESSAGE);
	            	}
	            	Stock_ID_textField.setText(null);
	            	Stock_Number_textField.setText(null);
	         }
		
			//ѡ������Ʒ��¼����Ʒ����
			if(Stock_Commodity_RadioButton.isSelected()){
				sql_Find = "select * from Stock where Commodity='"+Stock_Commodity_textField.getText()+"'";
				Statement s = con.createStatement();// ����SQL������  
				Count = co.Count(sql_Find);//��ȡ�ֶμ���
	            	if (Count > 0){
	            		//��ȡNumber�ֶεĳ�ʼֵ
	            		String priceType = null;
	            		rs = s.executeQuery(sql_Find);
	            		if(rs.next()) {
	            		   priceType = rs.getString("Number");
	            		   int_old_Number = Integer.parseInt(priceType); 
	            		}
	            		if(Stock_Number_textField.getText().length() == 0 )
            				JOptionPane.showMessageDialog(null, "��������Ʒ������", "����",JOptionPane.ERROR_MESSAGE);
	            		int_Stock_Number_textField = Integer.parseInt(Stock_Number_textField.getText());
	            		int NewNumber = int_Stock_Number_textField+int_old_Number;
	            		sql_Add = "update Stock set Number="+NewNumber+" where Commodity='"+Stock_Commodity_textField.getText()+"'";
	            		//ִ��SQL��䣬��������
	            		result=s.executeUpdate(sql_Add);  
	            		if (result>0) {  
	            			System.out.println("-------����ɹ�-------");
	            			JOptionPane.showMessageDialog(null, "��Ʒ�������³ɹ���", "����",JOptionPane.INFORMATION_MESSAGE);
	            			Count = co.Count(sql_Find);
	            		}  
	            	} 
	            	else {
	            		System.out.println("��Ʒ�������ڣ�");
	            		JOptionPane.showMessageDialog(null, "����Ʒ�����ڣ�", "����",JOptionPane.ERROR_MESSAGE);
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
