package com.rhy.security.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @Auther: Herion_Rhy
 * @Description:
 * @Date: Created in 2019/12/28 17:09
 * @Modified By:
 * @Version: 1.0.0
 */
@TableName("t_user_role")
public class UserRole {
    @TableId(value = "id",type = IdType.AUTO)
    private long id;
    @TableField("role_id")
    private long roleId;
    @TableField("user_id")
    private long userId;

    private Role role;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "id=" + id +
                ", roleId='" + roleId + '\'' +
                ", userId='" + userId + '\'' +
                ", role=" + role +
                '}';
    }
}
