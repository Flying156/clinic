package com.learn.clinic.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.learn.clinic.dao.dto.UserDTO;
import com.learn.clinic.dao.entity.UserDO;
import com.learn.clinic.dao.vo.UserVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户持久层
 *
 * @author Milk
 * @version 2023/12/27 9:37
 */
public interface UserMapper extends BaseMapper<UserDO> {


    /**
     * 获取用户信息
     */
    UserVO getUserVO(String username);

    /**
     * 分页查询数据
     */
    List<UserVO> queryList(@Param("username") String username, @Param("offset") long offset, @Param("size") long size);
}
