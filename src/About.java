import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;


public class About extends JDialog implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6380538246051172028L;
	private JPanel contentPane;
	private  JButton ButtonOK;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					About frame = new About();
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
	public About() {
		setTitle("\u5173\u4E8E\u672C\u7CFB\u7EDF");
		setResizable(false);
		this.setModal(true);//…Ë÷√Œ™ƒ£Ã¨¥∞ÃÂ
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);//æ”÷–œ‘ æ¥∞ÃÂ
		setBounds(100, 100, 509, 373);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setLocationRelativeTo(null);
		
		JLabel lblNewLabel = new JLabel("\u8FD9\u4E2A\u7CFB\u7EDF\u662FJava\u8BFE\u7A0B\u8BBE\u8BA1\u5C0F\u7EC4\u6210\u679C\r\n");
		lblNewLabel.setFont(new Font("Œ¢»Ì—≈∫⁄", Font.PLAIN, 12));
		lblNewLabel.setBounds(288, 43, 205, 15);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\u6210\u5458\uFF1AWindroid \u6C6A\u4FCA \u9648\u5B8F \u5F20\u6807");
		lblNewLabel_1.setFont(new Font("Œ¢»Ì—≈∫⁄", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(288, 68, 188, 15);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("(c) Copyright Windroid contributors and others 2015-2016.  All rights reserved");
		lblNewLabel_2.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		lblNewLabel_2.setForeground(Color.GRAY);
		lblNewLabel_2.setBounds(42, 319, 434, 15);
		contentPane.add(lblNewLabel_2);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 246, 251);
		contentPane.add(panel);
		
		JLabel lblNewLabel_3 = new JLabel("");
		panel.add(lblNewLabel_3);
		lblNewLabel_3.setIcon(new ImageIcon("C:\\Users\\xiexy\\Pictures\\QQ\u622A\u56FE20151211120450.png"));
		
		JLabel lblNewLabel_5 = new JLabel("\u5C0F\u578B\u8D85\u5E02\u7BA1\u7406\u7CFB\u7EDF Ver 1.1");
		lblNewLabel_5.setFont(new Font("Œ¢»Ì—≈∫⁄", Font.PLAIN, 12));
		lblNewLabel_5.setBounds(297, 178, 179, 15);
		contentPane.add(lblNewLabel_5);
		
		ButtonOK = new JButton("\u786E\u5B9A");
		ButtonOK.setFont(new Font("Œ¢»Ì—≈∫⁄", Font.PLAIN, 12));
		ButtonOK.setBounds(331, 238, 93, 23);
		contentPane.add(ButtonOK);
		
		ButtonOK.addActionListener(this);
		
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == ButtonOK){
			this.dispose();
		}
		
	}
		
	
}
