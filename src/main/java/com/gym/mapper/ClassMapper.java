package com.gym.mapper;


import com.gym.pojo.ClassOrder;
import com.gym.pojo.ClassTable;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ClassMapper {

    @Insert("insert into class_table(class_name,class_begin,class_time,coach_id,coach_name,class_points,class_max_member,class_member)" +
            " values(#{className},#{classBegin},#{classTime},#{coachId},#{coachName},#{classPoints},#{classMaxMember},0)")
    void addClass(ClassTable classTable);

    @Select("select employee_name from employee where employee_account = #{coachId}")
    String findCoach(Integer coachId);

    @Select("select * from class_table")
    List<ClassTable> list();

    @Select("select * from class_table where class_id = #{id}")
    ClassTable findById(Integer id);

    @Update("update class_table set class_name = #{className},class_begin = #{classBegin},class_time = #{classTime},coach_id = #{coachId},coach_name = #{coachName},class_points = #{classPoints},class_max_member = #{classMaxMember} where class_id = #{classId}")
    void update(ClassTable classTable);

    @Delete("delete from class_table where class_id = #{id}")
    void delete(Integer id);

    @Update("update class_table set class_member = #{i} where class_id = #{classId}")
    void setCurrentMember(int i, Integer classId);

    @Select("select * from class_order where class_id = #{id}")
    List<ClassOrder> orderList(Integer id);
}
