package cn.lh.user.service;

import cn.lh.user.po.UserCustom;

import java.sql.SQLException;

public interface UserService {
    public int checkUser(UserCustom userCustom) throws SQLException;
    public void regUser(UserCustom userCustom) throws SQLException;
    public UserCustom loginUser(UserCustom userCustom) throws SQLException;
}
