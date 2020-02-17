package com.example.jflowdemo.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * InterfaceName: WF_Comm_Controller_Service
 * Description: TODO
 * Author : Administrator
 * Date : 2020/2/13 13:29
 * Version : 1.0
 **/

//@FeignClient("jflow-service")
//@RequestMapping("/WF/Comm")
public interface WF_Comm_Controller_Service {

    /**
     * 默认执行的方法
     *
     * @return
     */
//    @RequestMapping(value = "/ProcessRequest")
//    public void ProcessRequest(HttpServletRequest request);
}
