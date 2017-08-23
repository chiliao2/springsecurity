package com.cdz.jn.repository;

import com.cdz.jn.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * 访问数据接口
 */
@NoRepositoryBean
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
}
