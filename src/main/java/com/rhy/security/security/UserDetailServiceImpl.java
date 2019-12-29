package com.rhy.security.security;

import com.rhy.security.entity.Role;
import com.rhy.security.entity.User;
import com.rhy.security.entity.UserRole;
import com.rhy.security.mapper.IRoleMapper;
import com.rhy.security.mapper.IUserMapper;
import com.rhy.security.mapper.IUserRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: Herion_Rhy
 * @Description: 用户信息实现类
 * @Date: Created in 2019/12/28 16:52
 * @Modified By:
 * @Version: 1.0.0
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    IUserMapper iUserMapper;
    @Autowired
    IRoleMapper iRoleMapper;
    @Autowired
    IUserRoleMapper iUserRoleMapper;
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        //搜索数据库用户信息
        User user = iUserMapper.selectByUserName(userName);
        //搜索权限信息
        if(user == null){
            throw new UsernameNotFoundException("UserName not found");
        }
        return this.changeToUserDetail(user);
    }

    private UserDetails changeToUserDetail(User user){
        //权限列表
        List<GrantedAuthority> authorities = new ArrayList<>();
        //赋予查询到的角色
        for(UserRole userRole : user.getUserRoles()){
            GrantedAuthority authority = new SimpleGrantedAuthority(userRole.getRole().getRoleName());
            authorities.add(authority);
        }
        //创建UserDetails对象，设置用户名、密码、权限
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getUserName(),user.getPwd(),authorities);
        return userDetails;

    }
}
