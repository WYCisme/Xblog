package com.blog.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blog.model.entity.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.blog.model.vo.ArticleVO;
import com.blog.model.vo.PermissionVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhengxin
 * @since 2019-03-29
 */
@Repository
public interface PermissionMapper extends BaseMapper<Permission> {


    @Select({ " select p.id, p.permission, p.description , p.available , pt.name as permissionTypeName , pt.id as permissionTypeId  ",
        " from permission p left join  permission_type pt on p.permission_type_id = pt.id  ",
        " ${ew.customSqlSegment} "
    })
    List<PermissionVO> page(Page<PermissionVO> page,@Param(Constants.WRAPPER) Wrapper wrapper);


    @Select({ " select p.id, p.permission, p.description , p.available , pt.name as permissionTypeName , pt.id as permissionTypeId  ",
        " from permission p left join  permission_type pt on p.permission_type_id = pt.id  ",
        " ${ew.customSqlSegment} "
    })
    List<PermissionVO> list(@Param(Constants.WRAPPER) Wrapper wrapper);

}
