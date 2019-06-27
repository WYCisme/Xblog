package com.blog.common.config.shiro;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.blog.model.entity.Admin;
import com.blog.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 自定义shiro
 *
 * @author Avalon
 * @date 2019/3/26
 */
@Component
@Slf4j
public class MyShiroRealm extends AuthorizingRealm {

    @Autowired
    private AdminService adminService;

    /**
     * 权限信息，包括角色以及权限
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        // 也就是SimpleAuthenticationInfo构造的时候第一个参数传递需要User对象
        Admin admin = (Admin)principals.getPrimaryPrincipal();
        List<String> roleList = adminService.findPermissions(admin.getId());
        List<String> permissionList = adminService.findPermissions(admin.getId());
        Set<String> role = new HashSet<>(roleList.size());
        Set<String> permission = new HashSet<>(permissionList.size());
        role.addAll(roleList);
        permission.addAll(permissionList);
        authorizationInfo.setRoles(role);
        authorizationInfo.setStringPermissions(permission);

        return authorizationInfo;
    }

    /**
     * 主要是用来进行身份认证的，也就是说验证用户输入的账号和密码是否正确
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        // 获取用户的输入的账号.
        String userName = (String)token.getPrincipal();
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Admin::getUsername, userName);
        Admin admin = adminService.getOne(queryWrapper);
        if (admin == null) {
            return null;
        }
        // 第一参数传递一个对象
        SimpleAuthenticationInfo authenticationInfo =
            new SimpleAuthenticationInfo(admin, admin.getPassword(), ByteSource.Util.bytes(admin.getSalt()), getName());

        return authenticationInfo;
    }

    public static void main(String[] args) {
        String hashAlgorithmName = "MD5";// 加密方式
        Object crdentials = "123456";// 密码原值
        Object salt = "P95b8QWk";// 盐值
        int hashIterations = 2;// 加密1024次
        Object result = new SimpleHash(hashAlgorithmName, crdentials, salt, hashIterations);
        System.out.println(result);

    }

}
