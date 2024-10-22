package au.id.wattle.chapman.camel.demo.service.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class AmortizationEntry {
    private LocalDateTime date;
    private String type;
    private BigDecimal amount;
    private BigDecimal balance;

}
