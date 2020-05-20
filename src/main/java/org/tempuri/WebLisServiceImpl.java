package org.tempuri;

import com.webservice.his.ba.Utils.FtpClient;
import com.webservice.his.ba.entity.SampleMaster;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.namespace.QName;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2020/4/3.
 */
@WebService(endpointInterface= "org.tempuri.WebLisService")
public class WebLisServiceImpl implements WebLisService {

    @Autowired
    private FtpClient client;

    @Override
    public String getPatOrdList(String reqXmlContent) {
        System.out.println("请求参数"+reqXmlContent);
        return xmlStr();
    }

    @WebMethod(operationName="GetBarCodeInfo")
    @Override
    public String getPatOrdListTwo(String reqXmlContent) {
        System.out.println("调取方法二接口");
        return xmlStr();
    }

    public String xmlStr(){
        String str="<?xml version=\"1.0\" encoding=\"utf-8\"?>" + "<root>" + "<Response>" + "<ResultCode>0</ResultCode> " + "<ResultContent>成功</ResultContent> " + "<OrderLists>" + "<OrderList>" + "<OrdID>7752780_86</OrdID>" + "<LabNo>18032600866</LabNo> " + "<ArcimCode>JY178</ArcimCode> " + "<ArcimDesc>高危HPV</ArcimDesc> " + "<OrderQty>1</OrderQty> " + "<OrderStatus>V</OrderStatus> " + "<TSPrice>165</TSPrice> " + "<OrderDept>1328</OrderDept> " + "<OrderRecDep>1601</OrderRecDep> " + "<OrderDoctor>410</OrderDoctor> " + "<OrderUser>452</OrderUser> " + "<OrderDate>2018-03-26</OrderDate> " + "<OrderTime>08:35:46</OrderTime> " + "<OrderSttDat>2018-03-26</OrderSttDat> " + "<OrderSttTim>08:30:00</OrderSttTim> " + "<AdmNo>7764692</AdmNo> " + "<AdmDate>2018-03-22</AdmDate> " + "<AdmTime>00:06:26</AdmTime> " + "<AdmTypeCode>E</AdmTypeCode> " + "<AdmType>I</AdmType> " + "<AdmTypeDesc>就诊类别名称</AdmTypeDesc> " + "<FeeType>1</FeeType> " + "<PatientWard>1420</PatientWard> " + "<BedNo>12</BedNo> " + "<Diagnose>发热查因;</Diagnose> " + "<SpecimenType>SP049@生殖道分泌物</SpecimenType> " + "<Notes/> " + "<BillStatus>TB</BillStatus> " + "<RegisterNo>0000441288</RegisterNo> " + "<PatientName>温美娥</PatientName> " + "<Sex>F</Sex> " + "<DOB>1963-11-21</DOB> " + "<DocumentID>0000441288</DocumentID> " + "<PatientAddress>深圳市宝安区41区金鼎花园A1栋2单元408</PatientAddress> " + "<IdentityCardNo>46002419631121684X</IdentityCardNo> " + "<SpecDate>2018-03-26</SpecDate> " + "<SpecTime>08:47</SpecTime> " + "<SpecUserCode>2614</SpecUserCode> " + "<Telephone>13510633878</Telephone> " + "<Age>54</Age> " + "<AgeUnit>岁</AgeUnit> " + "<DocCode>519</DocCode> " + "<DocDesc>孙莺</DocDesc> " + "<RecDeptCode>1401</RecDeptCode> " + "<RecDeptDesc>AYQBQ-爱婴区病区</RecDeptDesc> " + "<SpecimenTypeCode>7001</SpecimenTypeCode> " + "<SpecimenTypeDesc>滑液</SpecimenTypeDesc> " + "<SpVolume>5</SpVolume> " + "<ColInfo>5</ColInfo> " + "<Container>5</Container> " + "<GTSeq>5</GTSeq> " + "<SpecFlag>5</SpecFlag> " + "<VerifyDate>2020-04-13</VerifyDate> " + "<VerifyTime>10:42:20</VerifyTime> " + "<ColDate>2020-04-13</ColDate> " + "<ColTime>10:42:20</ColTime> " + "<AppDeptCode>1102</AppDeptCode> " + "<AppDeptDesc>CWMZSFC-财务门诊收费处</AppDeptDesc> " + "<RepLoct>宝安医院</RepLoct> " + "<RepDate>2020-04-13</RepDate> " + "<RepTime>10:42:20</RepTime> " + "<Note>医生备注</Note> " + "<SexCode>1051</SexCode> " + "<SexDesc>男</SexDesc> " + "<StartDate>2020-04-13</StartDate> " + "<StartTime>10:42:20</StartTime> " + "<SendFlag>CA</SendFlag> " + "</OrderList>" + "</OrderLists>" + "</Response>" + "</root>";
        return str;
    }

