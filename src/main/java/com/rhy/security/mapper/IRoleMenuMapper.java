package com.rhy.security.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rhy.security.entity.Menu;
import com.rhy.security.entity.RoleMenu;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Auther: Herion_Rhy
 * @Description:
 * @Date: Created in 2019/12/29 16:03
 * @Modified By:
 * @Version: 1.0.0
 */
@Mapper
@Component
public interface IRoleMenuMapper extends BaseMapper<RoleMenu> {
    List<RoleMenu> selectByRoleMenu(RoleMenu roleMenu);
}
