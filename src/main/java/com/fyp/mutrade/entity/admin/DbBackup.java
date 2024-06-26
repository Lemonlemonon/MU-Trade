package com.fyp.mutrade.entity.admin;
/**
 * Database backup record entity class
 */
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fyp.mutrade.entity.common.BaseEntity;

@Entity
@Table(name="fyp_db_backup")
@EntityListeners(AuditingEntityListener.class)
public class DbBackup extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name="filename",nullable=false,length=200)
	private String filename;//backup file name
	
	@Column(name="filepath",nullable=false,length=128)
	private String filepath;//backup file directory

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	@Override
	public String toString() {
		return "DbBackup [filename=" + filename + ", filepath=" + filepath
				+ "]";
	}

	
	
	
	
	
	
	
	
}
