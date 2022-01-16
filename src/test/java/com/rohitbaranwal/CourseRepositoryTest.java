package com.rohitbaranwal;


import java.util.List;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.rohitbaranwal.model.Course;
import com.rohitbaranwal.model.CourseMaterial;
import com.rohitbaranwal.model.Guardian;
import com.rohitbaranwal.model.Student;
import com.rohitbaranwal.model.Teacher;
import com.rohitbaranwal.repository.CourseMaterialRepository;
import com.rohitbaranwal.repository.CourseRepository;

@SpringBootTest
public class CourseRepositoryTest {

	@Autowired
	CourseRepository courseRepository;

	@Autowired
	CourseMaterialRepository courseMaterialRepository;

	@Test
	public void saveCourseMaterialWithCourse()
	{
		Course course1=Course.builder().title("DSA").credit(5).build();
		CourseMaterial courseMaterial1=CourseMaterial.builder().url("www.google.com").course(course1).build();
		Course course2=Course.builder().title("JAVA").credit(6).build();
		CourseMaterial courseMaterial2=CourseMaterial.builder().url("www.javatpoint.com").course(course2).build();
		courseMaterialRepository.save(courseMaterial1);
		courseMaterialRepository.save(courseMaterial2);
	} 
	@Test
	public void saveCourseWithTeacher()
	{
		Teacher teacher =Teacher.builder().firstName("Java Code ").lastName("Master").build();

		Course course=Course.builder().title("SPRING BOOT").credit(8).teacher(teacher).build();
		CourseMaterial courseMaterial=CourseMaterial.builder().url("www.spring.io").course(course).build();
		courseMaterialRepository.save(courseMaterial);
	}
	@Test
	public void saveCourseWithStudentAndTeacher()
	{
		Teacher teacher =Teacher.builder().firstName("Geek ").lastName("Task").build();
		Guardian guardian=Guardian.builder().name("rajendra").email("rajendra shekawat@gmail.com").phoneNumber("231345786").build();
		Student student1=Student.builder().firstName("ramesh").lastName("ram").emailId("ramesh.ram@gmail.com").guardian(guardian).build();
		Student student2=Student.builder().firstName("raju").lastName("ram").emailId("raju.ram@gmail.com").build();
		
		Course course=Course.builder().title("SPRING DATA JPA").credit(12).teacher(teacher).students(List.of(student1)).build();
		//courseRepository.save(course);
		CourseMaterial courseMaterial=CourseMaterial.builder().url("www.spring.io").course(course).build();
		courseMaterialRepository.save(courseMaterial);
	}

	@Nested
	class InnerTest{
		@Test
		void getAllCourses() {
			List<Course> list=courseRepository.findAll();

			System.out.println(list);  //here i want to see course material as well for a course because there is no meaning of coursematerial with out a course
			//so we need biderectional mapping instead of unidirectional
		}


		//Pagination and Sorting
		@Test
		public void findAllPagination()
		{

			Pageable firstPageWithOneRecord=PageRequest.of(0, 5); 
			Pageable secondPageWithOneRecord=PageRequest.of(1, 5); 

			List<Course> courses1= courseRepository.findAll(firstPageWithOneRecord).getContent();
			System.out.println("-------------------->");
			System.out.println(courses1);
			List<Course> courses2= courseRepository.findAll(secondPageWithOneRecord).getContent();
			System.out.println("-------------------->");
			System.out.println(courses2);

			long totalElements= courseRepository.findAll(firstPageWithOneRecord).getTotalElements();
			System.out.println("-------------------->");
			System.out.println("totalElements: " + totalElements);

			long totalPages= courseRepository.findAll(firstPageWithOneRecord).getTotalPages();
			System.out.println("-------------------->");
			System.out.println("totalPages: " + totalPages);
		}

		@Test
		public void findAllSorting()
		{
			Pageable sortByCredit=PageRequest.of(0, 5, Sort.by("credit").descending());
			Pageable sortByTitle=PageRequest.of(0, 5,Sort.by("title"));
			Pageable sortByTitleandCreditDesc=PageRequest.of(0, 5, Sort.by("title").descending().and(Sort.by("credit")));
			List<Course> courses1= courseRepository.findAll(sortByCredit).getContent();
			System.out.println("-------------------->");
			System.out.println(courses1);

			List<Course> courses2= courseRepository.findAll(sortByTitle).getContent();
			System.out.println("-------------------->");
			System.out.println(courses2);

			List<Course> courses3= courseRepository.findAll(sortByTitleandCreditDesc).getContent();
			System.out.println("-------------------->");
			System.out.println(courses3);
		}

		@Test 
		void testFindByTitleContaining()
		{
			Pageable firstPageEightRecords =PageRequest.of(0, 8);

			List<Course> courses=courseRepository.findByTitleContaining("DSA", firstPageEightRecords).getContent();

			System.out.println("=============");
			System.out.println(courses);
		}
	}
}
