package com.fyp.mumarket.entity.common;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fyp.mumarket.annotion.ValidateEntity;

/**
 * Student entity
 * @author Administrator
 *
 */
@Entity
@Table(name="fyp_student")
@EntityListeners(AuditingEntityListener.class)
public class Student extends BaseEntity{

	public static final int STUDENT_STATUS_ENABLE = 1;//Student status enable
	public static final int STUDENT_STATUS_UNABLE = 0;//Student status unable
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ValidateEntity(required=true,requiredLeng=true,minLength=1,maxLength=18,errorRequiredMsg="Student number cannot be NULL!",errorMinLengthMsg="Student number length must be greater than 1!",errorMaxLengthMsg="Student number cannot be greater than 18!")
	@Column(name="sn",nullable=false,length=18,unique=true)
	private String sn;//Student number
	
	@ValidateEntity(required=true,requiredLeng=true,minLength=3,maxLength=18,errorRequiredMsg="Password cannnot be NULL!",errorMinLengthMsg="Password length must be greater than 3!",errorMaxLengthMsg="Password length cannot be greater than 18!")
	@Column(name="password",nullable=false,length=18)
	private String password;//Student password
	
	@ValidateEntity(required=false)
	@Column(name="head_pic",length=128)
	private String headPic;//Icon
	
	@ValidateEntity(required=false)
	@Column(name="nickname",length=32)
	private String nickname = "MU-student";//Nickname
	
	@ValidateEntity(required=false)
	@Column(name="mobile",length=18)
	private String mobile=" ";//Mobile number
	
	@ValidateEntity(required=false)
	@Column(name="school",length=18)
	private String school=" ";//school belong to
	
	@ValidateEntity(required=false)
	@Column(name="academy",length=18)
	private String academy = "Maynooth";//academy belong to
	
	@ValidateEntity(required=false)
	@Column(name="grade",length=18)
	private String grade=" ";//Year belong to
	
	@ValidateEntity(required=false)
	@Column(name="status",length=1)
	private int status = STUDENT_STATUS_ENABLE;//Student account status, default enable

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}


	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getAcademy() {
		return academy;
	}

	public void setAcademy(String academy) {
		this.academy = academy;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getHeadPic() {
		return headPic;
	}

	public void setHeadPic(String headPic) {
		this.headPic = headPic;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Student [sn=" + sn + ", password=" + password + ", headPic="
				+ headPic + ", nickname=" + nickname + ", mobile=" + mobile
				+ ", school=" + school + ", academy=" + academy
				+ ", grade=" + grade + ", status=" + status + "]";
	}

	

	
	

	

	
	

	
	
	
}
