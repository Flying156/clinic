package com.learn.clinic.service;

import com.learn.clinic.dao.dto.PageDTO;
import com.learn.clinic.dao.entity.DrugDO;
import com.learn.clinic.expection.ServiceException;

import java.util.List;

/**
 * 药品服务层
 *
 * @author Milk
 * @version 2023/12/28 10:26
 */
public interface DrugService {

    /**
     * 查询所有药品
     *
     * @return 药品列表
     */
    PageDTO<DrugDO> queryAllDrug(String drugName);

    /**
     * 移除药品
     *
     * @param id ID
     */
    void removeDrug(Integer id);

    /**
     * 更新药品
     *
     * @param drugDO 数据
     */
    void updateDrug(DrugDO drugDO);

    /**
     * 添加药品
     *
     * @param drugDO 数据
     */
    void addDrug(DrugDO drugDO);

    /**
     * 更新药品数量
     *
     * @param idList 药品 ID 列表
     */
    void updateCount(List<Integer> idList) throws ServiceException;
}
