package au.id.wattle.chapman.camel.demo.service;

import au.id.wattle.chapman.camel.demo.service.dto.ComplexInput;
import au.id.wattle.chapman.camel.demo.service.dto.ComplexOutput;
import au.id.wattle.chapman.camel.demo.service.model.AmortizationEntry;
import au.id.wattle.chapman.camel.demo.service.model.SavingsState;
import au.id.wattle.chapman.camel.demo.service.reducers.InterestAccumulator;
import au.id.wattle.chapman.camel.demo.service.reducers.InterestCrediter;
import au.id.wattle.chapman.camel.demo.service.reducers.SavingsCrediter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.stereotype.Service;


@Service
public class ComplexServiceImpl implements IComplexService {

    private SavingsState process(SavingsState inputState) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime finalDate = now.plus(inputState.getInput().getInvestmentPeriod());

        SavingsState state = inputState.toBuilder()
                .interestAccumulated(BigDecimal.valueOf(0.0))
                .interestAccumulatedDate(now)
                .totalInterest(BigDecimal.valueOf(0.0))
                .totalSavings(inputState.getInput().getInitialInvestment())
                .nextInterestDate(now.plus(inputState.getInput().getInterestPeriod()))
                .nextSavingsDate(now.plus(inputState.getInput().getSavingsPeriod()))
                .amortization(new ArrayList<AmortizationEntry>())
                .build();

        state.getAmortization().add(AmortizationEntry.builder().amount(state.getInput().getInitialInvestment()).date(now).type("Inital Deposit").balance(state.getTotalSavings()).build()); 
    
        LocalDateTime nextDate = state.getNextSavingsDate().compareTo(state.getNextInterestDate()) < 0
                ? state.getNextSavingsDate()
                : state.getNextInterestDate();
        while (nextDate.compareTo(finalDate) <= 0) {

            state = InterestAccumulator.reduce(state, nextDate);
            state = InterestCrediter.reduce(state, nextDate);
            state = SavingsCrediter.reduce(state, nextDate);

            nextDate = state.getNextSavingsDate().compareTo(state.getNextInterestDate()) < 0
                    ? state.getNextSavingsDate()
                    : state.getNextInterestDate();
        }

        return state;

    }
    @Override
    public ComplexOutput calculate(ComplexInput input) {
        SavingsState state = SavingsState.builder().input(input).build();

        state = process(state);

        return ComplexOutput.builder().totalAmount(state.getTotalSavings()).totalInterest(state.getTotalInterest()).amortization(state.getAmortization()).build();
        
    }



}
