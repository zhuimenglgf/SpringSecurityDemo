package com.zhuimeng.service.impl;

import com.zhuimeng.dao.UsersMapper;
import com.zhuimeng.pojo.Users;
import com.zhuimeng.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2018/8/8.
 */
@Service("userService")
public class UserServiceImpl implements UserService,UserDetailsService {
    @Autowired
    private UsersMapper usersMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Users selectByPrimaryKey(Integer userid) {
        return usersMapper.selectByPrimaryKey(userid);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new User(username,passwordEncoder.encode("123456"),true,true,true,true, AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }
}
