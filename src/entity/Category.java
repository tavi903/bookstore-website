package entity;

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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "category")
@NamedQueries({
		@NamedQuery(name = "Category.count", query = "SELECT COUNT(c) FROM Category c"),
		@NamedQuery(name = "Category.findAll", query = "SELECT c FROM Category c ORDER BY c.name") })
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "category_id", unique = true)
	private long categoryId;

	@Size(min = 4, max = 30)
	@Column(name = "name", unique = true)
	private String name;

	@Column(name = "flag_deleted")
	private boolean deleted;

	@Version
	@Generated(GenerationTime.ALWAYS)
	@Column(name = "last_update")
	private Timestamp lastUpdate;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
	private Set<Book> books = new HashSet<Book>();

	public Category() {
	}

	public Category(String name, boolean isDeleted) {
		this.name = name;
		this.deleted = isDeleted;
	}

	@Override
	public int hashCode() {
		return Objects.hash(categoryId, deleted, lastUpdate, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Category other = (Category) obj;
		return categoryId == other.categoryId && deleted == other.deleted
				&& Objects.equals(lastUpdate, other.lastUpdate) && Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		return "Category [categoryId=" + categoryId + ", name=" + name + ", deleted=" + deleted + ", lastUpdate="
				+ lastUpdate + "]";
	}

}
