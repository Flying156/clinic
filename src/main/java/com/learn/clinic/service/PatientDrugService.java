package com.learn.clinic.service;

import com.learn.clinic.dao.dto.PageDTO;
import com.learn.clinic.dao.dto.PatientDrugDTO;
import com.learn.clinic.dao.vo.RecordVO;

/**
 * 开药服务层
 *
 * @author Milk
 * @version 2024/1/5 16:13
 */
public interface PatientDrugService {

    /**
     * 保存开药记录
     *
     * @param patientDrugDTO 开药数据
     */
    void saveRecord(PatientDrugDTO patientDrugDTO);

    /**
     * 分页获取开药数据
     *
     * @param patientName 搜索数据
     * @return 分页数据
     */
    PageDTO<RecordVO> getRecordList(String patientName);

    /**
     * 移除记录
     */
    void removeById(Integer id);
}
