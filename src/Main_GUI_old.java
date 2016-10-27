import java.awt.BorderLayout;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import   java.awt.*; 
import   java.awt.BorderLayout; 
import   java.awt.Component; 
import   java.awt.Graphics; 
import   java.awt.Graphics2D; 
 
import   javax.swing.Icon; 
import   javax.swing.ImageIcon; 
import   javax.swing.JFrame; 
import   javax.swing.JLabel;

public class Main_GUI_old extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5112604065393032558L;

	private JMenuItem MenuItem_Add;
	private JMenuItem MenuItem_New;
	private JMenuItem MenuItem_Del;
	private	JMenuItem MenuItem_Update;
	private	JMenuItem MenuItem_Sale;
	private JMenu Menu_User;
	private JMenuItem MenuItem_Logout;
	private  JMenuItem MenuItem_About;
	private JPanel panel;
	private JLabel lblNewLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					String L = "��ӪԱ";
					Icon Icon = null;
					Main_GUI frame = new Main_GUI(L);
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
	public Main_GUI_old(String L) {
		System.out.println("��ǰ�û���"+L);
		setTitle("\u5C0F\u578B\u5546\u94FA\u7BA1\u7406\u7CFB\u7EDF Ver 1.0");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 981, 562);
		
		JLabel   label   =   new   JLabel(new  ImageIcon( "C:/Users/xiexy/Pictures/543.jpg")); 
		this.getContentPane().add(label,   BorderLayout.CENTER); 
//        frame.getContentPane().add(new JButton("click"),BorderLayout.NORTH);
		this.setSize(1024, 600); 
		this.setLocationRelativeTo(null); 
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		this.setVisible(true); 
		
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu Menu_Management = new JMenu("\u7BA1\u7406");
		menuBar.add(Menu_Management);
		//����
		MenuItem_Add = new JMenuItem("\u8FDB\u8D27");
		Menu_Management.add(MenuItem_Add);
		if(L.equals("����Ա"))
			MenuItem_Add.setEnabled(false);
		//�������Ʒ
		MenuItem_New = new JMenuItem("\u6DFB\u52A0\u65B0\u5546\u54C1");
		Menu_Management.add(MenuItem_New);
		if(L.equals("����Ա"))
			MenuItem_New.setEnabled(false);
		//ɾ����Ʒ
		MenuItem_Del = new JMenuItem("\u5220\u9664\u5546\u54C1");
		Menu_Management.add(MenuItem_Del);
		if(L.equals("����Ա"))
			MenuItem_Del.setEnabled(false);
		//������
		MenuItem_Update = new JMenuItem("\u5E93\u5B58\u7BA1\u7406");
		Menu_Management.add(MenuItem_Update);
		if(L.equals("����Ա"))
			MenuItem_Update.setEnabled(false);
		
		Menu_Management.addSeparator();
		//����
		MenuItem_Sale = new JMenuItem("\u9500\u552E");
		Menu_Management.add(MenuItem_Sale);
		
		Menu_User = new JMenu("\u8D26\u6237");
		menuBar.add(Menu_User);
		//ע��
		MenuItem_Logout = new JMenuItem("\u6CE8\u9500");
		Menu_User.add(MenuItem_Logout);
		
		JMenu Menu_Help = new JMenu("\u5E2E\u52A9");
		menuBar.add(Menu_Help);
		//����
		MenuItem_About = new JMenuItem("\u5173\u4E8E");
		Menu_Help.add(MenuItem_About);
		
		
		
		//ע�������
		MenuItem_Add.addActionListener(this);
		MenuItem_New.addActionListener(this);
		MenuItem_Del.addActionListener(this);
		MenuItem_Update.addActionListener(this);
		MenuItem_Sale.addActionListener(this);
		MenuItem_Logout.addActionListener(this);
		MenuItem_About.addActionListener(this);
		
		
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		//����
		if (arg0.getSource() == MenuItem_Add){
			Add_Data_GUI add_data_GUI_obj = new Add_Data_GUI();
			add_data_GUI_obj.setVisible(true);
			add_data_GUI_obj.setLocationRelativeTo(null);//������ʾ����
			this.setEnabled(true);
		}
		//�������Ʒ
		if (arg0.getSource() == MenuItem_New){
			New_Data_GUI new_data_GUI_obj = new New_Data_GUI();
			new_data_GUI_obj.setVisible(true);
			new_data_GUI_obj.setLocationRelativeTo(null);//������ʾ����
			this.setEnabled(true);
		}
		//ɾ����Ʒ
		if (arg0.getSource() == MenuItem_Del){
			Delete_Data_GUI delete_data_GUI_obj = new Delete_Data_GUI();
			delete_data_GUI_obj.setVisible(true);
			delete_data_GUI_obj.setLocationRelativeTo(null);//������ʾ����
			this.setEnabled(true);
		}
		//������
		if (arg0.getSource() == MenuItem_Update){
			Update_Data_GUI update_data_GUI_obj = new Update_Data_GUI();
			update_data_GUI_obj.setVisible(true);
			update_data_GUI_obj.setLocationRelativeTo(null);//������ʾ����
			this.setEnabled(true);
		}
		//����
		if (arg0.getSource() == MenuItem_Sale){
			Sale_GUI sale_GUI_obj = new Sale_GUI();
			sale_GUI_obj.setVisible(true);
			sale_GUI_obj.setLocationRelativeTo(null);//������ʾ����
			this.setEnabled(true);
		}
		//ע��
		if (arg0.getSource() == MenuItem_Logout){
			int cont = JOptionPane.showConfirmDialog(null,"ȷ��ע����","�˻�",JOptionPane.OK_OPTION);
			if(cont!=JOptionPane.OK_OPTION){
				System.out.println("��");
		 	}
			else{
				System.out.println("��");
				JOptionPane.showMessageDialog(null, "��ע����ǰ�˻���", "�˻�",JOptionPane.INFORMATION_MESSAGE);
				login_GUI login_GUI_obj = new login_GUI();
				login_GUI_obj.setVisible(true);
				login_GUI_obj.setLocationRelativeTo(null);//������ʾ����
				this.setEnabled(true);
				setVisible(false);
			}
			
			
		}
		//[����]
		if (arg0.getSource() == MenuItem_About){
			About About_GUI_obj = new About();
			About_GUI_obj.setVisible(true);
			About_GUI_obj.setLocationRelativeTo(null);//������ʾ����
			this.setEnabled(true);
		}
		
		

	}
}
