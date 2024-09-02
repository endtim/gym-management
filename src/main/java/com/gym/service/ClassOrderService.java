package com.gym.service;

import com.gym.pojo.ClassOrder;
import com.gym.pojo.PageBean;

public interface ClassOrderService {
    ClassOrder findByIdAndMemberAccount(Integer id, Integer memberAccount);

    Integer findClassId(Integer id);

    void delete(Integer id);

    PageBean<ClassOrder> list(Integer pageNum, Integer pageSize);

    PageBean<ClassOrder> myList(Integer pageNum, Integer pageSize);

    void signUp(ClassOrder classOrder);
}
