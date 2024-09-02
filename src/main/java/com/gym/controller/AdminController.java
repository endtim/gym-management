package com.gym.controller;

import com.gym.pojo.*;
import com.gym.service.AdminService;
import com.gym.service.MemberService;
import com.gym.utils.JwtUtil;
import com.gym.utils.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;


@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private AdminService adminService;

    @Autowired
    private MemberService memberService;

    @PostMapping("/login")
    public Result<String> login(Integer adminAccount,String password){
        Admin loginAdmin = adminService.findByAdminAccount(adminAccount);
        if(loginAdmin == null){
            return Result.error("账号错误");
        }
        if(Md5Util.getMD5String(password).equals(loginAdmin.getAdminPassword())){
            Map<String,Object> claims = new HashMap<>();
            claims.put("account",loginAdmin.getAdminAccount());
            claims.put("adminAccount",loginAdmin.getAdminAccount());
            String token = JwtUtil.genToken(claims);
            String account = Integer.toString(loginAdmin.getAdminAccount());
            ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
            operations.set(account,token,1, TimeUnit.HOURS);
            return Result.success(token);
        }
        return Result.error("密码错误");

    }

    @PostMapping("/addMember")
    public Result addMember(@RequestBody @Validated Member member){
        //会员账号&卡号随机生成
        Random random = new Random();
        String account1 = "2024";
        for (int i = 0; i < 5; i++) {
            account1 += random.nextInt(10);
        }
        Integer account = Integer.parseInt(account1);
        //初始密码
        String password = "123456";
        //获取当前日期
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String nowDay = simpleDateFormat.format(date);
        member.setMemberAccount(account);
        member.setMemberPassword(password);
        member.setCardTime(nowDay);
        adminService.insertMember(member);
        return Result.success(account);

    }

    @GetMapping
    public Result<PageBean<Member>> list(Integer pageNum, Integer pageSize){

        PageBean<Member> pb =  adminService.list(pageNum,pageSize);
        return Result.success(pb);

    }

    @DeleteMapping
    public Result delete(Integer account){
        adminService.delete(account);
        return Result.success();
    }

    @PutMapping("/update")
    public Result update(@RequestBody @Validated Member member){

        adminService.update(member);
        return Result.success();
    }

    @GetMapping("/detail")
    public Result memberInfo(Integer account){
        Member member = memberService.findByMemberAccount(account);
        if(member == null){
            return Result.error("账号不存在");
        }
        return Result.success(member);
    }

    @GetMapping("/memberShip")
    public Result memberShip(Integer account){
        String msg = adminService.searchCard(account);
        if(msg == null){
            return Result.success("暂未办理会员服务");
        }
        return Result.success("截至"+msg+"过期");
    }

    @GetMapping("/list")
    public Result list(Integer id){
        Member member = memberService.findByMemberAccount(id);
        if(member == null){
            return Result.error("账号不存在");
        }
        List<Member> mb = adminService.orderList(id);
        return Result.success(mb);

    }

}
