package com.learn.clinic.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


/**
 * 请求路径
 *
 * @author Milk
 * @version 2023/12/27 9:15
 */

@Data
@Builder
@TableName("t_request_path")
@AllArgsConstructor
public class RequestPathDO {

    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 接口
     */
    private String url;

    /**
     * 描述
     */
    private String description;

}
