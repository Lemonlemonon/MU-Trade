package com.fyp.mumarket.entity.admin;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fyp.mumarket.annotion.ValidateEntity;
import com.fyp.mumarket.entity.common.BaseEntity;

/**
 * Backend role entity class
 * @author Administrator
 *
 */
@Entity
@Table(name="fyp_role")
@EntityListeners(AuditingEntityListener.class)
public class Role extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final int ADMIN_ROLE_STATUS_ENABLE = 1;//Role status available
	public static final int ADMIN_ROLE_STATUS_UNABLE = 0;//Role status unavailable
	
	@ValidateEntity(required=true,requiredLeng=true,minLength=1,maxLength=18,errorRequiredMsg="Username can't be empty",errorMinLengthMsg="The username length must be greater than 1!",errorMaxLengthMsg="The username length can't be greater than 18!")
	@Column(name="name",nullable=false,length=18)
	private String name;//Role name
	
	@ValidateEntity(required=false)
	@Column(name="authorities")
	@ManyToMany
	private List<Menu> authorities;//List of permissions (menus) corresponding to a role
	
	@ValidateEntity(required=false)
	@Column(name="status",length=1)
	private int status = ADMIN_ROLE_STATUS_ENABLE;//Role status, by default, is enabled

	@ValidateEntity(required=false)
	@Column(name="remark",length=128)
	private String remark;//Role remark

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Menu> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(List<Menu> authorities) {
		this.authorities = authorities;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "Role [name=" + name + ", authorities=" + authorities
				+ ", status=" + status + ", remark=" + remark + "]";
	}
	
	
	
	
	
	
}
