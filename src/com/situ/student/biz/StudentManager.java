package com.situ.student.biz;

import java.util.ArrayList;
import java.util.List;

import com.situ.student.dao.StudentDao;
import com.situ.student.entity.Student;
/**
 * 控制层：中转、控制作用
 * @author Administrator
 */
public class StudentManager {
	List<Student> list = new ArrayList<Student>();
	StudentDao studentDao = new StudentDao();

	public void save(List<Student> list) {
		studentDao.save();
	}

	public List<Student> load() {
		list = studentDao.load();
		return list;
	}
}
