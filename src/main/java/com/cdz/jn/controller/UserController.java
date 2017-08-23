package com.cdz.jn.controller;

import com.cdz.jn.entity.Role;
import com.cdz.jn.entity.User;
import com.cdz.jn.repository.RoleRepository;
import com.cdz.jn.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    /**
     * 测试项目开始之前先访问一下http://localhost:8080/user/add
     *
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addUser() {

        //增加用户kermit，角色为ADMIN
        Role role = new Role();
        role.setName("ROLE_USER");
        roleRepository.save(role);
        User user = new User();
        user.setName("小王");
        user.setUsername("kermit");
        user.setPassword(passwordEncoder.encode("kermit"));
        user.getRoles().add(role);
        userRepository.save(user);

        /**
         * 增加用户为lili,角色为USER
         */
        Role role2 = new Role();
        role2.setName("ROLE_ADMIN");
        roleRepository.save(role2);
        User user2 = new User();
        user2.setName("小玲");
        user2.setUsername("lili");
        user2.setPassword(passwordEncoder.encode("lili"));
        user2.getRoles().add(role2);
        userRepository.save(user2);
        return "success";
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    @PreAuthorize("hasRole('USER')")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
