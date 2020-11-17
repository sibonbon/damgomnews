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
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

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
@Entity(name="news_article")
@Audited
public class NewsArticleVO extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;
	@Column
	String title;
	@Column
	String contents;
	@Column
	String author;
	@Column
	String category;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)//�����͸� �������� ���� eager�� �ٷιٷ� ������ 
	//lazy�� ���߿� �ҷ��� ����� �������� ������ ������ ������ �������� ����� ��û��
	//cascade�� ��ü �������� 
	@JoinColumn(name="news_article_id")
	Collection<NewsReplyVO> replies;
}
