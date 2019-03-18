package com.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

//菜品类别
@Entity
@Table(name = "t_Leibie")
public class Leibie {

	@Id
	@GeneratedValue
	private int id;

	private int deletestatus;// 表示是否删除的状态，0表示未删除，1表示删除

	private String lname;// 菜品类别名

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDeletestatus() {
		return deletestatus;
	}

	public void setDeletestatus(int deletestatus) {
		this.deletestatus = deletestatus;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

}
