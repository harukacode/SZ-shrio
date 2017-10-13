package com.harukacode;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017-10-11.
 */
@Service
public class HelloAnno {
    @Autowired
    private org.apache.shiro.mgt.SecurityManager sm = null;
    @RequiresAuthentication
    @RequiresPermissions({"p1"})
    public void t(){
        System.out.println("ok=========");
    }
    public void login(){
        UsernamePasswordToken token = new UsernamePasswordToken("javass","cc");
        token.setRememberMe(true);
        SecurityUtils.setSecurityManager(sm);
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.login(token);
    }
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        HelloAnno t = (HelloAnno)ctx.getBean("helloAnno");
        t.login();
        t.t();
    }
}
