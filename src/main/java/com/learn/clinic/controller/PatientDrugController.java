package com.learn.clinic.controller;

import com.learn.clinic.dao.dto.PageDTO;
import com.learn.clinic.dao.dto.PatientDrugDTO;
import com.learn.clinic.dao.vo.RecordVO;
import com.learn.clinic.service.PatientDrugService;
import com.learn.clinic.uitls.Result;
import com.learn.clinic.uitls.Results;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 开药管理层
 *
 * @author Milk
 * @version 2024/1/5 16:13
 */
@RestController
@RequiredArgsConstructor
public class PatientDrugController {

    private final PatientDrugService patientDrugService;

    @PutMapping("/saveRecord")
    public Result saveRecord(@RequestBody PatientDrugDTO patientDrugDTO){
        patientDrugService.saveRecord(patientDrugDTO);
        return Results.success();
    }

    @GetMapping("/getRecord")
    public Result<PageDTO<RecordVO>> getRecordList(@RequestParam(required = false) String patientName){
        return Results.success(patientDrugService.getRecordList(patientName));
    }

    @DeleteMapping("/deleteRecord")
    public Result deleteRecord(Integer id){
        patientDrugService.removeById(id);
        return Results.success();
    }



}
