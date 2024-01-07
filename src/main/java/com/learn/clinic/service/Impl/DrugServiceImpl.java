package com.learn.clinic.service.Impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.learn.clinic.dao.dto.PageDTO;
import com.learn.clinic.dao.entity.DrugDO;
import com.learn.clinic.expection.ServiceException;
import com.learn.clinic.mapper.DrugMapper;
import com.learn.clinic.service.DrugService;
import com.learn.clinic.uitls.PageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * 药品服务层实现
 *
 * @author Milk
 * @version 2023/12/28 10:27
 */
@Service
@RequiredArgsConstructor
public class DrugServiceImpl implements DrugService {

    private final DrugMapper drugMapper;

    @Override
    public PageDTO<DrugDO> queryAllDrug(String drugName) {
        long offset = PageUtils.offset(), size = PageUtils.size();
        LambdaQueryWrapper<DrugDO> queryWrapper = Wrappers.lambdaQuery(DrugDO.class)
                .like(StrUtil.isNotBlank(drugName), DrugDO::getName, drugName);
        Page<DrugDO> drugPage = new Page<>(offset, size);
        IPage<DrugDO> drugIpage = drugMapper.selectPage(drugPage, queryWrapper);

        return PageUtils.build(drugIpage);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removeDrug(Integer id) {
        LambdaQueryWrapper<DrugDO> queryWrapper = Wrappers.lambdaQuery(DrugDO.class)
                        .eq(DrugDO::getId, id);
        drugMapper.delete(queryWrapper);
    }

    @Override
    public void updateDrug(DrugDO drugDO) {
        LambdaUpdateWrapper<DrugDO> updateWrapper = Wrappers.lambdaUpdate(DrugDO.class)
                .eq(DrugDO::getId, drugDO.getId());
        drugMapper.update(drugDO, updateWrapper);
    }

    @Override
    public void addDrug(DrugDO drugDO) {
        drugMapper.insert(drugDO);
    }

    @Override
    public void updateCount(List<Integer> idList) throws ServiceException {
        if(CollUtil.isEmpty(idList)){
            throw new ServiceException("未勾选任何值！");
        }
        LambdaUpdateWrapper<DrugDO> updateWrapper =  Wrappers.lambdaUpdate(DrugDO.class)
                .in(DrugDO::getId, idList)
                .setSql("quantity = quantity - 1");
        drugMapper.update(null, updateWrapper);
    }
}
