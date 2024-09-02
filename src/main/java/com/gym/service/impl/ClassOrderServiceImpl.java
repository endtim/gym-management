package com.gym.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.gym.mapper.ClassOrderMapper;
import com.gym.pojo.ClassOrder;
import com.gym.pojo.ClassTable;
import com.gym.pojo.PageBean;
import com.gym.pojo.Result;
import com.gym.service.ClassOrderService;
import com.gym.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ClassOrderServiceImpl implements ClassOrderService {

    @Autowired
    private ClassOrderMapper classOrderMapper;

    @Override
    public ClassOrder findByIdAndMemberAccount(Integer id, Integer memberAccount) {
        ClassOrder classOrder = classOrderMapper.findByIdAndMemberAccount(id,memberAccount);
        return classOrder;
    }

    @Override
    public Integer findClassId(Integer id) {
        return classOrderMapper.findById(id);
    }

    @Override
    public void delete(Integer id) {
        classOrderMapper.delete(id);
    }

    @Override
    public PageBean<ClassOrder> list(Integer pageNum, Integer pageSize) {
        PageBean<ClassOrder> pb = new PageBean<>();
        PageHelper.startPage(pageNum,pageSize);
        List<ClassOrder> as = classOrderMapper.list();
        Page<ClassOrder> p = (Page<ClassOrder>) as;
        pb.setTotal(p.getTotal());
        pb.setItems(p.getResult());
        return pb;
    }

    @Override
    public PageBean<ClassOrder> myList(Integer pageNum, Integer pageSize) {
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer account = (Integer) map.get("account");
        PageBean<ClassOrder> pb = new PageBean<>();
        PageHelper.startPage(pageNum,pageSize);
        List<ClassOrder> as = classOrderMapper.myList(account);
        Page<ClassOrder> p = (Page<ClassOrder>) as;
        pb.setTotal(p.getTotal());
        pb.setItems(p.getResult());
        return pb;
    }

    @Override
    public void signUp(ClassOrder classOrder) {
        classOrderMapper.signUp(classOrder);
    }
}
