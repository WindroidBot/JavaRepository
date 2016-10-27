import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.Color;
import java.awt.Font;


public class login_GUI extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5968700537494649679L;
	private JPanel contentPane;
	private JTextField User_name_textField;
	private JPasswordField User_password_passwordField;

	private JLabel User_name_Label;
	private  JLabel User_password_Label;
	private JButton Login_ButtonOK;
	private JButton Login_ButtonDel;
	String Level;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login_GUI frame = new login_GUI();
					frame.setLocationRelativeTo(null);//������ʾ����
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public login_GUI() {
		setResizable(false);
		setTitle("\u767B\u5F55");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(100, 100, 401, 274);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		User_name_Label = new JLabel("\u7528\u6237\u540D\uFF1A");
		User_name_Label.setFont(new Font("΢���ź�", Font.PLAIN, 12));
		User_name_Label.setBounds(86, 109, 54, 15);
		contentPane.add(User_name_Label);
		
		User_name_textField = new JTextField();
		User_name_textField.setBounds(172, 106, 132, 21);
		contentPane.add(User_name_textField);
		User_name_textField.setColumns(10);
		
		User_password_Label = new JLabel("\u5BC6\u7801\uFF1A");
		User_password_Label.setFont(new Font("΢���ź�", Font.PLAIN, 12));
		User_password_Label.setBounds(86, 148, 54, 15);
		contentPane.add(User_password_Label);
		
		User_password_passwordField = new JPasswordField();
		User_password_passwordField.setBounds(172, 145, 132, 21);
		contentPane.add(User_password_passwordField);
		
		Login_ButtonOK = new JButton("\u767B\u5F55");
		Login_ButtonOK.setFont(new Font("΢���ź�", Font.PLAIN, 12));
		Login_ButtonOK.setBounds(86, 196, 93, 23);
		contentPane.add(Login_ButtonOK);
		
		Login_ButtonDel = new JButton("\u6E05\u7A7A");
		Login_ButtonDel.setFont(new Font("΢���ź�", Font.PLAIN, 12));
		Login_ButtonDel.setBounds(211, 196, 93, 23);
		contentPane.add(Login_ButtonDel);
		
		JLabel Title_Label = new JLabel("          \u6B22\u8FCE\u4F7F\u7528\u8D85\u5E02\u7BA1\u7406\u7CFB\u7EDF");
		Title_Label.setFont(new Font("΢���ź�", Font.BOLD, 25));
		Title_Label.setForeground(Color.RED);
		Title_Label.setBounds(10, 10, 362, 89);
		contentPane.add(Title_Label);
		
		//ע�������
		Login_ButtonOK.addActionListener(this);
		Login_ButtonDel.addActionListener(this);
	}

	public void actionPerformed(ActionEvent arg0) {
		RE:	
		
			//�����ť�¼�
			if (arg0.getSource() == Login_ButtonDel ){
				User_name_textField.setText(null);
				User_password_passwordField.setText(null);
				break RE;
			}
			//ȷ����ť�¼�
		try {
			if (arg0.getSource() == Login_ButtonOK ){
				Connection con = LinkDataBase.Link(null);
				Statement s = con.createStatement();
				ResultSet rs;
				String sql_Find_user = "select * from User where Login_name = '"+User_name_textField.getText()+"'";
				//�����û����Ƿ����
				Find_Data find_user = new Find_Data();
				int Count_user = find_user.Count(sql_Find_user);
				//System.out.println(Count_user);
				if (Count_user > 0){
					//��������
					@SuppressWarnings("deprecation")
					String sql_Find_password = "select * from User where Login_name = '"+User_name_textField.getText()+"' and Password = '"+User_password_passwordField.getText()+"'";
					int Count_password = find_user.Count(sql_Find_password);
					if (Count_password > 0){
						rs = s.executeQuery(sql_Find_password);
						if(rs.next()) {
							Level = rs.getString("Level");//��ȡUser����Level�ֶε�ֵ
						}
						JOptionPane.showMessageDialog(null, "��¼�ɹ�����������ǣ�"+Level, "��¼",JOptionPane.INFORMATION_MESSAGE);	
						Main_GUI main_GUI_obj = new Main_GUI(Level);
						User_password_passwordField.setText(null); 
						User_name_textField.setText(null);
						setVisible(false);
			
						main_GUI_obj.setVisible(true);
						main_GUI_obj.setLocationRelativeTo(null);//������ʾ����
						this.setEnabled(true);
						
					}
					else {
						JOptionPane.showMessageDialog(null, "�������", "��¼",JOptionPane.ERROR_MESSAGE);
						User_password_passwordField.setText(null); 
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "�û������ڣ�", "��¼",JOptionPane.ERROR_MESSAGE);
					User_name_textField.setText(null); 
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
