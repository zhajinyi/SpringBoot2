/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.salessystem.service;

import com.jeeplus.common.persistence.Page;
import com.jeeplus.common.service.CrudService;
import com.jeeplus.modules.salessystem.dao.SalesDao;
import com.jeeplus.modules.salessystem.entity.Sales;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 订单Service
 * @author lgf
 * @version 2016-05-26
 */
@Service
@Transactional(readOnly = true)
public class SalesService extends CrudService<SalesDao, Sales> {

	public Sales get(String id) {
		return super.get(id);
	}
	
	public List<Sales> findList(Sales sales) {
		return super.findList(sales);
	}
	
	public Page<Sales> findPage(Page<Sales> page, Sales sales) {
		return super.findPage(page, sales);
	}
	
	@Transactional(readOnly = false)
	public void save(Sales sales) {
		super.save(sales);
	}
	
	@Transactional(readOnly = false)
	public void delete(Sales sales) {
		super.delete(sales);
	}
	
	
	
	
}