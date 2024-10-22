package au.id.wattle.chapman.camel.demo.service.reducers;

import au.id.wattle.chapman.camel.demo.service.model.AmortizationEntry;
import au.id.wattle.chapman.camel.demo.service.model.SavingsState;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;

public class InterestCrediter {

    public static final SavingsState reduce(SavingsState state, LocalDateTime date) {

        BigDecimal flooredInterest = state.getInterestAccumulated().setScale(2, RoundingMode.FLOOR);
        BigDecimal accumulatedInterest = state.getInterestAccumulated().subtract(flooredInterest);
        if(state.getNextInterestDate().compareTo(date) == 0) {
           state =  state.toBuilder()
           .interestAccumulated(accumulatedInterest)
           .nextInterestDate(date.plus(state.getInput().getInterestPeriod()))
           .totalInterest(state.getTotalInterest().add(flooredInterest))
           .totalSavings(state.getTotalSavings().add(flooredInterest)).build();

           state.getAmortization().add(AmortizationEntry.builder().amount(flooredInterest).date(date).type("Interest").balance(state.getTotalSavings()).build()); 
        }
        return state;
    }

}
