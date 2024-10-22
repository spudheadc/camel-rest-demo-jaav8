package au.id.wattle.chapman.camel.demo.service;

import org.springframework.stereotype.Service;

import au.id.wattle.chapman.camel.demo.service.dto.SimpleInput;
import au.id.wattle.chapman.camel.demo.service.dto.SimpleOutput;

@Service
public class SimpleServiceImpl implements ISimpleService {

    @Override
    public SimpleOutput calculate(SimpleInput input) {
        double monthlyRate = input.getAnnualRate() / 12.0;
        double monthlyRatePlusOne = input.getAnnualRate() / 12.0 + 1;
        double monthlyRatePlusOnePowTerm = Math.pow(monthlyRatePlusOne, input.getTermInMonths());

        double finalAmount = input.getInitialAmount() * monthlyRatePlusOnePowTerm
                + (input.getMonthlySavings() * (1- monthlyRatePlusOnePowTerm )) / (0-monthlyRate);
        finalAmount = Math.floor(finalAmount * 100)/100.0;

        return SimpleOutput.builder().totalAmount(finalAmount)
                .totalInterest(
                        finalAmount - (input.getInitialAmount() + input.getTermInMonths() * input.getMonthlySavings()))
                .build();
    }

}
