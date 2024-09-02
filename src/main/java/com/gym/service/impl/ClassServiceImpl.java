package com.gym.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.gym.mapper.ClassMapper;
import com.gym.pojo.ClassOrder;
import com.gym.pojo.ClassTable;
import com.gym.pojo.PageBean;
import com.gym.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassServiceImpl implements ClassService {

    @Autowired
    private ClassMapper classMapper;

    @Override
    public void addClass(ClassTable classTable) {
            String coachName = classMapper.findCoach(classTable.getCoachId());
            classTable.setCoachName(coachName);
            classMapper.addClass(classTable);
    }

    @Override
    public PageBean<ClassTable> list(Integer pageNum, Integer pageSize) {
        PageBean<ClassTable> pb = new PageBean<>();
        PageHelper.startPage(pageNum,pageSize);
        List<ClassTable> as = classMapper.list();
        Page<ClassTable> p = (Page<ClassTable>) as;
        pb.setTotal(p.getTotal());
        pb.setItems(p.getResult());
        return pb;
    }

    @Override
    public ClassTable findById(Integer id) {
        ClassTable classTable = classMapper.findById(id);
        return classTable;
    }

    @Override
    public void update(ClassTable classTable) {
        classMapper.update(classTable);
    }

    @Override
    public void delete(Integer id) {
        classMapper.delete(id);
    }

    @Override
    public void setCurrentMember(int i, Integer classId) {
        classMapper.setCurrentMember(i,classId);
    }

    @Override
    public List<ClassOrder> orderList(Integer id) {
        return classMapper.orderList(id);
    }


}
