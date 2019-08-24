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
	
	private static final String NAME_KEY = "NAME"; 
	private static final String SCH_YR_KEY = "SCHOOL_YR"; 
	private static final String CAMPUS_KEY = "CAMPUS"; 
	private static final String EXT_STUDENT_ID_KEY = "EXT_ID"; 
	private static final String ENTRY_DT_KEY = "ENTRY_DT"; 
	private static final String GRADE_LVL_KEY = "GRADE"; 
	
	public List<Student> searchStudents(HashMap<String,List<Object>> parameters){
		List<Student> matchedStudents = new ArrayList<Student>();
		
		for(String x : parameters.keySet()){
			if(NAME_KEY.equals(x)){
				List<Student> studentNames = searchStudentsByNames(parameters.get(x));  
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
	
	private List<Student> searchStudentsByNames(List<Object> studentsNames){
		List<Student> matchedStudents = new ArrayList<Student>(); 
		
		for(Object objName : studentsNames){
			if(objName instanceof String){
				String name = (String)objName; 
				String[] nameArray = name.split(" "); 
				if(nameArray.length > 2){
					matchedStudents.addAll(repo.findByFirstNameAndMiddleNameAndLastName(nameArray[0], nameArray[1], nameArray[2])); 
				}else{
					matchedStudents.addAll(repo.findByFirstNameAndMiddleNameAndLastName(nameArray[0], null, nameArray[2]));
				}
			}
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
		matchedStudents = repo.findBySchoolYear(convertedIntegers); 
		
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
		matchedStudents = repo.findByCampus(convertedIntegers); 
		
		return matchedStudents; 
	}

	private List<Student> searchStudentsByExternalStudentIds(List<Object> studentIds){
		List<Student> matchedStudents = new ArrayList<Student>();
		List<Integer> convertedIntegers = new ArrayList<Integer>(); 
		
		for(Object id : studentIds){
			if(id instanceof Integer){
				convertedIntegers.add((Integer) id); 
			}
		}
		matchedStudents = repo.findByExternalStudentId(convertedIntegers); 
		
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
		matchedStudents = repo.findBySchoolYear(convertedIntegers); 
		
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
		matchedStudents = repo.findBySchoolYear(convertedIntegers); 
		
		return matchedStudents; 
	}
}
