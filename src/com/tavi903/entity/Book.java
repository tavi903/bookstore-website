package com.tavi903.entity;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;
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
			+ "AND b.category.categoryId = coalesce(:category, b.category.categoryId)"),
	@NamedQuery(name = "Book.search",
			query = 
			  "SELECT b FROM Book b "
			+ "WHERE b.price >= coalesce(:lowerPrice, b.price) AND b.price <= coalesce(:upperPrice, b.price) "
			+ "AND b.title = coalesce(:title, b.title) AND b.author = coalesce(:author, b.author) "
			+ "AND b.publishDate >= coalesce(:lowerPublishDate, b.publishDate) AND b.publishDate <= coalesce(:upperPublishDate, b.publishDate) "
			+ "AND b.category.categoryId = coalesce(:category, b.category.categoryId) "
			+ "ORDER BY b.title"),
	@NamedQuery(name = "Book.selectTitles",  query = "SELECT DISTINCT b.title FROM Book b ORDER BY b.title"),
	@NamedQuery(name = "Book.selectAuthors", query = "SELECT DISTINCT b.author FROM Book b ORDER BY b.author"),
	@NamedQuery(name = "Book.maxPrice", query = "SELECT MAX(b.price) FROM Book b"),
	@NamedQuery(name = "Book.minPrice", query = "SELECT MIN(b.price) FROM Book b")
})
public class Book {

	@Id
	@NotNull
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "book_id")
	private long bookId;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id")
	private Category category;

	@Size(min = 3, max = 128, message = "Title must be longer than 3 characters and shorter than 128.")
	@Column(name = "title")
	private String title;

	@Size(min = 3, max = 64, message = "Author must be longer than 3 characters and shorter than 64.")
	@Column(name = "author")
	private String author;

	@Size(min = 64, message = "Description must be longer than 64 characters.")
	@Column(name = "description")
	private String description;

	@Size(min = 10, max = 10, message = "ISBN must be 10 characters.")
	@Column(name = "isbn")
	private String isbn;

	@Column(name = "image")
	private byte[] image;

	@NotNull
	@Column(name = "price", precision = 2, scale = 0)
	private float price;

	@NotNull
	@Column(name = "publish_date")
	private Date publishDate;

	@Version
	@Column(name = "last_update")
	private Timestamp lastUpdate;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "book")
	private Set<Review> reviews = new HashSet<Review>();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "book")
	private Set<OrderDetail> orderDetails = new HashSet<OrderDetail>();
	
	public Book() {
	}

	@Builder
	public Book(long bookId, Category category, String title, String author, String description, String isbn,
			byte[] image, float price, Date publishDate, Timestamp lastUpdate) {
		this.bookId = bookId;
		this.category = category;
		this.title = title;
		this.author = author;
		this.description = description;
		this.isbn = isbn;
		this.image = image;
		this.price = price;
		this.publishDate = publishDate;
		this.lastUpdate = lastUpdate;
	}

	@Override
	public int hashCode() {
		return Objects.hash(isbn);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		return Objects.equals(isbn, other.isbn);
	}

	@Override
	public String toString() {
		return "Book [bookId=" + bookId + ", category=" + category.toString() + ", title=" + title + ", author=" + author
				+ ", description=" + description + ", isbn=" + isbn + ", price="
				+ price + ", publishDate=" + publishDate + ", lastUpdate=" + lastUpdate + "]";
	}

}
