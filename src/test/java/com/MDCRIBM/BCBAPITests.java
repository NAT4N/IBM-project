package com.MDCRIBM;

import com.MDCRIBM.api.model.dto.DataDTO;
import com.MDCRIBM.api.model.feignclient.BcbEndPoint;
import com.MDCRIBM.api.service.DataService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
class BCBAPITests {

    @Autowired
    private DataService service;
    @Autowired
    private BcbEndPoint bcbEndPoint;

    @Test
    void conncetionBCBAPI()
    {
        assertNotNull(bcbEndPoint.getBcbData().value);
        System.out.println(bcbEndPoint.getBcbData().getValue().get(0));
    }
    @Test
    void populationDB()
    {
        bcbEndPoint.getBcbData().getValue().stream().forEach(x -> service.insertData(x));
        List<DataDTO> dto = service.getData();
        assertNotNull(dto);
        assertTrue(dto.size() > 0);
    }
}
