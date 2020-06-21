package com.webservice.his.ba.eventlisteners;

import com.webservice.his.ba.eventlisteners.event.ListenEvent;
import com.webservice.his.ba.eventlisteners.event.UpdateMemberEvent;
import org.springframework.stereotype.Component;

@Component
public class EventListenersDemo {
    @ListenEvent(event = UpdateMemberEvent.class, async = true)
    public void UpdateMemberEvent(UpdateMemberEvent event) throws Exception {
        System.out.println("人员修改事件");
    }
}
