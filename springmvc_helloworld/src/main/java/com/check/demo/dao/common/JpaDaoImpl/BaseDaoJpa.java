package com.check.demo.dao.common.JpaDaoImpl;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.sql.Blob;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.hibernate.engine.jdbc.ContextualLobCreator;
import org.hibernate.engine.jdbc.LobCreator;
import org.hibernate.internal.SessionImpl;
import org.springframework.transaction.annotation.Transactional;

import com.check.demo.dao.common.JpaDao.AbstractJpaDao;
import com.check.demo.dao.common.entity.BaseEntity;
import com.check.demo.dao.common.model.Pagination;
import com.check.demo.dao.common.utils.StringTools;


public abstract class BaseDaoJpa<E extends BaseEntity> extends CommonAbstract implements AbstractJpaDao<E>
{
	protected static final String COUNT_STR = "select count(*) ";
	// 如果有ID，使用count(id)会比count(*)快
	protected static final String COUNT_ID = "select count(id) ";
	@PersistenceContext
	protected EntityManager entityManager;
	protected final Class<E> clazz;

	@SuppressWarnings("unchecked")
	protected BaseDaoJpa()
	{
		clazz = (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		AssertUtil.notNull(clazz, "无法获得泛型的class");
	}

	protected BaseDaoJpa(Class<E> clazz)
	{
		this.clazz = clazz;
	}

	public final void setEntityManager(EntityManager entityManager)
	{
		this.entityManager = entityManager;
	}

	@Override
	@Transactional
	public final void persist(E entity)
	{
		if (entity == null) return;
		entityManager.persist(entity);
		entityManager.flush();
	}

	@Override
	@Transactional
	public final void merge(E entity)
	{
		if (entity == null) return;
		entityManager.merge(entity);
		entityManager.flush();
	}

	@Override
	@Transactional
	@Deprecated
	public final Serializable save(E obj)
	{
		if (obj == null) return null;
		entityManager.persist(obj);
		entityManager.flush();
		return null;
	}

	@Override
	public final E findById(Serializable id)
	{
		return id == null ? null : entityManager.find(clazz, id);
	}

	@Override
	public final E findByProperty(String propertyName, Object value)
	{
		AssertUtil.hasText(propertyName, "propertyName must specified.");
		String hql = "from " + clazz.getName() + " where " + propertyName + "=?1";
		TypedQuery<E> query = entityManager.createQuery(hql, clazz);
		query.setParameter(1, value);
		query.setMaxResults(1);
		List<E> list = query.getResultList();
		return list.size() > 0 ? list.get(0) : null;
	}

	@Override
	public final boolean isPropertyExist(String propertyName, Object value)
	{
		return findByProperty(propertyName, value) != null;
	}

	@Override
	@Transactional
	public final void saveOrUpdate(E obj)
	{
		if (obj == null) return;
		entityManager.merge(obj);
		entityManager.flush();
	}

	@Override
	@Transactional
	public final void saveOrUpdateAll(Collection<E> entities)
	{
		if (entities == null) return;
		for (E entity : entities)
		{
			entityManager.merge(entity);
		}
		entityManager.flush();
	}

	@Override
	@Transactional
	public final void update(E obj)
	{
		if (obj == null) return;
		entityManager.merge(obj);
		entityManager.flush();
	}

	@Override
	@Transactional
	public final void delete(E obj)
	{
		if (obj == null) return;
		entityManager.remove(entityManager.merge(obj));
		entityManager.flush();
	}

	@Override
	@Transactional
	public final void remove(E entity)
	{
		if (entity == null) return;
		entityManager.remove(entity);
		entityManager.flush();
	}

	@Override
	@Transactional
	public final int deleteByProperty(String propName, Object propValue)
	{
		AssertUtil.hasText(propName);
		AssertUtil.notNull(propValue);
		String hql = "delete from " + clazz.getName() + " where " + propName + "=?1";
		return executeHql(hql, propValue);
	}

	@Override
	@Transactional
	public final int deleteById(Serializable id)
	{
		E entity = findById(id);
		if (entity == null) return 0;
		entityManager.remove(entity);
		entityManager.flush();
		return 1;
	}

	@Override
	@Transactional
	public final void deleteAll(Collection<E> collections)
	{
		if (collections == null) return;
		for (E entity : collections)
		{
			entityManager.remove(entityManager.merge(entity));
		}
		entityManager.flush();
	}

	@Override
	@Transactional
	public final int deleteAll()
	{
		String hql = "delete from " + clazz.getName();
		int count = entityManager.createQuery(hql).executeUpdate();
		entityManager.flush();
		return count;
	}

	@Override
	public final List<E> getAll()
	{
		return getAllInOrder(null);
	}

	@Override
	public final List<E> getAllInOrder(String orderHql)
	{
		String hql = "from " + clazz.getName();
		if (StringTools.hasText(orderHql)) hql = hql + " " + orderHql;
		logger.info("从表中获取所有数据,请确认是否需要查询所有数据:{}", hql);
		return queryList(clazz, hql, (Object[]) null);
	}

	@Override
	public final Pagination getAll(int pageNumber, int pageSize)
	{
		String hql = "select count(*) from " + clazz.getName();
		long totalCount = querySize(hql, (Object[]) null);
		Pagination pg = newPagination(pageNumber, totalCount, pageSize);
		hql = "from " + clazz.getName();
		pg.setResult(pageList(clazz, hql, (Object[]) null, pg.getCurrentPage(), pageSize));
		return pg;
	}

	@Override
	public final long querySize(String hql, Object[] values)
	{
		if (hql == null) return 0;
		List<Long> list = queryList(Long.class, hql, values);
		return list.get(0);
	}

	@Override
	public final long querySize(String hql, Map<String, Object> values)
	{
		if (hql == null) return 0;
		List<Long> list = queryList(Long.class, hql, values);
		return list.get(0);
	}

	@Override
	public final long getTotalCount()
	{
		String hql = "select count(*) from " + clazz.getName();
		List<Long> list = queryList(Long.class, hql, (Object[]) null);
		return list.get(0);
	}

	/**
	 * 分页查询
	 * 
	 * @param eleClass
	 *            返回列表的元素类型
	 * @param hql
	 *            查询的hql语句
	 * @param values
	 *            名字占位符(ex :column)
	 * @param pageNo
	 *            页码
	 * @param pageSize
	 *            每页条数
	 * @return 查询结果
	 */
	protected final <T> List<T> pageList(Class<T> eleClass, String hql, Map<String, Object> values, int pageNo,
			int pageSize)
	{
		TypedQuery<T> query = entityManager.createQuery(hql, eleClass);
		prepareTypedQuery(query, values, pageNo, pageSize);
		return query.getResultList();
	}

	protected final List<E> pageList(String hql, Map<String, Object> values, int pageNo, int pageSize)
	{
		return pageList(clazz, hql, values, pageNo, pageSize);
	}

	protected final <T> List<T> queryList(Class<T> eleClass, String hql, Map<String, Object> values)
	{
		return pageList(eleClass, hql, values, -1, -1);
	}

	protected final List<E> queryList(String hql, Map<String, Object> values)
	{
		return pageList(clazz, hql, values, -1, -1);
	}

	/**
	 * 分页查询
	 * 
	 * @param eleClass
	 *            返回列表的元素类型
	 * @param hql
	 *            查询的hql语句
	 * @param values
	 *            顺序占位符(ex :?)
	 * @param pageNo
	 *            页码
	 * @param pageSize
	 *            每页条数
	 * @return 查询结果
	 */
	protected final <T> List<T> pageList(Class<T> eleClass, String hql, Object[] values, int pageNo, int pageSize)
	{
		TypedQuery<T> query = entityManager.createQuery(hql, eleClass);
		prepareTypedQuery(query, values, pageNo, pageSize);
		return query.getResultList();
	}

	protected final List<E> pageList(String hql, Object[] values, int pageNo, int pageSize)
	{
		return pageList(clazz, hql, values, pageNo, pageSize);
	}

	protected final <T> List<T> queryList(Class<T> eleClass, String hql, Object[] values)
	{
		return pageList(eleClass, hql, values, -1, -1);
	}

	protected final List<E> queryList(String hql, Object[] values)
	{
		return pageList(clazz, hql, values, -1, -1);
	}

	private <T> void prepareTypedQuery(TypedQuery<T> query, Map<String, Object> values, int pageNo, int pageSize)
	{
		int firstResult = pageNo < 1 ? 0 : pageSize * (pageNo - 1);
		if (firstResult >= 0) query.setFirstResult(firstResult);
		if (pageSize > 0) query.setMaxResults(pageSize);
		if (values != null)
		{
			for (Entry<String, Object> en : values.entrySet())
			{
				query.setParameter(en.getKey(), en.getValue());
			}
		}
	}

	private <T> void prepareTypedQuery(TypedQuery<T> query, Object[] values, int pageNo, int pageSize)
	{
		int firstResult = pageNo < 1 ? 0 : pageSize * (pageNo - 1);
		if (firstResult >= 0) query.setFirstResult(firstResult);
		if (pageSize > 0) query.setMaxResults(pageSize);
		if (values != null)
		{
			for (int i = 1; i <= values.length; i++)
			{
				query.setParameter(i, values[i - 1]);
			}
		}
	}

	/**
	 * 按hql语句执行更新操作(更新或删除)
	 * 
	 * @param hql
	 *            hql语句
	 * @return 受影响的记录条数
	 */
	@Transactional
	protected final int executeHql(String hql)
	{
		return executeHql(hql, (Object[]) null);
	}

	/**
	 * 按hql语句执行更新操作(更新或删除)
	 * 
	 * @param hql
	 *            hql语句
	 * @param value
	 *            hql语句中的条件
	 * @return 受影响的记录条数
	 */
	protected final int executeHql(String hql, Object value)
	{
		AssertUtil.notNull(value);
		return executeHql(hql, new Object[] { value });
	}

	/**
	 * 按hql语句执行更新操作(更新或删除)
	 * 
	 * @param hql
	 *            hql语句
	 * @param values
	 *            hql语句中的条件（多个）
	 * @return 受影响的记录条数
	 */
	@Transactional
	protected final int executeHql(String hql, Object[] values)
	{
		Query query = entityManager.createQuery(hql);
		if (values != null)
		{
			for (int i = 1; i <= values.length; i++)
			{
				query.setParameter(i, values[i - 1]);
			}
		}
		int count = query.executeUpdate();
		entityManager.flush();
		return count;
	}

	@Transactional
	protected final int executeHql(String hql, Map<String, Object> values)
	{
		Query query = entityManager.createQuery(hql);
		if (values != null)
		{
			for (Entry<String, Object> en : values.entrySet())
			{
				query.setParameter(en.getKey(), en.getValue());
			}
		}
		int count = query.executeUpdate();
		entityManager.flush();
		return count;
	}

	/**
	 * 分页查询
	 * 
	 * @param hql
	 *            查询语句
	 * @param values
	 *            参数
	 * @param countHql
	 *            查询数量的语句
	 * @param pageNumber
	 *            页码
	 * @param pageSize
	 *            每页的条数
	 * @return 查询结果
	 */
	protected final Pagination pageQuery(String hql, Object[] values, String countHql, int pageNumber, int pageSize)
	{
		AssertUtil.notNull(countHql, "countHql can not be null.");
		long totalCount = querySize(countHql, values);
		return pageQuery(totalCount, hql, values, pageNumber, pageSize);
	}

	protected final Pagination pageQuery(String hql, Map<String, Object> values, String countHql, int pageNumber,
			int pageSize)
	{
		AssertUtil.notNull(countHql, "countHql can not be null.");
		long totalCount = querySize(countHql, values);
		return pageQuery(totalCount, hql, values, pageNumber, pageSize);
	}

	/**
	 * 分页查询
	 * 
	 * @param hql
	 *            查询语句
	 * @param countHql
	 *            查询数量的语句
	 * @param pageNumber
	 *            页码
	 * @param pageSize
	 *            每页的条数
	 * @return 查询结果
	 * @see {@link #pageQuery(String, Object[], String, int, int)}
	 */
	protected final Pagination pageQuery(String hql, String countHql, int pageNumber, int pageSize)
	{
		return pageQuery(hql, (Object[]) null, countHql, pageNumber, pageSize);
	}

	/**
	 * 查询带分页
	 * 
	 * @param totalCount
	 * @param hql
	 *            查询语句
	 * @param values
	 *            参数
	 * @param pageNumber
	 *            页码
	 * @param pageSize
	 *            每页的条数
	 * @return 查询结果
	 */
	protected final Pagination pageQuery(long totalCount, String hql, Object[] values, int pageNumber, int pageSize)
	{
		Pagination pg = newPagination(pageNumber, totalCount, pageSize);
		pg.setResult(pageList(Object.class, hql, values, pg.getCurrentPage(), pageSize));
		return pg;
	}

	protected final Pagination pageQuery(long totalCount, String hql, Map<String, Object> values, int pageNumber,
			int pageSize)
	{
		Pagination pg = newPagination(pageNumber, totalCount, pageSize);
		pg.setResult(pageList(Object.class, hql, values, pg.getCurrentPage(), pageSize));
		return pg;
	}

	protected final LobCreator getLobCreator()
	{
		return new ContextualLobCreator(entityManager.unwrap(SessionImpl.class));
	}

	/**
	 * 使用当前的Lobreator将input转换成blob
	 * 
	 * @param in
	 * @return
	 */
	protected final Blob input2Blob(InputStream in)
	{
		return input2Blob(in, -1);
	}

	protected final Blob input2Blob(InputStream in, long length)
	{
		AssertUtil.notNull(in);
		try
		{
			long myLength = length < 0 ? in.available() : length;
			return getLobCreator().createBlob(in, myLength);
		}
		catch (IOException e)
		{
			throw new RuntimeException("", e);
		}
		finally
		{
			try
			{
				in.close();
			}
			catch (IOException e)
			{
				logger.error("", e);
			}
		}
	}

	private final Pagination newPagination(int pageNumber, long totalCount, int pageSize)
	{
		Pagination pagination = new Pagination();
		long count = Pagination.getPageCount(totalCount, pageSize);
		// 如页码过大，返回最后一页
		long currentPage = pageNumber > count ? count : pageNumber;
		pagination.setTotalCount(totalCount);
		pagination.setCurrentPage(Long.valueOf(currentPage).intValue());
		pagination.setPageSize(pageSize);
		pagination.setPageCount(count);
		return pagination;
	}

}
