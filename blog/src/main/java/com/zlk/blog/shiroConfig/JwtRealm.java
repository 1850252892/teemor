package com.zlk.blog.shiroConfig;

import com.zlk.blog.entity.User;
import com.zlk.blog.entity.UserPermission;
import com.zlk.blog.entity.UserRole;
import com.zlk.blog.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import xyz.teemor.tool.JWTUtil;
import xyz.teemor.tool.RedisUtil;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class JwtRealm extends AuthorizingRealm {
    @Autowired
    UserService userService;
    @Autowired
    RedisUtil redisUtil;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String username = JWTUtil.getUsername(principals.toString());
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        // 根据用户名查询当前用户拥有的角色
        List<UserRole> roles = (List<UserRole>) redisUtil.getObject(username+"roles");
        Set<String> roleNames = new HashSet<String>();
        for (UserRole role : roles) {
            roleNames.add(role.getName());
        }
        authorizationInfo.setRoles(roleNames);
        // 根据用户名查询当前用户权限
        List<UserPermission> permissions = (List<UserPermission>) redisUtil.getObject(username+"permissions");
        Set<String> permissionNames = new HashSet<String>();
        for (UserPermission permission : permissions) {
            permissionNames.add(permission.getPerName());
        }
        // 将权限名称提供给info
        authorizationInfo.setStringPermissions(permissionNames);
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) throws AuthenticationException {
        String token = (String) auth.getCredentials();
        // 解密获得username，从redis中获取
        String username = JWTUtil.getUsername(token);
        if (username==null||JWTUtil.verify(token,username,JWTUtil.SECRET)){
           throw new UnauthenticatedException();
        }
        User user = (User) redisUtil.getObject(username);
        System.out.println("----->>userInfo="+user.toString());
        if (user == null) {
            throw new AuthenticationException("User didn't existed!");
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                token, //用户名
                token, //密码
                getName()  //realm name
        );
        return authenticationInfo;
    }
}
