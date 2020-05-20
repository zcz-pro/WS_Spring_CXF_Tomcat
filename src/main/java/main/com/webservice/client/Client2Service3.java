package main.com.webservice.client;

import com.webservice.his.ba.entity.SampleMaster;
import com.webservice.his.ba.interceptor.ClientAuthInterceptor;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import javax.xml.namespace.QName;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Client2Service3 {
    public static void main(String args[]) {
        String wsdlUrl="http://10.17.200.60/csp/hsb/DHC.Published.YJDLISWebService.BS.YJDLISWebService.CLS?WSDL";
        String namespaceURI="http://tempuri.org";
        String localPart="ESBWebInterface";
        Object[] obj=new Object[]{"MSG0163","aaaa"};
        List<SampleMaster> entList=new ArrayList<SampleMaster>();
        try {
           String result= invoke(wsdlUrl,namespaceURI,localPart,obj);
            System.out.println(result);
            List<SampleMaster> a=analysisSampleXml(entList,result);
            System.out.println(a.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String invoke(String wsdlUrl,String namespaceURI,String localPart,Object[] obj) throws Exception{
        JaxWsDynamicClientFactory clientFactory = JaxWsDynamicClientFactory.newInstance();
        Client client =null;
        Object[] result = new Object[0];
        try {
            client= clientFactory.createClient(wsdlUrl);
            client.getOutInterceptors().add(new ClientAuthInterceptor("BarmHis", "123456"));
            client.getOutInterceptors().add(new LoggingOutInterceptor(new PrintWriter(new FileWriter("in.txt"))));
            client.getInInterceptors().add(new LoggingInInterceptor(new PrintWriter(new FileWriter("out.txt"))));
            try {
                //如果有命名空间的话
                QName operationName = new QName(namespaceURI,localPart); //如果有命名空间需要加上这个，第一个参数为命名空间名称，第二个参数为WebService方法名称
                result = client.invoke(operationName,obj);//后面为WebService请求参数数组
            } catch (Exception e) {
                String errMsg = "WebService发生异常！";
                result = new Object[]{errMsg};
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            client.close();
        }
        if(result.length<0 || result[0]==null){
            return null;
        }
        return (String)result[0];
    }

    private static List<SampleMaster> analysisSampleXml(List<SampleMaster> entList, String xml) throws DocumentException {
        Document document = DocumentHelper.parseText(xml);
        Element bookStore = document.getRootElement();
        String resultCode=bookStore.elementTextTrim("ResultCode");
        String resultContent=bookStore.elementTextTrim("ResultContent");
        Iterator<Element> iter = bookStore.elementIterator("Orders");
        // 遍历returnContent节点
        while (iter.hasNext()) {
            Element recordEle = iter.next();
            Iterator<Element> iter2 = recordEle.elementIterator("OrderList");
            while (iter2.hasNext()) {
                Element recordEle2 = iter2.next();
                String ordID = recordEle.elementTextTrim("OrdID");
                String labNo = recordEle2.elementTextTrim("LabNo");
                String arcimCode = recordEle2.elementTextTrim("ArcimCode");
                String arcimDesc = recordEle2.elementTextTrim("ArcimDesc");
                String orderQty = recordEle2.elementTextTrim("OrderQty");
                String billCode = recordEle2.elementTextTrim("BillCode");
                String specimenTypeCode = recordEle2.elementTextTrim("SpecimenTypeCode");
                String specimenTypeDesc = recordEle2.elementTextTrim("SpecimenTypeDesc");
                String spVolume = recordEle2.elementTextTrim("SpVolume");

                SampleMaster sample = new SampleMaster();
                //添加病人信息
                sample.setLabNo(labNo);//申请单-流水号-每次来医院唯一号
                sample.setOrdID(ordID);
                sample.setArcimCode(arcimCode);//病例号
                sample.setArcimDesc(arcimDesc);
                sample.setOrderQty(orderQty);
                sample.setBillCode(billCode);
                sample.setSpecimenTypeCode(specimenTypeCode);
                sample.setSpecimenTypeDesc(specimenTypeDesc);
                sample.setSpVolume(spVolume);
                entList.add(sample);
            }
        }
        return entList;
    }
}