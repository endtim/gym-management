package com.gym.service;

import com.gym.pojo.Admin;
import com.gym.pojo.Member;
import com.gym.pojo.PageBean;

import java.util.List;

public interface AdminService {

    Admin findByAdminAccount(Integer adminAccount);

    void insertMember(Member member);

    PageBean<Member> list(Integer pageNum, Integer pageSize);

    void delete(Integer account);

    void update(Member member);

    String searchCard(Integer account);

    List<Member> orderList(Integer id);
}
