package com.cloud.omid.common;

import java.io.Serializable;

public class TrxVO implements Serializable
{

    private String id;
    private String name;

    public TrxVO(String id, String name)
    {
        this.id = id;
        this.name = name;
    }

    public TrxVO()
    {
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

}
