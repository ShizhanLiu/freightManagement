package cn.yunhe.controller;

import cn.yunhe.pojo.*;
import cn.yunhe.realm.CustomRealm;
import cn.yunhe.service.DeptPService;
import cn.yunhe.service.ModulePService;
import cn.yunhe.service.RoleModulePService;
import cn.yunhe.service.RolePService;
import cn.yunhe.util.ExportExcelUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.List;

/**
 * @Author:Shizhan
 * @ProjectName:freight-codes
 * @currentTime: 2022/9/9 10:47
 */
@Controller
@RequestMapping("/role")
public class RolePController {
    @Autowired
    private RolePService rolePService;
    @Autowired
    private ModulePService modulePService;
    @Autowired
    private RoleModulePService roleModulePService;
    @Autowired
    private CustomRealm customRealm;


    @RequestMapping("/list")
    public String listRole(PageBean pageBean, Model model, HttpSession session){
      /*  PageBean pageBean  = new PageBean();*/
        PageBean pb = rolePService.listRoleOfPage(pageBean);
        model.addAttribute("pb",pb);
        session.setAttribute("url","/role/list");
        return "sysadmin/role/jRoleList";
    }

    /*指令发出后，第一个先跳到这里，从数据库中把内容查出来，然后再去页面展示*/
    @RequestMapping("tocreate")
    public String toCreate(){
        return "sysadmin/role/jRoleCreate";
    }

    @RequestMapping("create")
    public String create(RoleP roleP) throws Exception{
        rolePService.createRole(roleP);
        return "redirect:/role/list";
    }

    /**
     * 跳转为角色分配权限页面
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("tomodule")
    public String tomodule(String id,Model model) throws Exception{
        //根据roleid查询角色信息
        RoleP roleP = rolePService.findByRoleId(id);
        model.addAttribute("role",roleP);
        //查询所有的权限信息，将权限信息生成树状菜单
        return  "sysadmin/role/jRoleModule";

    }

    @RequestMapping("listModuleOfTreeBean")
    @ResponseBody
    public List<TreeNode> listModuleOfTreeBean() throws Exception{
        return modulePService.listModuleOfTreeBean();
    }

    /**
     * 支持数据回显
     * @param roleid
     * @return
     * @throws Exception
     */
    @RequestMapping("/listModuleOfTreeBeanByRoleId")
    @ResponseBody
    public List<TreeNode> listModuleOfTreeBeanByRoleId(String roleid) throws Exception{
        return modulePService.listModuleOfTreeBeanByRoleId(roleid);
    }



    /**
     *
     * @param roleId
     * @param moduleIds
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/assignModules")
    public String assignModules(String roleId,String[] moduleIds,Model model) throws Exception {
        roleModulePService.createRoleModule(roleId,moduleIds);
        //清空缓存
        customRealm.clearCached();
        return "redirect:/role/list";
    }




}
