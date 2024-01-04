package com.learn.clinic.service.Impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.learn.clinic.dao.dto.PageDTO;
import com.learn.clinic.dao.entity.PatientDO;
import com.learn.clinic.mapper.PatientMapper;
import com.learn.clinic.service.PatientService;
import com.learn.clinic.uitls.PageUtils;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;


/**
 * 病人服务实现
 *
 * @author Milk
 * @version 2024/1/3 16:01
 */
@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {

    private final PatientMapper patientMapper;

    @Override
    public PageDTO<PatientDO> getAllPatient(String name) {
        long offset = PageUtils.offset(), size = PageUtils.size();
        LambdaQueryWrapper<PatientDO> queryWrapper = Wrappers.lambdaQuery(PatientDO.class)
                .like(StrUtil.isNotBlank(name), PatientDO::getName, name);
        // 分页查询
        Page<PatientDO>  patientDOPage = new Page<>(offset, size);
        IPage<PatientDO> page = patientMapper.selectPage(patientDOPage, queryWrapper);
        return PageUtils.build(page.getRecords(), page.getTotal());
    }

    @Override
    public void remove(Integer id) {
        patientMapper.deleteById(id);
    }

    @Override
    public void update(@NotNull PatientDO patientDO) {
        LambdaUpdateWrapper<PatientDO> updateWrapper = Wrappers.lambdaUpdate(PatientDO.class)
                .eq(PatientDO::getId, patientDO.getId());
        patientMapper.update(patientDO, updateWrapper);
    }

    @Override
    public void addPatient(PatientDO patientDO) {
        patientMapper.insert(patientDO);
    }
}
