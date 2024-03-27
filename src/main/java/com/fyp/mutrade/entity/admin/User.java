package com.fyp.mutrade.entity.admin;

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
 * Backend user entity class
 * @author Administrator
 *
 */
@Entity
@Table(name="fyp_user")
@EntityListeners(AuditingEntityListener.class)
public class User extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final int USER_SEX_MAN = 1;//Male
	
	private static final int USER_SEX_WOMAN = 2;//Female
	
	private static final int USER_SEX_UNKONW = 0;//Unknown
	
	public static final int ADMIN_USER_STATUS_ENABLE = 1;//user status enabled
	public static final int ADMIN_USER_STATUS_UNABLE = 0;//user status unable
	
	@ManyToOne
	@JoinColumn(name="role_id")
	private Role role;//The role user belong to
	
	@ValidateEntity(required=true,requiredLeng=true,minLength=4,maxLength=18,errorRequiredMsg="Username can't be empty",errorMinLengthMsg="Username length must be greater than 4",errorMaxLengthMsg="The username length can't be greater than 18")
	@Column(name="username",nullable=false,length=18,unique=true)
	private String username;//User name
	
	@ValidateEntity(required=true,requiredLeng=true,minLength=4,maxLength=32,errorRequiredMsg="The password can't be empty",errorMinLengthMsg="The password length must be greater than 1",errorMaxLengthMsg="Password length can't greater than 32")
	@Column(name="password",nullable=false,length=32)
	private String password;//Password
	
	@ValidateEntity(required=false)
	@Column(name="status",length=1)
	private int status = ADMIN_USER_STATUS_ENABLE;//Role status, by default, is enabled
	
	@ValidateEntity(required=false)
	@Column(name="head_pic",length=128)
	private String headPic;//User avatar
	
	@ValidateEntity(required=false)
	@Column(name="sex",length=1)
	private int sex = USER_SEX_UNKONW;//User gender
	
	@ValidateEntity(required=false)
	@Column(name="mobile",length=12)
	private String mobile;//User phone number
	
	@ValidateEntity(required=false)
	@Column(name="email",length=32)
	private String email;//User email

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getHeadPic() {
		return headPic;
	}

	public void setHeadPic(String headPic) {
		this.headPic = headPic;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "User [role=" + role + ", username=" + username + ", password="
				+ password + ", status=" + status + ", headPic=" + headPic
				+ ", sex=" + sex + ", mobile=" + mobile + ", email=" + email
				+ "]";
	}

	
	
	
}
