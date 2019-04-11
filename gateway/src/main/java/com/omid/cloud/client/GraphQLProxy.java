package com.omid.cloud.client;

import java.util.Map;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "GRAPHQL-SERVICE")
@RibbonClient(name = "GRAPHQL-SERVICE")
@Service
public interface GraphQLProxy
{
    @PostMapping("/graphql")
    Map<String, Object> call(@RequestBody Map<String, Object> reqBody);

}
