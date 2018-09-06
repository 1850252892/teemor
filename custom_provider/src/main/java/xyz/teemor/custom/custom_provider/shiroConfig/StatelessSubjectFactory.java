package xyz.teemor.custom.custom_provider.shiroConfig;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.SubjectContext;
import org.apache.shiro.web.mgt.DefaultWebSubjectFactory;

public class StatelessSubjectFactory extends DefaultWebSubjectFactory {
    @Override
    public Subject createSubject(SubjectContext context) {
        //不创建session
        context.setSessionCreationEnabled(false);
        return super.createSubject(context);
    }
}
