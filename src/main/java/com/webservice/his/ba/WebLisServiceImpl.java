package com.webservice.his.ba;

import com.webservice.his.ba.Utils.FtpClient;
import com.webservice.his.ba.entity.SampleMaster;
import com.webservice.his.ba.service.IHisJoinRoute;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.namespace.QName;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by admin on 2020/4/3.
 */
@WebService(endpointInterface="com.webservice.his.ba.WebLisService")
public class WebLisServiceImpl implements WebLisService {

    @Autowired
    private FtpClient client;

    @Resource
    private IHisJoinRoute hisJoinRoute;

    @Override
    public String getPatOrdList(String code,String reqXmlContent) {
        Date date=new Date();
        SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String sdate=sf.format(date);
        System.out.println("["+sdate+"] 【"+code+"】请求参数"+reqXmlContent);
        if("LISGetPatOrdList".equals(code)){
            System.out.println("["+sdate+"] 条码调取响应参数"+xmlStr());
            return xmlStr();
        }else if("MSG0161".equals(code)){
            System.out.println("["+sdate+"] 回写条码状态响应参数"+xmlStr());
            return checkUpXmlStr();
        }else if("MSG0160".equals(code)){
            System.out.println("["+sdate+"] 退费响应参数"+xmlStr());
            return specimenStatusUpXmlStr();
        }
        return "";
    }

    @WebMethod(operationName="GetBarCodeInfo")
    @Override
    public String getPatOrdListTwo(String reqXmlContent) {
        System.out.println("调取方法二接口");
        return xmlStr();
    }

