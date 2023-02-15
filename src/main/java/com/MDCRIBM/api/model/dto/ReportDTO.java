package com.MDCRIBM.api.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportDTO {

    private BigDecimal bovinosTotal;
    private BigDecimal ovinosTotal;
    private BigDecimal caprinosTotal;
    private BigDecimal cafeTotal;
    private BigDecimal aviculturaTotal;

}
