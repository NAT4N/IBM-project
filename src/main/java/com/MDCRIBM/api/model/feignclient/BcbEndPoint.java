package com.MDCRIBM.api.model.feignclient;

import com.MDCRIBM.api.model.entity.BcbRootEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "bcb", url="https://olinda.bcb.gov.br/olinda/servico/SICOR/versao/v2/odata/InvestMunicipioProduto?$top=100&$format=json")
public interface BcbEndPoint {

    @RequestMapping(method = RequestMethod.GET)
    public BcbRootEntity getBcbData();

}
