package com.MDCRIBM.Integration;


import com.MDCRIBM.api.model.entity.BcbRootEntity;
import org.junit.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.rules.ErrorCollector;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;


@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BcbControllerTests {

    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate;

    @Rule
    public ErrorCollector errorCollector = new ErrorCollector();

    @BeforeEach
    public void setup()
    {
        restTemplate = new TestRestTemplate();
    }

    @Test
    void getAPIData()
    {
        ResponseEntity<BcbRootEntity> responseEntity = restTemplate.getForEntity("http://localhost:"+ port +"/api/v2/imp/bcbapi", BcbRootEntity.class);
        BcbRootEntity data = responseEntity.getBody();
        assertNotNull(data);
        assertEquals(100, data.getValue().size());
    }
    @Test
    void updateDatabase()
    {
        ResponseEntity<Void> responseEntity = restTemplate.getForEntity("http://localhost:"+ port +"/api/v2/imp/bcbapi/update", null ,Void.class);
        assertEquals(200, responseEntity.getStatusCodeValue());

    }
}
