package main.com.webservice.client;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.message.SOAPHeaderElement;

import javax.xml.namespace.QName;

public class Client2Service2 {
    public static void test1(String Str) throws Exception {
        Service service = new Service();
        Call call = (Call) service.createCall();
        String url = "http://localhost:1234/findInfo?wsdl";
        call.setTargetEndpointAddress(url);
        // 设置命名空间和方法名，命名空间需要和@WebService注解的targetNamespace一致
        call.setOperationName(new QName("http://service.cxf1.wishwzp.com/", "findInfo"));
        // 添加头信息，用于权限校验。QName的参数命名随意
        SOAPHeaderElement header = new SOAPHeaderElement(new QName("ABC"));
        header.setActor(null);
        header.addChildElement("ABC").addTextNode("testABC");
        call.addHeader(header);
        // 设置参数，这里调用一次addParameter，表示添加一个参数，然后定义接口传入参数objects数组
        // 数组个数和addParameter被调用次数相等，如果没有参数，则不调用addParameter和setReturnType，然后objects长度为0

        //xmlRequestStr 这个要和写的webservice的对应方法的入参名称要一样，要不收不到数据

        call.addParameter(new QName("content"), org.apache.axis.encoding.XMLType.XSD_STRING,javax.xml.rpc.ParameterMode.IN);
        // 设置返回类型
        call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);
        System.out.println("请求的报文："+Str);
        call.setUseSOAPAction(true);
        String result = (String) call.invoke(new String[] {Str});
        System.out.println("返回的加密报文："+result);
    }
    public static void main(String args[]) {
        try {
            test1("aaaaaa");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}