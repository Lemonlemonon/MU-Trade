package com.fyp.mutrade.entity.common;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fyp.mutrade.annotion.ValidateEntity;
import com.fyp.mutrade.entity.common.BaseEntity;

/**
 * Comment entity
 * @author Administrator
 *
 */
@Entity
@Table(name="fyp_comment")
@EntityListeners(AuditingEntityListener.class)
public class Comment extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name="student_id")
	private Student student;//Comment student
	
	@ManyToOne
	@JoinColumn(name="ads_id")
	private Ads ads;//Comment item
	
	@ValidateEntity(required=true,requiredLeng=true,minLength=1,maxLength=1000,errorRequiredMsg="Comment can not be empty",errorMinLengthMsg="Length not enough!",errorMaxLengthMsg="length can not be greater than 10000!")
	@Column(name="content",nullable=false,length=1024)
	private String content;//Comment content
	
	
	@Column(name="reply_to",length=64)
	private String replyTo;//Replier

	
	
	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Ads getAds() {
		return ads;
	}

	public void setAds(Ads ads) {
		this.ads = ads;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getReplyTo() {
		return replyTo;
	}

	public void setReplyTo(String replyTo) {
		this.replyTo = replyTo;
	}

	@Override
	public String toString() {
		return "Comment [student=" + student + ", ads=" + ads
				+ ", content=" + content + ", replyTo=" + replyTo + "]";
	}

	
	
}
