package com.region20.student.app.dashboard.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.region20.student.app.dashboard.persistence.Student;
import com.region20.student.app.dashboard.repository.StudentRepository;

@Service
public class StudentService {
	
	@Autowired
	private StudentRepository repo; 
	
	private static final String FIRST_NAME_KEY = "firstName";
	private static final String MIDDLE_NAME_KEY = "middleName";
	private static final String LAST_NAME_KEY = "lastName";
	private static final String SCH_YR_KEY = "schoolYear"; 
	private static final String CAMPUS_KEY = "campus"; 
	private static final String EXT_STUDENT_ID_KEY = "externalStudentId"; 
	private static final String ENTRY_DT_KEY = "entryDate"; 
	private static final String GRADE_LVL_KEY = "gradeLevel"; 
	
	public List<Student> searchStudents(HashMap<String,List<Object>> parameters){
		List<Student> matchedStudents = new ArrayList<Student>();
		
		for(String x : parameters.keySet()){
			if((FIRST_NAME_KEY.equals(x)) || (LAST_NAME_KEY.equals(x))){
				List<Student> studentNames = searchStudentsByNames(parameters.get(x), FIRST_NAME_KEY);  
				matchedStudents.addAll(studentNames); 
			}else if(MIDDLE_NAME_KEY.equals(x)){
				List<Student> studentNames = searchStudentsByNames(parameters.get(x), MIDDLE_NAME_KEY);  
				matchedStudents.addAll(studentNames); 
			}else if(LAST_NAME_KEY.equals(x)){
				List<Student> studentNames = searchStudentsByNames(parameters.get(x), LAST_NAME_KEY);  
				matchedStudents.addAll(studentNames); 
			}else if(SCH_YR_KEY.equals(x)){
				List<Object> studentSchYr = parameters.get(x); 
				matchedStudents.addAll(searchStudentsBySchoolYears(studentSchYr)); 
			}else if(CAMPUS_KEY.equals(x)){
				matchedStudents.addAll(searchStudentsByCampusNumbers(parameters.get(x))); 
			}else if(EXT_STUDENT_ID_KEY.equals(x)){
				matchedStudents.addAll(searchStudentsByExternalStudentIds(parameters.get(x))); 
			}else if(ENTRY_DT_KEY.equals(x)){
				matchedStudents.addAll(searchStudentsByEntryDates(parameters.get(x)));
			}else if(GRADE_LVL_KEY.equals(x)){
				matchedStudents.addAll(searchStudentsByGradeLevel(parameters.get(x))); 
			}
		}
		
		return matchedStudents; 
	}
	
	private List<Student> searchStudentsByNames(List<Object> studentsNames, String key){
		List<Student> matchedStudents = new ArrayList<Student>(); 
		List<String> namesFromJson = new ArrayList<String>(); 
		
		for(Object obj : studentsNames){
			if(obj instanceof String){
				namesFromJson.add((String) obj); 
			}
		}
		
		if(FIRST_NAME_KEY.equals(key)){
			matchedStudents.addAll(repo.findByFirstNameIn(namesFromJson)); 
		}else if(LAST_NAME_KEY.equals(key)){
			matchedStudents.addAll(repo.findByLastNameIn(namesFromJson)); 
		}else if(MIDDLE_NAME_KEY.equals(key)){
			matchedStudents.addAll(repo.findByMiddleNameIn(namesFromJson)); 
		}
		
		return matchedStudents; 
	}

	private List<Student> searchStudentsBySchoolYears(List<Object> studentYears){
		List<Student> matchedStudents = new ArrayList<Student>();
		List<Integer> convertedIntegers = new ArrayList<Integer>(); 
		
		for(Object year : studentYears){
			if(year instanceof Integer){
				convertedIntegers.add((Integer) year); 
			}
		}
		matchedStudents = repo.findBySchoolYearIn(convertedIntegers); 
		
		return matchedStudents; 
	}
	
	private List<Student> searchStudentsByCampusNumbers(List<Object> campusCodes){
		List<Student> matchedStudents = new ArrayList<Student>();
		List<Integer> convertedIntegers = new ArrayList<Integer>(); 
		
		for(Object code: campusCodes){
			if(code instanceof Integer){
				convertedIntegers.add((Integer) code); 
			}
		}
		matchedStudents = repo.findByCampusIn(convertedIntegers); 
		
		return matchedStudents; 
	}

	private List<Student> searchStudentsByExternalStudentIds(List<Object> studentIds){
		List<Student> matchedStudents = new ArrayList<Student>();
		List<Integer> convertedIntegers = new ArrayList<Integer>(); 
		
		for(Object id : studentIds){
			if(id instanceof String){
				convertedIntegers.add(new Integer((String) id)); 
			}
		}
		matchedStudents = repo.findByExternalStudentIdIn(convertedIntegers); 
		
		return matchedStudents; 
	}
	
	private List<Student> searchStudentsByEntryDates(List<Object> dates){
		List<Student> matchedStudents = new ArrayList<Student>();
		List<Integer> convertedIntegers = new ArrayList<Integer>(); 
		
		for(Object entryDate : dates){
			if(entryDate instanceof Integer){
				convertedIntegers.add((Integer) entryDate); 
			}
		}
		matchedStudents = repo.findBySchoolYearIn(convertedIntegers); 
		
		return matchedStudents; 
	}

	private List<Student> searchStudentsByGradeLevel(List<Object> levels){
		List<Student> matchedStudents = new ArrayList<Student>();
		List<Integer> convertedIntegers = new ArrayList<Integer>(); 
		
		for(Object level : levels){
			if(level instanceof Integer){
				convertedIntegers.add((Integer) level); 
			}
		}
		matchedStudents = repo.findBySchoolYearIn(convertedIntegers); 
		
		return matchedStudents; 
	}
}