    public String xmlStr(){
        String str="<Response><GetLabOrderListReturn><ResultCode>0</ResultCode><ResultContent>成功</ResultContent><OrderLists><OrderList><OrdID>11520361_2</OrdID><LabNo>20051504255</LabNo><ArcimCode>012014</ArcimCode><ArcimDesc>电解质6项</ArcimDesc><OrderQty>1</OrderQty><OrderStatus>V</OrderStatus><TSPrice>30</TSPrice><OrderDept>1824</OrderDept><OrderRecDep>1601</OrderRecDep><OrderDoctor>15</OrderDoctor><OrderUser>1655</OrderUser><OrderDate>2020-05-15</OrderDate><OrderTime>10:59:14</OrderTime><OrderSttDat>2020-05-15</OrderSttDat><OrderSttTim>10:50:00</OrderSttTim><AdmNo>11537280</AdmNo><AdmDate>2020-05-15</AdmDate><AdmTime>10:49:55</AdmTime><AdmType>O</AdmType><FeeType>15</FeeType><PatientWard/><BedNo/><Diagnose>体检;</Diagnose><SpecimenType>SP006@血浆</SpecimenType><Notes/><BillStatus>P</BillStatus><RegisterNo>0000087557</RegisterNo><PatientName>房笃智</PatientName><Sex>M</Sex><DOB>1981-04-18</DOB><DocumentID>0000087557</DocumentID><PatientAddress>广东省深圳市宝安区龙井二路118号人民医院</PatientAddress><IdentityCardNo>43062619810418563X</IdentityCardNo><SpecDate>2020-05-15</SpecDate><SpecTime>11:22</SpecTime><SpecUserCode>748</SpecUserCode><Telephone>13728901632</Telephone><Age>39</Age><AgeUnit>岁</AgeUnit><healthcardNo>615600458</healthcardNo></OrderList><OrderList><OrdID>11520361_3</OrdID><LabNo>20051504255</LabNo><ArcimCode>012019</ArcimCode><ArcimDesc>肾功能5项</ArcimDesc><OrderQty>1</OrderQty><OrderStatus>V</OrderStatus><TSPrice>80</TSPrice><OrderDept>1824</OrderDept><OrderRecDep>1601</OrderRecDep><OrderDoctor>15</OrderDoctor><OrderUser>1655</OrderUser><OrderDate>2020-05-15</OrderDate><OrderTime>10:59:14</OrderTime><OrderSttDat>2020-05-15</OrderSttDat><OrderSttTim>10:50:00</OrderSttTim><AdmNo>11537280</AdmNo><AdmDate>2020-05-15</AdmDate><AdmTime>10:49:55</AdmTime><AdmType>O</AdmType><FeeType>15</FeeType><PatientWard/><BedNo/><Diagnose>体检;</Diagnose><SpecimenType>SP006@血浆</SpecimenType><Notes/><BillStatus>P</BillStatus><RegisterNo>0000087557</RegisterNo><PatientName>房笃智</PatientName><Sex>M</Sex><DOB>1981-04-18</DOB><DocumentID>0000087557</DocumentID><PatientAddress>广东省深圳市宝安区龙井二路118号人民医院</PatientAddress><IdentityCardNo>43062619810418563X</IdentityCardNo><SpecDate>2020-05-15</SpecDate><SpecTime>11:22</SpecTime><SpecUserCode>748</SpecUserCode><Telephone>13728901632</Telephone><Age>39</Age><AgeUnit>岁</AgeUnit><healthcardNo>615600458</healthcardNo></OrderList><OrderList><OrdID>11520361_4</OrdID><LabNo>20051504255</LabNo><ArcimCode>012021</ArcimCode><ArcimDesc>肾功能4项</ArcimDesc><OrderQty>1</OrderQty><OrderStatus>V</OrderStatus><TSPrice>30</TSPrice><OrderDept>1824</OrderDept><OrderRecDep>1601</OrderRecDep><OrderDoctor>15</OrderDoctor><OrderUser>1655</OrderUser><OrderDate>2020-05-15</OrderDate><OrderTime>10:59:14</OrderTime><OrderSttDat>2020-05-15</OrderSttDat><OrderSttTim>10:50:00</OrderSttTim><AdmNo>11537280</AdmNo><AdmDate>2020-05-15</AdmDate><AdmTime>10:49:55</AdmTime><AdmType>O</AdmType><FeeType>15</FeeType><PatientWard/><BedNo/><Diagnose>体检;</Diagnose><SpecimenType>SP006@血浆</SpecimenType><Notes/><BillStatus>P</BillStatus><RegisterNo>0000087557</RegisterNo><PatientName>房笃智</PatientName><Sex>M</Sex><DOB>1981-04-18</DOB><DocumentID>0000087557</DocumentID><PatientAddress>广东省深圳市宝安区龙井二路118号人民医院</PatientAddress><IdentityCardNo>43062619810418563X</IdentityCardNo><SpecDate>2020-05-15</SpecDate><SpecTime>11:22</SpecTime><SpecUserCode>748</SpecUserCode><Telephone>13728901632</Telephone><Age>39</Age><AgeUnit>岁</AgeUnit><healthcardNo>615600458</healthcardNo></OrderList><OrderList><OrdID>11520361_5</OrdID><LabNo>20051504255</LabNo><ArcimCode>012036</ArcimCode><ArcimDesc>肝功能8项</ArcimDesc><OrderQty>1</OrderQty><OrderStatus>V</OrderStatus><TSPrice>44</TSPrice><OrderDept>1824</OrderDept><OrderRecDep>1601</OrderRecDep><OrderDoctor>15</OrderDoctor><OrderUser>1655</OrderUser><OrderDate>2020-05-15</OrderDate><OrderTime>10:59:14</OrderTime><OrderSttDat>2020-05-15</OrderSttDat><OrderSttTim>10:50:00</OrderSttTim><AdmNo>11537280</AdmNo><AdmDate>2020-05-15</AdmDate><AdmTime>10:49:55</AdmTime><AdmType>O</AdmType><FeeType>15</FeeType><PatientWard/><BedNo/><Diagnose>体检;</Diagnose><SpecimenType>SP006@血浆</SpecimenType><Notes/><BillStatus>P</BillStatus><RegisterNo>0000087557</RegisterNo><PatientName>房笃智</PatientName><Sex>M</Sex><DOB>1981-04-18</DOB><DocumentID>0000087557</DocumentID><PatientAddress>广东省深圳市宝安区龙井二路118号人民医院</PatientAddress><IdentityCardNo>43062619810418563X</IdentityCardNo><SpecDate>2020-05-15</SpecDate><SpecTime>11:22</SpecTime><SpecUserCode>748</SpecUserCode><Telephone>13728901632</Telephone><Age>39</Age><AgeUnit>岁</AgeUnit><healthcardNo>615600458</healthcardNo></OrderList><OrderList><OrdID>11520361_6</OrdID><LabNo>20051504255</LabNo><ArcimCode>012025</ArcimCode><ArcimDesc>血脂7项检查</ArcimDesc><OrderQty>1</OrderQty><OrderStatus>V</OrderStatus><TSPrice>79</TSPrice><OrderDept>1824</OrderDept><OrderRecDep>1601</OrderRecDep><OrderDoctor>15</OrderDoctor><OrderUser>1655</OrderUser><OrderDate>2020-05-15</OrderDate><OrderTime>10:59:14</OrderTime><OrderSttDat>2020-05-15</OrderSttDat><OrderSttTim>10:50:00</OrderSttTim><AdmNo>11537280</AdmNo><AdmDate>2020-05-15</AdmDate><AdmTime>10:49:55</AdmTime><AdmType>O</AdmType><FeeType>15</FeeType><PatientWard/><BedNo/><Diagnose>体检;</Diagnose><SpecimenType>SP006@血浆</SpecimenType><Notes/><BillStatus>P</BillStatus><RegisterNo>0000087557</RegisterNo><PatientName>房笃智</PatientName><Sex>M</Sex><DOB>1981-04-18</DOB><DocumentID>0000087557</DocumentID><PatientAddress>广东省深圳市宝安区龙井二路118号人民医院</PatientAddress><IdentityCardNo>43062619810418563X</IdentityCardNo><SpecDate>2020-05-15</SpecDate><SpecTime>11:22</SpecTime><SpecUserCode>748</SpecUserCode><Telephone>13728901632</Telephone><Age>39</Age><AgeUnit>岁</AgeUnit><healthcardNo>615600458</healthcardNo></OrderList><OrderList><OrdID>11520361_7</OrdID><LabNo>20051504255</LabNo><ArcimCode>000008</ArcimCode><ArcimDesc>空腹血糖检测</ArcimDesc><OrderQty>1</OrderQty><OrderStatus>V</OrderStatus><TSPrice>6</TSPrice><OrderDept>1824</OrderDept><OrderRecDep>1601</OrderRecDep><OrderDoctor>15</OrderDoctor><OrderUser>1655</OrderUser><OrderDate>2020-05-15</OrderDate><OrderTime>10:59:14</OrderTime><OrderSttDat>2020-05-15</OrderSttDat><OrderSttTim>10:50:00</OrderSttTim><AdmNo>11537280</AdmNo><AdmDate>2020-05-15</AdmDate><AdmTime>10:49:55</AdmTime><AdmType>O</AdmType><FeeType>15</FeeType><PatientWard/><BedNo/><Diagnose>体检;</Diagnose><SpecimenType>SP006@血浆</SpecimenType><Notes/><BillStatus>P</BillStatus><RegisterNo>0000087557</RegisterNo><PatientName>房笃智</PatientName><Sex>M</Sex><DOB>1981-04-18</DOB><DocumentID>0000087557</DocumentID><PatientAddress>广东省深圳市宝安区龙井二路118号人民医院</PatientAddress><IdentityCardNo>43062619810418563X</IdentityCardNo><SpecDate>2020-05-15</SpecDate><SpecTime>11:22</SpecTime><SpecUserCode>748</SpecUserCode><Telephone>13728901632</Telephone><Age>39</Age><AgeUnit>岁</AgeUnit><healthcardNo>615600458</healthcardNo></OrderList><OrderList><OrdID>11520361_13</OrdID><LabNo>20051504255</LabNo><ArcimCode>012468</ArcimCode><ArcimDesc>肝功能酶类检查</ArcimDesc><OrderQty>1</OrderQty><OrderStatus>V</OrderStatus><TSPrice>58</TSPrice><OrderDept>1824</OrderDept><OrderRecDep>1601</OrderRecDep><OrderDoctor>15</OrderDoctor><OrderUser>1655</OrderUser><OrderDate>2020-05-15</OrderDate><OrderTime>10:59:15</OrderTime><OrderSttDat>2020-05-15</OrderSttDat><OrderSttTim>10:51:00</OrderSttTim><AdmNo>11537280</AdmNo><AdmDate>2020-05-15</AdmDate><AdmTime>10:49:55</AdmTime><AdmType>O</AdmType><FeeType>15</FeeType><PatientWard/><BedNo/><Diagnose>体检;</Diagnose><SpecimenType>SP006@血浆</SpecimenType><Notes/><BillStatus>P</BillStatus><RegisterNo>0000087557</RegisterNo><PatientName>房笃智</PatientName><Sex>M</Sex><DOB>1981-04-18</DOB><DocumentID>0000087557</DocumentID><PatientAddress>广东省深圳市宝安区龙井二路118号人民医院</PatientAddress><IdentityCardNo>43062619810418563X</IdentityCardNo><SpecDate>2020-05-15</SpecDate><SpecTime>11:22</SpecTime><SpecUserCode>748</SpecUserCode><Telephone>13728901632</Telephone><Age>39</Age><AgeUnit>岁</AgeUnit><healthcardNo>615600458</healthcardNo></OrderList><OrderList><OrdID>11520361_14</OrdID><LabNo>20051504255</LabNo><ArcimCode>012033</ArcimCode><ArcimDesc>蛋白,胆红素及胆汁酸代谢</ArcimDesc><OrderQty>1</OrderQty><OrderStatus>V</OrderStatus><TSPrice>82</TSPrice><OrderDept>1824</OrderDept><OrderRecDep>1601</OrderRecDep><OrderDoctor>15</OrderDoctor><OrderUser>1655</OrderUser><OrderDate>2020-05-15</OrderDate><OrderTime>10:59:15</OrderTime><OrderSttDat>2020-05-15</OrderSttDat><OrderSttTim>10:51:00</OrderSttTim><AdmNo>11537280</AdmNo><AdmDate>2020-05-15</AdmDate><AdmTime>10:49:55</AdmTime><AdmType>O</AdmType><FeeType>15</FeeType><PatientWard/><BedNo/><Diagnose>体检;</Diagnose><SpecimenType>SP006@血浆</SpecimenType><Notes/><BillStatus>P</BillStatus><RegisterNo>0000087557</RegisterNo><PatientName>房笃智</PatientName><Sex>M</Sex><DOB>1981-04-18</DOB><DocumentID>0000087557</DocumentID><PatientAddress>广东省深圳市宝安区龙井二路118号人民医院</PatientAddress><IdentityCardNo>43062619810418563X</IdentityCardNo><SpecDate>2020-05-15</SpecDate><SpecTime>11:22</SpecTime><SpecUserCode>748</SpecUserCode><Telephone>13728901632</Telephone><Age>39</Age><AgeUnit>岁</AgeUnit><healthcardNo>615600458</healthcardNo></OrderList><OrderList><OrdID>11520361_54</OrdID><LabNo>20051504255</LabNo><ArcimCode>JY00109</ArcimCode><ArcimDesc>风湿3项检测</ArcimDesc><OrderQty>1</OrderQty><OrderStatus>V</OrderStatus><TSPrice>68</TSPrice><OrderDept>1824</OrderDept><OrderRecDep>1601</OrderRecDep><OrderDoctor>15</OrderDoctor><OrderUser>1655</OrderUser><OrderDate>2020-05-15</OrderDate><OrderTime>10:59:15</OrderTime><OrderSttDat>2020-05-15</OrderSttDat><OrderSttTim>10:54:00</OrderSttTim><AdmNo>11537280</AdmNo><AdmDate>2020-05-15</AdmDate><AdmTime>10:49:55</AdmTime><AdmType>O</AdmType><FeeType>15</FeeType><PatientWard/><BedNo/><Diagnose>体检;</Diagnose><SpecimenType>SP006@血浆</SpecimenType><Notes/><BillStatus>P</BillStatus><RegisterNo>0000087557</RegisterNo><PatientName>房笃智</PatientName><Sex>M</Sex><DOB>1981-04-18</DOB><DocumentID>0000087557</DocumentID><PatientAddress>广东省深圳市宝安区龙井二路118号人民医院</PatientAddress><IdentityCardNo>43062619810418563X</IdentityCardNo><SpecDate>2020-05-15</SpecDate><SpecTime>11:28</SpecTime><SpecUserCode>748</SpecUserCode><Telephone>13728901632</Telephone><Age>39</Age><AgeUnit>岁</AgeUnit><healthcardNo>615600458</healthcardNo></OrderList><OrderList><OrdID>11520361_55</OrdID><LabNo>20051504255</LabNo><ArcimCode>JY00097</ArcimCode><ArcimDesc>免疫球蛋白定量</ArcimDesc><OrderQty>1</OrderQty><OrderStatus>V</OrderStatus><TSPrice>48</TSPrice><OrderDept>1824</OrderDept><OrderRecDep>1601</OrderRecDep><OrderDoctor>15</OrderDoctor><OrderUser>1655</OrderUser><OrderDate>2020-05-15</OrderDate><OrderTime>10:59:15</OrderTime><OrderSttDat>2020-05-15</OrderSttDat><OrderSttTim>10:54:00</OrderSttTim><AdmNo>11537280</AdmNo><AdmDate>2020-05-15</AdmDate><AdmTime>10:49:55</AdmTime><AdmType>O</AdmType><FeeType>15</FeeType><PatientWard/><BedNo/><Diagnose>体检;</Diagnose><SpecimenType>SP006@血浆</SpecimenType><Notes/><BillStatus>P</BillStatus><RegisterNo>0000087557</RegisterNo><PatientName>房笃智</PatientName><Sex>M</Sex><DOB>1981-04-18</DOB><DocumentID>0000087557</DocumentID><PatientAddress>广东省深圳市宝安区龙井二路118号人民医院</PatientAddress><IdentityCardNo>43062619810418563X</IdentityCardNo><SpecDate>2020-05-15</SpecDate><SpecTime>11:28</SpecTime><SpecUserCode>748</SpecUserCode><Telephone>13728901632</Telephone><Age>39</Age><AgeUnit>岁</AgeUnit><healthcardNo>615600458</healthcardNo></OrderList><OrderList><OrdID>11520361_56</OrdID><LabNo>20051504255</LabNo><ArcimCode>JY00098</ArcimCode><ArcimDesc>补体C3、C4测定</ArcimDesc><OrderQty>1</OrderQty><OrderStatus>V</OrderStatus><TSPrice>32</TSPrice><OrderDept>1824</OrderDept><OrderRecDep>1601</OrderRecDep><OrderDoctor>15</OrderDoctor><OrderUser>1655</OrderUser><OrderDate>2020-05-15</OrderDate><OrderTime>10:59:15</OrderTime><OrderSttDat>2020-05-15</OrderSttDat><OrderSttTim>10:54:00</OrderSttTim><AdmNo>11537280</AdmNo><AdmDate>2020-05-15</AdmDate><AdmTime>10:49:55</AdmTime><AdmType>O</AdmType><FeeType>15</FeeType><PatientWard/><BedNo/><Diagnose>体检;</Diagnose><SpecimenType>SP006@血浆</SpecimenType><Notes/><BillStatus>P</BillStatus><RegisterNo>0000087557</RegisterNo><PatientName>房笃智</PatientName><Sex>M</Sex><DOB>1981-04-18</DOB><DocumentID>0000087557</DocumentID><PatientAddress>广东省深圳市宝安区龙井二路118号人民医院</PatientAddress><IdentityCardNo>43062619810418563X</IdentityCardNo><SpecDate>2020-05-15</SpecDate><SpecTime>11:28</SpecTime><SpecUserCode>748</SpecUserCode><Telephone>13728901632</Telephone><Age>39</Age><AgeUnit>岁</AgeUnit><healthcardNo>615600458</healthcardNo></OrderList></OrderLists></GetLabOrderListReturn></Response>\n";
        return str;
    }

