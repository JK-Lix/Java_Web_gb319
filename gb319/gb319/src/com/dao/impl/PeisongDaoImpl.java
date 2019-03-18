package com.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.dao.PeisongDao;
import com.model.Peisong;

public class PeisongDaoImpl extends HibernateDaoSupport implements PeisongDao {

	public void deleteBean(Peisong bean) {
		this.getHibernateTemplate().delete(bean);

	}

	public void insertBean(Peisong bean) {
		this.getHibernateTemplate().save(bean);

	}

	@SuppressWarnings("unchecked")
	public Peisong selectBean(String where) {
		List<Peisong> list = this.getHibernateTemplate().find("from Peisong " + where);
		if (list.size() == 0) {
			return null;
		}
		return list.get(0);
	}

	public int selectBeanCount(String where) {
		long count = (Long) this.getHibernateTemplate().find("select count(*) from Peisong " + where).get(0);
		return (int) count;
	}

	@SuppressWarnings("unchecked")
	public List<Peisong> selectBeanList(final int start, final int limit, final String where) {
		return (List<Peisong>) this.getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(final Session session) throws HibernateException, SQLException {
				List<Peisong> list = session.createQuery("from Peisong " + where).setFirstResult(start)
						.setMaxResults(limit).list();
				return list;
			}
		});
	}

	public void updateBean(Peisong bean) {
		this.getHibernateTemplate().update(bean);

	}

}
