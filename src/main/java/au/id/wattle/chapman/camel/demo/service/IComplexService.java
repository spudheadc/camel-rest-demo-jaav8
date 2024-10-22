package au.id.wattle.chapman.camel.demo.service;

import au.id.wattle.chapman.camel.demo.service.dto.ComplexInput;
import au.id.wattle.chapman.camel.demo.service.dto.ComplexOutput;

public interface IComplexService {
    public ComplexOutput calculate(ComplexInput input);

}
