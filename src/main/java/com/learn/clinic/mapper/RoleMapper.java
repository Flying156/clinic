package com.learn.clinic.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.learn.clinic.dao.entity.RoleDO;
import org.apache.ibatis.annotations.Select;

/**
 * @author Milk
 * @version 2024/1/3 10:53
 */
public interface RoleMapper extends BaseMapper<RoleDO> {

    @Select("select count(1) from t_role where role_name='doctor'")
    Long queryCount();
}
