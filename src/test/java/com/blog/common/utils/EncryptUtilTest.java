package com.blog.common.utils;

import com.blog.BaseUtil;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Avalon
 * @date 2019/2/15
 */
public class EncryptUtilTest extends BaseUtil {

    @Test
    public void keyCreate() {

        String a = EncryptUtil.getInstance().KeyCreate(12);
        System.out.println(a);
    }
}