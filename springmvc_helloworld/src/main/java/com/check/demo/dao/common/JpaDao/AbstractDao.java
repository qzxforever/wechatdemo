package com.check.demo.dao.common.JpaDao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.check.demo.dao.common.entity.BaseEntity;
import com.check.demo.dao.common.model.Pagination;



public interface AbstractDao<E extends BaseEntity>
{
	/**
	 * 保存一个实例
	 * 
	 * @param obj
	 * @return
	 */
	public Serializable save(E obj);

	/**
	 * 根据ID查找记录
	 * 
	 * @param id
	 * @return
	 */
	public E findById(Serializable id);

	/**
	 * 查找指定属性的记录
	 * 
	 * @param propertyName
	 * @param value
	 * @return
	 */
	public E findByProperty(final String propertyName, final Object value);

	/**
	 * 判断与该属性值相等的记录是否存在
	 * 
	 * @param propertyName
	 * @param value
	 * @return
	 */
	public boolean isPropertyExist(final String propertyName, final Object value);

	/**
	 * 保存或更新该条记录
	 * 
	 * @param obj
	 */
	public void saveOrUpdate(E obj);

	/**
	 * 批量更新记录
	 * 
	 * @param entities
	 */
	public void saveOrUpdateAll(Collection<E> entities);

	/**
	 * 更新该条记录
	 * 
	 * @param obj
	 */
	public void update(E obj);

	/**
	 * 删除该实例
	 * 
	 * @param obj
	 */
	public void delete(E obj);

	/**
	 * 删除propName=propValue的记录
	 * 
	 * @param propName
	 *            字读的名字
	 * @param propValue
	 *            字段的值
	 * @return 删除的记录数
	 */
	public int deleteByProperty(String propName, Object propValue);

	/**
	 * 删除指定ID的记录
	 * 
	 * @param id
	 *            id的值
	 * @return 删除的记录数：记录存在返回1，否则返回0
	 */
	public int deleteById(Serializable id);

	/**
	 * 删除一组实例
	 * 
	 * @param collections
	 *            存放实例的collections
	 */
	public void deleteAll(Collection<E> collections);

	/**
	 * 删除所有记录
	 * 
	 * @return
	 */
	public int deleteAll();

	/**
	 * 获取所有记录
	 * 
	 * @return
	 * @see {@link #getAllInOrder(String)}
	 */
	public List<E> getAll();

	/**
	 * 查询整张表
	 * 
	 * @param orderHql
	 *            排序的hql ex：order by xxx asc
	 * @return 表的记录
	 */
	public List<E> getAllInOrder(String orderHql);

	/**
	 * 分页获取记录
	 * 
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public Pagination getAll(int pageNumber, int pageSize);

	/**
	 * 
	 * @param hql
	 * @param values
	 * @return
	 */
	public long querySize(String hql, Object[] values);

	/**
	 * 
	 * @param hql
	 * @param values
	 * @return
	 */
	public long querySize(String hql, Map<String, Object> values);

	/**
	 * 取得当前表的总记录数
	 * 
	 * @return 总记录数
	 */
	public long getTotalCount();
}