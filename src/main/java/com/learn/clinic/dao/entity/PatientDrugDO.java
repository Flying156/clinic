package com.learn.clinic.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.learn.clinic.dao.BaseDO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 开药实体类
 *
 * @author Milk
 * @version 2024/1/5 15:11
 */
@TableName("t_patient_drug")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PatientDrugDO extends BaseDO {

    /**
     * ID
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 病人 ID
     */
    private Integer patientId;

    /**
     * 药品管理
     */
    private Integer drugId;

    /**
     * 开药人 ID
     */
    private Integer userId;
}
