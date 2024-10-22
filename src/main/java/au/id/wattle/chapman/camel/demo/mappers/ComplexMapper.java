package au.id.wattle.chapman.camel.demo.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.temporal.TemporalAmount;
import java.time.Period;

import au.id.wattle.chapman.camel.demo.models.SavingsResponse;
import au.id.wattle.chapman.camel.demo.service.dto.ComplexInput;
import au.id.wattle.chapman.camel.demo.service.dto.ComplexOutput;
import au.id.wattle.chapman.camel.demo.service.dto.QueryParams;
import au.id.wattle.chapman.camel.demo.service.dto.SimpleInput;
import au.id.wattle.chapman.camel.demo.service.dto.SimpleOutput;

@Mapper(componentModel = "spring")
public abstract class ComplexMapper {
    public abstract SavingsResponse complexOutputToSavingsResponse(ComplexOutput output);
    
    @Mapping(target = "savingsPeriod", source = "params.savingsFrequency", qualifiedByName="frequencyToPeriod")
    @Mapping(target = "interestPeriod", source = "params.interestFrequency", qualifiedByName="frequencyToPeriod")
    @Mapping(target = "investmentPeriod", source = "params.term", qualifiedByName="termToPeriod")
    public abstract ComplexInput complexInputFromQueryParams(QueryParams params);

    @Mapping(target = "amortization", ignore = true)
    public abstract SavingsResponse simpleOutputToSavingsResponse(SimpleOutput output);

    @Mapping(target = "monthlySavings", source = "params.savingsAmount")
    @Mapping(target = "termInMonths", source = "params.term")
    @Mapping(target = "initialAmount", source = "params.initialInvestment")
    @Mapping(target = "annualRate", source = "params.interestRate")
    public abstract SimpleInput simpleInputFromQueryParams(QueryParams params);

    @Named("frequencyToPeriod")
    public TemporalAmount frequencyToPeriod(String frequency) {

        Period ret = null;
        switch (frequency) {
            case "weekly":
                ret = Period.ofWeeks(1);
                break;
            case "fortnightly":
                ret = Period.ofWeeks(2);
                break;
            default:
            case "monthly":
                ret = Period.ofMonths(1);
                break;

        }
        return ret;
    }
    @Named("termToPeriod")
    public TemporalAmount termToPeriod(int term) {
        return Period.ofMonths(term);
    }

}
