package com.blog.common.constants;

import com.blog.common.utils.WebUtils;
import org.springframework.util.ClassUtils;
import org.springframework.util.ResourceUtils;

import java.io.FileNotFoundException;

/**
 * 静态常量类
 *
 * @author zx
 * @date 2019/1/28
 */
public class AppConstants {

    /** session 有效期 **/
    public static final String USER_SESSION = "admin_serssion";

    /** 翻页默认参数 **/
    public static int PAGE_DEFAULT_PAGE = 1;
    public static int PAGE_DEFAULT_LIMIT = 20;

    /** 登录 **/
    public static final String LOGIN = "/back/login";

    /** 主页 **/
    public static final String HOME = "/home/";

    /** cookie 有效期 **/
    public static int COOKIE_EXPIRE = 7 * 24 * 60 * 60;




    /** 上传文件路径 **/
    public static final String UPLOAD_PATH = "/static/static-front/upload/";



    private AppConstants() {}

}
