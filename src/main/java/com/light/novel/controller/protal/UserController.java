package com.light.novel.controller.protal;

import com.light.novel.common.Const;
import com.light.novel.common.ServerResponse;
import com.light.novel.enity.User;
import com.light.novel.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user/")
public class UserController {

    @Autowired
    private IUserService iUserService;


    /*用户登录*/
    @RequestMapping(value = "login.do", method = RequestMethod.POST)
    @ResponseBody()
    public ServerResponse<User> login(String username, String password, HttpSession session) {

        ServerResponse<User> response = iUserService.login(username, password);
        if (response.isSuccess()) {
            session.setAttribute(Const.CURRENT_USER, response.getData());
        }
        return response;
    }


    @RequestMapping(value = "logout.do", method = RequestMethod.GET)
    @ResponseBody()
    public ServerResponse<String> logout(HttpSession session) {
        session.removeAttribute(Const.CURRENT_USER);
        return ServerResponse.createBySuccess();
    }


    @RequestMapping(value = "register.do", method = RequestMethod.GET)
    @ResponseBody()
    public ServerResponse<String> register(User user) {
        return iUserService.register(user);
    }


    @RequestMapping(value = "check_valid.do", method = RequestMethod.GET)
    @ResponseBody()
    public ServerResponse<String> checkValid(String str, String type) {
        return iUserService.checkValid(str, type);
    }


    @RequestMapping(value = "get_user_info.do", method = RequestMethod.GET)
    @ResponseBody()
    public ServerResponse<User> getUserInfo(HttpSession session) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user != null) {
            return ServerResponse.createBySuccess(user);
        }
        return ServerResponse.createByErrorMessage("用户未登陆,无法获取当前用户的信息");
    }


    @RequestMapping(value = "forget_get_question.do", method = RequestMethod.GET)
    @ResponseBody()
    public ServerResponse<String> forgetGetQuestion(String username) {
        return iUserService.selectQuestion(username);
    }


    @RequestMapping(value = "forget_check_question.do", method = RequestMethod.GET)
    @ResponseBody()
    public ServerResponse<String> forgetCheckAnswer(String username, String question, String answer) {
        return iUserService.checkAnswer(username, question, answer);
    }


    @RequestMapping(value = "forget_reset_password.do", method = RequestMethod.GET)
    @ResponseBody()
    public ServerResponse<String> forgetRestPassword(String username, String passwordNew, String forgetToken) {
        return iUserService.forgetRestPassword(username, passwordNew, forgetToken);
    }

    @RequestMapping(value = "reset_password.do", method = RequestMethod.GET)
    @ResponseBody()
    public ServerResponse<String> resetPassword(HttpSession session, String passwordOld, String passwordNew) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorMessage("用户未登陆");
        }
        return iUserService.resetPasswored(passwordOld, passwordNew,user);
    }

    @RequestMapping(value = "aa.do", method = RequestMethod.POST)
    public String aa() {
        return "aa"; 
    }
}
