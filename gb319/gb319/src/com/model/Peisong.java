package com.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

//配送订单
@Entity
@Table(name = "t_Peisong")
public class Peisong {

	@Id
	@GeneratedValue
	private int id;// 主键

	private int deletestatus;// 表示是否删除的状态，0表示未删除，1表示删除

	@ManyToOne
	@JoinColumn(name = "dingdanid")
	private Dingdan dingdan;// 关联的订单，外键

	private String shijian;// 录入时间

	@Column(name = "info", columnDefinition = "TEXT")
	private String info;// 配送信息

	private String chuanda;// 配送是否送达

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Dingdan getDingdan() {
		return dingdan;
	}

	public void setDingdan(Dingdan dingdan) {
		this.dingdan = dingdan;
	}

	public String getShijian() {
		return shijian;
	}

	public void setShijian(String shijian) {
		this.shijian = shijian;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public int getDeletestatus() {
		return deletestatus;
	}

	public void setDeletestatus(int deletestatus) {
		this.deletestatus = deletestatus;
	}

	public String getChuanda() {
		return chuanda;
	}

	public void setChuanda(String chuanda) {
		this.chuanda = chuanda;
	}

}
