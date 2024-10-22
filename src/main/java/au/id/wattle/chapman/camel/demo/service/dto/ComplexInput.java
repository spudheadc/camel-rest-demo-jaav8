package au.id.wattle.chapman.camel.demo.service.dto;

import java.math.BigDecimal;
import java.time.temporal.TemporalAmount;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ComplexInput {
    private BigDecimal savingsAmount;
    private TemporalAmount savingsPeriod;
    private TemporalAmount investmentPeriod;
    private BigDecimal initialInvestment;
    private BigDecimal interestRate;
    private TemporalAmount interestPeriod;
}
