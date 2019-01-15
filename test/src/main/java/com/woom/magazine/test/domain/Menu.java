/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.woom.magazine.test.domain;

/**
 * 菜单
 *
 * @author yuhao.zx
 * @version $Id: Menu.java, v 0.1 2018年12月25日 3:08 PM yuhao.zx Exp $
 */
public class Menu {

    //卡路里
    private int    calorie;

    //名称
    private String name;

    //构造器
    public Menu(int calorie, String name) {
        this.calorie = calorie;
        this.name = name;
    }

    /**
     * Getter method for property <tt>calorie</tt>.
     *
     * @return property value of calorie
     */
    public int getCalorie() {
        return calorie;
    }

    /**
     * Setter method for property <tt>calorie</tt>.
     *
     * @param calorie  value to be assigned to property calorie
     */
    public void setCalorie(int calorie) {
        this.calorie = calorie;
    }

    /**
     * Getter method for property <tt>name</tt>.
     *
     * @return property value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter method for property <tt>name</tt>.
     *
     * @param name  value to be assigned to property name
     */
    public void setName(String name) {
        this.name = name;
    }
}