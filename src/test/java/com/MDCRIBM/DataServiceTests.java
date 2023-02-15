package com.MDCRIBM;

import com.MDCRIBM.api.model.exceptions.ObjectNotFoundException;
import com.MDCRIBM.api.model.dto.DataDTO;
import com.MDCRIBM.api.model.entity.BcbDataEntity;
import com.MDCRIBM.api.model.dto.ReportDTO;
import com.MDCRIBM.api.model.repository.DataRepository;
import com.MDCRIBM.api.service.DataService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@RunWith(SpringRunner.class)
@SpringBootTest
class DataServiceTests {

	int page = 0;
	int size = 10;
	@Autowired
	private DataService service;
	BcbDataEntity data;

	@Autowired
	private DataRepository dataRepository;

	@BeforeEach
	public void setup()
	{
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
	void insertDataBase() {
		DataDTO dto = service.insertData(data);
		assertNotNull(dto);

		Long id = dto.getId();

		DataDTO op = service.getDataById(id);
		assertNotNull(op);

		dto = op;

		assertEquals("A", dto.getMunicipio());
		assertEquals("B", dto.getNomeProduto());
		assertEquals("C", dto.getMesEmissao());
		assertEquals("D", dto.getAnoEmissao());
		assertEquals("E", dto.getCdPrograma());
		assertEquals("F", dto.getCdSubPrograma());
		assertEquals("G", dto.getCdFonteRecurso());
		assertEquals("H", dto.getCdTipoSeguro());
		assertEquals("I", dto.getCdEstado());
		assertEquals(0.00, dto.getVlCusteio());
		assertEquals("J", dto.getCdProduto());
		assertEquals("K", dto.getCdMunicipio());
		assertEquals("L", dto.getAtividade());
		assertEquals("M", dto.getCdModalidade());
		assertEquals(0.00, dto.getAreaInvest());

		service.delete(id);

		assertThrows(ObjectNotFoundException.class, ()-> service.getDataById(id));

	}
	@Test()
	void deleteDatabase()
	{
		service.delete(98L);
		assertThrows(ObjectNotFoundException.class, ()-> service.delete(98L));
	}
	@Test
	void updateDatabase() {
		Optional<BcbDataEntity> optional = dataRepository.findById(87L);
		assertTrue(optional.isPresent());

		BcbDataEntity datadb = optional.get();
		datadb = data;

		DataDTO dto = service.updateData(datadb, 87L);
		assertNotNull(dto);

		DataDTO op = service.getDataById(dto.getId());
		assertNotNull(op);

		assertEquals("A", op.getMunicipio());
		assertEquals("B", op.getNomeProduto());
		assertEquals("C", op.getMesEmissao());
		assertEquals("D", op.getAnoEmissao());
		assertEquals("E", op.getCdPrograma());
		assertEquals("F", op.getCdSubPrograma());
		assertEquals("G", op.getCdFonteRecurso());
		assertEquals("H", op.getCdTipoSeguro());
		assertEquals("I", op.getCdEstado());
		assertEquals(0.00, op.getVlCusteio());
		assertEquals("J", op.getCdProduto());
		assertEquals("K", op.getCdMunicipio());
		assertEquals("L", op.getAtividade());
		assertEquals("M", op.getCdModalidade());
		assertEquals(0.00, op.getAreaInvest());

		assertThrows(IllegalArgumentException.class, ()-> service.updateData(data, 99999L));

		setup();
		assertNull(service.updateData(data, 99999L));

	}
	@Test
	void getAllData()
	{
		List<DataDTO> data = service.getData();
		assertNotNull(data);
		assertTrue(data.get(0).getId() == 1);
	}

	@Test
	void getById()
	{
		List<DataDTO> data = service.getDataByQuery(1L, null, null,null,null,null,null,
				null,null,null,null,null,null,null,null,null, PageRequest.of(page,size));

		assertNotNull(data);
		assertEquals(1, data.size());

		data = service.getDataByQuery(99999L, null, null,null,null,null,null,
				null,null,null,null,null,null,null,null,null, PageRequest.of(page,size));

		assertTrue(data.isEmpty());
	}
	@Test
	void getBynomeProduto()
	{
		List<DataDTO> data = service.getDataByQuery(null, "BOVINOS", null,null,null,null,null,
				null,null,null,null,null,null,null,null,null, PageRequest.of(page,size));

		assertNotNull(data);
		assertEquals(10, data.size());

		data = service.getDataByQuery(null, "DESCONHECIDO", null,null,null,null,null,
				null,null,null,null,null,null,null,null,null, PageRequest.of(page,size));

		assertTrue(data.isEmpty());
	}

	@Test
	void getByCdPrograma()
	{
		List<DataDTO> data = service.getDataByQuery(null, null, "0999",null,null,null,null,
				null,null,null,null,null,null,null,null,null, PageRequest.of(page,size));

		assertNotNull(data);
		assertEquals(10, data.size());

		data = service.getDataByQuery(null, null, "00000",null,null,null,null,
				null,null,null,null,null,null,null,null,null, PageRequest.of(page,size));

		assertTrue(data.isEmpty());
	}
	@Test
	void getByCdSubPrograma()
	{
		List<DataDTO> data = service.getDataByQuery(null, null, null,"0",null,null,null,
				null,null,null,null,null,null,null,null,null, PageRequest.of(page,size));

		assertNotNull(data);
		assertEquals(10, data.size());

		data = service.getDataByQuery(null, null, null,"99999",null,null,null,
				null,null,null,null,null,null,null,null,null, PageRequest.of(page,size));

		assertTrue(data.isEmpty());
	}
	@Test
	void getByCdFonteRecurso()
	{
		List<DataDTO> data = service.getDataByQuery(null, null, null,null,"0502",null,null,
				null,null,null,null,null,null,null,null,null, PageRequest.of(page,size));

		assertNotNull(data);
		assertEquals(10, data.size());

		data = service.getDataByQuery(null, null, null,null,"999999",null,null,
				null,null,null,null,null,null,null,null,null, PageRequest.of(page,size));

		assertTrue(data.isEmpty());
	}
	@Test
	void getByCdTipoSeguro()
	{
		List<DataDTO> data = service.getDataByQuery(null, null, null,null,null,"9",null,
				null,null,null,null,null,null,null,null,null, PageRequest.of(page,size));

		assertNotNull(data);
		assertEquals(10, data.size());

		data = service.getDataByQuery(null, null, null,null,null,"99999",null,
				null,null,null,null,null,null,null,null,null, PageRequest.of(page,size));

		assertTrue(data.isEmpty());
	}
	@Test
	void getByCdEstado()
	{
		List<DataDTO> data = service.getDataByQuery(null, null, null,null,null,null,"24",
				null,null,null,null,null,null,null,null,null, PageRequest.of(page,size));

		assertNotNull(data);
		assertEquals(8, data.size());

		data = service.getDataByQuery(null, null, null,null,null,null,"999999",
				null,null,null,null,null,null,null,null,null, PageRequest.of(page,size));

		assertTrue(data.isEmpty());
	}
	@Test
	void getByCdProduto()
	{
		List<DataDTO> data = service.getDataByQuery(null, null, null,null,null,null,null,
				"4860",null,null,null,null,null,null,null,null, PageRequest.of(page,size));

		assertNotNull(data);
		assertEquals(8, data.size());

		data = service.getDataByQuery(null, null, null,null,null,null,null,
				"999999",null,null,null,null,null,null,null,null, PageRequest.of(page,size));

		assertTrue(data.isEmpty());
	}
	@Test
	void getByCdMunicipio()
	{
		List<DataDTO> data = service.getDataByQuery(null, null, null,null,null,null,null,
				null,"2888",null,null,null,null,null,null,null, PageRequest.of(page,size));

		assertNotNull(data);
		assertEquals(1, data.size());

		data = service.getDataByQuery(null, null, null,null,null,null,null,
				null,"999999",null,null,null,null,null,null,null, PageRequest.of(page,size));

		assertTrue(data.isEmpty());
	}
	@Test
	void getByCdModalidade()
	{
		List<DataDTO> data = service.getDataByQuery(null, null, null,null,null,null,null,
				null,null,"14",null,null,null,null,null,null, PageRequest.of(page,size));

		assertNotNull(data);
		assertEquals(10, data.size());

		data = service.getDataByQuery(null, null, null,null,null,null,null,
				null,null,"99999",null,null,null,null,null,null, PageRequest.of(page,size));

		assertTrue(data.isEmpty());
	}
	@Test
	void getByVlCusteio()
	{
		List<DataDTO> data = service.getDataByQuery(null, null, null,null,null,null,null,
				null,null,null,442267.7,null,null,null,null,null, PageRequest.of(page,size));

		assertNotNull(data);
		assertEquals(1, data.size());

		data = service.getDataByQuery(null, null, null,null,null,null,null,
				null,null,null,9999999.99,null,null,null,null,null, PageRequest.of(page,size));

		assertTrue(data.isEmpty());
	}
	@Test
	void getByMunicipio()
	{
		List<DataDTO> data = service.getDataByQuery(null, null, null,null,null,null,null,
				null,null,null,null,"CAMPO GRANDE",null,null,null,null, PageRequest.of(page,size));

		assertNotNull(data);
		assertEquals(1, data.size());

		data = service.getDataByQuery(null, null, null,null,null,null,null,
				null,null,null,null,"CAMPO DESCONHECIDO",null,null,null,null, PageRequest.of(page,size));

		assertTrue(data.isEmpty());
	}
	@Test
	void getByAnoEmissao()
	{
		List<DataDTO> data = service.getDataByQuery(null, null, null,null,null,null,null,
				null,null,null,null,null,"2015",null,null,null, PageRequest.of(page,size));

		assertNotNull(data);
		assertEquals(10, data.size());

		data = service.getDataByQuery(null, null, null,null,null,null,null,
				null,null,null,null,null,"2030",null,null,null, PageRequest.of(page,size));

		assertTrue(data.isEmpty());
	}
	@Test
	void getByMesEmissao()
	{
		List<DataDTO> data = service.getDataByQuery(null, null, null,null,null,null,null,
				null,null,null,null,null,null,"11",null,null, PageRequest.of(page,size));

		assertNotNull(data);
		assertEquals(6, data.size());

		data = service.getDataByQuery(null, null, null,null,null,null,null,
				null,null,null,null,null,null,"13",null,null, PageRequest.of(page,size));

		assertTrue(data.isEmpty());
	}
	@Test
	void getByAtividade()
	{
		List<DataDTO> data = service.getDataByQuery(null, null, null,null,null,null,null,
				null,null,null,null,null,null,null,"2",null, PageRequest.of(page,size));

		assertNotNull(data);
		assertEquals(10, data.size());

		data = service.getDataByQuery(null, null, null,null,null,null,null,
				null,null,null,null,null,null,null,"999999",null, PageRequest.of(page,size));

		assertTrue(data.isEmpty());
	}
	@Test
	void getByAreaInvest()
	{
		List<DataDTO> data = service.getDataByQuery(null, null, null,null,null,null,null,
				null,null,null,null,null,null,null,null,0.0, PageRequest.of(page,size));

		assertNotNull(data);
		assertEquals(10, data.size());

		data = service.getDataByQuery(null, null, null,null,null,null,null,
				null,null,null,null,null,null,null,null,9999999.99, PageRequest.of(page,size));

		assertTrue(data.isEmpty());
	}
	@Test
	void getDataByAno()
	{
		List<DataDTO> data = service.getDataByAno("2015", Pageable.ofSize(10));
		assertNotNull(data);
		assertEquals(11, data.size());
	}

	@Test
	void getRelatorio()
	{
		ReportDTO report = service.createReport("2015");
		assertNotNull(report);
	}
}
