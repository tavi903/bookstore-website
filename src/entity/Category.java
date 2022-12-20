package entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "category", catalog = "bookstoredb")
@NamedQueries({
		@NamedQuery(name = "Category.count", query = "SELECT COUNT(c) FROM Category c"),
		@NamedQuery(name = "Category.findAll", query = "SELECT c FROM Category c ORDER BY c.name") })
public class Category implements Serializable {

	private static final long serialVersionUID = 7868343389161799534L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "category_id", unique = true, nullable = false)
	private long categoryId;

	@Size(min = 4, max = 30)
	@Column(name = "name", unique = true, nullable = false, length = 30)
	private String name;

	@Column(name = "flag_deleted", nullable = false)
	private boolean deleted;

	@Version
	@Column(name = "last_update", nullable = false)
	private Timestamp lastUpdate;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
	private Set<Book> books = new HashSet<Book>();

	public Category() {
	}

	public Category(String name, boolean isDeleted) {
		this.name = name;
		this.deleted = isDeleted;
	}

	public Category(String name, boolean isDeleted, Set<Book> books) {
		this.name = name;
		this.deleted = isDeleted;
		this.books = books;
	}

}
