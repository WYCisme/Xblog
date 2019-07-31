package com.blog.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.blog.model.entity.RolePermission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.blog.model.vo.RolePermissionVO;
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
public interface RolePermissionMapper extends BaseMapper<RolePermission> {

    @Select(" select rp.id  , rp.role_id  as roleId, r.role, rp.permission_id  as permissionId, p.permission from role_permission rp left join role r on rp.role_id = r.id " +
            " left join permission p on rp.permission_id = p.id  ")
    List<RolePermissionVO> selectByPage(IPage page , RolePermissionVO rolePermissionVO );

}
