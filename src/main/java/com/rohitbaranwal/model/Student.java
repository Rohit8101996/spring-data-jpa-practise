package com.rohitbaranwal.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="student_tbl",
		uniqueConstraints = {@UniqueConstraint(name= "emailid_unique",columnNames = {"email_address"})
											})  //if we want to make a table name for ourself //adding unique constraint to particular coloumn if want to add as composite coloumn as unique add comma after email in double quotes
														//we can also multiple unique contraint on do see https://www.baeldung.com/jpa-unique-constraints
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Student {

	@Id
	//@GeneratedValue(strategy=GenerationType.AUTO) //we can do auto increment as generation stategy of primary key
	@SequenceGenerator(
			name = "student_sequence",
			sequenceName = "student_sequence",
			allocationSize = 1
			)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_sequence") // we can sequence to be generated 
	@Column
	Long id;
	@Column
	String firstName;
	@Column
	String lastName;
	@Column(name ="email_address",
			nullable = false) //making email column from ourself what we want in db .. //also everytime it is mandatory to pass email 
	String emailId; //also we want email id to email to be unique ..for this go where we defined table name
	
	@Embedded
	Guardian guardian;
	
	@ManyToMany(mappedBy = "students")
	List<Course> courses;
}
