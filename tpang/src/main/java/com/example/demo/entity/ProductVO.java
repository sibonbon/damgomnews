package com.example.demo.entity;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.hibernate.envers.Audited;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name="product")
@Audited
public class ProductVO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;
	@Column
	String companyName;
	@Column
	String productName;
	@Column
	String productDetail;
	@Column
	int productPrice;
	@Column
	int productQuantity;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)//�����͸� �������� ���� eager�� �ٷιٷ� ������ 
	//lazy�� ���߿� �ҷ��� ����� �������� ������ ������ ������ �������� ����� ��û��
	//cascade�� ��ü �������� 
	@JoinColumn(name="product_id")
	Collection<ProductReviewVO> reviews;
}
