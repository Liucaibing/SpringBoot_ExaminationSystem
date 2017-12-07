package examination.controller;

import examination.entity.User;
import examination.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    LoginService loginService;


    @ResponseBody
    @RequestMapping("/hello")
    public String hello() {
        return "Hello World";
    }

    @RequestMapping(value = "/doLogin",method = RequestMethod.POST)
    @ResponseBody
    String doLogin(String account, String password,HttpSession httpSession) {
        User user = null;
        try{
            user = loginService.doLogin(account, password);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("笨！你又忘开数据库了");
        }

        if (null != user) {

            httpSession.setAttribute("userid", user.getId());
            httpSession.setAttribute("name", user.getName());
            httpSession.setAttribute("permission", user.getPermission());

            String pageName = user.getPermission().toLowerCase();
            return "/"+pageName;
        }
        return "/wrongpassword";
    }


    @RequestMapping(value = "/")
    String index() {
        return "redirect:/login";
    }
    @RequestMapping(value = "/login")
    String login() {
        return "login";
    }

    @RequestMapping(value = "/session")
    String session(Model model,HttpSession httpSession) {

        model.addAttribute("permission", httpSession);
        return "session";
    }
    @RequestMapping(value = "/logout")
    String logout(Model model,HttpSession httpSession) {

        httpSession.removeAttribute("userid");
        httpSession.removeAttribute("name");
        httpSession.removeAttribute("permission");

        return "logout";
    }
    @RequestMapping(value = "/notpermission")
    String notlogin(Model model) {
        return "notpermission";
    }

    @RequestMapping(value = "/wrongpassword")
    String wrongpassword(Model model) {
        return "wrongpassword";
    }
}
