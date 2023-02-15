package com.MDCRIBM.api.service;

import com.MDCRIBM.api.model.exceptions.ObjectNotFoundException;
import com.MDCRIBM.api.model.dto.DataDTO;
import com.MDCRIBM.api.model.entity.BcbDataEntity;
import com.MDCRIBM.api.model.dto.ReportDTO;
import com.MDCRIBM.api.model.repository.DataRepository;
import org.jetbrains.annotations.NotNull;
import org.modelmapper.internal.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class DataService {

    @Autowired
    private DataRepository dataRepository;

    public DataDTO getDataById(long id) {
        return dataRepository.findById(id).map(DataDTO::create).orElseThrow(() -> new ObjectNotFoundException("Registro não encontrado"));
    }
    public List<DataDTO> getData() {
        return dataRepository.findAll().stream().map(DataDTO::create).collect(Collectors.toList());
    }
    public List<DataDTO> getDataByAno(String AnoEmissao, Pageable pageable) {
        return dataRepository.findByAnoEmissao(AnoEmissao).stream().map(DataDTO::create).collect(Collectors.toList());
    }

    public List<DataDTO> getDataByQuery(Long id, String nomeProduto, String cdPrograma, String cdSubPrograma, String cdFonteRecurso, String cdTipoSeguro, String cdEstado,
                                        String cdProduto, String cdMunicipio, String cdModalidade, Double vlCusteio, String municipio, String AnoEmissao, String mesEmissao,
                                        String atividade, Double areaInvest, Pageable pageable)
    {
        return dataRepository.findByMerge(id, nomeProduto, cdPrograma, cdSubPrograma, cdFonteRecurso, cdTipoSeguro,
                        cdEstado, cdProduto, cdMunicipio, cdModalidade, vlCusteio, municipio, AnoEmissao, mesEmissao, atividade, areaInvest, pageable)
                .stream().map(DataDTO::create).collect(Collectors.toList());
    }
    public ReportDTO createReport(String ano)
    {
        BigDecimal bovinos, cafe, ovinos, caprinos, avicultura;
        List<DataDTO> list = dataRepository.findByAnoEmissao(ano).stream().map(DataDTO::create).collect(Collectors.toList());
        //1300
        bovinos = BigDecimal.valueOf(list.stream().filter(x -> x.getCdProduto().equals("1300")).mapToDouble(x -> x.getVlCusteio()).sum());
        //1580
        cafe = BigDecimal.valueOf(list.stream().filter(x -> x.getCdProduto().equals("1580")).mapToDouble(x -> x.getVlCusteio()).sum());
        //5580
        ovinos = BigDecimal.valueOf(list.stream().filter(x -> x.getCdProduto().equals("5580")).mapToDouble(x -> x.getVlCusteio()).sum());
        //1920
        caprinos = BigDecimal.valueOf(list.stream().filter(x -> x.getCdProduto().equals("1920")).mapToDouble(x -> x.getVlCusteio()).sum());
        //1080
        avicultura = BigDecimal.valueOf(list.stream().filter(x -> x.getCdProduto().equals("1080")).mapToDouble(x -> x.getVlCusteio()).sum());

        return new ReportDTO(bovinos, ovinos, caprinos, cafe, avicultura);
    }
    public DataDTO insertData(@NotNull BcbDataEntity data) {
        Assert.isNull(data.getId());

        Assert.state(!data.getNomeProduto().isEmpty());
        Assert.state(!data.getCdPrograma().isEmpty());
        Assert.state(!data.getCdSubPrograma().isEmpty());
        Assert.state(!data.getCdFonteRecurso().isEmpty());
        Assert.state(!data.getCdTipoSeguro().isEmpty());
        Assert.state(!data.getCdEstado().isEmpty());
        Assert.state(!data.getCdProduto().isEmpty());
        Assert.state(!data.getMunicipio().isEmpty());
        Assert.state(!data.getCdModalidade().isEmpty());
        Assert.state(!data.getCdMunicipio().isEmpty());
        Assert.state(!data.getMesEmissao().isEmpty());
        Assert.state(!data.getAnoEmissao().isEmpty());
        Assert.state(!data.getAtividade().isEmpty());

        return DataDTO.create(dataRepository.save(data));
    }

    public DataDTO updateData(BcbDataEntity data, Long id) {
        Assert.isNull(data.getId());
        data.setId(id);

        Optional<BcbDataEntity> optional = dataRepository.findById(id);

        if(optional.isPresent())
        {
            BcbDataEntity db = optional.get();

            db.setMunicipio(data.getMunicipio());
            db.setNomeProduto(data.getNomeProduto());
            db.setMesEmissao(data.getMesEmissao());
            db.setAnoEmissao(data.getAnoEmissao());
            db.setCdPrograma(data.getCdPrograma());
            db.setCdSubPrograma(data.getCdSubPrograma());
            db.setCdTipoSeguro(data.getCdTipoSeguro());
            db.setCdFonteRecurso(data.getCdFonteRecurso());
            db.setCdEstado(data.getCdEstado());
            db.setVlCusteio(data.getVlCusteio());
            db.setCdProduto(data.getCdProduto());
            db.setCdMunicipio(data.getCdMunicipio());
            db.setAtividade(data.getAtividade());
            db.setCdModalidade(data.getCdModalidade());
            db.setAreaInvest(data.getAreaInvest());

            return DataDTO.create(dataRepository.save(data));
        }else {return null;}
    }
    public void delete(Long id) {
        if (getDataById(id) != null) {
            dataRepository.deleteById(id);
        }
    }

}
