package au.id.wattle.chapman.camel.demo.service;

import au.id.wattle.chapman.camel.demo.service.dto.SimpleInput;
import au.id.wattle.chapman.camel.demo.service.dto.SimpleOutput;

public interface ISimpleService {
    public SimpleOutput calculate(SimpleInput input);

}
