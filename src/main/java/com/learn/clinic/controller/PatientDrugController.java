package com.learn.clinic.controller;

import com.learn.clinic.dao.dto.PageDTO;
import com.learn.clinic.dao.dto.PatientDrugDTO;
import com.learn.clinic.dao.vo.RecordVO;
import com.learn.clinic.service.PatientDrugService;
import com.learn.clinic.uitls.Result;
import com.learn.clinic.uitls.Results;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 开药管理层
 *
 * @author Milk
 * @version 2024/1/5 16:13
 */
@Tag(name = "开药记录管理")
@RestController
@RequiredArgsConstructor
public class PatientDrugController {

    private final PatientDrugService patientDrugService;

    @Operation(summary = "保存开药记录")
    @PutMapping("/saveRecord")
    public Result saveRecord(@RequestBody PatientDrugDTO patientDrugDTO){
        patientDrugService.saveRecord(patientDrugDTO);
        return Results.success();
    }

    @Operation(summary = "分页查询开药记录")
    @GetMapping("/getRecord")
    public Result<PageDTO<RecordVO>> getRecordList(@RequestParam(required = false) String patientName){
        return Results.success(patientDrugService.getRecordList(patientName));
    }

    @Operation(summary = "删除药品")
    @DeleteMapping("/deleteRecord")
    public Result deleteRecord(Integer id){
        patientDrugService.removeById(id);
        return Results.success();
    }



}
