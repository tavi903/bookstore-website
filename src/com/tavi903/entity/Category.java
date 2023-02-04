package com.tavi903.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "category")
@NamedQueries({
		@NamedQuery(name = "Category.count", query = "SELECT COUNT(c) FROM Category c"),
		@NamedQuery(name = "Category.findAll", query = "SELECT c FROM Category c ORDER BY c.name") })
public class Category extends BaseEntity {
	
	@Size(min = 4, max = 30)
	private String name;
	@Column(name = "flag_deleted")
	private Boolean deleted;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
	private Set<Book> books = new HashSet<Book>();

	public Category() {
	}

	public Category(String name, boolean isDeleted) {
		this.name = name;
		this.deleted = isDeleted;
	}

	@Override
	public String toString() {
		return "Category [categoryId=" + super.toString() + ", name=" + name + ", deleted=" + deleted + "]";
	}

}
