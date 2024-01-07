package com.learn.clinic.dao.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 开药传输类
 *
 * @author Milk
 * @version 2024/1/5 16:00
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PatientDrugDTO {

    /**
     * 病人 ID
     */
    private Integer patientID;

    /**
     * 开药用户
     */
    private String username;


    /**
     * 药品 ID 列表
     */
    private List<Integer> drugIDList;
}
