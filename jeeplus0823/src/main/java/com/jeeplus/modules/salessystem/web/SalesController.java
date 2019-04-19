package com.jeeplus.modules.salessystem.web;
import com.jeeplus.common.config.Global;
import com.jeeplus.common.persistence.Page;
import com.jeeplus.common.utils.MyBeanUtils;
import com.jeeplus.common.utils.StringUtils;
import com.jeeplus.common.web.BaseController;
import com.jeeplus.modules.salessystem.entity.Sales;
import com.jeeplus.modules.salessystem.service.SalesService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * 销售订单表Controller
 * @author lgf
 * @version 2016-09-22
 */
@Controller
@RequestMapping(value = "${adminPath}/salesorder/sales")
public class SalesController extends BaseController {

	@Autowired
	private SalesService salesService;
	@RequiresPermissions("salesorder:sales:add")
	@RequestMapping(value = "add")
	public String add(Model model,Sales sales){
		List<Sales> list=salesService.findList(sales);
		model.addAttribute("list",list);
		return  "modules/salesorder/add";
	}


	@ModelAttribute
	public Sales get(@RequestParam(required = false) String id) {
		Sales sales = null;
		if (StringUtils.isNotBlank(id)) {
			sales = salesService.get(id);
		}
		if (sales == null) {
			sales = new Sales();
		}
		return sales;
	}


	/**
	 * 订单列表页面
	 */
	@RequiresPermissions("salesorder:sales:list")
	@RequestMapping(value = {"list", ""})
	public String list(Sales sales, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Sales> page = salesService.findPage(new Page<Sales>(request, response), sales);
		model.addAttribute("page", page);
		List<Sales> list=salesService.findList(sales);
		for(Sales sales1:list){
			List<Integer>  slist=new ArrayList<Integer>();
			sales1.getCreateBy().getId().
			 slist.add();
		}
		model.addAttribute("list",list);
		return "modules/salesorder/salesList" ;
	}

	/**
	 * 查看，增加，编辑表单页面
	 */
	@RequiresPermissions(value={"salesorder:sales:view","salesorder:sales:add","salesorder:sales:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(Sales sales, Model model) {
		model.addAttribute("sales", sales);
		return "modules/salesorder/salesForm";
	}

	/**
	 * 保存增加订单
	 */
	@RequiresPermissions(value={"salesorder:sales:add","salesorder:sales:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public String save(Sales pieClass, Model model, RedirectAttributes redirectAttributes) throws Exception{
		if (!beanValidator(model, pieClass)){
			return form(pieClass, model);
		}
		if(!pieClass.getIsNewRecord()){//编辑表单保存
			Sales t = salesService.get(pieClass.getId());//从数据库取出记录的值
			MyBeanUtils.copyBeanNotNull2Bean(pieClass, t);//将编辑表单中的非NULL值覆盖数据库记录中的值
			salesService.save(t);//保存
		}else{//新增表单保存
			salesService.save(pieClass);//保存
		}
		addMessage(redirectAttributes, "保存订单成功");
		return "redirect:"+Global.getAdminPath()+"/modules/salesorder/?repage";
	}

	/**
	 * 删除订单
	 */
	@RequiresPermissions("salesorder:sales:del")
	@RequestMapping(value = "delete")
	public String delete(Sales sales, RedirectAttributes redirectAttributes) {
		salesService.delete(sales);
		addMessage(redirectAttributes, "删除订单成功");
		return "redirect:"+Global.getAdminPath()+"/modules/salesorder/?repage";
	}

	/**
	 * 批量删除订单
	 */
	@RequiresPermissions("salesorder:sales:del")
	@RequestMapping(value = "deleteAll")
	public String deleteAll(String ids, RedirectAttributes redirectAttributes) {
		String idArray[] =ids.split(",");
		for(String id : idArray){
			salesService.delete(salesService.get(id));
		}
		addMessage(redirectAttributes, "批量删除订单成功");
		return "redirect:"+Global.getAdminPath()+"/echarts/pieClass/?repage";
	}



}