package com.learn.clinic.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * 请求地址权限实体类
 *
 * @author Milk
 * @version 2023/12/27 9:28
 */
@Data
@Builder
@TableName("t_request_path_permission")
@AllArgsConstructor
public class RequestPathPermissionDO {

    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * Url ID
     */
    private String urlId;

    /**
     * 权限 ID
     */
    private Integer permissionId;

}
