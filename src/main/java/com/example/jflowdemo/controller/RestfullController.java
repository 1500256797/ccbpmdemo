package com.example.jflowdemo.controller;

import com.example.jflowdemo.service.JflowService;
import com.example.jflowdemo.service.WF_Comm_Controller_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

/**
 * ClassName: RestfullController
 * Description: TODO
 * Author : Administrator
 * Date : 2020/2/13 11:37
 * Version : 1.0
 **/
@RestController
public class RestfullController {
    @Autowired
    JflowService jflowService;

    //@Autowired
    //WF_Comm_Controller_Service wf_comm_controller_service;

    @RequestMapping(value = "/Port_Login")
    public String Port_Login() {
        String userNo = "zhoutianjiao";
        try {
            jflowService.Port_Login(userNo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "success" ;
    }

    //待办
    @RequestMapping(value = "/DB_Todolist")
    public String DbToDoList(){
        //sysno应填空字符串，任何空格形式的字符串被禁止
        String result = jflowService.DB_Todolist("zhoutianjiao","");
        return result;
    }

    //可以发起的流程
    @RequestMapping(value = "/DB_StarFlows")
    public String DB_StarFlows(){
        String userNo = "zhoutianjiao";
        String sysNo = "";
        String temp ="false";
        try {
            temp = jflowService.DB_StarFlows(userNo, sysNo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return temp;
    }

    //在途
    @RequestMapping(value = "/DB_Running")
    public String DB_Runing(){
        String userNo = "zhoutianjiao";
        String sysNo = "";
        String temp ="false";
        try {
            temp = jflowService.DB_Runing(userNo, sysNo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return temp;
    }
//    //登陆初始化
//    @RequestMapping(value = "/webuser_login_init")
//    public void webuser_login_init(HttpServletRequest request){
//        System.out.println("打印请求投："+request.getHeader("Cookie"));
//        System.out.println("请求参数："+request.getParameter("DoType"));
//        jflowService.ProcessRequest(request);
//    }


    /**
     * 我发起的流程实例
     * @param userNo 用户编号
     * @param domain 统编号,为空时返回平台所有数据
     * @param pageSize
     * @param pageIdx
     * @return
     */
    @RequestMapping(value = "/DB_MyStartFlowInstance")
    public String DB_MyStartFlowInstance(@RequestParam("userNo")  String userNo, @RequestParam("domain")  String domain, @RequestParam("pageSize")  int pageSize, @RequestParam("pageIdx") int pageIdx){
        String tempResult = jflowService.DB_MyStartFlowInstance(userNo,domain,pageSize,pageIdx);
        return tempResult;
    }
//    {
//        "WorkID": 290,
//            "StarterName": "周天娇",
//            "Title": "集团研发部-zhoutianjiao,周天娇在2019-11-16 11:25发起.",
//            "WFSta": 0,
//            "WFState": 2,
//            "NodeName": "节点2",
//            "RDT": "2019-11-16 11:26",
//            "BillNo": "",
//            "FlowNote": "",
//            "FK_FlowSort": "100",
//            "FK_Flow": "015",
//            "FK_Dept": "1002",
//            "FID": 0,
//            "FK_Node": 1502,
//            "FK_NY": "2019-11",
//            "MyNum": 0,
//            "FlowName": "pop返回值demo",
//            "Starter": "zhoutianjiao",
//            "Sender": "zhoutianjiao",
//            "DeptName": "集团研发部",
//            "PRI": 1,
//            "SDTOfNode": "2019-11-17 11:26",
//            "SDTOfFlow": "",
//            "PFlowNo": "",
//            "PWorkID": 0,
//            "PNodeID": 0,
//            "PFID": 0,
//            "PEmp": "",
//            "CFlowNo": "",
//            "CWorkID": 0,
//            "GuestNo": "",
//            "GuestName": "",
//            "TodoEmps": "zhoupeng,周朋;",
//            "TodoEmpsNum": 1,
//            "TaskSta": 0,
//            "AtPara": "@CH1502=2019-11-17 11:26@LastTruckID=2123207970",
//            "Emps": "@zhoutianjiao@",
//            "GUID": "",
//            "SysType": "",
//            "SendDT": "2019-11-16 11:26",
//            "WeekNum": 0,
//            "TSpan": 0,
//            "Domain": "",
//            "TodoSta": 0,
//            "PrjNo": "",
//            "PrjName": "",
//            "SDTOfFlowWarning": "",
//            "DoDomain": ""
//    }
    //发起一个流程
//    public String createWork(){
//
//        jflowService.
//        return " ";
//    }

    /**
     * 创建WorkID
     * @param flowNo 流程编号
     * @param userNo 工作人员编号
     * @return 一个长整型的工作流程实例
     * @throws Exception
     */

    @RequestMapping(value = "/CreateWorkID")
    public long CreateWorkID(@RequestParam("flowNo") String flowNo, @RequestParam("userNo") String userNo){
        long workid =-1;
        try {
             workid = jflowService.CreateWorkID(flowNo, userNo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return workid==-1 ? -1 : workid;
    }


    /**
     * 执行发送
     *  flowNo 流的程模版ID
     *  workid 工作ID
     *  ht 参数，或者表单字段.
     *  toNodeID 到达的节点ID.如果让系统自动计算就传入0
     *  toEmps 到达的人员IDs,比如:zhangsan,lisi,wangwu. 如果为Null就标识让系统自动计算
     * @return 发送的结果信息.
     * @throws Exception
     */
    @RequestMapping("/sendwork")
    public String SendWordk( @RequestBody HashMap ht){
        String flowNo = (String) ht.get("flowNo");
        long workid = Long.parseLong(String.valueOf(ht.get("workid")));
        int toNodeID = Integer.parseInt(String.valueOf(ht.get("toNodeID")));
        String toEmps = (String) ht.get("toEmps");
        String userNo = (String) ht.get("userNO");
        Hashtable<String, String> hashtable_temp = new Hashtable<>();

        if(ht.get("ht") instanceof HashMap){
            System.out.println("我是一个hashmap");
//            System.out.println(((HashMap) ht.get("ht")).values());
            HashMap tempMap = (HashMap) ht.get("ht");
//            System.out.println(tempMap.get("ShiJianShuoMing"));
            hashtable_temp.put("ShiJianShuoMing", (String) tempMap.get("ShiJianShuoMing"));
            hashtable_temp.put("FaShengShiJian",tempMap.get("FaShengShiJian").toString());
        }else {
            System.out.println("我不是hashmap");
            System.out.println(ht.get("ht").toString());
        }
        String tempResult = jflowService.SendWork(flowNo, workid, hashtable_temp, toNodeID, toEmps, userNo);
        return tempResult;
    }
}
