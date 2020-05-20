package com.webservice.his.ba;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.junit.Test;

import java.io.File;
import java.util.Iterator;
import java.util.Random;

/**
 * Created by admin on 2020/4/17.
 */
public class AnalysisXml {
    @Test
    public void test(){
        String pdfName=System.currentTimeMillis()+String.format("%04d",new Random().nextInt(9999))+".pdf";
        System.out.println(pdfName);
        String str=getXmlStr();
        try {
            analysisBatchCodeXml(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test02(){
        Document document = DocumentHelper.createDocument();
        // 2、创建根节点rss
        Element root = document.addElement("viewconfig");
//        root.elementText("<>/\\122344");
        root.addElement("field01").addText(null);
        Element channel = root.addElement("querys");
        String xmlStr=document.getRootElement().asXML();
        System.out.println(xmlStr);

        try {
            Document document2 = DocumentHelper.parseText(xmlStr);
            Element bookStore = document2.getRootElement();
            String fondsId=bookStore.elementTextTrim("field01");
            System.out.println(fondsId);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        StringBuilder msgBody = new StringBuilder();
        String stu="a"==null?"":"b";
        msgBody.append("<Units>"+stu+"</Units>");// 单位
        System.out.println(msgBody.toString());
    }

    //解析
    private String analysisBatchCodeXml(String result) throws Exception {
        File file=new File("D:\\uploadPdf\\");
        if(!file.exists()){
            file.mkdirs();
        }
        Document document = DocumentHelper.parseText(result);
        Element bookStore = document.getRootElement();
        String carryLoc=bookStore.elementTextTrim("CarryLoc");
        String transDate=bookStore.elementTextTrim("TransDate");
        String transTime=bookStore.elementTextTrim("TransTime");
        String transUserCode=bookStore.elementTextTrim("TransUserCode");
        String transUserName=bookStore.elementTextTrim("TransUserName");
        String transContainer=bookStore.elementTextTrim("TransContainer");
        String transCount=bookStore.elementTextTrim("TransCount");
        String deliveryUser=bookStore.elementTextTrim("DeliveryUser");
        String deliveryDateTime=bookStore.elementTextTrim("DeliveryDateTime");
        /*Element infos=bookStore.element("CarryDetailInfoList");
        Iterator<Element> iter=infos.elementIterator("CarryDetailInfo");
        while (iter.hasNext()){
            Element info=iter.next();
            String LabNo=info.elementTextTrim("LabNo");
            String OrdName=info.elementTextTrim("OrdName");
            String SpecName=info.elementTextTrim("SpecName");
            String LabUser=info.elementTextTrim("LabUser");
            String ReceiveDateTime=info.elementTextTrim("ReceiveDateTime");
        }*/
        Iterator<Element> iters = bookStore.elementIterator("CarryDetailInfoList");
		while (iters.hasNext()){
			Element infos=iters.next();
			Iterator<Element> iter=infos.elementIterator("CarryDetailInfo");
			while (iter.hasNext()){
				Element info=iter.next();
				String LabNo=info.elementTextTrim("LabNo");
				String OrdName=info.elementTextTrim("OrdName");
				String SpecName=info.elementTextTrim("SpecName");
				String LabUser=info.elementTextTrim("LabUser");
				String ReceiveDateTime=info.elementTextTrim("ReceiveDateTime");
			}
		}
        return null;
    }

    private String getXmlStr(){
        String str="<CarryInfo>\n" + "<CarryLoc>产科病区</CarryLoc>\n" + "<TransDate>2020-02-18</TransDate>\n" + "<TransTime>17:32:35</TransTime>\n" + "<TransUserCode>632</TransUserCode>\n" + "<TransUserName>吴妙琴</TransUserName>\n" + "<TransContainer>9</TransContainer>\n" + "<TransCount>1</TransCount>\n" + "<DeliveryUser>周澎洋</DeliveryUser>\n" + "<DeliveryDateTime>2020-02-19 11:45:16</DeliveryDateTime>\n" + "<CarryDetailInfoList>\n" + "<CarryDetailInfo>\n" + "<LabNo>20020600433</LabNo>\n" + "<OrdName>尿液分析(急)</OrdName>\n" + "<SpecName>尿液</SpecName>\n" + "<LabUser/>\n" + "<ReceiveDateTime></ReceiveDateTime>\n" + "</CarryDetailInfo>\n" + "</CarryDetailInfoList>\n" + "</CarryInfo>";
        return str;
    }
}
