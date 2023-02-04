package com.tavi903.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "log")
@NamedQueries({
	@NamedQuery(name = "Log.count", query = "SELECT COUNT(l) FROM Log l"),
	@NamedQuery(name = "Log.findAll", query = "SELECT l FROM Log l ORDER BY l.date DESC") })
public class Log {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	private Timestamp date;
	private String exception;
	private String level;
	private String message;
	@Column(name = "stack_trace")
	private String stackTrace;
	
	public Log() {
	}

	@Builder
	public Log(Timestamp date, String exception, String level, String message, String stackTrace) {
		this.date = date;
		this.exception = exception;
		this.level = level;
		this.message = message;
		this.stackTrace = stackTrace;
	}

}