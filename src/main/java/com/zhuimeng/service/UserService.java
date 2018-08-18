package com.zhuimeng.service;

import com.zhuimeng.pojo.Users;

/**
 * Created by Administrator on 2018/8/8.
 */
public interface UserService {
    Users selectByPrimaryKey(Integer userid);
}
