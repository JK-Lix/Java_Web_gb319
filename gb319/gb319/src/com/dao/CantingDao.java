package com.dao;

import java.util.List;

import com.model.Canting;

public interface CantingDao {

	public void insertBean(Canting bean);

	public void deleteBean(Canting bean);

	public void updateBean(Canting bean);

	public Canting selectBean(String where);

	public List<Canting> selectBeanList(final int start, final int limit, final String where);

	public int selectBeanCount(final String where);

}