    @Override
    public String getDeptDict(String reqXmlContent) {
        return deptXmlStr();
    }

    public String deptXmlStr(){
        String str="<?xml version=\"1.0\" encoding=\"utf-8\"?>" + "<root>" + "<Response>" + "<LocInfoList>" + "<LocInfo>" + "<SerialNO>1</SerialNO>" + "<DeptCode>0102</DeptCode>" + "<DeptName>内一区</DeptName>" + "<InputCode>NYQ</InputCode>" + "</LocInfo>" + "<LocInfo>" + "<SerialNO>2</SerialNO>" + "<DeptCode>0101</DeptCode>" + "<DeptName>内一区门诊</DeptName>" + "<InputCode>NYQMZ</InputCode>" + "</LocInfo>" + "<LocInfo>" + "<SerialNO>3</SerialNO>" + "<DeptCode>01</DeptCode>" + "<DeptName>内一区病区</DeptName>" + "<InputCode>NYQBQ</InputCode>" + "</LocInfo>" + "</LocInfoList>" + "</Response>" + "</root>";
        return str;
    }

    @Override
    public String getHisUsers(String reqXmlContent) {
        return usersXmlStr();
    }

    public String usersXmlStr(){
        String str="<?xml version=\"1.0\" encoding=\"utf-8\"?>" + "<root>" + "<Response>" + "<UserInfoList>" + "<UserInfo>" + "<UserID>0259</UserID>" + "<UserDept>72</UserDept>" + "<UserName>乔向东</UserName>" + "<UserJob>副主任医师</UserJob>" + "<CreateDate></CreateDate>" + "</UserInfo>" + "<UserInfo>" + "<UserID>0765</UserID>" + "<UserDept>72</UserDept>" + "<UserName>张婷婷</UserName>" + "<UserJob>医师</UserJob>" + "<CreateDate></CreateDate>" + "</UserInfo>" + "<UserInfo>" + "<UserID>0332</UserID>" + "<UserDept>9701</UserDept>" + "<UserName>吴祖成</UserName>" + "<UserJob>副主任医师</UserJob>" + "<CreateDate></CreateDate>" + "</UserInfo>" + "</UserInfoList>" + "</Response>" + "</root>";
        return str;
    }

    @Override
    public String getLisArcimInfo(String reqXmlContent) {
        return ArcimXmlStr();
    }

    public String ArcimXmlStr(){
        String str="<?xml version=\"1.0\" encoding=\"utf-8\"?>" + "<root>" + "<Response>" + "<LisArcimInfoList>" + "<LisArcimInfo>" + "<ArcimRowID>3318_1</ArcimRowID>" + "<ArcimCode>JY178</ArcimCode>" + "<ArcimDesc>（限ICU)心梗标志物</ArcimDesc>" + "<ArcimUnit>项</ArcimUnit >" + "<ArcimPrice>19.0</ArcimPrice>" + "<ArcimLisBarCode>LIS提供给HIS的分类码</ArcimLisBarCode>" + "<ArcimSubCat >临检</ArcimSubCat >" + "<ArcimIsActive >Y</ArcimIsActive >" + "<ArcimDateFrom >2014-06-05</ArcimDateFrom >" + "<ArcimDateTo></ArcimDateTo>" + "</LisArcimInfo>" + "<LisArcimInfo>" + "<ArcimRowID>5420_1</ArcimRowID>" + "<ArcimCode>JY179</ArcimCode>" + "<ArcimDesc>（限ICU)床旁D-二聚体测定</ArcimDesc>" + "<ArcimUnit>项</ArcimUnit >" + "<ArcimPrice>228</ArcimPrice>" + "<ArcimLisBarCode>LIS提供给HIS的分类码</ArcimLisBarCode>" + "<ArcimSubCat >外送检验</ArcimSubCat >" + "<ArcimIsActive >Y</ArcimIsActive >" + "<ArcimDateFrom >2014-06-05</ArcimDateFrom >" + "<ArcimDateTo></ArcimDateTo>" + "</LisArcimInfo>" + "</LisArcimInfoList>" + "</Response>" + "</root>";
        return str;
    }

