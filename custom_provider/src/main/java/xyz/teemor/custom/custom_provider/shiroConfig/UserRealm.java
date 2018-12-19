package xyz.teemor.custom.custom_provider.shiroConfig;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import xyz.teemor.custom.custom_provider.entity.User;
import xyz.teemor.custom.custom_provider.entity.UserPermission;
import xyz.teemor.custom.custom_provider.entity.UserRole;
import xyz.teemor.custom.custom_provider.service.IUserService;
import xyz.teemor.custom.custom_provider.util.JWTUtil;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserRealm extends AuthorizingRealm {
    @Autowired
    IUserService userService;

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) throws AuthenticationException {
        String token = (String) auth.getCredentials();
        // 解密获得username，用于和数据库进行对比
        String username = token;
        User user = userService.selectUserById(username);
        System.out.println("----->>userInfo="+user.toString());
        if (user == null) {
            return null;
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                user.getAccount(), //用户名
                user.getPwd(), //密码
                getName()  //realm name
        );
        return authenticationInfo;
    }

    /**
     * 提供用户信息返回权限信息
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
//        Object user1=  principals.getPrimaryPrincipal();
//        User user=new User();
        String username = principals.toString();

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        // 根据用户名查询当前用户拥有的角色
        List<UserRole> roles = userService.selectRoleByUserId(username);
        Set<String> roleNames = new HashSet<String>();
        for (UserRole role : roles) {
            roleNames.add(role.getRoleName());
        }
        // 将角色名称提供给info
        authorizationInfo.setRoles(roleNames);
        // 根据用户名查询当前用户权限
        List<UserPermission> permissions = userService.selectPermissionByUserId(username);
        Set<String> permissionNames = new HashSet<String>();
        for (UserPermission permission : permissions) {
            permissionNames.add(permission.getPerName());
        }
        // 将权限名称提供给info
        authorizationInfo.setStringPermissions(permissionNames);

        return authorizationInfo;
    }
}
