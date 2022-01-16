package com.rohitbaranwal;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.rohitbaranwal.model.Course;
import com.rohitbaranwal.model.CourseMaterial;
import com.rohitbaranwal.repository.CourseMaterialRepository;

@SpringBootTest
public class CourseMaterialRepositoryTest {
	
	@Autowired
	CourseMaterialRepository courseMaterialRepository;

	@Test
	public void saveCourseMaterialWithCourse()
	{
		Course course=Course.builder().title("DSA").credit(6).build();
		CourseMaterial courseMaterial=CourseMaterial.builder().url("www.google.com").course(course).build();
		
		courseMaterialRepository.save(courseMaterial);
	}
	
	
	@Test
	public void printAllCourse()
	{
		List<CourseMaterial> courseMaterial=courseMaterialRepository.findAll();
		
		System.out.println(courseMaterial);
	}
}
