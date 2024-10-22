package au.id.wattle.chapman.camel.demo.service.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SimpleInput {
    private double monthlySavings;
    private int termInMonths;
    private double initialAmount;
    private double annualRate;
}
