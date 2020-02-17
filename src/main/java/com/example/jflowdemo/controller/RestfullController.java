package com.example.jflowdemo.controller;

import com.example.jflowdemo.service.JflowService;
import com.example.jflowdemo.service.WF_Comm_Controller_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

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

    @RequestMapping(value = "/DB_Todolist")
    public String DbToDoList(){
//        String userNo = "zhoutianjiao";
//        String sysNo = " ";
//        System.out.println("userNo = "+userNo);
//        String tempList = jflowService.DB_Todolist(userNo, sysNo);
        //String tempList = jflowService.DbToDoList();
        //return tempList;
        return " ";
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
}
