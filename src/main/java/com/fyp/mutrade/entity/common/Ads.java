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
 * Item entity
 * @author Administrator
 *
 */
@Entity
@Table(name="fyp_ads")
@EntityListeners(AuditingEntityListener.class)
public class Ads extends BaseEntity{

	public static final int ADS_STATUS_UP = 1;//Selling
	public static final int ADS_STATUS_DOWN = 2;//Withdraw
	public static final int ADS_STATUS_SOLD = 3;//Sold
	
	public static final int ADS_FLAG_ON = 1;//Highlighted
	public static final int ADS_FLAG_OFF = 0;//Not highlighted

	public static final int ADS_RECOMMEND_OFF = 0;//Recommend off
	public static final int ADS_RECOMMEND_ON = 1;//Recommended
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name="student_id")
	private Student student;//Student belong to
	
	@ValidateEntity(required=true,requiredLeng=true,minLength=1,maxLength=30,errorRequiredMsg="Item name cannot be NULL!",errorMinLengthMsg="Name length should be longer than 1 characters!",errorMaxLengthMsg="The item name length cannot exceed 30 characters!")
	@Column(name="name",nullable=false,length=32)
	private String name;//Item name
	
	@ManyToOne
	@JoinColumn(name="ads_category_id")
	private AdsCategory adsCategory;//Category
	
	@ValidateEntity(required=true,errorRequiredMsg="\"Please enter the original price",requiredMinValue=true,errorMinValueMsg="Price cannot lower than 0!")
	@Column(name="buy_price",nullable=false,length=8)
	private Float buyPrice;//original price
	
	@ValidateEntity(required=true,errorRequiredMsg="Please enter the selling price",requiredMinValue=true,errorMinValueMsg="Price cannot lower than 0!")
	@Column(name="sell_price",nullable=false,length=8)
	private Float sellPrice;//selling price
	
	@ValidateEntity(required=true,errorRequiredMsg="Please upload images for your item!")
	@Column(name="photo",nullable=false,length=128)
	private String photo;//Images string
	
	@Column(name="status",nullable=false,length=1)
	private int status = ADS_STATUS_UP;//Item status, set default as Selling
	
	@Column(name="flag",nullable=false,length=1)
	private int flag = ADS_FLAG_OFF;//Item highlighting, default as off
	
	@Column(name="recommend",nullable=false,length=1)
	private int recommend = ADS_RECOMMEND_OFF;//Item if recommended,default off 
	
	@ValidateEntity(required=true,requiredLeng=true,minLength=1,maxLength=1000,errorRequiredMsg="Item description cannot be NULL!",errorMinLengthMsg="The item description length must be greater than 1!",errorMaxLengthMsg="The item description length cannot exceed 1000 characters!")
	@Column(name="content",nullable=false,length=1024)
	private String content;//Item description 
	
	@Column(name="view_number",nullable=false,length=8)
	private int viewNumber = 0;//Item page views

	@Column(name="ads_bidding_id")
	private Long adsBiddingId;//Ads Bidding id

	public Long getAdsBiddingId() {
		return adsBiddingId;
	}

	public void setAdsBiddingId(Long adsBiddingId) {
		this.adsBiddingId = adsBiddingId;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public AdsCategory getAdsCategory() {
		return adsCategory;
	}

	public void setAdsCategory(AdsCategory adsCategory) {
		this.adsCategory = adsCategory;
	}

	public Float getBuyPrice() {
		return buyPrice;
	}

	public void setBuyPrice(Float buyPrice) {
		this.buyPrice = buyPrice;
	}

	public Float getSellPrice() {
		return sellPrice;
	}

	public void setSellPrice(Float sellPrice) {
		this.sellPrice = sellPrice;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public int getRecommend() {
		return recommend;
	}

	public void setRecommend(int recommend) {
		this.recommend = recommend;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	
	
	public int getViewNumber() {
		return viewNumber;
	}

	public void setViewNumber(int viewNumber) {
		this.viewNumber = viewNumber;
	}

	@Override
	public String toString() {
		return "Ads [student=" + student + ", name=" + name
				+ ", adsCategory=" + adsCategory + ", buyPrice=" + buyPrice
				+ ", sellPrice=" + sellPrice + ", photo=" + photo + ", status="
				+ status + ", flag=" + flag + ", recommend=" + recommend
				+ ", content=" + content + ", viewNumber=" + viewNumber + "]";
	}

	
	


	
}
