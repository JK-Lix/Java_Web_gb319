package com.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

//菜品
@Entity
@Table(name = "t_Caipin")
public class Caipin {

	@Id
	@GeneratedValue
	private int id;

	private int deletestatus;// 表示是否删除的状态，0表示未删除，1表示删除

	@ManyToOne
	@JoinColumn(name = "leibieid")
	private Leibie leibie;// 菜品类别，外键

	private String mingchen;// 菜品名称

	private String yuanjia;// 原价

	private String jiage;// 优惠价

	private String tupian;// 图片

	@Column(name = "miaoshu", columnDefinition = "TEXT")
	private String miaoshu;// 描述

	private String shijian;// 添加时间

	private String tuijian;// 是否推荐

	private int dianji;// 点击数

	private int buy;// 购买数

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

	public Leibie getLeibie() {
		return leibie;
	}

	public void setLeibie(Leibie leibie) {
		this.leibie = leibie;
	}

	public String getMingchen() {
		return mingchen;
	}

	public void setMingchen(String mingchen) {
		this.mingchen = mingchen;
	}

	public String getYuanjia() {
		return yuanjia;
	}

	public void setYuanjia(String yuanjia) {
		this.yuanjia = yuanjia;
	}

	public String getJiage() {
		return jiage;
	}

	public void setJiage(String jiage) {
		this.jiage = jiage;
	}

	public String getTupian() {
		return tupian;
	}

	public void setTupian(String tupian) {
		this.tupian = tupian;
	}

	public String getMiaoshu() {
		return miaoshu;
	}

	public void setMiaoshu(String miaoshu) {
		this.miaoshu = miaoshu;
	}

	public String getShijian() {
		return shijian;
	}

	public void setShijian(String shijian) {
		this.shijian = shijian;
	}

	public String getTuijian() {
		return tuijian;
	}

	public void setTuijian(String tuijian) {
		this.tuijian = tuijian;
	}

	public int getDianji() {
		return dianji;
	}

	public void setDianji(int dianji) {
		this.dianji = dianji;
	}

	public int getBuy() {
		return buy;
	}

	public void setBuy(int buy) {
		this.buy = buy;
	}

}
