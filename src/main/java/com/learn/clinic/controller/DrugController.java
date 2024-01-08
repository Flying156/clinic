package com.learn.clinic.controller;

import com.learn.clinic.dao.dto.PageDTO;
import com.learn.clinic.dao.entity.DrugDO;
import com.learn.clinic.expection.ServiceException;
import com.learn.clinic.service.DrugService;
import com.learn.clinic.uitls.Result;
import com.learn.clinic.uitls.Results;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 药品表现层
 *
 * @author Milk
 * @version 2023/12/28 10:28
 */
@Tag(name="药品管理")
@RestController
@RequiredArgsConstructor
public class DrugController {

    private final DrugService drugService;

    @Operation(summary = "分页查询药品")
    @GetMapping("/getAllDrug")
    public Result<PageDTO<DrugDO>> getDrug(@RequestParam(required = false) String drugName){
        return Results.success(drugService.queryAllDrug(drugName));
    }

    @Operation(summary = "更新药品数量")
    @PostMapping("/updateCount")
    public Result updateCount(@RequestBody List<Integer> idList) throws ServiceException {
        drugService.updateCount(idList);
        return Results.success();
    }

    @Operation(summary = "删除药品")
    @DeleteMapping("/deleteById")
    public Result<Void> removeDrug(@RequestParam Integer id){
        drugService.removeDrug(id);
        return Results.success();
    }

    @Operation(summary = "更新药品信息")
    @PostMapping("/updateDrug")
    public Result<Void> updateDrug(@RequestBody DrugDO drugDO){
        drugService.updateDrug(drugDO);
        return Results.success();
    }

    @Operation(summary = "新增药品")
    @PutMapping("/addDrug")
    public Result<Void> addDrug(@RequestBody DrugDO drugDO){
        drugService.addDrug(drugDO);
        return Results.success();
    }
}
