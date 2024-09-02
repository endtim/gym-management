package com.gym.mapper;


import com.gym.pojo.Admin;
import com.gym.pojo.Member;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AdminMapper {

    @Select("select * from admin where admin_account = #{adminAccount}")
    Admin findByAdminAccount(Integer adminAccount);

    @Insert("insert into member(member_account,member_password,member_name,member_gender,member_age,member_height,member_weight,member_phone,card_time,points)" +
            " values (#{memberAccount},#{memberPassword},#{memberName},#{memberGender},#{memberAge},#{memberHeight},#{memberWeight},#{memberPhone},#{cardTime},#{points})")
    void insertMember(Member member);

    @Select("select * from member")
    List<Member> list();

    @Delete("delete from member where member_account = #{account}")
    void delete(Integer account);

    @Update("update member set member_name = #{memberName},member_weight = #{memberWeight},member_age = #{memberAge},member_phone = #{memberPhone},member_height = #{memberHeight} ,points = #{points} where member_account = #{memberAccount}")
    void updateMember(Member member);

    @Select("select end_date from membership where member_account = #{account}")
    String searchCard(Integer account);

    @Select("select * from member where member_account = #{id}")
    List<Member> orderList(Integer id);
}
