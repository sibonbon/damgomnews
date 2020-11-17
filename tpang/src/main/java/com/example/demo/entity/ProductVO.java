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
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)//데이터를 가져오는 시점 eager은 바로바로 가져옴 
	//lazy는 나중에 불러옴 결속은 약해지고 성능은 빨라짐 뉴스만 가져오고 댓글은 요청시
	//cascade로 전체 수정가능 
	@JoinColumn(name="product_id")
	Collection<ProductReviewVO> reviews;
}
