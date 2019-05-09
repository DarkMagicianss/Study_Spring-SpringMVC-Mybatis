package cn.lh.user.service;

import cn.lh.user.controller.RegController;
import cn.lh.user.mapper.UserRegMapper;
import cn.lh.user.po.UserCustom;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.SQLException;

public class UserServiceImpl implements UserService{
    @Autowired
    private UserRegMapper userRegMapper;
    public int checkUser(UserCustom userCustom) throws SQLException {
        //使用user对象查询mysql中是否具有相同用户名的信息
        UserCustom select_user = userRegMapper.checkUser(userCustom);
        if(select_user != null){
            if(select_user.getUsername().equals(userCustom.getUsername())){
                //有查到结果用户名相同
                return RegController.NAMEEXIST;
            }else{
                //有查到结果不是用户名相同就是邮箱相同
                return RegController.EMAILEXIST;
            }
        }else{
            return RegController.CHECKSUCCESS;
        }
    }
    public void regUser(UserCustom userCustom) throws SQLException {
        //已通过验证便可直接进行用户注册
        //使用user对象进行mysql数据信息的添加
        userRegMapper.regUser(userCustom);
    }
    public UserCustom loginUser(UserCustom userCustom) throws SQLException {
        UserCustom select_user = null;
        select_user = userRegMapper.loginUser(userCustom);
        return select_user;
    }
}
