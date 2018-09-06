package xyz.teemor.custom.custom_provider.filter;

import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;
import xyz.teemor.custom.custom_provider.shiroConfig.JWTToken;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class MyPermissionFilter extends PermissionsAuthorizationFilter {

    @Override
    public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        String authorization = req.getHeader("Authorization");
        JWTToken token=new JWTToken(authorization);
        Subject subject = this.getSubject(request, response);
        subject.login(token);
        return super.isAccessAllowed(request, response, mappedValue);
    }
}
