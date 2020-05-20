package org.tempuri;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * Created by admin on 2020/4/3.
 */
@WebService
public interface WebLisService {
    /**
     * @description 获取检验医嘱列表
     * @author ZCZ
     * @version 1.0
     * @date 2020/4/10 13:35
     */
    @WebMethod(operationName="GetPatOrdList")
    String getPatOrdList(String reqXmlContent);

    @WebMethod(operationName="GetBarCodeInfo")
    String getPatOrdListTwo(String reqXmlContent);

    /**
     * @description 科室信息同步
     * @author ZCZ
     * @version 1.0
     * @date 2020/4/10 13:35
     */
    @WebMethod(operationName="GetDeptDict")
    String getDeptDict(String reqXmlContent);

    /**
     * @description 员工
     * @author ZCZ
     * @version 1.0
     * @date 2020/4/10 13:35
     */
    @WebMethod(operationName="GetHisUsers")
    String getHisUsers(String reqXmlContent);

    /**
     * @description 同步检验收费套餐信息（对应医嘱项目）
     * @author ZCZ
     * @version 1.0
     * @date 2020/4/10 13:35
     */
    @WebMethod(operationName="GetLisArcimInfo")
    String getLisArcimInfo(String reqXmlContent);

    /**
     * @description 接收标本（医嘱条码状态）
     * @author ZCZ
     * @version 1.0
     * @date 2020/4/10 13:35
     */
    @WebMethod(operationName="SendCheckUp")
    String sendCheckUp(String reqXmlContent);

    /**
     * @description 用于检验科回传标本的状态（退费调取）
     * @author ZCZ
     * @version 1.0
     * @date 2020/4/10 13:35
     */
    @WebMethod(operationName="SendSpecimenStatus")
    String sendSpecimenStatus(String reqXmlContent);

    /**
     * @description 拒绝接收标本（退费调取）
     * @author ZCZ
     * @version 1.0
     * @date 2020/4/10 13:35
     */
    @WebMethod(operationName="SendRefuse")
    String sendRefuse(String reqXmlContent);

    /**
     * @description 回写报告
     * @author ZCZ
     * @version 1.0
     * @date 2020/4/14 9:55
     */
    @WebMethod(operationName="SendReportMsg")
    String sendReportMsg(String reqXmlContent);

    /**
     * @description 关联检验号和医嘱号
     * @author ZCZ
     * @version 1.0
     * @date 2020/4/14 19:00
     */
    @WebMethod(operationName="SendLinkLabNoWithOrdRowId")
    String sendLinkLabNoWithOrdRowId(String reqXmlContent);

    /**
     * @description 通过批次号签收标本
     * @author ZCZ
     * @version 1.0
     * @date 2020/4/14 19:00
     */
    @WebMethod(operationName="GetAdviceBatchCodeList")
    String getAdviceBatchCodeList(String reqXmlContent);


    /**
     * @description 关联检验号和医嘱号
     * @author ZCZ
     * @version 1.0
     * @date 2020/4/14 19:00
     */
    @WebMethod(operationName="SendBarCodeStatus")
    String sendBarCodeStatus(String reqXmlContent);
}
