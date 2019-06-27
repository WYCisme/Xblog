package com.blog.common.utils;

import com.blog.BaseUtil;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author zx
 * @date 2019/5/5
 */
public class WebUtilsTest extends BaseUtil {

    @Test
    public void getBaseUrl() {
        System.out.println("baseurl:"+WebUtils.getBaseUrl());
    }

    @Test
    public void getUrl() {
        System.out.println("url:"+WebUtils.getUrl());
    }

    @Test
    public void getProjectUrl() {
        System.out.println("projecturl:"+WebUtils.getProjectUrl());
    }

    @Test
    public void getSystemUrl() {
        System.out.println("systemUrl"+WebUtils.getSystemUrl());
    }
}