package cn.yunhe.controller;

import cn.yunhe.pojo.*;
import cn.yunhe.realm.CustomRealm;
import cn.yunhe.service.*;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.net.www.MessageHeader;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author:Shizhan
 * @ProjectName:freight-codes
 * @currentTime: 2022/9/6 14:00
 */
@Controller
@RequestMapping("/user")
public class UserPController {
    @Autowired
    private UserPService userPService;
    @Autowired
    private DeptPService deptPService;
    @Autowired
    private UserinfoPService userinfoPService;
    @Autowired
    private RolePService rolePService;
    @Autowired
    private RoleUserPService roleUserPService;
    @Autowired
    private CustomRealm customRealm;

    @Autowired
    private MailSender mailSender;


/*    @RequestMapping("/login")
    @ResponseBody
    public UserP login(String username ){
        UserP userP = userPService.loginP(username);
        return userP;
    }*/
    @RequestMapping("/login")
    public String login(HttpServletRequest request){
        //1.从request获取登录失败的信息
        String shiroLoginFailure = (String) request.getAttribute("shiroLoginFailure");
        //2.判断错误消息信息
        if (UnknownAccountException.class.getName().equals(shiroLoginFailure)){
            request.setAttribute("errorInfo","账号不正确");
        }
        if (IncorrectCredentialsException.class.getName().equals(shiroLoginFailure)){
            request.setAttribute("errorInfo","密码不正确");
        }
        return "index";

    }


    @RequestMapping("list")
    public String listOfPage(PageBean pageBean, Model model){
        //进来先调用userPservice方法，拿到新的pageBean
        PageBean pb = userPService.listUserOfPage(pageBean);
        System.out.println();
        model.addAttribute("pb",pb);
        model.addAttribute("url","/user/list");
        return "sysadmin/user/jUserList";

    }

    /**
     *
     * @param model
     * @return
     */
    @RequestMapping("/tocreate")
    public String toCreate(Model model){
        //查询部门信息
        List<DeptP> deptList = deptPService.listDept();
        model.addAttribute("ds",deptList);
        //查询用户扩展信息
        List<UserinfoP> userPList = userinfoPService.listUserinfo();
        model.addAttribute("uis",userPList);
        return "sysadmin/user/jUserCreate";


    }


    /**
     * 新增用户
     * @param userP
     * @param userinfoP
     * @return
     */
    @RequestMapping("create")
    public String createUser(UserP userP, UserinfoP userinfoP) throws MessagingException {
        userPService.createUser(userP,userinfoP);
        //向新增用户发送邮件
        userP.setPassword("111");
        //获取密码
        String password = userP.getPassword();
        String userName = userP.getUserName();//获取用户名
        JavaMailSenderImpl javaMailSender = (JavaMailSenderImpl)mailSender;
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
        mimeMessageHelper.setFrom("shizhanliu0627@sino.com"); //设置发送者
        mimeMessageHelper.setTo(userinfoP.getEmail()); //设置接收者
        mimeMessageHelper.setSubject("欢迎新员工");
        String html = "<html>" +
                " <body>" +
                "  <h1>欢迎新员工</h1>" +
                "  <P>您的账号是："+userP.getUserName()+"</p>" +
                "  <P>您的密码是："+password+"</p>" +
                " </body>" +
                "</html>";
        mimeMessageHelper.setText(html,true);
        //为了避免延时操作
        //因为发送邮件会有延迟，所以开启多线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                javaMailSender.send(mimeMessage);
            }
        }).start();




        return "redirect:/user/list";
    }

    /**
     * 为用户分配角色
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("torole")
    public String torole(String id, Model model) throws Exception {
        //查询用户扩展信息
        UserinfoP userInfoP = userinfoPService.getUserInfoPById(id);
        model.addAttribute("userInfoP",userInfoP);
        //查询所有的角色信息
        List<RoleP> rolePS = rolePService.listRole();
        model.addAttribute("rs",rolePS);


        //查询用户拥有的角色
        List<RoleUserPKey> roleUserPKeys = roleUserPService.listRoleUser(id);
        //将用户拥有的角色保存到数组中
        /*String[] roleIds = new String[roleUserPKeys.size()];*/
        String roleIds = "";
        for (int i = 0;i<roleUserPKeys.size();i++){
            RoleUserPKey roleUserPKey = roleUserPKeys.get(i);
            roleIds+=roleUserPKey.getRoleId()+",";
        }
        if (roleIds.length()>0){
            roleIds = roleIds.substring(0,roleIds.length()-1);
            System.out.println(roleIds+"---");
        }

        model.addAttribute("roleIds",roleIds);

        return "sysadmin/user/jUserRole";





    }
    @RequestMapping("/assignRole")
    public String assignRole(String id,String[] roleIds) throws Exception {
        roleUserPService.addRoleUser(id,roleIds);
        //清空缓存
        customRealm.clearCached();
        return "redirect:/user/list";


    }


}
