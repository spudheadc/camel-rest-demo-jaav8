package au.id.wattle.chapman.camel.demo.service;

import java.math.BigDecimal;
import java.time.Period;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import au.id.wattle.chapman.camel.demo.service.dto.ComplexInput;
import au.id.wattle.chapman.camel.demo.service.dto.ComplexOutput;

public class ComplexServiceImplTest {
    @Test
    void testCalculate() {
        ComplexInput input = ComplexInput.builder().initialInvestment(BigDecimal.valueOf(1000)).interestRate(BigDecimal.valueOf(0.07)).interestPeriod(Period.ofMonths(1)).savingsAmount(BigDecimal.valueOf(100)).savingsPeriod(Period.ofWeeks(2)).investmentPeriod(Period.ofMonths(12)).build();
        IComplexService service = new ComplexServiceImpl();
        ComplexOutput output = service.calculate(input);
        Assertions.assertThat(output.getTotalAmount()).isEqualTo(BigDecimal.valueOf(3640.91));
        Assertions.assertThat(output.getTotalInterest()).isEqualTo(BigDecimal.valueOf(40.91));
        Assertions.assertThat(output.getAmortization().size()).isEqualTo(39);

    }
    @Test
    void testCalculate2() {
        ComplexInput input = ComplexInput.builder().initialInvestment(BigDecimal.valueOf(1000)).interestRate(BigDecimal.valueOf(0.1)).interestPeriod(Period.ofMonths(1)).savingsAmount(BigDecimal.valueOf(1)).savingsPeriod(Period.ofMonths(1)).investmentPeriod(Period.ofMonths(12)).build();
        IComplexService service = new ComplexServiceImpl();
        ComplexOutput output = service.calculate(input);
        Assertions.assertThat(output.getTotalAmount().setScale(2)).isEqualTo(BigDecimal.valueOf(1117.20).setScale(2));
    }
}
