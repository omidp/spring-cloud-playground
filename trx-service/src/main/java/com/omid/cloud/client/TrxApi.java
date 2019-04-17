package com.omid.cloud.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.omid.common.TrxVO;


@RestController
public class TrxApi
{

    private static final Logger log = LoggerFactory.getLogger(TrxApi.class);
    
    @GetMapping("/trxApi")
    public ResponseEntity<TrxVO> getApi()
    {
        log.info("trx api");
        return ResponseEntity.ok(new TrxVO("12344", "transaction 1"));
    }

}