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
	private int sum_price = 0;//��¼�ܼ�
	public int Final_Num;
	public int Old_Num[] = new int[50];//����Stock�����Number�ֶ�ֵ
	public int Buy_Num[] = new int[50];//����BuyList�����Buy_Number�ֶ�ֵ
	public int Old_ID[] = new int[50];//����ı������Ʒ��ID��
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args){
		
		Sale_GUI frame = new Sale_GUI();
		frame.setLocationRelativeTo(null);//������ʾ����
		frame.setVisible(true);
				
	}

	/**
	 * Create the frame.
	 */
	public Sale_GUI() {
		setResizable(false);
		this.setModal(true);//����Ϊģ̬����
		//����
		setTitle("\u9500\u552E");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 579, 354);
		Sale_GUI_contentPane = new JPanel();
		Sale_GUI_contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(Sale_GUI_contentPane);
		Sale_GUI_contentPane.setLayout(null);
		this.setLocationRelativeTo(null);
		//���ŵ�ѡ��ť
		Stock_ID_RadioButton = new JRadioButton("\u8D27\u53F7\uFF1A");
		Stock_ID_RadioButton.setFont(new Font("΢���ź�", Font.PLAIN, 12));
		buttonGroup.add(Stock_ID_RadioButton);
		Stock_ID_RadioButton.setSelected(true);
		Stock_ID_RadioButton.setBounds(35, 28, 121, 23);
		Sale_GUI_contentPane.add(Stock_ID_RadioButton);
		//�����ı���
		Stock_ID_textField = new JTextField();
		Stock_ID_textField.setBounds(214, 29, 66, 21);
		Sale_GUI_contentPane.add(Stock_ID_textField);
		Stock_ID_textField.setColumns(10);
		//���Ƶ�ѡ��ť
		Stock_Commodity_RadioButton = new JRadioButton("\u540D\u79F0\uFF1A");
		Stock_Commodity_RadioButton.setFont(new Font("΢���ź�", Font.PLAIN, 12));
		buttonGroup.add(Stock_Commodity_RadioButton);
		Stock_Commodity_RadioButton.setBounds(35, 75, 121, 23);
		Sale_GUI_contentPane.add(Stock_Commodity_RadioButton);
		//�����ı���
		Stock_Commodity_textField = new JTextField();
		Stock_Commodity_textField.setEditable(false);
		Stock_Commodity_textField.setBounds(214, 76, 66, 21);
		Sale_GUI_contentPane.add(Stock_Commodity_textField);
		Stock_Commodity_textField.setColumns(10);
		//������ǩ
		Stock_Number_Label = new JLabel("\u6570\u91CF\uFF1A");
		Stock_Number_Label.setFont(new Font("΢���ź�", Font.PLAIN, 12));
		Stock_Number_Label.setBounds(35, 125, 54, 15);
		Sale_GUI_contentPane.add(Stock_Number_Label);
		//�����ı���
		Stock_Number_textField = new JTextField();
		Stock_Number_textField.setBounds(214, 122, 66, 21);
		Sale_GUI_contentPane.add(Stock_Number_textField);
		Stock_Number_textField.setColumns(10);
		//ȷ����ť
		Stock_ButtonOK = new JButton("\u6DFB\u52A0");
		Stock_ButtonOK.setFont(new Font("΢���ź�", Font.PLAIN, 12));
		Stock_ButtonOK.setBounds(343, 121, 93, 23);
		Sale_GUI_contentPane.add(Stock_ButtonOK);
		//���˰�ť
		Stock_ButtonSALE = new JButton("\u7ED3\u8D26");
		Stock_ButtonSALE.setFont(new Font("΢���ź�", Font.PLAIN, 12));
		Stock_ButtonSALE.setBounds(415, 273, 93, 23);
		Sale_GUI_contentPane.add(Stock_ButtonSALE);
		//��հ�ť
		Stock_ButtonClear = new JButton("\u6E05\u7A7A");
		Stock_ButtonClear.setFont(new Font("΢���ź�", Font.PLAIN, 12));
		Stock_ButtonClear.setBounds(343, 53, 93, 23);
		Sale_GUI_contentPane.add(Stock_ButtonClear);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 153, 390, 162);
		Sale_GUI_contentPane.add(scrollPane);
		
		Stock_Sale_Table = new JTable();
		Stock_Sale_Table.setEnabled(false);
		scrollPane.setColumnHeaderView(Stock_Sale_Table);
		
		//ע�������
		Stock_ID_RadioButton.addItemListener(this);
		Stock_Commodity_RadioButton.addItemListener(this);
		Stock_ButtonOK.addActionListener(this);
		Stock_ButtonSALE.addActionListener(this);
		Stock_ButtonClear.addActionListener(this);
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

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void actionPerformed(ActionEvent e) {
		Toolkit.getDefaultToolkit().beep();
		RE:
		try {
			//������ر���
			Connection con = LinkDataBase.Link(null);
			String sql_Find,sql_Add,sql_Del,sql_Del_2,sql_Update;//����SQL���
			
			ResultSet result;
			ResultSet rs;//��ȡNumber�ֶ�����
			Find_Data co = new Find_Data();//����Find����
			
		//��հ�ť�¼�
		if (e.getSource() == Stock_ButtonClear ){
			sql_Find = "select * from BuyList";
			sql_Del = "delete from BuyList";
			sql_Del_2 = "delete from BuyList2";
			//����б�
			Delete_Data.Delete(sql_Del);
			Delete_Data.Delete(sql_Del_2);
			Statement s = con.createStatement();// ����SQL������  
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
	    	item.add("����");
	    	item.add("����");
	    	item.add("����");
	    	//item.add("price");
	    	DefaultTableModel model=new DefaultTableModel(values,item);
	    	Stock_Sale_Table.setModel(model);
	    	//�����ʱ����
	    	Stock_Commodity_textField.setText(null);
	    	Stock_ID_textField.setText(null);
	    	Stock_Number_textField.setText(null);
			Arrays.fill(Old_Num, -1);
			Arrays.fill(Buy_Num, -1);
			Arrays.fill(Old_ID, -1);
			i = 0;
			sum_price = 0;
		}
		
		//ȷ����ť�¼�
		if ( e.getSource() == Stock_ButtonOK ){
			
//-----------------------------------------------ѡ����ID��¼����Ʒ����-----------------------------------------
			if(Stock_ID_RadioButton.isSelected()){
				String sql_Text_repeat_Buy_ID = "select * from BuyList where ID="+Stock_ID_textField.getText();
				//��ȡ�����������ֵ
				String sql_get_Buy_ID = "select * from Stock where ID='"+Stock_ID_textField.getText()+"'";
				if(Stock_Number_textField.getText().length() == 0)
					JOptionPane.showMessageDialog(null, "������������Ϊ�գ�", "����",JOptionPane.ERROR_MESSAGE);
				int Count = co.Count(sql_get_Buy_ID);//����Stock ID�ֶμ���
				//�ж�ID��¼���Ƿ��ظ�
				int Count_Buy_ID= co.Count(sql_Text_repeat_Buy_ID);//��ȡBuyList ID�ֶμ���
				if (Count_Buy_ID != 0){
					JOptionPane.showMessageDialog(null, "�˻����Ѵ����嵥�ڣ������ظ�¼�룡", "����",JOptionPane.ERROR_MESSAGE);
					Stock_ID_textField.setText(null); 
					break RE;
				}
				//�ж��Ƿ����ID��
				if (Count > 0){
					Statement s = con.createStatement();
            		rs = s.executeQuery(sql_get_Buy_ID);
            		if(rs.next()) {
            			int temp_Stock_Num = Integer.parseInt(rs.getString("Number"));//��ȡ	Stock����Number�ֶε�ֵ
            			int price = Integer.parseInt(rs.getString("price"));
            			int temp_BuyList_Num = Integer.parseInt(Stock_Number_textField.getText());//��ȡ��������Buy_Num
            			//�жϿ���빺�����Ϸ���
            			if( temp_Stock_Num >= temp_BuyList_Num ){
            				Old_Num[i] = temp_Stock_Num;
            				Buy_Num[i] = temp_BuyList_Num;
            				sum_price += Buy_Num[i] * price ;//�����ܼ�
            				//��ȡ��ʱ�Ļ���
            				Old_ID[i] = Integer.parseInt(Stock_ID_textField.getText());
            				//System.out.println("��ȡ����Old_Num[i]ֵΪ��"+Old_Num[i]);
            				//System.out.println("��ȡ����Buy_Num[i]ֵΪ��"+Buy_Num[i]);
            				//System.out.println("���ڸı��IDΪ��"+Old_ID[i]);
            				i++;
            			}
            			else{
            				JOptionPane.showMessageDialog(null, "����Ʒ��治�㣡", "����",JOptionPane.ERROR_MESSAGE);
            				Stock_Number_textField.setText(null);
            				break RE;
            			}
            		}
				}
				else{
					JOptionPane.showMessageDialog(null, "û���ҵ���Ӧ�Ļ��ţ�", "����",JOptionPane.ERROR_MESSAGE);
					Stock_ID_textField.setText(null);
					break RE;
				}
				sql_Add = "INSERT INTO BuyList(ID,Buy_Number) VALUES ("+Stock_ID_textField.getText()+","+Integer.parseInt(Stock_Number_textField.getText())+")";
				sql_Find = "select * from BuyList";
				Add_Data.Add(sql_Add);
				Statement s = con.createStatement();// ����SQL������  
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
		    	item.add("����");
		    	//item.add("����");
		    	item.add("����");
		    	DefaultTableModel model=new DefaultTableModel(values,item);
		    	Stock_Sale_Table.setModel(model);
			}		
			
//----------------------------------ѡ������Ʒ��¼����Ʒ����-----------------------------------------
			if(Stock_Commodity_RadioButton.isSelected()){
				String sql_Text_repeat_Buy_Commodity = "select * from BuyList where Commodity="+Stock_Commodity_textField.getText();
				//��ȡ�����������ֵ
				String sql_get_Buy_Commodity = "select * from Stock where Commodity='"+Stock_Commodity_textField.getText()+"'";
				if(Stock_Number_textField.getText().length() == 0)
					JOptionPane.showMessageDialog(null, "������������Ϊ�գ�", "����",JOptionPane.ERROR_MESSAGE);
				int Count = co.Count(sql_get_Buy_Commodity);//����Stock ID�ֶμ���
				//�ж�ID��¼���Ƿ��ظ�
				int Count_Buy_Commodity= co.Count(sql_Text_repeat_Buy_Commodity);//��ȡBuyList ID�ֶμ���
				if (Count_Buy_Commodity != 0){
					JOptionPane.showMessageDialog(null, "�˻����Ѵ����嵥�ڣ������ظ�¼�룡", "����",JOptionPane.ERROR_MESSAGE);
					Stock_ID_textField.setText(null); 
					break RE;
				}
				//�ж��Ƿ����ID��
				if (Count > 0){
					Statement s = con.createStatement();
					rs = s.executeQuery(sql_get_Buy_Commodity);
					if(rs.next()) {
						int temp_Stock_Num = Integer.parseInt(rs.getString("Number"));//��ȡ	Stock����Number�ֶε�ֵ
						//System.out.println("��ȡ	Stock����Number�ֶε�ֵ�ɹ�");
						int Convert_Stock_ID = Integer.parseInt(rs.getString("ID"));
						//System.out.println("��ȡ	Stock����ID�ֶε�ֵ�ɹ�");
						int price = Integer.parseInt(rs.getString("price"));
						//System.out.println("��ȡ	Stock����price�ֶε�ֵ�ɹ�");
						int temp_BuyList_Num = Integer.parseInt(Stock_Number_textField.getText());//��ȡ��������Buy_Num
						//�жϿ���빺�����Ϸ���
						if( temp_Stock_Num >= temp_BuyList_Num ){
							Old_Num[i] = temp_Stock_Num;
							Buy_Num[i] = temp_BuyList_Num;
							sum_price += Buy_Num[i] * price ;//�����ܼ�
							//��ȡ��ʱ�Ļ���
							Old_ID[i] = Convert_Stock_ID;
							//System.out.println("��ȡ����Old_Num[i]ֵΪ��"+Old_Num[i]);
							//System.out.println("��ȡ����Buy_Num[i]ֵΪ��"+Buy_Num[i]);
							//System.out.println("���ڸı��IDΪ��"+Old_ID[i]);
							i++;
						}
						else{
							JOptionPane.showMessageDialog(null, "����Ʒ��治�㣡", "����",JOptionPane.ERROR_MESSAGE);
							Stock_Number_textField.setText(null);
							break RE;
        			}
        		}
			}
				else{
					JOptionPane.showMessageDialog(null, "û���ҵ���Ӧ����Ʒ��", "����",JOptionPane.ERROR_MESSAGE);
					Stock_Commodity_textField.setText(null);
					break RE;
					}
				sql_Add = "INSERT INTO BuyList2(Commodity,Buy_Number) VALUES ('"+Stock_Commodity_textField.getText()+"',"+Integer.parseInt(Stock_Number_textField.getText())+")";
				sql_Find = "select * from BuyList2";
				Add_Data.Add(sql_Add);
				System.out.println("�������ݳɹ���");
				Statement s = con.createStatement();// ����SQL������  
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
				item.add("��Ʒ");
				//item.add("Commodity");
				item.add("����");
				//item.add("price");
				DefaultTableModel model=new DefaultTableModel(values,item);
				Stock_Sale_Table.setModel(model);
			}
		}		

		//���˰�ť�¼� 
			if (e.getSource() == Stock_ButtonSALE ){
				int Count;
				if(Stock_ID_RadioButton.isSelected())
					Count = co.Count("select * from BuyList");
				else  Count = co.Count("select * from BuyList2");
				System.out.println("Count������"+Count);
				for( int j = 0; j < Count ; j++ ){
					Final_Num = Old_Num[j]-Buy_Num[j];
					sql_Update = "update Stock set Number="+Final_Num+" where ID='"+Old_ID[j]+"'";
					Update_Data.Update(sql_Update);
				}
				JOptionPane.showMessageDialog(null, "���˳ɹ����ܽ�"+sum_price, "����",JOptionPane.INFORMATION_MESSAGE);
			}
		}	catch (Exception e1) {  
			e1.printStackTrace(); 
			}		
	}
}