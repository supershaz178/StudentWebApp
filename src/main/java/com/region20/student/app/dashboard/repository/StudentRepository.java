package com.region20.student.app.dashboard.repository;

import java.sql.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.region20.student.app.dashboard.persistence.Student;

@Repository
@Transactional
public interface StudentRepository extends CrudRepository<Student, Integer> {
		
	public List<Student> findBySchoolYear(List<Integer> years); 
	
	public List<Student> findByCampus(List<Integer> campuses); 
	
	public List<Student> findByExternalStudentId(List<Integer> studentIds);
	
	public Student findByExternalStudentId(Integer studentId);
	
	public List<Student> findByEntryDate(List<Date> entryDates); 
	
	public List<Student> findByGradeLevel(List<Integer> gradeLevels); 
	
	public List<Student> findByFirstName(List<String> firstName); 
	
	public List<Student> findByLastName(List<String> lastName); 
	
	public List<Student> findByFirstNameAndMiddleNameAndLastName(String firstName, String lastName, String middleName); 

	//public List<Student> findByFullNames(List<String> fullNames); 
}
