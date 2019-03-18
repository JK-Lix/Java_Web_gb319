package com.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.dao.LeibieDao;
import com.model.Leibie;

public class LeibieDaoImpl extends HibernateDaoSupport implements LeibieDao {

	public void deleteBean(Leibie bean) {
		this.getHibernateTemplate().delete(bean);

	}

	public void insertBean(Leibie bean) {
		this.getHibernateTemplate().save(bean);

	}

	@SuppressWarnings("unchecked")
	public Leibie selectBean(String where) {
		List<Leibie> list = this.getHibernateTemplate().find("from Leibie " + where);
		if (list.size() == 0) {
			return null;
		}
		return list.get(0);
	}

	public int selectBeanCount(String where) {
		long count = (Long) this.getHibernateTemplate().find("select count(*) from Leibie " + where).get(0);
		return (int) count;
	}

	@SuppressWarnings("unchecked")
	public List<Leibie> selectBeanList(final int start, final int limit, final String where) {
		return (List<Leibie>) this.getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(final Session session) throws HibernateException, SQLException {
				List<Leibie> list = session.createQuery("from Leibie " + where).setFirstResult(start)
						.setMaxResults(limit).list();
				return list;
			}
		});
	}

	public void updateBean(Leibie bean) {
		this.getHibernateTemplate().update(bean);

	}

}
