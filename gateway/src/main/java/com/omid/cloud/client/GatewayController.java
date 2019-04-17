package com.omid.cloud.client;

import java.util.Map;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.omid.common.TrxVO;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class GatewayController
{

    private static final Logger log = LoggerFactory.getLogger(GatewayController.class);

    @Autowired
    GraphQLProxy graphQLProxy;
    
    @Autowired
    TrxProxy trxProxy;

    @PostMapping("/graphql")
    public ResponseEntity<Map<String, Object>> test(@RequestBody Map<String, Object> requestBody)
    {
        log.info("ZUUL GRAPHQL PROXY");
        Map<String, Object> resMap = graphQLProxy.call(requestBody);
        log.info("GQL RES : {}", resMap);
        JSONObject res = new JSONObject(resMap);
        if (res.has("errors"))
        {
            JSONObject json = res.getJSONArray("errors").getJSONObject(0);
            if (json.has("message"))
            {
                String msg = json.getString("message");
                if (msg != null && msg.length() > 0)
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resMap);
            }
        }
        return ResponseEntity.ok(resMap);
    }

    @GetMapping("/trxApi")
    @HystrixCommand(fallbackMethod = "fail")
    public ResponseEntity<TrxVO> trxApi()
    {
        log.info("ZUUL trxApi");
        TrxVO test = trxProxy.test();
        log.info("msg {}", test);
        return ResponseEntity.ok(test);
    }

    @GetMapping("/ping")
    public ResponseEntity<String> ping()
    {
        log.info("ZUUL PING");
        return ResponseEntity.ok("pong");
    }

    public ResponseEntity<String> fail()
    {
        log.info("Hystrix method callback");
        return ResponseEntity.ok("failed");
    }

}
