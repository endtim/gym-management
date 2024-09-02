package com.gym.pojo;


import com.gym.anno.EStatus;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class Equipment {
    @NotNull(groups = Update.class)
    private Integer equipmentId;
    @NotEmpty(groups = Add.class)
    private String equipmentName;
    @NotEmpty(groups = Add.class)
    private String equipmentLocation;
    @EStatus(groups = {Add.class,Update.class})
    private String equipmentStatus;
    private String equipmentMessage;

    public interface Add{

    }

    public interface Update{

    }
}
