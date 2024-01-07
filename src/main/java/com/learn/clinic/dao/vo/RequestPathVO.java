package com.learn.clinic.dao.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 请求路径展示
 *
 * @author Milk
 * @version 2024/1/7 15:21
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestPathVO {

    /**
     * id
     */
    private Integer key;

    /**
     * 描述
     */
    private String title;

}
