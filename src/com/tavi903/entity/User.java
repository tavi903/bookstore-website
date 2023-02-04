package com.tavi903.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Setter;
import lombok.Getter;

@Getter
@Setter
@Entity
@Table(name = "user")
@NamedStoredProcedureQuery(name = "User.update_user", procedureName = "update_user", parameters = {
		@StoredProcedureParameter(mode = ParameterMode.IN, type = Long.class, name = "p_last_accessed_time"),
		@StoredProcedureParameter(mode = ParameterMode.IN, type = Long.class, name = "p_user_id"),
		@StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "p_email"),
		@StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "p_password"),
		@StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "p_full_name") })
@NamedQueries({ 
		@NamedQuery(name = "User.findAll", query = "SELECT u FROM User u ORDER BY u.email"),
		@NamedQuery(name = "User.count", query = "SELECT COUNT(u) FROM User u"),
		@NamedQuery(name = "User.findByEmailAndPassword", query = "SELECT u FROM User u WHERE u.email = :email AND u.password = :password") })
public class User extends BaseEntity {

	@NotBlank
	@Email
	private String email;
	/*
	 * TODO: develop password custom validation (without spaces, with a number, ...)
	 * https://www.baeldung.com/javax-validation-method-constraints
	 */
	@Size(min = 8, max = 16)
	private String password;
	@NotBlank
	@Size(max = 30)
	@Column(name = "full_name")
	private String fullName;

	public User() {
	}

	public User(String email, String password,
			String fullName) {
		this.email = email;
		this.password = password;
		this.fullName = fullName;
	}

	@Override
	public String toString() {
		return "User [userId=" + super.toString() + ", email=" + email + ", password=" + password + ", fullName=" + fullName + "]";
	}
	
}
