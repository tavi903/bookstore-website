package com.tavi903.entity;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "order_detail")
public class OrderDetail extends BaseEntity {

	@Embedded
	private OrderDetailId orderDetailId;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "book_id", insertable = false, updatable = false)
	private Book book;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "order_id", insertable = false, updatable = false)
	private BookOrder bookOrder;

	public OrderDetail() {
	}

	public OrderDetail(Book book, BookOrder bookOrder) {
		this.book = book;
		this.bookOrder = bookOrder;
	}

	public Book getBook() {
		return this.book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public BookOrder getBookOrder() {
		return this.bookOrder;
	}

	public void setBookOrder(BookOrder bookOrder) {
		this.bookOrder = bookOrder;
	}

}
