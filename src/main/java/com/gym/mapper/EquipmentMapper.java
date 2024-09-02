package com.gym.mapper;

import com.gym.pojo.Equipment;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface EquipmentMapper {

    @Insert("insert into equipment(equipment_name,equipment_location,equipment_status,equipment_message)" +
            " values(#{equipmentName},#{equipmentLocation},#{equipmentStatus},#{equipmentMessage})")
    void add(Equipment equipment);


    List<Equipment> list(String status);

    @Update("update equipment set equipment_name = #{equipmentName},equipment_location = #{equipmentLocation},equipment_status = #{equipmentStatus},equipment_message = #{equipmentMessage} where equipment_id = #{equipmentId}")
    void update(Equipment equipment);

    @Select("select * from equipment where equipment_id = #{id}")
    Equipment findById(Integer id);

    @Delete("delete from equipment where equipment_id = #{id}")
    void delete(Integer id);
}
