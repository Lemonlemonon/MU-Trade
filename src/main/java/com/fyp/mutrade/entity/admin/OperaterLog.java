package com.fyp.mutrade.entity.admin;
/**
 * "Backend operation log recording table
 */
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fyp.mutrade.entity.common.BaseEntity;


@Entity
@Table(name="fyp_operater_log")
@EntityListeners(AuditingEntityListener.class)
public class OperaterLog extends BaseEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name="operator",nullable=false,length=18)
	private String operator;//Operator
	
	@Column(name="content",nullable=false,length=128)
	private String content;//operation content
	
	public String getOperator() {
		return operator;
	}

	public void setOperator(String operater) {
		this.operator = operater;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	

	
	
}
