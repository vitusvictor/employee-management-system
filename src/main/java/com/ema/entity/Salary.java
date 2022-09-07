package com.ema.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Salary extends BaseClass{
    private BigDecimal amount;

    private BigDecimal tax;

    private BigDecimal deduction;
}
