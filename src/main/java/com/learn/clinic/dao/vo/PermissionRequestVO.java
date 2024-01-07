package com.learn.clinic.dao.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 权限路径类
 *
 * @author Milk
 * @version 2024/1/7 15:20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PermissionRequestVO {

    /**
     * 权限 id
     */
    private Integer key;

    /**
     * 权限名称
     */
    private String title;

    /**
     * 可以访问的路径
     */
    private List<RequestPathVO> children;

}
