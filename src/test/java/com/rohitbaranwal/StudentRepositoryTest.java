package com.rohitbaranwal;


import java.util.List;


import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.rohitbaranwal.model.Guardian;
import com.rohitbaranwal.model.Student;
import com.rohitbaranwal.repository.StudentRepository;

//These are not test it is just to understand if jpa is working 
@SpringBootTest
public class StudentRepositoryTest {

	@Autowired						//we are using springboot test and autowired to so that we want data to populate to student
	StudentRepository studentRepository;

	@Test
	public void save()
	{
		Student student= Student
				.builder().firstName("ramesh")
				.lastName("kumar")
				.emailId("rameshkumar@gmail.com").build(); //using builder pattern to create student object 

		studentRepository.save(student);
	}
	@Test
	public void saveStudentwithGuardian()
	{
		Guardian guardian =Guardian.builder().email("shaktiiman@gmail.com").name("shaktiman").phoneNumber("99999999").build();
		Student student=Student.builder().emailId("rajjjen@gmail.com").firstName("rajjjennnt").lastName("kumar").guardian(guardian).build();

		studentRepository.save(student);
	}
	@Nested //Using Nested with Inner class as some test where getting executed before save methods above 
	//https://stackoverflow.com/questions/1548462/junit-before-only-for-some-test-methods/1548471
	class InnerTest{
		@Test
		public void printAllStudents()
		{
			List<Student> students=studentRepository.findAll();

			System.out.println(students);
		}

		@Test
		public void testFindByUserName()
		{
			List<Student> students=studentRepository.findByFirstName("ramesh");

			System.out.println(students);
		}

		@Test
		public void testFindByUserNameContaining()
		{
			List<Student> students=studentRepository.findByFirstNameContaining("r");

			System.out.println(students);
		}
		@Test
		public void testFindByGuardianName()
		{
			List<Student> students=studentRepository.findByGuardianName("shaktiman");

			System.out.println(students);
		}
		@Test
		public void testFindByLastNameNotNull()
		{
			List<Student> students=studentRepository.findByLastNameNotNull();

			System.out.println(students);
		}

		@Test
		public void testGetStudentByEmailAddress()
		{
			Student student=studentRepository.getStudentByEmailAddress("rameshkumar@gmail.com");

			System.out.println(student);
		}


		@Test
		public void testGetStudentFirstNameByEmailAddress()
		{
			String name=studentRepository.getStudentFirstNameByEmailAddress("rameshkumar@gmail.com");

			System.out.println(name);
		}

		@Test
		public void testGetStudentByEmailAddressNative()
		{
			Student student=studentRepository.getStudentByEmailAddressNative("rameshkumar@gmail.com");

			System.out.println(student);
		}

		@Test
		void testGetStudentByEmailAddressNativeNamed()
		{
			Student student=studentRepository.getStudentByEmailAddressNativeNamed("rameshkumar@gmail.com");

			System.out.println(student.getEmailId());
		}


	}
}
