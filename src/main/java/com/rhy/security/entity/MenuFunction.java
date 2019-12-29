package com.rhy.security.entity;

import java.util.List;

/**
 * @Auther: Herion_Rhy
 * @Description:
 * @Date: Created in 2019/12/29 16:06
 * @Modified By:
 * @Version: 1.0.0
 */
public class MenuFunction {
    private int id;
    private int menuId;
    private int functionId;
    private Menu menu;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public int getFunctionId() {
        return functionId;
    }

    public void setFunctionId(int functionId) {
        this.functionId = functionId;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    @Override
    public String toString() {
        return "MenuFunction{" +
                "id=" + id +
                ", menuId=" + menuId +
                ", functionId=" + functionId +
                ", menu=" + menu +
                '}';
    }
}
