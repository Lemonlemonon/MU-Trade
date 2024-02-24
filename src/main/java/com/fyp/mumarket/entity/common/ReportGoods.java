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
 * Report entity
 * @author Administrator
 *
 */
@Entity
@Table(name="fyp_report_goods")
@EntityListeners(AuditingEntityListener.class)
public class ReportGoods extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name="student_id")
	private Student student;//Student object belong to
	
	@ManyToOne
	@JoinColumn(name="goods_id")
	private Goods goods;//Goods object belong to
	
	@ValidateEntity(required=true,requiredLeng=true,minLength=1,maxLength=1000,errorRequiredMsg="Reason cannot be empty",errorMinLengthMsg="Length need to be greater than 1!",errorMaxLengthMsg="Reason cannot be greater than 10000")
	@Column(name="content",nullable=false,length=1024)
	private String content;//Report reason

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "RrportGoods [student=" + student + ", goods=" + goods
				+ ", content=" + content + "]";
	}
	
	
}
