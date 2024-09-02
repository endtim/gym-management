package com.gym.pojo;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class Member {
    private Integer memberAccount;
    @JsonIgnore
    private String memberPassword;
    @NotEmpty(groups = Add.class)
    private String memberName;
    @NotEmpty(groups = Update.class)
    private String memberGender;
    private Integer memberAge;
    private Integer memberHeight;
    private Integer memberWeight;
    private Long memberPhone;
    private String cardTime;
    private Integer points;

    public interface Add{

    }

    public interface Update{

    }
}
