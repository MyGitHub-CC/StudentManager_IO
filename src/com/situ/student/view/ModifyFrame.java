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
 * 点击修改按钮后，弹出“修改学生”窗口，修改完成后，点击保存，自动存入文件并通知StudentFrame刷新主窗口
 * @author Administrator
 */
public class ModifyFrame {
	List<Student> list;
	StudentManager studentManager = new StudentManager();
	JTextField nameTextField;
	JTextField sexTextField;
	JTextField ageTextField;
	Student student;
	
	int index; // 用于传入要修改的学生的索引
	CallBack callBack;
	public ModifyFrame(CallBack callBack, int index) {
		this.callBack = callBack;
		this.index = index;
	}
	
	public void modify() {
		list = studentManager.load();
		student = list.get(index);
		// 新建一个窗口并建立主面板
		JFrame frame = new JFrame();
		frame.setTitle("修改学生");
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
		nameTextField.setText(student.getName());
		nameTextField.setPreferredSize(new Dimension(90, 30));
		panel1.add(nameTextField);
		JPanel panel2 = new JPanel();
		JLabel sexLabel = new JLabel();
		sexLabel.setText("性别");
		panel2.add(sexLabel);
		sexTextField = new JTextField();
		sexTextField.setText(student.getSex());
		sexTextField.setPreferredSize(new Dimension(90, 30));
		panel2.add(sexTextField);
		JPanel panel3 = new JPanel();
		JLabel ageLabel = new JLabel();
		ageLabel.setText("年龄");
		panel3.add(ageLabel);
		ageTextField = new JTextField();
		ageTextField.setText(String.valueOf(student.getAge()));
		ageTextField.setPreferredSize(new Dimension(90, 30));
		panel3.add(ageTextField);
		JPanel panel4 = new JPanel();

		JButton saveButton = new JButton();
		saveButton.setText("保存");
		saveButton.setPreferredSize(new Dimension(60, 30));
		saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				student.setName(nameTextField.getText());
				student.setSex(sexTextField.getText());
				student.setAge(Integer.parseInt(ageTextField.getText()));
				list.set(index, student); // 根据传入的索引，将选中的学生的信息替换修改后的学生信息
				studentManager.save(list); // 将新的集合保存到文件
				callBack.callBack();
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
