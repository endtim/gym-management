package com.gym.pojo;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ClassOrder {
    private Integer classOrderId;
    private Integer classId;
    private String className;
    private Integer coachId;
    private String coachName;
    private String memberName;
    private Integer memberAccount;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime classBegin;
}
