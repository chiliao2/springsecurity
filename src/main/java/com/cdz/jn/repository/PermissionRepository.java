package com.cdz.jn.repository;

import com.cdz.jn.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PermissionRepository extends JpaRepository<Permission, Integer> {

    @Query(value = "select p.* from `user` u LEFT JOIN user_role  ur ON u.id = ur.user_id LEFT JOIN role  r on ur.roles_id = r.id LEFT JOIN role_permission rp on r.id = rp.role_id LEFT JOIN permission p on rp.permissions_id = p.id where u.username =:user", nativeQuery = true)
    List<Permission> queryCurrentUserPermissions(@Param("user") String userName);
}
