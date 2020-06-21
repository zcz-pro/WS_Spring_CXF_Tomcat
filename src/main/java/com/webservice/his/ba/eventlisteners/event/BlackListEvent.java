package com.webservice.his.ba.eventlisteners.event;

import org.springframework.context.ApplicationEvent;

/**
 * Created by admin on 2020/6/12.
 */
public class BlackListEvent extends ApplicationEvent {
    private final String address;
    private final String test;

    public BlackListEvent(Object source, String address, String test) {
        super(source);
        this.address = address;
        this.test = test;
        System.out.println("我是【BlackList】事件");
    }

    public String getAddress() {
        return address;
    }

    public String getTest() {
        return test;
    }
}