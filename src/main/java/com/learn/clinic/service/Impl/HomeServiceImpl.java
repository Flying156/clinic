package com.learn.clinic.service.Impl;

import com.learn.clinic.dao.vo.OverStaticVO;
import com.learn.clinic.dao.vo.RecentStaticVO;
import com.learn.clinic.mapper.DrugMapper;
import com.learn.clinic.mapper.PatientDrugMapper;
import com.learn.clinic.mapper.PatientMapper;
import com.learn.clinic.service.HomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 主页服务实现层
 *
 * @author Milk
 * @version 2024/1/5 17:00
 */
@Service
@RequiredArgsConstructor
public class HomeServiceImpl implements HomeService {

    private final DrugMapper drugMapper;
    private final PatientDrugMapper patientDrugMapper;
    private final PatientMapper patientMapper;

    @Override
    public OverStaticVO getOverData() {
        Long patientCount = patientMapper.selectCount(null);
        Long drugCount = drugMapper.selectCount(null);
        Double revenue = patientDrugMapper.queryRevenue();

        return OverStaticVO.builder()
                .revenue(revenue)
                .drugCount(drugCount)
                .patientCount(patientCount)
                .build();
    }

    @Override
    public List<RecentStaticVO> getRecentData() {
        return patientDrugMapper.queryRecentData();
    }
}
