package com.fyp.mutrade.entity.common;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fyp.mutrade.annotion.ValidateEntity;
//Site Setting entity class
@Entity
@Table(name="fyp_site_setting")
@EntityListeners(AuditingEntityListener.class)
public class SiteSetting extends BaseEntity{

	private static final long serialVersionUID = 1L;
	//Site name 
	@ValidateEntity(required=true,requiredLeng=true,minLength=1,maxLength=64,errorRequiredMsg="Website name cannot be empty!",errorMinLengthMsg="Site name length must be greater than 1!",errorMaxLengthMsg="Site name length can't not be greater than 128!")
	@Column(name="site_name",nullable=false,length=128)
	private String siteName;
	public String getSiteName() {return siteName;}
	public void setSiteName(String siteName) {this.siteName = siteName;}
	
	//Site URL
	@ValidateEntity(required=true,requiredLeng=true,minLength=1,maxLength=256,errorRequiredMsg="Site URL cannot be empty!",errorMinLengthMsg="Site URL length must be greater than 1!",errorMaxLengthMsg="Site URL length can't not be greater than 128!")
	@Column(name="site_url",nullable=false,length=256)
	private String siteUrl;
	public String getSiteUrl() {return siteUrl;}
	public void setSiteUrl(String siteUrl) {this.siteUrl = siteUrl;}
	
	//Site logo 1
	@ValidateEntity(required=true,requiredLeng=true,minLength=1,maxLength=256,errorRequiredMsg="Site logo cannot be empty!",errorMinLengthMsg="Site logo length must be greater than 1!",errorMaxLengthMsg="Site logo length can't not be greater than 128!")
	@Column(name="logo_1",nullable=false,length=256)
	private String logo1;
	public String getLogo1() {return logo1;}
	public void setLogo1(String logo1) {this.logo1 = logo1;}
	
	//Site logo 2
	@ValidateEntity(required=true,requiredLeng=true,minLength=1,maxLength=256,errorRequiredMsg="Site logo cannot be empty!",errorMinLengthMsg="Site logo length must be greater than 1!",errorMaxLengthMsg="Site logo length can't not be greater than 128!")
	@Column(name="logo_2",nullable=false,length=256)
	private String logo2;
	public String getLogo2() {return logo2;}
	public void setLogo2(String logo2) {this.logo2 = logo2;}
	
	//Site QR code
	@ValidateEntity(required=true,requiredLeng=true,minLength=1,maxLength=256,errorRequiredMsg="Site QR code cannot be empty!",errorMinLengthMsg="Site QR code length must be greater than 1!",errorMaxLengthMsg="Site QR code length can't not be greater than 128!")
	@Column(name="qrcode",nullable=false,length=256)
	private String qrcode; 
	public String getQrcode() {return qrcode;}
	public void setQrcode(String qrcode) {this.qrcode = qrcode;}
	
	//Site right info 
	@ValidateEntity(required=true,requiredLeng=true,minLength=1,maxLength=256,errorRequiredMsg="Site right info cannot be empty!",errorMinLengthMsg="Site right info length must be greater than 1!",errorMaxLengthMsg="Site right info length can't not be greater than 128!")
	@Column(name="all_rights",nullable=false,length=256)
	private String allRights;
	public String getAllRights() {return allRights;}
	public void setAllRights(String allRights) {this.allRights = allRights;}
	
	@Override
	public String toString() {
		return "SiteSetting [siteName=" + siteName + ", siteUrl=" + siteUrl +", qrcode=" + qrcode + ", allRights=" + allRights + ", logo1=" + logo1 + ", logo2=" + logo2 + "]";
	}

	
	
	
	
	
	

	
	
	
	
}
