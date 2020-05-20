package com.webservice.his.ba.service.impl;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * Created by admin on 2020/5/5.
 */
@Service("HCJoinRoute")
@Profile("SZ_HC")
public class HCJoinRoute extends HisJoinRoute{
    @Override
    public String test001() {
        return "杭创人民医院";
    }
}
