package com.sun.demo;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.Font;
import java.awt.Color;
import java.io.File;

import javax.swing.JTextArea;
import javax.swing.JTable;

public class TestTools2 extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static JFrame frame;
	private JTextField find_email_pd_input;
	private JTextField find_phone_pd_input;
	private JTextField find_email_pd_output;
	private JTextField find_phone_pd_output;
	private JTextField create_email_user_input;
	private JTextField create_email_pw_input;
	private JTextField create_phone_user_input;
	private JTextField create_phone_pw_input;
	private JTextField create_ran_user_input;
	private JTextField create_ran_pw_input;
	private JTextField pub_case_user_test;
	private JTextField pub_case_pw_test;
	private JTextField pub_case_title_test;
	private JTextField pub_topic_user_test;
	private JTextField pub_topic_pw_test;
	private JTextField pub_topic_title_test;
	private JTextField pub_case_user;
	private JTextField pub_case_pw;
	private JTextField pub_case_title;
	private JTextField pub_topic_user;
	private JTextField pub_topic_pw;
	private JTextField pub_topic_title;
	private JTextField apk_path;
	private JTextField create_caos_email_user_input;
	private JTextField create_caos_email_pw_input;
	private JTable table;
	private JTextField create_ran_caos_user_input;
	private JTextField create_ran_caos_pw_input;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new TestTools2();
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
	public TestTools2() {
		setForeground(Color.LIGHT_GRAY);
		setFont(new Font("Dialog", Font.BOLD, 13));
		setTitle("生成测试数据");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1040, 1001);
		contentPane = new JPanel();
		contentPane.setToolTipText("");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton find_email_pw = new JButton("得到邮箱重置密码链接");
		find_email_pw.addActionListener(new findEmailPW());
		
		JLabel label_1 = new JLabel("注册唯医");
		
		JButton create_email_user = new JButton("生成邮箱账号");
		create_email_user.addActionListener(new createEmailUser());
		
		JButton create_phone_user = new JButton("生成手机账号");
		create_phone_user.addActionListener(new createPhoneUser());
		
		JButton find_phone_pw = new JButton("得到手机验证码");
		find_phone_pw.addActionListener(new findPhonePW());
		
		find_email_pd_input = new JTextField();
		find_email_pd_input.setColumns(10);
		
		JLabel label_2 = new JLabel("输入邮箱");
		
		JLabel label_3 = new JLabel("输入手机号");
		
		find_phone_pd_input = new JTextField();
		find_phone_pd_input.setColumns(10);
		
		JLabel label_4 = new JLabel("链         接");		
		find_email_pd_output = new JTextField(1000);
		find_email_pd_output.setColumns(1000);
		find_email_pd_output.setEditable(false);
		
		JLabel label_5 = new JLabel("验证码");		
		find_phone_pd_output = new JTextField();
		find_phone_pd_output.setColumns(10);
		find_phone_pd_output.setEditable(false);
		
		JLabel label_6 = new JLabel("输入邮箱");
		
		create_email_user_input = new JTextField();
		create_email_user_input.setColumns(10);
		
		JLabel label_7 = new JLabel("密  码");
		
		create_email_pw_input = new JTextField();
		create_email_pw_input.setColumns(10);
		
		JLabel label_8 = new JLabel("输入手机");
		
		create_phone_user_input = new JTextField();
		create_phone_user_input.setColumns(10);
		
		JLabel label_9 = new JLabel("密  码");
		
		create_phone_pw_input = new JTextField();
		create_phone_pw_input.setColumns(10);
		
		JButton create_ran_user = new JButton("生成随机账号");
		create_ran_user.addActionListener(new createRanUser());
		
		JLabel label_10 = new JLabel("用  户  名");		
		create_ran_user_input = new JTextField();
		create_ran_user_input.setColumns(10);
		create_ran_user_input.setEditable(false);
		
		JLabel label_11 = new JLabel("密  码");		
		create_ran_pw_input = new JTextField();
		create_ran_pw_input.setColumns(10);
		create_ran_pw_input.setEditable(false);
		
		JLabel label_12 = new JLabel("发布病例");
		
		JButton pub_case_test = new JButton("发布(测试环境)");
		pub_case_test.addActionListener(new pubCaseTest());
		
		JLabel label_13 = new JLabel("发布者账号");
		
		pub_case_user_test = new JTextField();
		pub_case_user_test.setColumns(10);
		
		JLabel label = new JLabel("密  码");
		
		pub_case_pw_test = new JTextField();
		pub_case_pw_test.setColumns(10);
		
		JLabel label_16 = new JLabel("病例的主题");
		
		pub_case_title_test = new JTextField();
		pub_case_title_test.setColumns(10);
		pub_case_title_test.setEditable(false);
		
		JLabel pub = new JLabel("发布话题");
		
		JButton pub_topic_test = new JButton("发布(测试环境)");
		pub_topic_test.addActionListener(new pubTopicTest());
		
		JLabel label_15 = new JLabel("发布者账号");
		
		pub_topic_user_test = new JTextField();
		pub_topic_user_test.setColumns(10);
		
		JLabel label_17 = new JLabel("密  码");
		
		pub_topic_pw_test = new JTextField();
		pub_topic_pw_test.setColumns(10);
		
		JLabel label_18 = new JLabel("话题的主题");
		
		pub_topic_title_test = new JTextField();
		pub_topic_title_test.setEditable(false);
		pub_topic_title_test.setColumns(10);
		
		JButton pub_case = new JButton("发布(线上环境)");
		pub_case.addActionListener(new pubCase());
		pub_case.setEnabled(false);
		
		JLabel label_14 = new JLabel("发布者账号");
		
		pub_case_user = new JTextField();
		pub_case_user.setColumns(10);
		
		JLabel label_19 = new JLabel("密  码");
		
		pub_case_pw = new JTextField();
		pub_case_pw.setColumns(10);
		
		JLabel label_20 = new JLabel("病例的主题");
		
		pub_case_title = new JTextField();
		pub_case_title.setEditable(false);
		pub_case_title.setColumns(10);
		
		JButton pub_topic = new JButton("发布(线上环境)");
		pub_topic.addActionListener(new pubTopic());
		pub_topic.setEnabled(false);
		
		JLabel label_22 = new JLabel("发布者账号");
		
		pub_topic_user = new JTextField();
		pub_topic_user.setColumns(10);
		
		JLabel label_23 = new JLabel("密  码");
		
		pub_topic_pw = new JTextField();
		pub_topic_pw.setColumns(10);
		
		JLabel label_24 = new JLabel("话题的主题");
		
		pub_topic_title = new JTextField();
		pub_topic_title.setEditable(false);
		pub_topic_title.setColumns(10);
		
		JTextArea textArea = new JTextArea();
		textArea.setText("使用须知：\r\n1.邮箱链接和手机验证码已绑定测试环境，线上\r\n不可用。\r\n\r\n2.注册唯医和CAOS未绑定环境，根据本地hosts & \r\nDNS配置决定是测试还是线上，自定义注册请按规\r\n则注册。\r\n邮箱：3个字符+ypg70+2个字符+合法后缀\r\n手机：177000+5位数字\r\n\r\n3.病例和话题的线上发布暂时不开放。\r\n\r\n4.安装app前提需要USB连接设备到电脑，并已开\r\n启USB调试模式。然后添加安装包即可安装。\r\n");
		textArea.setEditable(false);
		
		JButton install_apk = new JButton("开始安装");
		install_apk.addActionListener(new installAPK());
		
		apk_path = new JTextField();
		apk_path.setColumns(10);
		
		JButton choose_file = new JButton("添加安装包");
		choose_file.addActionListener(new chooseFile());
		
		JLabel lblapp = new JLabel("安装app");
		
		JLabel lblNewLabel = new JLabel("注册CAOS");
		
		JButton create_caos_email_user = new JButton("生成邮箱账号");
		create_caos_email_user.addActionListener(new createCAOSUser());
		
		JLabel label_21 = new JLabel("输入邮箱");
		
		create_caos_email_user_input = new JTextField();
		create_caos_email_user_input.setColumns(10);
		
		JLabel label_25 = new JLabel("密  码");
		
		create_caos_email_pw_input = new JTextField();
		create_caos_email_pw_input.setColumns(10);
		
		table = new JTable();
		
		JButton create_ran_caos_user = new JButton("生成随机账号");
		create_ran_caos_user.addActionListener(new createRanCAOSUser());
		
		JLabel label_26 = new JLabel("用 户 名");
		
		create_ran_caos_user_input = new JTextField();
		create_ran_caos_user_input.setEditable(false);
		create_ran_caos_user_input.setColumns(10);
		
		JLabel label_27 = new JLabel("密  码");
		
		create_ran_caos_pw_input = new JTextField();
		create_ran_caos_pw_input.setEditable(false);
		create_ran_caos_pw_input.setColumns(10);
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(32)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(find_email_pw)
									.addGap(18)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(label_2)
										.addComponent(label_4, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(find_email_pd_input, GroupLayout.PREFERRED_SIZE, 182, GroupLayout.PREFERRED_SIZE)
										.addComponent(find_email_pd_output, GroupLayout.PREFERRED_SIZE, 533, GroupLayout.PREFERRED_SIZE)))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(pub, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(pub_topic, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)
										.addComponent(pub_topic_test, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE))))
							.addContainerGap(213, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(find_phone_pw)
									.addGap(18)
									.addComponent(label_3, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(find_phone_pd_input, GroupLayout.PREFERRED_SIZE, 175, GroupLayout.PREFERRED_SIZE)
									.addGap(47)
									.addComponent(label_5, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(find_phone_pd_output, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
												.addComponent(lblNewLabel)
												.addComponent(lblapp))
											.addGap(16))
										.addGroup(gl_contentPane.createSequentialGroup()
											.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
												.addComponent(label_12)
												.addComponent(label_1))
											.addPreferredGap(ComponentPlacement.UNRELATED)))
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
												.addGroup(gl_contentPane.createSequentialGroup()
													.addComponent(create_ran_user)
													.addGap(18)
													.addComponent(label_10, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
													.addPreferredGap(ComponentPlacement.UNRELATED)
													.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
														.addGroup(gl_contentPane.createSequentialGroup()
															.addComponent(create_ran_user_input, GroupLayout.PREFERRED_SIZE, 171, GroupLayout.PREFERRED_SIZE)
															.addGap(18)
															.addComponent(label_11, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE))
														.addGroup(gl_contentPane.createSequentialGroup()
															.addComponent(pub_case_user_test, GroupLayout.PREFERRED_SIZE, 171, GroupLayout.PREFERRED_SIZE)
															.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
															.addComponent(label)))
													.addPreferredGap(ComponentPlacement.RELATED)
													.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
														.addComponent(pub_case_pw_test, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)
														.addComponent(create_ran_pw_input, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)))
												.addGroup(gl_contentPane.createSequentialGroup()
													.addComponent(create_phone_user)
													.addGap(18)
													.addComponent(label_8, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
													.addPreferredGap(ComponentPlacement.UNRELATED)
													.addComponent(create_phone_user_input, GroupLayout.PREFERRED_SIZE, 171, GroupLayout.PREFERRED_SIZE)
													.addGap(18)
													.addComponent(label_9, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
													.addPreferredGap(ComponentPlacement.RELATED)
													.addComponent(create_phone_pw_input, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE))
												.addGroup(gl_contentPane.createSequentialGroup()
													.addComponent(create_email_user)
													.addPreferredGap(ComponentPlacement.RELATED)
													.addComponent(table, GroupLayout.PREFERRED_SIZE, 1, GroupLayout.PREFERRED_SIZE)
													.addPreferredGap(ComponentPlacement.UNRELATED)
													.addComponent(label_6, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
													.addPreferredGap(ComponentPlacement.RELATED)
													.addComponent(create_email_user_input, GroupLayout.PREFERRED_SIZE, 171, GroupLayout.PREFERRED_SIZE)
													.addGap(18)
													.addComponent(label_7, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
													.addPreferredGap(ComponentPlacement.UNRELATED)
													.addComponent(create_email_pw_input, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)))
											.addGap(33))
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
											.addGroup(gl_contentPane.createSequentialGroup()
												.addPreferredGap(ComponentPlacement.RELATED)
												.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
													.addComponent(pub_case_test, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
													.addGroup(gl_contentPane.createSequentialGroup()
														.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
															.addComponent(install_apk)
															.addComponent(create_caos_email_user))
														.addGap(18))
													.addGroup(gl_contentPane.createSequentialGroup()
														.addComponent(create_ran_caos_user, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
														.addGap(18)))
												.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
													.addGroup(gl_contentPane.createSequentialGroup()
														.addPreferredGap(ComponentPlacement.UNRELATED)
														.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
															.addGroup(gl_contentPane.createSequentialGroup()
																.addComponent(label_16)
																.addPreferredGap(ComponentPlacement.RELATED)
																.addComponent(pub_case_title_test, 372, 372, 372))
															.addComponent(label_13, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
															.addGroup(gl_contentPane.createSequentialGroup()
																.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
																	.addComponent(label_15, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
																	.addComponent(label_18, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE))
																.addPreferredGap(ComponentPlacement.RELATED)
																.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
																	.addComponent(pub_topic_title_test, GroupLayout.PREFERRED_SIZE, 351, GroupLayout.PREFERRED_SIZE)
																	.addGroup(gl_contentPane.createSequentialGroup()
																		.addComponent(pub_topic_user_test, GroupLayout.PREFERRED_SIZE, 171, GroupLayout.PREFERRED_SIZE)
																		.addGap(18)
																		.addComponent(label_17, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(ComponentPlacement.RELATED)
																		.addComponent(pub_topic_pw_test, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE))))
															.addGroup(gl_contentPane.createSequentialGroup()
																.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
																	.addComponent(label_22, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
																	.addComponent(label_24, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE))
																.addPreferredGap(ComponentPlacement.RELATED)
																.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
																	.addComponent(pub_topic_title, GroupLayout.PREFERRED_SIZE, 351, GroupLayout.PREFERRED_SIZE)
																	.addGroup(gl_contentPane.createSequentialGroup()
																		.addComponent(pub_topic_user, GroupLayout.PREFERRED_SIZE, 171, GroupLayout.PREFERRED_SIZE)
																		.addGap(24)
																		.addComponent(label_23, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(ComponentPlacement.RELATED)
																		.addComponent(pub_topic_pw, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE))))
															.addGroup(gl_contentPane.createSequentialGroup()
																.addComponent(apk_path, GroupLayout.DEFAULT_SIZE, 311, Short.MAX_VALUE)
																.addPreferredGap(ComponentPlacement.RELATED)
																.addComponent(choose_file)
																.addGap(28))))
													.addGroup(gl_contentPane.createSequentialGroup()
														.addGap(1)
														.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
															.addGroup(gl_contentPane.createSequentialGroup()
																.addComponent(label_21, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(ComponentPlacement.RELATED)
																.addComponent(create_caos_email_user_input, GroupLayout.PREFERRED_SIZE, 171, GroupLayout.PREFERRED_SIZE))
															.addGroup(gl_contentPane.createSequentialGroup()
																.addComponent(label_26, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(ComponentPlacement.RELATED)
																.addComponent(create_ran_caos_user_input, GroupLayout.PREFERRED_SIZE, 171, GroupLayout.PREFERRED_SIZE)))
														.addGap(37)
														.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
															.addGroup(gl_contentPane.createSequentialGroup()
																.addComponent(label_25, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(ComponentPlacement.RELATED)
																.addComponent(create_caos_email_pw_input, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE))
															.addGroup(gl_contentPane.createSequentialGroup()
																.addComponent(label_27, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(ComponentPlacement.RELATED)
																.addComponent(create_ran_caos_pw_input, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE))))))
											.addGroup(gl_contentPane.createSequentialGroup()
												.addComponent(pub_case, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.UNRELATED)
												.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
													.addGroup(gl_contentPane.createSequentialGroup()
														.addComponent(label_20, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(ComponentPlacement.RELATED)
														.addComponent(pub_case_title, GroupLayout.PREFERRED_SIZE, 363, GroupLayout.PREFERRED_SIZE))
													.addGroup(gl_contentPane.createSequentialGroup()
														.addComponent(label_14, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(ComponentPlacement.RELATED)
														.addComponent(pub_case_user, GroupLayout.PREFERRED_SIZE, 171, GroupLayout.PREFERRED_SIZE)
														.addGap(18)
														.addComponent(label_19, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(ComponentPlacement.RELATED)
														.addComponent(pub_case_pw, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE))))))))
							.addGap(29)
							.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 290, GroupLayout.PREFERRED_SIZE)
							.addGap(18))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(43)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(find_email_pw)
						.addComponent(label_2)
						.addComponent(find_email_pd_input, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(find_email_pd_output, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_4))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(21)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(find_phone_pw)
								.addComponent(label_3)
								.addComponent(find_phone_pd_input, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(label_5)
								.addComponent(find_phone_pd_output, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(31)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(label_1)
								.addComponent(create_email_user)
								.addComponent(table, GroupLayout.PREFERRED_SIZE, 1, GroupLayout.PREFERRED_SIZE)
								.addComponent(label_6)
								.addComponent(create_email_user_input, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(label_7)
								.addComponent(create_email_pw_input, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(create_phone_user)
								.addComponent(label_8)
								.addComponent(create_phone_pw_input, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(label_9)
								.addComponent(create_phone_user_input, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(create_ran_user)
								.addComponent(label_10)
								.addComponent(create_ran_pw_input, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(label_11)
								.addComponent(create_ran_user_input, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(40)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(pub_case_test)
								.addComponent(pub_case_pw_test, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(label_13)
								.addComponent(pub_case_user_test, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(label)
								.addComponent(label_12))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(label_16)
								.addComponent(pub_case_title_test, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(13)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(pub_case)
								.addComponent(label_14)
								.addComponent(pub_case_user, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(label_19)
								.addComponent(pub_case_pw, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(label_20)
								.addComponent(pub_case_title, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(35)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
									.addComponent(pub)
									.addComponent(pub_topic_test))
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
									.addComponent(label_15)
									.addComponent(pub_topic_user_test, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
									.addComponent(label_17)
									.addComponent(pub_topic_pw_test, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(label_18)
								.addComponent(pub_topic_title_test, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(30)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(label_22)
								.addComponent(pub_topic_user, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(pub_topic_pw, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(label_23)
								.addComponent(pub_topic))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(label_24)
								.addComponent(pub_topic_title, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(37)
							.addComponent(textArea, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(42)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(install_apk)
						.addComponent(apk_path, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblapp)
						.addComponent(choose_file))
					.addGap(48)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(create_caos_email_user)
						.addComponent(label_21)
						.addComponent(create_caos_email_user_input, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_25)
						.addComponent(create_caos_email_pw_input, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_26)
						.addComponent(create_ran_caos_user)
						.addComponent(create_ran_caos_user_input, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_27)
						.addComponent(create_ran_caos_pw_input, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(169))
		);
		contentPane.setLayout(gl_contentPane);
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
}
