package com.gym.service.impl;

import com.gym.mapper.MemberMapper;
import com.gym.pojo.Member;
import com.gym.pojo.MemberShip;
import com.gym.service.MemberService;
import com.gym.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.Map;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberMapper memberMapper;

    @Override
    public Member findByMemberAccount(Integer memberAccount) {
        Member member = memberMapper.findByMemberAccount(memberAccount);
        return member;
    }

    @Override
    public void update(Member member) {
        memberMapper.update(member);
    }

    @Override
    public void updatePwd(String newPwd,Integer account) {
        memberMapper.updatePwd(newPwd,account);
    }

    @Override
    public void byMemberCard(Member member, String type) {
        Integer account = member.getMemberAccount();
        MemberShip memberShip = memberMapper.findShipByMemberAccount(account);
        if (memberShip != null){
            if((memberShip.getStartDate()!= null)&&memberShip.getStatus().equals("有效")){
                LocalDate startDate = memberShip.getEndDate();
                LocalDate endDate = null;
                switch (type) {
                    case "月卡":
                        endDate = startDate.plusMonths(1);
                        break;
                    case "季卡":
                        endDate = startDate.plusMonths(3);
                        break;
                    case "年卡":
                        endDate = startDate.plusYears(1);
                        break;
                }
                memberMapper.update(member);
                memberMapper.updateMembership(account,endDate);
            }
            else{
                LocalDate startDate = LocalDate.now();
                LocalDate endDate = null;
                switch (type) {
                    case "月卡":
                        endDate = startDate.plusMonths(1);
                        break;
                    case "季卡":
                        endDate = startDate.plusMonths(3);
                        break;
                    case "年卡":
                        endDate = startDate.plusYears(1);
                        break;
                }
                memberShip.setMemberAccount(account);
                memberShip.setCardType(type);
                memberShip.setStartDate(startDate);
                memberShip.setEndDate(endDate);
                memberShip.setStatus("有效");
                memberMapper.addMemberShip(memberShip);
            }
        }
        else{
            MemberShip memberShip1 = new MemberShip();
            LocalDate startDate = LocalDate.now();
            LocalDate endDate = null;
            switch (type) {
                case "月卡":
                    endDate = startDate.plusMonths(1);
                    break;
                case "季卡":
                    endDate = startDate.plusMonths(3);
                    break;
                case "年卡":
                    endDate = startDate.plusYears(1);
                    break;
            }
            memberShip1.setMemberAccount(account);
            memberShip1.setCardType(type);
            memberShip1.setStartDate(startDate);
            memberShip1.setEndDate(endDate);
            memberShip1.setStatus("有效");
            memberMapper.addMemberShip(memberShip1);
        }
    }

    @Override
    public String searchCard() {
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer account = (Integer) map.get("account");
        String msg = memberMapper.searchCard(account);
        return msg;
    }

    @Override
    public void setPoints(int i, Integer memberAccount) {
        memberMapper.setPoints(i,memberAccount);
    }
}
