package com.situ.student.view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.situ.student.biz.StudentManager;
import com.situ.student.entity.Student;
import com.situ.student.model.StudentTableModel;
import com.situ.student.util.CallBack;

public class StudentFrame {
	List<Student> list;
	StudentTableModel studentTableModel;
	StudentManager studentManager = new StudentManager();
	JTextField nameTextField;
	JTextField sexTextField;
	JTextField ageTextField;
	JTable table;

	public void init() {
		// 新建学生信息管理系统主界面，并建立主面板，设置为纵向流布局
		JFrame frame = new JFrame();
		frame.setTitle("欢迎进入学生信息管理系统");
		frame.setSize(600, 500);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel mainPanel = (JPanel) frame.getContentPane();
		mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		BoxLayout boxLayout = new BoxLayout(mainPanel, BoxLayout.Y_AXIS);
		mainPanel.setLayout(boxLayout);

		// 新建3个子面板，并添加到主面板
		JPanel panel1 = new JPanel();
		panel1.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
		JLabel nameLabel = new JLabel();
		nameLabel.setText("姓名");
		panel1.add(nameLabel);
		nameTextField = new JTextField();
		nameTextField.setPreferredSize(new Dimension(90, 30));
		panel1.add(nameTextField);
		JLabel sexLabel = new JLabel();
		sexLabel.setText("性别");
		panel1.add(sexLabel);
		sexTextField = new JTextField();
		sexTextField.setPreferredSize(new Dimension(90, 30));
		panel1.add(sexTextField);
		JLabel ageLabel = new JLabel();
		ageLabel.setText("年龄");
		panel1.add(ageLabel);
		ageTextField = new JTextField();
		ageTextField.setPreferredSize(new Dimension(90, 30));
		panel1.add(ageTextField);

		JButton searchButton = new JButton();
		searchButton.setText("查找");
		searchButton.setPreferredSize(new Dimension(60, 30));
		searchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				searchStudent();
			}
		});
		panel1.add(searchButton);
		mainPanel.add(panel1);

		JPanel panel2 = new JPanel();
		list = new ArrayList<Student>();
		list = studentManager.load();
		studentTableModel = new StudentTableModel(list);
		table = new JTable(studentTableModel);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setPreferredSize(new Dimension(500, 300));
		panel2.add(scrollPane);
		mainPanel.add(panel2);

		JPanel panel3 = new JPanel();
		panel3.setLayout(new FlowLayout(FlowLayout.CENTER, 80, 20));
		JButton addButton = new JButton();
		addButton.setText("新增");
		addButton.setPreferredSize(new Dimension(60, 30));
		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AddFrame addFrame = new AddFrame(new CallBack() {
					@Override
					public void callBack() {
						refreshFrame();
					}
				});
				addFrame.add();
			}
		});
		panel3.add(addButton);

		JButton modifyButton = new JButton();
		modifyButton.setText("修改");
		modifyButton.setPreferredSize(new Dimension(60, 30));
		modifyButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int index = table.getSelectedRow();
				if (index > -1) {
					ModifyFrame modifyFrame = new ModifyFrame(new CallBack() {
						@Override
						public void callBack() {
							refreshFrame();
						}
					}, index);
					modifyFrame.modify();
				} else {
					JOptionPane.showMessageDialog(null, "请选中要修改的学生！");
				}
			}
		});
		panel3.add(modifyButton);

		JButton deleteButton = new JButton();
		deleteButton.setText("删除");
		deleteButton.setPreferredSize(new Dimension(60, 30));
		deleteButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int index = table.getSelectedRow();
				if (index > -1) {
					int result = JOptionPane.showConfirmDialog(null,
							"是否要删除记录?", "标题YES_NO_OPTION", 0);
					if (result == 0) {
						list.remove(index);
						studentManager.save(list);
						refreshFrame();
					}
				} else {
					JOptionPane.showMessageDialog(null, "请选中要删除的学生！");
				}
			}
		});
		panel3.add(deleteButton);
		mainPanel.add(panel3);

		frame.setVisible(true);
	}

	/**
	 * 回调函数时使用，用于刷新主面板上的内容
	 */
	public void refreshFrame() {
		list = studentManager.load();
		studentTableModel.setData(list);
	}

	/**
	 * 查询学生信息：根据姓名或性别或年龄查询，若三个都为空时，查询全部学生的信息
	 */
	public void searchStudent() {
		List<Student> searchList = new ArrayList<Student>();
		Student student = new Student();
		boolean isName = nameTextField.getText().equals("");
		boolean isSex = sexTextField.getText().equals("");
		boolean isAge = ageTextField.getText().equals("");
		if (!isName && isSex && isAge) {
			boolean isSearchByName = false;
			for (int i = 0; i < list.size(); i++) {
				if (nameTextField.getText().equals(list.get(i).getName())) {
					student = list.get(i);
					searchList.add(student);
					isSearchByName = true;
				}
			}
			if (!isSearchByName) {
				JOptionPane.showMessageDialog(null, "没有找到该学生！");
			} else {
				studentTableModel.setData(searchList);
			}
		} else if (isName && !isSex && isAge) {
			boolean isSearchBySex = false;
			for (int i = 0; i < list.size(); i++) {
				if (sexTextField.getText().equals(list.get(i).getSex())) {
					student = list.get(i);
					searchList.add(student);
					isSearchBySex = true;
				}
			}
			if (!isSearchBySex) {
				JOptionPane.showMessageDialog(null, "没有找到该学生！");
			} else {
				studentTableModel.setData(searchList);
			}
		} else if (isName && isSex && !isAge) {
			boolean isSearchByAge = false;
			for (int i = 0; i < list.size(); i++) {
				if (Integer.parseInt(ageTextField.getText()) == list.get(i)
						.getAge()) {
					student = list.get(i);
					searchList.add(student);
					isSearchByAge = true;
				}
			}
			if (!isSearchByAge) {
				JOptionPane.showMessageDialog(null, "没有找到该学生！");
			} else {
				studentTableModel.setData(searchList);
			}
		} else if (isName && isSex && isAge) {
			refreshFrame();
		}
	}

	public static void main(String[] args) {
		StudentFrame studentFrame = new StudentFrame();
		studentFrame.init();

	}
}
