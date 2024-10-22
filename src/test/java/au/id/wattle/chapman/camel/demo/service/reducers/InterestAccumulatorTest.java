package au.id.wattle.chapman.camel.demo.service.reducers;

import org.assertj.core.api.Assertions;
import org.assertj.core.data.Offset;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;

import au.id.wattle.chapman.camel.demo.service.dto.ComplexInput;
import au.id.wattle.chapman.camel.demo.service.model.SavingsState;

public class InterestAccumulatorTest {
    @Test
    void testReduce() {

        LocalDateTime now = LocalDateTime.now();
        SavingsState state = SavingsState.builder().interestAccumulatedDate(now).totalSavings(BigDecimal.valueOf(100.0))
                .input(ComplexInput.builder().interestRate(BigDecimal.valueOf(0.1)).interestPeriod(Period.ofMonths(1))
                        .build())
                .build();
        state = InterestAccumulator.reduce(state, now.plusDays(5));
        Assertions.assertThat(state.getInterestAccumulated()).isCloseTo(BigDecimal.valueOf(0.1369863013), Offset.offset(BigDecimal.valueOf(0.00000001)));
        Assertions.assertThat(ChronoUnit.DAYS.between(now, state.getInterestAccumulatedDate())).isEqualTo(5);
    }
}
