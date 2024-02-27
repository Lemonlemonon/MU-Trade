package com.fyp.mumarket.entity.common;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fyp.mumarket.annotion.ValidateEntity;
import com.fyp.mumarket.entity.common.BaseEntity;

/**
 * Wanted Goods entity
 * @author Administrator
 *
 */
@Entity
@Table(name="fyp_wanted_goods")
@EntityListeners(AuditingEntityListener.class)
public class WantedGoods extends BaseEntity{

	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name="student_id")
	private Student student;//Student belong to
	
	@ValidateEntity(required=true,requiredLeng=true,minLength=1,maxLength=30,errorRequiredMsg="Name cannot be empty!",errorMinLengthMsg="Name need to be longer than 1!",errorMaxLengthMsg="Name can not be longer than 30!")
	@Column(name="name",nullable=false,length=32)
	private String name;//Name
	
	@ValidateEntity(required=true,errorRequiredMsg="Enter expecting price!",requiredMinValue=true,errorMinValueMsg="expecting price can not be less than 0!")
	@Column(name="sell_price",nullable=false,length=8)
	private Float sellPrice;//Price
	
	@ValidateEntity(required=true,minLength=1,maxLength=128,errorRequiredMsg="Location cannot be empty!",errorMinLengthMsg="Location length need to be longer than 1",errorMaxLengthMsg="Location length can not be longer than 100!")
	@Column(name="trade_place",nullable=false,length=128)
	private String tradePlace;//Trade location
	
	@ValidateEntity(required=true,requiredLeng=true,minLength=1,maxLength=1000,errorRequiredMsg="Description can not be empty",errorMinLengthMsg="Description length need to be longer than 1!",errorMaxLengthMsg="Description length can not be longer than 1000!")
	@Column(name="content",nullable=false,length=1024)
	private String content;//Description
	
	@Column(name="view_number",nullable=false,length=8)
	private int viewNumber = 0;//View number

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Float getSellPrice() {
		return sellPrice;
	}

	public void setSellPrice(Float sellPrice) {
		this.sellPrice = sellPrice;
	}

	public String getTradePlace() {
		return tradePlace;
	}

	public void setTradePlace(String tradePlace) {
		this.tradePlace = tradePlace;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getViewNumber() {
		return viewNumber;
	}

	public void setViewNumber(int viewNumber) {
		this.viewNumber = viewNumber;
	}

	@Override
	public String toString() {
		return "WantedGoods [student=" + student + ", name=" + name
				+ ", sellPrice=" + sellPrice + ", tradePlace=" + tradePlace
				+ ", content=" + content + ", viewNumber=" + viewNumber + "]";
	}

	
	


	
}
