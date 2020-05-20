package main.com.webservice.client;

import com.webservice.his.ba.entity.SampleMaster;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

import javax.xml.namespace.QName;
import java.util.ArrayList;
import java.util.List;

public class Client2Service4 {
     public static void main(String args[]) {
      //TODO 300并发测试
           String wsdlUrl="http://10.17.100.140:8080/icube/cxfService/WebHisService?wsdl";
//          String wsdlUrl="http://10.17.100.246:8080/icube/cxfService/WebHisService?wsdl";
//          String wsdlUrl="http://172.20.209.164:8080/icube/cxfService/WebHisService?wsdl";
//          String wsdlUrl="http://172.20.207.133/icube/cxfService/WebHisService?wsdl";
          String namespaceURI="http://service.sample.sync.com/";
          String localPart="ReceiveAdviceBarCode";
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
        // String str="<Request><ResultCode>0</ResultCode><ResultContent>成功</ResultContent><OrderLists><OrderList><OrdID>11503710_1</OrdID><LabNo>20051105760</LabNo><ArcimCode>JY00224</ArcimCode><ArcimDesc>尿液分析</ArcimDesc><OrderQty>1</OrderQty><BillCode>P</BillCode><SpecimenTypeCode>SP011</SpecimenTypeCode><SpecimenTypeDesc>尿液</SpecimenTypeDesc><SpVolume></SpVolume><ColInfo></ColInfo><Container></Container><GTSeq></GTSeq><SpecFlag>0</SpecFlag><VerifyDate>2020-05-11</VerifyDate><VerifyTime>16:05:39</VerifyTime><ColDate></ColDate><ColTime></ColTime><AppDeptRowID>87</AppDeptRowID><AppDeptCode>1339</AppDeptCode><AppDeptDesc>TJZX-体检中心</AppDeptDesc><RecDeptRowID>31</RecDeptRowID><RecDeptCode>1601</RecDeptCode><RecDeptDesc>JYK-检验科</RecDeptDesc><TSPrice>9</TSPrice><OrderStatus>V</OrderStatus><DocCode>333</DocCode><DocDesc>杨志伟</DocDesc><RepLoct></RepLoct><RepDate></RepDate><RepTime></RepTime><Note></Note><AdmNo>11520600</AdmNo><AdmDate>2020-05-11</AdmDate><AdmTime>16:05:39</AdmTime><AdmTypeCode>H</AdmTypeCode><AdmTypeDesc>体检</AdmTypeDesc><FeeType>自费</FeeType><PatientWard>87@</PatientWard><BedNo></BedNo><Diagnose></Diagnose><RegisterNo>0004358186</RegisterNo><PatientName>东方测试1</PatientName><SexCode>F</SexCode><SexDesc>女</SexDesc><DOB>2015-02-20</DOB><Age>5岁2月21天</Age><DocumentID>00378246</DocumentID><PatientAddress>西海岸花园</PatientAddress><IdentityCardNo>510723199005184157</IdentityCardNo><Telephone>18782261111</Telephone><SpecUserCode></SpecUserCode><StartDate>2020-05-11</StartDate><StartTime>16:05:39</StartTime><SendFlag>NW</SendFlag><PhysiCycle></PhysiCycle></OrderList></OrderLists></Request>";
           String str="<Request><ResultCode>0</ResultCode><ResultContent>成功</ResultContent><OrderLists><OrderList><OrdID>11504208_38</OrdID><LabNo>20051105762</LabNo><ArcimCode>100141</ArcimCode><ArcimDesc>（限ICU)心衰标志物</ArcimDesc><OrderQty>1</OrderQty><BillCode>I</BillCode><SpecimenTypeCode>SP010</SpecimenTypeCode><SpecimenTypeDesc>新鲜全血</SpecimenTypeDesc><SpVolume/><ColInfo/><Container/><GTSeq/><SpecFlag>0</SpecFlag><VerifyDate>2020-05-11</VerifyDate><VerifyTime>21:03:35</VerifyTime><ColDate>2020-05-11</ColDate><ColTime>21:24</ColTime><AppDeptRowID>80</AppDeptRowID><AppDeptCode>1332</AppDeptCode><AppDeptDesc>ZZYXK-重症医学科</AppDeptDesc><RecDeptRowID>80</RecDeptRowID><RecDeptCode>1332</RecDeptCode><RecDeptDesc>ZZYXK-重症医学科</RecDeptDesc><TSPrice>200</TSPrice><OrderStatus>E</OrderStatus><DocCode>984</DocCode><DocDesc>王伟</DocDesc><RepLoct/><RepDate/><RepTime/><Note>ST</Note><AdmNo>11521091</AdmNo><AdmDate>2020-05-11</AdmDate><AdmTime>20:41:55</AdmTime><AdmTypeCode>I</AdmTypeCode><AdmTypeDesc>住院</AdmTypeDesc><FeeType>自费</FeeType><PatientWard>80@1423</PatientWard><BedNo>08</BedNo><Diagnose>吸入性肺炎</Diagnose><RegisterNo>0002333425</RegisterNo><PatientName>梁晶晶</PatientName><SexCode>F</SexCode><SexDesc>女</SexDesc><DOB>1998-12-19</DOB><Age>21岁4月24天</Age><DocumentID>00437545</DocumentID><PatientAddress>广东省深圳市宝安区松岗松河瑞园1A904</PatientAddress><IdentityCardNo>440981199812195625</IdentityCardNo><Telephone>15889577498</Telephone><SpecUserCode>1640</SpecUserCode><StartDate>2020-05-11</StartDate><StartTime>21:03:00</StartTime><SendFlag>NW</SendFlag><PhysiCycle/></OrderList></OrderLists></Request>";
           return str;
     }
}