    @Override
    public String getDeptDict(String reqXmlContent) {
        return deptXmlStr();
    }

    public String deptXmlStr(){
        String str="<?xml version=\"1.0\" encoding=\"utf-8\"?><root><Response><LocInfoList><LocInfo><SerialNO>1</SerialNO><DeptCode>0102</DeptCode><DeptName>内一区</DeptName><InputCode>NYQ</InputCode></LocInfo><LocInfo><SerialNO>2</SerialNO><DeptCode>0101</DeptCode><DeptName>内一区门诊</DeptName><InputCode>NYQMZ</InputCode></LocInfo><LocInfo><SerialNO>3</SerialNO><DeptCode>01</DeptCode><DeptName>内一区病区</DeptName><InputCode>NYQBQ</InputCode></LocInfo></LocInfoList></Response></root>";
        return str;
    }

    @Override
    public String getHisUsers(String reqXmlContent) {
        return usersXmlStr();
    }

    public String usersXmlStr(){
        String str="<?xml version=\"1.0\" encoding=\"utf-8\"?><root><Response><UserInfoList><UserInfo><UserID>0259</UserID><UserDept>72</UserDept><UserName>乔向东</UserName><UserJob>副主任医师</UserJob><CreateDate></CreateDate></UserInfo><UserInfo><UserID>0765</UserID><UserDept>72</UserDept><UserName>张婷婷</UserName><UserJob>医师</UserJob><CreateDate></CreateDate></UserInfo><UserInfo><UserID>0332</UserID><UserDept>9701</UserDept><UserName>吴祖成</UserName><UserJob>副主任医师</UserJob><CreateDate></CreateDate></UserInfo></UserInfoList></Response></root>";
        return str;
    }

