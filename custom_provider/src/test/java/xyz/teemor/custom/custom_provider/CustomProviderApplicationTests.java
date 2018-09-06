package xyz.teemor.custom.custom_provider;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.mgt.SecurityManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import xyz.teemor.custom.custom_provider.shiroConfig.UserRealm;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@MapperScan("xyz.teemor.custom.custom_provider.dao")
public class CustomProviderApplicationTests {

    @Autowired
    SecurityManager securityManager;


    @Test
    public void contextLoads() {
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("1", "57eb72e6b78a87a12d46a7f5e9315138");
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

    }

}
