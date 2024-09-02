package com.gym.controller;


import com.gym.pojo.Member;
import com.gym.pojo.Result;
import com.gym.service.MemberService;
import com.gym.utils.JwtUtil;
import com.gym.utils.Md5Util;
import com.gym.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/member")
@Validated
public class MemberController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private MemberService memberService;

    @PostMapping("/login")
    public Result<String> login(Integer memberAccount,String password){
        Member loginMember = memberService.findByMemberAccount(memberAccount);
        if(loginMember == null){
            return Result.error("账号错误");
        }
        if(Md5Util.getMD5String(password).equals(loginMember.getMemberPassword())){
            Map<String,Object> claims = new HashMap<>();
            claims.put("account",loginMember.getMemberAccount());
            claims.put("memberName",loginMember.getMemberName());
            String token = JwtUtil.genToken(claims);
            String account = Integer.toString(loginMember.getMemberAccount());
            ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
            operations.set(account,token,1, TimeUnit.HOURS);
            return Result.success(token);
        }
        return Result.error("密码错误");


    }

    @GetMapping("/memberInfo")
    public Result<Member> memberInfo(){
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer memberAccount = (Integer) map.get("account");
        Member member = memberService.findByMemberAccount(memberAccount);
        return Result.success(member);
    }

    @PutMapping("/update")
    public Result update(@RequestBody @Validated(Member.Update.class) Member member){

        memberService.update(member);
        return Result.success();
    }

    @PatchMapping("/updatePwd")
    public Result updatePwd(@RequestBody Map<String,String> params,@RequestHeader("Authorization") String token){

        String oldPwd = params.get("old_pwd");
        String newPwd = params.get("new_pwd");
        String rePwd = params.get("re_pwd");

        if(!StringUtils.hasLength(oldPwd) || !StringUtils.hasLength(newPwd) || !StringUtils.hasLength(rePwd)){
            return Result.error("输入的密码不能为空");
        }

        Map<String,Object> map = ThreadLocalUtil.get();
        Integer account = (Integer) map.get("account");
        Member loginMember = memberService.findByMemberAccount(account);


        if(!Md5Util.getMD5String(oldPwd).equals(loginMember.getMemberPassword())){
            return Result.error("原密码填写错误");
        }

        if(!newPwd.equals(rePwd)){
            return Result.error("两次新密码不一致");
        }

        if(newPwd.equals(oldPwd)){
            return Result.error("新密码不得与原密码一致");
        }

        memberService.updatePwd(newPwd,account);

        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        operations.getOperations().delete(String.valueOf(account));
        return Result.success();

    }


    @PostMapping("/membership")
    public Result buyMemberCard( String membershipType){
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer account = (Integer) map.get("account");
        Member loginMember = memberService.findByMemberAccount(account);
        Integer points = loginMember.getPoints();
        if(membershipType.equals("月卡")){
            if(points>=8){
                loginMember.setPoints(points-8);
                memberService.setPoints(loginMember.getPoints(),account);
                memberService.byMemberCard(loginMember,membershipType);
            }
            else{
                return Result.error("积分不足");
            }
        }
        else if (membershipType.equals("季卡")) {
            if(points>=20){
                loginMember.setPoints(points-20);
                memberService.setPoints(loginMember.getPoints(),account);
                memberService.byMemberCard(loginMember,membershipType);
            }
            else{
                return Result.error("积分不足");
            }
        }
        else{
            if(points>=78){
                loginMember.setPoints(points-78);
                memberService.setPoints(loginMember.getPoints(),account);
                memberService.byMemberCard(loginMember,membershipType);
            }
            else{
                return Result.error("积分不足");
            }
        }
        return Result.success("购买成功");
    }

    @GetMapping("/membership")
    public Result<String> searchCard(){
        String msg = memberService.searchCard();
        return Result.success("会员截至"+msg+"到期");
    }

}