    @Override
    public String getLisArcimInfo(String reqXmlContent) {
        return ArcimXmlStr();
    }

    public String ArcimXmlStr(){
        String str="<?xml version=\"1.0\" encoding=\"utf-8\"?><root><Response><LisArcimInfoList><LisArcimInfo><ArcimRowID>3318_1</ArcimRowID><ArcimCode>JY178</ArcimCode><ArcimDesc>（限ICU)心梗标志物</ArcimDesc><ArcimUnit>项</ArcimUnit ><ArcimPrice>19.0</ArcimPrice><ArcimLisBarCode>LIS提供给HIS的分类码</ArcimLisBarCode><ArcimSubCat >临检</ArcimSubCat ><ArcimIsActive >Y</ArcimIsActive ><ArcimDateFrom >2014-06-05</ArcimDateFrom ><ArcimDateTo></ArcimDateTo></LisArcimInfo><LisArcimInfo><ArcimRowID>5420_1</ArcimRowID><ArcimCode>JY179</ArcimCode><ArcimDesc>（限ICU)床旁D-二聚体测定</ArcimDesc><ArcimUnit>项</ArcimUnit ><ArcimPrice>228</ArcimPrice><ArcimLisBarCode>LIS提供给HIS的分类码</ArcimLisBarCode><ArcimSubCat >外送检验</ArcimSubCat ><ArcimIsActive >Y</ArcimIsActive ><ArcimDateFrom >2014-06-05</ArcimDateFrom ><ArcimDateTo></ArcimDateTo></LisArcimInfo></LisArcimInfoList></Response></root>";
        return str;
    }

