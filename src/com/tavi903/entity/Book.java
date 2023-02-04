package com.tavi903.entity;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "book")
@NamedQueries({
	@NamedQuery(name = "Book.count",   query = "SELECT COUNT(b) FROM Book b"),
	@NamedQuery(name = "Book.findAll", query = "SELECT b FROM Book b ORDER BY b.title"),
	@NamedQuery(name = "Book.countSearch", 
			query = 
			  "SELECT COUNT(b) FROM Book b "
			+ "WHERE b.price >= coalesce(:lowerPrice, b.price) AND b.price <= coalesce(:upperPrice, b.price) "
			+ "AND b.title = coalesce(:title, b.title) AND b.author = coalesce(:author, b.author) "
			+ "AND b.publishDate >= coalesce(:lowerPublishDate, b.publishDate) AND b.publishDate <= coalesce(:upperPublishDate, b.publishDate) "
			+ "AND b.category.id = coalesce(:category, b.category.id)"),
	@NamedQuery(name = "Book.search",
			query = 
			  "SELECT b FROM Book b "
			+ "WHERE b.price >= coalesce(:lowerPrice, b.price) AND b.price <= coalesce(:upperPrice, b.price) "
			+ "AND b.title = coalesce(:title, b.title) AND b.author = coalesce(:author, b.author) "
			+ "AND b.publishDate >= coalesce(:lowerPublishDate, b.publishDate) AND b.publishDate <= coalesce(:upperPublishDate, b.publishDate) "
			+ "AND b.category.id = coalesce(:category, b.category.id) "
			+ "ORDER BY b.title"),
	@NamedQuery(name = "Book.selectTitles",  query = "SELECT DISTINCT b.title FROM Book b ORDER BY b.title"),
	@NamedQuery(name = "Book.selectAuthors", query = "SELECT DISTINCT b.author FROM Book b ORDER BY b.author"),
	@NamedQuery(name = "Book.maxPrice", query = "SELECT MAX(b.price) FROM Book b"),
	@NamedQuery(name = "Book.minPrice", query = "SELECT MIN(b.price) FROM Book b")
})
public class Book extends BaseEntity {

	@NotNull
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "category_id")
	private Category category;
	@Size(min = 3, max = 128, message = "must be longer than 3 characters and shorter than 128.")
	private String title;
	@Size(min = 3, max = 64, message = "must be longer than 3 characters and shorter than 64.")
	private String author;
	@Size(min = 64, message = "must be longer than 64 characters.")
	private String description;
	@Size(min = 10, max = 10, message = "must be 10 characters.")
	private String isbn;
	private byte[] image;
	@NotNull
	private Float price;
	@NotNull
	@Column(name = "publish_date")
	private Date publishDate;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "book")
	private Set<Review> reviews = new HashSet<Review>();
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "book")
	private Set<OrderDetail> orderDetails = new HashSet<OrderDetail>();
	
	public Book() {
	}

	@Builder
	public Book(Long id, Category category, String title, String author, String description, String isbn,
			byte[] image, float price, Date publishDate, Timestamp lastUpdate) {
		super.setId(id);
		this.category = category;
		this.title = title;
		this.author = author;
		this.description = description;
		this.isbn = isbn;
		this.image = image;
		this.price = price;
		this.publishDate = publishDate;
	}

	@Override
	public String toString() {
		return "Book [bookId=" + super.toString() + ", category=" + category.toString() + ", title=" + title + ", author=" + author
				+ ", description=" + description + ", isbn=" + isbn + ", price="
				+ price + ", publishDate=" + publishDate + "]";
	}

}
