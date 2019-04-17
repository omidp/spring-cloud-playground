package com.omid.cloud.client;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import com.cloud.omid.common.TrxVO;

@FeignClient(name = "TRX-SERVICE")
@RibbonClient(name = "TRX-SERVICE")
@Service
public interface TrxProxy
{
    @GetMapping("/trxApi")
    TrxVO test();

}
