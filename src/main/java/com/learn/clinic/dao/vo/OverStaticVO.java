package com.learn.clinic.dao.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 数据展示类
 *
 * @author Milk
 * @version 2024/1/5 16:56
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OverStaticVO {


    /**
     * 医生数量
     */
    private Long doctorCount;

    /**
     * 药品数量
     */
    private Long drugCount;

    /**
     * 病人数量
     */
    private Long patientCount;

    /**
     * 开药收入
     */
    private Double revenue;

}
