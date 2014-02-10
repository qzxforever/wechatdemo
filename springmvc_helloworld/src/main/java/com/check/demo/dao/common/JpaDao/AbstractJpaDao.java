package com.check.demo.dao.common.JpaDao;

import java.io.Serializable;

import com.check.demo.dao.common.entity.BaseEntity;


public interface AbstractJpaDao<E extends BaseEntity> extends AbstractDao<E>
{
	/**
	 * 保存当前实体
	 * 
	 * @param entity
	 */
	public void persist(E entity);

	/**
	 * 
	 * @param entity
	 * @see {@link #saveOrUpdate(BaseEntity)}
	 */
	public void merge(E entity);

	/**
	 * 删除一个实体
	 * 
	 * @param entity
	 * @see {@link #delete(BaseEntity)}
	 */
	public void remove(E entity);

	/**
	 * JPA的保存没有返回值
	 * 
	 * @param entity
	 *            待保存的实体
	 * @see {@link #persist(BaseEntity)}
	 */
	@Deprecated
	public Serializable save(E entity);
}
