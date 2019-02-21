package com.debug.wust.dubbo.two.server.controller;

import com.debug.wust.dubbo.two.server.request.RecordPushRequest;
import com.debug.wust.dubbo.two.server.servive.OrderRecordService;
import com.google.common.collect.Maps;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.core.MediaType;
import java.util.Map;

@RestController
public class OrderRecordController {
    private static final org.slf4j.Logger log= LoggerFactory.getLogger(OrderRecordController.class);

    private static final String prefix="order";

    @Autowired
    private OrderRecordService orderRecordService;

    @RequestMapping(value = prefix+"/record/push",method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON)
    public Map<String, Object> pushRecord(@RequestBody RecordPushRequest pushRequest){

        Map<String,Object> resMap = Maps.newHashMap();
        resMap.put("code",0);
        resMap.put("msg","成功");
        try{
            log.info("接收到请求数据:{}",pushRequest);

            //处理用户下单数据,发起用户下单接口调用
            //orderRecordService.pushOrder(pushRequest);
            //通用通用http
            orderRecordService.pushOrderV2(pushRequest);

        }catch (Exception e){
            log.error("面向客户:下单异常",e.fillInStackTrace());
            resMap.put("code",-1);
            resMap.put("msg","失败");
        }
        return resMap;
    }
}
