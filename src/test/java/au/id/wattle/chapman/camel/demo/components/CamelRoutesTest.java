package au.id.wattle.chapman.camel.demo.components;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import au.id.wattle.chapman.camel.demo.models.SavingsResponse;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CamelRoutesTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void testSimple() {
        ResponseEntity<SavingsResponse> response = this.restTemplate.getForEntity("http://localhost:" + port + "/savings/calculation?rate=0.1&initialAmount=1000&term=12&calculationType=simple",
        SavingsResponse.class);
        assertEquals(response.getBody().getTotalAmount(), "1104.71");
    }
}
