package com.sun.demo;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;

import javax.swing.JTabbedPane;
import javax.swing.JDesktopPane;

import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JEditorPane;
import javax.swing.SwingConstants;
public class TestTools extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static JFrame frame;
	private JTextField create_ran_caos_user_input;
	private JTextField create_ran_caos_pw_input;
	private JTextField create_caos_email_user_input;
	private JTextField create_caos_email_pw_input;
	private JTextField create_email_user_input;
	private JTextField create_email_pw_input;
	private JTextField create_phone_user_input;
	private JTextField create_phone_pw_input;
	private JTextField create_ran_user_input;
	private JTextField create_ran_pw_input;
	private JTextField find_email_pd_input;
	private JTextField find_email_pd_output;
	private JTextField find_phone_pd_input;
	private JTextField find_phone_pd_output;
	private JTextField pub_case_user_test;
	private JTextField pub_case_pw_test;
	private JTextField pub_case_title_test;
	private JTextField pub_case_user;
	private JTextField pub_case_pw;
	private JTextField pub_case_title;
	private JTextField pub_topic_user_test;
	private JTextField pub_topic_pw_test;
	private JTextField pub_topic_title_test;
	private JTextField pub_topic_user;
	private JTextField pub_topic_pw;
	private JTextField pub_topic_title;
	private JTextField apk_path;
	private JTextField down_file_path;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new TestTools();
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
	public TestTools() {
		setForeground(Color.LIGHT_GRAY);
		setFont(new Font("Dialog", Font.BOLD, 13));
		setTitle("生成测试数据");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1036, 562);
		contentPane = new JPanel();
		contentPane.setToolTipText("");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(tabbedPane_1, GroupLayout.DEFAULT_SIZE, 842, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addComponent(tabbedPane_1, GroupLayout.PREFERRED_SIZE, 510, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		JDesktopPane desktopPane = new JDesktopPane();
		tabbedPane_1.addTab("注册用户", null, desktopPane, null);
		desktopPane.setBackground(SystemColor.controlHighlight);
		
		JButton create_ran_caos_user = new JButton("生成随机账号");
		create_ran_caos_user.setBounds(98, 110, 115, 23);
		desktopPane.add(create_ran_caos_user);
		create_ran_caos_user.addActionListener(new createRanCAOSUser());
		
		JLabel label_28 = new JLabel("注册CAOS");
		label_28.setBounds(24, 75, 64, 15);
		desktopPane.add(label_28);
		
		JLabel label_29 = new JLabel("用 户 名");
		label_29.setBounds(258, 114, 68, 15);
		desktopPane.add(label_29);
		
		create_ran_caos_user_input = new JTextField();
		create_ran_caos_user_input.setEditable(false);
		create_ran_caos_user_input.setColumns(10);
		create_ran_caos_user_input.setBounds(313, 111, 171, 21);
		desktopPane.add(create_ran_caos_user_input);
		
		JLabel label_30 = new JLabel("密  码");
		label_30.setBounds(505, 114, 36, 15);
		desktopPane.add(label_30);
		
		create_ran_caos_pw_input = new JTextField();
		create_ran_caos_pw_input.setEditable(false);
		create_ran_caos_pw_input.setColumns(10);
		create_ran_caos_pw_input.setBounds(544, 111, 108, 21);
		desktopPane.add(create_ran_caos_pw_input);
		
		JButton create_caos_email_user = new JButton("生成邮箱账号");
		create_caos_email_user.setBounds(98, 71, 115, 23);
		desktopPane.add(create_caos_email_user);
		create_caos_email_user.addActionListener(new createCAOSUser());
		
		JLabel label_31 = new JLabel("输入邮箱");
		label_31.setBounds(258, 75, 68, 15);
		desktopPane.add(label_31);
		
		create_caos_email_user_input = new JTextField();
		create_caos_email_user_input.setColumns(10);
		create_caos_email_user_input.setBounds(313, 72, 171, 21);
		desktopPane.add(create_caos_email_user_input);
		
		JLabel label_32 = new JLabel("密  码");
		label_32.setBounds(505, 75, 36, 15);
		desktopPane.add(label_32);
		
		create_caos_email_pw_input = new JTextField();
		create_caos_email_pw_input.setColumns(10);
		create_caos_email_pw_input.setBounds(544, 72, 108, 21);
		desktopPane.add(create_caos_email_pw_input);
		
		JLabel label_21 = new JLabel("注册唯医");
		label_21.setBounds(24, 175, 64, 15);
		desktopPane.add(label_21);
		
		JButton create_email_user = new JButton("生成邮箱账号");
		create_email_user.setBounds(98, 171, 115, 23);
		desktopPane.add(create_email_user);
		create_email_user.addActionListener(new createEmailUser());
		
		JLabel label_25 = new JLabel("输入邮箱");
		label_25.setBounds(258, 175, 54, 15);
		desktopPane.add(label_25);
		
		create_email_user_input = new JTextField();
		create_email_user_input.setColumns(10);
		create_email_user_input.setBounds(313, 172, 171, 21);
		desktopPane.add(create_email_user_input);
		
		JLabel label_26 = new JLabel("密  码");
		label_26.setBounds(505, 175, 36, 15);
		desktopPane.add(label_26);
		
		create_email_pw_input = new JTextField();
		create_email_pw_input.setColumns(10);
		create_email_pw_input.setBounds(544, 172, 108, 21);
		desktopPane.add(create_email_pw_input);
		
		JButton create_phone_user = new JButton("生成手机账号");
		create_phone_user.setBounds(98, 212, 115, 23);
		desktopPane.add(create_phone_user);
		create_phone_user.addActionListener(new createPhoneUser());
		
		JLabel label_27 = new JLabel("输入手机");
		label_27.setBounds(258, 216, 54, 15);
		desktopPane.add(label_27);
		
		create_phone_user_input = new JTextField();
		create_phone_user_input.setColumns(10);
		create_phone_user_input.setBounds(313, 213, 171, 21);
		desktopPane.add(create_phone_user_input);
		
		JLabel label_33 = new JLabel("密  码");
		label_33.setBounds(505, 216, 36, 15);
		desktopPane.add(label_33);
		
		create_phone_pw_input = new JTextField();
		create_phone_pw_input.setColumns(10);
		create_phone_pw_input.setBounds(544, 213, 108, 21);
		desktopPane.add(create_phone_pw_input);
		
		JButton create_ran_user = new JButton("生成随机账号");
		create_ran_user.setBounds(98, 252, 115, 23);
		desktopPane.add(create_ran_user);
		create_ran_user.addActionListener(new createRanUser());
		
		JLabel label_34 = new JLabel("用 户 名");
		label_34.setBounds(258, 256, 68, 15);
		desktopPane.add(label_34);
		
		create_ran_user_input = new JTextField();
		create_ran_user_input.setEditable(false);
		create_ran_user_input.setColumns(10);
		create_ran_user_input.setBounds(313, 253, 171, 21);
		desktopPane.add(create_ran_user_input);
		
		JLabel label_35 = new JLabel("密  码");
		label_35.setBounds(505, 256, 36, 15);
		desktopPane.add(label_35);
		
		create_ran_pw_input = new JTextField();
		create_ran_pw_input.setEditable(false);
		create_ran_pw_input.setColumns(10);
		create_ran_pw_input.setBounds(544, 253, 108, 21);
		desktopPane.add(create_ran_pw_input);
		
		JEditorPane dtrpncaoshosts = new JEditorPane();
		dtrpncaoshosts.setEditable(false);
		dtrpncaoshosts.setText("使用须知：\r\n1. 注册唯医和CAOS未绑定环境，根据本地hosts \r\n& DNS配置决定是测试还是线上。\r\n自定义注册请按规则注册。\r\n邮箱：3个字符+ypg70+2个字符+合法后缀\r\n手机：177000+5位数字");
		dtrpncaoshosts.setBackground(SystemColor.info);
		dtrpncaoshosts.setBounds(718, 23, 266, 120);
		desktopPane.add(dtrpncaoshosts);
		
		JDesktopPane desktopPane_1 = new JDesktopPane();
		tabbedPane_1.addTab("得到邮箱链接和手机验证码", null, desktopPane_1, null);
		desktopPane_1.setBackground(SystemColor.controlHighlight);
		
		JButton find_email_pw = new JButton("得到邮箱链接");
		find_email_pw.setBounds(46, 49, 117, 23);
		desktopPane_1.add(find_email_pw);
		find_email_pw.addActionListener(new findEmailPW());
		
		JLabel label_1 = new JLabel("输入邮箱");
		label_1.setBounds(194, 53, 61, 15);
		desktopPane_1.add(label_1);
		
		find_email_pd_input = new JTextField();
		find_email_pd_input.setColumns(10);
		find_email_pd_input.setBounds(252, 50, 182, 21);
		desktopPane_1.add(find_email_pd_input);
		
		JLabel label_6 = new JLabel("链    接");
		label_6.setBounds(194, 81, 48, 15);
		desktopPane_1.add(label_6);
		
		find_email_pd_output = new JTextField(1000);
		find_email_pd_output.setEditable(false);
		find_email_pd_output.setBounds(252, 78, 499, 21);
		desktopPane_1.add(find_email_pd_output);
		
		JButton find_phone_pw = new JButton("得到手机code");
		find_phone_pw.setBounds(46, 119, 117, 23);
		desktopPane_1.add(find_phone_pw);
		find_phone_pw.addActionListener(new findPhonePW());
		
		JLabel label_2 = new JLabel("输入手机");
		label_2.setBounds(194, 123, 61, 15);
		desktopPane_1.add(label_2);
		
		find_phone_pd_input = new JTextField();
		find_phone_pd_input.setColumns(10);
		find_phone_pd_input.setBounds(252, 120, 175, 21);
		desktopPane_1.add(find_phone_pd_input);
		
		JLabel label_4 = new JLabel("验证码");
		label_4.setBounds(451, 123, 45, 15);
		desktopPane_1.add(label_4);
		
		find_phone_pd_output = new JTextField();
		find_phone_pd_output.setEditable(false);
		find_phone_pd_output.setColumns(10);
		find_phone_pd_output.setBounds(494, 120, 108, 21);
		desktopPane_1.add(find_phone_pd_output);
		
		JEditorPane editorPane_1 = new JEditorPane();
		editorPane_1.setEditable(false);
		editorPane_1.setText("使用须知：\r\n1. 邮箱链接和手机验证码已绑定测试环境，线上\r\n不可用。");
		editorPane_1.setBackground(SystemColor.info);
		editorPane_1.setBounds(729, 10, 266, 59);
		desktopPane_1.add(editorPane_1);
		
		JDesktopPane desktopPane_2 = new JDesktopPane();
		tabbedPane_1.addTab("发布资源", null, desktopPane_2, null);
		desktopPane_2.setBackground(SystemColor.controlHighlight);
		
		JLabel label = new JLabel("发布病例");
		label.setBounds(25, 35, 56, 15);
		desktopPane_2.add(label);
		
		JButton pub_case_test = new JButton("发布(测试环境)");
		pub_case_test.setBounds(103, 31, 125, 23);
		desktopPane_2.add(pub_case_test);
		pub_case_test.addActionListener(new pubCaseTest());
		
		JLabel label_3 = new JLabel("发布者账号");
		label_3.setBounds(263, 35, 66, 15);
		desktopPane_2.add(label_3);
		
		pub_case_user_test = new JTextField();
		pub_case_user_test.setColumns(10);
		pub_case_user_test.setBounds(339, 32, 171, 21);
		desktopPane_2.add(pub_case_user_test);
		
		JLabel label_5 = new JLabel("密  码");
		label_5.setBounds(535, 35, 36, 15);
		desktopPane_2.add(label_5);
		
		pub_case_pw_test = new JTextField();
		pub_case_pw_test.setColumns(10);
		pub_case_pw_test.setBounds(581, 32, 108, 21);
		desktopPane_2.add(pub_case_pw_test);
		
		JLabel label_7 = new JLabel("病例的主题");
		label_7.setBounds(263, 70, 66, 15);
		desktopPane_2.add(label_7);
		
		pub_case_title_test = new JTextField();
		pub_case_title_test.setEditable(false);
		pub_case_title_test.setColumns(10);
		pub_case_title_test.setBounds(339, 67, 372, 21);
		desktopPane_2.add(pub_case_title_test);
		
		JButton pub_case = new JButton("发布(线上环境)");
		pub_case.setEnabled(false);
		pub_case.setBounds(103, 105, 125, 23);
		desktopPane_2.add(pub_case);
		pub_case.addActionListener(new pubCase());
		
		JLabel label_8 = new JLabel("发布者账号");
		label_8.setBounds(263, 109, 66, 15);
		desktopPane_2.add(label_8);
		
		JLabel label_9 = new JLabel("密  码");
		label_9.setBounds(535, 109, 36, 15);
		desktopPane_2.add(label_9);
		
		pub_case_user = new JTextField();
		pub_case_user.setColumns(10);
		pub_case_user.setBounds(339, 106, 171, 21);
		desktopPane_2.add(pub_case_user);
		
		pub_case_pw = new JTextField();
		pub_case_pw.setColumns(10);
		pub_case_pw.setBounds(581, 106, 108, 21);
		desktopPane_2.add(pub_case_pw);
		
		JLabel label_10 = new JLabel("病例的主题");
		label_10.setBounds(263, 147, 66, 15);
		desktopPane_2.add(label_10);
		
		pub_case_title = new JTextField();
		pub_case_title.setEditable(false);
		pub_case_title.setColumns(10);
		pub_case_title.setBounds(339, 144, 372, 21);
		desktopPane_2.add(pub_case_title);
		
		JLabel label_11 = new JLabel("发布话题");
		label_11.setBounds(25, 223, 56, 15);
		desktopPane_2.add(label_11);
		
		JButton pub_topic_test = new JButton("发布(测试环境)");
		pub_topic_test.setBounds(102, 219, 126, 23);
		desktopPane_2.add(pub_topic_test);
		pub_topic_test.addActionListener(new pubTopicTest());
		
		JLabel label_12 = new JLabel("发布者账号");
		label_12.setBounds(263, 223, 66, 15);
		desktopPane_2.add(label_12);
		
		pub_topic_user_test = new JTextField();
		pub_topic_user_test.setColumns(10);
		pub_topic_user_test.setBounds(339, 220, 171, 21);
		desktopPane_2.add(pub_topic_user_test);
		
		JLabel label_13 = new JLabel("密  码");
		label_13.setBounds(535, 223, 36, 15);
		desktopPane_2.add(label_13);
		
		pub_topic_pw_test = new JTextField();
		pub_topic_pw_test.setColumns(10);
		pub_topic_pw_test.setBounds(581, 220, 108, 21);
		desktopPane_2.add(pub_topic_pw_test);
		
		JLabel label_14 = new JLabel("话题的主题");
		label_14.setBounds(263, 263, 72, 15);
		desktopPane_2.add(label_14);
		
		pub_topic_title_test = new JTextField();
		pub_topic_title_test.setEditable(false);
		pub_topic_title_test.setColumns(10);
		pub_topic_title_test.setBounds(339, 260, 372, 21);
		desktopPane_2.add(pub_topic_title_test);
		
		JButton pub_topic = new JButton("发布(线上环境)");
		pub_topic.setEnabled(false);
		pub_topic.setBounds(105, 304, 123, 23);
		desktopPane_2.add(pub_topic);
		pub_topic.addActionListener(new pubTopic());
		
		JLabel label_15 = new JLabel("发布者账号");
		label_15.setBounds(263, 308, 66, 15);
		desktopPane_2.add(label_15);
		
		pub_topic_user = new JTextField();
		pub_topic_user.setColumns(10);
		pub_topic_user.setBounds(339, 305, 171, 21);
		desktopPane_2.add(pub_topic_user);
		
		JLabel label_16 = new JLabel("密  码");
		label_16.setBounds(535, 308, 36, 15);
		desktopPane_2.add(label_16);
		
		pub_topic_pw = new JTextField();
		pub_topic_pw.setColumns(10);
		pub_topic_pw.setBounds(581, 305, 108, 21);
		desktopPane_2.add(pub_topic_pw);
		
		JLabel label_17 = new JLabel("话题的主题");
		label_17.setBounds(263, 349, 72, 15);
		desktopPane_2.add(label_17);
		
		pub_topic_title = new JTextField();
		pub_topic_title.setEditable(false);
		pub_topic_title.setColumns(10);
		pub_topic_title.setBounds(339, 346, 372, 21);
		desktopPane_2.add(pub_topic_title);
		
		JEditorPane editorPane = new JEditorPane();
		editorPane.setEditable(false);
		editorPane.setText("使用须知：\r\n1. 病例和话题的线上发布暂时不开放。");
		editorPane.setBackground(SystemColor.info);
		editorPane.setBounds(729, 35, 266, 59);
		desktopPane_2.add(editorPane);
		
		JDesktopPane desktopPane_3 = new JDesktopPane();
		tabbedPane_1.addTab("辅助工具", null, desktopPane_3, null);
		desktopPane_3.setBackground(SystemColor.controlHighlight);
		
		JLabel label_18 = new JLabel("安装app");
		label_18.setBounds(21, 138, 54, 15);
		desktopPane_3.add(label_18);
		
		JButton install_apk = new JButton("开始安装");
		install_apk.setBounds(85, 134, 93, 23);
		desktopPane_3.add(install_apk);
		install_apk.addActionListener(new installAPK());
		
		apk_path = new JTextField();
		apk_path.setColumns(10);
		apk_path.setBounds(85, 167, 311, 21);
		desktopPane_3.add(apk_path);
		
		JButton choose_file = new JButton("添加安装包");
		choose_file.setBounds(406, 166, 103, 23);
		desktopPane_3.add(choose_file);
		choose_file.addActionListener(new chooseFile());
		
		down_file_path = new JTextField();
		down_file_path.setColumns(10);
		down_file_path.setBounds(85, 81, 311, 21);
		desktopPane_3.add(down_file_path);
		
		JButton down_choose_path = new JButton("选择路径");
		down_choose_path.setBounds(406, 80, 93, 23);
		desktopPane_3.add(down_choose_path);
		down_choose_path.addActionListener(new downChooseFile());
		
		JButton download_file = new JButton("开始下载");
		download_file.setBounds(85, 48, 93, 23);
		desktopPane_3.add(download_file);
		
		JEditorPane dtrpnapp = new JEditorPane();
		dtrpnapp.setEditable(false);
		dtrpnapp.setBackground(SystemColor.info);
		dtrpnapp.setText("使用须知：\r\n1. 下载app：先选择本地目录，点击开始下载，\r\n然后会去ftp服务器（192.168.1.37）下载最新的\r\n唯医安装包到选择的路径（根据最后修改时间）\r\n\r\n2. 安装app：前提需要USB连接设备到电脑，安\r\n装手机驱动并已开启USB调试模式。然后添加安\r\n装包即可安装。");
		dtrpnapp.setBounds(717, 24, 266, 149);
		desktopPane_3.add(dtrpnapp);
		
		JLabel lblNewLabel = new JLabel("下载唯医app");
		lblNewLabel.setToolTipText("\r\n");
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel.setBounds(0, 52, 77, 23);
		desktopPane_3.add(lblNewLabel);
		contentPane.setLayout(gl_contentPane);
		download_file.addActionListener(new downFile());
		
	}
	
	private class findEmailPW implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        	String url = GetResetPasswd.resGetEmail(find_email_pd_input.getText());
        	if (url != null) {
        		find_email_pd_output.setText(url);
        		JOptionPane.showMessageDialog(frame, "得到重置密码连接");
        	} else {
        		JOptionPane.showMessageDialog(frame, "sorry, 链接未获取到！");
        	}
        }
	}
	
	private class findPhonePW implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        	String url = GetResetPasswd.resGetPhone(find_phone_pd_input.getText());
        	if (url != null) {
        		find_phone_pd_output.setText(url);
        		JOptionPane.showMessageDialog(frame, "已得到手机验证码");
        	} else {
        		JOptionPane.showMessageDialog(frame, "sorry, 手机验证码未获取到！");
        	}
        }
	}
	
	private class createEmailUser implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        	Object alert = CreateAllinUser.createEmailUser(create_email_user_input.getText(), create_email_pw_input.getText());
   
        	JOptionPane.showMessageDialog(frame, alert.toString());
        }
	}
	
	private class createPhoneUser implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        	Object alert = CreateAllinUser.createPhoneUser(create_phone_user_input.getText(), create_phone_pw_input.getText());
   
        	JOptionPane.showMessageDialog(frame, alert.toString());
        }
	}
	
	private class createRanUser implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        	String email = RandomStr.randomEmail();
        	Object alert = CreateAllinUser.createEmailUser(email, "111111");
        	if (alert.toString().equals("邮箱已经存在!")) {
        		CreateAllinUser.createEmailUser(RandomStr.randomEmail(), "111111");
        	}       	
        	JOptionPane.showMessageDialog(frame, alert.toString());       	
        	create_ran_user_input.setText(email);
        	create_ran_pw_input.setText("111111");
        }
	}
	
	private class pubCase implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        	pub_case_title.setText("");
    		String type = null;
        	if (pub_case_user.getText().contains("@")) {
    			type = "email";
    		} else {
    			type = "mobile";
    		}    		
        	HttpClientLogin.httpLogin(pub_case_user.getText(), pub_case_pw.getText(), type, "www.allinmd.cn");
        	if (HttpClientLogin.loginStatus.toString().equals("false")) {
        		JOptionPane.showMessageDialog(frame, "登录失败，用户名或密码错误！");
        	} else {
            	PubCase.createCase("www.allinmd.cn");
        		if (PubCase.status.toString().equals("true")) {
        			pub_case_title.setText(PubCase.caseName);
        			JOptionPane.showMessageDialog(frame, "发布成功");  
        		}
        	} 
        }
	}
	
	private class pubCaseTest implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        	pub_case_title_test.setText("");
    		String type = null;
        	if (pub_case_user_test.getText().contains("@")) {
    			type = "email";
    		} else {
    			type = "mobile";
    		}    		
        	HttpClientLogin.httpLogin(pub_case_user_test.getText(), pub_case_pw_test.getText(), type, "192.168.1.41");
        	if (HttpClientLogin.loginStatus.toString().equals("false")) {
        		JOptionPane.showMessageDialog(frame, "登录失败，用户名或密码错误！");
        	} else {
            	PubCase.createCase("192.168.1.41");
        		if (PubCase.status.toString().equals("true")) {
        			pub_case_title_test.setText(PubCase.caseName);
        			JOptionPane.showMessageDialog(frame, "发布成功");  
        		}
        	} 
        }
	}
	
	private class pubTopic implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        	pub_topic_title.setText("");
    		String type = null;
        	if (pub_topic_user.getText().contains("@")) {
    			type = "email";
    		} else {
    			type = "mobile";
    		}    		
        	HttpClientLogin.httpLogin(pub_topic_user.getText(), pub_topic_pw.getText(), type, "www.allinmd.cn");
        	if (HttpClientLogin.loginStatus.toString().equals("false")) {
        		JOptionPane.showMessageDialog(frame, "登录失败，用户名或密码错误！");
        	} else {
        		PubTopic.createTopic("www.allinmd.cn");
        		if (PubTopic.status.toString().equals("true")) {
        			pub_topic_title.setText(PubTopic.topicName);
        			JOptionPane.showMessageDialog(frame, "发布成功");  
        		}
        	} 
        }
	}
	
	private class pubTopicTest implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        	pub_topic_title_test.setText("");
    		String type = null;
        	if (pub_topic_user_test.getText().contains("@")) {
    			type = "email";
    		} else {
    			type = "mobile";
    		}    		
        	HttpClientLogin.httpLogin(pub_topic_user_test.getText(), pub_topic_pw_test.getText(), type, "192.168.1.41");
        	if (HttpClientLogin.loginStatus.toString().equals("false")) {
        		JOptionPane.showMessageDialog(frame, "登录失败，用户名或密码错误！");
        	} else {
        		PubTopic.createTopic("192.168.1.41");
        		if (PubTopic.status.toString().equals("true")) {
        			pub_topic_title_test.setText(PubTopic.topicName);
        			JOptionPane.showMessageDialog(frame, "发布成功");  
        		}
        	} 
        }
	}
	
	private class chooseFile implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String fileName = null;
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES );
			fileChooser.showDialog(new JLabel(), "选择文件");
			File file = fileChooser.getSelectedFile();
			fileName = file.getName();
			if(file.isDirectory()) {
//				System.out.println("文件夹:"+file.getAbsolutePath());
				JOptionPane.showMessageDialog(frame, "请选择一个apk文件");
			} else if(fileName.substring(fileName.lastIndexOf(".") + 1).equals("apk") == false) {
				JOptionPane.showMessageDialog(frame, "请选择一个apk文件");
			} else {
				apk_path.setText(file.getAbsolutePath());
//				System.out.println("文件:"+file.getAbsolutePath());
			}		
		}
		
	}
	
	private class installAPK implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (apk_path.getText().isEmpty())
				JOptionPane.showMessageDialog(frame, "请选择一个apk文件");
			else {
				String status = AdbMethod.adbInstall(apk_path.getText());
				JOptionPane.showMessageDialog(frame, status);
			}
		}
		
	}
	
	private class createCAOSUser implements ActionListener {
		@Override
        public void actionPerformed(ActionEvent e) {
        	Object alert = CreateCAOSUser.createEmailUser(create_caos_email_user_input.getText(), create_caos_email_pw_input.getText());
        	System.out.println(alert.toString());
        	JOptionPane.showMessageDialog(frame, alert.toString());
        }
		
	}
	
	private class createRanCAOSUser implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        	String email = RandomStr.randomEmail();
        	Object alert = CreateCAOSUser.createEmailUser(email, "111111");
        	if (alert.toString().equals("账号已经存在！")) {
        		CreateAllinUser.createEmailUser(RandomStr.randomEmail(), "111111");
        	}       	
        	JOptionPane.showMessageDialog(frame, alert.toString());       	
        	create_ran_caos_user_input.setText(email);
        	create_ran_caos_pw_input.setText("111111");
        }	
	}
	
	private class downChooseFile implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES );
			fileChooser.showDialog(new JLabel(), "选择一个目录");
			File file = fileChooser.getSelectedFile();			
			if(file.isFile()) {
//				System.out.println("文件夹:"+file.getAbsolutePath());
				JOptionPane.showMessageDialog(frame, "请选择一个目录");
			} else {
				down_file_path.setText(file.getAbsolutePath());
			}
		}
		
	}
	
	private class downFile implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			boolean flag = FtpApcheDownFile.testDownFile(down_file_path.getText());
			if (flag)
				JOptionPane.showMessageDialog(frame, FtpApcheDownFile.maxfile + "下载成功！");
			else
				JOptionPane.showMessageDialog(frame, "文件下载失败！");
		}
		
	}
}
