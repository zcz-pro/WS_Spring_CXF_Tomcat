package com.webservice.his.ba.eventlisteners.listeners;

import com.webservice.his.ba.eventlisteners.event.BlackListEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * Created by admin on 2020/6/12.
 */
@Component
public class BlackListNotifier{

    @EventListener
    public void aaaa(BlackListEvent event) {
//        System.out.println("我是【Notifier】正在监听事件【BlackList】");
//        System.out.println(event);
        System.out.println(this);
        // notify appropriate parties via notificationAddress...
//        System.out.println(event.getAddress());
//        System.out.println(event.getTest());
    }
}
