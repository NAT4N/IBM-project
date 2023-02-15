package com.MDCRIBM.api.model.repository;

import com.MDCRIBM.api.model.entity.BcbDataEntity;
import feign.Param;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface DataRepository extends JpaRepository<BcbDataEntity, Long> {
    List<BcbDataEntity> findByAnoEmissao (String anoEmissao);

    @Query(value = "SELECT * FROM bcbdataentity WHERE " +
            "(:id is null or id= :id)"+
            "AND (:nomeProduto is null or nomeProduto= :nomeProduto)" +
            "AND (:cdPrograma is null or cdPrograma = :cdPrograma)" +
            "AND (:cdSubPrograma is null or cdSubPrograma = :cdSubPrograma)" +
            "AND (:cdFonteRecurso is null or cdFonteRecurso = :cdFonteRecurso)" +
            "AND (:cdTipoSeguro is null or cdTipoSeguro = :cdTipoSeguro)" +
            "AND (:cdEstado is null or cdEstado = :cdEstado)" +
            "AND (:cdProduto is null or cdProduto= :cdProduto)" +
            "AND (:cdMunicipio is null or cdMunicipio= :cdMunicipio) " +
            "AND (:cdModalidade is null or cdModalidade= :cdModalidade) " +
            "AND (:VlCusteio is null or vlCusteio= :VlCusteio) " +
            "AND (:Municipio is null or Municipio= :Municipio) " +
            "AND (:AnoEmissao is null or AnoEmissao= :AnoEmissao)" +
            "AND (:MesEmissao is null or MesEmissao= :MesEmissao)" +
            "AND (:Atividade is null or Atividade= :Atividade)" +
            "AND (:AreaInvest is null or AreaInvest= :AreaInvest)" , nativeQuery = true)
    List<BcbDataEntity> findByMerge(
            @Param("id") Long id,
            @Param("nomeProduto") String nomeProduto,
            @Param("cdPrograma") String cdPrograma,
            @Param("cdSubPrograma") String cdSubPrograma,
            @Param("cdFonteRecurso") String cdFonteRecurso,
            @Param("cdTipoSeguro") String cdTipoSeguro,
            @Param("cdEstado") String cdEstado,
            @Param("cdProduto") String cdProduto,
            @Param("cdMunicipio") String cdMunicipio,
            @Param("cdModalidade") String cdModalidade,
            @Param("vlCusteio") Double VlCusteio,
            @Param("municipio") String Municipio,
            @Param("anoEmissao") String AnoEmissao,
            @Param("mesEmissao") String MesEmissao,
            @Param("atividade") String Atividade,
            @Param("areaInvest") Double AreaInvest,
            Pageable pageable);
}
