package com.learn.clinic.controller;

import com.learn.clinic.dao.dto.PageDTO;
import com.learn.clinic.dao.entity.PatientDO;
import com.learn.clinic.service.PatientService;
import com.learn.clinic.uitls.Result;
import com.learn.clinic.uitls.Results;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


/**
 * 病人表现层
 *
 * @author Milk
 * @version 2024/1/3 16:02
 */
@Tag(name = "药品管理")
@RestController
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    @Operation(summary = "分页查询病人")
    @GetMapping("/getAllPatient")
    public Result<PageDTO<PatientDO>> getAllPatient(@RequestParam(required = false) String name){
        return Results.success(patientService.getAllPatient(name));
    }

    @Operation(summary = "删除病人")
    @DeleteMapping("/deletePatient")
    public Result remove(@RequestParam Integer id){
        patientService.remove(id);
        return Results.success();
    }

    @Operation(summary = "更新病人信息")
    @PostMapping("/updatePatient")
    public Result update(@RequestBody PatientDO patientDO){
        patientService.update(patientDO);
        return Results.success();
    }

    @Operation(summary = "添加病人")
    @PutMapping("/addPatient")
    public Result addPatient(@RequestBody PatientDO patientDO){
        patientService.addPatient(patientDO);
        return Results.success();
    }

}
