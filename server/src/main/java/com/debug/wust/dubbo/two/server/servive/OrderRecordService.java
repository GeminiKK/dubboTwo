package com.debug.wust.dubbo.two.server.servive;

import com.debug.wust.dubbo.two.server.data.DubboRecordResponse;
import com.debug.wust.dubbo.two.server.request.RecordPushRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class OrderRecordService {

    private static final org.slf4j.Logger log= LoggerFactory.getLogger(OrderRecordService.class);

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private HttpService httpService;

    private static final String url = "http://localhost:9013/v1/record/push";

    private OkHttpClient httpClient = new OkHttpClient();



    public void pushOrder(RecordPushRequest pushRequest) throws IOException {
        try {
            //构造builder
            Request.Builder builder = new Request.Builder().url(url).header("Content-Type","application/json");
            //构造请求体
            RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"),objectMapper.writeValueAsString(pushRequest));
            //构造请求
            Request request = builder.post(requestBody).build();
            //发起请求
            Response response = httpClient.newCall(request).execute();
            //log.info(response.body().toString());

        }catch (Exception e){
            throw e;
        }
    }

    //采用通用化http服务类实战
    public void pushOrderV2(RecordPushRequest pushRequest) throws Exception {
        try {
            Map<String,String> headerMap = new HashMap<>();
            headerMap.put("Content-Type","application/json");
            String res = httpService.post(url, headerMap, "application/json", objectMapper.writeValueAsString(pushRequest));

            log.info("响应结果:{}",res);

            //map解析,针对响应数据字段比较少的场景
            Map<String,Object> resMap = objectMapper.readValue(res, Map.class);
            log.info("得到的响应结果解析:{} ",resMap);
            Integer code = (Integer) resMap.get("code");
            String msg = (String) resMap.get("msg");
            Integer data = (Integer) resMap.get("data");
            log.info("code={} msg={} data={}",code,msg,data);

            //对象解析,这种方式更加通用,数据字段比较多/复杂的解析
            DubboRecordResponse recordResponse = objectMapper.readValue(res, DubboRecordResponse.class);
            log.info("得到的响应解析结果:{}",recordResponse);

        }catch (Exception e){
            throw e;
        }
    }
}
