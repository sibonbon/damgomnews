package com.example.demo.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.hibernate.envers.Audited;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;

@MappedSuperclass
@Data
@EntityListeners(value = (AuditingEntityListener.class))
@Audited
public class BaseEntity {

	@Column(updatable = false)//업데이트 할수없음
	@CreatedDate
	LocalDateTime createdDate;
	@Column
	@LastModifiedDate
	LocalDateTime lastModifiedDate;
	@Column(updatable = false)
	@CreatedBy
	String createdBy;
	@Column
	@LastModifiedBy
	String lastModifiedBy;
}
