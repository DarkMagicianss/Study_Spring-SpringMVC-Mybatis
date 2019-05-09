package cn.lh.user.controller;

import cn.lh.user.po.UserCustom;
import cn.lh.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

@Controller
public class LoginController {
    @Autowired
    private UserService userService;
    //登入用户信息
    @RequestMapping("/loginUser")
    public String loginUser(Model model, HttpServletRequest request, HttpServletResponse response, UserCustom userCustom) throws SQLException, UnsupportedEncodingException {
        //尝试进行用户登入操作
        UserCustom success_user = userService.loginUser(userCustom);
        String LogInfo = "" + success_user.getUsername() + "#" + success_user.getPassword();
        if(success_user != null){//用户已经登入成功
            //完成记住用户名的功能
            //将用户信息记录到cookie中
            Cookie rem_cookie = rememberUser(LogInfo);
            //回写cookie
            response.addCookie(rem_cookie);
            //存储用户信息，重定向到新页面
            HttpSession session = request.getSession();
            session.setAttribute("success_user",success_user);
            return "redirect:queryItems.action";
        }else{
            //此时登入用户的用户名和密码可能和数据库中的不一致
            //因此清楚记住用户名的cookie
            Cookie cle_cookie = clearRememberUser(request);
            //回写cookie
            if(cle_cookie!=null)
                response.addCookie(cle_cookie);
            model.addAttribute("log_user", LogInfo);
            model.addAttribute("login_error","用户名或者密码错误");
            return "login";
        }
    }

    public Cookie rememberUser(String LogInfo) throws UnsupportedEncodingException {
        String cookie_user = LogInfo;
        //解码
        //URLDecoder.decode(username,"UTF-8");
        //记住用户名,保存到cookie中
        Cookie cookie = new Cookie("log_user", cookie_user);
        //设置有效时间
        cookie.setMaxAge(60 * 60);
        //设置有效路径 使得整个服务都能获取cookie
        cookie.setPath("/");
        return cookie;
    }

    public Cookie clearRememberUser(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                //查找对应的username cookie
                if (cookie.getName().equals("log_user")) {
                    //取消对应cookie内容的保存
                    cookie.setMaxAge(0);
                    //设置有效路径 使得整个服务都能获取cookie
                    cookie.setPath("/");
                    return cookie;
                }
            }
        }
        return null;
    }
}
