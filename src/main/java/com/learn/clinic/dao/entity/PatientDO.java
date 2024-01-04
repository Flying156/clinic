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
 * 病人实体类
 *
 * @author Milk
 * @version 2024/1/3 15:52
 */
@Data
@TableName("t_patient")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PatientDO extends BaseDO {

    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 病人姓名
     */
    private String name;

    /**
     * 病人年龄
     */
    private Integer age;

    /**
     * 病人性别
     */
    private String gender;

    /**
     * 疾病，症状
     */
    private String disease;

}
