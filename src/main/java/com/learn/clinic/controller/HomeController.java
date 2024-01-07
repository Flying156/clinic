package com.learn.clinic.controller;

import com.learn.clinic.dao.vo.OverStaticVO;
import com.learn.clinic.dao.vo.RecentStaticVO;
import com.learn.clinic.service.HomeService;
import com.learn.clinic.uitls.Result;
import com.learn.clinic.uitls.Results;
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
@RestController
@RequiredArgsConstructor
public class HomeController {

    private final HomeService homeService;


    @GetMapping("/overStatic")
    private Result<OverStaticVO> getOverData(){
        return Results.success(homeService.getOverData());
    }

    @GetMapping("/recentStatic")
    private Result<List<RecentStaticVO>> getRecentStatic(){
        return Results.success(homeService.getRecentData());
    }

}