    @Override
    public String sendCheckUp(String reqXmlContent) {
        return checkUpXmlStr();
    }

    public String checkUpXmlStr(){
        String str="<CheckUpReturn><ResultCode>0</ResultCode><ResultContent>成功</ResultContent></CheckUpReturn>";
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
        String str="<SpecimenStatusReturn><ResultCode>0</ResultCode>" +
                "<ResultContent>成功</ResultContent></SpecimenStatusReturn>";
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
        String str="<?xml version=\"1.0\" encoding=\"utf-8\"?><root><RefuseReturn><ResultCode>0</ResultCode><ResultContent>成功</ResultContent></RefuseReturn></root>";
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
        String str="<RefuseReturn><ResultCode>0</ResultCode><ResultContent>成功</ResultContent></RefuseReturn>";
        return str;
    }

    @Override
    public String sendLinkLabNoWithOrdRowId(String reqXmlContent) {
        String testStr=hisJoinRoute.test001();
        System.out.println(testStr);
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
        String str="<Response><LinkLabNoWithOrdRowIdReturn><ResultContent>成功</ResultContent><ResultCode>0</ResultCode></LinkLabNoWithOrdRowIdReturn></Response>";
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
        String str="<CarryInfo><CarryLoc>产科病区</CarryLoc><TransDate>2020-02-18</TransDate><TransTime>17:32:35</TransTime><TransUserCode>632</TransUserCode><TransUserName>吴妙琴</TransUserName><TransContainer>90987</TransContainer><TransCount>1</TransCount><DeliveryUser>周澎洋</DeliveryUser><DeliveryDateTime>2020-02-19 11:45:16</DeliveryDateTime><CarryDetailInfoList><CarryDetailInfo><LabNo>20041700008</LabNo><OrdName>尿液分析(急)</OrdName><SpecName>尿液</SpecName><LabUser/><ReceiveDateTime></ReceiveDateTime></CarryDetailInfo></CarryDetailInfoList></CarryInfo>";
        return str;
    }

