package com.jeeplus.modules.storage.web;

import com.jeeplus.common.config.Global;
import com.jeeplus.common.persistence.Page;
import com.jeeplus.common.utils.MyBeanUtils;
import com.jeeplus.common.utils.StringUtils;
import com.jeeplus.common.web.BaseController;
import com.jeeplus.modules.echarts.entity.PieClass;
import com.jeeplus.modules.storage.entity.PutType;
import com.jeeplus.modules.storage.service.PutTypeService;
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
 * @Date: 2018/08/20
 * @Description 2018/08/21 完成对出入库类别的增删改查 批量删除
 * @Version
 */
@Controller
@RequestMapping(value = "${adminPath}/storage/putType")
public class PutTypeController extends BaseController {
    @Resource
    private PutTypeService putTypeService;

    @ModelAttribute
    public PutType get(@RequestParam(required=false) String id) {
        PutType entity = null;
        if (StringUtils.isNotBlank(id)){
            entity = putTypeService.get(id);
        }
        if (entity == null){
            entity = new PutType();
        }
        return entity;
    }
    /**
     * 出入库类别列表页面
     */
    @RequiresPermissions("storage:putType:list")
//    @RequestMapping(value = {"list",""})
    @RequestMapping(value = "list")
    public String list(PutType putType, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<PutType> page = putTypeService.findPage(new Page<PutType>(request, response), putType);
        model.addAttribute("page", page);
        List<PutType> list = putTypeService.findList(putType);
        model.addAttribute("list",list);
        return "modules/storage/putTypeList";
    }
    /**
     * 查看，增加，编辑出入库类别表单页面
     */
    @RequiresPermissions(value={"storage:putType:view","storage:putType:add","storage:putType:edit"},logical= Logical.OR)
    @RequestMapping(value = "form")
    public String form(PutType putType, Model model) {
        model.addAttribute("putType", putType);
        return "modules/storage/putTypesForm";
    }

    /**
     * 保存出入库类别
     */
    @RequiresPermissions(value={"storage:putType:add","storage:putType:edit"},logical=Logical.OR)
    @RequestMapping(value = "save")
    public String save(PutType putType, Model model, RedirectAttributes redirectAttributes) throws Exception{
        if (!beanValidator(model, putType)){
            return form(putType, model);
        }
        if(!putType.getIsNewRecord()){//编辑表单保存
            PutType t = putTypeService.get(putType.getId());//从数据库取出记录的值
            MyBeanUtils.copyBeanNotNull2Bean(putType, t);//将编辑表单中的非NULL值覆盖数据库记录中的值
            putTypeService.save(t);//保存
        }else{//新增表单保存
            putTypeService.save(putType);//保存
        }
        addMessage(redirectAttributes, "保存类别成功");
        return "redirect:"+ Global.getAdminPath()+"/storage/putType/list/?repage";
    }

    /**
     * 删除出入库类别
     */
    @RequiresPermissions("storage:putType:del")
    @RequestMapping(value = "delete")
    public String delete(PutType putType, RedirectAttributes redirectAttributes) {
        putTypeService.delete(putType);
        addMessage(redirectAttributes, "删除类别成功");
        return "redirect:"+Global.getAdminPath()+"/storage/putType/list/?repage";
    }

    /**
     * 批量删除出入库类别
     */
    @RequiresPermissions("storage:putType:del")
    @RequestMapping(value = "deleteAll")
    public String deleteAll(String ids, RedirectAttributes redirectAttributes) {
        String idArray[] =ids.split(",");
        for(String id : idArray){
            putTypeService.delete(putTypeService.get(id));
        }
        addMessage(redirectAttributes, "批量删除类别成功");
        return "redirect:"+Global.getAdminPath()+"/storage/putType/list/?repage";
    }



}
