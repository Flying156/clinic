package com.learn.clinic.controller;

import com.learn.clinic.dao.vo.OverStaticVO;
import com.learn.clinic.dao.vo.RecentStaticVO;
import com.learn.clinic.service.HomeService;
import com.learn.clinic.uitls.Result;
import com.learn.clinic.uitls.Results;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 主页表现层
 *
 * @author Milk
 * @version 2024/1/5 16:52
 */
@Tag(name = "首页管理")
@RestController
@RequiredArgsConstructor
public class HomeController {

    private final HomeService homeService;

    @Operation(summary = "获取各种物品数量")
    @GetMapping("/overStatic")
    private Result<OverStaticVO> getOverData(){
        return Results.success(homeService.getOverData());
    }

    @Operation(summary = "获取近7天的开药收入")
    @GetMapping("/recentStatic")
    private Result<List<RecentStaticVO>> getRecentStatic(){
        return Results.success(homeService.getRecentData());
    }

}