    @Override
    public String sendCheckUp(String reqXmlContent) {
        return checkUpXmlStr();
    }

    public String checkUpXmlStr(){
        String str="<?xml version=\"1.0\" encoding=\"utf-8\"?>" + "<root>" + "<Response>" + "<LisArcimInfoList>" + "<LisArcimInfo>" + "<ArcimRowID>3318_1</ArcimRowID>" + "<ArcimCode>JY178</ArcimCode>" + "<ArcimDesc>（限ICU)心梗标志物</ArcimDesc>" + "<ArcimUnit>项</ArcimUnit >" + "<ArcimPrice>19.0</ArcimPrice>" + "<ArcimLisBarCode>LIS提供给HIS的分类码</ArcimLisBarCode>" + "<ArcimSubCat >临检</ArcimSubCat >" + "<ArcimIsActive >Y</ArcimIsActive >" + "<ArcimDateFrom >2014-06-05</ArcimDateFrom >" + "<ArcimDateTo></ArcimDateTo>" + "</LisArcimInfo>" + "<LisArcimInfo>" + "<ArcimRowID>5420_1</ArcimRowID>" + "<ArcimCode>JY179</ArcimCode>" + "<ArcimDesc>（限ICU)床旁D-二聚体测定</ArcimDesc>" + "<ArcimUnit>项</ArcimUnit >" + "<ArcimPrice>228</ArcimPrice>" + "<ArcimLisBarCode>LIS提供给HIS的分类码</ArcimLisBarCode>" + "<ArcimSubCat >外送检验</ArcimSubCat >" + "<ArcimIsActive >Y</ArcimIsActive >" + "<ArcimDateFrom >2014-06-05</ArcimDateFrom >" + "<ArcimDateTo></ArcimDateTo>" + "</LisArcimInfo>" + "</LisArcimInfoList>" + "</Response>" + "</root>";
        return str;
    }

    /**
     * @description 用于检验科回传标本的状态（退费调取）
     * @author ZCZ
     * @version 1.0
     * @date 2020/4/10 13:35
     */
    @Override
    public String sendSpecimenStatus(String reqXmlContent) {
        System.out.println("退费回写="+reqXmlContent);
        return specimenStatusUpXmlStr();
    }

    public String specimenStatusUpXmlStr(){
        String str="<?xml version=\"1.0\" encoding=\"utf-8\"?>" + "<root>" + "<RefuseReturn>" + "<ResultCode>0</ResultCode>" + "<ResultContent>成功</ResultContent>" + "</RefuseReturn>" + "</root>";
        return str;
    }

    /**
     * @description 拒绝接收标本（退费调取）
     * @author ZCZ
     * @version 1.0
     * @date 2020/4/10 13:35
     */
    @Override
    public String sendRefuse(String reqXmlContent) {
        System.out.println("拒绝接收标本回写="+reqXmlContent);
        return refuseXmlStr();
    }

    public String refuseXmlStr(){
        String str="<?xml version=\"1.0\" encoding=\"utf-8\"?>" + "<root>" + "<RefuseReturn>" + "<ResultCode>0</ResultCode>" + "<ResultContent>成功</ResultContent>" + "</RefuseReturn>" + "</root>";
        return str;
    }

