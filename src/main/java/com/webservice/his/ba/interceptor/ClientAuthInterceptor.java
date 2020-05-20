package com.webservice.his.ba.interceptor;

import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.headers.Header;
import org.apache.cxf.helpers.DOMUtils;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.namespace.QName;
import java.util.List;

/**
 * Created by admin on 2020/4/8.
 */
public class ClientAuthInterceptor extends AbstractPhaseInterceptor<SoapMessage> {
    private String userId;
    private String userPass;

    public ClientAuthInterceptor(String userId, String userPass) {
        super(Phase.PREPARE_SEND);
        this.userId = userId;
        this.userPass = userPass;
    }
    @Override
    public void handleMessage(SoapMessage message) throws Fault {
        List<Header> headers = message.getHeaders();

        Document doc = DOMUtils.createDocument();

        Element ele = doc.createElement("authHeader");

        Element name = doc.createElement("userId");
        name.setTextContent(userId);

        Element password = doc.createElement("userPass");
        password.setTextContent(userPass);

        ele.appendChild(name);
        ele.appendChild(password);

        /**
         * 生成的XML文档
         * <authHeader>
         *      <userId>lzw</userId>
         *      <userPass>123456</userPass>
         * </authHeader>
         */

        headers.add(new Header(new QName(""), ele));
    }
}
