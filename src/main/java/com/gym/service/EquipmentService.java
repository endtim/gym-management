package com.gym.service;

import com.gym.pojo.Equipment;
import com.gym.pojo.PageBean;

public interface EquipmentService {
    void add(Equipment equipment);

    PageBean<Equipment> list(Integer pageNum, Integer pageSize, String status);

    void update(Equipment equipment);

    Equipment findById(Integer id);

    void delete(Integer id);
}
