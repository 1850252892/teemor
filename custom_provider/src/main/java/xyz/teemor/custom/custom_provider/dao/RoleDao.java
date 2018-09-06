package xyz.teemor.custom.custom_provider.dao;

import xyz.teemor.custom.custom_provider.entity.UserRole;

import java.util.List;
import java.util.Map;

public interface RoleDao {
    List<UserRole> selectRoleByUserId(String uid);

    Integer insertRole(UserRole role);

    Integer insertUr(Map<String,Integer> map);
}
