package com.gym.service;

import com.gym.pojo.ClassOrder;
import com.gym.pojo.ClassTable;
import com.gym.pojo.PageBean;

import java.util.List;

public interface ClassService {
    void addClass(ClassTable classTable);

    PageBean<ClassTable> list(Integer pageNum, Integer pageSize);

    ClassTable findById(Integer id);

    void update(ClassTable classTable);

    void delete(Integer id);

    void setCurrentMember(int i, Integer classId);

    List<ClassOrder> orderList(Integer id);
}
