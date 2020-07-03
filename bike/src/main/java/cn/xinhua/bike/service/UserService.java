package cn.xinhua.bike.service;

import cn.xinhua.bike.domain.User;

public interface UserService {
    /**
     * 注册用户
     * @param user
     * @return
     */
    boolean regist(User user);

    boolean active(String code);

    User login(User user);

    boolean addUserAvatar(String avatar,int uid);

    User findOne(int uid);
}
