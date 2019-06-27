package com.blog.mapper;

import com.blog.BaseUtil;
import com.blog.model.entity.Admin;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author zx
 * @date 2019/2/11
 */
public class AdminMapperTest extends BaseUtil {

    @Autowired
    AdminMapper adminMapper;

    @Test
    public void selectByLoginnameAndPassword() {

        Admin admin = adminMapper.selectById(1L);
        System.out.println(admin.getId());

    }


    @Test
    public void test2(){
    }
}