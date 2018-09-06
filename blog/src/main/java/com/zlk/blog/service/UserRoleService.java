package com.zlk.blog.service;

import com.zlk.blog.entity.UserRole;

public interface UserRoleService {
    int insert(String uId, String role);

    int delete(String uId, String role);

    UserRole select(String uId);

}
