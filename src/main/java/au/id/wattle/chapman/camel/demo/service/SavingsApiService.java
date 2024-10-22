package au.id.wattle.chapman.camel.demo.service;

import java.math.BigDecimal;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import au.id.wattle.chapman.camel.demo.apis.SavingsApiDelegate;
import au.id.wattle.chapman.camel.demo.models.SavingsResponse;

@Service
public class SavingsApiService implements SavingsApiDelegate {

  @Autowired
  private ProducerTemplate producerTemplate;

  @Override
    public ResponseEntity<SavingsResponse> calculateSavings(BigDecimal rate, BigDecimal savings, String frequency,
            String interestFrequency, BigDecimal initialAmount, Integer term, String calculationType) {

              Map<String, Object> map = Stream.of(new String[][] {
                { "initialAmount", initialAmount.toString() }, 
                { "rate", rate.toString() }, 
                { "term", term.toString() }, 
                { "interestFrequency", interestFrequency.toString() }, 
                { "savings", savings.toString() }, 
                { "frequency", savings.toString() }, 
                { "calculationType", calculationType.toString() }, 
              }).collect(Collectors.toMap(data -> data[0], data -> data[1]));
        SavingsResponse response = producerTemplate.requestBodyAndHeaders("direct:calculateSavings", "", map, SavingsResponse.class);
        
        return new ResponseEntity<SavingsResponse>(response, HttpStatus.OK);
    }

}
