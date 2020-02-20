package com.example.jflowdemo.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Hashtable;

/**
 * InterfaceName: jflowService
 * Description: TODO
 * Author : Administrator
 * Date : 2020/2/12 18:40
 * Version : 1.0
 **/
@FeignClient("jflow-service")
//@RequestMapping(value = "/restful")
public interface JflowService {
//    @RequestMapping(value = "/test")
//    public String queryPrestoDemo(HttpServletRequest request, HttpServletResponse response);

    /**
     * 登录.
     * @param userNo 用户登录编号
     */
    @RequestMapping(value = "/Port_Login")
    public void Port_Login(@RequestParam("userNo") String userNo) throws Exception;


//
//    @RequestMapping(value = "/jflow-web/restful/test1")
//    public String DbToDoList();

//    /**
//     * 默认执行的方法
//     *
//     * @return
//     *
//     */
//    @RequestMapping(value = "/jflow-web/WF/Comm/ProcessRequest.do")
//    public void ProcessRequest(HttpServletRequest request);
    /**
     * 待办
     * @param userNo 用户编号
     * @param sysNo 系统编号,为空时返回平台所有数据
     * @return
     */
    @RequestMapping(value = "/jflow-web/restful/DB_Todolist")
    public String DB_Todolist(@RequestParam("userNo") String userNo, @RequestParam("sysNo") String sysNo);


    /**
     * 获得在途
     * @param userNo 用户编号
     * @param sysNo 系统编号,为空时返回平台所有数据
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/jflow-web/restful/DB_Runing")
    public String DB_Runing(@RequestParam("userNo") String userNo, @RequestParam("sysNo") String sysNo) throws Exception;


    /**
     * 我可以发起的流程
     * @param userNo 用户编号
     * @param domain  系统编号,为空时返回平台所有数据
     * @return 返回我可以发起的流程列表.
     * @throws Exception
     */

    @RequestMapping(value = "/jflow-web/restful/DB_StarFlows")
    public String DB_StarFlows(@RequestParam("userNo") String userNo,@RequestParam("sysNo") String domain) throws Exception;


    /**
     * 我发起的流程实例
     * @param userNo 用户编号
     * @param domain 统编号,为空时返回平台所有数据
     * @param pageSize
     * @param pageIdx
     * @return
     */

    @RequestMapping(value = "/jflow-web/restful/DB_MyStartFlowInstance")
    public String DB_MyStartFlowInstance(@RequestParam("userNo")  String userNo, @RequestParam("domain")  String domain, @RequestParam("pageSize")  int pageSize, @RequestParam("pageIdx") int pageIdx);


    /**
     * 创建WorkID
     * @param flowNo 流程编号
     * @param userNo 工作人员编号
     * @return 一个长整型的工作流程实例
     * @throws Exception
     */

    @RequestMapping(value = "/jflow-web/restful/CreateWorkID")
    public long CreateWorkID(@RequestParam("flowNo") String flowNo, @RequestParam("userNo") String userNo) throws Exception;



    /**
     * 执行发送
     * @param flowNo 流的程模版ID
     * @param workid 工作ID
     * @param ht 参数，或者表单字段.
     * @param toNodeID 到达的节点ID.如果让系统自动计算就传入0
     * @param toEmps 到达的人员IDs,比如:zhangsan,lisi,wangwu. 如果为Null就标识让系统自动计算
     * @return 发送的结果信息.
     * @throws Exception
     */
    @RequestMapping(value = "/jflow-web/restful/SendWork")
    public String SendWork(@RequestParam("flowNo")  String flowNo , @RequestParam("workid") long workid , @RequestBody Hashtable ht, @RequestParam("toNodeID") int toNodeID, @RequestParam("toEmps") String toEmps, @RequestParam("userNO") String userNo);


    /**
     * 保存参数
     * @param workid 工作ID
     * @param paras 用于控制流程运转的参数，比如方向条件. 格式为:@JinE=1000@QingJaiTianShu=100
     * @throws Exception
     */
    @RequestMapping(value = "/jflow-web/restful/SaveParas")
    public void SaveParas(@RequestParam("workid") long workid, @RequestParam("paras") String paras,@RequestParam("userNo") String userNo) throws Exception;


    /**
     * 获得下一个节点信息
     * @param flowNo 流程编号
     * @param workid 流程实例
     * @param paras 方向条件所需要的参数，可以为空。
     * @return 下一个节点的JSON.
     * @throws Exception
     */

