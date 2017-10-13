package com.harukacode;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");

        /**
         * 使用工厂方法来生成指定数据源类型的SM工厂，进而得到SM实例
         */
        Factory<SecurityManager> f = new
                IniSecurityManagerFactory("classpath:TestShiro.ini");
        org.apache.shiro.mgt.SecurityManager s = f.getInstance();

        /**
         * 将SM注入到SU中来管理，SM的意义是包含数据源，SU来获取到一个公用的Subject，改Subject采用单独的
         * token来登录，token可以使用RememberMe功能，应该是缓存的功能。
         */
        SecurityUtils.setSecurityManager(s);
        UsernamePasswordToken token = new UsernamePasswordToken("javass", "cc");

        /**
         * RememberMe功能
         */
        token.setRememberMe(true);
        /**
         * SU来获取到一个公用的Subject
         */
        Subject currentUser = SecurityUtils.getSubject();

        /**
         * 成功了没有返回，失败了就会抛出异常
         */
        currentUser.login(token);
        boolean flag = currentUser.isPermitted("p1");
        currentUser.logout();
        boolean flag2 = currentUser.isRemembered();
        System.out.println("flag==" + flag);
        System.out.println("flag2==" + flag2);
        System.out.println("flag3==" + token.isRememberMe());
    }
}
