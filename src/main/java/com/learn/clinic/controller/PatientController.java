package com.learn.clinic.controller;

import com.learn.clinic.dao.dto.PageDTO;
import com.learn.clinic.dao.entity.PatientDO;
import com.learn.clinic.service.PatientService;
import com.learn.clinic.uitls.Result;
import com.learn.clinic.uitls.Results;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


/**
 * 病人表现层
 *
 * @author Milk
 * @version 2024/1/3 16:02
 */
@RestController
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    @GetMapping("/getAllPatient")
    public Result<PageDTO<PatientDO>> getAllPatient(@RequestParam(required = false) String name){
        return Results.success(patientService.getAllPatient(name));
    }

    @DeleteMapping("/deletePatient")
    public Result remove(@RequestParam Integer id){
        patientService.remove(id);
        return Results.success();
    }

    @PostMapping("/updatePatient")
    public Result update(@RequestBody PatientDO patientDO){
        patientService.update(patientDO);
        return Results.success();
    }

    @PutMapping("/addPatient")
    public Result addPatient(@RequestBody PatientDO patientDO){
        patientService.addPatient(patientDO);
        return Results.success();
    }

}
