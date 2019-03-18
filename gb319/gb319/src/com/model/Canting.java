package com.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

//餐厅最新信息
@Entity
@Table(name = "t_Canting")
public class Canting {

	@Id
	@GeneratedValue
	private int id;

	private int deletestatus;// 表示是否删除的状态，0表示未删除，1表示删除

	private String ctitle;// 信息标题

	private String tupian;// 图片

	@Column(name = "neirong", columnDefinition = "TEXT")
	private String neirong;// 信息内容

	private String shijian;// 添加时间

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

	public String getCtitle() {
		return ctitle;
	}

	public void setCtitle(String ctitle) {
		this.ctitle = ctitle;
	}

	public String getNeirong() {
		return neirong;
	}

	public void setNeirong(String neirong) {
		this.neirong = neirong;
	}

	public String getShijian() {
		return shijian;
	}

	public void setShijian(String shijian) {
		this.shijian = shijian;
	}

	public String getTupian() {
		return tupian;
	}

	public void setTupian(String tupian) {
		this.tupian = tupian;
	}

}
