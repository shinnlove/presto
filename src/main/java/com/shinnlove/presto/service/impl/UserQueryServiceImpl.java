/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.shinnlove.presto.service.impl;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.shinnlove.presto.model.User;
import com.shinnlove.presto.service.UserQueryService;
import com.shinnlove.presto.service.aspect.ServiceExecuteAspect;

/**
 * Service层具体实现。
 *
 * 项目早期，service层负责接受controller的调用，将仓储层查询到的数据处理、传递出去。
 * 项目后期，service可以拆分粒度更细，数据库读写服务也可以下沉到DAO层，视实际情况和成本决定，切莫人云亦云。
 *
 * Spring发展到4.x还是有很多人喜欢配置xml，2.x的时候就有人喜欢全注解，3.x回到xml，4.x又开始流行注解了。
 * 这都无所谓，一个bean而已，`@Service`怎么了，永远记住企业是结果导向的。
 *
 * PS：password这个字段在数据库重要md5加密，这里随便举了一个实体查询作为demo而已。
 * 不要问怎么找不到try...catch，@see {@link ServiceExecuteAspect}。
 *
 * @author shinnlove.jinsheng
 * @version $Id: UserQueryServiceImpl.java, v 0.1 2018-08-10 下午3:14 shinnlove.jinsheng Exp $$
 */
public class UserQueryServiceImpl implements UserQueryService {

    /** spring-jdbc集成(bean配置的bean不能`@Autowired`注入) */
    private JdbcTemplate jdbcTemplate;

    /**
     * @see com.shinnlove.presto.service.UserQueryService#countUser()
     */
    @Override
    public int countUser() {
        String sql = "select count(*) from user";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    /**
     * @see com.shinnlove.presto.service.UserQueryService#addUser(User) 
     */
    @Override
    public int addUser(User user) {
        String sql = "insert into user (name, password, age) values (?, ?, ?)";
        return jdbcTemplate.update(sql, user.getName(), user.getPassword(), user.getAge());
    }

    /**
     * @see com.shinnlove.presto.service.UserQueryService#deleteUser(int)
     */
    @Override
    public int deleteUser(int id) {
        String sql = "delete from user where id = ?";
        return jdbcTemplate.update(sql, id);
    }

    /**
     * @see com.shinnlove.presto.service.UserQueryService#getAllUser()
     */
    @Override
    public List<User> getAllUser() {
        String sql = "select id, name, password, age from user";
        RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);
        return jdbcTemplate.query(sql, rowMapper);
    }

    /**
     * @see com.shinnlove.presto.service.UserQueryService#getUserById(int) 
     */
    @Override
    public User getUserById(int id) {
        // 这种sql以后下沉到仓储层去，让service清爽一点
        String sql = "select id, name, password, age from user where id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[] { id },
            (rs, rowNum) -> new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4)));
    }

    /**
     * @see com.shinnlove.presto.service.UserQueryService#getUserByName(String) 
     */
    @Override
    public User getUserByName(String name) {
        String sql = "select id, name, password, age from user where name = ?";
        return jdbcTemplate.queryForObject(sql, new Object[] { name },
            (rs, rowNum) -> new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4)));
    }

    /**
     * @see com.shinnlove.presto.service.UserQueryService#getUserListByName(String) 
     */
    @Override
    public List<User> getUserListByName(String name) {
        String sql = "select id, name, password, age from user where name = ?";
        return jdbcTemplate.query(sql, new Object[] { name }, (rs, rowNum) -> new User(
            rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4)));
    }

    /**
     * Setter method for property jdbcTemplate.
     *
     * @param jdbcTemplate value to be assigned to property jdbcTemplate
     */
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

}