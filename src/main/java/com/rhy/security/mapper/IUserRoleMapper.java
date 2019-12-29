package com.rhy.security.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rhy.security.entity.User;
import com.rhy.security.entity.UserRole;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Auther: Herion_Rhy
 * @Description:
 * @Date: Created in 2019/12/28 17:17
 * @Modified By:
 * @Version: 1.0.0
 */
@Mapper
@Component
public interface IUserRoleMapper extends BaseMapper<UserRole> {
    List<UserRole> selectByIds(UserRole userRole);
}
