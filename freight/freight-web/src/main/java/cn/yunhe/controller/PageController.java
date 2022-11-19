package cn.yunhe.controller;

import cn.yunhe.pojo.CurrentUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author:Shizhan
 * @ProjectName:freight-codes
 * @currentTime: 2022/9/7 17:02
 */
@Controller
public class PageController {
    //欢迎页
    @RequestMapping("/")
    public String index(){
        return "index"; // /WEB-INF/pages/index.jsp
    }

    //登录
    @RequestMapping("/index")
    public String showLogin(){
        System.out.println("page controller");
        return "index";
    }

    //跳转主页
    @RequestMapping("/main")
    public String main(){
        return "home/fmain";// /WEB-INF/pages/hom/fmain.jsp
    }
    //标题栏菜单
    @RequestMapping("/homeAction_title")
    public String homeAction_totitle(Model model){
        //获取主体
        Subject subject = SecurityUtils.getSubject();
        //获取身份信息
        CurrentUser currentUser = (CurrentUser) subject.getPrincipal();
        model.addAttribute("currentUser",currentUser);
        return "home/title";
    }

    //跳转左侧菜单
    @RequestMapping("homeAction_toleft")
    public String homeAction_left(String moduleName) throws Exception {
        return moduleName+"/left";
    }

    //跳转主窗口
    @RequestMapping("homeAction_tomain")
    public String homeAction_main(String moduleName) throws Exception {
        return moduleName + "/main";
    }

}
