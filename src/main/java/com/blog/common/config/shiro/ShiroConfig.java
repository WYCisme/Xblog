package com.blog.common.config.shiro;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.Filter;

import com.blog.common.config.shiro.MyShiroFilter;
import lombok.extern.slf4j.Slf4j;

import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.ShiroFilter;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.blog.common.config.shiro.MyShiroRealm;

/**
 * Shiro配置
 *
 * @author Avalon
 * @date 2019/3/27
 */
@Configuration
@Slf4j
public class ShiroConfig {

    @Bean("sessionManager")
    public SessionManager sessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setSessionValidationSchedulerEnabled(true);
        sessionManager.setSessionIdCookieEnabled(true);
        return sessionManager;
    }

    @Bean("securityManager")
    public SecurityManager securityManager(MyShiroRealm myShiroRealm, SessionManager sessionManager) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        myShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        securityManager.setRealm(myShiroRealm);
        securityManager.setSessionManager(sessionManager);
        return securityManager;
    }

    /**
     * shiro 加密
     *
     * @param credentialsMatcher
     * @return
     */
    @Bean(name = "myShiroRealm")
    public MyShiroRealm authRealm(@Qualifier("hashedCredentialsMatcher") CredentialsMatcher credentialsMatcher) {
        MyShiroRealm authRealm = new MyShiroRealm();
        authRealm.setCredentialsMatcher(credentialsMatcher);
        return authRealm;
    }

    /**
     * shiro 加密
     * 
     * @return
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        // 加密方式
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        // 加密次数
        hashedCredentialsMatcher.setHashIterations(2);
        return hashedCredentialsMatcher;
    }

    /**
     * 过虑器,启动时加载
     * 
     * @param securityManager
     * @return
     */
    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        log.info("shiro初始化");
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        shiroFilter.setSecurityManager(securityManager);

        // shiro过滤
        Map<String, Filter> filters = new HashMap<>();
        filters.put("authc", new MyShiroFilter());
        shiroFilter.setFilters(filters);

        Map<String, String> filterMap = new LinkedHashMap<>();
        // 样式
        filterMap.put("/static-back/**", "anon");
        filterMap.put("/static-front/**", "anon");
        filterMap.put("/druid/**", "anon");
        filterMap.put("/captcha/captcha", "anon");
        filterMap.put("/back/logining", "anon");
        filterMap.put("/back/login", "anon");
        filterMap.put("/", "anon");
        filterMap.put("/back/register", "anon");
        filterMap.put("/aritle/**", "anon");
        filterMap.put("/back/**", "authc");

        // 未授权界面; AuthenticatingFilter
        shiroFilter.setUnauthorizedUrl("redirect:/home/index");
        shiroFilter.setFilterChainDefinitionMap(filterMap);

        return shiroFilter;
    }

    @Bean("lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * 开启shiro框架注解支持
     * 
     * @return
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator proxyCreator = new DefaultAdvisorAutoProxyCreator();
        // 必须使用cglib方式为Action对象创建代理对象
        proxyCreator.setProxyTargetClass(true);
        return proxyCreator;
    }

    /**
     * 配置shiro框架提供的切面类，用于创建代理对象
     * 
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }

    /*
     * shiro缓存管理器;
     * 需要注入对应的其它的实体类中-->安全管理器：securityManager可见securityManager是整个shiro的核心；
     */
    // @Bean
    // public EhCacheManager ehCacheManager() {
    // EhCacheManager cacheManager = new EhCacheManager();
    // cacheManager.setCacheManagerConfigFile("classpath:ehcache.xml");
    // return cacheManager;
    // }

}
