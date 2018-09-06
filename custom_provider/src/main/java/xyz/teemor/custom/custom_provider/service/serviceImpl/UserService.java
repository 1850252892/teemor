package xyz.teemor.custom.custom_provider.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.teemor.custom.custom_provider.dao.PermissionDao;
import xyz.teemor.custom.custom_provider.dao.RoleDao;
import xyz.teemor.custom.custom_provider.dao.UserDao;
import xyz.teemor.custom.custom_provider.entity.User;
import xyz.teemor.custom.custom_provider.entity.UserPermission;
import xyz.teemor.custom.custom_provider.entity.UserRole;
import xyz.teemor.custom.custom_provider.service.IUserService;

import java.util.List;

@Service
public class UserService implements IUserService {

    @Autowired
    UserDao userDao;
    @Autowired
    RoleDao roleDao;
    @Autowired
    PermissionDao permissionDao;


    @Override
    public List<UserPermission> selectPermissionByUserId(String uid) {
        return permissionDao.selectPermissionByUserId(uid);
    }

    @Override
    public List<UserRole> selectRoleByUserId(String uid) {
        return roleDao.selectRoleByUserId(uid);
    }

    @Override
    public User selectUserById(String uid) {
        User user=  userDao.selectUserById(uid);
        return user;
    }

    @Override
    public List<UserPermission> selectPermissionByType(Integer type) {
        return permissionDao.selectPermissionByType(type);
    }
}
