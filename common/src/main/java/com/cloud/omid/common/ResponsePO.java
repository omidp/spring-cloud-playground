package com.cloud.omid.common;

import java.io.Serializable;

public class ResponsePO implements Serializable
{

    private String nextChannel;
    private Long data;

    public ResponsePO()
    {
    }

    public ResponsePO(String nextChannel, Long data)
    {
        this.nextChannel = nextChannel;
        this.data = data;
    }

    public String getNextChannel()
    {
        return nextChannel;
    }

    public void setNextChannel(String nextChannel)
    {
        this.nextChannel = nextChannel;
    }

    public Long getData()
    {
        return data;
    }

    public void setData(Long data)
    {
        this.data = data;
    }

}
