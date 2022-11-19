package cn.yunhe.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * @Author:Shizhan
 * @ProjectName:freight-codes
 * @currentTime: 2022/9/6 16:28
 */
public class CustomRealm extends AuthorizingRealm {
    /**
     * //认证方法，可从authentication获取凭证信息
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //抓过来用户的账号名和密码。
        String username = (String) authenticationToken.getPrincipal();//身份信息
        //根据username查询数据库
        if (!username.equals("zhangsan")){
            return null;
        }
        //获取密码信息 --->从数据库获取,这里只是模拟一下
        String password = "123";
        //创建认证对象
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(username,password,this.getName());

        return info;
    }
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }


}
