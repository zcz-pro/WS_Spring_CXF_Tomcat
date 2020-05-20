package main.com.webservice.client;

import com.webservice.his.ba.entity.SampleMaster;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

import javax.xml.namespace.QName;
import java.util.ArrayList;
import java.util.List;

/**
 * @authorZCZ
 * @version 1.0
 * @return a
 * @description 杭创社康------检验申请单查询检验申请单查询
 * @date 2020/4/28 17:40
 */
public class LISRequestQuery {
     public static void main(String args[]) {
          //TODO 300并发测试
          String wsdlUrl="http://218.18.233.229:9528/HAI-SERVICE/service?wsdl";
          String namespaceURI="http://service.com/";
          String localPart="invoke";
          String urid="";//用户名（默认为空）
          String pwd="";//密码（默认为空）
          String name="LISRequestQuery";//流程名称（必填）
          String argss=strXml();//字符串格式参数
          Object[] obj=new Object[]{name,urid,pwd,argss};
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
         Object[] result = new Object[10];
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
     public static String strXml(){
         String str="<Request><LISRequest><RequestId>20051811497191</RequestId></LISRequest></Request>";
         return str;
     }
}