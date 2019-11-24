package com.baoyong.springboot.config;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户详情和管理器的实现类
 * 数据库和内存的区别在于DAO实现下面的方法还是直接使用我下面定义的users
 * @author byJiang
 * @title UserDetailsServiceAndManager
 * @date 2019/11/24
 **/
public class UserDetailsServiceAndManager implements UserDetailsManager {
    public static Map<String, UserDetails> users = new HashMap<>();
    // 新增一个基本用户到内存作为默认用户，如果是数据库，不需要有这一步创建内存用户，全部使用DAO查询数据库
    // {noop} means not to encode
    static{
        User user = new User("root", "{noop}12345", AuthorityUtils.NO_AUTHORITIES);
        users.put("root", user);
    }

    @Override
    public void createUser(UserDetails user) {
        users.put(user.getUsername(), user);
    }

    @Override
    public void updateUser(UserDetails user) {
        users.put(user.getUsername(), user);
    }

    @Override
    public void deleteUser(String username) {
        users.remove(username);
    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {
        // 获取当前认证用户
        // 1.通过SecurityContextHolder.getContext().getAuthentication() 方法获取当前认证用户，
        // 2.然后可以获取username,
        // 3.修改对应的用户的密码
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            throw new AccessDeniedException("can't change password as no authentication Object found in context "
            + "for current user null");
        }
        String username = authentication.getName();
        UserDetails user = users.get(username);
        if (user == null) {
            throw new IllegalStateException("current user does not exist in database");
        }
        // update database password of user

    }

    @Override
    public boolean userExists(String username) {
        return !(null == users.get(username));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return users.get(username);
    }
}
