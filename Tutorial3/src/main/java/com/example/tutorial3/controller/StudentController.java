package com.example.tutorial3.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;
import com.example.tutorial3.model.StudentModel;
import com.example.tutorial3.service.InMemoryStudentService;
import com.example.tutorial3.service.StudentService;

@Controller
public class StudentController {
	private final StudentService studentService;
	
	public StudentController() {
		studentService = new InMemoryStudentService();
	}
	
	@RequestMapping("/student/add")
	public String add(@RequestParam(value = "npm", required = true) String npm, @RequestParam(value = "name", required = true) String name, @RequestParam(value = "gpa", required = true) double gpa) {
		StudentModel student = new StudentModel(npm, name, gpa);
		studentService.addStudent(student);
		return "add";
	}
	
	@RequestMapping("/student/view")
	public String view(Model model, @RequestParam(value = "npm", required = true) String npm) {
		StudentModel student = studentService.selectStudent(npm);
		model.addAttribute("student", student);
		return "view";
	}
	
	@RequestMapping("/student/viewall")
	public String viewAll(Model model){
		List<StudentModel> students = studentService.selectAllStudents();
		model.addAttribute("student", students);
		return "viewall";
	}
	
	//pertama
		@RequestMapping("/student/view/{npm}")
		public String viewPath(@PathVariable Optional<String> npm, Model model) {
			StudentModel student = studentService.selectStudent(npm.get());
			model.addAttribute("student", student);
			
			if (student == null) {
				return "viewFailed";
			} else {
				return "view";

			}
		}
	
		//kedua
		@RequestMapping("/student/delete/{npm}")
		public String delete(@PathVariable Optional<String> npm, Model model) {
			StudentModel students = studentService.selectStudent(npm.get()); 
			if (students == null){
				return "deleteFailed";
			} else {
				List<StudentModel> studentsArr = studentService.selectAllStudents();
				studentsArr.remove(students);
				return "delete";
			}
		}
}
