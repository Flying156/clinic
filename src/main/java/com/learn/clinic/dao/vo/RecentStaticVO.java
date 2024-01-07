package com.learn.clinic.dao.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 近七天收入
 *
 * @author Milk
 * @version 2024/1/6 15:17
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecentStaticVO {

    /**
     * 收入
     */
    private Double revenue;

    /**
     * 日期
     */
    private Date date;

}
