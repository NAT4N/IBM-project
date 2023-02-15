package com.MDCRIBM.api.controller;

import com.MDCRIBM.api.model.dto.DataDTO;
import com.MDCRIBM.api.model.entity.BcbDataEntity;
import com.MDCRIBM.api.model.dto.ReportDTO;
import com.MDCRIBM.api.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/api/v2/imp/contrato")
public class DataController {

    static final String SIZE = "10";
    static final String PAGE = "0";

    @Autowired
    private DataService service;

    @GetMapping("/relatorio/{ano}")
    public ReportDTO createReport(@PathVariable("ano") String ano, @RequestParam(value = "page", defaultValue = "0") Integer page,
                                  @RequestParam(value = "size", defaultValue = "10000") Integer size)
    {
        return service.createReport(ano);
    }
    @GetMapping()
    public ResponseEntity getDataByQuery(@RequestParam(value = "id",required = false) Long id,                        @RequestParam(value = "nomeproduto", required = false) String nomeProduto,
                                     @RequestParam(value = "cdprograma", required = false) String cdPrograma,         @RequestParam(value = "cdsubprograma", required = false) String cdSubPrograma,
                                     @RequestParam(value = "cdfonterecurso", required = false) String cdFonteRecurso, @RequestParam(value = "cdtiposeguro", required = false) String cdTipoSeguro,
                                     @RequestParam(value = "cdestado", required = false) String cdEstado,             @RequestParam(value = "cdproduto", required = false) String cdProduto,
                                     @RequestParam(value = "cdmunicipio", required = false) String cdMunicipio,       @RequestParam(value = "cdmodalidade", required = false) String cdModalidade,
                                     @RequestParam(value = "vlcusteio", required = false) Double vlCusteio,           @RequestParam(value = "municipio",required = false) String municipio,
                                     @RequestParam(value = "anoemissao",required = false) String anoEmissao,          @RequestParam(value = "mesemissao",required = false) String mesEmissao,
                                     @RequestParam(value = "atividade",required = false) String atividade,            @RequestParam(value = "areainvest",required = false) Double areaInvest,
                                     @RequestParam(value = "page", defaultValue = PAGE) Integer page,                 @RequestParam(value = "size", defaultValue = SIZE) Integer size)
    {
        List<DataDTO> data = service.getDataByQuery(id, nomeProduto, cdPrograma, cdSubPrograma, cdFonteRecurso, cdTipoSeguro, cdEstado, cdProduto, cdMunicipio,
                cdModalidade, vlCusteio, municipio, anoEmissao, mesEmissao, atividade, areaInvest, PageRequest.of(page,size));
        return data.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok((data));
    }
    @PostMapping
    public ResponseEntity insertData(@RequestBody @Validated BcbDataEntity data) {
        DataDTO dataDTO = service.insertData(data);
        URI location = getUri(dataDTO.getId());
        return ResponseEntity.created(location).build();
    }
    private URI getUri(Long id)
    {
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
    }

    @PutMapping("/{id}")
    public ResponseEntity updateData(@RequestBody BcbDataEntity data, @PathVariable("id") Long id)
    {
        DataDTO dataDTO = service.updateData(data, id);
        return dataDTO != null ? ResponseEntity.ok(dataDTO) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteData(@PathVariable("id") Long id)
    {
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}
