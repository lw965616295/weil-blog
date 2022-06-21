package com.weil.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.weil.blog.entity.User;
import com.weil.blog.mapper.UserMapper;
import com.weil.blog.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weil.blog.utils.MD5Util;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author weil
 * @since 2022-06-15 15:21:15
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Override
    public User login(String username, String password) {
        List<User> list = list(new LambdaQueryWrapper<>(User.class)
                .eq(User::getName, username)
                .eq(User::getPassword, MD5Util.getMd5(password))
                .eq(User::getForbidden, 0));
        if(list != null && !list.isEmpty()){
            return list.get(0);
        }
        return null;
    }
}
