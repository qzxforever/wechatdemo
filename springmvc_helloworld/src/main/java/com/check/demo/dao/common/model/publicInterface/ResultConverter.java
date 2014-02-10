/*
 * Date: 2011-10-31
 * author: Peream  (peream@gmail.com)
 *
 */
package com.check.demo.dao.common.model.publicInterface;

import com.check.demo.dao.common.model.Pagination;

/**
 * 结果转换
 * 
 * @see Pagination#convertResult(ResultConverter)
 */
public interface ResultConverter<F, T>
{
	public T convert(F from);
}
