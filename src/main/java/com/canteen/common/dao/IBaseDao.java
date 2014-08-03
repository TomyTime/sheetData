package com.canteen.common.dao;

import com.canteen.common.utils.Page;
import com.canteen.entity.BaseEntity;
import org.hibernate.Session;

import java.util.List;

/**
 * Data Access Object (Dao) interface. This is an empty interface used to tag
 * our Dao classes. Common methods for each interface could be added here.
 * 
 * @author Matt Raible
 */

public interface IBaseDao {

	Session getCurrSession();

	/**
	 * <li>通过查询语句删除或更新信息</li>
	 * 
	 * @param sql
	 *            查询语句
	 * @param params
	 *            参数列表,与语句中的'?'号对应,没有则填null
	 * @throws org.hibernate.HibernateException
	 * @return 成功＝true
	 */
	public boolean delOrUpdate(String sql, Object[] params);

	/**
	 * <li>查询</li>
	 * 
	 * @param sql
	 *            查询语句
	 * @throws org.hibernate.HibernateException
	 * @return 返回List结果集
	 */
	public <T> List<T> find(String sql);

	/**
	 * <li>查询</li>
	 * 
	 * @param sql:查询语句
	 * @param params:参数列表,与语句中的'?'号对应,没有则填null
	 * @throws org.hibernate.HibernateException
	 * @return 返回List结果集
	 */
	public <T> List<T> find(String sql, Object[] params);

	/**
	 * <li>查询，带分页效果</li>
	 *
	 * @param row:每页显示多少行
	 * @param pages:当前显示第几页,从1开始
	 * @throws org.hibernate.HibernateException
	 * @return 返回List结果集
	 */
	public Page findPage(String sql, int row, int pages);

	/**
	 * <li>查询，带分页效果</li>
	 * 
	 * @param hql:查询语句
	 * @param params:参数列表,与语句中的'?'号对应,没有则填null
	 * @param row:每页显示多少行
	 * @param pages:当前显示第几页,从1开始
	 * @throws org.hibernate.HibernateException
	 * @return 返回List结果集
	 */
	public Page findPage(String hql, Object[] params, int row, int pages);

	/**
	 * <li>查询数据总数</li>
	 * 
	 * @author 旦旦而学
	 * @param hql:查询语句,select
	 *            count(x)...语句
	 * @param params:参数列表,与语句中的'?'号对应,没有则填null
	 * @throws org.hibernate.HibernateException
	 * @throws NumberFormatException
	 * @return int
	 */
	public int listCount(String hql, Object[] params);

	/**
	 * 新增记录
	 * 
	 * @param entity
	 */
	public void save(BaseEntity entity) throws Exception;

	/**
	 * 修改记录
	 * 
	 * @param entity
	 */
	public void update(BaseEntity entity) throws Exception;

	public BaseEntity findById(String id, Class className);

	public Page findPageBySql(String sql, int row, int pages, Class className);
	
	public Page findPageBySql(String sql, int row, int pages);

	public int listCountBySql(String hql, Object[] params);

	public <T> List<T> findBySql(String sql, Object[] param, Class className);

	public List<Object[]> findBySql(String sql, Object[] param);
	
	public List getTopList(String hql, int num, Object[] param);
}
