package com.rohitbaranwal.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString(exclude="course")  //since we defined lazy
public class CourseMaterial {
	
	@Id
	@SequenceGenerator(name = "course_material_sequence",sequenceName = "course_material_sequence",allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "course_material_sequence")
	private Long courseMaterialId;
	private String url;
	
	@OneToOne(
			cascade = CascadeType.ALL,  //cascading is done if want to save a parent class and have child class with it so when we save course material with course provided course should automatically saved in course table for that we have cascade 
			fetch = FetchType.LAZY //this is defined we want  course also to be fetched when we ask for course material if provided lazy we need to ask particularly for course in coursematerial 
			)
	@JoinColumn(name = "course_id",referencedColumnName = "courseId")
	private Course course; //One to One mapping ,as per requirement coursematerials exist for a particular course 
}
