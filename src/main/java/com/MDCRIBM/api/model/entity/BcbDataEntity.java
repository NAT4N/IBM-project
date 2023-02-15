package com.MDCRIBM.api.model.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.jetbrains.annotations.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.*;

@JsonRootName(value = "value")
@Entity
@Data
@NoArgsConstructor
@Table(name = "bcbdataentity")

public class BcbDataEntity {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @JsonProperty("Municipio")
    private String municipio;
    @NotNull
    private String nomeProduto;
    @NotNull
    @JsonProperty("MesEmissao")
    private String mesEmissao;
    @NotNull
    @JsonProperty("AnoEmissao")
    private String anoEmissao;
    @NotNull
    private String cdPrograma;
    @NotNull
    private String cdSubPrograma;
    @NotNull
    private String cdFonteRecurso;
    @NotNull
    private String cdTipoSeguro;
    @NotNull
    private String cdEstado;
    @NotNull
    @JsonProperty("VlCusteio")
    private double vlCusteio;
    @NotNull
    private String cdProduto;
    @NotNull
    private String cdMunicipio;
    @NotNull
    @JsonProperty("Atividade")
    private String atividade;
    @NotNull
    private String cdModalidade;
    @NotNull
    @JsonProperty("AreaInvest")
    private double areaInvest;

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto.replaceAll(String.valueOf((char)34),"");
    }
}
