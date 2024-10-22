package au.id.wattle.chapman.camel.demo.service.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SimpleOutput {
    private double totalAmount;
    private double totalInterest;
}
