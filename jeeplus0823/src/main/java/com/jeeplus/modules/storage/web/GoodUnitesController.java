package com.jeeplus.modules.storage.web;

import com.jeeplus.common.config.Global;
import com.jeeplus.common.persistence.Page;
import com.jeeplus.common.utils.MyBeanUtils;
import com.jeeplus.common.utils.StringUtils;
import com.jeeplus.common.web.BaseController;
import com.jeeplus.modules.storage.entity.GoodUnites;
import com.jeeplus.modules.storage.service.GoodUnitesService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Author BL
 * @Date: 2018/08/21
 * @Description 2018/08/22 完成对计量单位的增删改查 批量删除
 * @Version
 */
@Controller
@RequestMapping(value = "${adminPath}/storage/goodUnites")
public class GoodUnitesController extends BaseController {
    @Resource
    private GoodUnitesService goodUnitesService;

    @ModelAttribute
    public GoodUnites get(@RequestParam(required = false) String id) {
        GoodUnites entity = null;
        if (StringUtils.isNotBlank(id)) {
            entity = goodUnitesService.get(id);
        }
        if (entity == null) {
            entity = new GoodUnites();
        }
        return entity;
    }

    /**
     * 计量单位列表页面
     */
    @RequiresPermissions("storage:goodUnites:list")
//    @RequestMapping(value = {"list",""})
    @RequestMapping(value = "list")
    public String list(GoodUnites goodUnites, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page <GoodUnites> page = goodUnitesService.findPage(new Page <GoodUnites>(request, response), goodUnites);
        model.addAttribute("page", page);
        List <GoodUnites> list = goodUnitesService.findList(goodUnites);
        model.addAttribute("list", list);
        return "modules/storage/goodUnitesList";
    }
    /**
     * 查看，增加，编辑计量单位表单页面
     */
    @RequiresPermissions(value={"storage:goodUnites:view","storage:goodUnites:add","storage:goodUnites:edit"},logical= Logical.OR)
    @RequestMapping(value = "form")
    public String form(GoodUnites goodUnites, Model model) {
        model.addAttribute("goodUnites", goodUnites);
        return "modules/storage/goodUnitesForm";
    }

    /**
     * 保存计量单位
     */
    @RequiresPermissions(value={"storage:goodUnites:add","storage:goodUnites:edit"},logical=Logical.OR)
    @RequestMapping(value = "save")
    public String save(GoodUnites goodUnites, Model model, RedirectAttributes redirectAttributes) throws Exception{
        if (!beanValidator(model, goodUnites)){
            return form(goodUnites, model);
        }
        if(!goodUnites.getIsNewRecord()){//编辑表单保存
            GoodUnites t = goodUnitesService.get(goodUnites.getId());//从数据库取出记录的值
            MyBeanUtils.copyBeanNotNull2Bean(goodUnites, t);//将编辑表单中的非NULL值覆盖数据库记录中的值
            goodUnitesService.save(t);//保存
        }else{//新增表单保存
            goodUnitesService.save(goodUnites);//保存
        }
        addMessage(redirectAttributes, "保存类别成功");
        return "redirect:"+ Global.getAdminPath()+"/storage/goodUnites/list/?repage";
    }

    /**
     * 删除计量单位
     */
    @RequiresPermissions("storage:goodUnites:del")
    @RequestMapping(value = "delete")
    public String delete(GoodUnites goodUnites, RedirectAttributes redirectAttributes) {
        goodUnitesService.delete(goodUnites);
        addMessage(redirectAttributes, "删除类别成功");
        return "redirect:"+Global.getAdminPath()+"/storage/goodUnites/list/?repage";
    }
    /**
     * 批量删除计量单位
     */
    @RequiresPermissions("storage:goodUnites:del")
    @RequestMapping(value = "deleteAll")
    public String deleteAll(String ids, RedirectAttributes redirectAttributes) {
        String idArray[] =ids.split(",");
        for(String id : idArray){
            goodUnitesService.delete(goodUnitesService.get(id));
        }
        addMessage(redirectAttributes, "批量删除类别成功");
        return "redirect:"+Global.getAdminPath()+"/storage/goodUnites/list/?repage";
    }
}
