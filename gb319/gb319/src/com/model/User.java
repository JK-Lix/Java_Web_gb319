package com.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

//用户
@Entity
@Table(name = "t_User")
public class User {

	@Id
	@GeneratedValue
	private int id;

	private int deletestatus;// 表示是否删除的状态，0表示未删除，1表示删除

	private String username;// 用户名

	private String password;// 密码

	private String createtime;// 添加时间

	private int role;// 0表示管理员 1表示用户

	private String xingming;// 姓名

	private String dianhua;// 联系电话

	private String address;// 地址

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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public String getXingming() {
		return xingming;
	}

	public void setXingming(String xingming) {
		this.xingming = xingming;
	}

	public String getDianhua() {
		return dianhua;
	}

	public void setDianhua(String dianhua) {
		this.dianhua = dianhua;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
