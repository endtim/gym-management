package com.gym.service;

import com.gym.pojo.Member;

public interface MemberService {

    Member findByMemberAccount(Integer memberAccount);

    void update(Member member);

    void updatePwd(String newPwd,Integer account);

    void byMemberCard(Member member, String type);

    String searchCard();

    void setPoints(int i, Integer memberAccount);
}
