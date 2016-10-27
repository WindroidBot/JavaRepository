import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JFrame;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import java.awt.Color;


public class Main_GUI extends JFrame implements ActionListener,Icon{
	/**
	 * 
	 */
	private static final long serialVersionUID = -768433560036890812L;
	private   Icon   icon   =   null;

	private JMenuItem MenuItem_Add;
	private JMenuItem MenuItem_New;
	private JMenuItem MenuItem_Del;
	private	JMenuItem MenuItem_Update;
	private	JMenuItem MenuItem_Sale;
	private JMenu Menu_User;
	private JMenuItem MenuItem_Logout;
	private  JMenuItem MenuItem_About;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					String L = "收营员";
					Main_GUI frame = new Main_GUI(L);
					frame.setLocationRelativeTo(null);//居中显示窗体
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}
	
	 public void  Test_Image(Icon   icon)   { 
         this.icon   =   icon; 
//         icon=new ImageIcon(i);
	 } 

    
	
	

	/**
	 * Create the frame.
	 */
	public Main_GUI(String L) {
		System.out.println("当前用户："+L);
		setTitle("\u5C0F\u578B\u5546\u94FA\u7BA1\u7406\u7CFB\u7EDF Ver 1.1");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 981, 562);
		
		 Test_Image icon = new Test_Image(new ImageIcon("C:/Users/xiexy/Pictures/543.jpg")); 
         
         JLabel lblNewLabel = new JLabel("New label");
         lblNewLabel.setFont(new Font("微软雅黑", Font.BOLD, 22));
         lblNewLabel.setText("当前用户权限："+L);
         lblNewLabel.setForeground(Color.WHITE);
         lblNewLabel.setBounds(22, 23, 321, 54);
         getContentPane().add(lblNewLabel);
         JLabel   label   =   new   JLabel(icon); 
         label.setBounds(0, 0, 1008, 580);
         this.getContentPane().add(label);
         this.setSize(1024,640); 
         this.setLocationRelativeTo(null); 
         this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
         this.setVisible(true); 
		
		//菜单栏
		JMenuBar menuBar = new JMenuBar();
		menuBar.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		setJMenuBar(menuBar);
		JMenu Menu_Management = new JMenu("\u7BA1\u7406");
		Menu_Management.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		menuBar.add(Menu_Management);
		//进货
		MenuItem_Add = new JMenuItem("\u8FDB\u8D27");
		MenuItem_Add.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		Menu_Management.add(MenuItem_Add);
		if(L.equals("收银员"))
			MenuItem_Add.setEnabled(false);
		//添加新商品
		MenuItem_New = new JMenuItem("\u6DFB\u52A0\u65B0\u5546\u54C1");
		MenuItem_New.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		Menu_Management.add(MenuItem_New);
		if(L.equals("收银员"))
			MenuItem_New.setEnabled(false);
		//删除商品
		MenuItem_Del = new JMenuItem("\u5220\u9664\u5546\u54C1");
		MenuItem_Del.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		Menu_Management.add(MenuItem_Del);
		if(L.equals("收银员"))
			MenuItem_Del.setEnabled(false);
		//库存管理
		MenuItem_Update = new JMenuItem("\u5E93\u5B58\u7BA1\u7406");
		MenuItem_Update.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		Menu_Management.add(MenuItem_Update);
		if(L.equals("收银员"))
			MenuItem_Update.setEnabled(false);
		
		Menu_Management.addSeparator();
		//销售
		MenuItem_Sale = new JMenuItem("\u9500\u552E");
		MenuItem_Sale.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		Menu_Management.add(MenuItem_Sale);
		
		Menu_User = new JMenu("\u8D26\u6237");
		Menu_User.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		menuBar.add(Menu_User);
		//注销
		MenuItem_Logout = new JMenuItem("\u6CE8\u9500");
		MenuItem_Logout.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		Menu_User.add(MenuItem_Logout);
		
		JMenu Menu_Help = new JMenu("\u5E2E\u52A9");
		Menu_Help.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		menuBar.add(Menu_Help);
		//关于
		MenuItem_About = new JMenuItem("\u5173\u4E8E");
		MenuItem_About.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		Menu_Help.add(MenuItem_About);
		
		//注册监听器
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
		//进货
		if (arg0.getSource() == MenuItem_Add){
			Add_Data_GUI add_data_GUI_obj = new Add_Data_GUI();
			add_data_GUI_obj.setVisible(true);
			add_data_GUI_obj.setLocationRelativeTo(null);//居中显示窗体
			this.setEnabled(true);
		}
		//添加新商品
		if (arg0.getSource() == MenuItem_New){
			New_Data_GUI new_data_GUI_obj = new New_Data_GUI();
			new_data_GUI_obj.setVisible(true);
			new_data_GUI_obj.setLocationRelativeTo(null);//居中显示窗体
			this.setEnabled(true);
		}
		//删除商品
		if (arg0.getSource() == MenuItem_Del){
			Delete_Data_GUI delete_data_GUI_obj = new Delete_Data_GUI();
			delete_data_GUI_obj.setVisible(true);
			delete_data_GUI_obj.setLocationRelativeTo(null);//居中显示窗体
			this.setEnabled(true);
		}
		//库存管理
		if (arg0.getSource() == MenuItem_Update){
			Update_Data_GUI update_data_GUI_obj = new Update_Data_GUI();
			update_data_GUI_obj.setVisible(true);
			update_data_GUI_obj.setLocationRelativeTo(null);//居中显示窗体
			this.setEnabled(true);
		}
		//销售
		if (arg0.getSource() == MenuItem_Sale){
			Sale_GUI sale_GUI_obj = new Sale_GUI();
			sale_GUI_obj.setVisible(true);
			sale_GUI_obj.setLocationRelativeTo(null);//居中显示窗体
			this.setEnabled(true);
		}
		//注销
		if (arg0.getSource() == MenuItem_Logout){
			int cont = JOptionPane.showConfirmDialog(null,"确认注销？","账户",JOptionPane.OK_OPTION);
			if(cont!=JOptionPane.OK_OPTION){
				System.out.println("否");
		 	}
			else{
				System.out.println("是");
				JOptionPane.showMessageDialog(null, "已注销当前账户！", "账户",JOptionPane.INFORMATION_MESSAGE);
				login_GUI login_GUI_obj = new login_GUI();
				login_GUI_obj.setVisible(true);
				login_GUI_obj.setLocationRelativeTo(null);//居中显示窗体
				this.setEnabled(true);
				setVisible(false);
			}	
		}
		//关于
		if (arg0.getSource() == MenuItem_About){
			About About_GUI_obj = new About();
			About_GUI_obj.setVisible(true);
			About_GUI_obj.setLocationRelativeTo(null);//居中显示窗体
			this.setEnabled(true);
		}

	}

	@Override
	public int getIconHeight() {
		return   icon.getIconHeight(); 
	}

	@Override
	public int getIconWidth() {
		return   icon.getIconWidth(); 
	}

	@Override
	public void paintIcon(Component c, Graphics g, int x, int y) {
		float   wid   =   c.getWidth(); 
        float   hei   =   c.getHeight(); 
        int   iconWid   =   icon.getIconWidth(); 
        int   iconHei   =   icon.getIconHeight(); 

        Graphics2D   g2d   =   (Graphics2D)   g; 
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.scale(wid/iconWid,   hei/iconHei);
        icon.paintIcon(c,   g2d,   0,   0);
	}
}