    @Override
    public String sendBarCodeStatus(String reqXmlContent) {
        System.out.println("条码签收回写条码状态="+reqXmlContent);
        return barCodeStatusXmlStr();
    }

    public String barCodeStatusXmlStr(){
        String str="<SpecimenStatusReturn><ResultCode>0</ResultCode><ResultContent>成功</ResultContent></SpecimenStatusReturn>";
        return str;
    }


    /**
     * @description 杭创社康------检验申请单查询
     * @author ZCZ
     * @version 1.0
     * @date 2020/4/28 17:49
     */
    @Override
    public String lISRequestQuery(String urid ,String pwd,String name,String args) {
        System.out.println("urid="+urid+","+"pwd="+pwd+",name="+name+",args="+args);
        return barlISRequestQueryXmlStr();
    }

    public String barlISRequestQueryXmlStr(){
        String str="<Response><MsgHeader><ResponseCode>AA</ResponseCode><Detail>查询成功</Detail></MsgHeader><LISRequest><SourcePatientId>23212311</SourcePatientId><PatientType>01</PatientType><VisitId>3423412</VisitId><AuthorOrganization>02123</AuthorOrganization><ClinicId>3252413</ClinicId><HospizationId>43663</HospizationId><RequestId>20120303323232</RequestId><RequestOrganization>02123213-1</RequestOrganization><RequestOrganizationName>文华社康</RequestOrganizationName><RequestDeptName>科室1</RequestDeptName><RequestDateTime>20200429T083000</RequestDateTime><RequestDoctor>陈和</RequestDoctor><Ysdm>1111</Ysdm><IsEmergency>0</IsEmergency><Name>王平</Name><Sex>1</Sex><IdCardNo>230828198008134409</IdCardNo><MedicalCardId>21351234</MedicalCardId><BirthDate>19630902</BirthDate><Age>55</Age><MonthAge>5</MonthAge><PatientPhone>13387212242</PatientPhone><IsNeonatus>0</IsNeonatus><DeptCode>内科</DeptCode><Ysdm>2222</Ysdm><WardArea>二病区</WardArea><SickBedId>201</SickBedId><DiagnoseCode>S32.001</DiagnoseCode><DiagnoseName>第二腰椎压缩性骨折</DiagnoseName><InvoiceNo>32411001</InvoiceNo><Fee>205.50</Fee><ExamItemList>血常规，尿常规</ExamItemList><LisType >3</LisType><ExecuteOrganization>02123213-1</ExecuteOrganization><ExecuteDept>检验科</ExecuteDept><Items><Item><ItemCode>JY00511</ItemCode><ItemName>尿液分析（孕产）</ItemName><SampleTypeCode>01</SampleTypeCode><SampleTypeName>全血</SampleTypeName><Fee>103.00</Fee><Status>1</Status><Barcode>34214321123</Barcode><ExamItems><ExamItem><ExamItemCode>1111</ExamItemCode><ExamItemName>白细胞</ExamItemName><PrintFlag>1</PrintFlag></ExamItem></ExamItems></Item></Items></LISRequest></Response>";
        return str;
    }

