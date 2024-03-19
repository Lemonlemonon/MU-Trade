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
 * Item category entity
 * @author Administrator
 *
 */
@Entity
@Table(name="fyp_ads_category")
@EntityListeners(AuditingEntityListener.class)
public class AdsCategory extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ValidateEntity(required=true,requiredLeng=true,minLength=1,maxLength=18,errorRequiredMsg="Category name cannot be NULL!",errorMinLengthMsg="Category name length must be greater than 1!",errorMaxLengthMsg="The category name length cannot exceed 18 characters!")
	@Column(name="name",nullable=false,length=18)
	private String name;//Category name
	
	@ManyToOne
	@JoinColumn(name="parent_id")
	private AdsCategory parent;//Parent category
	
	@ValidateEntity(required=false)
	@Column(name="icon",length=32)
	private String icon;//icon
	
	@Column(name="sort",nullable=false,length=4)
	private Integer sort = 0;//Sort value 0=ascending order

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public AdsCategory getParent() {
		return parent;
	}

	public void setParent(AdsCategory parent) {
		this.parent = parent;
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

	@Override
	public String toString() {
		return "AdsCategory [name=" + name + ", parent=" + parent + ", icon="
				+ icon + ", sort=" + sort + "]";
	}

	
	

	
	
	
}
