package com.gym.mapper;


import com.gym.pojo.ClassOrder;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ClassOrderMapper {

    @Select("select * from class_order where class_id = #{id} and member_account = #{memberAccount}")
    ClassOrder findByIdAndMemberAccount(Integer id, Integer memberAccount);

    @Select("select class_id from class_order where class_order_id = #{id}")
    Integer findById(Integer id);

    @Delete("delete from class_order where class_order_id = #{id}")
    void delete(Integer id);

    @Select("select * from class_order")
    List<ClassOrder> list();

    @Select("select * from class_order where member_account = #{account}")
    List<ClassOrder> myList(Integer account);

    @Insert("insert into class_order(class_id,class_name,coach_id,coach_name,member_name,member_account,class_begin)" +
            " values (#{classId},#{className},#{coachId},#{coachName},#{memberName},#{memberAccount},#{classBegin})")
    void signUp(ClassOrder classOrder);
}
