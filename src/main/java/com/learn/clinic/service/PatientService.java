package com.learn.clinic.service;

import com.learn.clinic.dao.dto.PageDTO;
import com.learn.clinic.dao.entity.PatientDO;

/**
 * 病人服务层
 *
 * @author Milk
 * @version 2024/1/3 16:01
 */
public interface PatientService {

    /**
     * 分页查询病人
     *
     * @param name 姓名
     * @return     查询的数据
     */
    PageDTO<PatientDO> getAllPatient(String name);

    /**
     * 删除病人
     *
     * @param id 病人 ID
     */
    void remove(Integer id);

    /**
     * 更新病人
     *
     * @param patientDO 病人
     */
    void update(PatientDO patientDO);

    /**
     * 新增病人
     *
     * @param patientDO 病人
     */
    void addPatient(PatientDO patientDO);
}
