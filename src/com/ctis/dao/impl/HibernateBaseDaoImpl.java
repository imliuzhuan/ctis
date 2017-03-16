package com.ctis.dao.impl;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import com.ctis.dao.IHibernateBaseDao;
import com.ctis.exception.DAOException;
import com.ctis.util.PageResult;

public class HibernateBaseDaoImpl extends HibernateDaoSupport implements IHibernateBaseDao {
	private static Log logger = LogFactory.getLog(HibernateBaseDaoImpl.class);

	@Autowired
	public void setMySessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	/**
	 * 创建对象
	 * @param o
	 * @throws DAOException
	 */
	public void create(Object o) throws DAOException {
		try {
			getHibernateTemplate().save(o);
		} catch (DataAccessException e) {
			logger.error(e.getMessage(), e);
			throw new DAOException(e);
		}
	}

	/**
	 * 更新对象
	 * @param o
	 * @throws DAOException
	 */
	public void update(Object o) throws DAOException {
		try {
			getHibernateTemplate().update(o);
		} catch (DataAccessException e) {
			logger.error(e.getMessage(), e);
			throw new DAOException(e);
		}
	}

	/**
	 * 删除对象
	 * @param o
	 * @throws DAOException
	 */
	public void delete(Object o) throws DAOException {
		try {
			getHibernateTemplate().delete(o);
		} catch (DataAccessException e) {
			logger.error(e.getMessage(), e);
			throw new DAOException(e);
		}
	}

	/**
	 * 删除指定id的对象记录
	 * @param clazz
	 * @param id
	 * @throws DAOException
	 */
	@SuppressWarnings("rawtypes")
	public void delete(Class clazz, Serializable id) throws DAOException {
		try {
			delete(get(clazz, id));
		} catch (DataAccessException e) {
			logger.error(e.getMessage(), e);
			throw new DAOException(e);
		}
	}

	/**
	 * 清空表格数据
	 * @param clazz
	 * @throws DAOException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void deleteAll(Class clazz) throws DAOException {
		getHibernateTemplate().deleteAll(getHibernateTemplate().loadAll(clazz));
	}

	/**
	 * 根据id获得对象
	 * @param clazz
	 * @param id
	 * @return
	 * @throws DAOException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Object get(Class clazz, Serializable id) throws DAOException {
		try {
			return getHibernateTemplate().get(clazz, id);
		} catch (DataAccessException e) {
			logger.error(e.getMessage(), e);
			throw new DAOException(e);
		}
	}

	/**
	 * 查询，返回对象列表
	 * @param sql
	 * @return
	 * @throws DAOException
	 */
	@SuppressWarnings("rawtypes")
	public List query(String sql) throws DAOException {
		try {
			return getHibernateTemplate().find(sql);
		} catch (DataAccessException e) {
			logger.error(e.getMessage(), e);
			throw new DAOException(e);
		}
	}

	/**
	 * 返回符合条件翻页记录，返回记录类型为PageResult
	 * @param queryString 查询条件
	 * @param page 当前页码
	 * @param rows 总行数
	 * @return
	 * @throws DAOException
	 */
	public PageResult findPageResult(String queryString, final int page, final int rows) throws DAOException {
		try {
			int count = getListCount(queryString);
			int newPage = page;
			if (page > 1) {
				if ((page - 1) * rows >= count) {
					newPage = (int) (Math.ceil((count * 1.0) / rows));
				}
			}
			if (count == 0) {
				newPage = 1;
			}
			List<Object> queryResult = findPageList(queryString, newPage, rows);
			PageResult pageResult = new PageResult(queryResult, count);
			return pageResult;
		} catch (DAOException e) {
			logger.error(e.getMessage(), e);
			throw new DAOException(e);
		}

	}

	/**
	 * 返回符合条件的记录总数
	 * @param queryString 查询条件
	 * @return 记录总数
	 * @throws DAOException
	 */
	private int getListCount(String queryString) throws DAOException {
		int count = 0;
		try {
			String countQueryStr = "select count(*) ";
			String fromStr = null;
			String newSql = null;
			String sql = queryString.toUpperCase();
			int begin = sql.indexOf("FROM");
			int end = sql.lastIndexOf("ORDER BY");
			if (end > 0)
				fromStr = queryString.substring(begin, end);
			else
				fromStr = queryString.substring(begin);
			newSql = countQueryStr + fromStr;
			List<?> list = getHibernateTemplate().find(newSql);
			if (list != null) {
				count = Integer.parseInt(String.valueOf(list.get(0)));
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new DAOException(e);
		}
		return count;
	}

	/**
	 * 返回指定的一页记录数。用于翻页查询。返回对象列表
	 * @param queryString 查询条件
	 * @param page 当前页码
	 * @param rows 查询总行数
	 * @return
	 * @throws DAOException
	 */
	@SuppressWarnings("unchecked")
	private List<Object> findPageList(final String queryString, final int page, final int rows) throws DAOException {
		try {
			return (List<Object>) getHibernateTemplate().execute(new HibernateCallback<Object>() {
				public Object doInHibernate(Session session) throws HibernateException {
					Query q = session.createQuery(queryString);
					if (page > -1) {
						q.setFirstResult((page - 1) * rows);
					}
					if (rows > -1) {
						q.setMaxResults(rows);
					}
					List<Object> list = q.list();
					if (list == null) {
						list = Collections.EMPTY_LIST;
					}
					return list;
				}
			});
		} catch (DataAccessException e) {
			logger.error(e.getMessage(), e);
			throw new DAOException(e);
		}
	}
}
