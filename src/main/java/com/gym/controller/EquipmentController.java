package com.gym.controller;


import com.gym.pojo.*;
import com.gym.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/equipment")
@Validated
public class EquipmentController {

    @Autowired
    private EquipmentService equipmentService;

    @PostMapping
    public Result add(@RequestBody @Validated(Equipment.Add.class) Equipment equipment){
        equipmentService.add(equipment);
        return Result.success();
    }

    @GetMapping
    public Result<PageBean<Equipment>> list(Integer pageNum, Integer pageSize, @RequestParam(required = false) String status){

        PageBean<Equipment> pb =  equipmentService.list(pageNum,pageSize,status);
        return Result.success(pb);

    }

    @PutMapping
    public Result update(@RequestBody @Validated(Equipment.Update.class) Equipment equipment){

        equipmentService.update(equipment);
        return Result.success();
    }

    @GetMapping("/detail")
    public Result<Equipment> detail(Integer id){

        Equipment equipment = equipmentService.findById(id);
        return Result.success(equipment);
    }

    @DeleteMapping
    public Result delete(Integer id){

        equipmentService.delete(id);
        return Result.success();
    }

}
