package com.omid.cloud.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TrxApi
{

    private static final Logger log = LoggerFactory.getLogger(TrxApi.class);
    
    @GetMapping("/trxApi")
    public String getApi()
    {
        log.info("trx api");
        return "trx";
    }

}