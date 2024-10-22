package au.id.wattle.chapman.camel.demo.service.dto;

import java.math.BigDecimal;
import java.util.List;

import au.id.wattle.chapman.camel.demo.service.model.AmortizationEntry;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ComplexOutput {
    private BigDecimal totalAmount;
    private BigDecimal totalInterest;
    private List<AmortizationEntry> amortization;
}
