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
		
	public List<Student> findBySchoolYearIn(List<Integer> years); 
	
	public List<Student> findByCampusIn(List<Integer> campuses); 
	
	public List<Student> findByExternalStudentIdIn(List<Integer> studentIds);
	
	public Student findByExternalStudentId(Integer studentId);
	
	public List<Student> findByEntryDateIn(List<Date> entryDates); 
	
	public List<Student> findByGradeLevelIn(List<Integer> gradeLevels); 
	
	public List<Student> findByFirstNameIn(List<String> firstName); 
	
	public List<Student> findByLastNameIn(List<String> lastName); 
	
	public List<Student> findByFirstNameAndMiddleNameAndLastName(String firstName, String lastName, String middleName); 

	//public List<Student> findByFullNames(List<String> fullNames); 
}
