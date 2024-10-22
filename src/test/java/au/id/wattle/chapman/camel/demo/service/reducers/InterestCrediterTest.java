package au.id.wattle.chapman.camel.demo.service.reducers;

import org.assertj.core.api.Assertions;
import org.assertj.core.data.Offset;
import org.junit.jupiter.api.Test;

import au.id.wattle.chapman.camel.demo.service.dto.ComplexInput;
import au.id.wattle.chapman.camel.demo.service.model.SavingsState;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class InterestCrediterTest {
    @Test
    void testReduce() {
        
        LocalDateTime now = LocalDateTime.now();
        SavingsState state = SavingsState.builder().nextInterestDate(now).totalInterest(BigDecimal.valueOf(00.0)).totalSavings(BigDecimal.valueOf(100.0))
        .input(ComplexInput.builder().interestPeriod(Period.ofMonths(1))
                        .build())
                .interestAccumulated(BigDecimal.valueOf(20.123)).amortization(new ArrayList<>()).build();
                
        state = InterestCrediter.reduce(state, now);
        Assertions.assertThat(state.getInterestAccumulated()).isCloseTo(BigDecimal.valueOf(0.003),Offset.offset(BigDecimal.valueOf(0.00001)));
        Assertions.assertThat(state.getTotalSavings()).isEqualTo(BigDecimal.valueOf(120.12));
        Assertions.assertThat(state.getTotalInterest()).isEqualTo(BigDecimal.valueOf(20.12));
        Assertions.assertThat(ChronoUnit.MONTHS.between(now, state.getNextInterestDate())).isEqualTo(1);
        Assertions.assertThat(state.getAmortization().size()).isEqualTo(1);

    }
}
