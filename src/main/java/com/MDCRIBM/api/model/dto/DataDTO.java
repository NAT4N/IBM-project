package com.MDCRIBM.api.model.dto;

import com.MDCRIBM.api.model.entity.BcbDataEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.modelmapper.ModelMapper;


@Data
public class DataDTO {

    private Long id;
    @JsonProperty("Municipio")
    private String municipio;
    private String nomeProduto;
    @JsonProperty("MesEmissao")
    private String mesEmissao;
    @JsonProperty("AnoEmissao")
    private String anoEmissao;
    private String cdPrograma;
    private String cdSubPrograma;
    private String cdFonteRecurso;
    private String cdTipoSeguro;
    private String cdEstado;
    @JsonProperty("VlCusteio")
    private double vlCusteio;
    private String cdProduto;
    private String cdMunicipio;
    @JsonProperty("Atividade")
    private String atividade;
    private String cdModalidade;
    @JsonProperty("AreaInvest")
    private double areaInvest;

    public static DataDTO create(BcbDataEntity bcbDataEntity)
    {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(bcbDataEntity, DataDTO.class);
    }
}
