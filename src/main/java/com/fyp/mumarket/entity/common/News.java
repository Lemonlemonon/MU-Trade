package com.fyp.mumarket.entity.common;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import com.fyp.mumarket.annotion.ValidateEntity;
//News entity
@Entity
@Table(name="fyp_news")
@EntityListeners(AuditingEntityListener.class)
public class News extends BaseEntity{
	private static final long serialVersionUID = 1L;
	//News Title
	@ValidateEntity(required=true,requiredLeng=true,minLength=1,maxLength=64,errorRequiredMsg="News title cannot be empty!",errorMinLengthMsg="New title length must be greater than 1!",errorMaxLengthMsg="News title length cannot be more than 64!")
	@Column(name="title",nullable=false,length=64)
	private String title;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	//News content
	@ValidateEntity(required=true,requiredLeng=true,minLength=1,maxLength=10000,errorRequiredMsg="News content cannot be empty!",errorMinLengthMsg="News content length must be greater than 1!",errorMaxLengthMsg="News content length cannot be more than 10000!")
	@Column(name="content",nullable=false,length=10024)
	private String content;
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	//View number
	@Column(name="view_number",nullable=false,length=8)
	private Integer viewNumber = 0;
	public Integer getViewNumber() {
		return viewNumber;
	}
	public void setViewNumber(Integer viewNumber) {
		this.viewNumber = viewNumber;
	}
	
	//Sort value
	@Column(name="sort",nullable=false,length=4)
	private Integer sort = 0;//Category order, sorted in ascending order by default, with a default value of 0
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}

	@Override
	public String toString() {
		return "News [title=" + title + ", content=" + content
				+ ", viewNumber=" + viewNumber + ", sort=" + sort + "]";
	}

	
	
	
	
}
