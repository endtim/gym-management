package com.gym.pojo;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ClassTable {
    private Integer classId;
    private String className;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime classBegin;
    private String classTime;
    private Integer coachId;
    private String coachName;
    private Integer classPoints;
    private Integer classMaxMember;
    private Integer classMember;
}
