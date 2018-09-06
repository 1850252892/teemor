package xyz.teemor.custom.custom_provider.dao;

import xyz.teemor.custom.custom_provider.entity.UserPermission;

import java.util.List;
import java.util.Map;

public interface PermissionDao {
    List<UserPermission> selectPermissionByUserId(String uid);

    List<UserPermission> selectPermissionByType(Integer type);

    Integer insertPermission(UserPermission permission);

    Integer insertRp(Map<String,Integer> map);

}
