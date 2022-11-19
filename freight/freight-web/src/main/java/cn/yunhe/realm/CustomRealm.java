package cn.yunhe.realm;

import cn.yunhe.pojo.CurrentUser;
import cn.yunhe.pojo.ModuleP;
import cn.yunhe.pojo.UserP;
import cn.yunhe.service.UserPService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

/**
 * @Author:Shizhan
 * @ProjectName:freight-codes
 * @currentTime: 2022/9/7 14:42
 */
public class CustomRealm extends AuthorizingRealm {
    @Autowired
    private UserPService userPService;

    /**
     * 认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //获取身份信息
        String userName = (String) authenticationToken.getPrincipal();
        UserP userP = userPService.loginP(userName);
        if (userP==null){
            return null;
        }
        //获取密码信息
        String password = userP.getPassword();
        //生成的盐，等于用户名加用户编号
        String salt = userP.getUserName() +userP.getUserId();
       // SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(userName,password, ByteSource.Util.bytes(salt),this.getName());
        //根据用户信息查询部门信息和用户扩展信息
        CurrentUser currentUser = userPService.findUserInfoDeptByUser(userP);
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(currentUser,password,ByteSource.Util.bytes(salt),this.getName());



        return info;
    }

    /**
     * 授权
     * @param principalCollection
     * @return
     */

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取认证的身份信息
        CurrentUser currentUser = (CurrentUser) principalCollection.getPrimaryPrincipal();
        //获取用户信息
        UserP userP = currentUser.getUserP();
        //根据用户编号查找该用户拥有的权限
        List<ModuleP> permissions = userPService.getPermissionByUserId(userP.getUserId());
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        if (permissions!=null&&permissions.size()>0){
            //遍历集合 添加权限名称
            for (ModuleP moduleP:permissions){
                info.addStringPermission(moduleP.getCpermission());
            }

        }
        return info;


    }
    //清空缓存:在权限修改后调用realm中的方法，realm已经由spring管理，所以从spring中获取realm实例，调用clearCached方法。
    public void clearCached() {
        //获取身份信息
        PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
        //调用父类中清空缓存的方法
        super.clearCache(principals);
    }
}
