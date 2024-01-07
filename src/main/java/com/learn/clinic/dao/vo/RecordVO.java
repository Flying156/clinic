package com.learn.clinic.dao.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 开药记录展示类
 *
 * @author Milk
 * @version 2024/1/6 19:32
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecordVO {

    /**
     * id
     */
    private Integer id;

    /**
     * 病人名称
     */
    private String  patientName;

    /**
     * 医生名称
     */
    private String doctorName;

    /**
     * 药品名称
     */
    private String drugName;

    /**
     * 金额
     */
    private Double price;

    /**
     * 开药时间
     */
    private Date createTime;
}
