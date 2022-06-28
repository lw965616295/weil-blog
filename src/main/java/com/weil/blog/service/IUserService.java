package com.weil.blog.service;

import com.weil.blog.common.Result;
import com.weil.blog.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author weil
 * @since 2022-06-15 15:21:15
 */
public interface IUserService extends IService<User> {
    /**
     * 登录操作
     * @param username
     * @param password
     * @Return:
     * @Auther: weil
     * @Date: 2022/6/20 17:21
     */
    User login(String username, String password);

    /**
     * 更新账号或者昵称
     */
    Result updateName(User name);

    /**
     * 更新密码
     */
    Result updatePwd(User user, String originalPassword, String newPassword);
}
