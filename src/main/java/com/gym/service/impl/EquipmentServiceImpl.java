package com.gym.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.gym.mapper.EquipmentMapper;
import com.gym.pojo.Equipment;
import com.gym.pojo.PageBean;
import com.gym.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipmentServiceImpl implements EquipmentService {

    @Autowired
    private EquipmentMapper equipmentMapper;

    @Override
    public void add(Equipment equipment) {
        equipmentMapper.add(equipment);
    }

    @Override
    public PageBean<Equipment> list(Integer pageNum, Integer pageSize, String status) {
        PageBean<Equipment> pb = new PageBean<>();
        PageHelper.startPage(pageNum,pageSize);
        List<Equipment> as = equipmentMapper.list(status);
        Page<Equipment> p = (Page<Equipment>) as;
        pb.setTotal(p.getTotal());
        pb.setItems(p.getResult());
        return pb;
    }

    @Override
    public void update(Equipment equipment) {
        equipmentMapper.update(equipment);
    }

    @Override
    public Equipment findById(Integer id) {
        Equipment equipment = equipmentMapper.findById(id);
        return equipment;
    }

    @Override
    public void delete(Integer id) {
        equipmentMapper.delete(id);
    }
}
