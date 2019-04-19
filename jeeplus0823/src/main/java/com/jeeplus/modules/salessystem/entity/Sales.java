/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.salessystem.entity;

import com.jeeplus.common.persistence.DataEntity;
import com.jeeplus.common.utils.excel.annotation.ExcelField;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

/**
 * 班级Entity
 * @author lgf
 * @version 2016-05-26
 */
public class Sales extends DataEntity<Sales> {
//	id INT(20) PRIMARY KEY AUTO_INCREMENT COMMENT'单据号',
//	listTime DATETIME NOT NULL COMMENT'单据日期',
//			`customerid` INT(20) COMMENT'客户关联客户表',
//	departmentsId INT(10) COMMENT'销售部门',
//	sales INT(10) COMMENT'销售员',
//	warehouse INT(10) COMMENT'默认仓库',
//	typeNameid INT(4) COMMENT'销售类型'
	private Date listTime;
	private Integer customerid;
	private  Integer departmentsId;
	private Integer sales;
	private Integer warehouse;
	private  Integer typeNameid;
	private String customerName;//拓展字段客户名称
	private String officeName;//拓展字段部门名称
	private String roleName;//拓展字段人员姓名

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getOfficeName() {
		return officeName;
	}

	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}

	public Date getListTime() {
		return listTime;
	}

	public void setListTime(Date listTime) {
		this.listTime = listTime;
	}

	public Integer getCustomerid() {
		return customerid;
	}

	public void setCustomerid(Integer customerid) {
		this.customerid = customerid;
	}

	public Integer getDepartmentsId() {
		return departmentsId;
	}

	public void setDepartmentsId(Integer departmentsId) {
		this.departmentsId = departmentsId;
	}

	public Integer getSales() {
		return sales;
	}

	public void setSales(Integer sales) {
		this.sales = sales;
	}

	public Integer getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(Integer warehouse) {
		this.warehouse = warehouse;
	}

	public Integer getTypeNameid() {
		return typeNameid;
	}

	public void setTypeNameid(Integer typeNameid) {
		this.typeNameid = typeNameid;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
}