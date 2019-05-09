package cn.lh.user.controller;

import cn.lh.user.po.UserCustom;
import cn.lh.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@Controller
public class RegController {
    public static final int CHECKSUCCESS = 0;
    public static final int NAMEEXIST = 1;
    public static final int EMAILEXIST = 2;

    @Autowired
    private UserService userService;

    //注册用户信息
    @RequestMapping("/regUser")
    public String regUser(HttpServletRequest request, Model model, UserCustom userCustom) throws SQLException, ServletException, IOException {
        //用户名不能重复,邮箱是否重名
        //使用user对象查询mysql中是否具有相同用户名的信息
        int result = userService.checkUser(userCustom);
        String RegError = "" + userCustom.getUsername() + "#" + userCustom.getPassword() + "#" +
                userCustom.getNickname() + "#" + userCustom.getEmail();
        if (result == RegController.NAMEEXIST) {
            //用户名重名了
            model.addAttribute("req_user", RegError);
            model.addAttribute("username_error", "用户名重复!");
            //在服务器内部转发回注册页面 不写项目名
            return "reg";
        } else if (result == RegController.EMAILEXIST) {
            //邮箱重名了
            model.addAttribute("req_user", RegError);
            model.addAttribute("email_error", "邮箱重复!");
            //在服务器内部转发回注册页面 不写项目名
            return "reg";
        } else {//result == RegController.CHECKSUCCESS
            //判断验证码是否正确
            //获取request提交的验证码
            String checkcode_post = userCustom.getCheckcode();
            //拿到CheckServlet存入Session的真实的验证码
            String checkcode_true = (String) request.getSession().getAttribute("checkcode");
            if (checkcode_post.equalsIgnoreCase(checkcode_true)) {
                //将用户数据写入mysql数据库
                userService.regUser(userCustom);
                //应该要进行重定向 改变地址栏的url 并不需要进行request对象数据的传输
                return "redirect:/jsp/login.jsp";
            } else {
                //验证码错误
                model.addAttribute("req_user", RegError);
                model.addAttribute("checkcode_error", "验证码错误!");
                //在服务器内部转发回注册页面 不写项目名
                return "reg";
            }
        }
    }
}
