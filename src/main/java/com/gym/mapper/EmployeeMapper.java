package com.gym.mapper;

import com.gym.pojo.ClassTable;
import com.gym.pojo.Employee;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface EmployeeMapper {

    @Insert("insert into employee(employee_account,employee_name,employee_gender,employee_age,entry_time,staff,employee_message)" +
            " values (#{employeeAccount},#{employeeName},#{employeeGender},#{employeeAge},#{entryTime},#{staff},#{employeeMessage})")
    void add(Employee employee);

    @Select("select * from employee")
    List<Employee> list();

    @Select("select * from employee where employee_account = #{account}")
    Employee findById(Integer account);

    @Update("update employee set employee_name=#{employeeName},employee_age = #{employeeAge},staff = #{staff},employee_message = #{employeeMessage} where employee_account = #{employeeAccount}")
    void update(Employee employee);

    @Delete("delete from employee where employee_account = #{id}")
    void delete(Integer id);

    @Select("select * from class_table where coach_id = #{id}")
    List<ClassTable> classList(Integer id);
}
