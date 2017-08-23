package com.cdz.jn.repository;

import com.cdz.jn.entity.User;

/**
 * 访问数据接口
 */
public interface UserRepository {
    User findByUsername(String username);
}
