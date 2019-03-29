package com.elasticsearch.controller;

import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.support.WriteRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @Author: jimmy
 * @Date: 2019/3/21
 */
@RestController
public class TestController {
    public static final String INDEX_NAME   = "city_index";
    public static final String MAPPING_NAME = "tutorial_mapping";

    @Autowired
    RestHighLevelClient restHighLevelClient;

    @RequestMapping("/del")
    public String del() throws IOException {
        DeleteRequest request = new DeleteRequest(INDEX_NAME, MAPPING_NAME, "1");
        request.timeout(TimeValue.timeValueMinutes(2));
        request.setRefreshPolicy(WriteRequest.RefreshPolicy.WAIT_UNTIL);
        DeleteResponse deleteResponse = restHighLevelClient.delete(request, RequestOptions.DEFAULT);
        System.out.println(deleteResponse.status());
        //文档不存在
        if (deleteResponse.getResult() == DocWriteResponse.Result.NOT_FOUND) {
            System.out.println("文档不存在");
        }
        return "success";
    }



    @RequestMapping("/test")
    public String test(){
        System.out.println(restHighLevelClient);
        return "success";
    }
}
