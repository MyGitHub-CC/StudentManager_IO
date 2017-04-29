package com.situ.student.view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.situ.student.biz.StudentManager;
import com.situ.student.entity.Student;
import com.situ.student.util.CallBack;
/**
 * 点击新增按钮时弹出“新增学生”窗口，录入完成后，点击保存，自动存入文件并通知StudentFrame刷新主窗口
 * @author Administrator
 */
public class AddFrame {
	List<Student> list;
	StudentManager studentManager = new StudentManager();
	JTextField nameTextField;
	JTextField sexTextField;
	JTextField ageTextField;

	CallBack callBack;

	public AddFrame(CallBack callBack) {
		this.callBack = callBack;
	}

	public void add() {
		// 新建一个窗口并建立主面板
		JFrame frame = new JFrame();
		frame.setTitle("添加学生");
		frame.setSize(350, 260);
		frame.setLocationRelativeTo(null);
		JPanel mainPanel = (JPanel) frame.getContentPane();
		mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		BoxLayout boxLayout = new BoxLayout(mainPanel, BoxLayout.Y_AXIS);
		mainPanel.setLayout(boxLayout);
		
		// 建立4个子面板，并添加到主面板
		JPanel panel1 = new JPanel();
		JLabel nameLabel = new JLabel();
		nameLabel.setText("姓名");
		panel1.add(nameLabel);
		nameTextField = new JTextField();
		nameTextField.setPreferredSize(new Dimension(90, 30));
		panel1.add(nameTextField);
		JPanel panel2 = new JPanel();
		JLabel sexLabel = new JLabel();
		sexLabel.setText("性别");
		panel2.add(sexLabel);
		sexTextField = new JTextField();
		sexTextField.setPreferredSize(new Dimension(90, 30));
		panel2.add(sexTextField);
		JPanel panel3 = new JPanel();
		JLabel ageLabel = new JLabel();
		ageLabel.setText("年龄");
		panel3.add(ageLabel);
		ageTextField = new JTextField();
		ageTextField.setPreferredSize(new Dimension(90, 30));
		panel3.add(ageTextField);
		JPanel panel4 = new JPanel();

		JButton saveButton = new JButton();
		saveButton.setText("保存");
		saveButton.setPreferredSize(new Dimension(60, 30));
		saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!(nameTextField.getText().equals("")
						|| sexTextField.getText().equals("") || ageTextField
						.getText().equals(""))) {
					Student student = new Student();
					student.setName(nameTextField.getText());
					student.setSex(sexTextField.getText());
					student.setAge(Integer.parseInt(ageTextField.getText()));
					list = studentManager.load(); // 从文件中加载最新的所有学生信息
					list.add(student); // 添加新增的学生并保存
					studentManager.save(list);
					callBack.callBack();
					// 点击保存后，清除文本框内容
					nameTextField.setText("");
					sexTextField.setText("");
					ageTextField.setText("");
				}
			}
		});
		panel4.add(saveButton);

		mainPanel.add(panel1);
		mainPanel.add(panel2);
		mainPanel.add(panel3);
		mainPanel.add(panel4);

		frame.setVisible(true);
	}
}
