package com.example.tutorial3.service;

import com.example.tutorial3.model.StudentModel;

import java.util.ArrayList;
import java.util.List;

public class InMemoryStudentService implements StudentService {
	private static List<StudentModel> studentList = new ArrayList<StudentModel>();
	
	@Override
	public StudentModel selectStudent(String npm) {
		for(int i=0; i < studentList.size(); i++) {
			StudentModel student = studentList.get(i);
			if(student.getNpm().equals(npm)) {
				return student;
			}
		}
		return null;
	}
	
	@Override
	public List<StudentModel> selectAllStudents() {
		return studentList;
	}
	
	@Override
	public void addStudent(StudentModel student) {
		studentList.add(student);
	}
}
