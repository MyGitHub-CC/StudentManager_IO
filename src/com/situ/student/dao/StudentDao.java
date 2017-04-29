package com.situ.student.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import com.situ.student.entity.Student;

public class StudentDao {
	List<Student> list = new ArrayList<Student>();
	private static final String FILE = "F:/ppp.txt";

	public void save() {
		OutputStream outputStream = null;
		ObjectOutputStream objectOutputStream = null;
		try {
			outputStream = new FileOutputStream(FILE);
			objectOutputStream = new ObjectOutputStream(outputStream);
			objectOutputStream.writeObject(list);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (objectOutputStream != null) {
				try {
					objectOutputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (outputStream != null) {
				try {
					outputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	public List<Student> load() {
		File file = new File(FILE);
		if (file.exists()) {
			InputStream inputStream = null;
			ObjectInputStream objectInputStream = null;
			try {
				inputStream = new FileInputStream(FILE);
				objectInputStream = new ObjectInputStream(inputStream);
				list = (List<Student>) objectInputStream.readObject();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} finally {
				if (objectInputStream != null) {
					try {
						objectInputStream.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if (inputStream != null) {
					try {
						inputStream.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return list;
	}
}




// 从控制台进行增删改查
//public static void main(String[] args) {
//	StudentManager studentManager = new StudentManager();
//	studentManager.load();
//	while (true) {
//		System.out.println("----欢迎进入学生管理系统----");
//		System.out.println("请输入要进行的操作:");
//		System.out.println("1——添加指定学生信息");
//		System.out.println("2——删除指定学生信息");
//		System.out.println("3——修改指定学生信息");
//		System.out.println("4——查询指定学生信息");
//		System.out.println("5——查询所有学生信息");
//		System.out.println("0——退出");
//		int type = studentManager.scanner.nextInt();
//		if (type == 0) {
//			System.out.println("退出成功！");
//			break;
//		}
//		switch (type) {
//		case 1:
//			studentManager.add();
//			break;
//		case 2:
//			studentManager.delete();
//			break;
//		case 3:
//			studentManager.modify();
//			break;
//		case 4:
//			studentManager.find();
//			break;
//		case 5:
//			studentManager.findAll();
//			break;
//		default:
//			break;
//		}
//	}
//}
//public void findAll() {
//	for (int i = 0; i < list.size(); i++) {
//		System.out.println(list.get(i));
//	}
//}
//public void find() {
//	while (true) {
//		System.out.println("请输入查询方式：1-按姓名查找，2-按性别查找，3-按年龄查找，0-退出");
//		int type = scanner.nextInt();
//		if (type == 0) {
//			System.out.println("退出查找！");
//			break;
//		}
//		switch (type) {
//		case 1:
//			isSearchByName();
//			break;
//		case 2:
//			isSearchBySex();
//			break;
//		case 3:
//			isSearchByAge();
//			break;
//		default:
//			break;
//		}
//	}
//}
//public void isSearchByAge() {
//	System.out.println("请输入要查找的学生的年龄：");
//	int age = scanner.nextInt();
//	boolean isSearchByAgeFound = false;
//	for (int i = 0; i < list.size(); i++) {
//		if (list.get(i).getAge() == age) {
//			System.out.println(list.get(i));
//			isSearchByAgeFound = true;
//		}
//	}
//	if (!isSearchByAgeFound) {
//		System.out.println("没有找到该学生！");
//	}
//}
//public void isSearchBySex() {
//	System.out.println("请输入要查找的学生的性别：");
//	String sex = scanner.next();
//	boolean isSearchBySexFound = false;
//	for (int i = 0; i < list.size(); i++) {
//		if (list.get(i).getSex().equals(sex)) {
//			System.out.println(list.get(i));
//			isSearchBySexFound = true;
//		}
//	}
//	if (!isSearchBySexFound) {
//		System.out.println("没有找到该学生！");
//	}
//}
//public void isSearchByName() {
//	System.out.println("请输入要查找的学生的姓名：");
//	String name = scanner.next();
//	boolean isSearchByNameFound = false;
//	for (int i = 0; i < list.size(); i++) {
//		if (list.get(i).getName().equals(name)) {
//			System.out.println(list.get(i));
//			isSearchByNameFound = true;
//		}
//	}
//	if (!isSearchByNameFound) {
//		System.out.println("没有找到该学生！");
//	}
//}
//public void modify() {
//	System.out.println("请输入要修改的学生的姓名：");
//	String modifyName = scanner.next();
//	for (int i = 0; i < list.size(); i++) {
//		if (list.get(i) != null && list.get(i).getName().equals(modifyName)) {
//			System.out.println("请输入要修改的学生的姓名:");
//			String name = scanner.next();
//			System.out.println("请输入要修改的学生的性别:");
//			String sex = scanner.next();
//			System.out.println("请输入要修改的学生的年龄:");
//			int age = scanner.nextInt();
//			Student student = new Student(name, sex, age);
//			Collections.replaceAll(list, list.get(i), student);
//		}
//	}
//	save();
//}
//public void delete() {
//	System.out.println("请输入要删除的学生的姓名：");
//	String name = scanner.next();
//	for (int i = 0; i < list.size(); i++) {
//		if (list.get(i) != null && list.get(i).getName().equals(name)) {
//			list.remove(i);
//		}
//	}
//	save();
//	findAll();
//}
//public void add() {
//	System.out.println("请输入要录入的学生的人数：");
//	int num = scanner.nextInt();
//	for (int i = 0; i < num; i++) {
//		System.out.println("请输入学生的姓名：");
//		String name = scanner.next();
//		System.out.println("请输入学生的性别：");
//		String sex = scanner.next();
//		System.out.println("请输入学生的年龄：");
//		int age = scanner.nextInt();
//		Student student = new Student(name, sex, age);
//		list.add(student);
//	}
//	save();
//	findAll();
//}
