package com.dao;

import java.util.List;

import com.model.Peisong;

public interface PeisongDao {

	public void insertBean(Peisong bean);

	public void deleteBean(Peisong bean);

	public void updateBean(Peisong bean);

	public Peisong selectBean(String where);

	public List<Peisong> selectBeanList(final int start, final int limit, final String where);

	public int selectBeanCount(final String where);

}
