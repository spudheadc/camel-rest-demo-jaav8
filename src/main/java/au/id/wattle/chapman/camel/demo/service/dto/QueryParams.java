package au.id.wattle.chapman.camel.demo.service.dto;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class QueryParams {
    private int term;
    private BigDecimal initialInvestment;
    private BigDecimal savingsAmount;
    private String savingsFrequency;
    private BigDecimal interestRate;
    private String interestFrequency;

}
