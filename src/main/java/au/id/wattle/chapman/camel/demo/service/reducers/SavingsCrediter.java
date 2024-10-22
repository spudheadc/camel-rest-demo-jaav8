package au.id.wattle.chapman.camel.demo.service.reducers;

import au.id.wattle.chapman.camel.demo.service.model.AmortizationEntry;
import au.id.wattle.chapman.camel.demo.service.model.SavingsState;
import java.time.LocalDateTime;

public class SavingsCrediter {

    public static final SavingsState reduce(SavingsState state, LocalDateTime date) {

        
        if(state.getNextSavingsDate().compareTo(date) == 0) {
            state =  state.toBuilder()
            .nextSavingsDate(date.plus(state.getInput().getSavingsPeriod()))
            .totalSavings(state.getTotalSavings().add(state.getInput().getSavingsAmount())).build();

           state.getAmortization().add(AmortizationEntry.builder().amount(state.getInput().getSavingsAmount()).date(date).type("Savings").balance(state.getTotalSavings()).build()); 
        }
        return state;
    }

}