    @RequestMapping(value = "/jflow-web/restful/GenerNextStepNode")
    public String GenerNextStepNode(@RequestParam("flowNo") String flowNo, @RequestParam("workid") long workid,@RequestParam("paras") String paras, @RequestParam("userNo") String userNo) throws Exception;


    /**
     * 获得下一步节点的接收人
     * @param flowNo 流程ID
     * @param toNodeID 节点ID
     * @param workid 工作事例ID
     * @return 返回两个结果集一个是分组的Depts(No,Name)，另外一个是人员的Emps(No, Name, FK_Dept),接受后，用于构造人员选择器.
     * @throws Exception
     */

    @RequestMapping(value = "/jflow-web/restful/GenerNextStepNodeEmps")
    public String GenerNextStepNodeEmps(@RequestParam("flowNo") String flowNo, @RequestParam("toNodeID") int toNodeID, @RequestParam("workid") int workid,@RequestParam("userNo")  String userNo) throws Exception;


    /**
     * 将要退回到的节点
     * @param workID
     * @return 返回节点集合的json.
     * @throws Exception
     */
    @RequestMapping(value = "/jflow-web/restful/WillReturnToNodes")
    public String WillReturnToNodes(@RequestParam("workID") int workID,@RequestParam("userNo") String userNo) throws Exception;


    /**
     * 将要达到的节点
     * @param currNodeID 当前节点ID
     * @return 返回节点集合的json.
     * @throws Exception
     */
    @RequestMapping(value = "/jflow-web/restful/WillToNodes")
    public String WillToNodes(@RequestParam("currNodeID") int currNodeID, @RequestParam("userNo") String userNo) throws Exception;

    /**
     * 获得当前节点信息.
     * @param currNodeID  当前节点ID
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/jflow-web/restful/CurrNodeInfo")
    public String CurrNodeInfo(@RequestParam("currNodeID") int currNodeID,@RequestParam("userNo") String userNo) throws Exception;


    /**
     * 获得当前流程信息.
     * @param flowNo 流程ID
     * @return 当前节点信息
     * @throws Exception
     */
    @RequestMapping(value = "/jflow-web/restful/CurrFlowInfo")
    public String CurrFlowInfo(@RequestParam("flowNo") String flowNo, @RequestParam("userNo") String userNo) throws Exception;

    /**
     * 获得当前流程信息.
     * @param workID 流程ID
     * @return 当前节点信息
     * @throws Exception
     */
    @RequestMapping(value = "/jflow-web/restful/CurrGenerWorkFlowInfo")
    public String CurrGenerWorkFlowInfo(@RequestParam("workID") long workID,@RequestParam("userNo") String userNo) throws Exception;


    /**
     * 退回.
     * @param workID 流程ID
     * @param returnToNodeID 流程退回的节点ID
     * @param returnMsg 退回原因
     * @return 退回结果信息
     * @throws Exception
     */
    @RequestMapping(value = "/jflow-web/restful/Node_ReturnWork")
    public String Node_ReturnWork(@RequestParam("workID")long workID, @RequestParam("returnToNodeID") int returnToNodeID,@RequestParam("returnMsg") String returnMsg, @RequestParam("userNo")String userNo) throws Exception;

    /**
     * 执行流程结束 说明:强制流程结束.
     *
     * @param workID
     *            流程编号
     * @param workID
     *            工作ID
     * @param msg
     *            流程结束原因
     * @return 返回成功执行信息
     * @throws Exception
     */
    @RequestMapping(value = "/jflow-web/restful/Flow_DoFlowOverQiangZhi")
    public  String Flow_DoFlowOverQiangZhi(@RequestParam("workID") long workID, @RequestParam("msg") String msg, @RequestParam("userNo") String userNo) throws Exception;

    /**
     * 执行撤销
     * @param flowNo 流程编码
     * @param workID 工作ID
     * @param unSendToNode 撤销到的节点
     * @param fid
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/jflow-web/restful/Runing_UnSend")
    public String Runing_UnSend(@RequestParam("userNo") String userNo,@RequestParam("flowNo") String flowNo, @RequestParam("workID") long workID, @RequestParam("unSendToNode") int unSendToNode,@RequestParam("fid") long fid) throws Exception;


    /**
     * 流程结束后回滚
     * @param flowNo 流程编码
     * @param workId 工作ID
     * @param backToNodeID 回滚到的节点ID
     * @param backMsg 回滚原因
     * @return 回滚信息
     * @throws Exception
     */
    @RequestMapping(value = "/jflow-web/restful/DoRebackFlowData")
    public String DoRebackFlowData(@RequestParam("flowNo") String flowNo,@RequestParam("workId") long workId,@RequestParam("backToNodeID") int backToNodeID,@RequestParam("backMsg") String backMsg, @RequestParam("userNo") String userNo) throws Exception;


