package com.cdz.jn.service;

import com.cdz.jn.entity.Permission;
import com.cdz.jn.entity.User;
import com.cdz.jn.repository.PermissionRepository;
import com.cdz.jn.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(s);
        if (user == null) {
            throw new UsernameNotFoundException("invalid user");
        }

        List<GrantedAuthority> gas = new ArrayList<GrantedAuthority>();
        //获得当前用户的url权限信息
        List<Permission> permissions = permissionRepository.queryCurrentUserPermissions(user.getUsername());
        if (permissions != null && permissions.get(0) != null) {
            permissions.forEach(p -> gas.add(new SimpleGrantedAuthority(p.getName())));
        }

        //获得用户的角色信息
        gas.addAll(user.getAuthorities());
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), gas);
    }
}
