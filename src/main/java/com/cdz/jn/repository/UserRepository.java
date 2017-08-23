package com.cdz.jn.repository;

import com.cdz.jn.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 访问数据接口
 */
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
}
