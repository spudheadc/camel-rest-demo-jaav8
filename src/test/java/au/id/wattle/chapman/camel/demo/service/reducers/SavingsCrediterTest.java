package au.id.wattle.chapman.camel.demo.service.reducers;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import au.id.wattle.chapman.camel.demo.service.dto.ComplexInput;
import au.id.wattle.chapman.camel.demo.service.model.SavingsState;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class SavingsCrediterTest {
    @Test
    void testReduce() {
        
        LocalDateTime now = LocalDateTime.now();
        SavingsState state = SavingsState.builder()
                .input(ComplexInput.builder().savingsAmount(BigDecimal.valueOf(20)).savingsPeriod(Period.ofWeeks(1))
                        .build()).nextSavingsDate(now).totalSavings(BigDecimal.valueOf(100.0)).amortization(new ArrayList<>()).build();
                
        state = SavingsCrediter.reduce(state, now);
        Assertions.assertThat(state.getTotalSavings()).isEqualTo(BigDecimal.valueOf(120.0));
        Assertions.assertThat(ChronoUnit.WEEKS.between(now, state.getNextSavingsDate())).isEqualTo(1);
        Assertions.assertThat(state.getAmortization().size()).isEqualTo(1);

    }
}
