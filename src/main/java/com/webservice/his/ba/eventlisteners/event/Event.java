package com.webservice.his.ba.eventlisteners.event;

import java.util.EventObject;
import java.util.Map;

public class Event extends EventObject {
    public static Event EmptyEvent = new Event();
    private static final long serialVersionUID = -2794147263937359159L;
    private final long timestamp;
    public static final String eventServerID = "event_thread_server";
    public static final String eventServerDESC = "事件进程服务";
    private String appId;
    private String sessionId;
    private Map<String, Object> requestAttributesMap;

    public Event(Object source) {
        super(source);
        this.timestamp = System.currentTimeMillis();
    }

    private Event() {
        super(new Object());
        this.timestamp = System.currentTimeMillis();
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public String getAppId() {
        return this.appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getSessionId() {
        return this.sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public Map<String, Object> getRequestAttributesMap() {
        return this.requestAttributesMap;
    }

    public void setRequestAttributesMap(Map<String, Object> requestAttributesMap) {
        this.requestAttributesMap = requestAttributesMap;
    }
    @Override
    public String toString() {
        return this.getClass() + "[appId=" + this.appId + ", timestamp=" + this.timestamp + "]";
    }
}