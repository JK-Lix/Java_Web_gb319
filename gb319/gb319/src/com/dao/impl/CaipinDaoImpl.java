package com.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.dao.CaipinDao;
import com.model.Caipin;

public class CaipinDaoImpl extends HibernateDaoSupport implements CaipinDao {

	public void deleteBean(Caipin bean) {
		this.getHibernateTemplate().delete(bean);

	}

	public void insertBean(Caipin bean) {
		this.getHibernateTemplate().save(bean);

	}

	@SuppressWarnings("unchecked")
	public Caipin selectBean(String where) {
		List<Caipin> list = this.getHibernateTemplate().find("from Caipin " + where);
		if (list.size() == 0) {
			return null;
		}
		return list.get(0);
	}

	public int selectBeanCount(String where) {
		long count = (Long) this.getHibernateTemplate().find("select count(*) from Caipin " + where).get(0);
		return (int) count;
	}

	@SuppressWarnings("unchecked")
	public List<Caipin> selectBeanList(final int start, final int limit, final String where) {
		return (List<Caipin>) this.getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(final Session session) throws HibernateException, SQLException {
				List<Caipin> list = session.createQuery("from Caipin " + where).setFirstResult(start)
						.setMaxResults(limit).list();
				return list;
			}
		});
	}

	public void updateBean(Caipin bean) {
		this.getHibernateTemplate().update(bean);

	}

}
