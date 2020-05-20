package com.webservice.his.ba.service.impl;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * Created by admin on 2020/5/5.
 */
@Service("BARMJoinRoute")
@Profile("SZ_BARM")
public class BARMJoinRoute extends HisJoinRoute{
    @Override
    public String test001() {
        return "宝安人民医院";
    }
}
