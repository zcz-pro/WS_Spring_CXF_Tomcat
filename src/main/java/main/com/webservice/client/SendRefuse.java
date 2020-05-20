package main.com.webservice.client;

import com.webservice.his.ba.entity.SampleMaster;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

import javax.xml.namespace.QName;
import java.util.ArrayList;
import java.util.List;

public class SendRefuse {
     public static void main(String args[]) {
      //TODO 300并发测试
          String wsdlUrl="http://10.17.200.60/csp/hsb/DHC.Published.YJDLISWebService.BS.YJDLISWebService.CLS?WSDL";
          String namespaceURI="http://tempuri.org";
          String localPart="ESBWebInterface";
          String str=strXml();
          Object[] obj=new Object[]{"MSG0162",str};
          List<SampleMaster> entList=new ArrayList<SampleMaster>();
          try {
               String result= invoke(wsdlUrl,namespaceURI,localPart,obj);
               System.out.println(result);
          } catch (Exception e) {
               e.printStackTrace();
          }
     }

     public static String invoke(String wsdlUrl,String namespaceURI,String localPart,Object[] obj) throws Exception{
         JaxWsDynamicClientFactory clientFactory = JaxWsDynamicClientFactory.newInstance();
         Client client =null;
         Object[] result = new Object[0];
         try {
          long a=System.currentTimeMillis();
          client= clientFactory.createClient(wsdlUrl);
          try {
          //如果有命名空间的话
          QName operationName = new QName(namespaceURI,localPart); //如果有命名空间需要加上这个，第一个参数为命名空间名称，第二个参数为WebService方法名称
          result = client.invoke(operationName,obj);//后面为WebService请求参数数组
          } catch (Exception e) {
           e.printStackTrace();
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
    // JAXBUtils
     public static String strXml(){
     String str="<Request><Refuses><Refuse><LabNo></LabNo><Rowid>7757257_104</Rowid><RefuseReason>空管</RefuseReason><ExeUser>748</ExeUser><RefuseDate>2018-03-27</RefuseDate><RefuseTime>14:07:28</RefuseTime></Refuse></Refuses></Request>";
     return str;
     }
}