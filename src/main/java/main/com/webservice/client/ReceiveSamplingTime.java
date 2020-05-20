package main.com.webservice.client;

import com.webservice.his.ba.entity.SampleMaster;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

import javax.xml.namespace.QName;
import java.util.ArrayList;
import java.util.List;

public class ReceiveSamplingTime {
  public static void main(String args[]) {
 // String wsdlUrl="http://192.168.100.57/dthealth/web/web.DHCBARM.LIS.Service.LISService.CLS?WSDL";
 // String wsdlUrl="http://172.20.207.174:8080/icube/cxfService/WebHisService?wsdl";
   String wsdlUrl="http://172.20.207.133/icube/cxfService/WebHisService?wsdl";
  String namespaceURI="http://service.sample.sync.com/";
  String localPart="ReceiveSamplingTime";
  String str=strXml();
  Object[] obj=new Object[]{str};
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
 //  HTTPConduit conduit = (HTTPConduit) client.getConduit();
 //  HTTPClientPolicy policy = new HTTPClientPolicy();
 //  policy.setConnectionTimeout(5*1000);
 //  policy.setAllowChunking(false);
 //  policy.setReceiveTimeout(2*1000);
 //  conduit.setClient(policy);
 //  long b=System.currentTimeMillis();
 //  System.out.println((b-a)/1000);
 //  client.getOutInterceptors().add(new ClientAuthInterceptor("BarmHis", "123456"));
 //  client.getOutInterceptors().add(new LoggingOutInterceptor(new PrintWriter(new FileWriter("in.txt"))));
 //  client.getInInterceptors().add(new LoggingInInterceptor(new PrintWriter(new FileWriter("out.txt"))));
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
  String str="<Request>" + "<Collections>" + "<Collection>" + "<LabNo>20051100000002</LabNo>" + "<Rowid>C2005060005</Rowid>" + "<ExeUser>748</ExeUser>" + "<CollectionDate>2018-03-27</CollectionDate>" + "<CollectionTime>13:21:11</CollectionTime>" + "</Collection>" + "</Collections>" + "</Request>";
  return str;
  }
}