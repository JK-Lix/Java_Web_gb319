package com.dao;

import java.util.List;

import com.model.Leibie;

public interface LeibieDao {

	public void insertBean(Leibie bean);

	public void deleteBean(Leibie bean);

	public void updateBean(Leibie bean);

	public Leibie selectBean(String where);

	public List<Leibie> selectBeanList(final int start, final int limit, final String where);

	public int selectBeanCount(final String where);

}
