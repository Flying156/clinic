package com.learn.clinic.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.learn.clinic.dao.entity.PatientDrugDO;
import com.learn.clinic.dao.vo.RecentStaticVO;
import com.learn.clinic.dao.vo.RecordVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 开药记录持久层
 *
 * @author Milk
 * @version 2024/1/5 15:29
 */
public interface PatientDrugMapper extends BaseMapper<PatientDrugDO> {

    /**
     * 获取诊所买药所得
     *
     * @return  总金额
     */
    Double queryRevenue();

    /**
     * 查询每天的营业额
     *
     * @return 营业额
     */
    List<RecentStaticVO> queryRecentData();

    /**
     * 分页查询数据
     */
    List<RecordVO> queryList(@Param("patientName") String patientName, @Param("offset") long offset, @Param("size") long size);
}
