package com.rohitbaranwal.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
//we dont want guardian entity to be in a seperate table in mysql and want to be stored in single table with student so using embeddable and
@AttributeOverrides({
	@AttributeOverride(name="name",column = @Column(name="guardian_name")),
	@AttributeOverride(name="email",column = @Column(name="guardian_email")),
	@AttributeOverride(name="phoneNumber",column = @Column(name="guardian_phone")) //by this we are mapping coloumn of guardian entity to student table coloumn
})  //we can also use these attributeoveride in student class above field guardian instead of here or we do here

@Embeddable
public class Guardian {
	
	@Column
	String name;
	@Column
	String email;
	@Column
	String phoneNumber;

}
