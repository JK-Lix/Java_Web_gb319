package com.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

//购物车
@Entity
@Table(name = "t_Gouwuche")
public class Gouwuche {

	@Id
	@GeneratedValue
	private int id;// 主键

	@ManyToOne
	@JoinColumn(name = "userid")
	private User user;// 用户 关联用户的ID 外键

	@ManyToOne
	@JoinColumn(name = "caipinid")
	private Caipin caipin;// 关联的菜品，外键

	private String createtime;// 添加时间

	private double total;// 小计

	private int sl;// 数量

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public int getSl() {
		return sl;
	}

	public void setSl(int sl) {
		this.sl = sl;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public Caipin getCaipin() {
		return caipin;
	}

	public void setCaipin(Caipin caipin) {
		this.caipin = caipin;
	}

}
