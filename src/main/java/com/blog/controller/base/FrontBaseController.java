package com.blog.controller.base;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.blog.common.utils.WebUtils;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.blog.common.constants.SystemConstants;
import com.blog.common.utils.CacheUtil;
import com.blog.model.entity.Admin;
import com.blog.model.vo.R;
import com.blog.service.AdminService;

/**
 * 基类控制器
 *
 * @author zx
 * @date 2019/1/28
 */
@Slf4j
@Controller
public class FrontBaseController extends BaseController {

    @Autowired
    protected AdminService adminService;

    /**
     * 获取管理员ID
     *
     * @return
     */
    public R<Admin> getAdminByAccessToken() {
        HttpServletRequest request = WebUtils.getRequest();
        String accessToken = request.getHeader("accessToken");
        QueryWrapper<Admin> adminQueryWrapper = new QueryWrapper<>();
        adminQueryWrapper.lambda().eq(Admin::getSubmitToken, accessToken);
        Admin admin = adminService.getOne(adminQueryWrapper);
        if (admin == null) {
            return R.errorT("获取用户凭证失败!", null);
        }
        return R.okT(admin);
    }


}
