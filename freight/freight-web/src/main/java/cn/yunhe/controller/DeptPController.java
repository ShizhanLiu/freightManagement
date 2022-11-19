package cn.yunhe.controller;

import cn.yunhe.pojo.DeptP;
import cn.yunhe.pojo.DeptVo;
import cn.yunhe.pojo.PageBean;
import cn.yunhe.service.DeptPService;
import cn.yunhe.util.ExportExcelUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
@RequestMapping("/dept")
public class DeptPController {
    @Autowired
    private DeptPService deptPService;
    private PageBean pageBean;
    private Model model;
    private HttpServletRequest request;
    private HttpServletResponse response;

    @RequestMapping("/list")
    public String listDeptAndParent(PageBean pageBean, Model model, HttpSession session){
      /*  PageBean pageBean  = new PageBean();*/
        PageBean pb = deptPService.listDeptOfPage(pageBean);
        model.addAttribute("pb",pb);
        session.setAttribute("url","/dept/list");
        return "sysadmin/dept/jDeptList";
    }

    /**
     * 跳转新增页面
     * @param model
     * @return
     */
    /*指令发出后，第一个先跳到这里，从数据库中把内容查出来，然后再去页面展示*/
    @RequestMapping("tocreate")
    public String toCreate(Model model){
        List<DeptP> depts = deptPService.listDept();
        model.addAttribute("depts",depts);
        return "sysadmin/dept/jDeptCreate";
    }

    /**
     * 新增部门
     * @param deptP
     * @return
     */
    @RequestMapping("create")
    public String create(DeptP deptP){
        deptPService.createDept(deptP);
        return "redirect:/dept/list";
    }

    @RequestMapping("toupdate")
    public String toCreate(String deptId,Model model){
        //根据部门编号查询当前，ie被选中部门信息
        DeptP deptP = deptPService.findByDeptId(deptId);
        //塞进域对象中
        model.addAttribute("dept",deptP);
        //查询部门列表
        List<DeptP> depts = deptPService.listDept();
        model.addAttribute("depts",depts);
        return "sysadmin/dept/jDeptUpdate";
    }



    @RequestMapping("update")
    public String update(DeptP deptP){
        deptPService.updateDept(deptP);
        return "redirect:/dept/list";
    }

    @RequestMapping("/export")
    public void export(PageBean pageBean, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        //查询分页数据
        PageBean pb = deptPService.listDeptOfPage(pageBean);
        System.out.println(pb+"------------------------->");

        List<DeptVo> data = (List<DeptVo>) pb.getData();
        System.out.println(data);
        //获取excel模板
        String realPath = request.getSession().getServletContext().getRealPath("/");
        String filePath = "/tpl/dept_export.xlsx";
        ExportExcelUtil exportExcelUtil = new ExportExcelUtil();
        File excelTplFile = exportExcelUtil.getExcelTplFile(realPath, filePath);
        //获取工作簿
        Workbook workbook = exportExcelUtil.getWorkbook(excelTplFile);
        //获取工作薄中的sheet
        Sheet sheet = exportExcelUtil.getSheet(workbook, "部门信息");
        for (DeptVo deptVo:data){
            //创建行
            Row row = exportExcelUtil.createRow(sheet);
            //创建单元格
            //第一列单元格
            Cell cell0 = exportExcelUtil.createCell(row, 0);
            cell0.setCellValue(deptVo.getDeptNo());
            //第二列单元格
            Cell cell1 = exportExcelUtil.createCell(row, 1);
            cell1.setCellValue(deptVo.getParentName());
            //第三列单元格
            Cell cell2 = exportExcelUtil.createCell(row, 2);
            cell2.setCellValue(deptVo.getDeptName());
            //第四列单元格
            Cell cell3 = exportExcelUtil.createCell(row, 3);
            cell3.setCellValue(deptVo.getState()==1?"启用":"禁用");
            //往外写出
            String fileName="dept_list.xlsx";
            response.setContentType("application/ms-excel");
            response.setHeader("Content-disposition", "attachment;filename="+fileName);
            ServletOutputStream outputStream = response.getOutputStream();
            workbook.write(outputStream);
            outputStream.flush();
            outputStream.close();


        }



    }

}
