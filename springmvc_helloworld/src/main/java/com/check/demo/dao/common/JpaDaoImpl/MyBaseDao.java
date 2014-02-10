/*
 * Date: 2011-12-15
 * author: Peream  (peream@gmail.com)
 *
 */
package com.check.demo.dao.common.JpaDaoImpl;

import com.check.demo.dao.common.entity.BaseEntity;


public abstract class MyBaseDao<E extends BaseEntity> extends BaseDaoJpa<E>
{
	protected MyBaseDao()
	{

	}
//	@PersistenceContext  
//	private EntityManager manager;
//	
//	public void setJpaEntityManager()
//	{
//		super.setEntityManager(manager);
//	}

}
