package entity;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashSet;
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
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "book", catalog = "bookstoredb", uniqueConstraints = @UniqueConstraint(columnNames = "title"))
@NamedQueries({
	@NamedQuery(name = "Book.count",   query = "SELECT COUNT(b) FROM Book b"),
	@NamedQuery(name = "Book.findAll", query = "SELECT b FROM Book b ORDER BY b.title"),
	@NamedQuery(name = "Book.countSearch", 
			query = 
			  "SELECT COUNT(b) FROM Book b "
			+ "WHERE b.price >= IFNULL(:lowerPrice, b.price) AND b.price <= IFNULL(:upperPrice, b.price) "
			+ "AND b.title = IFNULL(:title, b.title) AND b.author = IFNULL(:author, b.author) "
			+ "AND b.publishDate >= IFNULL(:lowerPublishDate, b.publishDate) AND b.publishDate <= IFNULL(:upperPublishDate, b.publishDate) "
			+ "AND b.category.categoryId = IFNULL(:category, b.category.categoryId)"),
	@NamedQuery(name = "Book.search",
			query = 
			  "SELECT b FROM Book b "
			+ "WHERE b.price >= IFNULL(:lowerPrice, b.price) AND b.price <= IFNULL(:upperPrice, b.price) "
			+ "AND b.title = IFNULL(:title, b.title) AND b.author = IFNULL(:author, b.author) "
			+ "AND b.publishDate >= IFNULL(:lowerPublishDate, b.publishDate) AND b.publishDate <= IFNULL(:upperPublishDate, b.publishDate) "
			+ "AND b.category.categoryId = IFNULL(:category, b.category.categoryId) "
			+ "ORDER BY b.title"),
	@NamedQuery(name = "Book.selectTitles",  query = "SELECT DISTINCT b.title FROM Book b ORDER BY b.title"),
	@NamedQuery(name = "Book.selectAuthors", query = "SELECT DISTINCT b.author FROM Book b ORDER BY b.author"),
	@NamedQuery(name = "Book.maxPrice", query = "SELECT MAX(b.price) FROM Book b"),
	@NamedQuery(name = "Book.minPrice", query = "SELECT MIN(b.price) FROM Book b")
})
public class Book implements Serializable {

	private static final long serialVersionUID = -2439382557636658867L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "book_id", unique = true, nullable = false)
	private long bookId;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id", nullable = false)
	private Category category;

	@Size(min = 3, max = 128)
	@Column(name = "title", unique = true, nullable = false, length = 128)
	private String title;

	@Size(min = 3, max = 64)
	@Column(name = "author", nullable = false, length = 64)
	private String author;

	@Size(min = 64)
	@Column(name = "description", nullable = false, length = 16777215)
	private String description;

	@Size(min = 15, max = 15)
	@Column(name = "isbn", nullable = false, length = 15)
	private String isbn;

	@Column(name = "image", nullable = false)
	private byte[] image;

	@Column(name = "price", nullable = false, precision = 2, scale = 0)
	private float price;

	@NotNull
	@Column(name = "publish_date", nullable = false, length = 10)
	private Date publishDate;

	@Version
	@Column(name = "last_update", nullable = false)
	private Timestamp lastUpdate;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "book")
	private Set<Review> reviews = new HashSet<Review>();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "book")
	private Set<OrderDetail> orderDetails = new HashSet<OrderDetail>();
	
	public Book() {
	}

	@Builder
	public Book(Category category, String title, String author, String description, String isbn,
			byte[] image, float price, Date publishDate, Timestamp lastUpdate) {
		super();
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

}
