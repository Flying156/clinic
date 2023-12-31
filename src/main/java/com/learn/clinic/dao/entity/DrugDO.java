package com.learn.clinic.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.learn.clinic.dao.BaseDO;
import lombok.*;
import org.jetbrains.annotations.NotNull;

import java.util.Date;

/**
 * 药品实体类
 *
 * @author Milk
 * @version 2023/12/26 21:55
 */
@Data
@Builder
@TableName("t_drug")
@AllArgsConstructor
@NoArgsConstructor
public class DrugDO extends BaseDO {

    /**
     * ID
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 药品名称
     */
    @NotNull
    private String name;

    /**
     * 药效
     */
    @NotNull
    private String effect;

    /**
     * 药品数量
     */
    @NotNull
    private Integer quantity;

    /**
     * 药品价格
     */
    @NotNull
    private Double price;

    /**
     * 制造商
     */
    @NotNull
    private String manufacturer;

    /**
     * 生产日期
     */
    @NotNull
    private Date produceTime;

    /**
     * 过期日期
     */
    @NotNull
    private Date expireTime;

}
