package com.learn.clinic.service;

import com.learn.clinic.dao.vo.OverStaticVO;
import com.learn.clinic.dao.vo.RecentStaticVO;

import java.util.List;

/**
 * 主页服务层
 *
 * @author Milk
 * @version 2024/1/5 16:59
 */
public interface HomeService {

    /**
     * 获取总体数据
     *
     * @return 数据
     */
    OverStaticVO getOverData();

    /**
     * 获取近几天的收入
     *
     * @return 数据
     */
    List<RecentStaticVO> getRecentData();
}
