package com.zlk.blog.shiroConfig;

import com.zlk.blog.filter.MyPermissionFilter;
import com.zlk.blog.filter.StatelessAuthcFilter;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.DefaultSessionManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerExceptionResolver;
import xyz.teemor.tool.RedisUtil;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;


/**
 * Created by Administrator on 2017/12/11.
 */
@Configuration
public class ShiroConfig {
    // cas server地址
    public static final String casServerUrlPrefix = "http://127.0.0.1";
    // Cas登录页面地址
    public static final String casLoginUrl = casServerUrlPrefix + "/login";
    // Cas登出页面地址
    public static final String casLogoutUrl = casServerUrlPrefix + "/logout";
    // 当前工程对外提供的服务地址
    public static final String shiroServerUrlPrefix = "http://127.0.1.28:8080";
    // casFilter UrlPattern
    public static final String casFilterUrlPattern = "/index";
    // 登录地址
    public static final String loginUrl = casLoginUrl + "?service=" + shiroServerUrlPrefix + casFilterUrlPattern;
    // 登出地址（casserver启用service跳转功能，需在webapps\cas\WEB-INF\cas.properties文件中启用cas.logout.followServiceRedirects=true）
    public static final String logoutUrl = casLogoutUrl+"?service="+loginUrl;
    // 登录成功地址
//    public public final String loginSuccessUrl = "/index";
    // 权限认证失败跳转地址
    public static final String unauthorizedUrl = "/error/403.html";


    @Bean
    public RedisUtil redisUtil(){
        RedisUtil redisUtil= new RedisUtil();
        redisUtil.init();
        return redisUtil;
    }

    /**
     * 凭证匹配器
     * （由于我们的密码校验交给Shiro的SimpleAuthenticationInfo进行处理了
     * ）
     *
     * @return
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("md5");//散列算法:这里使用MD5算法;
        hashedCredentialsMatcher.setHashIterations(1);//散列的次数，比如散列两次，相当于 md5(md5(""));
        return hashedCredentialsMatcher;
    }

    @Bean
    public JwtRealm myShiroRealm() {
        JwtRealm realm = new JwtRealm();
        return realm;
    }

    @Bean
    public static LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public static DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator(){
        return new DefaultAdvisorAutoProxyCreator();
    }

    @Bean
    public StatelessSubjectFactory subjectFactory(){
        return new StatelessSubjectFactory();
    }
    @Bean(name = "securityManager")
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(myShiroRealm());
        DefaultSessionManager sessionManager=new DefaultWebSessionManager();
        sessionManager.setSessionValidationSchedulerEnabled(false);
       securityManager.setSessionManager(sessionManager);
//        // 自定义session管理 使用redis
        securityManager.setSubjectFactory(subjectFactory());
        DefaultSubjectDAO subjectDAO=new DefaultSubjectDAO();
        DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
        defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
        subjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
        securityManager.setSubjectDAO(subjectDAO);
        return securityManager;
    }

    //自定义sessionManager
    @Bean
    public SessionManager sessionManager() {
        MySessionManager mySessionManager = new MySessionManager();
        return mySessionManager;
    }





    /**
     * 开启shiro aop注解支持.
     * 使用代理方式;所以需要开启代码支持;
     *
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
    @Bean
    public Map<String,String> myPermissionMap(){
        return (Map<String, String>) redisUtil().getObject("permissions");
    }
//    @Bean
//    public StatelessAuthcFilter statelessAuthcFilter(){
//        return new StatelessAuthcFilter();
//    }
    @Bean
    public ShiroFilterFactoryBean shirFilter(@Qualifier("securityManager")SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String, Filter> filters=new HashMap<>();
        filters.put("myFilter",new StatelessAuthcFilter());
        filters.put("perms",new MyPermissionFilter());
        shiroFilterFactoryBean.setFilters(filters);
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        //注意过滤器配置顺序 不能颠倒
        // 配置不会被拦截的链接 顺序判断
        filterChainDefinitionMap.put("/public/**", "anon");
        filterChainDefinitionMap.put("/ajaxLogin", "anon");
        filterChainDefinitionMap.put("/jwtLogin", "anon");
        filterChainDefinitionMap.put("/login", "anon");
        filterChainDefinitionMap.put("/logout","anon");
        // 此处将logout页面设置为anon，而不是logout，因为logout被单点处理，而不需要再被shiro的logoutFilter进行拦截


        filterChainDefinitionMap.put("/user","user");
        filterChainDefinitionMap.putAll(myPermissionMap());
        filterChainDefinitionMap.put("/**", "myFilter");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    /**
     * 注册全局异常处理
     * @return
     */
    @Bean(name = "exceptionHandler")
    public HandlerExceptionResolver handlerExceptionResolver() {
       return new MyExceptionHandler();
    }
}
