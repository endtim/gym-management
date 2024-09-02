package com.gym.mapper;

import com.gym.pojo.Member;
import com.gym.pojo.MemberShip;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.time.LocalDate;

@Mapper
public interface MemberMapper {

    @Select("select * from member where member_account = #{memberAccount}")
    Member findByMemberAccount(Integer memberAccount);

    @Update("update member set member_name = #{memberName},member_weight = #{memberWeight},member_age = #{memberAge},member_phone = #{memberPhone},member_height = #{memberHeight}  where member_account = #{memberAccount}")
    void update(Member member);

    @Update("update member set member_password = #{newPwd} where member_account = #{account}")
    void updatePwd(String newPwd, Integer account);

    @Insert("insert into membership(member_account,card_type,start_date,end_date,status)" +
            " values(#{memberAccount},#{cardType},#{startDate},#{endDate},#{status})")
    void addMemberShip(MemberShip memberShip);

    @Select("select * from membership where member_account = #{memberAccount}")
    MemberShip findShipByMemberAccount(Integer account);

    @Update("update membership set end_date = #{endDate} where member_account = #{account}")
    void updateMembership(Integer account, LocalDate endDate);

    @Select("select end_date from membership where member_account = #{account}")
    String searchCard(Integer account);

    @Update("update member set points = #{i} where member_account = #{memberAccount}")
    void setPoints(int i, Integer memberAccount);
}
