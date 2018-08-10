/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.shinnlove.presto.model;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 模型层的领域模型demo。
 *
 * 特别注意：在项目早期，没有分层那么明确的时候，领域模型和仓储模型可以是同一个。
 * 后期如果领域模型需要冗余一些字段或者是扩展一些二次计算字段，可以在仓储模型基础上扩展。
 *
 * PS：领域模型可以被前端序列化，所以实现`Serializable`，id如果是bigint，则java中对应long，即可修改。
 *
 * @author shinnlove.jinsheng
 * @version $Id: User.java, v 0.1 2018-08-10 下午2:52 shinnlove.jinsheng Exp $$
 */
public class User implements Serializable {

    /** uuid */
    private static final long serialVersionUID = 1910281847669392561L;

    /** id */
    private int               id;

    /** name */
    private String            name;

    /** pwd */
    private String            password;

    /** how old are ... */
    private int               age;

    /**
     * construct for ...
     */
    public User() {
    }

    /**
     *
     *
     * @param id
     * @param name
     * @param password
     * @param age
     */
    public User(int id, String name, String password, int age) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.age = age;
    }

    /**
     * Getter method for property id.
     *
     * @return property value of id
     */
    public int getId() {
        return id;
    }

    /**
     * Setter method for property id.
     *
     * @param id value to be assigned to property id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter method for property name.
     *
     * @return property value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter method for property name.
     *
     * @param name value to be assigned to property name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter method for property password.
     *
     * @return property value of password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setter method for property password.
     *
     * @param password value to be assigned to property password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Getter method for property age.
     *
     * @return property value of age
     */
    public int getAge() {
        return age;
    }

    /**
     * Setter method for property age.
     *
     * @param age value to be assigned to property age
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}