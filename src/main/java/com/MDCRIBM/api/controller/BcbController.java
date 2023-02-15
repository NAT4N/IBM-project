package com.MDCRIBM.api.controller;

import com.MDCRIBM.api.model.feignclient.BcbEndPoint;
import com.MDCRIBM.api.model.entity.BcbRootEntity;
import com.MDCRIBM.api.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v2/imp/bcbapi")
public class BcbController {

    @Autowired
    private BcbEndPoint bcbEndPoint;

    @Autowired
    private DataService service;

    @GetMapping
    public BcbRootEntity getBcbDataAPI()
    {
        return bcbEndPoint.getBcbData();
    }

    @GetMapping("/update")
    public ResponseEntity updateDatabase() {
        bcbEndPoint.getBcbData().getValue().stream().forEach(x -> service.insertData(x));
        return ResponseEntity.ok("Banco da dados local atualizado com sucesso !");
    }

}
