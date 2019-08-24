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
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.region20.student.app.dashboard.persistence.Student;
import com.region20.student.app.dashboard.repository.StudentRepository;
import com.region20.student.app.dashboard.service.StudentService;

@RestController
@RequestMapping("/")
public class StudentController {
	
	@Autowired
	private StudentRepository repo; 
	
	@Autowired
	private StudentService service; 
	
	@RequestMapping(method=RequestMethod.POST, value="/add_student", produces="application/json; charset=UTF-8")
	public ResponseEntity<Student> addStudent(@RequestBody Map<String,String> newStudentJson){
		Student newStudent = buildStudentFromMap(newStudentJson); 
		
		if(repo.existsById(newStudent.getId())){
			return new ResponseEntity<Student>(HttpStatus.BAD_REQUEST); 
		}
		repo.save(newStudent);
		return ResponseEntity.ok(newStudent); 

	}
	
	@RequestMapping(method=RequestMethod.GET, value="/search", produces="application/json; charset=UTF-8")
	public ResponseEntity<List<Student>> searchForStudents(@RequestBody HashMap<String,List<Object>> parameters){
		List<Student> matchedStudents = service.searchStudents(parameters); 
		
		if(matchedStudents.isEmpty() || matchedStudents == null){
			return new ResponseEntity<List<Student>>(HttpStatus.NO_CONTENT); 
		} 
		
		return ResponseEntity.ok(matchedStudents); 
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/{student_id}", produces="application/json; charset=UTF-8")
	public ResponseEntity<Student> updateStudent(@RequestBody Map<String,String> studentMap, @PathVariable(value="product_id")Integer id){
		
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
		
		return ResponseEntity.ok(newStudent); 
	}

	@RequestMapping(method=RequestMethod.GET,value="/", produces="application/json; charset=UTF-8")
	public ModelAndView getAllStudents(){
		List<Student> allStudents = (List<Student>) repo.findAll();
		ModelAndView mv = new ModelAndView(); 
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			String json = mapper.writeValueAsString(allStudents);
			
			if(allStudents.isEmpty() || allStudents == null){
				mv = new ModelAndView("index", HttpStatus.NO_CONTENT); 
			}else{
				mv.addObject("allStudents", json); 
			}

		} catch (JsonProcessingException e) {
			mv.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		} 
				
		return mv;  
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
