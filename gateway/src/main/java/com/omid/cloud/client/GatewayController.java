package com.omid.cloud.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class GatewayController
{

    private static final Logger log = LoggerFactory.getLogger(GatewayController.class);
    
    @Autowired
    TrxProxy trxProxy;
    
    @GetMapping("/test")
    @HystrixCommand(fallbackMethod="fail")
    public ResponseEntity<String> test()
    {
        log.info("ZUUL TRX PROXY");
        String test = trxProxy.test();
        return ResponseEntity.ok(test);
    }
    
    
    public ResponseEntity<String> fail()
    {
        log.info("Hystrix method callback");
        return ResponseEntity.ok("failed");
    }
    
    
}
