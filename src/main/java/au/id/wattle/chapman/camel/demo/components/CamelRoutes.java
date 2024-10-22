package au.id.wattle.chapman.camel.demo.components;

import java.math.BigDecimal;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import au.id.wattle.chapman.camel.demo.mappers.ComplexMapper;
import au.id.wattle.chapman.camel.demo.models.SavingsResponse;
import au.id.wattle.chapman.camel.demo.service.IComplexService;
import au.id.wattle.chapman.camel.demo.service.ISimpleService;
import au.id.wattle.chapman.camel.demo.service.dto.ComplexInput;
import au.id.wattle.chapman.camel.demo.service.dto.ComplexOutput;
import au.id.wattle.chapman.camel.demo.service.dto.QueryParams;
import au.id.wattle.chapman.camel.demo.service.dto.SimpleInput;
import au.id.wattle.chapman.camel.demo.service.dto.SimpleOutput;

@Component
public class CamelRoutes extends RouteBuilder {
    @Autowired(required = false)
    private IComplexService complexService;
    @Autowired(required = false)
    private ISimpleService simpleService;

    @Autowired
    private ComplexMapper mapper;

    @Override
    public void configure() throws Exception {
        from("direct:calculateSavings")
                .routeId("calculateSavings")
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        QueryParams input = QueryParams.builder()
                                .initialInvestment(
                                        new BigDecimal(exchange.getIn().getHeader("initialAmount").toString()))
                                .interestRate(new BigDecimal(exchange.getIn().getHeader("rate").toString()))
                                .term(Integer.parseInt(exchange.getIn().getHeader("term").toString()))
                                .interestFrequency(exchange.getIn().getHeader("interestFrequency").toString())
                                .savingsAmount(new BigDecimal(exchange.getIn().getHeader("savings").toString()))
                                .savingsFrequency(exchange.getIn().getHeader("frequency").toString())
                                .build();
                        exchange.getIn().setBody(input);
                    }
                })
                .choice()
                .when(header("calculationType").isEqualTo("simple"))
                .to("direct:calculateSavingsSimple")
                .otherwise().to("direct:calculateSavingsComplex");
        from("direct:calculateSavingsComplex")
                .routeId("complex-route")
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        ComplexInput input = mapper.complexInputFromQueryParams((QueryParams) exchange.getIn().getBody());
                        ComplexOutput output = complexService.calculate(input);
                        SavingsResponse response = mapper.complexOutputToSavingsResponse(output);
                        exchange.getMessage().setBody(response);
                    }
                })
                .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(200))
                .setHeader(Exchange.CONTENT_TYPE, constant("application/json"));
        from("direct:calculateSavingsSimple")
                .routeId("simple-route")
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        SimpleInput input = mapper.simpleInputFromQueryParams((QueryParams) exchange.getIn().getBody());
                        SimpleOutput output = simpleService.calculate(input);
                        SavingsResponse response = mapper.simpleOutputToSavingsResponse(output);
                        exchange.getMessage().setBody(response);
                    }
                })
                .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(200))
                .setHeader(Exchange.CONTENT_TYPE, constant("application/json"));
    }

}
