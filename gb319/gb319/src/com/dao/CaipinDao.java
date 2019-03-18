package com.dao;

import java.util.List;

import com.model.Caipin;

public interface CaipinDao {

	public void insertBean(Caipin bean);

	public void deleteBean(Caipin bean);

	public void updateBean(Caipin bean);

	public Caipin selectBean(String where);

	public List<Caipin> selectBeanList(final int start, final int limit, final String where);

	public int selectBeanCount(final String where);

}
