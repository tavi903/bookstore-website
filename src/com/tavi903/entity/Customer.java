package com.tavi903.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "customer", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class Customer extends BaseEntity {

	@Column(name = "email", unique = true, nullable = false, length = 64)
	private String email;

	@Column(name = "fullname", nullable = false, length = 30)
	private String fullname;

	@Column(name = "address", nullable = false, length = 128)
	private String address;

	@Column(name = "city", nullable = false, length = 32)
	private String city;

	@Column(name = "country", nullable = false, length = 64)
	private String country;

	@Column(name = "phone", nullable = false, length = 15)
	private String phone;

	@Column(name = "zipcode", nullable = false, length = 24)
	private String zipcode;

	@Column(name = "password", nullable = false, length = 16)
	private String password;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "register_date", nullable = false, length = 19)
	private Date registerDate;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
	private Set<Review> reviews = new HashSet<Review>();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
	private Set<BookOrder> bookOrders = new HashSet<BookOrder>();

	public Customer() {
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFullname() {
		return this.fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getZipcode() {
		return this.zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getRegisterDate() {
		return this.registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	public Set<Review> getReviews() {
		return this.reviews;
	}

	public void setReviews(Set<Review> reviews) {
		this.reviews = reviews;
	}

	public Set<BookOrder> getBookOrders() {
		return this.bookOrders;
	}

	public void setBookOrders(Set<BookOrder> bookOrders) {
		this.bookOrders = bookOrders;
	}

}
