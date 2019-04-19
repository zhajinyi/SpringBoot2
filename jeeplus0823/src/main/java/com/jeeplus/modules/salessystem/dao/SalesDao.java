/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.salessystem.dao;

import com.jeeplus.common.persistence.CrudDao;
import com.jeeplus.common.persistence.annotation.MyBatisDao;
import com.jeeplus.modules.salessystem.entity.Sales;

/**
 * 班级DAO接口
 * @author lgf
 * @version 2016-05-26
 */
@MyBatisDao
public interface SalesDao extends CrudDao<Sales> {

	
}