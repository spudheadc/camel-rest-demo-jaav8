package au.id.wattle.chapman.camel.demo.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import au.id.wattle.chapman.camel.demo.service.dto.SimpleInput;
import au.id.wattle.chapman.camel.demo.service.dto.SimpleOutput;

public class SimpleServiceImplTest {

    ISimpleService service = new SimpleServiceImpl();

    @Test
    void testCalculate() {
        SimpleInput input = SimpleInput.builder().annualRate(0.1).initialAmount(1000).monthlySavings(1).termInMonths(12).build();
        SimpleOutput output = service.calculate(input);
        Assertions.assertThat(output.getTotalAmount()).isEqualTo(1117.27);

    }
}
