package com.region20.student.app.dashboard.persistence;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity 
@Table(name="STUDENT")
public class Student implements Serializable {
	
	private static final long serialVersionUID = 7046650665254434581L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="STUDENT_SEQ")
	@Column(name="ID")
	private Integer id; 
	
	@Column(name="SCHOOL_YR")
	private Integer schoolYear;
	
	@Column(name="CAMPUS")
	private Integer campus;
	
	@Column(name="EXT_STU_ID")
	private Integer externalStudentId;
	
	@Column(name="ENTRY_DT")
	private Date entryDate; 
	
	@Column(name="GRADE_LVL")
	private Integer gradeLevel; 
	
	@Column(name="FIRST_NM")
	private String firstName; 
	
	@Column(name="MIDDLE_NM")
	private String middleName; 
	
	@Column(name="LAST_NM")
	private String lastName;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getSchoolYear() {
		return schoolYear;
	}
	public void setSchoolYear(Integer schoolYear) {
		this.schoolYear = schoolYear;
	}
	public Integer getCampus() {
		return campus;
	}
	public void setCampus(Integer campus) {
		this.campus = campus;
	}
	public Integer getExternalStudentId() {
		return externalStudentId;
	}
	public void setExternalStudentId(Integer externalStudentId) {
		this.externalStudentId = externalStudentId;
	}
	public Date getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}
	public Integer getGradeLevel() {
		return gradeLevel;
	}
	public void setGradeLevel(Integer gradeLevel) {
		this.gradeLevel = gradeLevel;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	} 

}
