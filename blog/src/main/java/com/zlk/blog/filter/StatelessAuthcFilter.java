package com.zlk.blog.filter;

import com.alibaba.fastjson.JSON;
import com.zlk.blog.model.ResponseModel;
import com.zlk.blog.shiroConfig.JWTToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class StatelessAuthcFilter extends BasicHttpAuthenticationFilter {

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest req = (HttpServletRequest) request;
        String authorization = req.getHeader("Authorization");
        if (authorization==null||authorization=="")
            onNotLogin(response);
        JWTToken token=new JWTToken(authorization);
        try {
            Subject subject = this.getSubject(request, response);
            subject.login(token);
        } catch (Exception e) {
            onTokenError(response); //6、登录失败
            return false;
        }
        return true;
    }

    //登录失败时默认返回401状态码
    private void onTokenError(ServletResponse response) throws IOException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        ResponseModel model=new ResponseModel();
        model.setCode(401);
        model.setMsg("token error");
        model.setData("");
        httpResponse.getWriter().write(JSON.toJSONString(model));
    }

    //登录失败时默认返回401状态码
    private void onNotLogin(ServletResponse response) throws IOException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        ResponseModel model=new ResponseModel();
        model.setCode(200);
        model.setMsg("not login");
        model.setData("");
        httpResponse.getWriter().write(JSON.toJSONString(model));
    }
}
