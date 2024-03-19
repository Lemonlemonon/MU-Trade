package com.fyp.mumarket.entity.common;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import com.fyp.mumarket.annotion.ValidateEntity;
//Related site entity
@Entity
@Table(name="fyp_related_site")
@EntityListeners(AuditingEntityListener.class)
public class RelatedSite extends BaseEntity{

	private static final long serialVersionUID = 1L;
	
	@ValidateEntity(required=true,requiredLeng=true,minLength=1,maxLength=64,errorRequiredMsg="Site Name cannot be NULL!",errorMinLengthMsg="Site Name length must be greater than 1!",errorMaxLengthMsg="Site Name length cannot be greater than 64!")
	@Column(name="name",nullable=false,length=64)
	private String name;//Site name
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@ValidateEntity(required=true,requiredLeng=true,minLength=1,maxLength=256,errorRequiredMsg="Site URL cannot be NULL!",errorMinLengthMsg="Site URL length must be greater than 1!",errorMaxLengthMsg="Site URL length cannot be greater than 256!")
	@Column(name="url",nullable=false,length=256)
	private String url;//Site URL
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	@Column(name="sort",nullable=false,length=4)
	private Integer sort = 0;//Sort value 0=ascending order
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}

	@Override
	public String toString() {
		return "RelatedSite [name=" + name + ", url=" + url + ", sort=" + sort
				+ "]";
	}	
	
}
