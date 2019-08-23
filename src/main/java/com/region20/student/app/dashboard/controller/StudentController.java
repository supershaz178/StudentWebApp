package com.region20.student.app.dashboard.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.region20.student.app.dashboard.persistence.Student;
import com.region20.student.app.dashboard.repository.StudentRepository;
import com.region20.student.app.dashboard.service.StudentService;

@RestController
@RequestMapping("/students")
public class StudentController {
	
	@Autowired
	private StudentRepository repo; 
	
	@Autowired
	private StudentService service; 
	
	@RequestMapping(method=RequestMethod.POST, value="/add_student")
	public ResponseEntity<Student> addProduct(@RequestBody Map<String,String> newStudentJson){
		Student newStudent = buildStudentFromMap(newStudentJson); 
		
		if(repo.existsById(newStudent.getId())){
			return new ResponseEntity<Student>(HttpStatus.BAD_REQUEST); 
		}
		repo.save(newStudent);
		return new ResponseEntity<Student>(HttpStatus.CREATED); 

	}
	
	@RequestMapping(method=RequestMethod.GET, value="/search")
	public ResponseEntity<List<Student>> searchForStudents(@RequestBody HashMap<String,List<Object>> parameters){
		ResponseEntity<List<Student>> response = null; 
		
		List<Student> matchedStudents = service.searchStudents(parameters); 
		response = new ResponseEntity<List<Student>>(matchedStudents, HttpStatus.OK); 
		
		return response; 
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/{student_id}")
	public ResponseEntity<Student> updateProduct(@RequestBody Map<String,String> studentMap, @PathVariable(value="product_id")Integer id){
		
		Student newStudent;
		if(!repo.existsById(id)){
			return new ResponseEntity<Student>(HttpStatus.BAD_REQUEST);
		}else{
			newStudent = repo.findById(id).get(); 
			newStudent.setFirstName(studentMap.get("firstName"));
			newStudent.setMiddleName(studentMap.get("middleName"));
			newStudent.setLastName(studentMap.get("lastName"));
			newStudent.setCampus(new Integer(studentMap.get("campus")));
			newStudent.setExternalStudentId(new Integer(studentMap.get("externalStudentId")));
			newStudent.setSchoolYear(new Integer(studentMap.get("schoolYear")));
			newStudent.setGradeLevel(new Integer(studentMap.get("gradeLevel")));
			repo.save(newStudent); 
		}
		
		return new ResponseEntity<Student>(newStudent,HttpStatus.OK);
	}

	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<Student>> getAllProducts(){
		List<Student> allStudents = (List<Student>) repo.findAll(); 
		
		return new ResponseEntity<List<Student>>(allStudents, HttpStatus.OK);
	}

	
	private Student buildStudentFromMap(Map<String,String> studentMap){
		Student newStudent = new Student(); 
		
		SimpleDateFormat sdf = new SimpleDateFormat("mm/dd/yyyy"); 
		
		newStudent.setId(new Integer(studentMap.get("id")));
		newStudent.setFirstName(studentMap.get("firstName"));
		newStudent.setMiddleName(studentMap.get("middleName"));
		newStudent.setLastName(studentMap.get("lastName"));
		newStudent.setCampus(new Integer(studentMap.get("campus")));
		newStudent.setExternalStudentId(new Integer(studentMap.get("externalStudentId")));
		newStudent.setSchoolYear(new Integer(studentMap.get("schoolYear")));
		newStudent.setGradeLevel(new Integer(studentMap.get("gradeLevel")));
		try {
			newStudent.setEntryDate((java.sql.Date)sdf.parse(studentMap.get("entryDate")));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return newStudent;
	}

}
