package com.learn.clinic.dao.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 角色传输类
 *
 * @author Milk
 * @version 2024/1/7 19:54
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDTO {

    /**
     * id
     */
    private Integer id;

    /**
     * 角色名字
     */
    private String roleName;

    /**
     * 描述
     */
    private String description;

    /**
     * 权限列表
     */
    private List<Integer> keyList;

}
