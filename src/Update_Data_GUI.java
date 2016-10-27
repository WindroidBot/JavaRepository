import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ButtonGroup;

import java.awt.Font;


public class Update_Data_GUI extends JDialog implements ItemListener,ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3645351241485376089L;
	private JPanel contentPane;
	private JTable Update_Table;
	private JTextField Update_ID_textField;
	private JTextField Update_Commodity_textField;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	
	private JRadioButton Update_ID_RadioButton = new JRadioButton();
	private JRadioButton Update_Commodity_RadioButton = new JRadioButton();
	private JButton Update_ButtonClear = new JButton();
	private JButton Update_ButtonOK = new JButton();
	private JButton Update_ButtonFind;
	
	String temp_Commodity = null,temp_Number = null,temp_price = null;
	int temp_ID = 0;
	int temp_update_ID;
	String temp_update_Commodity;
	
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Update_Data_GUI frame = new Update_Data_GUI();
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
	public Update_Data_GUI() {
		setResizable(false);
		this.setModal(true);//����Ϊģ̬����
		setTitle("\u7EFC\u5408\u7BA1\u7406");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setLocationRelativeTo(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(24, 118, 253, 114);
		contentPane.add(scrollPane);
		
		Update_Table = new JTable();
		scrollPane.setViewportView(Update_Table);
		
		Update_ID_RadioButton = new JRadioButton("\u8D27\u53F7\uFF1A");
		Update_ID_RadioButton.setFont(new Font("΢���ź�", Font.PLAIN, 12));
		buttonGroup.add(Update_ID_RadioButton);
		Update_ID_RadioButton.setSelected(true);
		Update_ID_RadioButton.setBounds(24, 20, 121, 23);
		contentPane.add(Update_ID_RadioButton);
		
		Update_ID_textField = new JTextField();
		Update_ID_textField.setBounds(151, 21, 66, 21);
		contentPane.add(Update_ID_textField);
		Update_ID_textField.setColumns(10);
		
		Update_Commodity_RadioButton = new JRadioButton("\u540D\u79F0\uFF1A");
		Update_Commodity_RadioButton.setFont(new Font("΢���ź�", Font.PLAIN, 12));
		buttonGroup.add(Update_Commodity_RadioButton);
		Update_Commodity_RadioButton.setBounds(24, 75, 121, 23);
		contentPane.add(Update_Commodity_RadioButton);
		
		Update_Commodity_textField = new JTextField();
		Update_Commodity_textField.setEditable(false);
		Update_Commodity_textField.setBounds(151, 76, 126, 21);
		contentPane.add(Update_Commodity_textField);
		Update_Commodity_textField.setColumns(10);
		
		Update_ButtonFind = new JButton("\u67E5\u8BE2");
		Update_ButtonFind.setFont(new Font("΢���ź�", Font.PLAIN, 12));
		Update_ButtonFind.setBounds(317, 75, 93, 23);
		contentPane.add(Update_ButtonFind);
		
		Update_ButtonClear = new JButton("\u653E\u5F03\u66F4\u6539");
		Update_ButtonClear.setFont(new Font("΢���ź�", Font.PLAIN, 12));
		Update_ButtonClear.setBounds(317, 144, 93, 23);
		contentPane.add(Update_ButtonClear);
		
		Update_ButtonOK = new JButton("\u786E\u8BA4\u66F4\u6539");
		Update_ButtonOK.setFont(new Font("΢���ź�", Font.PLAIN, 12));
		Update_ButtonOK.setBounds(317, 202, 93, 23);
		contentPane.add(Update_ButtonOK);
		
		//ע�������
		Update_ID_RadioButton.addItemListener(this);
		Update_Commodity_RadioButton.addItemListener(this);
		Update_ButtonOK.addActionListener(this);
		Update_ButtonClear.addActionListener(this);
		Update_ButtonFind.addActionListener(this);
	}

	//��ѡ��ť�¼�
	public void itemStateChanged(ItemEvent arg0) {
		if(Update_ID_RadioButton.isSelected()){
			Update_Commodity_textField.setEditable(false);
			Update_Commodity_textField.setText(null);
			Update_ID_textField.setEditable(true);
		}
		if(Update_Commodity_RadioButton.isSelected()){
			Update_ID_textField.setEditable(false);
			Update_ID_textField.setText(null);
			Update_Commodity_textField.setEditable(true);
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void actionPerformed(ActionEvent e) {
		Toolkit.getDefaultToolkit().beep();
		RU:
		RE:
		try {
			//������ر���
			
			Connection con = LinkDataBase.Link(null);
			Statement s = con.createStatement();
			String sql_Find;//����SQL���
			ResultSet rs;//��ȡNumber�ֶ�����
			Find_Data co = new Find_Data();//����Find����

			//��ѯ��ť�¼�
			if (e.getSource() == Update_ButtonFind ){
				//��ID�Ų�ѯ
				if(Update_ID_RadioButton.isSelected()){
					int a = Integer.parseInt(Update_ID_textField.getText());
					sql_Find = "select * from Stock where ID = '"+a+"'";
					//sql_Find = "select * from Stock";
					rs = s.executeQuery(sql_Find);
					System.out.println("����˲�ѯ");
					//��ȡ�����������ֵ
					int Count = co.Count(sql_Find);//����Stock ID�ֶμ���
					//�ж��Ƿ����ID��
					if (Count > 0){
						//������������б�
						Vector values =new Vector();
				    	while (rs.next()) { 
				    		Vector temp=new Vector();
				    		temp.add(rs.getInt("ID"));
				    		temp_ID = rs.getInt("ID");
				    		temp_update_ID = rs.getInt("ID");//����
				    		//System.out.println(temp_ID);
				    		temp.add(rs.getString("Commodity"));
				    		temp_Commodity = rs.getString("Commodity");
				    		temp_update_Commodity = rs.getString("Commodity");//����
				    		temp.add(rs.getString("Number"));
				    		temp_Number = rs.getString("Number");
				    		temp.add(rs.getString("price"));
				    		temp_price = rs.getString("price");
				    		values.add(temp);
				    	}
				    	Vector item=new Vector();
				    	item.add("����");
				    	item.add("����");
				    	item.add("����");
				    	item.add("����");
				    	DefaultTableModel model=new DefaultTableModel(values,item);
				    	Update_Table.setModel(model);
					}
					else{
						JOptionPane.showMessageDialog(null, "û���ҵ���Ӧ�Ļ��ţ�", "������",JOptionPane.ERROR_MESSAGE);
						Update_ID_textField.setText(null);
						break RE;
					}
				}
				//����Ʒ����ѯ
				if(Update_Commodity_RadioButton.isSelected()){
					String a = Update_Commodity_textField.getText();
					sql_Find = "select * from Stock where Commodity = '"+a+"'";
					//sql_Find = "select * from Stock";
					rs = s.executeQuery(sql_Find);
					System.out.println("����˲�ѯ");
					//��ȡ�����������ֵ
					int Count = co.Count(sql_Find);//����Stock ID�ֶμ���
					//�ж��Ƿ����ID��
					if (Count > 0){
						//������������б�
						Vector values =new Vector();
				    	while (rs.next()) { 
				    		Vector temp=new Vector();
				    		temp.add(rs.getInt("ID"));
				    		temp_ID = rs.getInt("ID");
				    		temp_update_ID = rs.getInt("ID");//����
				    		//System.out.println(temp_ID);
				    		temp.add(rs.getString("Commodity"));
				    		temp_Commodity = rs.getString("Commodity");
				    		temp_update_Commodity = rs.getString("Commodity");//����
				    		temp.add(rs.getString("Number"));
				    		temp_Number = rs.getString("Number");
				    		temp.add(rs.getString("price"));
				    		temp_price = rs.getString("price");
				    		values.add(temp);
				    	}
				    	Vector item=new Vector();
				    	item.add("����");
				    	item.add("����");
				    	item.add("����");
				    	item.add("����");
				    	DefaultTableModel model=new DefaultTableModel(values,item);
				    	Update_Table.setModel(model);
					}
					else{
						JOptionPane.showMessageDialog(null, "û���ҵ���Ӧ�Ļ�����", "������",JOptionPane.ERROR_MESSAGE);
						Update_ID_textField.setText(null);
						break RE;
					}
				}			
			}
			//��հ�ť�¼�
			if (e.getSource() == Update_ButtonClear ){
				//������������б�
				Vector values =new Vector();
		    		Vector temp=new Vector();
		    		temp.add(temp_ID);
		    		//temp_ID = rs.getInt("ID");
		    		temp.add(temp_Commodity);
		    		//temp_Commodity = rs.getString("Commodity");
		    		temp.add(temp_Number);
		    		//temp_Number = rs.getString("Number");
		    		temp.add(temp_price);
		    		//temp_price = rs.getString("price");
		    		values.add(temp);
		    	Vector item=new Vector();
		    	item.add("����");
		    	item.add("����");
		    	item.add("����");
		    	item.add("����");
		    	DefaultTableModel model=new DefaultTableModel(values,item);
		    	Update_Table.setModel(model);
			}
		
			//ȷ����ť�¼�
			if ( e.getSource() == Update_ButtonOK ){
				//����SQL���
				String Update_Table_ID = Update_Table.getValueAt(0,0).toString();
				String Update_Table_Commodity = Update_Table.getValueAt(0,1).toString();
				String Update_Table_Number = Update_Table.getValueAt(0,2).toString();
				String Update_Table_price = Update_Table.getValueAt(0,3).toString();
				
				//�жϺϷ���
				String Test_ID = "select * from Stock where ID= '"+Update_Table_ID+"'";
				String Test_Commodity = "select * from Stock where Commodity= '"+Update_Table_Commodity+"'";
				int Count_ID,Count_Commodity;//��ѯ�������
				Find_Data Test = new Find_Data();//����Find����
				Count_ID = Test.Count(Test_ID);
				Count_Commodity = Test.Count(Test_Commodity);

	    		if ((Count_ID>0) && (Integer.parseInt(Update_Table_ID) != temp_update_ID)) {
	    			JOptionPane.showMessageDialog(null, "�˻����Ѵ��ڣ������޸�Ϊ���ֵ��", "������",JOptionPane.ERROR_MESSAGE);
	    			break RU;
	    		}
	    		if ((Count_Commodity>0) && (Update_Table_Commodity != temp_update_Commodity)) {
	    			JOptionPane.showMessageDialog(null, "�������Ѵ��ڣ������޸�Ϊ���ֵ��", "������",JOptionPane.ERROR_MESSAGE);
	    			break RU;
	    		}
				
	    		//��ID�Ų�ѯ
				if(Update_ID_RadioButton.isSelected()){	
					//ִ���޸�
					String sql_Update = "update Stock set ID = '"+Update_Table_ID+"',Commodity = '"+Update_Table_Commodity+"',Number = '"+Update_Table_Number+"',price = '"+Update_Table_price+"' where ID = '"+Update_ID_textField.getText()+"'";
					Update_Data.Update(sql_Update);			
				}
				//�����ƺŲ�ѯ
				if(Update_ID_RadioButton.isSelected()){
					//ִ���޸�
					String sql_Update = "update Stock set ID = '"+Update_Table_ID+"',Commodity = '"+Update_Table_Commodity+"',Number = '"+Update_Table_Number+"',price = '"+Update_Table_price+"' where Commodity = '"+Update_Commodity_textField.getText()+"'";
					Update_Data.Update(sql_Update);
				}
			}
		}
		catch (Exception e1) {  
			e1.printStackTrace(); 
		}
	}
}
