package au.id.wattle.chapman.camel.demo.service.reducers;

import au.id.wattle.chapman.camel.demo.service.model.SavingsState;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class InterestAccumulator {
    public static final SavingsState reduce(SavingsState state, LocalDateTime date) {
        long days = ChronoUnit.DAYS.between(state.getInterestAccumulatedDate(), date);
        return state.toBuilder().interestAccumulated(state.getTotalSavings()
                .multiply(state.getInput().getInterestRate()).multiply(BigDecimal.valueOf(days / 365.0)))
                .interestAccumulatedDate(date).build();
    }
}