    /**
     * @description 杭创社康------2.5检验报告接收
     * @author ZCZ
     * @version 1.0
     * @date 2020/4/28 17:49
     */
    @Override
    public String lISReportSend(String urid ,String pwd,String name,String args) {
        System.out.println("urid="+urid+","+"pwd="+pwd+",name="+name+",args="+args);
        return barLISReportSendXmlStr();
    }

    public String barLISReportSendXmlStr(){
        String str="<SpecimenStatusReturn><ResultCode>0</ResultCode><ResultContent>成功</ResultContent></SpecimenStatusReturn>";
        return str;
    }

    /**
     * @description 杭创社康------2.5检验报告接收
     * @author ZCZ
     * @version 1.0
     * @date 2020/4/28 17:49
     */
    @Override
    public String invoke(String urid ,String pwd,String name,String args) {
        System.out.println("urid="+urid+","+"pwd="+pwd+",name="+name+",args="+args);
        if("LISRequestQuery".equals(name)){
            return barlISRequestQueryXmlStr();
        }else if("LISReportSend".equals(name)){
            return barInvokeXmlStr();
        }
        return "";
    }

    public String barInvokeXmlStr(){
        String str="<SpecimenStatusReturn><ResultCode>0</ResultCode><ResultContent>成功</ResultContent></SpecimenStatusReturn>";
        return str;
    }
}
