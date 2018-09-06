package xyz.teemor.custom.custom_provider.shiroConfig;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.teemor.custom.custom_provider.entity.User;
import xyz.teemor.custom.custom_provider.model.ResponseModel;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/11.
 */
@Controller
public class ShiroController {

    /**
     * 登录方法
     * @param
     * @return
     */
    @RequestMapping(value = "/ajaxLogin")
    @ResponseBody
    public void ajaxLogin() {
//        ResponseModel responseModel=new ResponseModel();
//        String username=user.getAccount();
//        String password=user.getPwd();
//        if (username==null||password==null){
//
//        }
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("1008611","123456");
        Map<String,String> result=new HashMap<>();
        try {
            subject.login(token);
            System.out.println("登录成功");
        } catch (IncorrectCredentialsException e) {
            System.out.println("密码错误");
        } catch (LockedAccountException e) {
            System.out.println("登录失败，该用户已被冻结");
        } catch (AuthenticationException e) {
            System.out.println("该用户不存在");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("is role:"+subject.hasRole("系统管理员"));
        throw new UnauthorizedException();

    }

    @RequestMapping("/test")
    @RequiresPermissions(value={"test"},logical = Logical.OR)
    public @ResponseBody String test(){
        return "test";
    }

    @RequestMapping("/403")
    public String noAuth(){
        return "403";
    }

    /**
     * 未登录，shiro应重定向到登录界面，此处返回未登录状态信息由前端控制跳转页面
     * @return
     */
    @RequestMapping(value = "/unauth")
    @ResponseBody
    public Object unauth() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", "1000000");
        map.put("msg", "未登录");
        return map;
    }

    @RequestMapping("/loginPage")
    public String login(){

        return "pages/login";
    }
}
