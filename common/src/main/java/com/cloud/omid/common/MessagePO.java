package com.cloud.omid.common;

import java.io.Serializable;

public class MessagePO implements Serializable
{

    private String name;
    private boolean saved;

    public MessagePO()
    {
    }
    
    

    public MessagePO(String name, boolean saved)
    {
        this.name = name;
        this.saved = saved;
    }



    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public boolean isSaved()
    {
        return saved;
    }

    public void setSaved(boolean saved)
    {
        this.saved = saved;
    }

}
