package xyz.teemor.custom.custom_provider.filter;

import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

public class MyPermissionFilter extends PermissionsAuthorizationFilter {

    @Override
    public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws IOException {
        UsernamePasswordToken t = new UsernamePasswordToken("1008611","123456");
//            getSubject(request, response).login(t);
//            Subject subject = SecurityUtils.getSubject();
        Subject subject = this.getSubject(request, response);
        subject.login(t);
        return super.isAccessAllowed(request, response, mappedValue);
    }
}
