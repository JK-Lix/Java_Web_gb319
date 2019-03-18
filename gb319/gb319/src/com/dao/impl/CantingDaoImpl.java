package com.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.dao.CantingDao;
import com.model.Canting;

public class CantingDaoImpl extends HibernateDaoSupport implements CantingDao {

	public void deleteBean(Canting bean) {
		this.getHibernateTemplate().delete(bean);

	}

	public void insertBean(Canting bean) {
		this.getHibernateTemplate().save(bean);

	}

	@SuppressWarnings("unchecked")
	public Canting selectBean(String where) {
		List<Canting> list = this.getHibernateTemplate().find("from Canting " + where);
		if (list.size() == 0) {
			return null;
		}
		return list.get(0);
	}

	public int selectBeanCount(String where) {
		long count = (Long) this.getHibernateTemplate().find("select count(*) from Canting " + where).get(0);
		return (int) count;
	}

	@SuppressWarnings("unchecked")
	public List<Canting> selectBeanList(final int start, final int limit, final String where) {
		return (List<Canting>) this.getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(final Session session) throws HibernateException, SQLException {
				List<Canting> list = session.createQuery("from Canting " + where).setFirstResult(start)
						.setMaxResults(limit).list();
				return list;
			}
		});
	}

	public void updateBean(Canting bean) {
		this.getHibernateTemplate().update(bean);

	}

}