    @Override
    public String sendReportMsg(String reqXmlContent) {
        System.out.println("回写报告单="+reqXmlContent);

//        System.out.println("上传文件开始");
//        InputStream is=null;
//        try {
//            File file=new File("C:\\Users\\admin\\Desktop\\分拣机接口文档_苏州祺康.pdf");
//            is=new FileInputStream(file);
//            client.connect();
//            //            client.upload("test2.pdf",new File("C:\\Users\\admin\\Desktop\\分拣机接口文档_苏州祺康.pdf"));
//           String result= client.uploadFileToDailyDir("test3.pdf",is);
//           String url="ftp://"+client.getHost()+":"+client.getPort()+"/"+client.getWorkingDir()+"/"+result;
//            System.out.println(result);
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                is.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }

        return reportXmlStr();
    }

    public String reportXmlStr(){
        String str="<RefuseReturn>" + "<ResultCode>0</ResultCode>" + "<ResultContent>成功</ResultContent>" + "</RefuseReturn>";
        return str;
    }

    @Override
    public String sendLinkLabNoWithOrdRowId(String reqXmlContent) {
        System.out.println("关联检验号和医嘱号="+reqXmlContent);
        String wsdlUrl="http://192.168.100.57/dthealth/web/web.DHCBARM.LIS.Service.LISService.CLS?WSDL";
        // String wsdlUrl="http://172.20.207.174:8080/icube/cxfService/WebHisService?wsdl";
        // String wsdlUrl="http://localhost/icube/cxfService/WebHisService?wsdl";
        String namespaceURI="http://tempuri.org";
        String localPart="SendLinkLabNoWithOrdRowId";
        Object[] obj=new Object[]{reqXmlContent};
        List<SampleMaster> entList=new ArrayList<SampleMaster>();
        try {
            String result= invoke(wsdlUrl,namespaceURI,localPart,obj);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return linkLabNoWithOrdRowIdXmlStr();
    }

    public String linkLabNoWithOrdRowIdXmlStr(){
        String str="<Response>" + "<LinkLabNoWithOrdRowIdReturn>" + "<ResultContent>成功</ResultContent>" + "<ResultCode>0</ResultCode>" + "</LinkLabNoWithOrdRowIdReturn>" + "</Response>";
        return str;
    }

    public static String invoke(String wsdlUrl,String namespaceURI,String localPart,Object[] obj) throws Exception{
        JaxWsDynamicClientFactory clientFactory = JaxWsDynamicClientFactory.newInstance();
        Client client =null;
        Object[] result = new Object[0];
        try {
            client= clientFactory.createClient(wsdlUrl);
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

    @Override
    public String getAdviceBatchCodeList(String reqXmlContent) {
        System.out.println("通过批次号签收标本="+reqXmlContent);
        return batchCodeXmlStr();
    }

    public String batchCodeXmlStr(){
        String str="<CarryInfo>" + "<CarryLoc>产科病区</CarryLoc>" + "<TransDate>2020-02-18</TransDate>" + "<TransTime>17:32:35</TransTime>" + "<TransUserCode>632</TransUserCode>" + "<TransUserName>吴妙琴</TransUserName>" + "<TransContainer>90987</TransContainer>" + "<TransCount>1</TransCount>" + "<DeliveryUser>周澎洋</DeliveryUser>" + "<DeliveryDateTime>2020-02-19 11:45:16</DeliveryDateTime>" + "<CarryDetailInfoList>" + "<CarryDetailInfo>" + "<LabNo>20041700008</LabNo>" + "<OrdName>尿液分析(急)</OrdName>" + "<SpecName>尿液</SpecName>" + "<LabUser/>" + "<ReceiveDateTime></ReceiveDateTime>" + "</CarryDetailInfo>" + "</CarryDetailInfoList>" + "</CarryInfo>";
        return str;
    }

    @Override
    public String sendBarCodeStatus(String reqXmlContent) {
        System.out.println("上机操作回写条码状态="+reqXmlContent);
        return barCodeStatusXmlStr();
    }

    public String barCodeStatusXmlStr(){
        String str="<SpecimenStatusReturn><ResultCode>0</ResultCode><ResultContent>成功</ResultContent></SpecimenStatusReturn>";
        return str;
    }
}
