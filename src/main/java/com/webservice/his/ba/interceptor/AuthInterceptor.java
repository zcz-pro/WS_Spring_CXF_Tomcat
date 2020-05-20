package com.webservice.his.ba.interceptor;

import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.headers.Header;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.List;

/**
 * @Description: 自定义拦截器在哪个阶段实施拦截
 * @author lzw
 * @date 2013年11月6日 上午11:54:48
 * @version V1.0
 * @Copyright (c)
 */
public class AuthInterceptor extends AbstractPhaseInterceptor<SoapMessage> {

    public AuthInterceptor() {
        //拦截器在调用方法之前拦截SOAP消息
        super(Phase.PRE_INVOKE);
    }

    /**
     * @Description: 拦截器操作
     * @param msg 被拦截到的SOAP消息
     * @throws Fault
     */
    @Override
    public void handleMessage(SoapMessage msg) throws Fault {

        System.out.println("=====自定义拦截器=======");
        //获取SOAP消息的Header
        List<Header> headers = msg.getHeaders();
        //如果没有Header
        if(headers == null || headers.size() < 1) {
            throw new Fault(new IllegalArgumentException("没有Header,拦截器实施拦截"));
        }
        //获取Header携带是用户和密码信息
        Header firstHeader = headers.get(0);
        Element ele = (Element) firstHeader.getObject();

        NodeList userIdEle = ele.getElementsByTagName("userId");
        NodeList userPassEle = ele.getElementsByTagName("userPass");

        if (userIdEle.getLength() != 1) {
            throw new Fault(new IllegalArgumentException("用户Id格式不对"));
        }

        if (userPassEle.getLength() != 1) {
            throw new Fault(new IllegalArgumentException("用户密码格式不对"));
        }

        //获取元素的文本内容
        String userId = userIdEle.item(0).getTextContent();
        String userPass = userPassEle.item(0).getTextContent();

        if (!userId.equals("BarmHis") || !userPass.equals("123456")) {
            throw new Fault(new IllegalArgumentException("用户和密码不正确"));
        }
    }

}