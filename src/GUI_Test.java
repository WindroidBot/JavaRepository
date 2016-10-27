import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;


public class GUI_Test extends JFrame implements ItemListener,ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3983910385276380219L;
	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI_Test frame = new GUI_Test();
					frame.setLocationRelativeTo(null);//居中显示窗体
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
	public GUI_Test() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(36, 33, 369, 186);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(308, 229, 93, 23);
		contentPane.add(btnNewButton);
		
		btnNewButton.addActionListener(this);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void actionPerformed(ActionEvent arg0) {
		Connection con = LinkDataBase.Link(null);
		try {
			Statement s = con.createStatement();
		
		String sql_Find;//定义SQL语句
		ResultSet rs;//获取Number字段内容
		sql_Find = "select * from Stock";
		rs = s.executeQuery(sql_Find);
		if(rs.next()) {
		}
		
		Vector values =new Vector();
    	while (rs.next()) { 
    		Vector temp=new Vector();
    		temp.add(rs.getString("ID"));
    		temp.add(rs.getString("Commodity"));
    		temp.add(rs.getString("Number"));
    		temp.add(rs.getString("price"));
    		values.add(temp);
    	}
    	Vector item=new Vector();
    	item.add("货号");
    	item.add("名称");
    	//item.add("Commodity");
    	item.add("数量");
    	item.add("单价");
    	//item.add("price");
    	DefaultTableModel model=new DefaultTableModel(values,item);
    	table.setModel(model);
		
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
		
		
		
	}

	@Override
	public void itemStateChanged(ItemEvent arg0) {
		// TODO 自动生成的方法存根
		
	}
}
