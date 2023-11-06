package com.fyp.mumarket.entity.admin;

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
 * Backend menu entity class
 * @author Administrator
 *
 */
@Entity
@Table(name="fyp_menu")
@EntityListeners(AuditingEntityListener.class)
public class Menu extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ValidateEntity(required=true,requiredLeng=true,minLength=1,maxLength=18,errorRequiredMsg="Menu name can't be empty",errorMinLengthMsg="The menu name length must be greater than 1!",errorMaxLengthMsg="The menu name length can't be greater than 18!")
	@Column(name="name",nullable=false,length=18)
	private String name;//Menu name
	
	@ManyToOne
	@JoinColumn(name="parent_id")
	private Menu parent;//Menu parent category
	
	@ValidateEntity(required=false)
	@Column(name="url",length=128)
	private String url;//Menu url

	@ValidateEntity(required=false)
	@Column(name="icon",length=32)
	private String icon;//Menu icon
	
	@Column(name="sort",nullable=false,length=4)
	private Integer sort = 0;//Menu order, by default in ascending order, with a default value of 0

	@Column(name="is_bitton",nullable=false)
	private boolean isButton = false;
	
	@Column(name="is_show",nullable=false)
	private boolean isShow = true;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Menu getParent() {
		return parent;
	}

	public void setParent(Menu parent) {
		this.parent = parent;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	
	
	public boolean isButton() {
		return isButton;
	}

	public void setButton(boolean isButton) {
		this.isButton = isButton;
	}

	public boolean isShow() {
		return isShow;
	}

	public void setShow(boolean isShow) {
		this.isShow = isShow;
	}

	@Override
	public String toString() {
		return "Menu [name=" + name + ", parent=" + parent + ", url=" + url
				+ ", icon=" + icon + ", sort=" + sort + ", isButton="
				+ isButton + ", isShow=" + isShow + "]";
	}

	

	
	
	
}
