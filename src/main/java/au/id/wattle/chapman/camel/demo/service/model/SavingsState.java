package au.id.wattle.chapman.camel.demo.service.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import au.id.wattle.chapman.camel.demo.service.dto.ComplexInput;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class SavingsState {
    private BigDecimal interestAccumulated;
    private LocalDateTime interestAccumulatedDate;
    private ComplexInput input;
    private BigDecimal totalSavings;
    private BigDecimal totalInterest;
    private LocalDateTime nextInterestDate;
    private LocalDateTime nextSavingsDate;
    private List<AmortizationEntry> amortization;


}
