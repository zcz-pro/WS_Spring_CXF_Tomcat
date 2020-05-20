package main.com.webservice.client;

import com.webservice.his.ba.entity.SampleMaster;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;

import javax.xml.namespace.QName;
import java.util.ArrayList;
import java.util.List;

public class BackReport {
 public static void main(String args[]) {
 String wsdlUrl="http://192.168.100.57/dthealth/web/web.DHCBARM.LIS.Service.LISService.CLS?WSDL";
// String wsdlUrl="http://172.20.207.174:8080/icube/cxfService/WebHisService?wsdl";
// String wsdlUrl="http://172.20.201.226/icube/cxfService/WebHisService?wsdl";
 String namespaceURI="http://tempuri.org";
 String localPart="SendReportMsg";
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
//  client= ClientProxy.getClient("localPart");
  HTTPConduit conduit = (HTTPConduit) client.getConduit();
  HTTPClientPolicy policy = new HTTPClientPolicy();
  policy.setConnectionTimeout(5*1000);
//  policy.setAllowChunking(false);
  policy.setReceiveTimeout(2*1000);
  conduit.setClient(policy);
  long b=System.currentTimeMillis();
  System.out.println((b-a)/1000);
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
 String str="<Request><ReportMsg><OrdID>C2004260008</OrdID><LabNo></LabNo><ReportID></ReportID><ReportUrl></ReportUrl><RecUser>licaih3</RecUser><RecDate>2020-04-26</RecDate><RecTime>19:22:28</RecTime><EntryUser></EntryUser><EntryDate>2020-04-26</EntryDate><EntryTime>19:25:01.0</EntryTime><AuthUser></AuthUser><AuthDate>2020-04-26</AuthDate><AuthTime>19:25:21.0</AuthTime><Notes></Notes><ImageFile>ftp://10.17.100.246:21/NginxServer//2020/04/27/15879682062499686.pdf</ImageFile><MainWarnDesc></MainWarnDesc><ResultMsgs><ResultMsg><LabNo>20042501861</LabNo><TestCode>ACM99911</TestCode><TestName>甲状腺球蛋白</TestName><TestEngName>tg</TestEngName><Result>1.00</Result><Units>ng/ml</Units><Notes></Notes><ResultFlag>1653</ResultFlag><Ranges>1.15-130.8</Ranges><Sequence>0</Sequence><MICFlag></MICFlag><MICName></MICName><WarnDesc></WarnDesc></ResultMsg><ResultMsg><LabNo>20042501861</LabNo><TestCode>ACM00110</TestCode><TestName>三碘甲状原氨酸*(T3)</TestName><TestEngName>tt3</TestEngName><Result>55.00</Result><Units>nmol/L</Units><Notes></Notes><ResultFlag>1652</ResultFlag><Ranges>1.01-2.48</Ranges><Sequence>0</Sequence><MICFlag></MICFlag><MICName></MICName><WarnDesc></WarnDesc></ResultMsg><ResultMsg><LabNo>20042501861</LabNo><TestCode>ACL01110</TestCode><TestName>促甲状腺激素*</TestName><TestEngName>tsh</TestEngName><Result>1.000</Result><Units>μlU/mL</Units><Notes></Notes><ResultFlag>1651</ResultFlag><Ranges>0.38-5.33</Ranges><Sequence>0</Sequence><MICFlag></MICFlag><MICName></MICName><WarnDesc></WarnDesc></ResultMsg><ResultMsg><LabNo>20042501861</LabNo><TestCode>ACM00510</TestCode><TestName>甲状腺素*（T4）</TestName><TestEngName>tt4</TestEngName><Result>22.00</Result><Units>nmol/L</Units><Notes></Notes><ResultFlag>1653</ResultFlag><Ranges>69.97-152.52</Ranges><Sequence>0</Sequence><MICFlag></MICFlag><MICName></MICName><WarnDesc></WarnDesc></ResultMsg><ResultMsg><LabNo>20042501861</LabNo><TestCode>ACM00210</TestCode><TestName>游离三碘甲状原氨酸*(FT3)</TestName><TestEngName>ft3</TestEngName><Result>11.00</Result><Units>pmol/L</Units><Notes></Notes><ResultFlag>1652</ResultFlag><Ranges>3.09-7.42</Ranges><Sequence>0</Sequence><MICFlag></MICFlag><MICName></MICName><WarnDesc></WarnDesc></ResultMsg><ResultMsg><LabNo>20042501861</LabNo><TestCode>ADE05510</TestCode><TestName>甲状腺球蛋白抗体</TestName><TestEngName>tgab</TestEngName><Result>1.00</Result><Units>IU/ml</Units><Notes></Notes><ResultFlag>1651</ResultFlag><Ranges>0-4.9</Ranges><Sequence>0</Sequence><MICFlag></MICFlag><MICName></MICName><WarnDesc></WarnDesc></ResultMsg><ResultMsg><LabNo>20042501861</LabNo><TestCode>ACM00610</TestCode><TestName>游离甲状腺素*(FT4)</TestName><TestEngName>ft4</TestEngName><Result>11.00</Result><Units>pmol/L</Units><Notes></Notes><ResultFlag>1651</ResultFlag><Ranges>7.64-16.03</Ranges><Sequence>0</Sequence><MICFlag></MICFlag><MICName></MICName><WarnDesc></WarnDesc></ResultMsg><ResultMsg><LabNo>20042501861</LabNo><TestCode>ADE05710</TestCode><TestName>抗甲状腺过氧化物酶抗体(TPOAb)</TestName><TestEngName>tpoab</TestEngName><Result>1.00</Result><Units>IU/ml</Units><Notes></Notes><ResultFlag>1651</ResultFlag><Ranges>0-9</Ranges><Sequence>0</Sequence><MICFlag></MICFlag><MICName></MICName><WarnDesc></WarnDesc></ResultMsg></ResultMsgs></ReportMsg></Request>";
 return str;
 }
}