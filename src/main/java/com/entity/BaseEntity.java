package com.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.util.DateUtil;

/**
 * 抽象的基础模型，用于定义所有的模型的公用属性
 * 
 * @author tan
 *
 */
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7790316836758753272L;

	private Integer id;
	private String createUser;
	private String editUser;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")    
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")	
	@Column(name="create_time",columnDefinition="datetime comment '更新时间'")
	private Date createTime;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")    
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")	
	@Column(name="edit_time",columnDefinition="datetime comment '更新时间'")
	private Date editTime;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getEditUser() {
		return editUser;
	}

	public void setEditUser(String editUser) {
		this.editUser = editUser;
	}

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "createTime", columnDefinition = "datetime comment '更新时间'")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "editTime", columnDefinition = "datetime comment '更新时间'")
	public Date getEditTime() {
		return editTime;
	}

	public void setEditTime(Date editTime) {
		this.editTime = editTime;
	}

	@PrePersist // save
	protected void onCreate() {
		if (createTime == null)
			this.createTime = DateUtil.getNowFull();
		if (editTime == null)
			this.editTime = createTime;
	}

	@PreUpdate // update
	protected void onUpdate() {
		if (editTime == null)
			this.editTime = DateUtil.getNowFull();
		if (createTime == null)
			this.createTime = editTime;
	}

	@Override
	public int hashCode() {
		return id == null ? System.identityHashCode(this) : id.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass().getPackage() != obj.getClass().getPackage()) {
			return false;
		}
		final BaseEntity other = (BaseEntity) obj;
		if (id == null) {
			if (other.getId() != null) {
				return false;
			}
		} else if (!id.equals(other.getId())) {
			return false;
		}
		return true;
	}
}
