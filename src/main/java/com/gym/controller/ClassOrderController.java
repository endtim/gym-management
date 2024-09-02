package com.gym.controller;


import com.gym.pojo.*;
import com.gym.service.AdminService;
import com.gym.service.ClassOrderService;
import com.gym.service.ClassService;
import com.gym.service.MemberService;
import com.gym.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDateTime;
import java.util.Map;


@RestController
@RequestMapping("/classOrder")
@Validated
public class ClassOrderController {

    @Autowired
    private ClassOrderService classOrderService;

    @Autowired
    private ClassService classService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private AdminService adminService;

    @PostMapping
    public Result singUp (Integer id){
        ClassTable classTable = classService.findById(id);
        if(classTable.getClassMember() >= classTable.getClassMaxMember()){
            return Result.error("课程人数已满，无法报名");
        }
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer memberAccount = (Integer) map.get("account");
        Member member = memberService.findByMemberAccount(memberAccount);

        ClassOrder classOrder1 = classOrderService.findByIdAndMemberAccount(id,memberAccount);
        if(classOrder1 != null){
            return Result.error("您已选择该课程");
        }
        if(member.getPoints() < classTable.getClassPoints()){
            return Result.error("积分不足，无法报名");
        }
        ClassOrder classOrder = new ClassOrder();
        classOrder.setClassId(id);
        classOrder.setClassName(classTable.getClassName());
        classOrder.setCoachId(classTable.getCoachId());
        classOrder.setClassBegin(classTable.getClassBegin());
        classOrder.setCoachName(classTable.getCoachName());
        classOrder.setMemberAccount(memberAccount);
        classOrder.setMemberName(member.getMemberName());
        classService.setCurrentMember(classTable.getClassMember()+1,classTable.getClassId());
        memberService.setPoints(member.getPoints()-classTable.getClassPoints(),memberAccount);
        classOrderService.signUp(classOrder);
        return Result.success();


    }

    @DeleteMapping
    public Result Withdrawal(Integer id){
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer memberAccount = (Integer) map.get("account");
        Member member = memberService.findByMemberAccount(memberAccount);
        Integer classId = classOrderService.findClassId(id);
        ClassTable classTable = classService.findById(classId);
        if(LocalDateTime.now().isAfter(classTable.getClassBegin())){
            return Result.error("课程已经开始，无法退课");
        }
        Integer points = member.getPoints();
        points += classTable.getClassPoints();
        member.setPoints(points);
        adminService.update(member);
        classService.setCurrentMember(classTable.getClassMember()-1,classId );
        classOrderService.delete(id);
        return Result.success();
    }

    @GetMapping
    public Result<PageBean<ClassOrder>> list(Integer pageNum, Integer pageSize){

        PageBean<ClassOrder> pb =  classOrderService.list(pageNum,pageSize);
        return Result.success(pb);

    }

    @GetMapping("/myList")
    public Result<PageBean<ClassOrder>> myList(Integer pageNum, Integer pageSize){

        PageBean<ClassOrder> pb =  classOrderService.myList(pageNum,pageSize);
        return Result.success(pb);

    }

}
