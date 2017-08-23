package com.cdz.jn.repository;

import com.cdz.jn.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface RoleRepository extends JpaRepository<Role, Integer> {
}
