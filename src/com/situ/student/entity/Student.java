package com.situ.student.entity;

import java.io.Serializable;
/**
 * 实体类：学生的基本信息
 * @author Administrator
 */
@SuppressWarnings("serial")
public class Student implements Serializable {
	private String name;
	private String sex;
	private int age;

	public Student() {
		super();
	}

	public Student(String name, String sex, int age) {
		super();
		this.name = name;
		this.sex = sex;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Student [name=" + name + ", sex=" + sex + ", age=" + age + "]";
	}

}
