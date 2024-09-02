package com.gym.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.gym.mapper.AdminMapper;
import com.gym.pojo.Admin;
import com.gym.pojo.Equipment;
import com.gym.pojo.Member;
import com.gym.pojo.PageBean;
import com.gym.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public Admin findByAdminAccount(Integer adminAccount) {
        Admin admin = adminMapper.findByAdminAccount(adminAccount);
        return admin;
    }

    @Override
    public void insertMember(Member member) {
        adminMapper.insertMember(member);
    }

    @Override
    public PageBean<Member> list(Integer pageNum, Integer pageSize) {
        PageBean<Member> pb = new PageBean<>();
        PageHelper.startPage(pageNum,pageSize);
        List<Member> as = adminMapper.list();
        Page<Member> p = (Page<Member>) as;
        pb.setTotal(p.getTotal());
        pb.setItems(p.getResult());
        return pb;
    }

    @Override
    public void delete(Integer account) {
        adminMapper.delete(account);
    }

    @Override
    public void update(Member member) {
        adminMapper.updateMember(member);
    }

    @Override
    public String searchCard(Integer account) {
        return adminMapper.searchCard(account);
    }

    @Override
    public List<Member> orderList(Integer id) {
        return adminMapper.orderList(id);
    }
}
