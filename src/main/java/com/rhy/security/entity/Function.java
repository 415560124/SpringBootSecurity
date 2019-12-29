package com.rhy.security.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.util.List;

/**
 * @Auther: Herion_Rhy
 * @Description:
 * @Date: Created in 2019/12/29 16:04
 * @Modified By:
 * @Version: 1.0.0
 */
@TableName("t_function")
public class Function {
    private int id;
    private String title;
    private String path;
    private List<MenuFunction> menuFunctionList;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public List<MenuFunction> getMenuFunctionList() {
        return menuFunctionList;
    }

    public void setMenuFunctionList(List<MenuFunction> menuFunctionList) {
        this.menuFunctionList = menuFunctionList;
    }

    @Override
    public String toString() {
        return "Function{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", path='" + path + '\'' +
                ", menuFunctionList=" + menuFunctionList +
                '}';
    }
}
