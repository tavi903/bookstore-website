package com.tavi903.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import lombok.Data;

@Data
@MappedSuperclass
public abstract class BaseEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "created_at", updatable = false)
	private Timestamp createdAt;
	
	@Column(name = "created_by", updatable = false)
	private String createdBy;
	
	@Version
	@Column(name = "last_update")
	private Timestamp lastUpdate;
	
	@Column(name = "updated_by")
	private String updatedBy;

}
