package com.gym.pojo;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.gym.anno.CardType;
import com.gym.anno.Status;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class MemberShip {
    @NotNull
    private Integer id;
    private Integer memberAccount;
    @CardType
    private String cardType;
    private LocalDate startDate;
    private LocalDate endDate;
    @Status
    private String status;
}
