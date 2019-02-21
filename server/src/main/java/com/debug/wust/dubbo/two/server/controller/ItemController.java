package com.debug.wust.dubbo.two.server.controller;

import com.debug.wust.dubbo.one.api.response.BaseResponse;
import com.debug.wust.dubbo.one.api.service.IDubboItemService;
import com.google.common.collect.Maps;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class ItemController {

    private static final org.slf4j.Logger log= LoggerFactory.getLogger(ItemController.class);

    private static final String prefix="item";

    @Autowired
    private IDubboItemService dubboItemService;

    /**
     * 用户商城列表查询
     * @return
     */
    @RequestMapping(value = prefix+"/list",method = RequestMethod.GET)
    public Map<String,Object> list(){
        Map<String,Object> resMap = Maps.newHashMap();
        resMap.put("code","0");
        resMap.put("msg","成功");

        //调用服务的提供方,dubboOne提供的列表查询功能
        try {
            BaseResponse response = dubboItemService.listItems();
            if(response!=null && response.getCode().equals(0)){
                resMap.put("data",response.getData());
            }

        }catch (Exception e){
            e.printStackTrace();
            resMap.put("code","-1");
            resMap.put("msg","失败");
        }
        return resMap;
    }

    /**
     * 用户商城列表分页查询
     * @return
     */
    @RequestMapping(value = prefix+"/page/list",method = RequestMethod.GET)
    public Map<String,Object> pageList(Integer pageNo,Integer PageSize){
        if(pageNo == null||PageSize == null||pageNo<=0||PageSize<=0){
            pageNo=1;
            PageSize=2;
        }

        Map<String,Object> resMap = Maps.newHashMap();
        resMap.put("code","0");
        resMap.put("msg","成功");

        //调用服务的提供方,dubboOne提供的列表分页查询功能
        try {
            BaseResponse response = dubboItemService.listPageItems(pageNo,PageSize);
            if(response!=null && response.getCode().equals(0)){
                resMap.put("data",response.getData());
            }

        }catch (Exception e){
            e.printStackTrace();
            resMap.put("code","-1");
            resMap.put("msg","失败");
        }
        return resMap;
    }

    /**
     * 用户商城列表分页查询
     * @return
     */
    @RequestMapping(value = prefix+"/page/list/params",method = RequestMethod.GET)
    public Map<String,Object> pageListParams(Integer pageNo,Integer PageSize,String search){
        if(pageNo == null||PageSize == null||pageNo<=0||PageSize<=0){
            pageNo=1;
            PageSize=2;
        }
        //检查程序是否能正常执行到这里
        Map<String,Object> resMap = Maps.newHashMap();
        resMap.put("code","0");
        resMap.put("msg","成功");

        //调用服务的提供方,dubboOne提供的列表分页查询功能
        try {
            BaseResponse response = dubboItemService.listPageItemsParams(pageNo,PageSize,search);
            if(response!=null && response.getCode().equals(0)){
                resMap.put("data",response.getData());
            }

        }catch (Exception e){
            e.printStackTrace();
            resMap.put("code","-1");
            resMap.put("msg","失败");
        }
        return resMap;
    }
}