    /**
     获得工作进度-用于展示流程的进度图

     @param workID workID
     @param userNo 用户编号
     @return 返回待办
     */
    @RequestMapping(value = "/jflow-web/restful/WorkProgressBar")
    public String WorkProgressBar(@RequestParam("workID") long  workID, @RequestParam("userNo") String userNo);
    /**
     查询数据
     @param sqlOfSelect 要查询的sql
     @param password 用户密码
     @return 返回查询数据
     */
    @RequestMapping(value = "/jflow-web/restful/DB_RunSQLReturnJSON")
    public String DB_RunSQLReturnJSON(@RequestParam("sqlOfSelect") String sqlOfSelect, @RequestParam("password") String password);
    /**
     * 执行抄送
     * @param fk_node 流程编号
     * @param workID 工作ID
     * @param toEmpNo 抄送人员编号
     * @param toEmpName 抄送人员人员名称
     * @param msgTitle 标题
     * @param msgDoc 内容
     * @return 执行信息
     * @throws Exception
     */
    @RequestMapping(value = "/jflow-web/restful/Node_CC_WriteTo_CClist")
    public String Node_CC_WriteTo_CClist(@RequestParam("fk_node") int fk_node,@RequestParam("workID") long workID, @RequestParam("toEmpNo") String toEmpNo, @RequestParam("toEmpName") String toEmpName,
                                         @RequestParam("msgTitle") String msgTitle,@RequestParam("msgDoc") String msgDoc,@RequestParam("userNo")String userNo);
    /**
     是否可以查看该流程
     @param flowNo 流程编号
     @param workid 工作ID
     @return 是否可以查看该工作.
      * @throws Exception
     */
    @RequestMapping(value = "/jflow-web/restful/Flow_IsCanView")
    public Boolean Flow_IsCanView(@RequestParam("flowNo") String flowNo,  @RequestParam("workid") long workid,  @RequestParam("userNo") String userNo);

    /**
     是否可以查看该流程
     @param workid 要查询的 sql
     @param userNo 用户
     @return 是否可以查看该工作.
      * @throws Exception
     */
    @RequestMapping(value = "/jflow-web/restful/Flow_IsCanDoCurrentWork")
    public Boolean Flow_IsCanDoCurrentWork(@RequestParam("workid") long workid, @RequestParam("userNo") String userNo);
    /**
     * 获取指定人员的抄送列表 说明:可以根据这个列表生成指定用户的抄送数据.
     *
     * @param userNo
     *            人员编号,如果是null,则返回所有的.
     * @return 返回该人员的所有抄送列表,结构同表WF_CCList.
     */
    @RequestMapping(value = "/jflow-web/restful/DB_CCList")
    public String DB_CCList( @RequestParam("userNo") String userNo);


    /**
     * 获得工作进度-用于展示流程的进度图
     *
     * @param workID
     *            workID
     * @param userNo
     *            用户编号
     * @return 返回待办
     */
    @RequestMapping(value = "/jflow-web/restful/WorkProgressBar20")
    public String WorkProgressBar20( @RequestParam("workID") long  workID,@RequestParam("userNo") String userNo);


    // 根据当前节点获得下一个节点.
    //nodeID 当前节点ID
    //Dirs   流程运行需要的参数
    //@RequestMapping(value = "/jflow-web/restful/GetNextNodeID")
    //public int GetNextNodeID(@RequestParam("nodeID") int nodeID, @RequestParam("workID") DataTable dirs)

    /**
     写入审核信息

     <param name="workid">workID</param>
     <param name="msg">审核信息</param>
     *
     */
    //@RequestMapping(value = "/jflow-web/restful/Node_WriteWorkCheck")
    //public void Node_WriteWorkCheck(@RequestParam("nodeID") long workid, @RequestParam("nodeID") String msg);

//    /**
//     * sdk表单加载初始化信息
//     * @param workID   工作ID
//     * @return 请参考相关的文档,或者baidu ccbpm sdk表单 SDK_Page_Init
//     * @throws Exception
//     */
//    @RequestMapping(value = "/jflow-web/restful/SDK_Page_Init")
//    public String SDK_Page_Init(@RequestParam("workID") long  workID,@RequestParam("userNo") String userNo);
}
