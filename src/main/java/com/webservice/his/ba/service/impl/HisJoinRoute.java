package com.webservice.his.ba.service.impl;

import com.webservice.his.ba.service.IHisJoinRoute;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * Created by admin on 2020/5/5.
 */
@Service("HisJoinRoute")
@Profile("SZ_HIS")
public abstract class HisJoinRoute implements IHisJoinRoute {
    @Override
    public String test001() {
        return null;
    }
}
