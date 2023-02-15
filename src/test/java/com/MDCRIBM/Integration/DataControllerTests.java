package com.MDCRIBM.Integration;

import com.MDCRIBM.api.model.dto.DataDTO;
import com.MDCRIBM.api.model.entity.BcbDataEntity;
import com.MDCRIBM.api.model.dto.ReportDTO;
import org.junit.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.rules.ErrorCollector;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DataControllerTests {
    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate;
    BcbDataEntity data;
    @Rule
    public ErrorCollector errorCollector = new ErrorCollector();

    @BeforeEach
    public void setup()
    {
        restTemplate = new TestRestTemplate();
        data = new BcbDataEntity();

        data.setMunicipio("A");
        data.setNomeProduto("B");
        data.setMesEmissao("C");
        data.setAnoEmissao("D");
        data.setCdPrograma("E");
        data.setCdSubPrograma("F");
        data.setCdFonteRecurso("G");
        data.setCdTipoSeguro("H");
        data.setCdEstado("I");
        data.setVlCusteio(0.00);
        data.setCdProduto("J");
        data.setCdMunicipio("K");
        data.setAtividade("L");
        data.setCdModalidade("M");
        data.setAreaInvest(0.00);
    }


    @Test
    void getById()
    {
        ResponseEntity<DataDTO[]> responseEntity = restTemplate.getForEntity("http://localhost:"+ port +"/api/v2/imp/contrato?id=15", DataDTO[].class);
        DataDTO[] data = responseEntity.getBody();
        assertNotNull(data);
        assertEquals(Arrays.stream(data).map(DataDTO::getNomeProduto).toList().get(0), "OUTRAS MÁQUINAS");
        assertEquals(Arrays.stream(data).map(DataDTO::getAnoEmissao).toList().get(0), "2014");
        assertEquals(Arrays.stream(data).map(DataDTO::getCdProduto).toList().get(0), "5440");
    }
    @Test
    void getDataNomeProduto()
    {
        ResponseEntity<DataDTO[]> responseEntity = restTemplate.getForEntity("http://localhost:"+ port +"/api/v2/imp/contrato?nomeproduto=BOVINOS", DataDTO[].class);
        DataDTO[] data = responseEntity.getBody();
        assertNotNull(data);
        assertEquals(10, data.length);
    }
    @Test
    void getDataCdProduto()
    {
        ResponseEntity<DataDTO[]> responseEntity = restTemplate.getForEntity("http://localhost:"+ port +"/api/v2/imp/contrato?cdproduto=1300", DataDTO[].class);
        DataDTO[] data = responseEntity.getBody();
        assertNotNull(data);
        assertEquals(10, data.length);
    }
    @Test
    void getDataCdPrograma()
    {
        ResponseEntity<DataDTO[]> responseEntity = restTemplate.getForEntity("http://localhost:"+ port +"/api/v2/imp/contrato?cdprograma=0999", DataDTO[].class);
        DataDTO[] data = responseEntity.getBody();
        assertNotNull(data);
        assertEquals(10, data.length);
    }
    @Test
    void getDataCdSubPrograma()
    {
        ResponseEntity<DataDTO[]> responseEntity = restTemplate.getForEntity("http://localhost:"+ port +"/api/v2/imp/contrato?cdsubprograma=0", DataDTO[].class);
        DataDTO[] data = responseEntity.getBody();
        assertNotNull(data);
        assertEquals(10, data.length);
    }
    @Test
    void getDataCdFonteRecurso()
    {
        ResponseEntity<DataDTO[]> responseEntity = restTemplate.getForEntity("http://localhost:"+ port +"/api/v2/imp/contrato?cdfonterecurso=0303", DataDTO[].class);
        DataDTO[] data = responseEntity.getBody();
        assertNotNull(data);
        assertEquals(4, data.length);
    }
    @Test
    void getDataCdTipoSeguro()
    {
        ResponseEntity<DataDTO[]> responseEntity = restTemplate.getForEntity("http://localhost:"+ port +"/api/v2/imp/contrato?cdtiposeguro=9", DataDTO[].class);
        DataDTO[] data = responseEntity.getBody();
        assertNotNull(data);
        assertEquals(10, data.length);
    }
    @Test
    void getDataCdEstado()
    {
        ResponseEntity<DataDTO[]> responseEntity = restTemplate.getForEntity("http://localhost:"+ port +"/api/v2/imp/contrato?cdestado=27", DataDTO[].class);
        DataDTO[] data = responseEntity.getBody();
        assertNotNull(data);
        assertEquals(3, data.length);
    }
    @Test
    void getDataCdMunicipio()
    {
        ResponseEntity<DataDTO[]> responseEntity = restTemplate.getForEntity("http://localhost:"+ port +"/api/v2/imp/contrato?cdmunicipio=48897", DataDTO[].class);
        DataDTO[] data = responseEntity.getBody();
        assertNotNull(data);
        assertEquals(1, data.length);
    }
    @Test
    void getDataCdModalidade()
    {
        ResponseEntity<DataDTO[]> responseEntity = restTemplate.getForEntity("http://localhost:"+ port +"/api/v2/imp/contrato?cdmodalidade=27", DataDTO[].class);
        DataDTO[] data = responseEntity.getBody();
        assertNotNull(data);
        assertEquals(10, data.length);
    }
    @Test
    void getDataVlCusteio()
    {
        ResponseEntity<DataDTO[]> responseEntity = restTemplate.getForEntity("http://localhost:"+ port +"/api/v2/imp/contrato?vlcusteio=73683.2", DataDTO[].class);
        DataDTO[] data = responseEntity.getBody();
        assertNotNull(data);
        assertEquals(1, data.length);
    }
    @Test
    void getDataMunicipio()
    {
        ResponseEntity<DataDTO[]> responseEntity = restTemplate.getForEntity("http://localhost:"+ port +"/api/v2/imp/contrato?municipio=CAMPO GRANDE", DataDTO[].class);
        DataDTO[] data = responseEntity.getBody();
        assertNotNull(data);
        assertEquals(1, data.length);
    }
    @Test
    void getDataAnoEmissao()
    {
        ResponseEntity<DataDTO[]> responseEntity = restTemplate.getForEntity("http://localhost:"+ port +"/api/v2/imp/contrato?ano=2015", DataDTO[].class);
        DataDTO[] data = responseEntity.getBody();
        assertNotNull(data);
        assertEquals(10, data.length);
    }
    @Test
    void getDataMesEmissao()
    {
        ResponseEntity<DataDTO[]> responseEntity = restTemplate.getForEntity("http://localhost:"+ port +"/api/v2/imp/contrato?mes=12", DataDTO[].class);
        DataDTO[] data = responseEntity.getBody();
        assertNotNull(data);
        assertEquals(10, data.length);
    }
    @Test
    void getDataAtividade()
    {
        ResponseEntity<DataDTO[]> responseEntity = restTemplate.getForEntity("http://localhost:"+ port +"/api/v2/imp/contrato?atividade=2", DataDTO[].class);
        DataDTO[] data = responseEntity.getBody();
        assertNotNull(data);
        assertEquals(10, data.length);
    }
    @Test
    void getDataAreaInvest()
    {
        ResponseEntity<DataDTO[]> responseEntity = restTemplate.getForEntity("http://localhost:"+ port +"/api/v2/imp/contrato?areainvest=0", DataDTO[].class);
        DataDTO[] data = responseEntity.getBody();
        assertNotNull(data);
        assertEquals(10, data.length);
    }

    @Test
    void getRelatorio()
    {
        ResponseEntity<ReportDTO> responseEntity = restTemplate.getForEntity("http://localhost:"+ port +"/api/v2/imp/contrato/relatorio/2015", ReportDTO.class);
        ReportDTO data = responseEntity.getBody();
        assertNotNull(data);
        assertEquals(1166000.0, Double.parseDouble(data.getBovinosTotal().toString()));
    }

    @Test
    void getDataAllArgs()
    {
        ResponseEntity<DataDTO[]> responseEntity = restTemplate.getForEntity("http://localhost:"+ port +"/api/v2/imp/contrato?id=15&nomeproduto=OUTRAS MÁQUINAS&cdprograma=0999&cdsubprograma=0&cdfonterecurso=0503&cdtiposeguro=9&cdestado=10&cdproduto=5440&cdmunicipio=31615&cdmodalidade=14&municipio=TURVÂNIA&mesemissao=05&anoemissao=2014&vlcusteio=33500.0&atividade=1&areaInvest=0.0", DataDTO[].class);
        DataDTO[] data = responseEntity.getBody();
        assertNotNull(data);
        assertEquals(1, data.length);
    }

    @Test
    void insertDatabase() {
        HttpEntity<BcbDataEntity> request = new HttpEntity<>(data, null);
        ResponseEntity<String> result = restTemplate.postForEntity("http://localhost:"+ port +"/api/v2/imp/contrato/", request, String.class);
        assertEquals(201, result.getStatusCodeValue());

        BcbDataEntity faildata = data;
        faildata.setId(89L);

        HttpEntity<BcbDataEntity> failrequest = new HttpEntity<>(data, null);
        result = restTemplate.postForEntity("http://localhost:"+ port +"/api/v2/imp/contrato/", failrequest, String.class);
        assertEquals(400, result.getStatusCodeValue());
    }
    @Test
    void deleteDatabase()
    {
        HttpEntity<Void> request = new HttpEntity<>(null, null);
        ResponseEntity<Void> response = restTemplate.exchange("http://localhost:"+ port +"/api/v2/imp/contrato/25", HttpMethod.DELETE, request, Void.class);
        assertEquals(200, response.getStatusCodeValue());
        response = restTemplate.exchange("http://localhost:"+ port +"/api/v2/imp/contrato/999999", HttpMethod.DELETE, request, Void.class);
        assertEquals(404, response.getStatusCodeValue());
    }
    @Test
    void updateDatabase()
    {
        HttpEntity<BcbDataEntity> request = new HttpEntity<>(data, null);
        ResponseEntity<Void> response = restTemplate.exchange("http://localhost:"+ port +"/api/v2/imp/contrato/33", HttpMethod.PUT, request, Void.class);
        assertEquals(200, response.getStatusCodeValue());
        response = restTemplate.exchange("http://localhost:"+ port +"/api/v2/imp/contrato/999999", HttpMethod.PUT, request, Void.class);
        assertEquals(404, response.getStatusCodeValue());

        BcbDataEntity faildata = data;
        faildata.setId(38L);
        HttpEntity<BcbDataEntity> failrequest = new HttpEntity<>(faildata, null);
        response = restTemplate.exchange("http://localhost:"+ port +"/api/v2/imp/contrato/999999", HttpMethod.PUT, failrequest, Void.class);
        assertEquals(400, response.getStatusCodeValue());

    }
}
