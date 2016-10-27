import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;


@SuppressWarnings("serial")
public class Delete_Data_GUI extends JDialog implements ItemListener,ActionListener{

	private JPanel contentPane;
	private JTextField Del_ID_textField;
	private JTextField Del_Commodity_textField;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	
	private JRadioButton Del_ID_RadioButton = new JRadioButton();
	private JRadioButton Del_Commodity_RadioButton = new JRadioButton();
	

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		Delete_Data_GUI frame = new Delete_Data_GUI();
		frame.setLocationRelativeTo(null);//������ʾ����
		frame.setVisible(true);
	}*/

	/**
	 * Create the frame.
	 */
	public Delete_Data_GUI() {
		setTitle("\u5220\u9664\u5E93\u5B58");
		setResizable(false);
		this.setModal(true);//����Ϊģ̬����
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 391, 263);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setLocationRelativeTo(null);
		
		Del_ID_RadioButton = new JRadioButton("\u8D27\u53F7\uFF1A");
		Del_ID_RadioButton.setFont(new Font("΢���ź�", Font.PLAIN, 12));
		Del_ID_RadioButton.setSelected(true);
		Del_ID_RadioButton.setToolTipText("");
		buttonGroup.add(Del_ID_RadioButton);
		Del_ID_RadioButton.setBounds(30, 63, 121, 23);
		contentPane.add(Del_ID_RadioButton);
		
		Del_ID_textField = new JTextField();
		Del_ID_textField.setBounds(157, 64, 66, 21);
		contentPane.add(Del_ID_textField);
		Del_ID_textField.setColumns(10);
		
		Del_Commodity_RadioButton = new JRadioButton("\u540D\u79F0\uFF1A");
		Del_Commodity_RadioButton.setFont(new Font("΢���ź�", Font.PLAIN, 12));
		buttonGroup.add(Del_Commodity_RadioButton);
		Del_Commodity_RadioButton.setBounds(30, 108, 121, 23);
		contentPane.add(Del_Commodity_RadioButton);
		
		Del_Commodity_textField = new JTextField();
		Del_Commodity_textField.setBounds(157, 109, 93, 21);
		Del_Commodity_textField.setEditable(false);
		contentPane.add(Del_Commodity_textField);
		Del_Commodity_textField.setColumns(10);
		
		JButton Del_ButtonOK = new JButton("\u786E\u5B9A\u5220\u9664");
		Del_ButtonOK.setFont(new Font("΢���ź�", Font.PLAIN, 12));
		Del_ButtonOK.setBounds(259, 172, 93, 23);
		contentPane.add(Del_ButtonOK);
		
		JLabel lblNewLabel = new JLabel("\u201C\u5220\u9664\u5E93\u5B58\u201D\u4F1A\u5220\u9664\u6307\u5B9A\u5546\u54C1\u7684\u5168\u90E8\u4FE1\u606F\uFF0C\u8BF7\u8C28\u614E\u64CD\u4F5C\uFF01");
		lblNewLabel.setFont(new Font("΢���ź�", Font.BOLD, 12));
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setToolTipText("");
		lblNewLabel.setBounds(10, 10, 374, 47);
		contentPane.add(lblNewLabel);
		
		//ע�������
		Del_ID_RadioButton.addItemListener(this);
		Del_Commodity_RadioButton.addItemListener(this);
		Del_ButtonOK.addActionListener(this);
	}
	
	//��ѡ��ť�¼�
	public void itemStateChanged(ItemEvent arg0) {
		if(Del_ID_RadioButton.isSelected()){
			Del_Commodity_textField.setEditable(false);
			Del_Commodity_textField.setText(null);
			Del_ID_textField.setEditable(true);
		}
		if(Del_Commodity_RadioButton.isSelected()){
			Del_ID_textField.setEditable(false);
			Del_ID_textField.setText(null);
			Del_Commodity_textField.setEditable(true);
		}
	}

	//ȷ��ɾ����ť�¼�
	public void actionPerformed(ActionEvent arg0) {
		Toolkit.getDefaultToolkit().beep();
		try {
			//������ر���
			String sql_Find,sql_Del;//����SQL���
			int Count;//��ѯ�������
			Find_Data co = new Find_Data();//����Find����
			
			//ѡ����ID��ɾ����Ʒ
			if(Del_ID_RadioButton.isSelected()){
				sql_Find = "select * from Stock where ID="+Del_ID_textField.getText();
				sql_Del = "delete from Stock where ID='"+Del_ID_textField.getText()+"'"; 
				Count = co.Count(sql_Find);//��ȡ�ֶμ���
				if (Count > 0){
					int cont = JOptionPane.showConfirmDialog(null,"ȷ��ɾ����","ɾ�����",JOptionPane.OK_OPTION);
						if(cont!=JOptionPane.OK_OPTION){
							System.out.println("��");
					 	}
						else{
							System.out.println("��");
							Delete_Data.Delete(sql_Del);
							JOptionPane.showMessageDialog(null, "��ɾ������Ʒ��", "ɾ�����",JOptionPane.INFORMATION_MESSAGE);
						}
				}
				else {
            		System.out.println("ID�Ų����ڣ�");
            		JOptionPane.showMessageDialog(null, "���Ų����ڣ�", "ɾ�����",JOptionPane.ERROR_MESSAGE);
            	}
				Del_ID_textField.setText(null);
			}
		
			//ѡ������Ʒ��ɾ����Ʒ
			if(Del_Commodity_RadioButton.isSelected()){
				sql_Find = "select * from Stock where Commodity='"+Del_Commodity_textField.getText()+"'";
				sql_Del = "delete from Stock where Commodity='"+Del_Commodity_textField.getText()+"'";  
				Count = co.Count(sql_Find);//��ȡ�ֶμ���
				if (Count > 0){
					int cont = JOptionPane.showConfirmDialog(null,"ȷ��ɾ����","ɾ�����",JOptionPane.OK_OPTION);
						if(cont!=JOptionPane.OK_OPTION){
							System.out.println("��");
					 	}
						else{
							System.out.println("��");
							Delete_Data.Delete(sql_Del);
							JOptionPane.showMessageDialog(null, "��ɾ������Ʒ��", "ɾ�����",JOptionPane.INFORMATION_MESSAGE);
						}
				}
				else {
            		System.out.println("ID�Ų����ڣ�");
            		JOptionPane.showMessageDialog(null, "����Ʒ�������ڣ�", "ɾ�����",JOptionPane.ERROR_MESSAGE);
            	}
				Del_Commodity_textField.setText(null);
			}
		}
		catch (Exception e) {  
			e.printStackTrace(); 
		}
	}
}


