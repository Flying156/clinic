package com.learn.clinic.service.Impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.learn.clinic.dao.dto.PageDTO;
import com.learn.clinic.dao.dto.PatientDrugDTO;
import com.learn.clinic.dao.entity.PatientDO;
import com.learn.clinic.dao.entity.PatientDrugDO;
import com.learn.clinic.dao.entity.UserDO;
import com.learn.clinic.dao.vo.RecordVO;
import com.learn.clinic.dao.vo.UserVO;
import com.learn.clinic.mapper.PatientDrugMapper;
import com.learn.clinic.mapper.PatientMapper;
import com.learn.clinic.mapper.UserMapper;
import com.learn.clinic.service.PatientDrugService;
import com.learn.clinic.uitls.PageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 开药服务层实现层
 *
 * @author Milk
 * @version 2024/1/5 16:14
 */
@Service
@RequiredArgsConstructor
public class PatientDrugServiceImpl implements PatientDrugService {

    private final PatientDrugMapper patientDrugMapper;
    private final PatientMapper patientMapper;
    private final UserMapper userMapper;

    @Override
    public void saveRecord(PatientDrugDTO patientDrugDTO) {
        Integer patientId = patientDrugDTO.getPatientID();
        LambdaQueryWrapper<UserDO> queryWrapper = Wrappers.lambdaQuery(UserDO.class)
                .eq(StrUtil.isNotEmpty(patientDrugDTO.getUsername()), UserDO::getUsername, patientDrugDTO.getUsername());
        UserDO userDO = userMapper.selectOne(queryWrapper);

        patientDrugDTO.getDrugIDList().forEach(drugID ->{
            PatientDrugDO patientDrugDO = PatientDrugDO.builder()
                    .patientId(patientId)
                    .userId(userDO.getId())
                    .drugId(drugID)
                    .build();

            patientDrugMapper.insert(patientDrugDO);
        });
    }

    @Override
    public PageDTO<RecordVO> getRecordList(String patientName) {
        Long count = 0L;
        if(StrUtil.isNotEmpty(patientName)) {
            LambdaQueryWrapper<PatientDO> queryWrapper = Wrappers.lambdaQuery(PatientDO.class)
                    .like(StrUtil.isNotEmpty(patientName), PatientDO::getName, patientName);

            PatientDO patientDO = patientMapper.selectOne(queryWrapper);
            if(patientDO != null) {

                LambdaQueryWrapper<PatientDrugDO> patientDrugLambdaQueryWrapper = Wrappers.lambdaQuery(PatientDrugDO.class)
                        .eq(PatientDrugDO::getPatientId, patientDO.getId());
                count = patientDrugMapper.selectCount(patientDrugLambdaQueryWrapper);
            }
        }else{
            count = patientDrugMapper.selectCount(null);
        }
        // 分页查询数据
        long offset = PageUtils.offset(), size = PageUtils.size();
        List<RecordVO> recordVOList = patientDrugMapper.queryList(patientName, offset, size);
        return PageUtils.build(recordVOList , count);
    }

    @Override
    public void removeById(Integer id) {
        patientDrugMapper.deleteById(id);
    }
}
