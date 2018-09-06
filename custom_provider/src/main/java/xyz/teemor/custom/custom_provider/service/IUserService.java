package xyz.teemor.custom.custom_provider.service;

import xyz.teemor.custom.custom_provider.entity.User;
import xyz.teemor.custom.custom_provider.entity.UserPermission;
import xyz.teemor.custom.custom_provider.entity.UserRole;

import java.util.List;

public interface IUserService {
    List<UserPermission> selectPermissionByUserId(String uid);

    List<UserRole> selectRoleByUserId(String uid);

    User selectUserById(String uid);

    List<UserPermission> selectPermissionByType(Integer type);
}
