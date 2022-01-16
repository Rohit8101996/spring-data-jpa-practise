package com.rohitbaranwal.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rohitbaranwal.model.Student;

@Repository
public interface StudentRepository extends  JpaRepository<Student, Long> {
	
	public List<Student> findByFirstName(String firstName);
	
	public List<Student> findByFirstNameContaining(String firstFewCharactersOfFirstName); // with containing on function 
																									//it will look for name which having first few characters in coloumn name in table 

	public List<Student> findByLastNameNotNull(); //finding all lastname which is not null
	
	public List<Student> findByGuardianName(String name);
	
	public Student findByFirstNameAndLastName(String firstName,String LastName);
	
	
	//JPQL Queries --These are based on classes in java and atttributes of java and not based on tables of database
	@Query("select s from Student s where s.emailId=?1 ")
	public Student getStudentByEmailAddress(String email);
	
	//if we need only field from student table
	@Query("select s.firstName from Student s where s.emailId=?1 ")
	public String getStudentFirstNameByEmailAddress(String email);
	
	//if we have a complex requirement and jpql cannot work so in this case we will use native query i.e., sql query for tables in db instead of jpql
	//Native Query example
	@Query(value="select * from student_tbl s where s.email_address=?1",nativeQuery = true)
	public Student getStudentByEmailAddressNative(String email);
	
	//Named Native query when we have more value to pass instead of ?1 we can write ?email
	@Query(value="select * from student_tbl s where s.email_address=:email",nativeQuery = true)
	public Student getStudentByEmailAddressNativeNamed(@Param("email") String email);
	
	//All above mentioned are to fetch data but to modify we need to use update
	@Modifying //since this is update query we need to add modifying annotation
	@Transactional //ideally it should to be used on service layer where multiple repositries are invoked and can also be rollback
	@Query(value="update student_tbl set first_name=?1 where email_address=?2",nativeQuery = true)
	public int updateStudentNameByEmailId(String name,String email);
}
