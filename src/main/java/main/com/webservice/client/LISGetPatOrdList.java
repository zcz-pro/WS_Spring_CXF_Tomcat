package main.com.webservice.client;

import com.webservice.his.ba.entity.SampleMaster;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

import javax.xml.namespace.QName;
import java.util.ArrayList;
import java.util.List;

public class LISGetPatOrdList {
     public static void main(String args[]) {
      //TODO 300并发测试
         // String wsdlUrl="http://10.17.100.246:8080/icube/cxfService/WebHisService?wsdl";
//          String wsdlUrl="http://172.20.209.164:8080/icube/cxfService/WebHisService?wsdl";
          String wsdlUrl="http://10.17.200.60/csp/hsb/DHC.Published.YJDLISWebService.BS.YJDLISWebService.CLS?WSDL";
          String namespaceURI="http://tempuri.org";
          String localPart="ESBWebInterface";
          String str=strXml();
          Object[] obj=new Object[]{"LISGetPatOrdList",str};
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
// String str="<Request>" + "<ResultCode>0</ResultCode> " + "<ResultContent>成功</ResultContent> " + "<OrderLists>" + "<OrderList>" + "<OrdID>7752780_117</OrdID>" + "<ArcimCode>JY00196</ArcimCode> " + "<ArcimDesc>高危HPV11</ArcimDesc> " + "<OrderQty>1</OrderQty> " + "<OrderStatus>V</OrderStatus> " + "<TSPrice>165</TSPrice> " + "<OrderDept>1328</OrderDept> " + "<OrderRecDep>1601</OrderRecDep> " + "<OrderDoctor>410</OrderDoctor> " + "<OrderUser>452</OrderUser> " + "<OrderDate>2018-03-26</OrderDate> " + "<OrderTime>08:35:46</OrderTime> " + "<OrderSttDat>2018-03-26</OrderSttDat> " + "<OrderSttTim>08:30:00</OrderSttTim> " + "<AdmNo>7764692</AdmNo> " + "<AdmDate>2018-03-22</AdmDate> " + "<AdmTime>00:06:26</AdmTime> " + "<AdmTypeCode>O</AdmTypeCode> " + "<AdmType>I</AdmType> " + "<AdmTypeDesc>就诊类别名称</AdmTypeDesc> " + "<FeeType>1</FeeType> " + "<PatientWard>1420</PatientWard> " + "<BedNo>12</BedNo> " + "<Diagnose>发热查因;</Diagnose> " + "<SpecimenType>SP049@生殖道分泌物</SpecimenType> " + "<Notes/> " + "<BillStatus>TB</BillStatus> " + "<RegisterNo>0000441288</RegisterNo> " + "<PatientName>温美娥</PatientName> " + "<Sex>F</Sex> " + "<DOB>1963-11-21</DOB> " + "<DocumentID>0000441288</DocumentID> " + "<PatientAddress>深圳市宝安区41区金鼎花园A1栋2单元408</PatientAddress> " + "<IdentityCardNo>46002419631121684X</IdentityCardNo> " + "<SpecDate>2018-03-26</SpecDate> " + "<SpecTime>08:47</SpecTime> " + "<SpecUserCode>2614</SpecUserCode> " + "<Telephone>13510633878</Telephone> " + "<Age>54</Age> " + "<AgeUnit>岁</AgeUnit> " + "<DocCode>519</DocCode> " + "<DocDesc>孙莺</DocDesc> " + "<RecDeptCode>1401</RecDeptCode> " + "<RecDeptDesc>AYQBQ-爱婴区病区</RecDeptDesc> " + "<SpecimenTypeCode>7001</SpecimenTypeCode> " + "<SpecimenTypeDesc>滑液</SpecimenTypeDesc> " + "<SpVolume>5</SpVolume> " + "<ColInfo>5</ColInfo> " + "<Container>5</Container> " + "<GTSeq>5</GTSeq> " + "<SpecFlag>5</SpecFlag> " + "<VerifyDate>2020-04-13</VerifyDate> " + "<VerifyTime>10:42:20</VerifyTime> " + "<ColDate>2020-04-13</ColDate> " + "<ColTime>10:42:20</ColTime> " + "<AppDeptCode>1102</AppDeptCode> " + "<AppDeptDesc>CWMZSFC-财务门诊收费处</AppDeptDesc> " + "<RepLoct>宝安医院</RepLoct> " + "<RepDate>2020-04-13</RepDate> " + "<RepTime>10:42:20</RepTime> " + "<Note>医生备注</Note> " + "<SexCode>1051</SexCode> " + "<SexDesc>男</SexDesc> " + "<StartDate>2020-04-13</StartDate> " + "<StartTime>10:42:20</StartTime> " + "<SendFlag>CA</SendFlag> " + "</OrderList>" + "</OrderLists>" + "</Request>";
 String str="<Request><GetLabOrderList><LabNo>20051105761</LabNo><CardValue/><CardType/><RegisterNo/><StartDate/><EndDate/><EpsiodeType/><AdmWard/></GetLabOrderList></Request>";
 return str;
 }
}