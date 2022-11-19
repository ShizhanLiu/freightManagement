package cn.yunhe.demo;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;

/**
 * @Author:Shizhan
 * @ProjectName:freight-codes
 * @currentTime: 2022/9/6 16:03
 */
public class Test2 {
    public static void main(String[] args) {
        //构建SecurityManager工厂，IniSecurityManagerFactory可以从ini文件中初始化SecurityManager环境，因为这里是用的自定义域

        IniSecurityManagerFactory iniSecurityManagerFactory = new IniSecurityManagerFactory("classpath:shiro-realm.ini");

        // 通过工厂创建SecurityManager
        SecurityManager securityManager = iniSecurityManagerFactory.getInstance();
        // 使用SecurityUtils将securityManager设置到运行环境中
        SecurityUtils.setSecurityManager(securityManager);

        // 创建一个Subject实例，该实例认证要使用上边创建的securityManager进行
        Subject subject = SecurityUtils.getSubject();

        // 创建token令牌，记录用户认证的身份和凭证即账号和密码,这是抓来用户的账号密码
        UsernamePasswordToken token = new UsernamePasswordToken("zhangsan","123");

        // 用户登录
        subject.login(token);
        System.out.println();
        // 用户认证状态
        Boolean isAuthenticated = subject.isAuthenticated();
        System.out.println("用户认证状态：" + isAuthenticated);

        // 退出登录
        subject.logout();
        isAuthenticated = subject.isAuthenticated();
        System.out.println("用户认证状态：" + isAuthenticated);



    }
}
