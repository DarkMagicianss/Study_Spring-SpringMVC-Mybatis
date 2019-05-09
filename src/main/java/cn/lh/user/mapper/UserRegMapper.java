package cn.lh.user.mapper;

import cn.lh.user.po.UserCustom;
import java.sql.SQLException;

public interface UserRegMapper {
    public  UserCustom checkUser(UserCustom userCustom) throws SQLException ;
    public  void regUser(UserCustom userCustom) throws SQLException ;
    public  UserCustom loginUser(UserCustom userCustom) throws SQLException ;
}
