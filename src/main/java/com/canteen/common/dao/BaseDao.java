package com.canteen.common.dao;

import java.math.BigDecimal;
import java.util.List;

import com.canteen.common.utils.Page;
import com.canteen.entity.BaseEntity;
import org.apache.commons.lang.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

public class BaseDao extends HibernateDaoSupport implements IBaseDao {

	public boolean delOrUpdate(String hql, Object[] params)
			throws HibernateException {
		if (null == hql || hql.equals("")) {
			return false;
		}
		Query query = this.getCurrSession().createQuery(hql);
		if (null != params && params.length > 0) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i, params[i]);
			}
		}
		int result = query.executeUpdate();
		return result > 0 ? true : false;
	}

	public Page findPage(String hql, int row, int pages)
			throws HibernateException {
		if (null == hql || hql.equals("")) {
			return null;
		}
		Session session = this.getCurrSession();
		Query query = session.createQuery(hql);
		if (row > 0 && pages > 0) {
			// 取得每页显示结果集
			query.setMaxResults(row);
			// 取得当前页码所要显示的结果集
			query.setFirstResult(row * (pages - 1));
		}
		// 获取总数
		int total = this.listCount(hql,null);
		Page page = new Page(row * (pages - 1), total, row, query.list());
		return page;
	}

	public Page findPage(String hql, Object[] params, int row, int pages)
			throws HibernateException {

		if (null == hql || hql.equals("")) {
			return null;
		}
		Query query = this.getCurrSession().createQuery(hql);
		if (row > 0 && pages > 0) {
			// 取得每页显示结果集
			query.setMaxResults(row);
			// 取得当前页码所要显示的结果集
			query.setFirstResult(row * (pages - 1));
		}
		if (null != params && params.length > 0) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i, params[i]);
			}
		}
		// 获取总数
		int total = this.listCount(hql, params);
		Page page = new Page(row * (pages - 1), total, row, query.list());
		return page;
	}

	/**
	 * 获取当前session
	 */
	public Session getCurrSession() throws HibernateException {
		SessionFactory sessionFactory = this.getHibernateTemplate()
				.getSessionFactory();
		Session session = null;
		boolean fail = false;
		try {
			session = sessionFactory.getCurrentSession();
		} catch (Exception ex) {
			fail = true;
		}
		if (fail || session == null) {
			session = sessionFactory.openSession();
		}
		return session;
	}

	public int listCount(String hql, Object[] params)
			throws HibernateException, NumberFormatException {
		String sql=hql.substring(hql.indexOf("from"));
		sql="select count(*) "+sql;
		Query query = getCurrSession().createQuery(sql);
		if (null != params && params.length > 0) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i, params[i]);
			}
		}
		return ((Long) query.uniqueResult()).intValue();

	}
	
	public int update(String sql, Object[] params)
			throws HibernateException {
		if (null == sql || sql.equals("")) {
			return -1;
		}
		Query query = getCurrSession().createQuery(sql);
		if (null != params && params.length > 0) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i, params[i]);
			}
		}
		return query.executeUpdate();
	}

	public void save(BaseEntity entity) {
		this.getHibernateTemplate().save(entity);
	}

	public void update(BaseEntity entity) {
		this.getHibernateTemplate().update(entity);
	}

	public BaseEntity findById(String id,Class className){
		return (BaseEntity)this.getHibernateTemplate().get(className, id);
	}
	

	public Page findPageBySql(String sql, int row, int pages,Class className) {
		if (null == sql || sql.equals("")) {
			return null;
		}
		Session session = this.getCurrSession();
		SQLQuery query = session.createSQLQuery(sql);
		if (row > 0 && pages > 0) {
			// 取得每页显示结果集
			query.setMaxResults(row);
			// 取得当前页码所要显示的结果集
			query.setFirstResult(row * (pages - 1));
		}
		// 获取总数
		int total = this.listCountBySql(sql,null);
		query.addEntity(className);
		Page page = new Page(row * (pages - 1), total, row, query.list());
		return page;
	}
	
	public Page findPageBySql(String sql, int row, int pages) {
		if (null == sql || sql.equals("")) {
			return null;
		}
		Session session = this.getCurrSession();
		SQLQuery query = session.createSQLQuery(sql);
		if (row > 0 && pages > 0) {
			// 取得每页显示结果集
			query.setMaxResults(row);
			// 取得当前页码所要显示的结果集
			query.setFirstResult(row * (pages - 1));
		}
		// 获取总数
		int total = this.listCountBySql(sql,null);
		Page page = new Page(row * (pages - 1), total, row, query.list());
		return page;
	}

	public int listCountBySql(String sql, Object[] params) {
		//String tsql=sql.substring(sql.indexOf("from"));
		sql="select count(*) from("+sql+")";
		Query query = getCurrSession().createSQLQuery(sql);
		if (null != params && params.length > 0) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i, params[i]);
			}
		}
		return ((BigDecimal) query.uniqueResult()).intValue();
	}
	
	public <T> List<T> findBySql(String sql, Object[] params, Class className){
		if (null == sql || sql.equals("")) {
			return null;
		}
		Session session = this.getCurrSession();
		SQLQuery query = session.createSQLQuery(sql);
		query.addEntity(className);
		if (null != params && params.length > 0) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i, params[i]);
			}
		}
		return query.list();
	}
	
	public List<Object[]> findBySql(String sql, Object[] params){
		if (null == sql || sql.equals("")) {
			return null;
		}
		Session session = this.getCurrSession();
		SQLQuery query = session.createSQLQuery(sql);
		if (null != params && params.length > 0) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i, params[i]);
			}
		}
		return query.list();
	}

	public List getTopList(String hql,int num,Object[] params) {
		Query query = getCurrSession().createQuery(hql);
		if (null != params && params.length > 0) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i, params[i]);
			}
		}
		query.setFirstResult(1);
		query.setMaxResults(num);
		return query.list();
	}

	public <T> List<T> find(String sql) {
		if (StringUtils.isBlank(sql)) {
			return null;
		}
		Session session = this.getCurrSession();
		Query query = session.createQuery(sql);
		List list = query.list();
		return list;
	}

	public <T> List<T> find(String sql, Object[] params) {
		if (StringUtils.isBlank(sql)) {
			return null;
		}
		Session session = this.getCurrSession();
		Query query = session.createQuery(sql);
		if (null != params && params.length > 0) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i, params[i]);
			}
		}
		List list = query.list();
		return list;
	}

}
